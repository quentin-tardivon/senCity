package sencity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by quentin on 6/4/16.
 */
public class Main extends Application{

    @Override public void start(Stage stage) throws Exception {
        NumberAxis xAxis;
        NumberAxis yAxis;
        stage.setTitle("Couverture réseau");

        System.out.println("Veuillez entrer le nom du fichier wifi:");
        Scanner wifisc = new Scanner(System.in);
        String wifi = wifisc.nextLine();

        if (wifi.equals("capture_wifi.csv")) {
            xAxis = new NumberAxis(6.154,6.157,0.001);
            yAxis = new NumberAxis(48.668,48.670,0.001);
        }
        else {
            xAxis = new NumberAxis(6.153, 6.170, 0.0001);
            yAxis = new NumberAxis(48.669, 48.680, 0.00001);
        }
        System.out.println("Veuillez entrer le nom du fichier GPS:");
        Scanner gpssc = new Scanner(System.in);
        String gps = gpssc.nextLine();
        System.out.println("Veuillez entrer la tolérance désirée:");
        Scanner tolsc = new Scanner(System.in);
        String tolstr = tolsc.nextLine();
        double tol = Double.parseDouble(tolstr);

        System.out.println("Extract TreeTraces");
        TreeTraces trace_capture = new TreeTraces();

        try {

            trace_capture.load(wifi, gps, tol);
            System.out.println("Extracted");
        } catch (IOException exception) {
            System.out.println("Fichier introuvable");
        }
        Integer quit = 0;
        while (quit != 1) {

            ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
            xAxis.setLabel("Longitude");
            yAxis.setLabel("Latitude");
            sc.setTitle("Couverture Réseau");

            System.out.println("Quelle type de saisie voulez-vous utiliser? (0 pour complète, 1 pour prédictive)");
            Scanner reponse0 = new Scanner(System.in);
            String reponse = reponse0.nextLine();
            Integer rep = 0;
            rep = rep.parseInt(reponse);
            if (rep == 0) {
                System.out.println("Nom du ssid à extraire?");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                Traces extract_ssid = trace_capture.extract(reponse);
                System.out.println(extract_ssid.toString());

                XYChart.Series valTraces = new XYChart.Series();
                for (Trace i: extract_ssid) {
                    valTraces.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));

                }
                sc.getData().addAll(valTraces);
                Scene scene = new Scene(sc,500,400);
                scene.getStylesheets().add("./Chart.css");
                stage.setScene(scene);
                stage.show();


            } else if (rep == 1) {
                System.out.println("Préfixe du ssid à extraire?");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                LinkedList<Traces> extract_ssid = trace_capture.extractAll(reponse);
                System.out.println(extract_ssid.toString());
            }
            System.out.println("Voulez-vous encore extraire un ssid particulier? (1 pour quitter, 0 pour extraire)");
            Scanner reponse2 = new Scanner(System.in);
            String quitstr = reponse2.nextLine();
            quit = Integer.parseInt(quitstr);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
