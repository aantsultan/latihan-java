package com.latihan.java.reflection.helper;

import com.latihan.java.reflection.annotation.NotBlank;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object person) throws IllegalAccessException {
        Class<?> aClass = person.getClass();
        for(Field field : aClass.getDeclaredFields()){
            field.setAccessible(true);
            NotBlank notBlank = field.getAnnotation(NotBlank.class);
            String value = (String) field.get(person);
            if(notBlank != null){
                if(notBlank.allowEmptyString()){
                    // ignore
                } else {
                    value = value.trim();
                }
            }

            if ("".equalsIgnoreCase(value)){
                throw new RuntimeException("Field " + field.getName() + " must not blank");
            }
        }
    }

}
