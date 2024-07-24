package com.latihan.java.spring.security.form.dto;

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
public class UserDto {

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Size(max = 20)
    private String password;

}
