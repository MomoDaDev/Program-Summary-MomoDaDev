package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Main extends Application {
    public static DoubleProperty bmi;
    @Override
    public void start(Stage primaryStage) throws Exception{

         bmi = new SimpleDoubleProperty(3);


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("BMI Rechner");
        primaryStage.setScene(new Scene(root, 500, 475));

        Controller controller = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
