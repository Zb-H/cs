package Intro;

/**
 * intro to recursion
 */
public class App {
    public static void main(String[] args) {
        test1();
    }
// derect recursion
    public static void test1(){
        System.out.println("----test01----");
        test1();        //StackOverFlowError
    }

// inderect recursion
    public static void test2(){
        System.out.println("----test02----");
        test3();
    }
    public static void test3(){
        test2();
    }
}
