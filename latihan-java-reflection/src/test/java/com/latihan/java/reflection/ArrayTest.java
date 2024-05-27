package com.latihan.java.reflection;

import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

class ArrayTest {

    @Test
    void testArray(){
        Class<String[]> aClass = String[].class;
        Class<int[]> integerArrayClass = int[].class;
        Class<Person> personClass = Person.class;

        Assertions.assertTrue(aClass.isArray());
        Assertions.assertTrue(integerArrayClass.isArray());
        Assertions.assertFalse(personClass.isArray());
    }

    @Test
    void testArrayMember(){
        Class<String[]> aClass = String[].class;

        System.out.println(Arrays.toString(aClass.getDeclaredFields()));
        System.out.println(Arrays.toString(aClass.getDeclaredConstructors()));
        System.out.println(Arrays.toString(aClass.getDeclaredMethods()));
        System.out.println(aClass.getComponentType());

        Assertions.assertEquals("[]", Arrays.toString(aClass.getDeclaredFields()));
        Assertions.assertEquals("[]", Arrays.toString(aClass.getDeclaredConstructors()));
        Assertions.assertEquals("[]", Arrays.toString(aClass.getDeclaredMethods()));
    }

    @Test
    void testArrayManipulation(){
        Class<String[]> stringArrayClass = String[].class;
        String[] array = (String[]) Array.newInstance(stringArrayClass.getComponentType(), 10);
        System.out.println(Arrays.toString(array));

        Array.set(array, 0, "aant");
        Array.set(array, 1, "sultan");
        Array.set(array, 2, "rahmanya");

        System.out.println(Arrays.toString(array));

        Assertions.assertEquals("aant", Array.get(array, 0));
        Assertions.assertEquals("sultan", Array.get(array, 1));
        Assertions.assertEquals("rahmanya", Array.get(array, 2));

    }
}
