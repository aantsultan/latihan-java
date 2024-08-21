package com.latihan.java.spring.jasperreport.service;

import com.latihan.java.spring.jasperreport.config.ApplicationProperties;
import com.latihan.java.spring.jasperreport.dto.CreateUserRequest;
import com.latihan.java.spring.jasperreport.dto.UserDto;
import com.latihan.java.spring.jasperreport.model.User;
import com.latihan.java.spring.jasperreport.repository.UserRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ResourceLoader resourceLoader;
    private final ApplicationProperties applicationProperties;

    @Override
    public String save(CreateUserRequest request) {
        User user = new User();

        if (ObjectUtils.isEmpty(request.getName())) {
            throw new RuntimeException("Bad Request");
        }

        user.setName(request.getName());
        userRepository.save(user);
        return "OK";
    }

    @Override
    public List<UserDto> getAll() {
        List<User> result = userRepository.findAll();
        return result.stream().map(data -> UserDto.builder()
                .id(data.getId())
                .name(data.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public Resource generatePdf() {
        List<User> result = userRepository.findAll();
        @Cleanup InputStream userreport = resourceLoader
                .getResource(applicationProperties.getReportPath() + "user-report.jrxml")
                .getInputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperReport jasperReport = JasperCompileManager.compileReport(userreport);
        byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, new HashMap<>(), dataSource);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return new InputStreamResource(inputStream);
    }

    @Override
    @SneakyThrows
    public Resource generateExcel() {
        List<User> result = userRepository.findAll();
        @Cleanup InputStream userreport = resourceLoader
                .getResource(applicationProperties.getReportPath() + "user-report.jrxml")
                .getInputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperReport jasperReport = JasperCompileManager.compileReport(userreport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setSheetNames(new String[]{"user_report"});
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setWhitePageBackground(true);
        configuration.setIgnoreGraphics(false);
        configuration.setFontSizeFixEnabled(false);

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        byte[] bytes = baos.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return new InputStreamResource(inputStream);
    }


}
