package senCity;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by quentin on 16/03/16.
 */
public class Traces {
    LinkedList<Trace> listeTrace;

    public Traces() {
        this.listeTrace = new LinkedList<>();
    }

    public void ajouter(Trace trace) {
        this.listeTrace.add(trace);
    }

    public int taille() {
        return this.listeTrace.size();
    }

    public String toString() {
        String result = "";
        for(int i = 0; i<this.taille(); i++) {
            result = result + "\n" + this.listeTrace.get(i).toString();
        }
        return result;
    }

    public void load(String nomFichier) throws IOException {
        //TO DO
    }

    public static void main(String[] args) {
        Traces traces = new Traces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        traces.ajouter(trace1);
        traces.ajouter(trace2);
        traces.ajouter(trace3);
        System.out.println(traces.toString());
    }
}
