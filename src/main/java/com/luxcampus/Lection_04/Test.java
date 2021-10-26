package com.luxcampus.Lection_04;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        EmployeeService employeeService = new EmployeeService(employeeFactory.generateDefaultEmployees());

        employeeService.printEmployees();

        double actualCash = employeeService.calculateSalaryAndBonus();
        double expectedCash = 34400;
        assertEquals("calculateSalaryAndBonus", actualCash, expectedCash);

        Employee actualEmployee = employeeService.getById(2);
        Employee expectedEmployee = new Employee(2, "Ab", 22, 5000.0, Gender.FEMALE, 11);
        expectedEmployee.setId(2);
        assertEquals("getById", actualEmployee, expectedEmployee);

        Employee [] actualEmployeeByName = employeeService.getByName("Ab");
        assertEquals("getByName", actualEmployeeByName[0], expectedEmployee);

        employeeService.sortByName();
        Employee [] expectedEmployees = new Employee[]{
                new Employee("Aa"),
                new Employee("Aa"),
                new Employee("Ab"),
                new Employee("Ac"),
        };
        assertEqualsArray("sortByName", employeeService.employees, expectedEmployees);

        employeeService.sortByNameAndSalary();
        Employee [] expectedEmployeesNameAndSalary = new Employee[]{
                new Employee("Aa", 15000),
                new Employee("Aa", 5000),
                new Employee("Ab", 5000),
                new Employee("Ac", 5000),
        };
        assertEqualsArrayNameAndSalary("sortByNameAndSalary", employeeService.employees, expectedEmployeesNameAndSalary);

        Employee employee = new Employee(1, "TOMMY", 89, 7777.00, Gender.MALE, 9);
        employeeService.edit(employee);
        Employee actualNewEmployee = employeeService.getById(1);
        assertEquals("edit", actualNewEmployee, employee);

        employeeService.remove(1);
        Employee [] expectedEmployeesUpdated = new Employee[]{
                new Employee("Aa", 15000),
                new Employee("Ab", 5000),
                new Employee("Ac", 5000),
        };
        assertEqualsArrayNameAndSalary("removed", employeeService.employees, expectedEmployeesUpdated);


    }
    static void assertEquals(String testId, double actual, double expected) {
        if (expected == actual) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }
    static void assertEquals(String testId, Employee actual, Employee expected) {
        if (expected.equals(actual)) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }
    static void assertEqualsArray(String testId, Employee[] actual, Employee[] expected) {
        int count = 0;
            for (int i = 0; i < actual.length; i++) {
                if (actual[i].getName().equals(expected[i].getName())) {
                    count++;
                }
            }
            if (count == actual.length) {
                System.out.println("TEST " + testId + " PASSED");
            } else {
                System.out.println("TEST " + testId + " FAILED!");
                System.out.print("Expected: ");
                System.out.println(Arrays.toString(expected));
                System.out.print("Actual: ");
                System.out.println(Arrays.toString(actual));
            }
        }
    static void assertEqualsArrayNameAndSalary(String testId, Employee[] actual, Employee[] expected) {
        int count = 0;
        for (int i = 0; i < actual.length; i++) {
            if (actual[i].getName().equals(expected[i].getName())&&actual[i].getSalary()==expected[i].getSalary()) {
                count++;
            }
        }
        if (count == actual.length) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED!");
            System.out.print("Expected: ");
            System.out.println(Arrays.toString(expected));
            System.out.print("Actual: ");
            System.out.println(Arrays.toString(actual));
        }
    }


}