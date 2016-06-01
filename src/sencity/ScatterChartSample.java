package sencity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;


public class ScatterChartSample extends Application {

    @Override public void start(Stage stage) {

        System.out.println("Extract GraphData");
        TreeTraces trace_capture = new TreeTraces();
        try {
            double time = System.currentTimeMillis();
            trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
            System.out.println(System.currentTimeMillis() - time);
        }
        catch(IOException exception) {
            System.out.println("Fichier introuvable");
        }
        double time = System.currentTimeMillis();
        Traces extract_ssid = trace_capture.extract("eduroam");
        Traces extract_ssid2 = trace_capture.extract("BDE");

        System.out.println(System.currentTimeMillis() - time);
        System.out.println(extract_ssid.toString());

        DataGraphMat GraphDeTest = new DataGraphMat(extract_ssid,10,90.0);
        DataGraphMat GraphDeTest2 = new DataGraphMat(extract_ssid2,10,90.0);

        System.out.println(GraphDeTest.toString());
        System.out.println(GraphDeTest.getPourcentage());
        System.out.println(GraphDeTest.surfaceReseau());
        stage.setTitle("Couverture réseau");
        NumberAxis xAxis = new NumberAxis(6.15, 6.16, 0.0001);
        NumberAxis yAxis = new NumberAxis(48.66, 48.67, 0.0001);
        ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle("Couverture Réseau");

        XYChart.Series valTraces = new XYChart.Series();
        valTraces.setName("eduroam");
        XYChart.Series valTraces2 = new XYChart.Series();
        valTraces2.setName("BDE");

        for (Trace i: GraphDeTest.getListeSommet()) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));
        }
        for (Trace i: GraphDeTest2.getListeSommet()) {
            valTraces2.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));
        }



        sc.getData().addAll(valTraces,valTraces2);
        Scene scene = new Scene(sc,500,400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void graphTraces(Stage stage,Collection<Trace> traces) {
        stage.setTitle("Couverture réseau");
        final NumberAxis xAxis = new NumberAxis(6.154, 6.158, 0.01);
        final NumberAxis yAxis = new NumberAxis(48.669, 48.670, 0.01);
        final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle("Couverture Réseau");

        XYChart.Series valTraces = new XYChart.Series();

        for (Trace i: traces) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));
        }

        sc.getData().addAll(valTraces);
        Scene scene = new Scene(sc,1000,600);
        stage.setScene(scene);
        stage.show();
    }
}