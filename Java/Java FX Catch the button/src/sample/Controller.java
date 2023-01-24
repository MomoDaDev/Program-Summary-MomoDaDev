package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // game_settings:
    public static int TIMETOREACT;
    public static int ROWS;
    public static int COLLUMNS;
    public static int PLAYTIME;

    static ImageView imageView;
    @FXML
    Pane mainpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set background image
        setupBackgroundImage();
        // set up buttons
        setupButtons();
        // set up title image
        setupTitleImage();
        // initialize game settings
        initializeGameSettings();
    }
    /*
    * <Button fx:id="start" layoutX="233.0" layoutY="208.0" mnemonicParsing="false" onAction="#startGame" prefHeight="38.0" prefWidth="134.0" text="Start" />
      <Button fx:id="options" layoutX="233.0" layoutY="268.0" mnemonicParsing="false" onAction="#goToOptions" prefHeight="38.0" prefWidth="134.0" text="Options" />
      <Button fx:id="exit" layoutX="233.0" layoutY="328.0" mnemonicParsing="false" onAction="#exitGame" prefHeight="38.0" prefWidth="134.0" text="Exit" />*/
/*
* <ImageView fitHeight="79.0" fitWidth="560.0" layoutX="20.0" layoutY="40.0" pickOnBounds="true" preserveRatio="true">
         <image>
            <Image url="@../../images/catch_the_button_title.png" />
         </image></ImageView>*/
    private void initializeGameSettings() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("././settings/game_settings.txt"));
            TIMETOREACT = Integer.parseInt(br.readLine().split("=")[1]);
            ROWS = Integer.parseInt(br.readLine().split("=")[1]);
            COLLUMNS = Integer.parseInt(br.readLine().split("=")[1]);
            PLAYTIME = Integer.parseInt(br.readLine().split("=")[1]);

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setupTitleImage(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("././images/catch_the_button_title.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image titleimage = new Image(input);
        ImageView titleimageView = new ImageView(titleimage);
        titleimageView.setFitHeight(79);
        titleimageView.setFitWidth(560);
        titleimageView.setX(20);
        titleimageView.setY(50);
        mainpane.getChildren().add(titleimageView);
    }
    private  void setupButtons(){
        Button start = new Button();
        start.setText("Start");
        start.setLayoutX(233);
        start.setLayoutY(208);
        start.setPrefSize(134, 38);
        start.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame(event);
            }
        });
        Button options = new Button();
        options.setText("Options");
        options.setLayoutX(233);
        options.setLayoutY(268);
        options.setPrefSize(134, 38);
        options.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        options.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToOptions(event);
            }
        });
        Button exit = new Button();
        exit.setText("Exit");
        exit.setLayoutX(233);
        exit.setLayoutY(328);
        exit.setPrefSize(134, 38);
        exit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitGame();
            }
        });
        mainpane.getChildren().addAll(start, options, exit);
    }

    private void setupBackgroundImage(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("././images/background_image.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image backgroundImage = new Image(input);
        imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(700);
        imageView.setFitWidth(1200);
        imageView.setX(-300);
        imageView.setY(-150);
        mainpane.getChildren().add(imageView);

        Thread t = new Thread(new BackgroundImageScalingLoop());
        t.start();
    }

    public void startGame(javafx.event.ActionEvent event){
        System.out.println("Starting game");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("game.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    stage.close();
                }
            });
            ((Stage)((Node)event.getSource()).getScene().getWindow()).hide();
            stage.showAndWait();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToOptions(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("options.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Options");
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    stage.close();
                }
            });
            ((Stage)((Node)event.getSource()).getScene().getWindow()).hide();
            stage.showAndWait();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exitGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave the game?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            safeOptions();
            System.exit(0);
        }
    }
    public static void safeOptions(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("././settings/game_settings.txt"));
            bw.write("timeToReact=" + TIMETOREACT);
            bw.write("\nrows=" + ROWS);
            bw.write("\ncollumns=" + COLLUMNS);
            bw.write("\nplaytime=" + PLAYTIME);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
