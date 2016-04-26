package senCity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Quentin TARDIVON
 * Contient une liste de Trace
 */
public class ArrayListTraces extends Traces{

    public ArrayListTraces() {
        initialiser();
    }

    @Override
    public Collection<Trace> initialiser() {
        listeTrace = new ArrayList<>();
        return listeTrace;
    }

    /**
     * Est appelée ici sans arguments
     * @param args
     */
    public static void main(String[] args) {
        /*ArrayListTraces arrayListTraces = new ArrayListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        arrayListTraces.ajouter(trace1);
        arrayListTraces.ajouter(trace2);
        arrayListTraces.ajouter(trace3);
        System.out.println(arrayListTraces.toString());
        */
        ArrayListTraces trace_capture = new ArrayListTraces();
        double time = System.currentTimeMillis();
        try {
            trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
            long freem = Runtime.getRuntime().freeMemory();
            //System.out.println(trace_capture.toString());

            try {
                trace_capture.save("test_sauvegarde");
                System.out.println("Save!");
                System.out.println(freem);
                time = System.currentTimeMillis() - time;
                System.out.println("Time=" + time);
                for (Trace t: trace_capture) { System.out.println(t) ;}
            }
            catch (IOException exception) {
                System.out.println("Impossible de créer le fichier ou trop de donnée perdue");
            }
        }
        catch(IOException exception) {
            System.out.println("Fichier introuvable");
        }
    }
}

