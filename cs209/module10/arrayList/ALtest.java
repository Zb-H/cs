import java.io.*;
import java.util.*;

public class ALtest{

	static File file = new File("input.txt");
	static File output = new File("output.txt");
	static String str;

	public static void main(String[] args) throws FileNotFoundException{
		Scanner fr = new Scanner(file);
		ArrayList<String> txt = new ArrayList<String>();
		while(fr.hasNextLine()){
			txt.add(fr.nextLine());
		}
		PrintStream ps = new PrintStream(output);
		for(String s: txt){
			ps.printf("%s ", s);
		}
	}
}

