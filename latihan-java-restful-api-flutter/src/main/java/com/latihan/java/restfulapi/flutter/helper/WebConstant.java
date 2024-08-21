package com.latihan.java.restfulapi.flutter.helper;

public class WebConstant {

    public static final String[] WHITELIST_URL = {"/api/users", "/api/cat"};
    public static final long EXPIRE_TIME = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
}
