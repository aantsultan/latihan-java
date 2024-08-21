package com.latihan.java.spring.jasperreport.service;

import com.latihan.java.spring.jasperreport.config.ApplicationProperties;
import com.latihan.java.spring.jasperreport.dto.BeamKeDto;
import com.latihan.java.spring.jasperreport.dto.CreateWeavingSizingRequest;
import com.latihan.java.spring.jasperreport.dto.WeavingSizingDto;
import com.latihan.java.spring.jasperreport.dto.WeavingSizingReportDto;
import com.latihan.java.spring.jasperreport.helper.DtoHelper;
import com.latihan.java.spring.jasperreport.model.WeavingSizing;
import com.latihan.java.spring.jasperreport.repository.WeavingSizingRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeavingSizingServiceImpl implements WeavingSizingService {

    private final ResourceLoader resourceLoader;
    private final ApplicationProperties applicationProperties;
    private final WeavingSizingRepository weavingSizingRepository;

    @Override
    @SneakyThrows
    public Resource generateExcel() {
        List<WeavingSizing> result = weavingSizingRepository.findAll();
        List<WeavingSizingReportDto> dtos = new ArrayList<>();
        String tanggal = null;
        String jenisSetKain = null;
        String jenisKain = null;
        String bulanTahun = null;
        boolean isNewRecord = true;
        for (WeavingSizing data : result) {
            WeavingSizingReportDto dto = DtoHelper.toWeavingSizingReportDto(data);
            if (jenisSetKain == null && tanggal == null) {
                // set initial record
                jenisSetKain = dto.getJenisSetKain();
                tanggal = dto.getTanggal();
                jenisKain = dto.getJenisKain();
                bulanTahun = dto.getBulanTahun();
                this.setBeamKe1(data, dto);
            } else {
                if (dto.getJenisSetKain().equals(jenisSetKain)
                        && dto.getTanggal().equals(tanggal)
                        && dto.getJenisKain().equals(jenisKain)) {
                    isNewRecord = false;
                } else {
                    // set new when new jenis kain each new record
                    jenisKain = dto.getJenisKain();
                    isNewRecord = true;
                    this.setBeamKe1(data, dto);
                }

                // group by bulanTahun
//                if(dto.getBulanTahun().equals(bulanTahun)){
//                    dto.setBulanTahun("");
//                } else {
//                    bulanTahun = dto.getBulanTahun();
//                }

                // group by jenisSetKain and tanggal each has same data from data before
                if (dto.getJenisSetKain().equals(jenisSetKain) && dto.getTanggal().equals(tanggal)) {
                    dto.setJenisSetKain("");
                    dto.setTanggal("");
                } else {
                    jenisSetKain = dto.getJenisSetKain();
                    tanggal = dto.getTanggal();
                }
            }

            if (isNewRecord) {
                dtos.add(dto);
            } else {
                this.updateRecord(dtos, data);
            }
        }

        @Cleanup InputStream reportStream = resourceLoader
                .getResource(applicationProperties.getReportPath() + "sizing-input.jrxml")
                .getInputStream();
        InputStream logoStream = resourceLoader.getResource(applicationProperties.getReportPath() + "Logo AGB Outline message.png").getInputStream();
        BufferedImage image = ImageIO.read(logoStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logoImage", image);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dtos);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setSheetNames(new String[]{"report"});
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

    @Override
    public String save(CreateWeavingSizingRequest request) {
        WeavingSizing weavingSizing = new WeavingSizing();

        if (ObjectUtils.isEmpty(request.getJenisSetKain())) {
            throw new RuntimeException("Bad Request");
        }

        weavingSizing.setJenisSetKain(request.getJenisSetKain());
        weavingSizing.setTanggal(request.getTanggal());
        weavingSizing.setNoBenang(request.getNoBenang());
        weavingSizing.setJenisKain(request.getJenisKain());

        weavingSizing.setNoBeam(request.getNoBeam());
        weavingSizing.setBeamKe(request.getBeamKe());
        weavingSizing.setJumlahPcsPerBeam(request.getJumlahPcsPerBeam());

        weavingSizing.setBulanTahun(request.getBulanTahun());

        weavingSizingRepository.save(weavingSizing);
        return "OK";
    }

    @Override
    public List<WeavingSizingDto> getAll() {
        List<WeavingSizing> result = weavingSizingRepository.findAll();
        return result.stream().map(DtoHelper::toWeavingSizingDto).collect(Collectors.toList());
    }

    private void setBeamKe1(WeavingSizing data, WeavingSizingReportDto dto) {
        BeamKeDto beamKeDto = BeamKeDto.builder()
                .noBeam(data.getNoBeam())
                .jumlahPcsPerBeam(data.getJumlahPcsPerBeam())
                .build();
        dto.setBeamKe1(beamKeDto);
    }

    private void setBeamKe2(WeavingSizing data, WeavingSizingReportDto dto) {
        BeamKeDto beamKeDto = BeamKeDto.builder()
                .noBeam(data.getNoBeam())
                .jumlahPcsPerBeam(data.getJumlahPcsPerBeam())
                .build();
        dto.setBeamKe2(beamKeDto);
    }

    private void setBeamKe3(WeavingSizing data, WeavingSizingReportDto dto) {
        BeamKeDto beamKeDto = BeamKeDto.builder()
                .noBeam(data.getNoBeam())
                .jumlahPcsPerBeam(data.getJumlahPcsPerBeam())
                .build();
        dto.setBeamKe3(beamKeDto);
    }

    private void updateRecord(List<WeavingSizingReportDto> dtos, WeavingSizing data) {
        int lastIndex = dtos.size() - 1;
        WeavingSizingReportDto updateDto = dtos.get(lastIndex);
        switch (data.getBeamKe()) {
            case 2:
                this.setBeamKe2(data, updateDto);
                break;
            case 3:
                this.setBeamKe3(data, updateDto);
                break;
        }
        dtos.set(lastIndex, updateDto);
    }
}
