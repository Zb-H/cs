package Review;

public class SwitchDemo {
    public static void demo(int choice){
        switch(choice){
            case 1 :
                System.out.println("c = 1");
                break;
            case 2 :
                System.out.println("c = 2");
                break;
            case 3 :
                System.out.println("c = 3");
                break;
            case 4 :
                System.out.println("Exit");
                break;
            default :
                System.out.println("Invalid");
                break;
        }
    }
}
