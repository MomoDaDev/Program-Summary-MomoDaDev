package com.company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spielorganisator {
    public static Random rnd = new Random();
    public static List<String> gesamtgewürfelt = new ArrayList<String>();
    public static Game game;
    public static boolean continuegame = true;

    public static void play() throws IOException {
        game = Ferienspiel.initialize();

        System.out.println("Spielorganisator: es wird gegen das Muster: " + game.muster + " gespielt");
        System.out.println("und es wurden " + game.schüler.size() + " Spieler geladen. Happy game!");

        for (int i = 0; i < game.schüler.size(); i++) {
            game.schüler.get(i).start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Es wurde " + Spielorganisator.game.rundengespielt + " mal gespielt");
        }
        for (int i = 0; i < game.schüler.size(); i++) {
            for (int j = 0; j < game.schüler.size() - 1; j++) {
                int first = game.schüler.get(j).punkte;
                int next = game.schüler.get(j + 1).punkte;
                if (first > next){
                    Schüler s = game.schüler.get(j);
                    game.schüler.set(j, game.schüler.get(j + 1));
                    game.schüler.set(j + 1, s);
                }
            }
        }
        continuegame = false;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Punkte: ");
        for (int i = game.schüler.size() - 1; i >= 0; i--) {
            System.out.println(game.schüler.get(i).name + ": " + game.schüler.get(i).punkte);
        }

        //for (int i = 0; i < game.schüler.size(); i++) {
        //    System.out.println(game.schüler.get(i).name);
        //}
        //System.out.println(game.muster);


    }
}
