package BattleshipBoard;
/**
 * The Coordinate class represents one location on the Battleship board.
 * A Coordinate stores a row index and a column index.
 * It also overrides the equals method so two Coordinate objects are considered equal
 * when they have the same row and column values.
 */
public class Coordinate{
    private int row;
    private int col;
    // Constructs an empty Coordinate object.
    public Coordinate(){

    }
    // Sets the row/column index of this coordinate.
    public void setRow(int row){
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
    }
    // Gets row/column index of this coordinate.
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    // Compares this coordinate with another object.
    // Two Coordinate objects are considered equal if they have the same row and column values.
    // Rewrite .equals
    @Override
    public boolean equals(Object obj){
        // if all objs ponits to the same address
        if(this == obj){
            return true;
        }
        // if obj is not class Coordinate return false
        if(!(obj instanceof Coordinate)){
            return false;
        }
        // force convert obj to class Coordinate
        Coordinate other = (Coordinate) obj;
        // compare coordinates
        return this.row == other.row && this.col == other.col;
    }
}
