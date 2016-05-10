package sencity;

import java.util.Map;


/**
 * Created by quentin on 27/04/16.
 */

public abstract class MapTraces extends AbstractTraces{
    Map<String, Traces> mapTraces;

    public abstract Map<String,Traces> initialiser();

    /**
     * Ajoute une trace à la liste
     * @param trace la trace à ajouter
     */
    public void ajouter(Trace trace) {
        String ssid = trace.ssid;
        Traces result = new LinkedListTraces();
        if (mapTraces.containsKey(ssid)) {
            result = this.mapTraces.get(ssid);
            result.ajouter(trace);
            this.mapTraces.replace(ssid,result);
        }
        else {
            result.ajouter(trace);
            this.mapTraces.put(ssid,result);
        }
    }

    /**
     *
     * @return la taille de la liste de trace
     */
    public int taille() {
        return this.mapTraces.size();
    }

    public abstract Traces extract(String ssid);
}
