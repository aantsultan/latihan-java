package com.latihan.java.spring.jasperreport.service;

import com.latihan.java.spring.jasperreport.config.ApplicationProperties;
import com.latihan.java.spring.jasperreport.model.WeavingSizing;
import com.latihan.java.spring.jasperreport.repository.WeavingSizingRepository;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MStopServiceImpl implements MStopService {

    private final ResourceLoader resourceLoader;
    private final ApplicationProperties applicationProperties;
    private final WeavingSizingRepository weavingSizingRepository;

    @Override
    @SneakyThrows
    public Resource generateExcelUsingSubReport() {
        List<WeavingSizing> result = weavingSizingRepository.findAll();

        @Cleanup InputStream reportStream = resourceLoader
                .getResource(applicationProperties.getReportPath() + "m-stop-laporan-kerja.jrxml")
                .getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        @Cleanup InputStream subReportStream = resourceLoader
                .getResource(applicationProperties.getReportPath() + "m-stop-laporan-kerja_cek-mutu.jrxml")
                .getInputStream();
        JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataSource", dataSource);
        parameters.put("jasperSubReport", jasperSubReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setSheetNames(new String[]{"report"});
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setWhitePageBackground(false);
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

    @Override
    @SneakyThrows
    public Resource generateExcelUsingTable() {
        List<WeavingSizing> result = weavingSizingRepository.findAll();

        @Cleanup InputStream reportStream = resourceLoader
                .getResource(applicationProperties.getReportPath() + "m-stop-table.jrxml")
                .getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataSource", dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setSheetNames(new String[]{"report"});
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setWhitePageBackground(false);
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
