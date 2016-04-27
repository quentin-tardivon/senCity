package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by quentin on 27/04/16.
 */

public abstract class MapTraces{
    Map<String, Traces> mapTraces;

    public abstract Map<String,Traces> initialiser();

    /**
     * Ajoute une trace à la liste
     * @param trace la trace à ajouter
     */
    public void ajouter(String ssid,Trace trace) { //TODO
        if (mapTraces.containsKey(ssid)) {
            this.mapTraces.get(ssid).ajouter();
        }
        else {
            this.mapTraces.put(ssid,);
        }
    }

    /**
     *
     * @return la taille de la liste de trace
     */
    public int taille() {
        return this.mapTraces.size();
    }


    public String toString() {
        String result = "";
        Iterator itera = this.mapTraces.iterator();
        for(int i = 0; i<this.taille(); i++) {
            while (itera.hasNext()) {
                result = result + "\n" + itera.next().toString();
            }
        }
        return result;
    }

    /**
     * Charge un fichier et rempli une liste de trace avec les trace trouvées
     * @param wifiFile le nom du fichier wifi à trouver
     * @param gpsFile le nom du fichier gps à trouver
     * @throws IOException Si le fichier est introuvable (Ici, il doit se trouver à la racine
     */
    public void load(String wifiFile, String gpsFile, Double seuil) throws IOException {
        double nbTotal = 0;
        double nbReal = 0;
        String StrtoWifiFile = "./" + wifiFile;
        String StrtoGPSFile = "./" + gpsFile;
        Scanner SLineWifi = new Scanner (new FileReader(new File(StrtoWifiFile)));
        SLineWifi.nextLine();


        while (SLineWifi.hasNextLine()) {

            Scanner SElementWifi = new Scanner(SLineWifi.nextLine());
            nbTotal+=1.0;
            SElementWifi.useDelimiter(",");
            String ts = SElementWifi.next();
            ts = ts.substring(0,10); //Récupération des 10 premiers caractères de ts
            SElementWifi.next();
            String ssid = SElementWifi.next();
            SElementWifi.next();
            SElementWifi.next();
            Integer signal = 0;
            signal = signal.parseInt(SElementWifi.next());

            if (!ssid.equals("<hidden>")) { //Optimisation possible en reprenant le parcours du fichier au lieu de recommencer
                Scanner SLineGPS = new Scanner (new FileReader(new File(StrtoGPSFile)));
                SLineGPS.nextLine();
                while (SLineGPS.hasNextLine()) {

                    Scanner SElementGPS = new Scanner(SLineGPS.nextLine());
                    SElementGPS.useDelimiter(",");
                    String tsGPS = SElementGPS.next();
                    tsGPS = tsGPS.substring(0,10); //Récupération des 10 premiers caractères de ts
                    if (tsGPS.equals(ts)) {
                        String lati = SElementGPS.next();
                        String longi = SElementGPS.next();
                        Double finalLati = Double.parseDouble(lati);
                        Double finalLongi = Double.parseDouble(longi);
                        GPS coord = new GPS(finalLati, finalLongi);
                        this.ajouter(ssid,new Trace(ts, ssid, signal,coord));
                        nbReal+=1.0;
                        break;
                    }
                    SElementGPS.close();
                }
                SLineGPS.close();

            }

            SElementWifi.close();
        }
        SLineWifi.close();
        if (((nbTotal - nbReal)/nbTotal) * 100 > seuil) {
            throw new IOException();
        }
    }

    /**
     * Sauvegarde une liste de trace dans un fichier (toString les affiche dans le bon format, il n'y a qu'à enregistrer
     * @param nomFichier où enregistrer
     * @throws IOException si il est impossible d'écrire dans le fichier
     */
    public void save(String nomFichier) throws IOException {
        FileWriter writer = new FileWriter("./"+ nomFichier);

        writer.write(this.toString());

        writer.close();
    }

    public abstract Traces extract(String ssid);
}
