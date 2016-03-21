package com.pierangeloc.java;

public class Person {

    private String name;
    private String surname;
    private String discipline;

    public Person(String name, String surname, String discipline) {
        this.name = name;
        this.surname = surname;
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

}
