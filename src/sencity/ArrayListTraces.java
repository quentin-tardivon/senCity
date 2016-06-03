package sencity;

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
    public ArrayList<Trace> initialiser() {
        ArrayList listeTrace = new ArrayList<>();
        return listeTrace;
    }

    @Override
    public Traces extract(String ssid) {
        Traces result = new ArrayListTraces();
        for (Trace t: this.listeTrace) {
            if (t.ssid.equals(ssid)) {
                result.ajouter(t);
            }
        }
        return result;
    }
}

