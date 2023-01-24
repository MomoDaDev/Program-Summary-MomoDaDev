package com.company;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ferienspiel {
    public static Game initialize() throws IOException {
        FileReader path = new FileReader("././files/spieler.csv");
        List<Schüler> schüler = new ArrayList<Schüler>();
        BufferedReader br = new BufferedReader(path);

        String muster = br.readLine().split(",")[1];

        br.readLine();
        String line;
        while ((line = br.readLine()) != null){
            schüler.add(new Schüler(line));
        }

        return new Game(schüler, muster);
    }
}
