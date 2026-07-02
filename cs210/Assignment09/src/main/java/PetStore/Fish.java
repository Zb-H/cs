package PetStore;

public class Fish extends Pet{

    private int age;

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String speak(){
        return "I'm a fish I can't speak.";
    }
}
