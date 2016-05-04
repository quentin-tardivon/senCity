package sencity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quentin on 27/04/16.
 */
public class HashMapTraces extends MapTraces {

    public HashMapTraces() {
        initialiser();
    }

    @Override
    public Map<String,Traces> initialiser() {
        mapTraces = new HashMap<String,Traces>();
        return mapTraces;
    }

    @Override
    public Traces extract(String ssid) {
       return mapTraces.get(ssid);
    }

    @Override
    public String toString() {
        String result = new String();
        for (String s : mapTraces.keySet()) {
            result = result + mapTraces.get(s).toString();
        }
        return result;
    }
}
