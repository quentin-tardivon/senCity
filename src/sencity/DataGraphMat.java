package sencity;

import java.util.ArrayList;
import java.util.Arrays;


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

    /**
     *
     * @param traces
     * @param seuilSignal
     * @param seuilDistance
     */
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

    public ArrayList<Trace> getListeSommet() {
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

    /**
     * calcule et renvoie la distance en mètres entre 2 points GPS
     * formule : S = R * arccos ( sin(φA) * sin(φB) + cos(φA)*cos(φB)*cos(dλ) )
     * @param point1
     * @param point2
     * @return la distance en mètre
     */
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

    /**
     * Pour le sommet i donné, parcours toutes les colonnes et pour chaque voisin de i (matrice[i][j]==true) ajoute le
     * sommet correspondant à result
     * @param i numero de la ligne, c'est à dire numero du sommet de départ
     * @retur int[] result contenant la liste des voisins de i et -1 en dernier élément
     */
    public int[] voisins(int i) {
        int[] result = new int[listeSommet.size()];
        int indice = 0;
        for (int j = 0; j<listeSommet.size(); j++) {
            if (matriceArc[i][j]) {
                result[indice]=j;
                indice+=1;
            }
        }
        result[indice] = -1;
        return result;
    }

    /**
     * Algorithme de dijkstra, calcule tous les plus court chemins chemins à partir de depart.
     * @param depart sommet de départ de l'algorithme
     * @return int[] predecesseur  : liste des sommet atteignable depuis depart
     */
    public int[] dijkstra(int depart) {
        /*if (!this.existeSommet(depart)) {
            System.out.println("Le départ n'existe pas!");
            return null;
        }*/

        final Double[] chemin = new Double[this.getListeSommet().size()];
        final boolean[] marquage = new boolean[this.getListeSommet().size()];
        final int[] predecesseur = new int[(this.getListeSommet().size())];

        for (int i = 0; i < chemin.length; i++) {
            chemin[i] = Double.MAX_VALUE;

        }
        chemin[depart] = 0.0;

        for (int i = 0; i < chemin.length; i++) {
            final int suivant = minVertex(chemin,marquage);
            if (suivant == -1) {
                return predecesseur;
            }

            marquage[suivant] = true;

            final int[] n = this.voisins(suivant);
            int j = 0;
            while (n[j] != -1) {
                final int v = n[j];
                //System.out.println(n.length);
                final Double d = chemin[suivant] + this.distance(this.getListeSommet().get(suivant).coord, this.getListeSommet().get(v).coord);
                if (chemin[v] > d) {
                    chemin[v] = d;
                    predecesseur[v] = suivant;
                }
                j+=1;
            }
        }
        return predecesseur;
    }

    /**
     * renvoie le sommet correspondant a la plus petite distance
     * @param chemin chemin potentiel à évaluer
     * @param marquage liste de sommet deja parcouru
     * @return sommet correspondant au pus court chemin
     */
    private static int minVertex(Double[] chemin, boolean[] marquage) {
        Double x = Double.MAX_VALUE;
        int y = -1;
        for (int i = 0; i < chemin.length; i++) {
            if (!marquage[i] && chemin[i] < x) {
                y = i;
                x = chemin[i];
            }
        }
        return y;
    }

    /**
     * parcours la liste des prédécesseurs en partant du sommet d'arrivée et ajoute ses prédécesseurs tant qu'il
     * n'a pas atteint le sommet de départ
     * @param pred resultat de la fonction dijkstra
     * @param dep sommet de départ
     * @param arrivee sommet d'arrivée
     * @return affiche la liste des sommets correspondant au plus court chemin entre les sommet de départ de le sommet
     * d'arrivée
     */
    public static ArrayList<Integer> afficheChemin(int[] pred, int dep, int arrivee){
        final ArrayList<Integer> chemin = new ArrayList<Integer>();
        int x = arrivee;
        while (x != dep) {
            chemin.add(0, x);
            x = pred[x];
        }
        chemin.add(0,dep);
        System.out.println(chemin);
        return chemin;
    }


}
