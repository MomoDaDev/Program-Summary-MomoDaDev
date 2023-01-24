package farbe;

public class FarbkarteNeu {
    private Farbe farbe;
    private String name;

    public FarbkarteNeu(Farbe farbe){
        this.farbe = farbe;
        name = this.farbe.toString();
    }

    public Farbe getFarbe(){
        return farbe;
    }
    public String getFarbName(){
        return name;
    }
}
