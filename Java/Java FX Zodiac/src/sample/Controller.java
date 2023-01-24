package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label zodiac_label;
    @FXML
    private Pane mainpane;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        setImage();
        setSign();
        System.out.println("Initialized");
    }
    private void setImage(){
        try {
            imageView.setImage(new Image(new FileInputStream("././images/zodiac_signs.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void datePicketOnAction(ActionEvent event){
        setSign();
    }
    private void setSign(){
        zodiac_label.setText("Your Zodiac Sign: " + getZodiac(datePicker.getValue()));
    }
    private String getZodiac(LocalDate localDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        int month = localDate.getMonthValue();
        System.out.println(month);
        int day = localDate.getDayOfMonth();
        String sign = "nothing";
        switch (month){
            case 1:
                if (day < 20)
                    sign = "Capricorn";
                else
                    sign = "Aquarius";
                break;
            case 2:
                if (day < 19)
                    sign = "Aquarius";
                else
                    sign = "Pisces";
                break;
            case 3:
                if (day < 21)
                    sign = "Pisces";
                else
                    sign = "Aries";
                break;
            case 4:
                if (day < 20)
                    sign = "Aries";
                else
                    sign = "Taurus";
                break;
            case 5:
                if (day < 21)
                    sign = "Taurus";
                else
                    sign = "Gemini";
                break;
            case 6:
                if (day < 21)
                    sign = "Gemini";
                else
                    sign = "Cancer";
                break;
            case 7:
                if (day < 23)
                    sign = "Cancer";
                else
                    sign = "Leo";
                break;
            case 8:
                if (day < 23)
                    sign = "Leo";
                else
                    sign = "Virgo";
                break;
            case 9:
                if (day < 23)
                    sign = "Virgo";
                else
                    sign = "Libra";
                break;
            case 10:
                if (day < 23)
                    sign = "Libra";
                else
                    sign = "Scorpio";
                break;
            case 11:
                if (day < 22)
                    sign = "Scorpio";
                else
                    sign = "Sagittarius";
                break;
            case 12:
                if (day < 22)
                    sign = "Sagittarius";
                else
                    sign ="Capricorn";
                break;
            default:
                System.out.println("invalid input");
        }
        return sign;
    }
}



