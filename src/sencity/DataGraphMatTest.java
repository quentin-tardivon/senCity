package sencity;

/**
 * Created by quentin on 6/4/16.
 */
public class DataGraphMatTest {

    @org.junit.Test
    public void initialiser() throws Exception {
        Traces tracesInit = new LinkedListTraces();
        Trace trace1 = new Trace("ts1", "freebox", 23, new GPS(48.669037,6.154897));
        Trace trace2 = new Trace("ts2", "freebox", 23, new GPS(48.669170,6.154442));
        Trace trace3 = new Trace("ts3", "freebox", 23, new GPS(48.669175,6.154513));
        Trace trace4 = new Trace("ts4", "freebox", 23, new GPS(48.669200,6.154908));
        tracesInit.ajouter(trace1);
        tracesInit.ajouter(trace2);
        tracesInit.ajouter(trace3);
        tracesInit.ajouter(trace4);

        DataGraphMat graphDeTest = new DataGraphMat(tracesInit,10,20.0);
        System.out.println(graphDeTest.getListeSommet().toString());
        for (int i = 0; i<graphDeTest.getListeSommet().size();i++) {
            System.out.println();
            for (int j = 0; j<graphDeTest.getListeSommet().size();j++) {
                System.out.print(graphDeTest.getMatriceArc()[i][j] + " ");
            }
        }

    }


    @org.junit.Test
    public void voisins() throws Exception {
        Traces tracesInit = new LinkedListTraces();
        Trace trace1 = new Trace("ts1", "freebox", 23, new GPS(48.669037,6.154897));
        Trace trace2 = new Trace("ts2", "freebox", 23, new GPS(48.669170,6.154442));
        Trace trace3 = new Trace("ts3", "freebox", 23, new GPS(48.669175,6.154513));
        Trace trace4 = new Trace("ts4", "freebox", 23, new GPS(48.669200,6.154908));
        tracesInit.ajouter(trace1);
        tracesInit.ajouter(trace2);
        tracesInit.ajouter(trace3);
        tracesInit.ajouter(trace4);

        DataGraphMat graphDeTest = new DataGraphMat(tracesInit,10,20.0);
        System.out.println(graphDeTest.getListeSommet().toString());
        for (int i = 0; i<graphDeTest.getListeSommet().size();i++) {
            System.out.println();
            for (int j = 0; j<graphDeTest.getListeSommet().size();j++) {
                System.out.print(graphDeTest.getMatriceArc()[i][j] + " ");
            }
        }

        int[] voisinsTest1 = graphDeTest.voisins(0);
        int[] voisinsTest2 = graphDeTest.voisins(1);
        for (int i = 0; i<voisinsTest1.length;i++) {
            System.out.print(voisinsTest2[i] + " ");
        }
    }

    @org.junit.Test
    public void dijkstra() throws Exception {
        int[] tabSommet = {1,2,3,4,5,6};
        boolean[][] tabChemin = {   {false,true,false,true,false,false},
                                    {false,false,true,true,true,false},
                                    {false,false,false,false,true,false},
                                    {false,false,false,false,true,true},
                                    {false,false,false,false,false,true},
                                    {false,false,false,false,false,false} };

        int[][] tabDistance = {
                {0,5,0,6,0,0},
                {0,0,8,1,2,0},
                {0,0,0,0,12,0},
                {0,0,0,0,4,5},
                {0,0,0,0,0,6},
                {0,0,0,0,0,0}
        };


    }

    @org.junit.Test
    public void afficheChemin() throws Exception {
        Traces tracesInit = new LinkedListTraces();
        Trace trace1 = new Trace("ts1", "freebox", 23, new GPS(48.669037,6.154897));
        Trace trace2 = new Trace("ts2", "freebox", 23, new GPS(48.669170,6.154442));
        Trace trace3 = new Trace("ts3", "freebox", 23, new GPS(48.669175,6.154513));
        Trace trace4 = new Trace("ts4", "freebox", 23, new GPS(48.669200,6.154908));
        tracesInit.ajouter(trace1);
        tracesInit.ajouter(trace2);
        tracesInit.ajouter(trace3);
        tracesInit.ajouter(trace4);

        DataGraphMat graphDeTest = new DataGraphMat(tracesInit,10,20.0);
        System.out.println(graphDeTest.getListeSommet().toString());
        for (int i = 0; i<graphDeTest.getListeSommet().size();i++) {
            System.out.println();
            for (int j = 0; j<graphDeTest.getListeSommet().size();j++) {
                System.out.print(graphDeTest.getMatriceArc()[i][j] + " ");
            }
        }

        int[] voisinsTest1 = graphDeTest.voisins(0);
        int[] voisinsTest2 = graphDeTest.voisins(1);
        for (int i = 0; i<voisinsTest1.length;i++) {
            System.out.print(voisinsTest2[i] + " ");
        }
        int[] pred = new int[4];
        pred[0] = 2;
        pred[1] = 3;
        pred[2] = 1;
        pred[3] = 1;
        graphDeTest.afficheChemin(pred,0,3);

    }

}