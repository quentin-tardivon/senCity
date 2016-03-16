package senCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Stats {
	
	public static String trouve(String name, String valeur, String fichier) {
		String StrtoFile = "./" + fichier;
		try {
			int toFound = 0;
			Scanner SLine = new Scanner (new FileReader(new File(StrtoFile)));
			Scanner SElement = new Scanner(SLine.nextLine());
			SElement.useDelimiter(" ");
			SElement.next();
			SElement.useDelimiter(",");
			while(SElement.hasNext()) {
				String test = SElement.next().toLowerCase().trim();
				System.out.println(test);
				if (test.equals(name)) {
					break;
				}
				else {
					toFound +=1;
				}	
			}
			
			System.out.println(toFound);
			while(SLine.hasNextLine()) {
				String nextLine = SLine.nextLine();
				SElement = new Scanner(nextLine);
				for (int i = 0; i < toFound-1; i++) {
					SElement.next();
				}
				if (SElement.next() == valeur) {
					return nextLine;
				}
			}
			
			return "Not Found";
				
		}
		catch(FileNotFoundException excep) {
			return ("error");
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(trouve(args[0], args[1], args[2]));
	}
}
