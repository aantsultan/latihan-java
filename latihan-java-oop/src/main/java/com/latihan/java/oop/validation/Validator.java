package com.latihan.java.oop.validation;

import com.latihan.java.oop.annotation.NotBlank;
import com.latihan.java.oop.exception.ValidationException;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object request){
        Field[] fields = request.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.getAnnotation(NotBlank.class) != null){
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(request);
                    if(value == null || value.isEmpty()){
                        throw new ValidationException("Field " + field.getName() + " is blank");
                    }
                } catch (IllegalAccessException e){
                    System.out.println("Tidak bisa akses field " + field.getName());
                }
            }
        }
    }

}
