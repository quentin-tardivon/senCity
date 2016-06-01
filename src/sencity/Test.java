package sencity;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Test {
	

	public static void main(String[] args) {
		testGraphData();
		//testPartialExtractTreeTraces();
		//testExtractTreeTraces();
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
			trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
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
			trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
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
			trace_capture.load("capture_wifi.csv","capture_gps.csv",20.0);
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
			trace_capture.load("capture_wifi_2.csv", "capture_gps_2.csv",20.0);
			System.out.println(System.currentTimeMillis() - time);
		}
		catch(IOException exception) {
			System.out.println("Fichier introuvable");
		}
		double time = System.currentTimeMillis();
		Traces extract_ssid = trace_capture.extract("BDE");
		System.out.println(System.currentTimeMillis() - time);
		//System.out.println(extract_ssid.toString());

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
		Double moydistance = 0.0;
		Double moysignal = 0.0;
		int compt = 0;
		int compt2 = 0;
		Double maxDistance = -200.0;
		Double minDistance = 3000000000000.0;
		int maxSignal = -200;
		int minSignal = 30000000;
		int iIndice = 0;
		int jIndice = 0;
		for (Trace i : extract_ssid) {
			moysignal += i.signal;
			compt2 += 1;
			if (maxSignal < i.signal) {
				maxSignal = i.signal;
			}
			else if (minSignal > i.signal) {
				minSignal = i.signal;
			}
			for (Trace j : extract_ssid) {
				if (jIndice > iIndice) {
					Double distanceact = distanceStatic(i.coord,j.coord) ;
					if (distanceact > maxDistance) {
						maxDistance = distanceact;
					}
					else if (distanceact < minDistance) {
						minDistance = distanceact;
					}
					System.out.println(distanceact);
					moydistance += distanceact;
					compt +=1;
				}
				jIndice +=1;
			}
			jIndice = 0;
			iIndice +=1;
		}
		System.out.println("Moyenne Distance:");
		System.out.println(moydistance/compt);
		System.out.println("Moyenne Signal:");
		System.out.println(moysignal/compt2);
		System.out.println("Maximum Distance:");
		System.out.println(maxDistance);
		System.out.println("Minimum Distance:");
		System.out.println(minDistance);
		System.out.println("Maximum Signal:");
		System.out.println(maxSignal);
		System.out.println("Minimum Signal:");
		System.out.println(minSignal);
		System.out.println(compt);
		System.out.println(compt2);
	}

	public static void testPartialExtractTreeTraces() {
		System.out.println("Extract PartialTreeTraces");
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
		LinkedList<Traces> extract_ssid = trace_capture.extractAll("free");
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(extract_ssid.toString());

	}


	static public Double distanceStatic(GPS point1, GPS point2) {
		return 6378137 * Math.acos(Math.sin(Math.toRadians(point1.getLatitude())) * Math.sin(Math.toRadians(point2.getLatitude())) + (Math.cos(Math.toRadians(point1.getLatitude())) * Math.cos(Math.toRadians(point2.getLatitude())) * Math.cos(Math.toRadians(point2.getLongitude()) - Math.toRadians(point1.getLongitude()))));
	}


	public static void testGraphData() {
		System.out.println("Extract GraphData");
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
		Traces extract_ssid = trace_capture.extract("eduroam");
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(extract_ssid.toString());
		DataGraphMat GraphDeTest = new DataGraphMat(extract_ssid,10,90.0);
		System.out.println(GraphDeTest.toString());
		System.out.println(GraphDeTest.getPourcentage());
		System.out.println(GraphDeTest.surfaceReseau());
		Stage stage = new Stage();
		new ScatterChartSample().graphTraces(stage,GraphDeTest.getListeSommet());

	}



}
