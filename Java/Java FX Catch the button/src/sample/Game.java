package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Initializable {

    static boolean running = false;
    static int current_field;
    static int counter; // punkte
    static int countdown;
    static Timer timetoreacttimer;
    static Label timeleft;
    static Random rnd = new Random();
    static GridPane maingridpane;
    static TimerTask timetoreacttask;
    static Label l_counter;

    @FXML
    AnchorPane gamepane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up backgroundimage
        setupBackgroundImage();
        // set up leave button
        leavebtn();
        // set up Gridpane
        setupGridPane();
        // set up label
        setupLabel();
    }
    private void leavebtn(){
        Button btnback = new Button("Quit");
        btnback.setLayoutX(20);
        btnback.setLayoutY(120);
        btnback.setPrefSize(134, 38);
        btnback.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        btnback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnback.getScene().getWindow();
                stage.close();
            }
        });
        gamepane.getChildren().add(btnback);
    }
    private void stopgame() {
        System.out.println("STOP");
        timetoreacttimer.cancel();
        running = false;

        maingridpane.setVgap(0);
        maingridpane.setHgap(0);

        for (Node imgage: maingridpane.getChildren()) {
            try {
                ((ImageView)imgage).setImage(new Image(new FileInputStream("././images/bedrock.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void Continue(){
        l_counter.setText("Your Points: " + counter);
        timetoreacttask = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() -> {
                    try {
                        ((ImageView)maingridpane.getChildren().get(current_field - 1)).setImage(new Image(new FileInputStream("././images/KnopfNeutral.jpg")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Continue();
                });
            }

        };
        timetoreacttimer.cancel();
        timetoreacttimer = new Timer();
        timetoreacttimer.schedule(timetoreacttask, Controller.TIMETOREACT * 1000, Controller.TIMETOREACT * 1000);
        int randomrow = rnd.nextInt(Controller.ROWS);
        int randomcol = rnd.nextInt(Controller.COLLUMNS);
        current_field = randomrow * Controller.ROWS + randomcol + 1;
        try {
            ((ImageView)maingridpane.getChildren().get(current_field - 1)).setImage(new Image(new FileInputStream("././images/KnopfUnactivated.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void playgame(){
        System.out.println("lets go");
        running = true;
        countdown = Controller.PLAYTIME;
        counter = 0; // setzt punkte auf 0

        Timer startingtimer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() -> {
                    countdown--;
                    timeleft.setText("Time left: " + countdown);
                    if (countdown == 0){
                        startingtimer.cancel();
                        stopgame();
                    }
                });
            }

        };
        startingtimer.schedule(task, 1000, 1000);

        timetoreacttimer = new Timer();
        timetoreacttask = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() -> {
                    Continue();
                });
            }

        };

        timetoreacttimer.schedule(timetoreacttask, Controller.TIMETOREACT * 1000, Controller.TIMETOREACT * 1000);
        Continue();
    }
    private void setupLabel(){
        timeleft = new Label("Starting in 3 . . .");
        timeleft.setLayoutX(20);
        timeleft.setLayoutY(20);
        timeleft.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
        timeleft.setStyle("-fx-text-fill: white;");

        l_counter = new Label("Your Points: 0");
        l_counter.setLayoutX(20);
        l_counter.setLayoutY(60);
        l_counter.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
        l_counter.setStyle("-fx-text-fill: white;");

        gamepane.getChildren().addAll(timeleft, l_counter);

        countdown = 3;
        running = false;

        Timer startingtimer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() -> {
                    countdown--;
                    timeleft.setText("Starting in " + (countdown) + " . . .");
                    if (countdown == 0){
                        startingtimer.cancel();
                        playgame();
                    }
                });
            }

        };
        startingtimer.schedule(task, 1000, 1000);
        timetoreacttimer = new Timer();
    }
    private void setupBackgroundImage(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("././images/game_background.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image backgroundimage = new Image(input);
        ImageView backgroundimageView = new ImageView(backgroundimage);
        backgroundimageView.setFitHeight(1080);
        backgroundimageView.setFitWidth(1920);
        backgroundimageView.setX(0);
        backgroundimageView.setY(0);
        gamepane.getChildren().add(backgroundimageView);
    }
    //<GridPane layoutX="460.0" layoutY="40.0" prefHeight="1000.0" prefWidth="1000.0">
    private void setupGridPane(){
        maingridpane = new GridPane();
        maingridpane.setLayoutX(1920 / 2 - Controller.COLLUMNS * 50);
        maingridpane.setLayoutY(1000 / 2 - Controller.ROWS * 50);
        maingridpane.setPrefSize(Controller.ROWS * 100, Controller.COLLUMNS * 100);
        maingridpane.setHgap(10);
        maingridpane.setVgap(10);
        final int numCols = Controller.COLLUMNS;
        final int numRows = Controller.ROWS;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            maingridpane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            maingridpane.getRowConstraints().add(rowConst);
        }

        // fill fields with images
        for (int i = 0; i < Controller.COLLUMNS; i++) {
            for (int j = 0; j < Controller.ROWS; j++) {
                try {
                    ImageView iv = new ImageView(new Image(new FileInputStream("././images/KnopfNeutral.jpg")));
                    iv.setFitWidth(100);
                    iv.setFitHeight(100);
                    iv.setScaleZ(i * Controller.ROWS + j + 1); // +1 weil man bei 0 das Bild nicht drücken kann
                    iv.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (running && iv.getScaleZ() == current_field) {
                                System.out.println(counter);
                                try {
                                    iv.setImage(new Image(new FileInputStream("././images/KnopfPressed.jpg")));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    iv.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (running && iv.getScaleZ() == current_field) {
                                counter++;
                                try {
                                    iv.setImage(new Image(new FileInputStream("././images/KnopfActivated.jpg")));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(counter);
                                Continue();
                            }
                        }
                    });
                    maingridpane.add(iv, j, i);
                    System.out.println(i + ", " + j);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        gamepane.getChildren().add(maingridpane);
    }
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
