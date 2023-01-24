package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ObservableList<Schüler> schüler = FXCollections.observableArrayList();
    TableView<Schüler> tableView;

    @FXML
    public Pane mainpane;
    @FXML
    public TextField searchnachname;
    @FXML
    public TextField searchvorname;
    @FXML
    public CheckBox islam;
    @FXML
    public CheckBox ob;
    @FXML
    public CheckBox pfk;
    @FXML
    public CheckBox römkath;
    @FXML
    public CheckBox evang;
    @FXML
    public CheckBox alevi;
    @FXML
    public CheckBox serborth;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileReader fr = new FileReader("././files/data.csv");
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); //skip first row
            br.readLine(); //skip second row
            String line;
            while ((line = br.readLine()) != null){
                Schüler s = new Schüler();
                String[] linesplitted = line.split(",");
                s.Katalognummer = Integer.parseInt(linesplitted[0]);
                s.Nachname = linesplitted[1];
                s.Vorname = linesplitted[2];
                s.Religion = linesplitted[3];

                int currentindex = 1;
                //,,RK,RE,RISL,ORTH,QUM,ITZ-J,UNITY,BSPKU <---
                //BSPKU
                s.BSPKU = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.BSPKU) { currentindex++; }
                currentindex++;
                //UNITY
                s.UNITY = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.UNITY) { currentindex++; }
                currentindex++;
                //ITZ-J
                s.ITZJ = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.ITZJ) { currentindex++; }
                currentindex++;
                //QUM
                s.QUM = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.QUM) { currentindex++; }
                currentindex++;
                //ORTH
                s.ORTH = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.ORTH) { currentindex++; }
                currentindex++;
                //RISL
                s.RISL = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.RISL) { currentindex++; }
                currentindex++;
                //RE
                s.RE = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.RE) { currentindex++; }
                currentindex++;
                //RK
                s.RK = (line.substring(line.length() - currentindex, line.length() - currentindex + 1).equals("x"));
                if (s.RK) { currentindex++; }
                currentindex++;

                schüler.add(s);
                System.out.println(s.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //tableview
        //Katalognummer column
        TableColumn<Schüler, Integer> katalognummerColumn = new TableColumn<>("Katalognummer");
        katalognummerColumn.setMinWidth(20);
        katalognummerColumn.setCellValueFactory(new PropertyValueFactory<>("Katalognummer"));
        //Nachname column
        TableColumn<Schüler, String> nachnameColumn = new TableColumn<>("Nachname");
        nachnameColumn.setMinWidth(150);
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("Nachname"));
        //Vorname column
        TableColumn<Schüler, String> vornameColumn = new TableColumn<>("Vorname");
        vornameColumn.setMinWidth(200);
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("Vorname"));
        //Religion column
        TableColumn<Schüler, String> religionColumn = new TableColumn<>("Religion");
        religionColumn.setMinWidth(200);
        religionColumn.setCellValueFactory(new PropertyValueFactory<>("Religion"));
        //RK,RE,RISL,ORTH,QUM,ITZ-J,UNITY,BSPKU
        //RK column
        TableColumn<Schüler, Boolean> RKColumn = new TableColumn<>("RK");
        RKColumn.setMinWidth(40);
        RKColumn.setCellValueFactory(new PropertyValueFactory<>("RK"));
        //RE column
        TableColumn<Schüler, Boolean> REColumn = new TableColumn<>("RE");
        REColumn.setMinWidth(40);
        REColumn.setCellValueFactory(new PropertyValueFactory<>("RE"));
        //RISL column
        TableColumn<Schüler, Boolean> RISLColumn = new TableColumn<>("RISL");
        RISLColumn.setMinWidth(40);
        RISLColumn.setCellValueFactory(new PropertyValueFactory<>("RISL"));
        //ORTH column
        TableColumn<Schüler, Boolean> ORTHColumn = new TableColumn<>("ORTH");
        ORTHColumn.setMinWidth(40);
        ORTHColumn.setCellValueFactory(new PropertyValueFactory<>("ORTH"));
        //QUM column
        TableColumn<Schüler, Boolean> QUMColumn = new TableColumn<>("QUM");
        QUMColumn.setMinWidth(40);
        QUMColumn.setCellValueFactory(new PropertyValueFactory<>("QUM"));
        //ITZ-J column
        TableColumn<Schüler, Boolean> ITZJColumn = new TableColumn<>("ITZJ");
        ITZJColumn.setMinWidth(40);
        ITZJColumn.setCellValueFactory(new PropertyValueFactory<>("ITZJ"));
        //UNITY column
        TableColumn<Schüler, Boolean> UNITYColumn = new TableColumn<>("UNITY");
        UNITYColumn.setMinWidth(40);
        UNITYColumn.setCellValueFactory(new PropertyValueFactory<>("UNITY"));
        //BSPKU column
        TableColumn<Schüler, Boolean> BSPKUColumn = new TableColumn<>("BSPKU");
        BSPKUColumn.setMinWidth(40);
        BSPKUColumn.setCellValueFactory(new PropertyValueFactory<>("BSPKU"));

        //select columns
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(schüler);
        tableView.getColumns().addAll(katalognummerColumn, nachnameColumn, vornameColumn, religionColumn,
                RKColumn, REColumn, RISLColumn, ORTHColumn, QUMColumn, ITZJColumn, UNITYColumn, BSPKUColumn);

        mainpane.getChildren().add(tableView);

        //setting up searchbar for Vorname
        searchvorname.textProperty().addListener((observable, oldValue, newValue) -> {
            updateAll();
        });

        //setting up searchbar for Nachname
        searchnachname.textProperty().addListener((observable, oldValue, newValue) -> {
            updateAll();
        });

        //setting up checkboxes for religions
        islam.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        ob.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        pfk.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        römkath.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        evang.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        alevi.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
        serborth.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateAll();
            }
        });
    }
    public void updateAll(){
        tableView.setItems(updateReligion(updateNachname(updateVorname(schüler))));
    }
    public ObservableList<Schüler> updateReligion(ObservableList<Schüler> schüler){
        ObservableList<Schüler> updatedschüler = FXCollections.observableArrayList();
        for (int i = 0; i < schüler.size(); i++) {
            if (!alevi.isSelected() && schüler.get(i).Religion.equals("ALEVI")){ continue; }
            if (!evang.isSelected() && schüler.get(i).Religion.equals("evang. A.B.")){ continue; }
            if (!islam.isSelected() && schüler.get(i).Religion.equals("islam.")){ continue; }
            if (!ob.isSelected() && schüler.get(i).Religion.equals("o.B.")){ continue; }
            if (!pfk.isSelected() && schüler.get(i).Religion.equals("PfK Gem. Gottes iÖ")){ continue; }
            if (!römkath.isSelected() && schüler.get(i).Religion.equals("röm.-kath.")){ continue; }
            if (!serborth.isSelected() && schüler.get(i).Religion.equals("serb.-orth.")){ continue; }
            updatedschüler.add(schüler.get(i));
        }
        return updatedschüler;
    }

    public ObservableList<Schüler> updateVorname(ObservableList<Schüler> schüler) {
        ObservableList<Schüler> updatedschüler = FXCollections.observableArrayList();
        for (int i = 0; i < schüler.size(); i++) {
            if (schüler.get(i).Vorname.toLowerCase().contains(searchvorname.getText().toLowerCase())){
                updatedschüler.add(schüler.get(i));
            }
        }
        return updatedschüler;
    }

    public ObservableList<Schüler> updateNachname(ObservableList<Schüler> schüler) {
        ObservableList<Schüler> updatedschüler = FXCollections.observableArrayList();
        for (int i = 0; i < schüler.size(); i++) {
            if (schüler.get(i).Nachname.toLowerCase().contains(searchnachname.getText().toLowerCase())){
                updatedschüler.add(schüler.get(i));
            }
        }
        return updatedschüler;
    }
}
