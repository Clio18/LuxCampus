package com.luxcampus.Lection_08.Reflection;

import java.util.Objects;

public class Person implements ActionInterface {
    private int age;
    private String name;
    public String city;

    public Person() {
    }

    public Person(int age, String name, String city) {
        this.age = age;
        this.name = name;
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void makeAction() {
        System.out.println("Do a party!");
    }

    public static final int declare(){
        return 100;
    }

    private void nameToUpperCase(){
        this.name.toUpperCase();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, city);
    }
}
