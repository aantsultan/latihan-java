package com.latihan.java.restfulapi.flutter.helper;

public enum ErrorEnum {

    ERROR_401("Username or password is incorrect");

    private final String value;

    ErrorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
