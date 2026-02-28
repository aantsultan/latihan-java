package com.latihan.java.multiple.database.dto;

import com.latihan.java.multiple.database.dto.md.SalesGroupDto;

public class UserSalesGroupDto {

    private String name;
    private String email;
    private SalesGroupDto salesGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SalesGroupDto getSalesGroup() {
        return salesGroup;
    }

    public void setSalesGroup(SalesGroupDto salesGroup) {
        this.salesGroup = salesGroup;
    }
}
