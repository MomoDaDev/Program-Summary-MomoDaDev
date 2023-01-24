package com.company;

public class Spieler {
    public int id;
    public String vorname;
    public String nachname;
    public int punkte;
    public Spieler(int id, String vorname, String nachname, int punkte){
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.punkte = punkte;
    }

    public void start(){
        Thread t = new Thread(new Werfen(this));
        t.start();
    }

    @Override
    public String toString() {
        return id + ", " + vorname + ", " + nachname + ", " + punkte;
    }
}
