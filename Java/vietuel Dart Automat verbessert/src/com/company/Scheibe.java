package com.company;

import java.time.Instant;
import java.util.Random;

public class Scheibe {
    public Random rnd;
    public boolean won;
    public Spieler winner;
    public int anzWürfe;
    public Scheibe(){
        won = false;
        rnd = new Random();
        anzWürfe = 0;
    }
    //Die Wahrscheinlichkeit, dass die Scheibe normal ohne Extrapunkte getroffen wird lieg bei
    public Spieler play(Spieler spieler) {
        if (rnd.nextInt(10) != 0) { //90 % ob getroffen wurde
            anzWürfe++;
            spieler.würfe++;
            int number = rnd.nextInt(1000) + 1;
            int wurfpunkte = rnd.nextInt(20) + 1;
            if (number >= 1 && number <= 100) { // double
                wurfpunkte *= 2;
            } else if (number >= 101 && number <= 150) { // triple
                wurfpunkte *= 3;
            } else if (number >= 151 && number <= 160) { // bull
                wurfpunkte = 25;
            } else if (number >= 161 && number <= 170) { // bull's eye
                int number2 = rnd.nextInt(4) + 1;
                if (number2 == 1) {
                    wurfpunkte = 50;
                }
            } // Der Spieler trifft zu 83.75 % auf die Restfläche eines Segments
            spieler.punkte -= wurfpunkte; //Punkte werden abgezogen
            //schaut, ob es schon einen Gewinner gibt, damit nicht überschrieben wird
            if (spieler.punkte <= 0 && won == false && winner == null) {
                won = true;
                winner = spieler;
                System.out.println(Instant.now());
            }
        }
        try { // Wartezeit von 100 bis 300 ms
            Thread.sleep(rnd.nextInt(200) + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return spieler;
    }
}
