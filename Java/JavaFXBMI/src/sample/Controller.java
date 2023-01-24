package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField tfergebnis;
    @FXML
    private javafx.scene.control.TextField tfcm;
    @FXML
    private javafx.scene.control.TextField tfkg;
    @FXML
    private javafx.scene.control.TextField tfJahre;

    public DoubleProperty cm = new SimpleDoubleProperty();
    public DoubleProperty kg = new SimpleDoubleProperty();
    public DoubleProperty ergebnis = new SimpleDoubleProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfcm.textProperty().bind(cm.asString());

        tfkg.textProperty().bind(kg.asString());

        ergebnis.bind(kg.divide(Math.pow(cm.doubleValue(), 2)));

        tfergebnis.textProperty().bind(ergebnis.asString());
    }
}
