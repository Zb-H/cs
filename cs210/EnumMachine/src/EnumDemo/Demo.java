package EnumDemo;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        State state = State.INPUT;

        while (state != State.EXIT) {
            System.out.print("> ");
            String input = sc.nextLine().trim();

            state = state.cook(input);
        }

        System.out.println("Program exited.");
        sc.close();
    }
}
