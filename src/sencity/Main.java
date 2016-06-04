package sencity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by quentin on 6/4/16.
 */
public class Main extends Application {


    @Override public void start(Stage stage) throws Exception {
        System.out.println("Veuillez entrer le nom du fichier wifi:");
        Scanner wifisc = new Scanner(System.in);
        String wifi = wifisc.nextLine();
        int axis = 1;
        if (wifi.equals("capture_wifi.csv")) {
            axis = 0;
        }
        LinkedList<Traces> listeResultats= new LinkedList<Traces>();
        LinkedList<LinkedList<Traces>> listeResultatsMultiGraphe= new LinkedList<>();
        LinkedList<String> listeTitre = new LinkedList<>();
        LinkedList<String> listeTitreMultiGraphe = new LinkedList<>();
        System.out.println("Veuillez entrer le nom du fichier GPS:");
        Scanner gpssc = new Scanner(System.in);
        String gps = gpssc.nextLine();
        System.out.println("Veuillez entrer la tolérance désirée:");
        Scanner tolsc = new Scanner(System.in);
        String tolstr = tolsc.nextLine();
        double tol = Double.parseDouble(tolstr);

        System.out.println("Extraction");
        TreeTraces trace_capture = new TreeTraces();

        try {

            trace_capture.load(wifi, gps, tol);
            System.out.println("Extracted");
        } catch (IOException exception) {
            System.out.println("Fichier introuvable");
        }
        Integer quit = 0;
        while (quit != 1) {

            System.out.println("Quelle type d'action souhaitez-vous réaliser?\n (0 pour extraction par nom complet, 1 pour extraction prédictive, 2 pour création de graphe)");
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
                listeResultats.add(extract_ssid);
                listeTitre.add(reponse);

            } else if (rep == 1) {
                System.out.println("Préfixe du ssid à extraire?");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                LinkedList<Traces> extract_ssid = trace_capture.extractAll(reponse);
                System.out.println(extract_ssid.toString());
                Traces resultat = new LinkedListTraces();
                for (Traces i : extract_ssid) {
                    for (Trace j : i) {
                        resultat.ajouter(j);
                    }
                }
                listeTitre.add(reponse);
                listeResultats.add(resultat);
            }
            else if (rep == 2) {
                System.out.println("Voulez-vous faire un Graphe pour un SSID, plusieurs SSID ou tous les SSID?\n (0 pour un, 1 pour plusieurs, 2 pour tous)");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                Integer repo = 0;
                repo = repo.parseInt(reponse);
                if (repo == 0) {
                    System.out.println("Nom du ssid à extraire?");
                    Scanner reponse2 = new Scanner(System.in);
                    String reponse21 = reponse2.nextLine();
                    System.out.println("Seuil de distance?");
                    Scanner reponse3 = new Scanner(System.in);
                    String reponse31 = reponse3.nextLine();
                    System.out.println("Seuil de signal?");
                    Scanner reponse4 = new Scanner(System.in);
                    String reponse41 = reponse4.nextLine();

                    int seuilSignal = Integer.parseInt(reponse41);
                    double seuilDistance = Double.parseDouble(reponse31);
                    Traces extract_ssid = trace_capture.extract(reponse21);
                    System.out.println(extract_ssid.toString());
                    DataGraphMat dataGraph = new DataGraphMat(extract_ssid,seuilSignal,seuilDistance);


                    System.out.println("Latitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double lat1 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double long1 = Double.parseDouble(reponse1.nextLine());

                    GPS coord1 = new GPS(lat1,long1);

                    System.out.println("Latitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double lat2 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double long2 = Double.parseDouble(reponse1.nextLine());

                    GPS coord2 = new GPS(lat2,long2);

                    int indiceI = 0;
                    int depart = -1;
                    int arrive = -1 ;
                    System.out.print(dataGraph.getListeSommet().toString());
                    for (Trace i: dataGraph.getListeSommet()) {
                        if (i.coord.equals(coord1)) {
                            depart = indiceI;
                        }
                        else if (i.coord.equals(coord2)) {
                            arrive = indiceI;
                        }
                        indiceI+=1;
                    }
                    int[] dijkstraRes = dataGraph.dijkstra(depart);
                    ArrayList<Integer> courtChemin = dataGraph.afficheChemin(dijkstraRes,depart,arrive);
                    Traces cheminCourt = new LinkedListTraces();

                    for (int i=0; i<courtChemin.size();i++) {
                        Integer indice = courtChemin.get(i);
                        cheminCourt.ajouter(dataGraph.getListeSommet().get(indice));
                    }
                    LinkedList<Traces> resultatInter = new LinkedList<>();
                    resultatInter.add(cheminCourt);
                    resultatInter.add(extract_ssid);
                    listeResultatsMultiGraphe.add(resultatInter);
                    listeTitreMultiGraphe.add(reponse21 + " " + coord1.toString() + " " + coord2.toString());

                }
                else if(repo == 1) {
                    int quit2 = 0;
                    Traces extract_ssid = new LinkedListTraces();
                    String titre = "";
                    while (quit2 == 0) {
                        System.out.println("Nom du ssid à extraire?");
                        Scanner reponse2 = new Scanner(System.in);
                        String reponse21 = reponse2.nextLine();
                        titre = titre + reponse21;
                        Traces partial_extract = trace_capture.extract(reponse21);
                        for (Trace i : partial_extract) {
                            extract_ssid.ajouter(i);
                        }
                        System.out.println("Ajouter un autre ssid?\n (0 si oui, 1 sinon)");
                        Scanner reponse5 = new Scanner(System.in);
                        quit2 = Integer.parseInt(reponse5.nextLine());
                    }
                    System.out.println("Seuil de distance?");
                    Scanner reponse3 = new Scanner(System.in);
                    String reponse31 = reponse3.nextLine();
                    System.out.println("Seuil de signal?");
                    Scanner reponse4 = new Scanner(System.in);
                    String reponse41 = reponse4.nextLine();

                    int seuilSignal = Integer.parseInt(reponse41);
                    double seuilDistance = Double.parseDouble(reponse31);
                    System.out.println(extract_ssid.toString());
                    DataGraphMat dataGraph = new DataGraphMat(extract_ssid,seuilSignal,seuilDistance);
                    System.out.println("Latitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double lat1 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double long1 = Double.parseDouble(reponse1.nextLine());

                    GPS coord1 = new GPS(lat1,long1);

                    System.out.println("Latitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double lat2 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double long2 = Double.parseDouble(reponse1.nextLine());

                    GPS coord2 = new GPS(lat2,long2);

                    int indiceI = 0;
                    int depart = -1;
                    int arrive = -1 ;
                    for (Trace i: dataGraph.getListeSommet()) {
                        if (i.coord.equals(coord1)) {
                            depart = indiceI;
                        }
                        else if (i.coord.equals(coord2)) {
                            arrive = indiceI;
                        }
                        indiceI+=1;
                    }
                    int[] dijkstraRes = dataGraph.dijkstra(depart);
                    ArrayList<Integer> courtChemin = dataGraph.afficheChemin(dijkstraRes,depart,arrive);
                    Traces cheminCourt = new LinkedListTraces();

                    for (int i=0; i<courtChemin.size();i++) {
                        Integer indice = courtChemin.get(i);
                        cheminCourt.ajouter(dataGraph.getListeSommet().get(indice));
                    }
                    LinkedList<Traces> resultatInter = new LinkedList<>();
                    resultatInter.add(cheminCourt);
                    resultatInter.add(extract_ssid);
                    listeResultatsMultiGraphe.add(resultatInter);
                    listeTitreMultiGraphe.add(titre + " " + coord1.toString() + " " + coord2.toString());


                }
                else if(repo == 2) {


                    System.out.println("Seuil de distance?");
                    Scanner reponse3 = new Scanner(System.in);
                    String reponse31 = reponse3.nextLine();
                    System.out.println("Seuil de signal?");
                    Scanner reponse4 = new Scanner(System.in);
                    String reponse41 = reponse4.nextLine();

                    int seuilSignal = Integer.parseInt(reponse41);
                    double seuilDistance = Double.parseDouble(reponse31);
                    LinkedList<Traces> partialExtract = trace_capture.extractAll("");
                    Traces extract_ssid = new LinkedListTraces();
                    for (Traces i : partialExtract) {
                        for (Trace j : i) {
                            extract_ssid.ajouter(j);
                        }
                    }
                    System.out.println(extract_ssid.toString());
                    DataGraphMat dataGraph = new DataGraphMat(extract_ssid,seuilSignal,seuilDistance);


                    System.out.println("Latitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double lat1 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point1?");
                    reponse1 = new Scanner(System.in);
                    Double long1 = Double.parseDouble(reponse1.nextLine());

                    GPS coord1 = new GPS(lat1,long1);

                    System.out.println("Latitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double lat2 = Double.parseDouble(reponse1.nextLine());
                    System.out.println("Longitude du point2?");
                    reponse1 = new Scanner(System.in);
                    Double long2 = Double.parseDouble(reponse1.nextLine());

                    GPS coord2 = new GPS(lat2,long2);

                    int indiceI = 0;
                    int depart = -1;
                    int arrive = -1 ;
                    for (Trace i: dataGraph.getListeSommet()) {
                        if (i.coord.equals(coord1)) {
                            depart = indiceI;
                        }
                        else if (i.coord.equals(coord2)) {
                            arrive = indiceI;
                        }
                        indiceI+=1;
                    }
                    int[] dijkstraRes = dataGraph.dijkstra(depart);
                    ArrayList<Integer> courtChemin = dataGraph.afficheChemin(dijkstraRes,depart,arrive);
                    Traces cheminCourt = new LinkedListTraces();

                    for (int i=0; i<courtChemin.size();i++) {
                        Integer indice = courtChemin.get(i);
                        cheminCourt.ajouter(dataGraph.getListeSommet().get(indice));
                    }
                    LinkedList<Traces> resultatInter = new LinkedList<>();
                    resultatInter.add(cheminCourt);
                    resultatInter.add(extract_ssid);
                    listeResultatsMultiGraphe.add(resultatInter);
                    listeTitreMultiGraphe.add("all" + " " + coord1.toString() + " " + coord2.toString());

                }
            }
            System.out.println("Voulez-vous encore effectuer une action particulier? (1 pour quitter, 0 pour continuer)");
            Scanner reponse2 = new Scanner(System.in);
            String quitstr = reponse2.nextLine();
            quit = Integer.parseInt(quitstr);
        }
        int indiceJ = 0;
        for (Traces i : listeResultats) {
            stage = new Stage();
            createGraph(i,stage,listeTitre.get(indiceJ),axis);
            stage.show();
            indiceJ+=1;
        }
        indiceJ = 0;
        for (LinkedList i : listeResultatsMultiGraphe) {
            stage = new Stage();
            createMultiGraphe(i,stage,listeTitreMultiGraphe.get(indiceJ),axis);
            stage.show();
            indiceJ+=1;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void createGraph(Traces traces, Stage stage, String titre, int axis) {
        stage.setTitle(titre);
        NumberAxis xAxis;
        NumberAxis yAxis;
        if (axis == 0) {
            xAxis = new NumberAxis(6.154,6.157,0.001);
            yAxis = new NumberAxis(48.668,48.670,0.001);
        }
        else {
            xAxis = new NumberAxis(6.153, 6.170, 0.0001);
            yAxis = new NumberAxis(48.669, 48.680, 0.00001);
        }
        ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle(titre);

        XYChart.Series valTraces = new XYChart.Series();
        for (Trace i: traces) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude()*1,i.coord.getLatitude()*1));
        }
        sc.getData().addAll(valTraces);
        Scene scene = new Scene(sc,500,400);
        scene.getStylesheets().add("./Chart.css");
        stage.setScene(scene);
    }

    public static void createMultiGraphe(LinkedList<Traces> traces,Stage stage,String titre,int axis) {
        stage.setTitle(titre);
        NumberAxis xAxis;
        NumberAxis yAxis;
        if (axis == 0) {
            xAxis = new NumberAxis(6.154,6.157,0.001);
            yAxis = new NumberAxis(48.668,48.670,0.001);
        }
        else {
            xAxis = new NumberAxis(6.153, 6.170, 0.0001);
            yAxis = new NumberAxis(48.669, 48.680, 0.00001);
        }
        ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Longitude");
        yAxis.setLabel("Latitude");
        sc.setTitle(titre);

        XYChart.Series valTraces = new XYChart.Series();
        XYChart.Series valTraces2 = new XYChart.Series();
        for (Trace i: traces.get(1)) {
            valTraces.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));
        }
        for (Trace i: traces.get(0)) {
            valTraces2.getData().add(new XYChart.Data(i.coord.getLongitude(),i.coord.getLatitude()));
        }

        sc.getData().addAll(valTraces,valTraces2);
        Scene scene = new Scene(sc,500,400);
        scene.getStylesheets().add("./Chart.css");
        stage.setScene(scene);
    }

}
