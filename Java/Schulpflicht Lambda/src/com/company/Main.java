package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        Set<Person> students = Einlesen.readFile("210211PersonenDaten.csv", "././");

        ArrayList<Person> SchulpflichtigStudents = (ArrayList<Person>)findSchulpflichtig(students);

        System.out.println("Schulpflichtige Schüler: ");
        SchulpflichtigStudents.forEach(x -> System.out.println(x.toString()));
        System.out.println("Anzahl der Schulpflichtigen: " + SchulpflichtigStudents.size());


    }
    public static List<Person> findSchulpflichtig(Set<Person> students) {
        return students.stream()
                .filter(s -> (Period.between(s.birth_date, LocalDate.now()).getYears() >= 6) &&
                        (Period.between(s.birth_date, LocalDate.now()).getYears() <= 15))
                .collect(Collectors.toList());
    }
}
