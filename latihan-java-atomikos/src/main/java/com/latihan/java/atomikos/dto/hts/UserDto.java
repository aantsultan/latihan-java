package com.latihan.java.atomikos.dto.hts;

import com.latihan.java.atomikos.dto.md.SalesGroupDto;
import lombok.Data;

public @Data class UserDto {

    private long id;
    private String name;
    private String email;
    private SalesGroupDto salesGroup;
}
