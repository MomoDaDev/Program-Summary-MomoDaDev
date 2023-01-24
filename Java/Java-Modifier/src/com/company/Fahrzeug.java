package com.company;

public class Fahrzeug {
    String name;

    public Fahrzeug(String n) { name = n; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name;
    }
}
