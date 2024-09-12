package com.latihan.java.spring.postgresql.jsonb.service;

import com.latihan.java.spring.postgresql.jsonb.dto.*;
import com.latihan.java.spring.postgresql.jsonb.model.Address;
import com.latihan.java.spring.postgresql.jsonb.model.Profile;
import com.latihan.java.spring.postgresql.jsonb.model.User;
import com.latihan.java.spring.postgresql.jsonb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public ApiResponseDto<String> save(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        Address address1 = new Address();
        Address address2 = new Address();

        ProfileDto profileDto = requestDto.getProfile();
        AddressDto addressDto1 = profileDto.getAddress1();
        AddressDto addressDto2 = profileDto.getAddress2();

        address1.setName(addressDto1.getName());
        address1.setCountry(addressDto1.getCountry());

        address2.setName(addressDto2.getName());
        address2.setCountry(addressDto2.getCountry());

        Profile profile = new Profile();
        profile.setAddress1(address1);
        profile.setAddress2(address2);

        user.setProfile(profile);

        repository.save(user);

        return ApiResponseDto.<String>builder()
                .data("OK")
                .message("SUKSES")
                .build();
    }

    @Override
    public ApiResponseDto<List<UserResponseDto>> findAll() {
        List<User> result = repository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user : result) {
            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getName());

            Profile profile = user.getProfile();
            Address address1 = profile.getAddress1();
            Address address2 = profile.getAddress2();

            AddressDto addressDto1 = new AddressDto();
            addressDto1.setName(address1.getName());
            addressDto1.setCountry(address1.getCountry());
            AddressDto addressDto2 = new AddressDto();
            addressDto2.setName(address2.getName());
            addressDto2.setCountry(address2.getCountry());

            ProfileDto profileDto = new ProfileDto();
            profileDto.setAddress1(addressDto1);
            profileDto.setAddress2(addressDto2);

            dto.setProfile(profileDto);
            dtos.add(dto);
        }

        return ApiResponseDto.<List<UserResponseDto>>builder()
                .data(dtos)
                .message("SUKSES")
                .build();
    }
}
