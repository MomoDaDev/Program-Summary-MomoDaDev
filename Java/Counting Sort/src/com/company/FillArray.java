package com.company;

import java.util.Random;

public class FillArray {
    static Random rnd = new Random();
    public static int[] fillIntArraywithRandom(int n, int intervallgrenze){
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = rnd.nextInt(intervallgrenze - 1) + 1; // der wert darf nicht null sein, da der Algorithmus sonst nicht funktioniert
        }
        return x;
    }
}
