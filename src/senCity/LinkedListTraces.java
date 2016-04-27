package senCity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Quentin TARDIVON
 * Contient une liste de Trace
 */
public class LinkedListTraces extends Traces{


    public LinkedListTraces() {
        initialiser();
    }

    @Override
    public Collection<Trace> initialiser() {
        listeTrace = new LinkedList<>();
        return listeTrace;
    }
}
