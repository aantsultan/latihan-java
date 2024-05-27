package com.latihan.java.reflection;

import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class PrimitiveTypeTest {

    @Test
    void testPrimitiveType(){
        Class<Integer> integerClass = int.class;
        Class<Long> longClass = long.class;
        Class<Boolean> booleanClass = boolean.class;

        System.out.println(integerClass.isPrimitive());
        System.out.println(longClass.isPrimitive());
        System.out.println(booleanClass.isPrimitive());
    }

    @Test
    void testGetField() throws NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Field age = personClass.getDeclaredField("age");
        Assertions.assertTrue(age.getType().isPrimitive());
    }

    @Test
    void testGetFieldValue() throws NoSuchFieldException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Field age = personClass.getDeclaredField("age");

        age.setAccessible(true);
        Person person = new Person("aant", "sultan");
        person.setAge(20);

        Assertions.assertTrue(age.getType().isPrimitive());
        Assertions.assertEquals(age.getInt(person), person.getAge());
    }

    @Test
    void testInvokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Method setAge = personClass.getDeclaredMethod("setAge", int.class);

        Person person = new Person();
        setAge.invoke(person, 25);

        Assertions.assertEquals(25, person.getAge());
    }
}
