package sencity;

/**
 * Created by theo on 03/06/16.
 */
public class Dijkstra {

    public static Trace[] dijkstra(DataGraphMat G, Trace depart) {
        /*if (!this.existeSommet(depart)) {
            System.out.println("Le départ n'existe pas!");
            return null;
        }*/

        final Double[] chemin = new Double[G.getListeSommet().size()];
        final boolean[] marquage = new boolean[G.getListeSommet().size()];
        final Trace[] predecesseur = new Trace[G.getListeSommet().size()];

        for (int i = 0; i < chemin.length; i++) {
            chemin[i] = Double.MAX_VALUE;
        }
        chemin[G.getListeSommet().indexOf(depart)] = 0.0;

        for (int i = 0; i < chemin.length; i++) {
            final Trace suivant = G.getListeSommet().get(minVertex(chemin, marquage));
            marquage[G.getListeSommet().indexOf(suivant)] = true;

            final Traces n = G.voisins(suivant);
            for (int j = 0; j < n.taille(); j++) {
                final Trace v = n.get(j); //
                final Double d = chemin[G.getListeSommet().indexOf(suivant)] + G.distance(suivant.coord, v.coord);
                if (chemin[G.getListeSommet().indexOf(v)] > d) {
                    chemin[G.getListeSommet().indexOf(v)] = d;
                    predecesseur[G.getListeSommet().indexOf(v)] = suivant; //
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

    public static void printPath(DataGraphMat G, Trace[] pred, Trace dep, Trace arrivee) {
        final ArrayListTraces chemin = new ArrayListTraces();
        Trace x = arrivee;
        while (!x.equals(dep))  {
            chemin.ajouter(x);
            x = pred[G.getListeSommet().indexOf(x)];
        }
        chemin.ajouter(dep);
        System.out.println(chemin.toString());
    }
}
