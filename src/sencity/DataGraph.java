package sencity;

import java.util.LinkedList;

/**
 * Created by quentin on 5/18/16.
 */
public class DataGraph {

    private LinkedList listeSommet = new LinkedList();
    private LinkedList listeArc = new LinkedList();

    private Double pourcentage;

    public DataGraph(Traces traces, int seuilSignal, Double seuilDistance) {
        initialiser(traces, seuilSignal, seuilDistance);
    }

    public void initialiser(Traces traces, int seuilSignal, Double seuilDistance) {
        Double traceTotal = 0.0;
        Double traceGraphe = 0.0;
        int iIndice = 0;
        int jIndice = 0;
        for (Trace i : traces) {
            if (i.signal > seuilSignal) {
                this.ajouterSommet(i);
                traceGraphe += 1;
                for (Trace j : traces) {
                    if (jIndice > iIndice) {
                        Double distanceSom = distance(i.coord,j.coord);
                        if (distanceSom < seuilDistance) {
                            this.ajouterArc(new Arc(i,j,distanceSom));
                        }
                    }
                    jIndice +=1;
                }
            }
            traceTotal +=1;
            jIndice = 0;
            iIndice +=1;
        }
        pourcentage = traceGraphe/traceTotal * 100.0;
    }

    public LinkedList getListeSommet() {
        return listeSommet;
    }

    public LinkedList getListeArc() {
        return listeArc;
    }

    public void setListeSommet(LinkedList listeSommet) {
        this.listeSommet = listeSommet;
    }

    public void setListeArc(LinkedList listeArc) {
        this.listeArc = listeArc;
    }

    public void ajouterArc(Arc arc) {
        listeArc.add(arc);
    }

    public void ajouterSommet(Trace sommet) {
        listeSommet.add(sommet);
    }

    public Double distance(GPS point1, GPS point2) {
        return 6378137 * Math.acos(Math.sin(Math.toRadians(point1.getLatitude())) * Math.sin(Math.toRadians(point2.getLatitude())) + (Math.cos(Math.toRadians(point1.getLatitude())) * Math.cos(Math.toRadians(point2.getLatitude())) * Math.cos(Math.toRadians(point2.getLongitude()) - Math.toRadians(point1.getLongitude()))));
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    @Override
    public String toString() {
        return "DataGraph{" +
                "listeSommet=" + listeSommet +
                ", listeArc=" + listeArc +
                '}';
    }
}
