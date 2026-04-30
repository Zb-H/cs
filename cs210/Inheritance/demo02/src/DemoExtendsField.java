
/**
 * 
 */
public class DemoExtendsField{
    public static void main(String[] args) {
        Sup sup = new Sup();      //create object for super class
        System.out.println(sup.numSuper);           // super class's object can only use stuff from super class, 
        System.err.println("===============");      // can not access anything from subclass
        Sub sub = new Sub();
        System.out.println(sub.numSub);             //sub class's object can access stuff in both super and sub class
        System.err.println(sub.numSuper);
        System.err.println("===============");  

        //直接访问
        System.out.println(sub.num);                // 100 重名访问规则是 看Sub sub = new Sub(), = 左边是Sub 就是Sub
        //System.out.println(sub.xyz);              // 没有则向上（父类）找， 没有就编译错误
        System.out.println("===============");
        //间接访问
        sub.methodSub();                            // 100 子类方法优先用子类，没有再向父类找
        sub.methodSup();                            // 10 *这个方法是在父类中定义的，属于父类，输出的是父类方法的值
    }
}
