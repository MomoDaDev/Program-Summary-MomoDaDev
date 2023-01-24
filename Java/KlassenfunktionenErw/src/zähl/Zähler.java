package zähl;

public class Zähler {
    public static int zähler = 0;
    private int serienNummer;
    public Zähler(){
        zähler++;
        serienNummer = zähler;
    }
    public static int getZähler(){
        return zähler;
    }
}
