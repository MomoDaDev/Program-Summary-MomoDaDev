package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Person {
    public int id;
    public String first_name;
    public String last_name;
    public LocalDate birth_date;

    public Person(int id, String first_name, String last_name, LocalDate birth_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    public long getAge() { return Period.between(birth_date, LocalDate.now()).getYears(); }


    //Standard toString
    @Override
    public String toString() {
        return "Schüler{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", Age=" + getAge() +
                '}';
    }
}
