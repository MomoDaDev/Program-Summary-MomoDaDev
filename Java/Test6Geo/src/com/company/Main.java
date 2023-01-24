package com.company;

import java.util.List;

public class Main {

    // Wie verh¨alt sich Ihr Programm in Bezug auf O-Notation bei großer Zahl von Punkten und Kugeln?
    // O(anz_Punkte * anz_Kugeln)

    // Wie wurden Sie Ihr Programm ¨ ¨andern um auch Quader, bzw. andere geometrische Formen prufen zu k ¨ ¨onnen?
    // Ich würde eine neue Klasse für die jeweilige Form erstellen. Die Methode ob der Punkt in der Form liegt muss
    // je nachdem umgeschrieben werden.

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        List<Kugel> kugeln = ReadFiles.initializeKugeln();
        List<Punkt> punkte = ReadFiles.initializePunkte();

        for (Kugel k : kugeln) {
            System.out.println(k.toString());
        }
        for (Punkt p : punkte) {
            System.out.println(p.toString());
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        //Kugel testKugel1 = new Kugel(5, 5, 5, 3);
        //Punkt testPunkt1 = new Punkt(3, 3, 3);
        //System.out.println("TestPunkt1 ist in testKugel1: " + testKugel1.IsPunktInKugel(testPunkt1));

        for (Kugel k : kugeln) {
            for (Punkt p : punkte) {
                if (k.IsPunktInKugel(p)){
                    System.out.println(ANSI_GREEN + p.toString() + " ist in " + k.toString() + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + p.toString() + " ist nicht in " + k.toString() + ANSI_RESET);
                }
            }
        }

    }
}
