package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("././files/readme.txt"));
        List<String> options = new ArrayList<>();
        String[] line = br.readLine().split(";");
        br.close();
        for (int i = 0; i < line.length; i++) {
            options.add(line[i]);
        }

        if(options.contains("1A")){
            Aufgaben.Aufgabe1A(5, 4);
        }
        if(options.contains("1B")){
            Aufgaben.Aufgabe1B(243);
        }
        if(options.contains("2A")){
            Aufgaben.Aufgabe2A(1327);
        }
        if(options.contains("2B")){
            Aufgaben.Aufgabe2B("Regallager");
        }
        if(options.contains("2C")){
            Aufgaben.Aufgabe2C(new int[]{1,4,7,2,3,73,6,32,678,12,9,3,1,6,8,9,1,9,7,4,3,7,2,1,6,7});
        }
    }
}
