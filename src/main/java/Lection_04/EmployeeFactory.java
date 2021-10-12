package Lection_04;

import java.util.Random;

public class EmployeeFactory {
    private Employee [] employees;

    public EmployeeFactory(int size) {
        this.employees = generateEmployees(size);
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    //Написать класс EmployeeFactory с методом:
    //generateEmployees(size) -> генерирует случайным образом заданое количество сотрудников
    public Employee [] generateEmployees(int size){
        NameSource nameSource = new NameSource();
        Employee [] employees = new Employee[size];
        Random random = new Random();
        for (int i = 0; i < employees.length; i++) {
            //60 years is maximum
            int age = 18 + random.nextInt(42);
            double salaryValue = 1500 + (5000 - 1500) * random.nextDouble();
            //format of salary xxxx.xx
            double salary = (Math.round(salaryValue * 100))/100.0;
            int fixedBugs = random.nextInt(20);
            Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
            if(gender.equals(Gender.MALE)){
                int index = random.nextInt(nameSource.getMale().length);
                String name = nameSource.getMale()[index];
                Employee employee = new Employee(name, age, salary, gender, fixedBugs);
                employees[i] = employee;
            } else if(gender.equals(Gender.FEMALE)){
                int index = random.nextInt(nameSource.getFemale().length);
                String name = nameSource.getFemale()[index];
                Employee employee = new Employee(name, age, salary, gender, fixedBugs);
                employees[i] = employee;
            }else {
                int index = random.nextInt(nameSource.getOther().length);
                String name = nameSource.getOther()[index];
                Employee employee = new Employee(name, age, salary, gender, fixedBugs);
                employees[i] = employee;
            }
        }
        return employees;
    }
}
