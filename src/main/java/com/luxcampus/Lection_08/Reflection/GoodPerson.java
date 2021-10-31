package com.luxcampus.Lection_08.Reflection;

public class GoodPerson extends Person implements Cloneable, ActionInterface{
    private String mood;

    public GoodPerson(int age, String name, String city, String mood) {
        super(age, name, city);
        this.mood = mood;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
