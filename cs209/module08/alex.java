package lab08;

/*
 * Alex starts with $50 and saves $10 each week. Write the code for a while loop to
 * calculate how many weeks it will take for him to have $1000 saved up.
 */

public class alex{
	public static void main(String[] args){

		int money = 50;
		int weeks = 0;

		while(money<1000){
			money += 10;
			weeks++;	
		}

		System.out.println("It takes " + weeks + " weeks.");
	}
}
