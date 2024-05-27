package com.latihan.java.reflection;

import com.latihan.java.reflection.helper.Validator;
import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Test;

class AnnotationTest {

    @Test
    void validatorTest() throws IllegalAccessException {
        Person person = new Person("aant", "");
        Validator.validate(person);
    }

}
