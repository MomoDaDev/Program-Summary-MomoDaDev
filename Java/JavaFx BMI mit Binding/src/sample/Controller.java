package sample;
// GANZ GANZ WICHTIG BEI MEINEM SCENBUILDER KANN ICH DIE CONTROLLER CLASS NICHT ÜBER SCENEBUILDER CONNECTEN UM METHODEN ZU VERWENDEN ALSO IN MEINEM FXML FIL 
// MUSS ICH BEI <Pane> ALS LETZTES ATTRIBUT   fx:controller="sample.Controller"   REINSCHREIBEN: ALSO ALS "fx:controller" was meine Klasse ist die ich wie den Controller verwenden will
// muss ich = "sample" für mein fxml file angeben und dahinter direkt ".Controller" also den Namen der Klasse
// https://medium.com/@prashantsedhain/how-to-assign-a-controller-to-fxml-file-made-in-scene-builder-in-javafx-application-5b19a64f8ab#:~:text=Controller%20Class%20is%20on%20the,the%20Scene%20Builder%20V%202.0.

import com.sun.javafx.scene.control.DoubleField;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea taBMI;
    @FXML
    private TextField tfKg;
    @FXML
    private TextField tfCm;
    @FXML
    private TextField tfAge;

    public DoubleProperty höheInMeter = new SimpleDoubleProperty();
    public DoubleProperty gewichtInKg = new SimpleDoubleProperty();

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Slider funktioniert nicht
        Slider slider = new Slider();
        tfKg.setText(slider.valueProperty().toString());
        tfCm.setText(slider.valueProperty().toString());

        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(tfCm.textProperty(), höheInMeter, converter);
        Bindings.bindBidirectional(tfKg.textProperty(), gewichtInKg, converter);

        NumberBinding value = gewichtInKg.divide(höheInMeter).divide(höheInMeter).multiply(10000);
        taBMI.textProperty().bind(value.asString());

        /*
        System.out.println(" i am here");
        höheInMeter = new SimpleDoubleProperty(Double.parseDouble(tfCm.getText()) / 100);
        gewichtInKg = new SimpleDoubleProperty(Double.parseDouble(tfKg.getText()));
        doubleNumber = new SimpleDoubleProperty(gewichtInKg.getValue() / Math.pow(höheInMeter.getValue(), 2));


        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional((Property<String>) taBMI, doubleNumber, converter);
        //taBMI.textProperty().bind(doubleNumber.getBean());

         */
    }

    /*double höheInMeter = Double.parseDouble(tfCm.getText()) / 100;
        double gewichtInKg = Double.parseDouble(tfKg.getText());
        ergebnis = new MyNumber(String.valueOf(gewichtInKg/Math.pow(höheInMeter, 2)));

        taBMI.textProperty().bind((ObservableValue<? extends String>) ergebnis);

     */
}
