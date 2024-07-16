package com.latihan.java.restfulapi.flutter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;
    @NotBlank
    @Size(max = 255)
    private String password;

}
