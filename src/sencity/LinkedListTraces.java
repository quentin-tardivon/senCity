package sencity;

import java.util.Collection;
import java.util.LinkedList;

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

    @Override
    public Traces extract(String ssid) {
        Traces result = new LinkedListTraces();
        for (Trace t: this.listeTrace) {
            if (t.ssid.equals(ssid)) {
                result.ajouter(t);
            }
        }
        return result;
    }
}
