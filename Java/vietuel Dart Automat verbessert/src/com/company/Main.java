package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scheibe Dartscheibe;
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Spieler> spieler = new ArrayList<Spieler>();
        Dartscheibe = new Scheibe();

        FileReader fr = new FileReader("./files/201109DartSpielerListe.csv");
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        br.readLine();
        String line;
        while ((line = br.readLine()) != null){
            String[] linesplit = line.split(",");
            int id = Integer.parseInt(linesplit[0]);
            String nachname = linesplit[1];
            String vorname = linesplit[2];
            spieler.add(new Spieler(id, vorname, nachname, 301));
        }
        br.close();

        for (int i = 0; i < spieler.size(); i++) {
            spieler.get(i).thread.start();
        }

        while (!Dartscheibe.won){
            Thread.sleep(1000);
        }

        System.out.println("We have a winner: " + Dartscheibe.winner.toString());
        System.out.println("Total throws: " + Dartscheibe.anzWürfe);
    }
}
