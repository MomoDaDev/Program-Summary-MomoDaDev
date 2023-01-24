package com.company;

import java.time.LocalDate;
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


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}
