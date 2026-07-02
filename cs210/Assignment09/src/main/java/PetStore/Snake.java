package PetStore;

public class Snake extends Pet{

    private double length;

    public double getLength(){
        return this.length;
    }

    public void setLength(double length){
        this.length = length;
    }

    @Override
    public String speak(){
        return "Hiss Hiss.";
    }
}
