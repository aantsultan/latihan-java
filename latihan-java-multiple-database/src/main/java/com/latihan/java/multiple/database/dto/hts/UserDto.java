package com.latihan.java.multiple.database.dto.hts;

import com.latihan.java.multiple.database.dto.md.SalesGroupDto;
import lombok.Data;

public @Data class UserDto {

    private long id;
    private String name;
    private String email;
    private SalesGroupDto salesGroup;
}
