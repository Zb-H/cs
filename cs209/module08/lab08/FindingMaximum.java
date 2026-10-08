package lab08;
import java.util.*;
/* author@ Zhaobi Huang
 * CS209
 * Feb26
 * This program reads positive integers from the user until the user enters a negative number
 * identifies and returns the maximum value entered by the user.
 */

public class FindingMaximum{
    static boolean exit = false;
    static int a[] = new int[10000];
    static String reader;
    static String yn;
    //number = each valid element is going to register in array
    static int number;
    //order = how many valid number registed in array
    static int order;
    //max = max number
    static int max;
    //index = the index number of max number in array
    static int index;

    public static void prompt(int i){
        if(i==1){
            System.out.print("Enter a positive number(or 'stop'/a negetive number to exit input): ");
        }else if(i==2){
            System.out.println("No input!");
        }else if(i==3){
            System.out.println("A negetive integer detected!");
        }else if(i==4){
            System.out.println("Invalid input!");
        }
    }
    //find the largest number in array and the index number of it
    public static void compare(){
        for(int i=0; i<order;i++){
            if (a[i]> max){
                max = a[i];
            }
        }
        for(int i=0;i<order;i++){
            if(a[i]==max){
                index = i;
            }
        }
        System.out.println("Maximum value: " + max + " on No." + (index+1) +" valid input.");
    }
    //reset array and max number etc.
    public static void clear(){
		for(int i=0;i<a.length;i++){
			a[i]=0;
		}
        order =0;
		max = 0;
        index =0;
	}

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        //order = 0;
        //max = 0;

        while(!exit){
            
            while(true){
                prompt(1);
                reader = sc.nextLine().trim();
                    //make sure input is valid
                    if(reader.isEmpty()){
                        prompt(2);
                        continue;
                    }
                    if(reader.equalsIgnoreCase("stop")){
                        break;
                    }
                    try{
                        number = Integer.parseInt(reader);
                        //input is negetive, stop collecting numbers
                        if(number<0){
                            prompt(3);
                            break;
                        //prevent input from exceeding array limit
                        }else if (order>=a.length){
                            prompt(5);
                            break;
                        }else{
                            a[order] = number;
                            order++;
                        }
                    }catch(Exception e){
                        prompt(4);
                    }   
            }
			//for(int i=0;i<order;i++){
			//	System.out.printf("%d ", a[i]);
			//}
			//System.out.println();
            compare();
            //ask usr to have another run or not to
            while(true){
                System.out.print("Continue(Y/n): ");
                yn = sc.nextLine().trim();
                if(yn.equalsIgnoreCase("y") || yn.isEmpty()){
                    clear();
                    exit = false;
                    break;
                }else if(yn.equalsIgnoreCase("n")){
                    exit = true;
                    break;
                }
            }
        }
        sc.close();
    }
}
