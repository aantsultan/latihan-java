package com.latihan.java.spring.bc365.helper;

public class ApiHelper {

    private ApiHelper(){}

    public static String createQueryUid(Integer uid){
        return "(UID=" + uid + ")";
    }

}
