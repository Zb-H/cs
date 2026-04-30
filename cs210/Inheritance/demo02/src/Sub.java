


public class Sub extends Sup{
    int numSub = 1;

    int num = 100;

    public void methodSub(){
        //因为本类有num，用本类num
        System.out.println(num);
    }
}
