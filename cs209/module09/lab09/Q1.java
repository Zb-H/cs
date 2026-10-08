import java.util.*;
import java.io.*;

public class Q1{

	// This method inputs the name of the file and returns the number of lines in the file.
	public static int countLines(String file) throws FileNotFoundException {
		int lines = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNextLine()){
			fr.nextLine();
			lines++;
		}
		fr.close();
		return lines;
	}

	public static void main(String[] args) throws FileNotFoundException {
		int lineCount = countLines("input.txt");
    		System.out.println(lineCount);
	}
}
