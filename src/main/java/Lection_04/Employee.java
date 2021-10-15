package Lection_04;

import java.util.Objects;

public class Employee {
    //Каждый сотрудник имеет следующие свойства
    //id, name, age, salary, gender, fixedBugs, defaultBugRate
private long id;
private String name;
private int age;
protected double salary;
private Gender gender;
protected int fixedBugs;
public static double defaultBugRate = 100;

    public Employee(long id, String name, int age, double salary, Gender gender, int fixedBugs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.fixedBugs = fixedBugs;
    }

    public Employee(long id, String name, int age, double salary, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getFixedBugs() {
        return fixedBugs;
    }

    public void setFixedBugs(int fixedBugs) {
        this.fixedBugs = fixedBugs;
    }

    public double earn (){
      return  salary + fixedBugs * defaultBugRate;
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
