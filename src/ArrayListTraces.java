package senCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


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

