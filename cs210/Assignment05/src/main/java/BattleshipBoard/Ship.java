package BattleshipBoard;
/**
 * The Ship class is an abstract parent class for all Battleship ships.
 * A ship stores its size, type, hit count, sunk status, direction,
 * and thecoordinates it occupies on the board.
 * The class provides methods for setting basic ship information,
 * initializing ship coordinates, recording hits, and checking whether the ship has sunk.
 * Specific ship types such as Carrier, Battleship, Cruiser, Submarine,
 * and Destroyer extend this class and define their own size and type.
 */
public abstract class Ship{
    private int size;
    private int hit;
    private boolean isSunk;
    private boolean isHorizontal;
    private String type;
    private Coordinate[] coordinates;
    // Constructs a Ship object.
    // The hit count starts at 0, and the ship starts as not sunk.
    public Ship(){
        this.hit = 0;
        this.isSunk = false;
    }
    // Sets the size, type and direction of the ship.
    public void setSize(int size){
        this.size = size;
    }
    public void setType(String type){
        this.type = type;
    }

    public void setDirection(boolean direction){
        this.isHorizontal = direction;
    }
    // Gets ship's data fields
    public int getSize(){
        return this.size;
    }
    public String getType(){
        return this.type;
    }
    public int getHit(){
        return this.hit;
    }
    public boolean getStatus(){
        return this.isSunk;
    }
    public boolean getDirection(){
        return this.isHorizontal;
    }
    public Coordinate[] getCoordinates(){
        return this.coordinates;
    }
    // Checks whether the ship has sunk.
    // If the ship has not already sunk and its hit count is greater than
    // or equal to its size, the ship's sunk status is changed to true.
    public boolean checkSink(){
        if(!this.isSunk && (this.hit >= this.size)){
           this.isSunk = true;
           return true;
        }
        return false;
    }
    // Initializes the coordinates occupied by the ship.
    // The first coordinate is the starting row and column.
    // If the ship is horizontal/vertical, the following coordinates increase by column/row.
    public void initShip(int row, int col){
        this.coordinates = new Coordinate[this.size];
        this.coordinates[0] = new Coordinate();
        this.coordinates[0].setRow(row);
        this.coordinates[0].setCol(col);
    for(int i = 1; i < this.size; i++){
        if(this.isHorizontal){                  // horizontal
            // x = col + 1
            this.coordinates[i] = new Coordinate();
                this.coordinates[i].setRow(this.coordinates[i-1].getRow());
                this.coordinates[i].setCol(this.coordinates[i-1].getCol() + 1);
            }else{
                // y = row + 1
                this.coordinates[i] = new Coordinate();
                this.coordinates[i].setRow(this.coordinates[i-1].getRow() + 1);
                this.coordinates[i].setCol(this.coordinates[i-1].getCol());
            }
        }
    }
    // Records one hit on the ship.
    public void hit(){
        this.hit ++;
    }
}
// Sub classes
class Carrier extends Ship{
    public Carrier(){
        super();
        setSize(5);
        setType("Carrier");
    }
}
class Battleship extends Ship{
    public Battleship(){
        super();
        setSize(4);
        setType("Battleship");
    }
}
class Cruiser extends Ship{
    public Cruiser(){
        super();
        setSize(3);
        setType("Cruiser");
    }
}
class Submarine extends Ship{
    public Submarine(){
        super();
        setSize(3);
        setType("Submarine");
    }
}
class Destroyer extends Ship{
    public Destroyer(){
        super();
        setSize(2);
        setType("Destroyer");
    }
}
