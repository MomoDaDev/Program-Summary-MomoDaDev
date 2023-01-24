package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Pane mainpane;

    // Circle
    double radius = 15;
    double distanceVertical = 40;
    double distanceHorizontal = 260;
    double startpositionX = 300;
    double startpositionY = 30 + radius;
    double orgSceneX, orgSceneY;

    private Tree roottree;

    public void draw(Line line, Tree tree, double posX, double posY, double distanceVertical, double distanceHorizontal, double currentrow) {
        Circle c = new Circle();

        if (line != null){
            line.endXProperty().bind(c.centerXProperty());
            line.endYProperty().bind(c.centerYProperty());
            mainpane.getChildren().add(line);
        }

        c.setCursor(Cursor.HAND);

        c.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

        });
        c.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;


            c.setCenterX(c.getCenterX() + offsetX);
            c.setCenterY(c.getCenterY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        c.setCenterX(posX);
        c.setCenterY(posY);
        c.setRadius(radius);
        c.setStroke(Paint.valueOf("black"));
        c.setFill(Paint.valueOf("white"));
        Label l = new Label(String.valueOf(tree.number));
        l.setDisable(true);
        l.layoutXProperty().bind(c.centerXProperty().subtract(radius / 2.1));
        l.layoutYProperty().bind(c.centerYProperty().subtract(radius / 1.8));
        //left
        Line lineleft = new Line();
        if (tree.left != null){
            lineleft.startXProperty().bind(c.centerXProperty());
            lineleft.startYProperty().bind(c.centerYProperty());
            draw(lineleft, tree.left, posX - distanceHorizontal, posY + distanceVertical, distanceVertical, distanceHorizontal / 2, currentrow + 1);
        }
        //right
        Line lineright = new Line();
        if (tree.right != null){
            lineright.startXProperty().bind(c.centerXProperty());
            lineright.startYProperty().bind(c.centerYProperty());
            draw(lineright, tree.right, posX + distanceHorizontal, posY + distanceVertical, distanceVertical, distanceHorizontal / 2, currentrow + 1);
        }

        mainpane.getChildren().add(c);

        mainpane.getChildren().add(l);
    }

    /*
    public CustomGrid calculateRows(int numberOfRows, double startX, double startY, double distanceVertical, double distanceHorizontal) {
        Row[] rows; //first row gets initialized seperately so that the startposition is set
        if(numberOfRows <= 0){
            System.out.println("calculateRows returned null");
            return null;
        } else{
            System.out.println("first row getting initialized");
            rows = new Row[numberOfRows];
            Position[] row1 = new Position[1];
            row1[0] = new Position(startX, startY);
            rows[0] = new Row(row1);
            System.out.println(row1[0].posX + ", " + row1[0].posY);
            System.out.println("first row sucessfully initialized");
        }

        for (int i = 1; i < numberOfRows; i++) {
            int index = 0;
            Position[] positions = new Position[(int)Math.pow(2, i)];
            System.out.println((int)Math.pow(2, i));
            System.out.println("positions created (row " + i + ")");
            for (int j = 0; j < Math.pow(2, i - 1); j++) {
                Position[] leftandright = new Position[2];
                //left
                leftandright[0] = new Position(rows[i-1].positions[j].posX
                        - (distanceHorizontal / Math.pow(2, i)), rows[i-1].positions[j].posY + distanceVertical);
                System.out.println("left created");
                System.out.println(leftandright[0].posX + ", " + leftandright[0].posY);
                positions[index] = leftandright[0];
                index++;
                //right
                leftandright[1] = new Position(rows[i-1].positions[j].posX
                        + (distanceHorizontal / Math.pow(2, i)), rows[i-1].positions[j].posY + distanceVertical);
                System.out.println("right created");
                System.out.println(leftandright[1].posX + ", " + leftandright[1].posY);
                positions[index] = leftandright[1];
                index++;
            }
            System.out.println("Positions: (X / Y)");
            for (int j = 0; j < positions.length; j++) {
                System.out.println(positions[j].posX + ", " + positions[j].posY);
            }
            System.out.println("-------------------------");
            rows[i] = new Row(positions);
            System.out.println("row " + i + " initialized");
        }
        return new CustomGrid(rows);
    }
    */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Row 7
        Tree r7_3 = new Tree(57, null, null);
        //Row 6
        Tree r6_7 = new Tree(67, null, null);
        Tree r6_9 = new Tree(69, r7_3, null);
        //Row 5
        Tree r5_1 = new Tree(51, null, null);
        Tree r5_2 = new Tree(52, null, null);
        Tree r5_3 = new Tree(53, null, null);
        Tree r5_5 = new Tree(55, r6_7, null);
        Tree r5_6 = new Tree(56, null, null);
        Tree r5_7 = new Tree(57, null, r6_9);
        Tree r5_8 = new Tree(58, null, null);
        Tree r5_9 = new Tree(59, null, null);
        Tree r5_10 = new Tree(510, null, null);
        //Row 4
        Tree r4_1 = new Tree(41, r5_1, r5_2);
        Tree r4_2 = new Tree(42, r5_3, r5_5);
        Tree r4_3 = new Tree(43, r5_6, r5_7);
        Tree r4_5 = new Tree(45, r5_8, r5_9);
        Tree r4_6 = new Tree(46, r5_10, null);
        //Row 3
        Tree r3_1 = new Tree(31, r4_1, r4_2);
        Tree r3_2 = new Tree(32, r4_3, null);
        Tree r3_3 = new Tree(33, r4_5, r4_6);
        //Row 2
        Tree r2_1 = new Tree(21, r3_1, r3_2);
        Tree r2_2 = new Tree(22, r3_3, null);
        //Row 1
        roottree = new Tree(11, r2_1, r2_2);
    }
    public void btn_delete_pressed(ActionEvent event) {
        Button build_tree = (Button) mainpane.getChildren().get(0);
        Button delete = (Button) mainpane.getChildren().get(1);
        mainpane.getChildren().clear();
        mainpane.getChildren().addAll(
                build_tree,
                delete
        );
        System.out.println("Deleted");
    }
    public void btn_pressed(ActionEvent event) {

        draw(null, roottree, startpositionX, startpositionY, distanceVertical, distanceHorizontal / 2, 1);

        /*
        CustomGrid customGrid = calculateRows(4, startpositionX, startpositionY, distanceVertical, distanceHorizontal);
        for (int i = 0; i < customGrid.rows.length; i++) {
            for (int j = 0; j < customGrid.rows[i].positions.length; j++) {
                Circle c = new Circle();
                c.setLayoutX(customGrid.rows[i].positions[j].posX);
                c.setLayoutY(customGrid.rows[i].positions[j].posY);
                c.setRadius(radius);
                c.setFill(Paint.valueOf("grey"));
                Label l = new Label("1");
                l.setLayoutX(customGrid.rows[i].positions[j].posX - radius / 2.1);
                l.setLayoutY(customGrid.rows[i].positions[j].posY - radius / 1.8);
                mainpane.getChildren().add(c);
                mainpane.getChildren().add(l);
            }
        }*/
/*
        double endpositionX = startpositionX - 50;
        double endpositionY = startpositionY + 50;

        Line l1 = new Line();
        l1.setStartX(startpositionX);
        l1.setStartY(startpositionY);
        l1.setEndX(endpositionX);
        l1.setEndY(endpositionY);

        Circle c1 = new Circle();
        c1.setCenterX(startpositionX);
        c1.setCenterY(startpositionY);
        c1.setRadius(radius);
        c1.setStroke(Paint.valueOf("red"));


        Circle c2 = new Circle();
        c2.setCenterX(endpositionX);
        c2.setCenterY(endpositionY);
        c2.setRadius(radius);

        mainpane.getChildren().add(l1);
        mainpane.getChildren().add(c1);
        mainpane.getChildren().add(c2);
        mainpane.getChildren().add(new Button("test"));

        System.out.println("done");*/
    }
}
