package sencity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by quentin on 21/03/16.
 */
public abstract class Traces extends AbstractTraces implements Iterable<Trace> {

    Collection<Trace> listeTrace;
    int taille = 0;

    /**
     * Ajoute une trace à la liste
     * @param trace la trace à ajouter
     */
    public void ajouter(Trace trace) {
        this.taille +=1;
        this.listeTrace.add(trace);
    }

    public void retirer(Trace trace) {
        for (Trace i: listeTrace) {
            if (trace.coord.equals(i.coord) && trace.signal == i.signal && trace.ssid.equals(i.ssid)) {
                listeTrace.remove(i);
                break;
            }
        }
        this.taille = taille -1;
    }

    /**
     *
     * @return la taille de la liste de trace
     */
    public int taille() {
        return taille;
    }


    public String toString() {
        String result = "";
        for (Trace t: listeTrace) { result = result + "\n" + t.toString();}
        return result;
    }


    public abstract Collection<Trace> initialiser();


    @Override
    public Iterator<Trace> iterator() {
        return listeTrace.iterator();
    }

    public abstract Traces extract(String ssid);


}
