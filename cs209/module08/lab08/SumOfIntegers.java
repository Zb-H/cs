package lab08;
import java.util.*;
/* athor@Zhaobi Hunag
 * CS209
 * Feb26
 * This program, SumOfIntegers.java computes and 
 * returns the sum of all positive integers entered by the user.
 */
public class SumOfIntegers{

	static int sum, number, order;
	static int a[] = new int[10000];

	public static void prompt(int i, int sum){
		if(i==1){
			System.out.print("Enter a positive integer(enter 'stop' to stop input): ");
		}else if(i==2){
			System.out.println("Invalid input!");
		}else if(i==3){
			System.out.println("Sum: " + sum);
		}else if(i==4){
			System.out.println("Too many numbers!");
		}
	}

	public static int accumulate(){
		//acumilate usr input from array
		for(int i=0; i<a.length; i++){
			sum += a[i];
		}
		return sum;	
	}

	public static int clear(){
		//clear array and output after each run
		for(int i=0;i<a.length;i++){
			a[i]=0;
		}
		return sum = 0;
	}

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		order = 0;
		boolean exit = true;
		//outer loop for control whether usr wants another round
		while(exit){

			//inner loop for input, enter 'stop' to stop input
			while(true){

				prompt(1,0);
				String reader = sc.nextLine().trim();
				//enter stop to stop input
				if(reader.isEmpty()){
					continue;
				}else if(reader.equalsIgnoreCase("stop")){
					break;
				}
				//make sure usr enter positive integers exclude 0
				try{
					//ignore none positive interger input and ask usr to re-enter
					number = Integer.parseInt(reader);
					if (number <= 0){
						prompt(2, 0);
					//prevent from input times exceed array limit
					}else if (order >= a.length){
						prompt(4, 0);
						break;
					}else{
						//register input into array by order only when everything is valid
						a[order] = number;
						order++;
					}
				//ignore none interger input and ask usr to re-enter
				}catch(Exception e){
					prompt(2,0);
				}
			}
			/*test if everything registered in the array is correct
			System.out.print("You entered: ");
			for(int i=0;i<a.length;i++){
				System.out.printf("%d ", a[i]);
			}
			System.out.println();
			*/
			accumulate();
			prompt(3,sum);
			//ask usr if they want another round
			while(true){
				System.out.print("Continue?(Y/n): ");
				String onoff =sc.nextLine().trim();
	
				if(onoff.isEmpty()){
					clear();
					exit = true;
					break;
				}else if(onoff.equalsIgnoreCase("y")){
					clear();
					exit = true;						
					break;
				}else if(onoff.equalsIgnoreCase("n")){
					exit = false;
					break;
				}else{
					continue;
				}
			}
		}
		sc.close();
	}
}
	






