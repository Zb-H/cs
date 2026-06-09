package BattleshipBoard;

public class Board {
    private char[][] board;

    public Board() {
        createBoard();
    }

    public void createBoard() {
        board = new char[20][20];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = '0';
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    public boolean placeShip(int row, int col, String type, String direction) {
        int length = getShipLength(type);

        if (length == -1) {
            System.out.println("Invalid ship type.");
            return false;
        }

        direction = direction.toLowerCase();

        if (!direction.equals("horizontal") && !direction.equals("vertical")) {
            System.out.println("Invalid direction.");
            return false;
        }

        // check edge
        if (direction.equals("horizontal")) {
            if (col + length > 20) {
                System.out.println("Ship does not fit horizontally.");
                return false;
            }
        } else {
            if (row + length > 20) {
                System.out.println("Ship does not fit vertically.");
                return false;
            }
        }

        // check overlap
        for (int i = 0; i < length; i++) {
            int currentRow = row;
            int currentCol = col;

            if (direction.equals("horizontal")) {
                currentCol = col + i;
            } else {
                currentRow = row + i;
            }

            if (board[currentRow][currentCol] != '0') {
                System.out.println("Ships cannot overlap.");
                return false;
            }
        }

        // place ship
        for (int i = 0; i < length; i++) {
            int currentRow = row;
            int currentCol = col;

            if (direction.equals("horizontal")) {
                currentCol = col + i;
            } else {
                currentRow = row + i;
            }

            board[currentRow][currentCol] = '-';
        }

        return true;
    }

    public void fire(int row, int col) {
        if (row < 0 || row >= 20 || col < 0 || col >= 20) {
            System.out.println("Invalid coordinate.");
            return;
        }

        if (board[row][col] == '-') {
            board[row][col] = 'X';
            System.out.println("Hit!");
        } else if (board[row][col] == '0') {
            board[row][col] = '?';
            System.out.println("Miss!");
        } else {
            System.out.println("You already fired here.");
        }
    }

    private int getShipLength(String type) {
        type = type.toLowerCase();

        if (type.equals("carrier")) {
            return 5;
        } else if (type.equals("battleship")) {
            return 4;
        } else if (type.equals("destroyer")) {
            return 2;
        } else if (type.equals("cruiser")) {
            return 3;
        } else if (type.equals("submarine")) {
            return 3;
        } else {
            return -1;
        }
    }
}
