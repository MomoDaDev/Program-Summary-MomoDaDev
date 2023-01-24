package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class Play {
    public static Random rnd = new Random();
    public static  boolean won = false;
    public static Spieler winner;
    public static void play() throws IOException, InterruptedException {
        List<Spieler> spieler = new ArrayList<Spieler>();

        FileReader fr = new FileReader("./files/DartSpielerListe.csv");
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

        for (int i = 0; i < spieler.size(); i++) {
            spieler.get(i).start();
        }

        while (!won){
            Thread.sleep(1000);
        }

        System.out.println("We have a winner: " + winner.toString());
    }
    public static Spieler play(Spieler spieler) {
        if (Play.rnd.nextInt(10) != 0) { //90 % ob getroffen wurde
            int number = Play.rnd.nextInt(1000) + 1;
            int wurfpunkte = Play.rnd.nextInt(21) + 1;
            if (number >= 1 && number <= 100) { // double
                wurfpunkte *= 2;
            } else if (number >= 101 && number <= 150) { // triple
                wurfpunkte *= 3;
            } else if (number >= 151 && number <= 160) { // bull
                wurfpunkte = 25;
            } else if (number >= 161 && number <= 170) { // bull's eye
                int number2 = Play.rnd.nextInt(4) + 1;
                if (number2 == 1) {
                    wurfpunkte = 50;
                }
            }
            spieler.punkte -= wurfpunkte;
            if (spieler.punkte <= 0 && Play.won == false && Play.winner == null) {
                Play.won = true;
                Play.winner = spieler;
                System.out.println(Instant.now());
            }
        }
        try {
            Thread.sleep(Play.rnd.nextInt(200) + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return spieler;
    }
}
