package com.latihan.java.reflection;

import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterTest {

    @Test
    void parameterTest() {
        Class<Person> personClass = Person.class;
        Method[] methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Parameter name : " + parameter.getName());
                System.out.println("Parameter type : " + parameter.getType());
            }
            System.out.println("=========");
        }
    }

    @Test
    void setParameterTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Method setFirstName = personClass.getDeclaredMethod("setFirstName", String.class);

        Person person = new Person("aant", "sultan");
        setFirstName.invoke(person, "Joko");

        System.out.println(person.getFirstName());
    }
}
