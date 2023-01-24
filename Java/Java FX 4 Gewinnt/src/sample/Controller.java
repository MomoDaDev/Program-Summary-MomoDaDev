package sample;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    double mainSceneX, mainSceneY, rootPositionX, rootPositionY;
    private enum Colors{
        RED,
        BLUE,
        WHITE
    }
    Colors[][] gamepoints = new Colors[6][7];
    Colors currentcolor = Colors.RED;

    @FXML
    private GridPane maingrid;

    @FXML
    private Pane mainpane;

    @FXML
    private Label circle_coords;
    @FXML
    private Label l_coords;
    @FXML
    private Label label_winner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gamepoints[i][j] = Colors.WHITE;
            }
        }
        maingrid.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        for (int i = 0; i < 7; i++) {
            Rectangle r = new Rectangle();
            r.setWidth(40);
            r.setHeight(40);
            r.setStroke(Paint.valueOf("black"));
            r.setFill(Paint.valueOf("white"));
            maingrid.add(r, i, 0);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Circle c = new Circle();
                c.setRadius(20);
                c.setStroke(Paint.valueOf("black"));
                c.setFill(Paint.valueOf("white"));
                maingrid.add(c, i, j);
            }
        }

        mainpane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String msg =
                        "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                        "(sceneX: " + event.getSceneX() + ", sceneY: "  + event.getSceneY() + ") -- " +
                        "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

                l_coords.setText(msg);
            }
        });
        Circle behindchip = new Circle();
        behindchip.setRadius(20);
        behindchip.setStroke(Paint.valueOf("black"));
        behindchip.setFill(Paint.valueOf("lightgray"));
        rootPositionX = 111;
        rootPositionY = 75;
        behindchip.setCenterX(rootPositionX);
        behindchip.setCenterY(rootPositionY);
        mainpane.getChildren().add(behindchip);

        Circle chip = new Circle();
        chip.setRadius(20);
        chip.setStroke(Paint.valueOf("black"));
        chip.setFill(Paint.valueOf("red"));
        chip.setCenterX(rootPositionX);
        chip.setCenterY(rootPositionY);
        chip.setCursor(Cursor.HAND);
        chip.setOnMousePressed((t) -> {
            mainSceneX = t.getSceneX();
            mainSceneY = t.getSceneY();
        });
        chip.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - mainSceneX;
            double offsetY = t.getSceneY() - mainSceneY;

            chip.setCenterX(chip.getCenterX() + offsetX);
            chip.setCenterY(chip.getCenterY() + offsetY);

            mainSceneX = t.getSceneX();
            mainSceneY = t.getSceneY();
            circle_coords.setText("Circle: (x: " + chip.getCenterX() + ", y: " + chip.getCenterY() + ")");
            chip.setRadius(8);
        });
        chip.setOnMouseReleased((t) -> {
            System.out.println("released");
            double x = 159;
            double y = 55;
            double length = 40;
            double distance = 218 - 159;

            double chipX = chip.getCenterX();
            double chipY = chip.getCenterY();
            for (int i = 0; i < 7; i++) {
                double currentX = x + distance * i;
                if(chip.getCenterX() >= currentX && chip.getCenterX() <= currentX + length && chip.getCenterY() >= y && chip.getCenterY() <= y + length){
                    playgame(currentX + length / 2, y + length / 2, i);
                    if (currentcolor == Colors.BLUE){
                        chip.setFill(Paint.valueOf("blue"));
                    } else{
                        chip.setFill(Paint.valueOf("red"));
                    }
                    break;
                }
            }

            chip.setCenterX(rootPositionX);
            chip.setCenterY(rootPositionY);
            chip.setRadius(20);
        });
        mainpane.getChildren().add(chip);
    }
    private void playgame(double centerX, double centerY, int column){
        boolean free = false;
        int row = 0;
        for (int i = 0; i < 6; i++) {
            if (gamepoints[5 - i][column] == Colors.WHITE){
                gamepoints[5 - i][column] = currentcolor;
                free = true;
                row = 5 - i;
                break;
            }
        }
        if(!free){
            System.out.println("no free spot");
        }else{
            System.out.println(row);

            Circle droppingcircle = new Circle();

            if(currentcolor == Colors.BLUE){
                droppingcircle.setFill(Paint.valueOf("blue"));
                currentcolor = Colors.RED;
            } else{
                droppingcircle.setFill(Paint.valueOf("red"));
                currentcolor = Colors.BLUE;
            }
            System.out.println("playing");
            droppingcircle.setCenterX(centerX);
            droppingcircle.setCenterY(centerY);
            droppingcircle.setRadius(20);
            droppingcircle.setStroke(Paint.valueOf("black"));

            Line line = new Line();
            line.setStartX(centerX);
            line.setStartY(centerY);
            line.setEndX(centerX);
            line.setEndY(centerY + (row + 1) * 41.5);

            PathTransition transition = new PathTransition();
            transition.setDuration(Duration.millis(155 * (row + 1)));
            transition.setNode(droppingcircle);
            transition.setPath(line);
            transition.setCycleCount(1);

            mainpane.getChildren().add(droppingcircle);
            transition.play();

            if(checkwinner(Colors.BLUE)){
                label_winner.setText("BLUE WON!");
            }
            if(checkwinner(Colors.RED)){
                label_winner.setText("RED WON!");
            }
        }
    }
    private boolean checkwinner(Colors color){
        //rows
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(gamepoints[i][j] == color && gamepoints[i][j+1] == color && gamepoints[i][j+2] == color && gamepoints[i][j+3] == color){
                    return true;
                }
            }
        }
        //colums
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if(gamepoints[j][i] == color && gamepoints[j+1][i] == color && gamepoints[j+2][i] == color && gamepoints[j+3][i] == color){
                    return true;
                }
            }
        }
        //diagonal up right
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                if(gamepoints[j][i] == color && gamepoints[j-1][i+1] == color && gamepoints[j-2][i+2] == color && gamepoints[j-3][i+3] == color){
                    return true;
                }
            }
        }
        //diagonal down right
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if(gamepoints[j][i] == color && gamepoints[j+1][i+1] == color && gamepoints[j+2][i+2] == color && gamepoints[j+3][i+3] == color){
                    return true;
                }
            }
        }

        return false;
    }
}
