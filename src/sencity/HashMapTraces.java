package sencity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quentin on 27/04/16.
 */
public class HashMapTraces extends MapTraces {

    @Override
    public Map initialiser() {
        Map mapTraces = new HashMap();
        return mapTraces;
    }

    @Override
    public Traces extract(String ssid) {
        Traces result = new LinkedListTraces();
        for (String s : mapTraces.keySet()) {
            result.ajouter(mapTraces.get(s));
        }
        return result;
    }
}
