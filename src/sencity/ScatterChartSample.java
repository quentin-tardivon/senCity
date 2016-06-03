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
            trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
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
        NumberAxis xAxis = new NumberAxis(6.15,6.16,0.001);
        NumberAxis yAxis = new NumberAxis(48.666,48.671,0.001);
        ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle("Couverture Réseau");

        /*Collection<Traces> extract_all = trace_capture.extractAll("");
        for (Traces i : extract_all) {
            DataGraphMat GraphDeTest = new DataGraphMat(i,10,90.0);
            XYChart.Series valTraces = new XYChart.Series();
            for (Trace j : i) {
                valTraces.getData().add(new XYChart.Data(j.coord.getLongitude()*1,j.coord.getLatitude()*1));
            }
            sc.getData().addAll(valTraces);
        }*/


        DataGraphMat GraphDeTest = new DataGraphMat(extract_ssid2,10,190.0);
        XYChart.Series valTraces = new XYChart.Series();
        int indiceI = 0;
        Trace trace1 = null;
        Trace trace2 = null;
        for (Trace i: GraphDeTest.getListeSommet()) {
            indiceI += 1;
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude()*1,i.coord.getLatitude()*1));
            if (indiceI == 1) {
                trace1 = i;
            }
            else if(indiceI == 2) {
                trace2 = i;
            }
        }
        /*XYChart.Series valTraces2 = new XYChart.Series();
        for (Trace i: GraphDeTest.dijkstra(trace1,trace2)) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude()*1,i.coord.getLatitude()*1));
        }*/
        sc.getData().addAll(valTraces);

        Scene scene = new Scene(sc,500,400);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}