package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Options implements Initializable {

    @FXML
    AnchorPane optionspane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up background image
        setupBackgroundImage();
        // configure back button
        configurebtnback();
        // set up options title
        setupTitleImage();
        // set up labels and comboboxes
        setupLabelandComboBox();
    }
    /*
      <ComboBox layoutX="150.0" layoutY="118.0" prefWidth="150.0" promptText="5" />
      <ComboBox layoutX="150.0" layoutY="188.0" prefWidth="150.0" promptText="5" />
      <ComboBox layoutX="150.0" layoutY="258.0" prefWidth="150.0" />
      <ComboBox layoutX="150.0" layoutY="328.0" prefWidth="150.0" />
      <Label layoutX="14.0" layoutY="122.0" text="Label" />
      <Label layoutX="14.0" layoutY="192.0" text="Label" />
      <Label layoutX="14.0" layoutY="262.0" text="Label" />
      <Label layoutX="14.0" layoutY="332.0" text="Label" />*/
    private void setupLabelandComboBox(){
        //Labels
        Label timeToReact = new Label("Time to react: ");
        timeToReact.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
        timeToReact.setStyle("-fx-text-fill: white;");
        timeToReact.setLayoutX(14);
        timeToReact.setLayoutY(122);
        Label rows = new Label("Rows: ");
        rows.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
        rows.setStyle("-fx-text-fill: white;");
        rows.setLayoutX(14);
        rows.setLayoutY(192);
        Label collumns = new Label("Collumns: ");
        collumns.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
        collumns.setStyle("-fx-text-fill: white;");
        collumns.setLayoutX(14);
        collumns.setLayoutY(262);
        Label playtime = new Label("Playtime: ");
        playtime.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
        playtime.setStyle("-fx-text-fill: white;");
        playtime.setLayoutX(14);
        playtime.setLayoutY(332);

        optionspane.getChildren().addAll(timeToReact, rows, collumns, playtime);

        //Comboboxes
        ComboBox cbtimeToReact = new ComboBox();
        cbtimeToReact.setPromptText("" + Controller.TIMETOREACT);
        cbtimeToReact.setLayoutX(200);
        cbtimeToReact.setLayoutY(129);
        cbtimeToReact.setPrefWidth(100);
        cbtimeToReact.getItems().addAll(generateNumberRange(1, 20));
        cbtimeToReact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.TIMETOREACT = Integer.parseInt(cbtimeToReact.getSelectionModel().getSelectedItem().toString());
                System.out.println("TimeToReact set to " + Controller.TIMETOREACT);
            }
        });
        ComboBox cbrows = new ComboBox();
        cbrows.setPromptText("" + Controller.ROWS);
        cbrows.setLayoutX(200);
        cbrows.setLayoutY(199);
        cbrows.setPrefWidth(100);
        cbrows.getItems().addAll(generateNumberRange(3, 8));
        cbrows.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.ROWS = Integer.parseInt(cbrows.getSelectionModel().getSelectedItem().toString());
                System.out.println("Rows set to " + Controller.ROWS);
            }
        });
        ComboBox cbcollumns = new ComboBox();
        cbcollumns.setPromptText("" + Controller.COLLUMNS);
        cbcollumns.setLayoutX(200);
        cbcollumns.setLayoutY(269);
        cbcollumns.setPrefWidth(100);
        cbcollumns.getItems().addAll(generateNumberRange(3, 8));
        cbcollumns.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.COLLUMNS = Integer.parseInt(cbcollumns.getSelectionModel().getSelectedItem().toString());
                System.out.println("Collumns set to " + Controller.COLLUMNS);
            }
        });
        ComboBox cbplaytime = new ComboBox();
        cbplaytime.setPromptText("" + Controller.PLAYTIME);
        cbplaytime.setLayoutX(200);
        cbplaytime.setLayoutY(339);
        cbplaytime.setPrefWidth(100);
        cbplaytime.getItems().addAll(generateNumberRange(5, 120));
        cbplaytime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.PLAYTIME = Integer.parseInt(cbplaytime.getSelectionModel().getSelectedItem().toString());
                System.out.println("Playtime set to " + Controller.PLAYTIME);
            }
        });

        optionspane.getChildren().addAll(cbtimeToReact, cbrows, cbcollumns, cbplaytime);
    }
    private Integer[] generateNumberRange(int min, int max){
        Integer[] range = new Integer[max - min + 1];
        for (int i = min; i <= max; i++) {
            range[i - min] = i;
        }
        return range;
    }
    /*
    * <ImageView fitHeight="86.0" fitWidth="260.0" layoutX="14.0" layoutY="14.0" pickOnBounds="true" preserveRatio="true">
         <image>
            <Image url="@../../images/options_title.png" />
         </image>
      </ImageView>
    * */
    private void setupTitleImage(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("././images/options_title.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image titleimage = new Image(input);
        ImageView titleimageView = new ImageView(titleimage);
        titleimageView.setFitHeight(86);
        titleimageView.setFitWidth(260);
        titleimageView.setX(14);
        titleimageView.setY(14);
        optionspane.getChildren().add(titleimageView);
    }
    //<Button fx:id="btnback" layoutX="456.0" layoutY="341.0" mnemonicParsing="false" prefHeight="45.0" prefWidth="130.0" text="Button" />
    private void configurebtnback(){
        Button btnback = new Button("Back");
        btnback.setLayoutX(452);
        btnback.setLayoutY(334);
        btnback.setPrefSize(134, 38);
        btnback.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        btnback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnback.getScene().getWindow();
                stage.close();
            }
        });
        optionspane.getChildren().add(btnback);
    }
    private void setupBackgroundImage(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("././images/options_background.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image backgroundimage = new Image(input);
        ImageView backgroundimageView = new ImageView(backgroundimage);
        backgroundimageView.setFitHeight(500);
        backgroundimageView.setFitWidth(1000);
        backgroundimageView.setX(0);
        backgroundimageView.setY(-100);
        optionspane.getChildren().add(backgroundimageView);
    }
}
