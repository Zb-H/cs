import java.io.*;
import java.util.*;

public class Q3{
	//This method inputs the name of the file and a words, and returns the number of character without spaces in the file.
	public static int countCharacterWithoutSpaces(String file) throws FileNotFoundException{
		int characters = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNext()){
			String token = fr.next();
			//test
			//System.out.printf("%s",token);
			characters += token.length();
		}
		fr.close();
		return characters;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		int characterCountNoSpaces = countCharacterWithoutSpaces("input.txt");
       		System.out.println(characterCountNoSpaces);
	}
}
