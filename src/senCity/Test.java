package senCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Test {
	

	public static void main(String[] args) {
	
	String StrtoFile = ("./capture_wifi.csv");
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
}
