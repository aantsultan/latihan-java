package com.latihan.java.restfulapi.flutter.dto.response;

import com.latihan.java.restfulapi.flutter.helper.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String fullName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private Gender gender;
    private Boolean promoEvents;
    private Boolean termConditions;

}
