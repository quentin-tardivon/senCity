package sencity;

import java.lang.reflect.Array;
import java.util.*;


/**
 * Created by quentin on 5/18/16.
 */
public class DataGraphMat {

    private ArrayList<Trace> listeSommet = new ArrayList();
    private boolean[][] matriceArc = new boolean[20000][20000];

    private Double pourcentage;

    public DataGraphMat(Traces traces, int seuilSignal, Double seuilDistance) {
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
                            this.ajouterArc(iIndice,jIndice);
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

    public boolean existeSommet(Trace sommet) {
        for (Trace i : getListeSommet()) {
            if (i.equals(sommet)) {
                return true;
            }
        }
        return false;
    }

    public void setListeSommet(ArrayList listeSommet) {
        this.listeSommet = listeSommet;
    }


    public void ajouterArc(int i, int j) {
        matriceArc[i][j] = true;
    }

    public void retirerArc(int i, int j) {
        matriceArc[i][j] = false;
    }

    public boolean existArc(int i, int j) {
        return matriceArc[i][j];
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

    public boolean[][] getMatriceArc() {
        return matriceArc;
    }

    @Override
    public String toString() {
        return "DataGraphMat{" +
                "listeSommet=" + listeSommet +
                ", matriceArc=" + Arrays.toString(matriceArc) +
                ", pourcentage=" + pourcentage +
                '}';
    }

    public Double surfaceReseau() {
        Trace sommetHG = new Trace("0","init",-10,new GPS(0.0,100.0));
        Trace sommetHD = new Trace("0","init",-10,new GPS(0.0,0.0));
        Trace sommetBG = new Trace("0","init",-10,new GPS(100.0,100.0));
        Trace sommetBD = new Trace("0","init",-10,new GPS(100.0,0.0));
        Double p,diag,b1,c1,b2,c2,surface;
        for (Trace i : listeSommet) {
            if (i.coord.getLatitude()>sommetHG.coord.getLatitude() && i.coord.getLongitude()<sommetHG.coord.getLongitude()) {
                sommetHG=i;
            }
            if (i.coord.getLatitude()>sommetHD.coord.getLatitude() && i.coord.getLongitude()>sommetHD.coord.getLongitude()) {
                sommetHD=i;
            }
            if (i.coord.getLatitude()<sommetBG.coord.getLatitude() && i.coord.getLongitude()<sommetBG.coord.getLongitude()) {
                sommetBG=i;
            }
            if (i.coord.getLatitude()<sommetBD.coord.getLatitude() && i.coord.getLongitude()>sommetBD.coord.getLongitude()) {
                sommetBD=i;
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

    public Traces dijkstra(Trace depart, Trace destination) {
        if (!this.existeSommet(depart)) {
            System.out.println("Le départ n'existe pas!");
            return null;
        }
        final Traces marquage = new ArrayListTraces();
        final Double[] dist = new Double[listeSommet.size()];
        final Traces[] chemin = new Traces[listeSommet.size()];
        for (int i=0; i <listeSommet.size(); i++) {
            dist[i] = 100000.0;
            chemin[i] = new ArrayListTraces();
        }
        dist[listeSommet.indexOf(depart)] = 0.0;
        chemin[listeSommet.indexOf(depart)].ajouter(depart);

        marquage.ajouter(depart);

        while (marquage.taille() != 0) {
            Trace x = enleverMin(dist,marquage);
            if (x.equals(destination)) {
                //return dist[listeSommet.indexOf(destination)];
                return chemin[listeSommet.indexOf(destination)];
            }
            for (int i=0;i<listeSommet.size(); i++) {
                if (existArc(listeSommet.indexOf(x), i)) {
                   this.miseAJour(listeSommet.get(i),x,marquage,dist,chemin);
                }
            }
        }
        return null;
    }

    public void miseAJour(Trace y, Trace x, Traces marquage, Double[] dist, Traces[] chemin) {
        if (dist[listeSommet.indexOf(y)] > dist[listeSommet.indexOf(x)] + distance(x.coord,y.coord)) {
            dist[listeSommet.indexOf(y)] = dist[listeSommet.indexOf(x)] + distance(x.coord,y.coord);
            chemin[listeSommet.indexOf(y)].ajouter(x);
            marquage.ajouter(y);
        }
    }

    public Trace enleverMin(Double[] dist, Traces marquage) {
        Double min = 0.0;
        int indiceMin = -1;
        for (int i=0; i<dist.length; i++) {
            if (dist[i] < min) {
                min = dist[i];
                indiceMin = i;
            }
        }
        return marquage.retirer(listeSommet.get(indiceMin));
    }
}
