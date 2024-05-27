package com.latihan.java.reflection;

import com.latihan.java.reflection.data.Data;
import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

class ParameterizeTypeTest {

    @Test
    void testGetMethodGeneric() throws NoSuchMethodException {
        Class<Person> personClass = Person.class;
        Method getHobbies = personClass.getDeclaredMethod("getHobbies");
        Type type = getHobbies.getGenericReturnType();
        Assertions.assertInstanceOf(ParameterizedType.class, type);

        if(type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println(parameterizedType.getRawType());
            System.out.println(parameterizedType.getOwnerType());
            System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
        }
    }

    @Test
    void testGetGenericParameterTypes() throws NoSuchMethodException {
        Class<Person> personClass = Person.class;
        Method getHobbies = personClass.getDeclaredMethod("setHobbies", List.class);
        Type[] types = getHobbies.getGenericParameterTypes();

        for (Type type : types) {
            System.out.println(type.getClass());
            if(type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.println(parameterizedType.getRawType());
                System.out.println(parameterizedType.getOwnerType());
                System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
            }
        }
    }

    @Test
    void testFieldGeneric() throws NoSuchMethodException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Field hobbies = personClass.getDeclaredField("hobbies");
        hobbies.setAccessible(true);

        Type type = hobbies.getGenericType();
        System.out.println(type.getClass());

        if(type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println(parameterizedType.getRawType());
            System.out.println(parameterizedType.getOwnerType());
            System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
        }
    }

    @Test
    void testVariableType(){
        Class<Data> dataClass = Data.class;
        TypeVariable<Class<Data>>[] types = dataClass.getTypeParameters();
        for (TypeVariable<Class<Data>> variable : types) {
            System.out.println(variable.getName());
            System.out.println(Arrays.toString(variable.getBounds()));
            System.out.println(variable.getGenericDeclaration());
        }
    }
}
