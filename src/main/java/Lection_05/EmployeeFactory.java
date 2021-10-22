package Lection_05;

import Lection_05.Data.Gender;
import Lection_05.Data.NameSource;
import Lection_05.Entity.Designer;
import Lection_05.Entity.Developer;
import Lection_05.Entity.Employee;
import Lection_05.Entity.Manager;

import java.util.Random;

public class EmployeeFactory {

    //генерирует заданое количество сотрудников (Mock for testing)
    public Employee[] generateDefaultEmployees() {
        return new Employee[]{
                new Employee(1, "Aa", 22, 5000, Gender.MALE, 11),
                new Employee(2, "Ab", 22, 5000, Gender.FEMALE, 11),
                new Employee(3, "Ac", 22, 5000, Gender.FEMALE, 11),
                new Employee(4, "Aa", 22, 15000, Gender.MALE, 11),
        };
    }


    //Написать класс EmployeeFactory с методом:
    //generateEmployees(size) -> генерирует случайным образом заданое количество сотрудников
    public Employee[] generateEmployees(int size) {
        NameSource nameSource = new NameSource();
        Employee[] employees = new Employee[size];
        Random random = new Random();
        long counter = 0;
        for (int i = 0; i < employees.length; i++) {
            //60 years is maximum
            int age = 18 + random.nextInt(42);
            double salaryValue = 1500 + (5000 - 1500) * random.nextDouble();
            //format of salary xxxx.xx
            double salary = (Math.round(salaryValue * 100)) / 100.0;
            int fixedBugs = random.nextInt(20);
            Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
            long id = ++counter;
            String name = createEmployeeName(random, nameSource, gender);
            int choice = random.nextInt(3);
            if (choice == 0) {
                int workingDays = random.nextInt(24) + 1;
                //format of salary x.xx
                double rate = (Math.round(random.nextDouble() * 10 * 100)) / 100.0;
                Designer employee = new Designer(id, name, age, salary, gender, rate, workingDays);
                employees[i] = employee;
            } else if (choice == 1) {
                Manager employee = new Manager(id, name, age, salary, gender);
                employees[i] = employee;
            } else {
                int coeff = random.nextBoolean()?2:0;
                Developer employee = new Developer(id, name, age, salary, gender, fixedBugs, coeff);
                employees[i] = employee;
            }
        }
        return employees;
    }

    public String createEmployeeName(Random random, NameSource nameSource, Gender gender) {
        String employeeName = null;
        if (gender.equals(Gender.MALE)) {
            int index = random.nextInt(nameSource.getMale().length);
            employeeName = nameSource.getMale()[index];
        } else if (gender.equals(Gender.FEMALE)) {
            int index = random.nextInt(nameSource.getFemale().length);
            employeeName = nameSource.getFemale()[index];

        } else {
            int index = random.nextInt(nameSource.getOther().length);
            employeeName = nameSource.getOther()[index];

        }
        return employeeName;
    }
}
