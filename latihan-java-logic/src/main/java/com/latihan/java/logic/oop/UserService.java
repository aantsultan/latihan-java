package com.latihan.java.logic.oop;

public interface UserService {

    String userName() throws Exception;
    static String password(){
        return "ADMIN";
    }

}
