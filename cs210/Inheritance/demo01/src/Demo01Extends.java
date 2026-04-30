/**
 * public class <superclass>{
 *  //......
 * }
 * public class <subclass> extends <superclass>{
 *  //......
 * }
 */
public class Demo01Extends{
    public static void main(String[] args){
        //create an object of subclass
        Teacher teacher = new Teacher();
        // though there is nothing in subclass,
        // method extended from superclass can still be called
        teacher.method();       //output: method execute

        Assistant assistant = new Assistant();

        assistant.method();
    }
}
