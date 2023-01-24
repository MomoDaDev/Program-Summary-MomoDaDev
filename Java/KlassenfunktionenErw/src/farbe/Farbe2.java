package farbe;

public enum Farbe2 {
    ROT ("Rot"),
    SCHWARZ("Schwarz"),
    GELB("Gelb"),
    WEISS("Weiss");

    private String name;

    private Farbe2(String farbe) {
        name = farbe;
    }

    public String getFarbe() {
        return name;
    }
}
