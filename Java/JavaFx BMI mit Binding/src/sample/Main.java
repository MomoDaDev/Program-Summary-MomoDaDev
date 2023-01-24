package sample;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


// immer setup sdk dann libraries auf + dann C:\java sdk\ lib auswählen apply okay

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(" BMI Rechner ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        /*
        Controller controller = new Controller();
        double höheInMeter = Double.parseDouble(controller.tfCm.getText()) / 100;
        double gewichtInKg = Double.parseDouble(controller.tfKg.getText());
        double doubleNumber = gewichtInKg / Math.pow(höheInMeter, 2);

        DoubleProperty value = new SimpleDoubleProperty(doubleNumber);

        NumberBinding number = value.add(0);

        controller.taBMI.setText(number.getValue().toString());

         */

    }


    public static void main(String[] args) { launch(args); }
}
