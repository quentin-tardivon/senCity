package sencity;


import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by quentin on 5/16/16.
 */
public class QuizzCar {


    public static void main(String[] args) {
        System.out.println("Veuillez entrer le nom du fichier wifi:");
        Scanner wifisc = new Scanner(System.in);
        String wifi = wifisc.nextLine();
        System.out.println("Veuillez entrer le nom du fichier GPS:");
        Scanner gpssc = new Scanner(System.in);
        String gps = gpssc.nextLine();
        System.out.println("Veuillez entrer la tolérance désirée:");
        Scanner tolsc = new Scanner(System.in);
        String tolstr = tolsc.nextLine();
        double tol = Double.parseDouble(tolstr);

        System.out.println("Extract TreeTraces");
        TreeTraces trace_capture = new TreeTraces();
        try {

            trace_capture.load(wifi, gps,tol);
            System.out.println("Extracted");
        }
        catch(IOException exception) {
            System.out.println("Fichier introuvable");
        }
        Integer quit = 0;
        while (quit !=1) {
            System.out.println("Quelle type de saisie voulez-vous utiliser? (0 pour complète, 1 pour prédictive)");
            Scanner reponse0 = new Scanner(System.in);
            String reponse = reponse0.nextLine();
            Integer rep = 0;
            rep = rep.parseInt(reponse);
            if (rep == 0) {
                System.out.println("Nom du ssid à extraire?");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                Traces extract_ssid = trace_capture.extract(reponse);
                System.out.println(extract_ssid.toString());
            }
            else if (rep == 1){
                System.out.println("Préfixe du ssid à extraire?");
                Scanner reponse1 = new Scanner(System.in);
                reponse = reponse1.nextLine();
                LinkedList<Traces> extract_ssid = trace_capture.extractAll(reponse);
                System.out.println(extract_ssid.toString());
            }
            System.out.println("Voulez-vous encore extraire un ssid particulier? (1 pour quitter, 0 pour extraire)");
            Scanner reponse2 = new Scanner(System.in);
            String quitstr = reponse2.nextLine();
            quit = Integer.parseInt(quitstr);

        }


    }

}
