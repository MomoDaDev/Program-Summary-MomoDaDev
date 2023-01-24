package com.company;

public class Werfen implements Runnable {
    public Werfen(Spieler spieler){
        this.spieler = spieler;
    }
    public Spieler spieler;
    @Override
    public void run() {
        while (!Main.Dartscheibe.won){
            spieler = Main.Dartscheibe.play(spieler);
        }
    }
}