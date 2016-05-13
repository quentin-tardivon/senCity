package sencity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Test {
	

	public static void main(String[] args) {
		testExtractTreeTraces();
		//testExtractArray();
		//testExtractLinkedList();
		//testExtractHashMapTraces();
		//testLinkedList();
		//testHashMapTraces();
	}

	public void testTrace() {
		GPS coord = new GPS(32.0,23.4);
		Trace testTrace = new Trace("123456", "eduroam", 23, coord);
		System.out.println(testTrace.toString());
	}

	public void testInitiale() {
		String StrtoFile = ("./ressources/capture_wifi.csv");
		try {
			Scanner SLine = new Scanner (new FileReader(new File(StrtoFile)));


			String Line = "";
			String Element = "";

			for (int i = 0; i < 2 ;i++) {
				if (SLine.hasNextLine()) {
					Line = SLine.nextLine();

				}
				else {
					System.out.println("Attention pas de ligne suivante");
				}
			}

			Scanner SElement = new Scanner(SLine.nextLine());
			SElement.useDelimiter(",");

			for (int i = 0; i<9;i++) {
				if (SElement.hasNext()) {
					System.out.println(SElement.next());
				}
				else {
					System.out.println("Pas de suivant");
				}
			}
			System.out.println(Line);
			SLine.close();
			SElement.close();
		}
		catch(FileNotFoundException exeception1) {
			System.out.println("Attention FilNotFound");
		}
		finally {

		}
	}

	public void testArrayList() {
		/*ArrayListTraces arrayListTraces = new ArrayListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        arrayListTraces.ajouter(trace1);
        arrayListTraces.ajouter(trace2);
        arrayListTraces.ajouter(trace3);
        System.out.println(arrayListTraces.toString());
        */
		ArrayListTraces trace_capture = new ArrayListTraces();
		double time = System.currentTimeMillis();
		try {
			trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
			long freem = Runtime.getRuntime().freeMemory();
			//System.out.println(trace_capture.toString());

			try {
				trace_capture.save("output_file");
				System.out.println("Save!");
				System.out.println(freem);
				time = System.currentTimeMillis() - time;
				System.out.println("Time=" + time);
				//for (Trace t: trace_capture) { System.out.println(t) ;}
			}
			catch (IOException exception) {
				System.out.println("Impossible de créer le fichier ou trop de donnée perdue");
			}
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
	}

	public static void testLinkedList() {
		/*LinkedListTraces linkedListTraces = new LinkedListTraces();
        Trace trace1 = new Trace("123456", "eduroam", 23);
        Trace trace2 = new Trace("123456", "lolilo", 23);
        Trace trace3 = new Trace("123456", "yolooo", 23);
        linkedListTraces.ajouter(trace1);
        linkedListTraces.ajouter(trace2);
        linkedListTraces.ajouter(trace3);
        System.out.println(linkedListTraces.toString());
        */
		LinkedListTraces trace_capture = new LinkedListTraces();
		double time = System.currentTimeMillis();
		try {
			trace_capture.load("capture_wifi_2.csv","capture_gps_2.csv",20.0);
			long freem = Runtime.getRuntime().freeMemory();
			//System.out.println(trace_capture.toString());

			try {
				trace_capture.save("test_sauvegarde");
				System.out.println("Save!");
				System.out.println(freem);
				time = System.currentTimeMillis() - time;
				System.out.println("Time=" + time);
			}
			catch (IOException exception) {
				System.out.println("Impossible de créer le fichier");
			}
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
	}

	public static void testGPS() {
		double longi = 12.3;
		double lati = 34.3;
		GPS coord = new GPS(longi,lati);
		System.out.println(coord.getLatitude());
		System.out.println(coord.toString());
		coord.setLatitude(23.0);
		coord.setLongitude(12.45);
		System.out.println(coord.toString());
	}

	public static void testExtractArray() {
		System.out.println("Extract ArrayList");
		ArrayListTraces trace_capture = new ArrayListTraces();
		try {
			double time = System.currentTimeMillis();
			trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
			System.out.println(System.currentTimeMillis() - time);
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
		double time = System.currentTimeMillis();
		Traces extract_ssid = trace_capture.extract("BDE");
		//System.out.println(extract_ssid.toString());
		System.out.println(System.currentTimeMillis() - time);
	}

	public static void testExtractLinkedList() {
		System.out.println("Extract LinkedList");
		LinkedListTraces trace_capture = new LinkedListTraces();
		try {
			double time = System.currentTimeMillis();
			trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
			System.out.println(System.currentTimeMillis() - time);
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
		double time = System.currentTimeMillis();
		Traces extract_ssid = trace_capture.extract("BDE");
		//System.out.println(extract_ssid.toString());
		System.out.println(System.currentTimeMillis() - time);
	}

	public static void testHashMapTraces() {
		HashMapTraces trace_capture = new HashMapTraces();
		double time = System.currentTimeMillis();
		try {
			trace_capture.load("capture_wifi_2.csv","capture_gps_2.csv",20.0);
			long freem = Runtime.getRuntime().freeMemory();
			//System.out.println(trace_capture.toString());

			try {
				trace_capture.save("test_sauvegarde");
				System.out.println("Save!");
				System.out.println(freem);
				time = System.currentTimeMillis() - time;
				System.out.println("Time=" + time);
			}
			catch (IOException exception) {
				System.out.println("Impossible de créer le fichier");
			}
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
	}

	public static void testExtractHashMapTraces() {
		System.out.println("Extract HashMap");
		HashMapTraces trace_capture = new HashMapTraces();
		try {
			double time = System.currentTimeMillis();
			trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
			System.out.println(System.currentTimeMillis() - time);
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
		double time = System.currentTimeMillis();
		Traces extract_ssid = trace_capture.extract("BDE");
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(extract_ssid.toString());

	}

	public static void testExtractTreeTraces() {
		System.out.println("Extract TreeTraces");
		TreeTraces trace_capture = new TreeTraces();
		try {
			double time = System.currentTimeMillis();
			trace_capture.load("capture_wifi.csv", "capture_gps.csv",20.0);
			System.out.println(System.currentTimeMillis() - time);
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
		double time = System.currentTimeMillis();
		Traces extract_ssid = trace_capture.extract("BDE");
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(extract_ssid.toString());

	}

}
