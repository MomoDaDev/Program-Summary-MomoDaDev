package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static List<String> readFile(String filename, String path) {
        try {
            FileReader fileReader = new FileReader(path + filename);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            List<String> statements = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                statements.add(line);
            }
            br.close();
            return statements;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
