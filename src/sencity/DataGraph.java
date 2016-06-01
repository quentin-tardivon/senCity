package sencity;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * Created by quentin on 5/18/16.
 */
public class DataGraph {

    private Collection<Trace> listeSommet = new LinkedList();
    private Collection<Arc> listeArc = new LinkedList();

    private Double pourcentage;

    public DataGraph(Traces traces, int seuilSignal, Double seuilDistance) {
        initialiser(traces, seuilSignal, seuilDistance);
    }

    public Iterator<Arc> iterator() {
        return listeArc.iterator();
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

    public Collection<Trace> getListeSommet() {
        return listeSommet;
    }

    public Collection<Arc> getListeArc() {
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

    public Double surfaceReseau() {
        Trace sommetHG = new Trace("0","init",-10,new GPS(0.0,100.0));
        Trace sommetHD = new Trace("0","init",-10,new GPS(0.0,0.0));
        Trace sommetBG = new Trace("0","init",-10,new GPS(100.0,100.0));
        Trace sommetBD = new Trace("0","init",-10,new GPS(100.0,0.0));
        Double p,diag,b1,c1,b2,c2,surface;
        for (Arc i : listeArc) {
            if (i.getSommet1().coord.getLatitude()>sommetHG.coord.getLatitude() && i.getSommet1().coord.getLongitude()<sommetHG.coord.getLongitude()) {
                sommetHG=i.getSommet1();
            }
            if (i.getSommet1().coord.getLatitude()>sommetHD.coord.getLatitude() && i.getSommet1().coord.getLongitude()>sommetHD.coord.getLongitude()) {
                sommetHD=i.getSommet1();
            }
            if (i.getSommet1().coord.getLatitude()<sommetBG.coord.getLatitude() && i.getSommet1().coord.getLongitude()<sommetBG.coord.getLongitude()) {
                sommetBG=i.getSommet1();
            }
            if (i.getSommet1().coord.getLatitude()<sommetBD.coord.getLatitude() && i.getSommet1().coord.getLongitude()>sommetBD.coord.getLongitude()) {
                sommetBD=i.getSommet1();
            }
        }
        System.out.println(sommetHG);
        System.out.println(sommetHD);
        System.out.println(sommetBG);
        System.out.println(sommetBD);
        diag = distance(sommetBG.coord,sommetHD.coord);
        b1 = distance(sommetHG.coord,sommetHD.coord);
        c1 = distance(sommetBG.coord,sommetHG.coord);
        b2 = distance(sommetBG.coord,sommetBD.coord);
        c2 = distance(sommetHD.coord,sommetBD.coord);

        System.out.println(diag);
        System.out.println(b1);
        System.out.println(c1);
        System.out.println(b2);
        System.out.println(c2);


        p=(diag+b1+c1)/2;
        surface=Math.sqrt(p*(p-diag)*(p-b1)*(p-c1));
        p=(diag+b2+c2)/2;
        surface+=Math.sqrt(p*(p-diag)*(p-b2)*(p-c2));
        return surface;


    }

}
