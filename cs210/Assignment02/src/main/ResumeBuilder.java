package main;

import java.io.*;
import java.util.*;
import util.*;

public class ResumeBuilder{
	public static void main(String[] args) throws IOException{

		Profile p = new Profile();
		Scanner sc = new Scanner(System.in);
		String output;

		File dir = new File("AppData");
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(dir,"Resume.txt");
		FileWriter wtr = new FileWriter(file,false);


		while(true){
			System.out.printf("Enter your firstname: ");
			String fn = sc.nextLine().trim();
			System.out.printf("Enter your lastname: ");
			String ln = sc.nextLine().trim();

			p.setName(fn,ln);
			if(p.getName() == null){
				continue;
			}

			output = String.format("%-10s| %-30s", "name: ", p.getName());
			System.out.printf("%s\n", output);
			wtr.write(output);

			break;
		}
		while(true){
			System.out.printf("Enter your number: ");
			long number = sc.nextLong();
			sc.nextLine();
			p.setNumber(number);
			if(p.getNumber() == 0){
				continue;
			}

			output = String.format("%-10s| %-30s", "Number: ", p.getNumber());
			System.out.printf("%s\n", output);
			wtr.write(output);

			break;
		}
        while(true){
            System.out.printf("Enter your school: ");
            String school = sc.nextLine().trim();

            p.setSchool(school);
            if(p.getSchool() == null){
                continue;
            }
            output = String.format("%-10s| %-30s", "School: ", p.getSchool());
            System.out.printf("%s\n", output);
            wtr.write(output);

            break;
        }

		wtr.close();
		sc.close();

	}
}
