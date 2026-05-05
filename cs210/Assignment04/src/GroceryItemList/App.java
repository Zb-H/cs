package GroceryItemList;
import java.util.*;
import java.io.*;

/**
 *  Zhaobi Huang
 *  cs210
 *  2026-05-01
 *
 *  Create grocery object items
 *  Add the grocery items to the grocery list
 *  Generate a report of the grocery list including item descriptions, prices and total price.
 *
 */
public class App{
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int listCount = 1;
        String name;
        String fileName;
        int quantity;
        double pricePerUnit;
        boolean isRunning = false;
        File dir = new File("lists");


        //program loop
        do{
            GroceryList list = new GroceryList();

            // single list loop
            while(true){
                System.out.printf("%s\n", "-".repeat(100));
                System.out.printf("%30s -%02d-\n", "Item", (list.getNumItems()+1));
                // item loop
                while(true){
                    System.out.printf("%-50s", "Enter item name:");
                    name = sc.nextLine().trim();

                    if(name.isEmpty()){
                        continue;
                    }
                    break;
                }
                // quantity loop
                while(true){
                    try{
                        System.out.printf("%-50s", "Enter item quantity:");
                        String input = sc.nextLine().trim();
                        if(input.isEmpty()){
                            continue;
                        }
                        quantity = Integer.parseInt(input);
                        break;
                    }catch(NumberFormatException e){
                        System.out.printf("%65s\n","Quantity must be an integer");
                        continue;
                    }
                }
                // pricePerUnit loop
                while(true){
                    try{
                        System.out.printf("%-50s", "Enter item price(per unit):");
                        String input = sc.nextLine().trim();
                        if(input.isEmpty()){
                            continue;
                        }
                        pricePerUnit = Double.parseDouble(input);
                        break;
                    }catch(NumberFormatException e){
                        System.out.printf("%62s\n", "pice must be a double");
                        continue;
                    }
                }
                // data load statements
                list.add(new GroceryItemOrder(name, quantity, pricePerUnit));
                // if list is full print list
                if(list.getNumItems() >= list.MAX_ITEMS){
                    System.out.printf("%s\n", "-".repeat(100));
                    System.out.printf("List max capacity reached, printing list\n\n");

                    fileName = String.format("%s%02d.txt", "GroceryLists", listCount);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File file = new File(dir, fileName);
                    FileWriter wtr = new FileWriter(file, false);

                    list.displayItems(listCount, wtr);
                    wtr.close();
                    System.out.printf("\n\n");

                    while(true){
                        System.out.printf("%-50s", "Create more lists(Y/n):");
                        String input = sc.nextLine().trim();
                        if(input.isEmpty() || input.equalsIgnoreCase("y")){
                            System.out.printf("\n");
                            isRunning = true;
                            break;
                        }else if(input.equalsIgnoreCase("n")){
                            System.out.printf("\n");
                            isRunning = false;
                            break;
                        }else{
                            continue;
                        }
                    }
                    break;
                }
            }
            listCount ++;
        }while(isRunning);
        sc.close();
    }
}
