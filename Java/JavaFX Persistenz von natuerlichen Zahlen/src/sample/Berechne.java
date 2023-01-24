package sample;

import java.util.List;
import java.util.stream.Collectors;

public class Berechne {
    public static int getPersistence(int zahl) {
        int persistence = 0;
        List<Integer> interim_result;
        boolean running = true;

        while (running) {
            if (zahl < 10) { running = false; }
            else { persistence++; }

            interim_result = getDigits(String.valueOf(zahl));
            zahl = getProductOfIntegers(interim_result);


        }

        return persistence;
    }
    private static List<Integer> getDigits(String digitsInString) {
        return digitsInString.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
    }
    private static int getProductOfIntegers(List<Integer> integers) {
        return integers.stream().reduce(1, (a, b) -> a * b);
    }
}
