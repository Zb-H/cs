package BattleshipBoard;

import java.util.*;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Board game = new Board();

        game.placeShip(0, 0, "carrier", "horizontal");
        game.placeShip(2, 5, "battleship", "vertical");
        game.placeShip(6, 3, "destroyer", "horizontal");
        game.placeShip(10, 10, "cruiser", "vertical");
        game.placeShip(15, 15, "submarine", "horizontal");

        game.printBoard();

        System.out.println();

        game.fire(0, 0);   // hit
        game.fire(1, 1);   // miss

        System.out.println();

        game.printBoard();
    }
}
