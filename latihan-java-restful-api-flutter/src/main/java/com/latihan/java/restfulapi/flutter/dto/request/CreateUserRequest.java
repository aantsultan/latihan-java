package com.latihan.java.restfulapi.flutter.dto.request;

import com.latihan.java.restfulapi.flutter.helper.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank
    @Size(max = 100)
    private String fullName;
    @NotBlank
    @Size(max = 100)
    private String email;
    @NotBlank
    @Size(max = 20)
    private String phoneNumber;
    private Date birthDate;
    private Gender gender;
    @NotBlank
    @Size(max = 255)
    private String password;
    @NotBlank
    @Size(max = 255)
    private String confirmPassword;
    private Boolean promoEvents;
    private Boolean termConditions;

}
