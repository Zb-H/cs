package BattleshipBoard;
/**
 * The Board class represents a Battleship game board.
 * A board stores its number of rows, number of columns, and a 2D character array.
 * The board can be initialized with a default character, updated with hit or miss markers,
 * used to display player ships, and printed with x/y axes.
 */
public class Board{
    private int boardRow;
    private int boardCol;
    private char[][] board;
    // Constructs an empty Board object.
    public Board(){

    }
    // Sets value for board's row and column
    // The board must be at least 10x10, 100x100 at most.
    public void setBoardRow(int boardRow){
        if(boardRow < 10 || boardRow > 100){
            throw new IllegalArgumentException("Row must between 10~2147483647");
        }
        this.boardRow = boardRow;
    }
    public void setBoardCol(int boardCol){
        if(boardCol < 10 || boardCol > 100){
            throw new IllegalArgumentException("Col must between 10~2147483647");
        }
        this.boardCol = boardCol;
    }
    // Get row and col values
    public int getBoardRow(){
        return this.boardRow;
    }
    public int getBoardCol(){
        return this.boardCol;
    }
    // Returns the character stored at a given coordinate.
    public char getCharAt(Coordinate coor){
        return this.board[coor.getRow()][coor.getCol()];
    }
    // Initialize board array with given character
    public void initBoard(char c){
        this.board = new char[this.boardRow][this.boardCol];
        for(int i = 0; i < this.boardRow; i++){
            for(int j = 0; j < this.boardCol; j++){
                this.board[i][j] = c;
            }
        }
    }
    // Registers a character at a specific coordinate.
    // This method is used to mark hits, misses, or other board changes.
    public void register(Coordinate coor, char c){
        this.board[coor.getRow()][coor.getCol()] = c;
    }
    // Places a ship on the board.
    // Each coordinate occupied by the ship is marked with '-'.
    public void placeShip(Ship ship){
        for(Coordinate coor : ship.getCoordinates()){
            this.board[coor.getRow()][coor.getCol()] = '-';
        }
    }
    // Prints the board with x-axis and y-axis labels.
    // The y-axis is printed from top to bottom
    // while the x-axis is printed along the bottom of the board.
    public void printBoard(){
        int lineNum = this.boardRow;
        System.out.printf("%2s%s%n", "Y", "|");
        for(char[] line : this.board){
            System.out.printf("%2d%s",lineNum, "|");
            for(char c : line){
                System.out.printf("%3c", c);
            }
            lineNum --;
            System.out.println();
        }
        System.out.printf("  +");
        for(int i = 0; i <= this.boardCol; i++){
            System.out.printf("%3s", "---");
        }
        System.out.println();
        System.out.printf("   ");
        for(int i = 1; i <= this.boardCol; i++){
            System.out.printf("%3d", i);
        }
        System.out.printf("%3s", "X");
        System.out.println();
    }
}
