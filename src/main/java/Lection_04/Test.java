package Lection_04;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        EmployeeService employeeService = new EmployeeService(employeeFactory.generateDefaultEmployees());

        employeeService.printEmployees();

        double actualCash = employeeService.calculateSalaryAndBonus();
        double expectedCash = 34400;
        assertEquals("calculateSalaryAndBonus", actualCash, expectedCash);

//        System.out.println(employeeService.getById(1));
//        System.out.println();
//        System.out.println(employeeService.getById(100));
//        System.out.println(Arrays.toString(employeeService.getByName("Levi")));
//        employeeService.sortByName();
//        employeeService.printEmployees();
//        System.out.println();
//        employeeService.sortByNameAndSalary();
//        employeeService.printEmployees();
//        System.out.println();
//        Employee e = new Employee("TOMMY", 89, 7777.00, Gender.MALE, 9);
//        e.setId(1);
//        System.out.println(employeeService.edit(e));
//        System.out.println();
//        employeeService.printEmployees();
//        System.out.println();
//        System.out.println(employeeService.remove(1));
//        System.out.println();
//        employeeService.printEmployees();
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
        if (expected == actual) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

}