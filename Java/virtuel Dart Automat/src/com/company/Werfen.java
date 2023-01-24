package com.company;

import java.time.Instant;

public class Werfen implements Runnable {
    public Werfen(Spieler spieler){
        this.spieler = spieler;
    }
    public Spieler spieler;
    @Override
    public void run() {
        while (!Play.won){
            Play.play(spieler);
        }
    }
}
