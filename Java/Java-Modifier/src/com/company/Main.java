package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fahrzeug> parkplatz = new ArrayList<Fahrzeug>();
        parkplatz.add(new Fahrzeug("f1"));
        parkplatz.add(new Fahrzeug("f2"));
        parkplatz.add(new Fahrzeug("f3"));
        parkplatz.add(new Fahrzeug("f4"));
        parkplatz.add(new Fahrzeug("f5"));

        System.out.println(Arrays.toString(new ArrayList[]{parkplatz}));
    }
}
