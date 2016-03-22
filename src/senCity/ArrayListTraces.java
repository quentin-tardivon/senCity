package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Quentin TARDIVON
 * Contient une liste de Trace
 */
public class ArrayListTraces extends Traces{
    ArrayList<Trace> listeTrace = new ArrayList<>();

    public ArrayListTraces() {
    }

    @Override
    public Collection<Trace> initialiser() {
        return this.listeTrace;
    }

    /**
     * Ajoute une trace à la liste
     * @param trace la trace à ajouter
     */
    public void ajouter(Trace trace) {
        this.listeTrace.add(trace);
    }


    @Override
    public String toString() {
        String result = "";
        for (int i =0; i<listeTrace.size();i++) {
            result = result + listeTrace.get(i).toString()+"\n";
        }
        return result;
    } //Tentative de récupérer l'ancien toString

    /**
     * Est appelée ici sans arguments
     * @param args
     */
    public static void main(String[] args) {
        LinkedListTraces linkedListTraces = new LinkedListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        linkedListTraces.ajouter(trace1);
        linkedListTraces.ajouter(trace2);
        linkedListTraces.ajouter(trace3);
        System.out.println(linkedListTraces.toString());

        LinkedListTraces trace_capture = new LinkedListTraces();
        try {
            trace_capture.load("capture_wifi.csv");
            System.out.println(trace_capture.toString());

            try {
                trace_capture.save("test_sauvegarde");
                System.out.println("Save!");
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

