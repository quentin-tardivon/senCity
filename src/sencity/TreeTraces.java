package sencity;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by quentin on 5/13/16.
 */
public class TreeTraces extends AbstractTraces {
    Node root;

    public TreeTraces() {
        root = null;
    }

    /**
     * Ajoute une Trace à un arbre TreeTraces. focusNode représente le noeud/ la Trace éudiée
     * A chaque étape, vérifie si la lettre étudiée du ssid correspond à celle du noeud , si oui avance dans l'arbre
     * (fils) et dans le ssid (lettre suivante) , sinon parcours les frères à sa recherche.
     * @param trace trace à ajouter à l'arbre
     */
    @Override
    public void ajouter(Trace trace) {
        Node focusNode = root;
        int i = 1;
        if (root == null) {     //racine null
            root = new Node(trace.ssid.charAt(0));
            focusNode = root;
        } else {    //racine non nulle, placement de focusNode au on endroit dans l'arbre
            if (focusNode.getLetter() != trace.ssid.charAt(0)) {
                while (focusNode.getBrother() != null && focusNode.getBrother().getLetter() != trace.ssid.charAt(0)) {
                    focusNode = focusNode.getBrother();
                }
                if (focusNode.getBrother() == null) {
                    focusNode.setBrother(new Node(trace.ssid.charAt(0)));
                }
                focusNode = focusNode.getBrother();
            }
        }
        while (i < trace.ssid.length()) {
            if (focusNode.getSon() != null) { //si focusNode a un fils
                if (focusNode.getSon().getLetter() == trace.ssid.charAt(i)) {   //si le fils de focusNode contient la
                    focusNode = focusNode.getSon();                             //lettre i
                    i += 1;
                }
                else {
                    focusNode = focusNode.getSon();
                    while (focusNode.getBrother() != null && focusNode.getBrother().getLetter() != trace.ssid.charAt(i)) {
                        focusNode = focusNode.getBrother();
                    }
                    if (focusNode.getBrother() == null) {
                        focusNode.setBrother(new Node(trace.ssid.charAt(i)));
                    }
                    focusNode = focusNode.getBrother();
                    i += 1;
                }
            }
            else { // si focusNode n'a pas de fils
                focusNode.setSon(new Node(trace.ssid.charAt(i)));
                focusNode = focusNode.getSon();
                i += 1;
            }
            if (focusNode.getListetraces() == null) {
                focusNode.setListetraces(new LinkedListTraces());
            }
        }
        focusNode.getListetraces().ajouter(trace);
    }

    /**
     * explore l'arbre à la recherche d'un ssid, pour chaque lettre, si focusNode contient la meme lettre on avance
     * dans l'arbre, sinon on se déplace dans les noeuds frères
     * @param ssid ssid recherché
     * @return renvoie la liste des Trace correspondant au ssid recherché
     * @throws IllegalArgumentException
     */
    @Override
    public Traces extract(String ssid) throws IllegalArgumentException { //TODO Rajouter cette exception si on ne trouve pas le ssid
        Node focusNode = root;
        int prof = ssid.length();
        int i = 0;
        while (i<prof-1) {
            if (focusNode.getLetter() == ssid.charAt(i)) {
                i +=1;
                focusNode = focusNode.getSon();
            }
            else {
                focusNode = focusNode.getBrother();
            }
        }
        return focusNode.getListetraces();
    }

    /**
     * recherche tous les ssid commencant par un préfixe donné, la boucle while s'arrete dès que le préfixe à été trouvé
     * puis c'est recursExtract qui effectue la recherche
     * @param prefixe préfixe des ssid recherchés
     * @return result, liste de Traces de tous les ssid trouvés
     */
    public LinkedList<Traces> extractAll(String prefixe) {
        Node focusNode = root;
        final LinkedList<Traces> result = new LinkedList<>();
        int prof = prefixe.length();
        int i = 0;
        while (i<prof-1) {
            if (focusNode.getLetter() == prefixe.charAt(i)) {
                i +=1;
                focusNode = focusNode.getSon();
            }
            else {
                focusNode = focusNode.getBrother();
            }
        }
        recursExtract(focusNode, result);
        return result;
    }

    /**
     * fonction récursive qui s'arrete dès que le noeud contient une Traces et l'ajoute à result. Sinon parcours
     * tous les noeuds frères et fils jusqu'à trouver une Traces
     * @param noeud noeud évalué par la fonction
     * @param result correspond au resultat de la fonction extractAll. Contient une liste de Traces
     */
    public void recursExtract(Node noeud,LinkedList<Traces> result) {
        if (noeud.getListetraces() != null) {
            result.add(noeud.getListetraces());
        }
        if (noeud.getBrother() != null) {
            recursExtract(noeud.getBrother(), result);
        }
        if (noeud.getSon() != null) {
            recursExtract(noeud.getSon(), result);
        }
    }





}
