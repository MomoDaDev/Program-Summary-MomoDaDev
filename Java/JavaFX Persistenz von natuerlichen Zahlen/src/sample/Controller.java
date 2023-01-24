package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Pane mainpane;

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int number = 77;
        int persistence = Berechne.getPersistence(number);
        System.out.println("The persistence of " + number + " is " + persistence);

        SchreibeFile.schreibeFilePersistenceWithRange(0, 10000);

    }

}
