package Factorial;
// f(n) = f(n-1)*n
public class App{
    public static void main(String[] args){
        System.out.print("factorial of 5 is: " + f(5));
    }

    public static int f(int n){
        if (n == 1){
            return 1;
        }else{
            return f(n-1)*n;
        }
    }
}
