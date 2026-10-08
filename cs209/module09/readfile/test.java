package readfile;
import java.io.*;
import java.util.*;
/*
 * This program test file reader
*/
public class test{

	static String[] s;
	static File file = new File("readfile/text.txt");
	static File output = new File("readfile/out.txt");

	public static void main(String[] args) throws FileNotFoundException {

		Scanner fr = new Scanner(file);
		PrintStream out = new PrintStream(output);
		
		int i = 0;

		while(fr.hasNextLine()){
			String line =fr.nextLine();
			s[i]= line;
			i++;
			out.printf("%s\n", line);
			System.out.printf("%s\n",line);
		}
		for(int i=0;i<s.length)

		fr.close();
		out.close();
		System.out.printf("%s%d%s\n", "file has ", i ," lines.");
		System.out.printf("%s%s\n","Address@",System.getProperty("user.dir"));
	}
}


