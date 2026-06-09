package BattleshipBoard;

public class Board{
    private int boardRow;
    private int boardCol;
    private char[][] board;

    public Board(){

    }
    // set value for board row and col
    public void setBoardRow(int boardRow){
        if(boardRow < 10 || boardRow > 2147483647){
            throw new IllegalArgumentException("Row must between 10~2147483647");
        }
        this.boardRow = boardRow;
    }
    public void setBoardCol(int boardCol){
        if(boardCol < 10 || boardCol > 2147483647){
            throw new IllegalArgumentException("Col must between 10~2147483647");
        }
        this.boardCol = boardCol;
    }
    // get row and col values
    public int getBoardRow(){
        return this.boardRow;
    }
    public int getBoardCol(){
        return this.boardCol;
    }
    public char getCharAt(Coordinate coor){
        return this.board[coor.getRow()][coor.getCol()];
    }
    // initial board array
    public void initBoard(char c){
        this.board = new char[this.boardRow][this.boardCol];
        for(int i = 0; i < this.boardRow; i++){
            for(int j = 0; j < this.boardCol; j++){
                this.board[i][j] = c;
            }
        }
    }
    // load hit mark or miss into board array
    public void register(Coordinate coor, char c){
        this.board[coor.getRow()][coor.getCol()] = c;
    }
    public void placeShip(Ship ship){
        for(Coordinate coor : ship.getCoordinates()){
            this.board[coor.getRow()][coor.getCol()] = '-';
        }
    }
    // print board with x and y axis
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
