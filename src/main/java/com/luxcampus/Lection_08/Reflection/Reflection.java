package com.luxcampus.Lection_08.Reflection;

import com.luxcampus.Lection_07.ArrayList;

import java.lang.reflect.*;

public class Reflection {

    //    Метод принимает класс и возвращает созданный объект этого класса
    static Person getObject(Class clazz) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Person) clazz.getDeclaredConstructor().newInstance();
    }

    //    Метод принимает object и вызывает у него все методы без параметров
    static void invokeMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getParameters().length == 0) {
                method.invoke(object);
            }
        }
    }

    //    Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    static void printSignatureOfMethodsWithFinal(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    //    Метод принимает Class и выводит все не публичные методы этого класса
    static void invokeNotPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    //    Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    static void getSuperClassAndInterfaces(Class clazz) {
        for (Class anInterface : clazz.getInterfaces()) {
            System.out.println(anInterface.getCanonicalName());
        }
        System.out.println(clazz.getSuperclass().getCanonicalName());
    }

    //    Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    static void setDefaults (Object object) throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if(Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
                if(field.getType().isPrimitive()) {
                    if (field.getType().equals(int.class)) {
                        field.setInt(object, 0);
                    } else if (field.getType().equals(double.class)) {
                        field.setDouble(object, 0.0);
                    } else if (field.getType().equals(long.class)) {
                        field.setLong(object, 0L);
                    } else if (field.getType().equals(boolean.class)) {
                        field.setBoolean(object, false);
                    }
                }else {
                    field.set(object, null);
                }
            }
        }
    }


    /*
    helper methods for testing performance
     */
    static ArrayList helperGetInterfacesInClass(Class clazz) {
        ArrayList arrayList = new ArrayList();
        for (Class anInterface : clazz.getInterfaces()) {
            arrayList.add(anInterface);
        }
        return arrayList;
    }
    static Class helperGetSuperClass(Class clazz) {
        return clazz.getSuperclass();
    }
    static ArrayList helperInvokeNotPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : methods) {
            if (!Modifier.isPublic(method.getModifiers())) {
                arrayList.add(method);
            }
        }
        return arrayList;
    }
    static String helperSignatureOfMethodsWithFinalByItName(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        String name = "";
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isFinal(method.getModifiers())) {
                name = method.getName();
            }
        }
        return name;
    }
    static Method helperSignatureOfMethodsWithFinalByMethodSignature(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        Method result = null;
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isFinal(method.getModifiers())) {
                result = method;
            }
        }
        return result;
    }
    static ArrayList helperInvokeMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        ArrayList arrayList = new ArrayList();
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getParameters().length == 0) {
                arrayList.add(method);
            }
        }
        return arrayList;
    }

}
