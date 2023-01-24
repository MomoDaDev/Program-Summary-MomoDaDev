package com.company;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static double standort_x = 23.69;
    static double standort_y = 7.71;

    public static void main(String[] args) throws IOException, CsvValidationException { // 210218KoordinatenLaden.csv
        Set<Laden> laeden = new HashSet<>();
        FileReader fileReader = new FileReader("././210218KoordinatenLaden.csv");
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] ls;
        while ((ls = csvReader.readNext()) != null){
            laeden.add(new Laden(Integer.parseInt(ls[0]), ls[1], Double.parseDouble(ls[2]), Double.parseDouble(ls[3])));
        }
        csvReader.close();

        List<Laden> laedenImUmkreis = getimUmkreis(laeden, 1.5);
        System.out.println(String.format(laedenImUmkreis.stream().
                map(Object::toString).
                collect(Collectors.joining("\n")).toString()));
        System.out.println("Anzahl: " + laedenImUmkreis.size());
    }

    public static List<Laden> getimUmkreis(Set<Laden> laeden, double radius){
        return laeden.stream()
                .filter(l -> (Math.pow(Math.abs(l.X - standort_x), 2)
                        + Math.pow(Math.abs(l.Y - standort_y), 2) <= Math.pow(radius, 2)))
                .collect(Collectors.toList());
    }
}
