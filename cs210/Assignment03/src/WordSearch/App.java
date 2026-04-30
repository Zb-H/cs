package WordSearch;

import java.util.*;

/**
 * Zhaobi Huang
 * cs210
 * 2026-4-26
 *
 * * Program description:
 * A command-line word storage and search program.
 *
 * * This program uses a state machine with four states:
 *
 * -- INPUT mode, the user can enter words. Valid words are stored
 * in an ArrayList<Words>. Invalid words are rejected.
 *
 * -- SEARCH mode, the user can enter words to search for. The program
 * counts how many times each search word appears in the stored word list.
 *
 * -- COMMANDD mode, the program displays available commands.
 *
 * * Commands:
 *   h     -- show command list
 *   grep  -- enter search mode
 *   inpt  -- return to input mode
 *   esc   -- exit the program
 *
 * * Word rules:
 *   A valid word may contain letters and hyphens.
 *   Examples:
 *     valid:   apple, note-book, mother-in-law
 *     invalid: apple123, -apple, apple-
 *
 * * Main features:
 *   -- Stores valid words
 *   -- Rejects invalid words
 *   -- Searches stored words
 *   -- Counts word frequency
 *   -- Uses enum-based state machine
*/

public class App{
    // simply prints 100 '-'
    private static void line(){
        for(int i = 0; i < 100; i++){
            System.out.printf("-");
        }
        System.out.printf("\n");
    }
    // main method
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        // array list contains addresses of objects
        ArrayList<Words> wordList = new ArrayList<>();
        State state = State.INPUT;      // init state is input state

        line();
        System.out.printf("%70s\n","=== Enter 'h' to toggle command list. ===");
        line();
        // program loop
        while(state != State.EXIT){
            // indicator indicates which state we at
            if(state == State.INPUT){
                System.out.printf("[Input] > ");
            }else if(state == State.SEARCH){
                System.out.printf("[Search] > ");
            }else{
                System.out.printf("[Command] > ");
            }

            String input = sc.nextLine().trim();

            line();
            // state switch
            state = state.cook(input, wordList);
        }

        System.out.printf("Program exited.\n");
        sc.close();
    }
}
