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
            //trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
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

        stage.setTitle("Couverture réseau");
        /*NumberAxis xAxis = new NumberAxis(6.154,6.157,0.001);
        NumberAxis yAxis = new NumberAxis(48.668,48.670,0.001);*/
        NumberAxis xAxis = new NumberAxis(6.153, 6.170, 0.0001);
        NumberAxis yAxis = new NumberAxis(48.669, 48.680, 0.00001);
        ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle("Couverture Réseau");

        Collection<Traces> extract_all = trace_capture.extractAll("");
        for (Traces i : extract_all) {
            //DataGraphMat GraphDeTest = new DataGraphMat(i,10,90.0);
            XYChart.Series valTraces = new XYChart.Series();
            for (Trace j : i) {
                valTraces.getData().add(new XYChart.Data(j.coord.getLongitude(),j.coord.getLatitude()));
            }
            sc.getData().addAll(valTraces);
        }


        /*DataGraphMat GraphDeTest = new DataGraphMat(extract_ssid2,10,90.0);
        XYChart.Series valTraces = new XYChart.Series();
        for (Trace i: GraphDeTest.getListeSommet()) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude()*1,i.coord.getLatitude()*1));
        sc.getData().addAll(valTraces);
        }*/


        Scene scene = new Scene(sc,500,400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}