package farbe;

public class TestEnums {
    public static void testeEnums(){
        FarbkarteOld farbkarteOld_1 = new FarbkarteOld(2);
        System.out.println("farbkarteOld_1: Farbe: " + farbkarteOld_1.getFarbName());

        FarbkarteOld farbkarteOld_2 = new FarbkarteOld(0);
        System.out.println("farbkarteOld_2: Farbe: " + farbkarteOld_2.getFarbName());

        FarbkarteOld farbkarteOld_3 = new FarbkarteOld(7);
        System.out.println("farbkarteOld_3: Farbe: " + farbkarteOld_3.getFarbName());

        FarbkarteNeu farbkarteNeu_1 = new FarbkarteNeu(Farbe.SCHWARZ);
        System.out.println("farbkarteNeu_1: Farbe: " + farbkarteNeu_1.getFarbName());

        FarbkarteNeu farbkarteNeu_2 = new FarbkarteNeu(Farbe.values()[3]);
        System.out.println("farbkarteNeu_2: Farbe: " + farbkarteNeu_2.getFarbName());

        //Farbe2 farbe2_1 = new Farbe2("Gelb"); Der Konstruktor kann nicht aufgerufen werden, da er privat ist
    }
}
