package com.luxcampus.Lection_05.Entity;

import com.luxcampus.Lection_05.Data.Gender;

public class Manager extends Employee {

    public Manager(long id, String name, int age, double salary, Gender gender) {
        super(id, name, age, salary, gender);
    }

    @Override
    public double earn(){
        return salary;
    }


    @Override
    public  String toString(){
        return getClass().getSimpleName() + ": " +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", salary=" + salary +
                ", gender=" + super.getGender();
    }

}
