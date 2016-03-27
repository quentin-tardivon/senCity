package senCity;

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
public abstract class Traces {

    Collection<Trace> listeTrace;

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
        Iterator itera = this.listeTrace.iterator();
        for(int i = 0; i<this.taille(); i++) {
            while (itera.hasNext()) {
                result = result + "\n" + itera.next().toString();
            }
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
            ts = ts.substring(0,10); //Récupération des 10 premiers caractères de ts
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

    public abstract Collection<Trace> initialiser();

}
