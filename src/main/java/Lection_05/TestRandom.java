package Lection_05;

public class TestRandom {
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        EmployeeService employeeService = new EmployeeService(employeeFactory.generateEmployees(5));

        employeeService.printEmployees();

        double actualCash = employeeService.calculateSalaryAndBonus();
        System.out.println(actualCash);

        Employee actualEmployee = employeeService.getById(2);
        System.out.println(actualEmployee.toString());

        employeeService.sortByName();
        employeeService.printEmployees();
        System.out.println();

        Designer designer = new Designer(6, "LEON", 100, 7777, Gender.MALE, 1.2, 20);
        employeeService.add(designer);
        employeeService.printEmployees();
        System.out.println();

        employeeService.remove(5);
        employeeService.remove(1);
        employeeService.printEmployees();
    }
}
