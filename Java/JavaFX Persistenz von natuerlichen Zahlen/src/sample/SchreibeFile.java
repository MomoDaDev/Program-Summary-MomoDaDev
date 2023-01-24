package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class SchreibeFile {
    public static void schreibeFilePersistenceWithRange(int start, int end) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("././Ergebnis.csv"));
            for (int i = start; i <= end; i++) {
                bw.write("Number: " + i + "; Persistence: " + Berechne.getPersistence(i) + "\n");
            }
            bw.close();
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
