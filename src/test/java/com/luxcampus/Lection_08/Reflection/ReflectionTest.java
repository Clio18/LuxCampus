package com.luxcampus.Lection_08.Reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static com.luxcampus.Lection_08.Reflection.Reflection.*;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    @DisplayName("test to get object on empty object and null")
    @Test
    void testGetObjectWithEmptyObjectAndNull() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Person person = new Person();
        assertEquals(person, getObject(Person.class));
        assertEquals(person.getClass(), getObject(Person.class).getClass());
        assertTrue(getObject(Person.class) != null);
        assertThrows(NullPointerException.class, () -> {
            getObject(null);
        });
    }

    @DisplayName("test to verify quantity of methods without parameters")
    @Test
    void testHelperInvokeMethodsWithoutParameters() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        assertEquals(8, helperInvokeMethodsWithoutParameters(person).size());
    }

    @DisplayName("test method to verify the name of final method")
    @Test
    void printSignatureOfFinalMethodsByName() throws InvocationTargetException, IllegalAccessException {
        String methodName = "declare";
        Person person = new Person();
        assertEquals(methodName, helperSignatureOfMethodsWithFinalByItName(person));
        assertThrows(NullPointerException.class, () -> {
            helperSignatureOfMethodsWithFinalByItName(null);
        });
    }

    @DisplayName("test to verify the identity of method's return")
    @Test
    void printSignatureOfFinalMethodsByMethod() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        assertEquals(Person.declare(), helperSignatureOfMethodsWithFinalByMethodSignature(person).invoke(person));
    }

    @DisplayName("test to verify quantity of not public method")
    @Test
    void invokeNotPublicMethodsByTestMethods() {
        assertEquals(1, helperInvokeNotPublicMethods(Person.class).size());
    }

    @DisplayName("test to verify the name of super class")
    @Test
    void helperGetSuperClassName() {
        assertEquals(Person.class, helperGetSuperClass(GoodPerson.class));
    }

    @DisplayName("test to verify quantity of interfaces")
    @Test
    void helperGetInterfaces() {
        assertEquals(2, helperGetInterfacesInClass(GoodPerson.class).size());
    }

    @DisplayName("test to verify set default values of the fields")
    @Test
    void setDefaultsValues() throws IllegalAccessException {
        Person person = new Person(10, "A", "n");
        setDefaults(person);
        assertEquals(new Person(0, null, "n"), person);
    }
}