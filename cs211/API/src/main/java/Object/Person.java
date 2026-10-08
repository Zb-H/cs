package Object;

public class Person{
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    /**
     * 直接打印对象的地址值没有意义，需要重写Object类的toString方法
     * 打印对象的属性（name，age）
     */
    @Override
    public String toString(){
        return "Name: " + this.name + ", age: " + this.age;
    }
}
