package BattleshipBoard;

public abstract class Ship{
    private int size;
    private int hit;
    private boolean isSunk;
    private boolean isHorizontal;
    private String type;
    private Coordinate[] coordinates;

    public Ship(){
        this.hit = 0;
        this.isSunk = false;
    }

    public void setSize(int size){
        this.size = size;
    }
    public void setType(String type){
        this.type = type;
    }

    public void setDirection(boolean direction){
        this.isHorizontal = direction;
    }

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

    public boolean checkSink(){
        if(!this.isSunk && (this.hit >= this.size)){
           this.isSunk = true;
           return true;
        }
        return false;
    }
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
    public void hit(){
        this.hit ++;
    }
}
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
