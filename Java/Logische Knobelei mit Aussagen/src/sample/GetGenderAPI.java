package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class GetGenderAPI {
    public static Main.Gender GetGenderByName(String name){
        try {
            String out = new Scanner(new URL("https://api.genderize.io?name=" + name).openStream(), "UTF-8").useDelimiter("\\A").next();
            String strInput = out.split("\"")[7]; // we want the third line

            System.out.println(out);

            if (strInput.equalsIgnoreCase("male")) {
                return Main.Gender.MALE;
            } else {
                return Main.Gender.FEMALE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
