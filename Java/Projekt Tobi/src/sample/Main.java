package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Translate Languages");
        Scene scene = new Scene(root, 600, 400);
        String fontSheet = fileToStylesheetString( new File("././resources/css/dark-theme.css") );
        scene.getStylesheets().add(fontSheet);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private String fileToStylesheetString ( File stylesheetFile ) {
        try {
            return stylesheetFile.toURI().toURL().toString();
        } catch ( MalformedURLException e ) {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
