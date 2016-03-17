package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

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
        String StrtoFile = "./" + nomFichier;
        Scanner SLine = new Scanner (new FileReader(new File(StrtoFile)));
        SLine.nextLine();
        while (SLine.hasNextLine()) {
            Scanner SElement = new Scanner(SLine.nextLine());
            SElement.useDelimiter(",");
            String ts = SElement.next();
            SElement.next();
            String ssid = SElement.next();
            SElement.next();
            SElement.next();
            Integer signal = 0;
            signal = signal.parseInt(SElement.next());
            this.ajouter(new Trace(ts, ssid, signal));
            SElement.close();
        }
        SLine.close();

    }

    public void save(String nomFichier) throws IOException {
        FileWriter writer = new FileWriter("./"+ nomFichier);

        writer.write(this.toString());

        writer.close();
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

        Traces trace_capture = new Traces();
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
