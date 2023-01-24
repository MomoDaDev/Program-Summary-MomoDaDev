package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Label lbconnString;
    @FXML
    private TextField tfAtribut;
    @FXML
    private TextField tfTable;
    @FXML
    private TextField tfCondition;
    @FXML
    private Label lberrorMsg;
    @FXML
    private TextArea taOutput;
    @FXML
    private TextArea tainsertOutput;
    @FXML
    private TextArea taupdateOutput;
    @FXML
    private TextField tfinsertvalues;
    @FXML
    private TextField tfinserttable;
    @FXML
    private TextField tfupdatetable;
    @FXML
    private TextField tfupdatestatement;
    @FXML
    private TextField tfupdatecondition;
    @FXML
    private TextArea taudeleteOutput;
    @FXML
    private TextField tfdeletetable;
    @FXML
    private TextField tfdeletecondition;

    public enum StatementType {
        query,
        update
    }

    public void onConfirm(ActionEvent event) {
        // build statement from TextField: tfAtribut, tfTable, tfCondition
        String sql = "SELECT " + (tfAtribut.getText()) + " FROM " + (tfTable.getText());
        if (!tfCondition.getText().trim().equals(""))
            sql += " WHERE " + tfCondition.getText();
        send_request(sql, StatementType.query);
    }
    public String makeConnectionString() {
        String host = "127.0.0.1";
        String connStr = null;

        // Mit MySql verbinden
        connStr = "jdbc:mysql://" + host + "/troe" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        return connStr;
    }
    private String padleft(String str, int length){
        System.out.println("before:{" + str + "}");
        if (str.equals("null")) // the ll aren't wide enough in the textarea. That's why it needs an extions of 6 extra whitespaces
            str += "      "; // 6 spaces
        int x = str.length();
        for (int i = 0; i < length - x; i++) {
            str += " ";
        }
        System.out.println("after:{" + str + "}");
        return str;
    }
    public void ausgeben(ResultSet rs) throws SQLException {
        String str_result = "";
        String str;
        ArrayList list = new ArrayList();
        str = "";
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            String s = rs.getMetaData().getColumnLabel(i);
            if (i < rs.getMetaData().getColumnCount()) {
                str += padleft(s.trim(), 16);
                str += "| ";
            }
            else {
                str += s;
            }
        }

        str_result = str + "\n";
        for (int i = 0; i < str.length() + 6; i++) {
            str_result += "-";
        }

        rs.getMetaData().getColumnCount();
        while (rs.next()) {
            str = "";
            list.clear();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String s = rs.getString(i);
                System.out.println("[log]:" + s);
                if (s == null){
                    s = "null";
                }
                if (i < rs.getMetaData().getColumnCount()) {
                    str += padleft(s, 16);
                    str += "| ";
                } else {
                    str += s;
                }
                list.add(s);
            }

            str_result += "\n" + str;
        }

        taOutput.setText(str_result);
    }
    private boolean send_request(String sql, StatementType ST){
        //Insert into LAGER (LNR,ORT,STUECKKAP) values (11,'Probstdorf',40);
        Connection conn = null;
        String connStr;
        Statement stmt;
        ResultSet rs;
        String eingabe;

        try {
            connStr = this.makeConnectionString();
            lbconnString.setText(connStr);

            conn = DriverManager.getConnection(connStr, "troe", "troe_pw");

            stmt = conn.createStatement();

            switch (ST){
                case query:
                    rs = stmt.executeQuery(sql);
                    ausgeben(rs);
                    break;
                case update:
                    stmt.executeUpdate(sql);
                    break;
            }


            System.out.println("SQL:" + sql);

            lberrorMsg.setText("no errors");
            return true;
        } catch (SQLException ex) {
            lberrorMsg.setText(
                    "SQLException: " + ex.getMessage()
                            +"SQLState: " + ex.getSQLState()+"\n"
                            +"VendorError: " + ex.getErrorCode());
            // handle any errors

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }
    private boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch (Exception e){}
        return false;
    }
    public void onInsertConfirm(ActionEvent event) {
        //Insert into Preise (ANR, GUELTIG_AB, GUELTIG_BIS, PREIS) VALUES (2, '2014-01-01','2014-01-31',200);
        // build statement from TextField: , tfinsertvalues, tfinserttable
        StringBuffer stringBuffer = new StringBuffer();
        String[] values = tfinsertvalues.getText().split(",");
        for (int i = 0; i < values.length; i++) {
            if (!isNumeric(values[i])){
                stringBuffer.append("'" + values[i].trim() + "',");
            }
            else{
                stringBuffer.append(values[i] + ",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1); // remove ',' at the end
        String sql = "INSERT INTO " + (tfinserttable.getText()) + " VALUES (" + (stringBuffer) + ")";

        System.out.println(sql);

         boolean b = send_request(sql, StatementType.update);
         if (b){
             tainsertOutput.setText("Inserted values:\n\n" + sql);
         }
    }
    public void onUpdateConfirm(ActionEvent event) {
        //"update employee set name='Michael Sam' where emp_id=1";

        String sql = "UPDATE " + (tfupdatetable.getText()) + " SET " + (tfupdatestatement.getText()) + " WHERE " + tfupdatecondition.getText();

        boolean b = send_request(sql, StatementType.update);
        if (b){
            taupdateOutput.setText("Updated values:\n\n" + sql);
        }
    }
    public void onDeleteConfirm(ActionEvent event) {
        //"DELETE FROM Registration WHERE id = 101";

        String sql = "DELETE FROM " + (tfdeletetable.getText()) + " WHERE " + (tfdeletecondition.getText());

        boolean b = send_request(sql, StatementType.update);
        if (b){
            taudeleteOutput.setText("Deleted values");
        }
    }
}
