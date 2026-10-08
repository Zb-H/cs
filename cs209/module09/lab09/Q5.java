import java.util.*;
import java.io.*;

public class Q5{
	//This method inputs the name of the file and returns the number of character including spaces in the file.
	public static int countCharacterWithSpaces(String file) throws FileNotFoundException{
		int characters = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNextLine()){
			String line = fr.nextLine();
			characters += line.length();
		}
		return characters;
	}
	public static void main(String[] args) throws FileNotFoundException{
		int characterCountWithSpaces = countCharacterWithSpaces("input.txt");
       		System.out.println(characterCountWithSpaces);
	}
}
