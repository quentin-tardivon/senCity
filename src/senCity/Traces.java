package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Quentin TARDIVON
 * Contient une liste de Trace
 */
public class Traces {
    LinkedList<Trace> listeTrace;

    public Traces() {
        this.listeTrace = new LinkedList<>();
    }

    /**
     * Ajoute une trace à la liste
     * @param trace la trace à ajouter
     */
    public void ajouter(Trace trace) {
        this.listeTrace.add(trace);
    }

    /**
     *
     * @return la taille de la liste de trace
     */
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

    /**
     * Charge un fichier et rempli une liste de trace avec les trace trouvées
     * @param nomFichier le nom du fichier à trouver
     * @throws IOException Si le fichier est introuvable (Ici, il doit se trouver à la racine
     */
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

    /**
     * Sauvegarde une liste de trace dans un fichier (toString les affiches dans le bon format, il n'y a qu'à enregistrer
     * @param nomFichier où enregistrer
     * @throws IOException si il est impossible d'écrire dans le fichier
     */
    public void save(String nomFichier) throws IOException {
        FileWriter writer = new FileWriter("./"+ nomFichier);

        writer.write(this.toString());

        writer.close();
    }

    /**
     * Est appelée ici sans arguments
     * @param args
     */
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
