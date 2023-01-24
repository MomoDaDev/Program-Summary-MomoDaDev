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
        Set<Person> employees = new HashSet<>();

	    String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("././210208BirthdatesGT18.csv"));
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null){
            String[] ls = line.split(",");
            employees.add(new Person(Integer.parseInt(ls[0]), ls[1], ls[2], LocalDate.parse(ls[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        }

        ArrayList<Person> employeesOlderThan18 = (ArrayList<Person>)find18orOlder(employees);

        employeesOlderThan18.forEach(x -> System.out.println(x.toString()));
        System.out.println("Average Age of Adults: " + getAverageOlderThan18(employees));
        System.out.println("Average Age of All Employees: " + getAverage(employees));
        System.out.println("Amount of Employees younger than 18: " + getAmountOfYoungerThan18(employees));


    }
    public static List<Person> find18orOlder(Set<Person> employees) {
        return employees.stream().filter(
                e -> Period.between(e.birth_date,
                        LocalDate.now()).getYears() >= 18).collect(Collectors.toList());
    }
    public static double getAverageOlderThan18(Set<Person> employees) {
        return employees.stream().filter(e -> Period.between(e.birth_date,
                LocalDate.now()).getYears() >= 18)
                .mapToDouble(e -> Period.between(e.birth_date,
                LocalDate.now()).getYears()).average()
                .orElse(Double.NaN);
    }
    public static double getAverage(Set<Person> employees) {
        return employees.stream()
                .mapToDouble(e -> Period.between(e.birth_date,
                        LocalDate.now()).getYears()).average()
                .orElse(Double.NaN);
    }
    public static long getAmountOfYoungerThan18(Set<Person> employees) {
        return employees.stream().filter(
                e -> Period.between(e.birth_date,
                        LocalDate.now()).getYears() < 18)
                .mapToLong(e -> Period.between(e.birth_date,
                LocalDate.now()).getYears()).count();
    }
}
