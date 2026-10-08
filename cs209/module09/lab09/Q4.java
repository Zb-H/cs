import java.util.*;
import java.io.*;

public class Q4{
	//Create a method, called generateReport(), which inputs the name of the file, creates an output file, called output.txt, and write the word count report to the given file. You are expected to re-use the methods you developed in the previous questions.
	public static void generateReport(String file) throws FileNotFoundException{
		int words = 0;
		int lines = 0;
		int characters = 0;
		int spaces = 0;
		Scanner fr = new Scanner(new File(file));
		while(fr.hasNextLine()){
			String line = fr.nextLine();
			characters += line.length();
			for(int i=0;i<line.length();i++){
				if(line.charAt(i)==' '){
					spaces++;
				}
			}
			lines++;
			Scanner ls = new Scanner(line);
			while(ls.hasNext()){
				ls.next();
				words++;
			}
			ls.close();
		}
		fr.close();
		/* test
		System.out.printf("%s%d\n", "Line Count:",lines);
		System.out.printf("%s%d\n","Word Count:",words);
		System.out.printf("%s%d\n","Characters (no spaces)",characters-spaces);
		System.out.printf("%s%d\n","Characters (with spaces)",characters);
		*/
		PrintStream out = new PrintStream(new File("output.txt"));
		out.printf("%s%d\n", "Line Count:",lines);
		out.printf("%s%d\n","Word Count:",words);
		out.printf("%s%d\n","Characters (no spaces)",characters-spaces);
		out.printf("%s%d\n","Characters (with spaces)",characters);
		out.close();

	}

	public static void main(String[] args) throws FileNotFoundException{
		generateReport("input.txt");
	}
}

