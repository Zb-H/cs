import java.io.*;
import java.util.*;

public class Q2{
	//This method inputs the name of the file and returns the number of words in the file. 
	public static int countWords(String file) throws FileNotFoundException{
		int words = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNext()){
			fr.next();
			words++;
		}
		fr.close();
		return words;
	}

	public static void main(String[] args) throws FileNotFoundException{
		int wordCount = countWords("input.txt");
    		System.out.println(wordCount);
	}
}

