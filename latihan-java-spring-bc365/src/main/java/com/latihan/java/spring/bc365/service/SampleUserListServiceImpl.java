package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dao.SampleUserListDao;
import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.SampleUserListDto;
import com.latihan.java.spring.bc365.model.ODataV4;
import com.latihan.java.spring.bc365.model.ODataV4SULResponse;
import com.latihan.java.spring.bc365.model.SampleUserList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleUserListServiceImpl implements SampleUserListService {

    private final SampleUserListDao sampleUserListDao;

    @Override
    public ODataV4Dto<SampleUserListDto> findAll() {
        ODataV4<SampleUserList> response = sampleUserListDao.findAll();
        List<SampleUserListDto> sampleUserListDtos = response.getValue().stream()
                .map(data -> SampleUserListDto.builder()
                        .uid(data.getUid())
                        .phone(data.getPhone())
                        .email(data.getEmail())
                        .address(data.getAddress())
                        .ulangTahun(data.getUlangTahun())
                        .odataEtag(data.getOdataEtag())
                        .build())
                .collect(Collectors.toList());
        return ODataV4Dto.<SampleUserListDto>builder()
                .odataContext(response.getOdataContext())
                .value(sampleUserListDtos)
                .build();
    }

    @Override
    public void save(SampleUserListDto request) {
        SampleUserList sample = new SampleUserList();
        sample.setUid(request.getUid());
        sample.setAddress(request.getAddress());
        sample.setEmail(request.getEmail());
        sample.setPhone(request.getPhone());
        sample.setUlangTahun(request.getUlangTahun());

        ODataV4SULResponse result = sampleUserListDao.create(sample);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save data Sample User List");
        }
    }
}
