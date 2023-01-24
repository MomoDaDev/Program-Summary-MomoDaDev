package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int start = 0, end = 300;

        // Brute Force Method

        /*
        System.out.println("Bruteforce-Calculating from range " + start + " -> " + end);
        AllResults allResultsbruteForce = Calculate.calculateRangeBruteForce(start, end);
        System.out.println(allResultsbruteForce.toString());
        */

        // Optimized Method

        System.out.println("Calculating from range " + start + " -> " + end + " (Optimized)");
        AllResults allResults = Calculate.calculateRange(start, end);
        System.out.println(allResults.toString());
    }
}
