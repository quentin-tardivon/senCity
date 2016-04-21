package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Quentin TARDIVON
 * Contient une liste de Trace
 */
public class LinkedListTraces extends Traces{


    public LinkedListTraces() {
        initialiser();
    }

    @Override
    public Collection<Trace> initialiser() {
        listeTrace = new LinkedList<>();
        return listeTrace;
    }

    /**
     * Est appelée ici sans arguments
     * @param args
     */
    public static void main(String[] args) {
        /*LinkedListTraces linkedListTraces = new LinkedListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        linkedListTraces.ajouter(trace1);
        linkedListTraces.ajouter(trace2);
        linkedListTraces.ajouter(trace3);
        System.out.println(linkedListTraces.toString());
        */
        LinkedListTraces trace_capture = new LinkedListTraces();
        double time = System.currentTimeMillis();
        try {
            trace_capture.load("capture_wifi.csv","capture_gps.csv",20.0);
            long freem = Runtime.getRuntime().freeMemory();
            System.out.println(trace_capture.toString());

            try {
                trace_capture.save("test_sauvegarde");
                System.out.println("Save!");
                System.out.println(freem);
                time = System.currentTimeMillis() - time;
                System.out.println("Time=" + time);
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
