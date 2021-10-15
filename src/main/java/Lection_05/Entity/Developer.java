package Lection_05.Entity;

import Lection_05.Data.Gender;

import java.util.Random;

public class Developer extends Employee {
    public Developer(long id, String name, int age, double salary, Gender gender, int fixedBugs) {
        super(id, name, age, salary, gender, fixedBugs);
    }
    @Override
    public double earn(){
        //(ставка + fixedBugs * коэффициент) * (randomBoolean ? 2 : 0)
        return (salary + fixedBugs* Employee.defaultBugRate)*(new Random().nextBoolean()?2:0);
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
