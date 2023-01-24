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
            sql = "SELECT " + (tfAtribut) + " FROM " + (tfTable) + " WHERE " + (tfCondition);

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
        String host = "TROE";
        String connStr = null;

        // Mit MySql verbinden
        connStr = "jdbc:mysql://" + host + "/troe" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        return connStr;
    }
    public void ausgeben(ResultSet rs) throws SQLException {

        String str;
        ArrayList list = new ArrayList();
        str = "";
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (i > 1) {
                str += ", ";
            }
            str += rs.getMetaData().getColumnLabel(i);
        }

        taOutput.setText(str + "\n\n");

        rs.getMetaData().getColumnCount();
        while (rs.next()) {
            str = "";
            list.clear();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                if (i > 1) {
                    str += ", ";
                }
                str += rs.getString(i);
                list.add(rs.getString(i));
            }

            taOutput.setText("\n" + taOutput.getText());
        }
    }
}
