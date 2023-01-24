package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Einlesen {
    public static Set<Person> readFile(String filename, String fileLocation) throws IOException, ParseException {
        Set<Person> students = new HashSet<>();

        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation + filename));
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null){
            String[] ls = line.split(",");
            students.add(new Person(Integer.parseInt(ls[0]), ls[1], ls[2], LocalDate.parse(ls[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        }

        return students;
    }
}
