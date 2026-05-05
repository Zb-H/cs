package GroceryItemList;

// This class stores information about a single grocery item being ordered.
// copied from professor Hueffed and slightly modified

public class GroceryItemOrder
{
    private String name;
    private int quantity;
    private double pricePerUnit;
    // constructor
    public GroceryItemOrder(String name, int quantity, double pricePerUnit)
    {
        this.name = name;
        // this.quantity = quantity;        -- does not understand what is this for since a setter method exists
        setQuantity(quantity);
        this.pricePerUnit = pricePerUnit;
    }
    // rename method to cost, getcost is very confusing
    public double cost()
    {
        return quantity * pricePerUnit;
    }
    // seems like a setter method? fixed misspelling from setQuanity if it is a setter method
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    // modify this method makes printing method easier to read
    public String toString()
    {
        return String.format("%10s of %-20s", quantity, name);
    }

    // Joe - Added this method
    // seems like a getter method
    // rename method to getName, getItemName is also extremly confusing
    // and may violates the JavaBeans naming convention and breaks automatic mapping.
    public String getName()
    {
       return this.name;
    }
}
