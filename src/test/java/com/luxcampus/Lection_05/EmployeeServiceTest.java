package com.luxcampus.Lection_05;

import com.luxcampus.Lection_05.Data.Gender;
import com.luxcampus.Lection_05.Entity.Designer;
import com.luxcampus.Lection_05.Entity.Developer;
import com.luxcampus.Lection_05.Entity.Employee;
import com.luxcampus.Lection_05.Entity.Manager;
import com.luxcampus.Lection_05.Service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeServiceTest{
    Employee[] employees = new Employee[]{
            new Designer(1, "Aa", 20, 5000.50, Gender.MALE, 10, 10),
            new Designer(2, "Ab", 30, 2500.10, Gender.FEMALE, 5, 5),
            new Developer(3, "Ac", 25, 7000, Gender.OTHER, 10, 2),
            new Developer(4, "Aa", 27, 15000, Gender.MALE, 5, 0),
            new Manager(5, "Cc", 22, 777.9, Gender.MALE),
            new Manager(6, "Cc", 21, 7777.90, Gender.FEMALE),
    };
    EmployeeService employeeService = new EmployeeService(employees);

    @Test
    public void testCalculateSalaryAndBonus() {
        double expected = employeeService.calculateSalaryAndBonus();
        double actual = 7625.6 + 16000 + 777.9 + 7777.9;
        double notActual = 7;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        Employee expected = new Manager(5, "Cc", 22, 777.9, Gender.MALE);
        Employee actual = employeeService.getById(5);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByName() {
        Employee[] expected = new Employee[]{
                new Manager(5, "Cc", 22, 777.9, Gender.MALE),
                new Manager(6, "Cc", 21, 7777.90, Gender.FEMALE)};
        Employee[] actual = employeeService.getByName("Cc");
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByName() {
        Employee[] expected = new Employee[]{
                new Designer(1, "Aa", 20, 5000.50, Gender.MALE, 10, 10),
                new Developer(4, "Aa", 27, 15000, Gender.MALE, 5, 0),
                new Designer(2, "Ab", 30, 2500.10, Gender.FEMALE, 5, 5),
                new Developer(3, "Ac", 25, 7000, Gender.OTHER, 10, 2),
                new Manager(5, "Cc", 22, 777.9, Gender.MALE),
                new Manager(6, "Cc", 21, 7777.90, Gender.FEMALE),
        };
        Employee[] actual = employeeService.sortByName();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByNameAndSalary() {
        Employee[] expected = new Employee[]{
                new Developer(4, "Aa", 27, 15000, Gender.MALE, 5, 0),
                new Designer(1, "Aa", 20, 5000.50, Gender.MALE, 10, 10),
                new Designer(2, "Ab", 30, 2500.10, Gender.FEMALE, 5, 5),
                new Developer(3, "Ac", 25, 7000, Gender.OTHER, 10, 2),
                new Manager(6, "Cc", 21, 7777.90, Gender.FEMALE),
                new Manager(5, "Cc", 22, 777.9, Gender.MALE),
        };
        Employee[] actual = employeeService.sortByNameAndSalary();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testEdit() {
        Employee expected = new Developer(3, "Tom Ford", 50, 1000_000, Gender.MALE, 100, 2);
        employeeService.edit(expected);
        Employee actual = employeeService.getById(3);
        assertEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Employee expected = new Developer(3, "Ac", 25, 7000, Gender.OTHER, 10, 2);
        Employee actual = employeeService.remove(3);
        assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Employee expected = new Developer(10, "Tom Ford", 50, 1000_000, Gender.MALE, 100, 2);
        employeeService.add(expected);
        Employee actual = employeeService.getById(10);
        assertEquals(expected, actual);
    }
}