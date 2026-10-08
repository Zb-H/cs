import java.util.*;
import java.io.*;

public class WordStatistics{
	
	static File input = new File("input.txt");
	static File output = new File("output.txt");

	static ArrayList<String> txt = new ArrayList<String>();
	static ArrayList<Integer> count = new ArrayList<Integer>();


	public static void fileReader(File input) throws FileNotFoundException{
		Scanner fr = new Scanner(input);

		while(fr.hasNextLine()){
			txt.add(fr.nextLine());
		}
		fr.close();
	}

	public static void main(String[] args) throws FileNotFoundException{
		fileReader(input);	
		for(String s : txt){
			System.out.printf("%s ", s);
		}
	}
}
