package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Pane pane;
    @FXML
    public Circle circle;
    private Ball ball = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ball = new Ball(9.81, 0, 5);
    }
    public void btn_drop_clicked(ActionEvent event) {
        System.out.println("btn_drop_clicked");
        ball.Drop();
    }
    public void btn_step_clicked(ActionEvent event) {
        System.out.println("btn_step_clicked");
        ball.Step();
    }
    public void btn_new_Ball_clicked(ActionEvent event) {
        System.out.println("btn_new_Ball_clicked");
        ball = new Ball(9.81, 0, 5);
    }
    public void DrawCircle(String color, double radius, double XCoordinate, double YCoordinate){
        circle.setRadius(radius);
        circle.setStroke(Paint.valueOf(color));
        circle.setFill(Paint.valueOf(color));
        circle.setCenterX(XCoordinate);
        circle.setCenterY(YCoordinate);
        circle.setVisible(true);
    }
}
