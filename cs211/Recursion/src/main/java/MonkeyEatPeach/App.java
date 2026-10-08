package MonkeyEatPeach;

// f(n+1) = f(n)-f(n)/2 - 1
// 2f(n+1) = 2f(n)- f(n) - 2
// f(n) = 2f(n+1) + 2
//
// f(10) = 1
// f(1) = ?
public class App{

    public static void main(String[] args){

      //  System.out.print(f(1));

        prt(1);
    }

    public static int f(int n){

        if(n == 10){
            return 1;
        }
        return 2*f(n+1) + 2;
    }

    public static void prt(int n){
        if (n > 10){
            return;
        }

        System.out.println("day : " + n + ", peach : " + f(n));
        prt(n+1);
    }
}
