package com.latihan.java.reflection;

import com.latihan.java.reflection.data.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyTest {

    @Test
    void testProxy() {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("getName")) {
                    return "Car Proxy";
                }

                if (method.getName().equals("run")) {
                    System.out.println("Car is running");
                }

                return null;
            }
        };

        Car car = (Car) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Car.class}, invocationHandler);

        Assertions.assertEquals("Car Proxy", car.getName());
        car.run();
    }

}
