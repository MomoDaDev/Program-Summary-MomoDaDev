package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField searchbar;
    @FXML
    private Label errorlabelSearchbar;
    public void onSearch(Event event){
        if (searchbar.getText().trim().equals("")) {
            errorlabelSearchbar.setVisible(true);
        } else {
            errorlabelSearchbar.setVisible(false);
        }
    }
}
