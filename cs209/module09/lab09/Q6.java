import java.io.*;
import java.util.*;

public class Q6{
	//This method inputs the name of the file and a word, and returns the frequency of the given word in the file.
	//The method count all words that contain the given word, ignoring case sensitivity.
	public static int countWord(String file, String token) throws FileNotFoundException{
		int count = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNext()){
			String word = fr.next();
				if(word.toLowerCase().contains(token.toLowerCase())){
					count++;
				}
		}
		fr.close();
		return count;
	}

	public static void main(String[] args) throws FileNotFoundException{
		int wordCounter = countWord("input.txt","Influencer");
    		System.out.println(wordCounter);
	}
}
