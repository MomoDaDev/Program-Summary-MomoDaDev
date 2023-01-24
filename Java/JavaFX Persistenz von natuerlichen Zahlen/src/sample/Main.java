package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
=======
import javafx.scene.chart.LineChart;
>>>>>>> cfcd87725067ccb600b3016b36ae44d02e9c66b9
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Persistence of numbers");
        xAxis.setLabel("Number");
        yAxis.setLabel("Persistence");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Persistence");
        for (int i = 0; i < 10; i++) {
            series1.getData().add(new XYChart.Data(String.valueOf(i), Berechne.getPersistence(i)));
        }

        bc.getData().addAll(series1);


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
<<<<<<< HEAD
        primaryStage.setTitle("Persistence Calculator");
        primaryStage.setScene(new Scene(root, 1080, 600));
=======
        primaryStage.setTitle("Persistence");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Persistenz");
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < 10000; i++) {
            series.getData().add(new XYChart.Data(i, Berechne.getPersistence(i)));
        }

        primaryStage.setScene(new Scene(lineChart, 800, 600));
        lineChart.getData().add(series);
>>>>>>> cfcd87725067ccb600b3016b36ae44d02e9c66b9
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
    private void fillBarChart(int start, int end) {

    }