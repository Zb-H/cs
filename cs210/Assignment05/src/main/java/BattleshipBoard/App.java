package BattleshipBoard;

import java.util.*;
/**
 * Hello world!
 */
public class App{
    public static int convertToCol(int x){
        return x - 1;
    }
    public static int convertToRow(int y, Board board){
        return board.getBoardRow() - y;
    }
    public static void setRandomDirection(Ship ship){
        ship.setDirection(Math.random() < 0.5);
    }
    public static Coordinate randomCoordinate(Board board){
        Coordinate coor = new Coordinate();
        coor.setRow((int)(Math.random() * board.getBoardRow()));
        coor.setCol((int)(Math.random() * board.getBoardCol()));
        return coor;
    }
    public static int randomCol(Board board, Ship ship){
        if(ship.getDirection()){
            return (int)(Math.random() * (board.getBoardCol() - ship.getSize() + 1));
        }else{
            return (int)(Math.random() * board.getBoardCol());
        }
    }
    public static int randomRow(Board board, Ship ship){
        if(ship.getDirection()){
            return (int)(Math.random() * board.getBoardRow());
        }else{
            return (int)(Math.random() * (board.getBoardRow() - ship.getSize() + 1));
        }
    }
    public static int intInput(Scanner sc){
        while(true){
            try{
                return Integer.parseInt(sc.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Input must be an integer.");
                continue;
            }
        }
    }
    public static boolean directionInput(Scanner sc){
        while(true){
            int input = intInput(sc);
            if(input == 0){
                return true;
            }else if(input == 1){
                return false;
            }else{
                System.out.println("Input must be either 0 or 1.");
                continue;
            }
        }
    }
    public static boolean rowOoB(int row, Board board, Ship ship){
        if(ship.getDirection()){
            return row >= board.getBoardRow() || row < 0;
        }else{
            return (row + ship.getSize()) > board.getBoardRow() || row < 0;
        }
    }
    public static boolean colOoB(int col, Board board, Ship ship){
        if(ship.getDirection()){
            return col + ship.getSize() > board.getBoardCol() || col < 0;
        }else{
            return col >= board.getBoardCol() || col < 0;
        }
    }
    public static boolean overlay(Ship[] ships, int currentIndex){
        Coordinate[] currentCoordinates = ships[currentIndex].getCoordinates();
        for(int i = 0; i < currentIndex; i++){
            Coordinate[] lastCoordinates = ships[i].getCoordinates();
            for(Coordinate lastCoordinate : lastCoordinates){
                for(Coordinate currentCoordinate : currentCoordinates){
                    if(currentCoordinate.equals(lastCoordinate)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static void testShipInfo(Ship[] ships){
        for(Ship ship : ships){
            System.out.printf("type: " + ship.getType() + " | ");
            System.out.println("size: " + ship.getSize());
            System.out.println("ishorizontal: " + ship.getDirection());
            System.out.printf("hit: " + ship.getHit() + " | ");
            System.out.println("isSunk: " + ship.getStatus());
            for(Coordinate coordinate : ship.getCoordinates()){
                System.out.print("row: " + coordinate.getRow() + " ");
                System.out.print("col: " + coordinate.getCol() + " | ");
            }
            System.out.println();
        }
    }
    private static void fire(Ship[] ships, Coordinate target, Board board){
        if(board.getCharAt(target) == 'X' || board.getCharAt(target) == '?'){
            throw new IllegalArgumentException("You've already fired at this location, pick another.");
        }
        for(Ship ship : ships){
            for(Coordinate coor : ship.getCoordinates()){
                if(coor.equals(target)){
                    ship.hit();
                    board.register(target, 'X');
                    System.out.println("Hit");
                    return;
                }
            }
        }
        System.out.println("Miss");
        board.register(target, '?');
    }
    private static void checkStatus(Ship[] ships){
        for(Ship ship : ships){
            if(ship.checkSink()){
                System.out.println(ship.getType() + " is sunk");
            }
        }
    }
    private static boolean allShipSunk(Ship[] ships){
        if(ships[0].getStatus() && ships[1].getStatus() &&
           ships[2].getStatus() && ships[3].getStatus() &&
           ships[4].getStatus()){
            return true;
        }else{
            return false;
        }
    }
    // main method
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
/********************************* prompt for input to init boards ****************************/
        // set player board
        Board playerBoard = new Board();
        System.out.println("Enter board row: ");
        while(true){
            try{
                playerBoard.setBoardRow(intInput(sc));
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter board col: ");
        while(true){
            try{
                playerBoard.setBoardCol(intInput(sc));
                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        // set bot board
        Board botBoard = new Board();
        botBoard.setBoardRow(playerBoard.getBoardRow());
        botBoard.setBoardCol(playerBoard.getBoardCol());
        playerBoard.initBoard('0');
        botBoard.initBoard('0');

        playerBoard.printBoard();
        System.out.println("\n");
        botBoard.printBoard();
/********************************** create ships **************************************/
        Ship[] botShips = {
                            new Carrier(),
                            new Battleship(),
                            new Cruiser(),
                            new Submarine(),
                            new Destroyer()
        };
        Ship[] playerShips = {
                            new Carrier(),
                            new Battleship(),
                            new Cruiser(),
                            new Submarine(),
                            new Destroyer()
        };
/********************************** init ships ***********************************************/
        for(int i = 0; i < botShips.length; i++){
            setRandomDirection(botShips[i]);
            while(true){
                int row;
                int col;
                while(true){
                    row = randomRow(botBoard, botShips[i]);
                    if(rowOoB(row, playerBoard, botShips[i])){
                        continue;
                    }
                    break;
                }
                while(true){
                    col = randomCol(botBoard, botShips[i]);
                    if(colOoB(col, playerBoard, botShips[i])){
                        continue;
                    }
                    break;
                }
                botShips[i].initShip(row, col);
                if(overlay(botShips, i)){
                    continue;
                }else{
                    break;
                }
            };
        }
        testShipInfo(botShips);

        for(int i = 0; i < playerShips.length; i++){
            // prompt for direction
            System.out.println("Enter " + playerShips[i].getType() + "direction: ");
            System.out.println("('0' for horizontal, '1' for vertical)");
            playerShips[i].setDirection(directionInput(sc));
            // prompt for x and y, loop til ship array meets all requirement
            while(true){
                // prompt for x and y for coordinate and convert to col and row
                int col;
                int row;
                System.out.println("Enter x-coordinate: ");
                while(true){
                    col = convertToCol(intInput(sc));
                    if(colOoB(col, playerBoard, playerShips[i])){
                        System.out.println("Out of bound, enter again.");
                        continue;
                    }
                    break;
                }
                System.out.println("Enter y-coordinate: ");
                while(true){
                    row = convertToRow(intInput(sc), playerBoard);
                    if(rowOoB(row, playerBoard, playerShips[i])){
                        System.out.println("Out of bound, enter again.");
                        continue;
                    }
                    break;
                }
                // initial ship
                playerShips[i].initShip(row, col);
                // check overlay
                if(overlay(playerShips, i)){
                    System.out.println("Overlay detected, enter again.");
                    continue;
                }
                break;
            };
        }
        testShipInfo(playerShips);      // test
        // place player ship on player board
        for(Ship ship : playerShips){
            playerBoard.placeShip(ship);
        }
        playerBoard.printBoard();       // test
        botBoard.printBoard();      // test
/************************************* fire and register hit or miss on board *********************************************/
        int loopCount = 0;
        while(!allShipSunk(playerShips) && !allShipSunk(botShips)){
            // prompt for new coordinate target
            Coordinate playerTarget = new Coordinate();
            System.out.println("Enter target x-coordinate: ");
            while(true){
                playerTarget.setCol(convertToCol(intInput(sc)));
                if(playerTarget.getCol() >= botBoard.getBoardCol() || playerTarget.getCol() < 0){
                    System.out.println("Out of bounds, enter agian.");
                    continue;
                }
                break;
            }
            System.out.println("Enter target y-coordinate: ");

            while(true){
                playerTarget.setRow(convertToRow(intInput(sc), botBoard));
                if(playerTarget.getRow() >= botBoard.getBoardRow() || playerTarget.getRow() < 0){
                    System.out.println("Out of bounds, enter agian.");
                    continue;
                }
                break;
            }
            // register player and bot random target coordinate on boards
            // try new location till the location have not be selected before
            try{
                fire(botShips, playerTarget, botBoard);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                continue;
            }
            while(true){
                try{
                    fire(playerShips, randomCoordinate(playerBoard), playerBoard);
                    break;
                }catch(IllegalArgumentException e){
                    continue;
                }
            }


            checkStatus(playerShips);
            checkStatus(botShips);
            loopCount ++;
            playerBoard.printBoard();
            botBoard.printBoard();
        }
/************************************ check winner *****************************************/
        if(allShipSunk(playerShips)){
            System.out.println("BOT WINS after " + loopCount + " rounds.");
        }else{
            System.out.println("PLAYER WINS" + loopCount + " rounds.");
        }
        sc.close();
    }
}
