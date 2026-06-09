package BattleshipBoard;

public class Coordinate{
    private int row;
    private int col;

    public Coordinate(){

    }

    public void setRow(int row){
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    // rewrite .equals to make it decide if objs are equal by compare coordinates
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
