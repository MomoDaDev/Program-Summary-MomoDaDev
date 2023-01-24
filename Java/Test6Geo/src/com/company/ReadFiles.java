package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {
    public static List<Kugel> initializeKugeln(){
        List<Kugel> kugeln = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("././210617Kugeln.csv"));
            String line;
            String[] ls; // ls -> lines_split
            br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null){
                ls = line.split(",");
                kugeln.add(new Kugel(Integer.parseInt(ls[0]), Integer.parseInt(ls[1]),
                        Integer.parseInt(ls[2]), Integer.parseInt(ls[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return kugeln;
    }
    public static List<Punkt> initializePunkte(){
        List<Punkt> punkte = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("././210617Punkte.csv"));
            String line;
            String[] ls; // ls -> lines_split
            br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null){
                ls = line.split(",");
                punkte.add(new Punkt(Integer.parseInt(ls[0]),
                        Integer.parseInt(ls[1]), Integer.parseInt(ls[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return punkte;
    }
}
