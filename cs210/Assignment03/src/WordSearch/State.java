package WordSearch;

import java.util.*;

/**
 * enum state machine
 * 4 states in total: command, input, search and exit state
 *          [command state]-------------
 *            ^         ^              |
 *            |         |              |
 *            v         v              |
 * [input state] <---> [search state]  |
 *           |         |               |
 *           v         v               |
 *           [exit state]<--------------
 *  * Git
 *
 * -- Repository :
 *    https://github.com/Zb-H/cs/tree/main
 *
 * -- Latest version (auto-updated):
 *    https://github.com/Zb-H/cs/blob/main/cs210/Assignment03/src/WordSearch/State.java
 */

public enum State {
    // cammand state
    COMMANDD{
        // state switch logic
        @Override
        public State cook(String input, ArrayList<Words> wordList){
            if(input.equalsIgnoreCase("esc")){
                return EXIT;
            }
            if(input.equalsIgnoreCase("grep")){
                System.out.printf("%60s\n","Enter search mode.");
                return SEARCH;
            }
            if(input.isEmpty()){
                System.out.printf("%54s\n","No input.");
                return this;
            }
            if(input.equalsIgnoreCase("inpt")){
                System.out.printf("%58s\n","Back to input mode.");
                return INPUT;
            }
            commandList();
            return this;
        }
    },
    // input state
    INPUT{
        // state switch logic
        @Override
        public State cook(String input, ArrayList<Words> wordList){
            if(input.equalsIgnoreCase("esc")){
                return EXIT;
            }
            if(input.equalsIgnoreCase("grep")){
                System.out.printf("%60s\n","Enter search mode.");
                return SEARCH;
            }
            if(input.isEmpty()){
                System.out.printf("%54s\n","No input.");
                return this;
            }
            if(input.equalsIgnoreCase("h")){
                commandList();
                return COMMANDD;
            }
            // create list to store filted input tokens
            ArrayList<String> invalid  = new ArrayList<>();
            ArrayList<String> valid = new ArrayList<>();

            //System.out.printf("%50s%s\n", "Input: ", input);      // -- testing

            slicer(input, valid, invalid);
            // load tokens
            for (String token : valid) {        // for each string in string list
                wordList.add(new Words(token));     // init an obj and load obj in list
            }
            // print filtered input
            printer(invalid, valid, "Loaded");
            //System.out.printf("\n%s\n", wordList.toString());     // -- just for experiment, see if it prints out addresses
            return this;
        }
    },
    // search state
    SEARCH{
        // state switch logic
        @Override
        public State cook(String input, ArrayList<Words> wordList){
            if(input.equalsIgnoreCase("esc")){
                return EXIT;
            }
            if(input.equalsIgnoreCase("inpt")){
                System.out.printf("%58s\n","Back to input mode.");
                return INPUT;
            }
            if(input.isEmpty()){
                System.out.printf("%54s\n","No input.");
                return this;
            }
            if(input.equalsIgnoreCase("h")){
                commandList();
                return COMMANDD;
            }
            // create list to store filted input tokens
            ArrayList<String> invalid  = new ArrayList<>();
            ArrayList<String> valid = new ArrayList<>();

            //System.out.printf("%50s%s\n","Searching: ", input);       // -- testing

            slicer(input, valid, invalid);

            printer(invalid, valid, "Searching");
            // search words
            for(String target : valid){
                int wordCount = 0;      // times counter
                for(Words w : wordList){        // for each object in list
                    if(target.equalsIgnoreCase(w.getWord())){       // check if search word equals obj.getWord()
                        wordCount ++;
                    }
                }
                System.out.printf("%48s: %d times.\n", target, wordCount);
            }

            return this;
        }
    },
    // exit state
    EXIT{
        // exit state is final state can not return to other states
        @Override
        public State cook(String input, ArrayList<Words> wordList){
            return this;
        }
    };      // end of last state
    // abstract method
    public abstract State cook(String input, ArrayList<Words> wordList);
    // regex for data
    private static final String REGEX = "^[A-Za-z]+(-[A-Za-z]+)?(-[A-Za-z]+)?$";
    // spilt input and check each of them matches regex to store
    private static void slicer(String input, ArrayList<String> valid, ArrayList<String> invalid){
        for(String token : input.split("\\s+")){
            if(token.matches(REGEX)){
                valid.add(token);
            }else{
                invalid.add(token);
            }
        }
    }
    // input inspect result printing method
    private static void printer(ArrayList<String> invalid, ArrayList<String> valid, String lable){
        if(!invalid.isEmpty()){
            System.out.printf("%50s%s\n", "Invalid word: ", invalid.toString());
        }
        if(!valid.isEmpty()){
            System.out.printf("%43s%s%s\n", lable," word: ", valid.toString());
        }else{
            System.out.printf("%57s\n", "No valid word.");
        }
    }
    // simply prints out type in commands
    private static void commandList(){

            System.out.printf("%65s\n%66s\n%62s\n"
                            , "Type 'inpt' to enter input mode."
                            , "Type 'grep' to enter search mode."
                            , "Type 'esc' to exit program.");
    }

}:word
