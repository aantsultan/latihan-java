package com.latihan.java.oop;

import com.latihan.java.oop.dto.User;
import com.latihan.java.oop.validation.Validator;
import org.junit.jupiter.api.Test;

class ReflectionTest {

    @Test
    void userTest(){
        User user = new User();
        Validator.validate(user);
    }

}
