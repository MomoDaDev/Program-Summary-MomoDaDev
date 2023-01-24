package com.company;

public class Teil {
    public String seriennummer;
    public Geometrie geometrie;
    public double gewicht;
    public Teil (String seriennummer, Geometrie geometrie, double gewicht){
        this.seriennummer = seriennummer;
        this.geometrie = geometrie;
        this.gewicht = gewicht;
    }

    @Override
    public String toString() {
        return seriennummer + ", " + geometrie.name() + ", " + gewicht;
    }
}
