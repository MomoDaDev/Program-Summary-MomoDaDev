package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private ComboBox cb_language;
    @FXML
    private TextArea ta_input;
    @FXML
    private TextArea ta_output;
    @FXML
    private Label l_error;

    public void btn_translate_click(ActionEvent event){
        System.out.println("btn_translate clicked");

        if (cb_language.getValue() != null &&
                !cb_language.getValue().toString().isEmpty()){
            String language = cb_language.getValue().toString();
            System.out.println("Translating to " + language);
            l_error.setVisible(false);

            translate(ta_input.getText(), language);
        } else {
            l_error.setVisible(true);
            l_error.setText("Error: Please select a language");
        }


    }
    private void translate(String input, String language){
        System.out.println("Starting to translate");
        System.out.println("Input: " + input);

        String result_output = "";

        String output = request_content(input);
        System.out.println("request_content return value: " + output);

        if (output.equals("error")){
            l_error.setVisible(true);
            l_error.setText("Error: Problems while translating");
            return;
        }

        String[] output_split = output.split("\"");

        for (int i = 1; i < output_split.length; i += 4) {
            if (cb_language.getValue().equals(output_split[i])){
                result_output = output_split[i + 2]; // get the content in the selected language
                System.out.println("Output from array: " + output_split[i + 2]);
                break;
            }
        }
        ta_output.setText(result_output);

        System.out.println("Output: " + result_output);
    }
    private void initialize_languages() {
        String output = request_content("test");

        System.out.println(output);

        String[] outputsplit = output.split("\"");

        for (int i = 1; i < outputsplit.length; i += 4) {
            cb_language.getItems().add(outputsplit[i]);
        }
    }
    private String request_content(String text){
        String output = "";
        try {
            URL url = new URL("https://translator-server1.herokuapp.com/" + text);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            output = content.toString();
        } catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "error";
        }
        return output;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_languages();

        System.out.println("Initialized!");
    }
}
