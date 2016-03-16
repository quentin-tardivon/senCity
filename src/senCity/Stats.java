package senCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Stats {
	
	public static String trouve(String name, String valeur, String fichier) {
		String StrtoFile = "./" + fichier;
		String normalizeName = name.toLowerCase().replace(" ","");
		String normalizeValeur = valeur.toLowerCase().replace(" ","");
		String result = "";
		try {
			int toFound = 0;
			Scanner SLine = new Scanner (new FileReader(new File(StrtoFile)));
			Scanner SElement = new Scanner(SLine.nextLine());
			SElement.useDelimiter(" ");
			SElement.next();
			SElement.useDelimiter(",");
			while(SElement.hasNext()) {
				String test = SElement.next().toLowerCase().trim();
				if (test.equals(normalizeName)) {
					break;
				}
				else {
					toFound +=1;
				}	
			}

			while(SLine.hasNextLine()) {
				String nextLine = SLine.nextLine();
				SElement = new Scanner(nextLine);
				SElement.useDelimiter(",");
				for (int i = 0; i < toFound; i++) {
					SElement.next();
				}
				String element = SElement.next().toLowerCase().replace(" ","");
				if (element.equals(normalizeValeur)) {
					result = result + "\n" + nextLine;
				}
			}
			
			return result;
				
		}
		catch(FileNotFoundException excep) {
			return ("error");
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(trouve(args[0], args[1], args[2]));
	}
}
