package sencity;

import java.util.ArrayList;

/**
 * Created by theo on 03/06/16.
 */
public class Dijkstra {

    public static int[] dijsktra(DataGraphMat G, Trace depart) {
        /*if (!this.existeSommet(depart)) {
            System.out.println("Le départ n'existe pas!");
            return null;
        }*/

        final Double[] chemin = new Double[G.getListeSommet().size()];
        final boolean[] marquage = new boolean[G.getListeSommet().size()];
        final int[] predecesseur = new int[(G.getListeSommet().size())];

        for (int i = 0; i < chemin.length; i++) {
            chemin[i] = Double.MAX_VALUE;
        }
        chemin[G.getListeSommet().indexOf(depart)] = 0.0;

        for (int i = 0; i < chemin.length; i++) {
            final int suivant = minVertex(chemin,marquage);
            marquage[suivant] = true;

            final int[] n = G.voisins(suivant);
            for (int j = 0; j < n.length; j++) {
                final int v = n[j]; //
                final Double d = chemin[suivant] + G.distance(G.getListeSommet().get(suivant).coord, G.getListeSommet().get(v).coord);
                if (chemin[v] > d) {
                    chemin[v] = d;
                    predecesseur[v] = suivant; //
                }
            }
        }
        return predecesseur;
    }


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

    public static void afficheChemin(DataGraphMat G, int[] pred, int dep, int arrivee) {
        final ArrayList chemin = new ArrayList();
        int x = arrivee;
        while (x != dep) {
            chemin.add(0, x);
            x = pred[x];
        }
        chemin.add(0,dep);
        System.out.println(chemin);
    }
}
