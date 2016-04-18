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
        ArrayListTraces arrayListTraces = new ArrayListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        arrayListTraces.ajouter(trace1);
        arrayListTraces.ajouter(trace2);
        arrayListTraces.ajouter(trace3);
        System.out.println(arrayListTraces.toString());

        ArrayListTraces trace_capture = new ArrayListTraces();
        try {
            trace_capture.load("capture_wifi.csv");
            long freem = Runtime.getRuntime().freeMemory();
            System.out.println(trace_capture.toString());

            try {
                trace_capture.save("test_sauvegarde");
                System.out.println("Save!");
                System.out.println(freem);
            }
            catch (IOException exception) {
                System.out.println("Impossible de créer le fichier");
            }
        }
        catch(IOException exception) {
            System.out.println("Fichier introuvable");
        }
    }
}
