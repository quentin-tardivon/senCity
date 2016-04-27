package senCity;

import java.io.IOException;
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
    public Collection<Trace> initialiser() {
        listeTrace = new ArrayList<>();
        return listeTrace;
    }
}

