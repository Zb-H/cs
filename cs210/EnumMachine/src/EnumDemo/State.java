package EnumDemo;
//import java.util.*;

/**
 * Enum machine demo
 */

public enum State{
    INPUT{
        @Override
        public State cook(String input){
            if(input.equalsIgnoreCase("esc")){
                return EXIT;
            }
            if(input.equalsIgnoreCase("grep")){
                System.out.printf("Entering search mode.\n");
                return SEARCH;
            }
            System.out.printf("%s %s\n","Input: ", input);
            return this;
        }
    },
    SEARCH{
        @Override
        public State cook(String input){
            if(input.equalsIgnoreCase("esc")){
                return EXIT;
            }
            if(input.equalsIgnoreCase("inp")){
                System.out.printf("Back to input mode.\n");
                return INPUT;
            }
            System.out.printf("%s %s\n", "Searching: ", input);
            return this;
        }
    },
    EXIT{
        @Override
        public State cook(String input){
            return this;
        }
    };

    public abstract State cook(String input);
}
