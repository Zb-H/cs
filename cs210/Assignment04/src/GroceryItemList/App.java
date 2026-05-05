package GroceryItemList;
import java.util.*;
import java.io.*;

/**
 * Program Name: Grocery Item List
 * Author: Zhaobi Huang
 * Course: CS 210
 * Date: 2026-05-01
 *
 * GitHub URL:
 * https://github.com/Zb-H/cs/blob/main/cs210/Assignment04/src/GroceryItemList/App.java
 *
 * Description:
 * This program creates grocery shopping lists using the GroceryItemOrder
 * and GroceryList classes. The user enters an item name, quantity, and
 * price per unit for each grocery item. Each item is stored as a
 * GroceryItemOrder object and added to a GroceryList object.
 *
 * When the grocery list reaches its maximum capacity, the program displays
 * a formatted report showing each item description, item cost, and the total
 * cost of the grocery list. The report is also written to a txt file inside
 * the "lists" folder.
 *
 * Main Features:
 * - Creates grocery item objects from user input
 * - Adds grocery items to a grocery list
 * - Validates quantity and price input
 * - Displays a formatted grocery list report
 * - Writes the grocery list report to a text file
 * - Allows the user to create additional grocery lists
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
                    // if nothing is read, promt again
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
                    // create derictory if it is not exit under package derictory and create a file writer
                    fileName = String.format("%s%02d.txt", "GroceryLists", listCount);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File file = new File(dir, fileName);
                    FileWriter wtr = new FileWriter(file, false);
                    // prints output in both terminal and txt file and close writing in file
                    list.displayItems(listCount, wtr);
                    wtr.close();
                    System.out.printf("\n\n");
                    // statements decide to kill program loop or not
                    while(true){
                        System.out.printf("%-50s", "Create more lists(Y/n):");
                        String input = sc.nextLine().trim();
                        // defaut or 'y' to continue on program looping, reads 'n' to exit program
                        // otherwise anyhting else read will continue prompting
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
            listCount ++;       // list count +1 for next title
        }while(isRunning);
        sc.close();
    }
}
