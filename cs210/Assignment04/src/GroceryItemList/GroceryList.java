package GroceryItemList;

import java.io.FileWriter;
import java.io.IOException;

// This class keeps track of a grocery list of items
// copied from professor Hueffed and slightly modified

public class GroceryList {
    public static final int MAX_ITEMS = 10;
    // declare array contains obj from GroceryItemOrder
    private GroceryItemOrder[] items;
    private int numItems;

    public GroceryList()
    {
        // create an array contains 10 elements,let items point to it
        // each element is an obj from cls GroceryItemOrder
        items = new GroceryItemOrder[MAX_ITEMS];
        numItems = 0;       // index
    }

    public int getNumItems(){
        return this.numItems;
    }

    public void add(GroceryItemOrder item)
    {
        items[numItems] = item;
        numItems++;
    }
    // rename method to totalCost
    public double totalCost()
    {
        double cost = 0.0;
        for (int i = 0; i < numItems; i++)
        {
            cost += items[i].cost();
        }
        return cost;
    }

    // Joe - Added this item
    // modified printing method makes it easier to read
    // add statements to write output in a file
    public void displayItems(int listCounti, FileWriter wtr) throws IOException
    {
        String title = String.format("%45s\n\n", "-- Grocery List --");
        System.out.printf(title);
        wtr.write(title);


        for (int i = 0; i < numItems; i++)
        {
            String line = String.format("|%30s| total  $%-30.2f|\n", items[i].toString(), items[i].cost());
        	System.out.printf(line);
            wtr.write(line);
        }

        String totalCost = String.format("\n%35s >$%.2f<\n", "Total cost", totalCost());
        System.out.printf(totalCost);
        wtr.write(totalCost);
    }
}
