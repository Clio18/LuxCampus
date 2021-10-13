package Lection_04;

import java.util.Objects;

public class Employee {
    //Каждый сотрудник имеет следующие свойства
    //id, name, age, salary, gender, fixedBugs, defaultBugRate
private long id;
private static long generator = 1;
private String name;
private int age;
private double salary;
private Enum gender;
private int fixedBugs;
public static double defaultBugRate = 100;

    public Employee(String name, int age, double salary, Enum gender, int fixedBugs) {
        //id auto generation
        this.id = generator++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.fixedBugs = fixedBugs;
    }

    public Employee(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Enum getGender() {
        return gender;
    }

    public void setGender(Enum gender) {
        this.gender = gender;
    }

    public int getFixedBugs() {
        return fixedBugs;
    }

    public void setFixedBugs(int fixedBugs) {
        this.fixedBugs = fixedBugs;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                ", fixedBugs=" + fixedBugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Double.compare(employee.salary, salary) == 0 && fixedBugs == employee.fixedBugs && name.equals(employee.name) && gender.equals(employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, gender, fixedBugs);
    }
}
