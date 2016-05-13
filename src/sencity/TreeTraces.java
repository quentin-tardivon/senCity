package sencity;


/**
 * Created by quentin on 5/13/16.
 */
public class TreeTraces extends AbstractTraces {
    Node root;
    Node focusNode;

    public TreeTraces() {
        root = null;
    }


    @Override
    public void ajouter(Trace trace) { //TODO Regarder attentivement les NODE.SON
        focusNode = root;
        int i = 0;
        while (i < trace.ssid.length()) {
            if (focusNode == null) {
                focusNode = new Node(trace.ssid.charAt(i));
                i+=1;
                focusNode.son = new Node(trace.ssid.charAt(i));
                i+=1;
                focusNode = focusNode.son;
            }
            else if (focusNode.letter == trace.ssid.charAt(i)){
                if (focusNode.son == null) {
                    focusNode.son = new Node(trace.ssid.charAt(i));
                    i+=1;
                }
                focusNode = focusNode.son;
            }
            else {
                if (focusNode.brother == null) {
                    focusNode.brother = new Node(trace.ssid.charAt(i));
                    i+=1;
                }
                focusNode = focusNode.brother;
            }

        }
        if (focusNode.listetraces == null){
            focusNode.listetraces = new LinkedListTraces();
            focusNode.listetraces.ajouter(trace);
        }
        else {
            focusNode.listetraces.ajouter(trace);
        }
        this.toString(root);
    }

    @Override
    public Traces extract(String ssid) {
        Traces result;
        focusNode = root;
        int i = 0;
        while(i<ssid.length()) {
            System.out.println(i);
            if (ssid.charAt(i) == focusNode.letter) {
                focusNode = focusNode.son;
                i+=1;
            }
            else {
                focusNode = focusNode.brother;
            }

        }
        result = focusNode.listetraces;
        return result;
    }

    public String toString(Node noeud) {
        String result = "";
        if (noeud != null){
            result = result + this.toString(noeud.son);
            result = result + noeud.toString();
            result = result + this.toString(noeud.brother);
        }
        return result;
    }
}
