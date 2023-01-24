package farbe;

public class FarbkarteOld {
    public static int KFARBE_ROT =0;
    public static int KFARBE_SCHWARZ =1;
    public static int KFARBE_GELB =2;
    public static int KFARBE_WEISS =3;
    private int farbe;

    public FarbkarteOld(int farbe){
        this.farbe = farbe;
    }

    public int getFarbe(){
        return farbe;
    }
    public String getFarbName(){
        switch(farbe){
            case 0:
                return "ROT";
            case 1:
                return "SCHWARZ";
            case 2:
                return "GELB";
            case 3:
                return "WEISS";
            default:
                return "no color";
        }
    }
}
