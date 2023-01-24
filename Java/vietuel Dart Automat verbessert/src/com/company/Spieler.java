package com.company;

public class Spieler {
    public int id;
    public String vorname;
    public String nachname;
    public int punkte;
    public int würfe;
    public Thread thread;
    public Spieler(int id, String vorname, String nachname, int punkte){
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.punkte = punkte;
        würfe = 0;
        thread = new Thread(new Werfen(this));
    }

    @Override
    public String toString() {
        return id + ", " + vorname + ", " + nachname + ", Punkte: " + punkte + ", Würfe: " + würfe;
    }
}
