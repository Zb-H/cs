import java.util.*;
/* Zhaobi Huang
 * CS209
 * March 18th 2026
 * this program ask the user to input a string. 
 * If the length of the string is even and 
 * the number of uppercase in the string is equal to the number of lowercase in the string, 
 * stop the program with "bye!".
 * Otherwise ask the user to input a string again. 
 */

public class Q18{
	//this method tells whether the number of upper case and lower case of usr input is equal
	public static int upperCase =0;
	public static int lowerCase =0;
	public static boolean countCase(String str){
		// this loop scans every char of the input and store each of them into a char
		for(int i=0;i<str.length();i++){	
			char word = str.charAt(i);
			//this if statement counts lower and upper case
			if(word<=90 && word>=65){
				upperCase++;
			}else if(word>=97 && word<=122){
				lowerCase++;
			}
		}
		//this if statment returns true if the number of upper and lower cases are equal
		//false if they are not
		if(upperCase==lowerCase){
			return true;
		}else{
			return false;
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		//this while loop ask usr for input if the input requirment is not met
		while(true){
			System.out.print("input a string: ");
			//stores the input
			String str = sc.nextLine().trim();
			//pass str to method case() and tell if two requiremnets are both satisfied
			if((str.length())%2==1 && countCase(str)){
				// if they are, program end
				System.out.println("bye");
				break;
			}else{
				//if they are not loop back to the beginning
				continue;
			}
		}
			//testing
			//System.out.printf("%s\n%s",lowerCase,upperCase);
	
		sc.close();
	}
}

