package Lection_05.Entity;

import Lection_05.Data.Gender;

import java.util.Random;

public class Developer extends Employee {
    //this coefficient established the raising of the salary
    private int coefficient;

    public Developer(long id, String name, int age, double salary, Gender gender, int fixedBugs, int coefficient) {
        super(id, name, age, salary, gender, fixedBugs);
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public double earn(){
        //(ставка + fixedBugs * коэффициент) * (randomBoolean ? 2 : 0)
        return (salary + fixedBugs* Employee.defaultBugRate)*coefficient;
    }

    @Override
    public  String toString(){
        return getClass().getSimpleName() + ": " +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", salary=" + salary +
                ", gender=" + super.getGender() +
                ", fixedBugs=" + fixedBugs;
    }

}
