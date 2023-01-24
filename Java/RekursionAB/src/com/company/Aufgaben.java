package com.company;

import java.util.*;

public class Aufgaben {
    public static void Aufgabe1A(int a, int n){
        System.out.println("Aufgabe1A");
        int p = 1;
        if (n > 0){
            p = pot(p, a, n);
        }
        System.out.println(p);
    }
    private static int pot(int p, int a, int n){
        p = p * a;
        n = n - 1;
        if (n > 0){
            p = pot(p, a, n);
        }
        return p;
    }
    public static void Aufgabe1B(double n){
        System.out.println("Aufgabe1B");
        double x = funktionBerechnen(n - 1) + 2 * n - 1;
        System.out.println(x);
    }
    private static double funktionBerechnen(double n){
        double x;
        if (n == 1){
            return 1;
        } else {
            x = funktionBerechnen(n - 1) + 2 * n - 1;
        }
        return x;
    }
    public static void Aufgabe2A(int p){
        System.out.println("Aufgabe2A");
        boolean prim = istPrimzahl(p, p - 1);
        System.out.println(p + " ist " + (prim ? "eine" : "keine") + " Primzahl");
    }
    private static boolean istPrimzahl(int p, int n){
        if (n > 1){
            boolean b = (p % n != 0);
            if (!b){
                return false;
            }
            boolean b2 = istPrimzahl(p, n - 1);
            if (!b2){
                return false;
            }
        }
        return true;
    }
    public static void Aufgabe2B(String wort){
        System.out.println("Aufgabe2B");
        char[] c = wort.toLowerCase().toCharArray();
        System.out.println(c.length / 2);
        boolean pal = istPalidrom(c, c.length / 2);
        System.out.println(wort + " ist " + (pal ? "ein" : "kein") + " Palidrom");
    }
    private static boolean istPalidrom(char[] c, int n){
        if (n >= 1){
            boolean b = (c[n - 1] == c[c.length - n]);
            if (!b){
                return false;
            }
            boolean b2 = istPalidrom(c, n - 1);
            if (!b){
                return false;
            }
        }
        return true;
    }
    public static void Aufgabe2C(int[] x){
        System.out.println("Aufgabe2C");
        MyElement myElement = new MyElement();
        for (int i = 0; i < x.length; i++) {
            myElement.addnumber(x[i]);
        }
        myElement.printAll();
    }
}
