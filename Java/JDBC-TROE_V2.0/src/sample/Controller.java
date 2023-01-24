package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void onConfirm(ActionEvent event) {
        Connection conn = null;
        String connStr;
        Statement stmt;
        ResultSet rs;
        String sql;
        String eingabe;

        try {
            connStr = this.makeConnectionString();
            lbconnString.setText(connStr);

            conn = DriverManager.getConnection(connStr, "troe", "troe_pw");

            // build statement from TextField: tfAtribut, tfTable, tfCondition
            stmt = conn.createStatement();
            sql = "SELECT " + (tfAtribut.getText()) + " FROM " + (tfTable.getText());
            if (!tfCondition.getText().trim().equals(""))
                sql += " WHERE " + tfCondition.getText();

            rs = stmt.executeQuery(sql);

            ausgeben(rs);

            System.out.println("SQL:" + sql);

            lberrorMsg.setText("no errors");
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
}
