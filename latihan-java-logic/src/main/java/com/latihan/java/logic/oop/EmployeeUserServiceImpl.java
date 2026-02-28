package com.latihan.java.logic.oop;

public class EmployeeUserServiceImpl implements UserService, EmployeeService{

    @Override
    public String userName() throws Exception {
        return "EmployeeUser";
    }
}
