package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.FileReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    static double standort_x = 23.69;
    static double standort_y = 7.71;
    static double factor = 15; // adjusts the coords to the window size
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @FXML
    private Label l_coords;
    @FXML
    private Pane mainpane;
    @FXML
    private CheckBox removeafterclick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainpane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String msg = "sceneX: " + event.getSceneX() + ", sceneY: "  + event.getSceneY()
                        + " -- " + "coord_X: " + df2.format(event.getSceneX() / factor)
                        + " km , coord_Y: "  + df2.format(event.getSceneY()/ factor) + " km";
                standort_x = event.getSceneX() / factor;
                standort_y = event.getSceneY() / factor;
                l_coords.setText(msg);
            }
        });



        Set<Laden> laeden = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("././210218KoordinatenLaden.csv");
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String[] ls;
            while ((ls = csvReader.readNext()) != null){
                laeden.add(new Laden(Integer.parseInt(ls[0]), ls[1], Double.parseDouble(ls[2]), Double.parseDouble(ls[3])));
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        // ----------------------------------
        // get mouse positions when clicked
        mainpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getSceneX());
                System.out.println(event.getSceneY());

                draw(getimUmkreis(laeden, 1.5), event.getSceneX(), event.getSceneY());
            }
        });
    }

    private void draw(List<Laden> laden, double coord_X, double coord_Y) {
        System.out.println(String.format(laden.stream().
                map(Object::toString).
                collect(Collectors.joining("\n")).toString()));
        System.out.println("Anzahl: " + laden.size());

        if (removeafterclick.isSelected()){

            Node x1 = mainpane.getChildren().get(0);
            Node x2 = mainpane.getChildren().get(1);
            Node x3 = mainpane.getChildren().get(2);

            mainpane.getChildren().clear();

            mainpane.getChildren().addAll(x1, x2, x3);
        }

        // Laeden:
        for (int i = 0; i < laden.size(); i++) {
            Circle c_laden = new Circle();
            c_laden.setRadius(4);
            c_laden.setStroke(Paint.valueOf("black"));
            c_laden.setFill(Paint.valueOf("red"));
            c_laden.setCenterX(laden.get(i).X * factor);
            c_laden.setCenterY(laden.get(i).Y * factor);
            mainpane.getChildren().add(c_laden);
        }
        // eigener Standort
        Circle player = new Circle();
        player.setRadius(4);
        player.setStroke(Paint.valueOf("black"));
        player.setFill(Paint.valueOf("blue"));
        player.setCenterX(coord_X);
        player.setCenterY(coord_Y);
        mainpane.getChildren().add(player);
    }

    private static List<Laden> getimUmkreis(Set<Laden> laeden, double radius){
        return laeden.stream()
                .filter(l -> (Math.pow(Math.abs(l.X - standort_x), 2)
                        + Math.pow(Math.abs(l.Y - standort_y), 2) <= Math.pow(radius, 2)))
                .collect(Collectors.toList());
    }
}
