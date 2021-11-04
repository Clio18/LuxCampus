package com.luxcampus.Lection_08.AnnotationGenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class QueryGeneratorTest {

    @DisplayName("Test get all objects")
    @Test
    void getAll() {
        QueryGenerator queryGenerator = new QueryGenerator();
        //get all annotation @Column from table Person @Table
        String getAllSql = queryGenerator.getAll(Person.class);
        String expectedSql = "SELECT id, person_name, salary FROM persons;";
        assertEquals(expectedSql, getAllSql);
    }

    @DisplayName("Test insert object and verify the values of it fields")
    @Test
    void insert() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        QueryGenerator queryGenerator = new QueryGenerator();
        Person person = new Person();
        person.setId(10);
        person.setName("Tom");
        person.setSalary(1000.0);

        String getAllSql = queryGenerator.insert(person);
        String expectedSql = "INSERT INTO persons (id, person_name, salary) VALUES (" + person.getId() +
                ", " + person.getName() + ", " + person.getSalary() + ");";
        assertEquals(expectedSql, getAllSql);
    }

    @DisplayName("Test insert null object and verify exception throwing")
    @Test
    void insertIfNull() {
        QueryGenerator queryGenerator = new QueryGenerator();
        assertThrows(NullPointerException.class, () -> {
            queryGenerator.insert(null);
        });
    }

    @DisplayName("Test update object by id and verify the values of it fields")
    @Test
    void update() throws IllegalAccessException {
        QueryGenerator queryGenerator = new QueryGenerator();
        Person person = new Person();
        person.setId(10);
        person.setName("Tom");
        person.setSalary(1000.0);
        String getAllSql = queryGenerator.update(person);
        String expectedSql = "UPDATE persons SET person_name = " +
                person.getName() + ", salary = " + person.getSalary() + " WHERE id = " + person.getId() + ";";

        assertEquals(expectedSql, getAllSql);
    }

    @DisplayName("Test update if null object and verify exception throwing")
    @Test
    void updateIfNull() {
        QueryGenerator  queryGenerator = new QueryGenerator();
        assertThrows(NullPointerException.class, ()->{
            queryGenerator.update(null);
        });
    }

    @DisplayName("Test get object by id and verify the values of it fields")
    @Test
    void getById() {
        QueryGenerator queryGenerator = new QueryGenerator();
        Person person = new Person();
        person.setId(100);
        String getAllSql = queryGenerator.getById(Person.class, 100);
        String expectedSql = "SELECT id, person_name, salary FROM persons WHERE id = " + person.getId() + ";";
        assertEquals(expectedSql, getAllSql);
    }

    @DisplayName("Test get object by id if id is null and verify exception throwing")
    @Test
    void getByIfIdNull() {
        QueryGenerator queryGenerator = new QueryGenerator();
        assertThrows(NullPointerException.class, () -> {
            queryGenerator.getById(Person.class, null);
        });
    }

    @DisplayName("Test delete object by id")
    @Test
    void delete() {
        QueryGenerator queryGenerator = new QueryGenerator();
        Person person = new Person();
        person.setId(100);
        String getAllSql = queryGenerator.delete(Person.class, 100);
        String expectedSql = "DELETE FROM persons WHERE id = " + person.getId() + ";";
        assertEquals(expectedSql, getAllSql);
    }

    @DisplayName("Test delete object by id if id is null")
    @Test
    void deleteIfIdNull() {
        QueryGenerator queryGenerator = new QueryGenerator();
        assertThrows(NullPointerException.class, () -> {
            queryGenerator.delete(Person.class, null);
        });
    }
}