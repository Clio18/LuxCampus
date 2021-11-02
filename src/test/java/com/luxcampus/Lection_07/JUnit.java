package com.luxcampus.Lection_07;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.Annotation;


public class JUnit {
    public static void main(String[] args) throws Throwable {
        runTests(ArrayListTest.class);
    }

    public static void runTests(Class clazz) throws Throwable {
        Method[] methods = clazz.getDeclaredMethods();
        Object obj = clazz.getConstructor().newInstance();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(obj);
                    System.out.println("Test " + method.getName() + " is success!");
                } catch (Throwable e) {
                    System.err.println("Test " + method.getName() + " failed!");
                    e.printStackTrace();
                }
            }

        }
    }

}
