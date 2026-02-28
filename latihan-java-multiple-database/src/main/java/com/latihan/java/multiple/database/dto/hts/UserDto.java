package com.latihan.java.multiple.database.dto.hts;

public class UserDto {

    private long id;
    private String name;
    private String email;
    private long salesGroupId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getSalesGroupId() {
        return salesGroupId;
    }

    public void setSalesGroupId(long salesGroupId) {
        this.salesGroupId = salesGroupId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salesGroupId=" + salesGroupId +
                '}';
    }
}
