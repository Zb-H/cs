package lab08;
import java.util.*;

/*
 * author@ Zhoabi Hunag
 * CS209
 * Feb27
 * This program generates a random number between 1 and 100 and 
 * ask the user to guess the number until the correct guess is made.
 * The program provides feedback ('Too high!' or 'Too low!') for each incorrect guess and 
 * prints the number of attempts the user made until finding the correct number.
 */

public class GuessNumber{

    static boolean exit;
    static int attempts;

    public static void prompt(int i){
        if(i==1)
            System.out.print("Enter your guess: ");
        if(i==2)
            System.out.println("Guess the number between 1 and 100!");
        if(i==3)
            System.out.println("Too high!");
        if(i==4)
            System.out.println("Too low!"); 
        if(i==5)
            System.out.println("Correct! You guessed it in " + (attempts+1) + " valid attempts.");      
        if(i==6)
            System.out.println("Invalid input!"); 
        if(i==7)
            System.out.println("No input!");
        if(i==8)
            System.out.print("One more round?(Y/n): ");
    }
    public static void main(String[] args) {

        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        exit = false;
    
        while(!exit){
            //reset attempts
            attempts = 0;
            prompt(2);
            //generate a random int
            int random = r.nextInt(100)+1;
            //input loop
            while(true){
                prompt(1);
                String input = sc.nextLine().trim();
                //nake sure usr enters valid input
                if (input.isEmpty()){
                    prompt(7);
                    continue;
                }
                try {
                    int number = Integer.parseInt(input);
                    //campare input with random int
                    if (number>random){
                        prompt(3);
                        attempts++;
                    }else if(number<random){
                        prompt(4);
                        attempts++;
                    }else{
                        prompt(5);
                        break;
                    }
                } catch (Exception e) {
                    prompt(6);
                }
            }
            //ask usr for one more round or not to
            while(true){
                prompt(8);
                String yn = sc.nextLine().trim();
                if(yn.equalsIgnoreCase("y")){
                    exit = false;
                    break;
                }else if(yn.equalsIgnoreCase("n")){
                    exit = true;
                    break;
                }else{
                    continue;
                }
            }
        }
    }
}