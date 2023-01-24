package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sortieren {
    static final int robotCount = 3;
    public static Random rnd = new Random();
    public static boolean geleert = false;

    public static List<Teil> alleTeile;
    public static List<Regal> alleRegale;
    public static Regal currentRegal;
    public static void starteProzess() throws IOException, InterruptedException {
        alleTeile = Collections.synchronizedList(new LinkedList<Teil>());
        alleRegale = Collections.synchronizedList(new LinkedList<Regal>());
        currentRegal = new Regal();

        FileReader fr = new FileReader("./files/StanzContainer.csv");
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        String line;
        while ((line = br.readLine()) != null){
            String[] linesplit = line.split(",");
            String seriennummer = linesplit[0];
            Geometrie geometrie = Geometrie.valueOf(linesplit[1]);
            double gewicht = Double.parseDouble(linesplit[2]);
            alleTeile.add(new Teil(seriennummer, geometrie, gewicht));
        }
        System.out.println("File initialized");

        for (int i = 0; i < robotCount; i++) {
            Roboter roboter = new Roboter();
            roboter.starte();
        }

        while (!geleert){
            Thread.sleep(1000);
        }
        System.out.println("Alles gefüllt");


        for (int i = 0; i < alleRegale.size(); i++) {
            System.out.println("Regal " + (i + 1));
            //for (int j = 0; j < alleRegale.get(i).teile.size(); j++) {
            //    System.out.println(alleRegale.get(i).teile.toString());
            //}
            //System.out.println();
            System.out.println("--------------------------------------------------");
        }
    }
}
