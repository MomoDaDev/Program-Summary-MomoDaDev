package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.*;

public class Calculate {
    public static AllResults calculateRangeBruteForce (int start, int end) {
        Instant before = Instant.now();
        AllResults allResults = new AllResults();
        int number;

        for (number = start; number <= end; number++) {     // number

            Results results = new Results(number, new ArrayList<>());

            for (int i1 = 0; i1 <= end; i1++) {             // i1
                for (int i2 = 0; i2 <= end; i2++) {         // i2
                    for (int i3 = 0; i3 <= end; i3++) {     // i3
                        for (int i4 = 0; i4 <= end; i4++) { // i4
                            if (check(i1, i2, i3, i4, number)) {
                                results.addNumbers(i1*i1, i2*i2, i3*i3, i4*i4);
                            }
                        }
                    }
                }
            }

            allResults.allresults.add(results);
        }

        Instant after = Instant.now();
        long delta = Duration.between(before, after).toMillis();
        System.out.println("Estimated time to calculate: " + delta + " milliseconds");

        return allResults;
    }
    public static AllResults calculateRange (int start, int end) {
        Instant before = Instant.now();
        AllResults allResults = new AllResults();
        int number;

        for (number = start; number <= end; number++) {     // number

            Results results = new Results(number, new ArrayList<>());

            for (int i1 = 0; i1 <= Math.floor(Math.sqrt(end)); i1++) {             // i1
                for (int i2 = 0; i2 <= Math.floor(Math.sqrt(end)); i2++) {         // i2
                    for (int i3 = 0; i3 <= Math.floor(Math.sqrt(end)); i3++) {     // i3
                        for (int i4 = 0; i4 <= Math.floor(Math.sqrt(end)); i4++) { // i4
                            if (check(i1, i2, i3, i4, number)) {
                                results.addNumbers(i1*i1, i2*i2, i3*i3, i4*i4);
                            }
                        }
                    }
                }
            }

            allResults.allresults.add(results);
        }

        Instant after = Instant.now();
        long delta = Duration.between(before, after).toMillis();
        System.out.println("Estimated time to calculate: " + delta + " milliseconds");

        return allResults;
    }
    private static boolean check (int i1, int i2, int i3, int i4, int number){
        return (number) == (i1*i1+i2*i2+i3*i3+i4*i4);
    }
}
