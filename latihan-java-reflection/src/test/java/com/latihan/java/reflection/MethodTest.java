package com.latihan.java.reflection;

import com.latihan.java.reflection.model.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodTest {

    @Test
    void methodTest(){
        Class<Person> personClass = Person.class;
        Method[] methods = personClass.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(Arrays.toString(method.getParameters()));
            System.out.println(Arrays.toString(method.getExceptionTypes()));
        }
    }

    @Test
    void getMethodValueTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Method getFirstName = personClass.getDeclaredMethod("getFirstName");

        Person person = new Person("aant", "sultan");
        String firstName = (String)getFirstName.invoke(person);
        System.out.println(firstName);
    }

    @Test
    void getMethodPrivate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Method getFirstName = personClass.getDeclaredMethod("getTest", int.class);
        getFirstName.setAccessible(true);

        Person person = new Person("aant", "sultan");
        String firstName = (String)getFirstName.invoke(person, 1);
        System.out.println(firstName);
    }
}
