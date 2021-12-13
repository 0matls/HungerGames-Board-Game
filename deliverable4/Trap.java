package deliverable4;

/**
 * 
 * Class denoting a trap on the board.Traps can be either ropes or animals.
 *
 */
public class Trap {
    private int id;
    private int x;
    private int y;
    private String type;
    private int points;
    
    /**
     * Constructor of the class; initializes its identity (counting starts from one),and its type (i.e. rope or animal).
     * 
     * NOTE THAT :  its x and y coordinates on the board aren't needed as arguments as they will be set in 
     * function createRandomTrap in class Board, so for now they are temporarily set to 0.
     * Points (the player loses them when he comes across a trap's tile on the board) are also temporarily set to 0 
     * as their value will be defined (randomly) in function createRandomTrap in class Board.
     * 
     * @param id the unique identifier of the trap
     * @param type (i.e rope or animal)
     */
    
    public Trap(int id, String type) {
        this.id = id;
        this.x = 0;
        this.y = 0;
        this.type = type;
        this.points = 0;    
    }
    
    /**
     * Constructor of the class; initializes its variables by a Trap object given as an argument
     * @param trap object
     */
    
    public Trap(Trap trap) {
        this.id = trap.id;
        this.x = trap.x;
        this.y = trap.y;
        this.type = trap.type;
        this.points = trap.points;
    }
    /**
     * functions (a.k.a setters) to set the values of the variables in the class
     * @param id, x, y, type, points
     */
    public void setId(int id) {
        
        this.id = id;
    }
    
    public void setX(int x) {
        
        this.x = x;
    }
    
    public void setY(int y) {
        
        this.y = y;
    }
    
    public void setType(String type) {
        
        this.type = type;
    }
    
    public void setPoints(int points) {
        
        this.points = points;
    }
    
    /**
     * functions (a.k.a getters) to get the values of the variables in the class
     * @return id, x, y, type, points
     */
    
    public int getId() {
        
        return id;
    }
    
    public int getX() {
        
        return x;
    }
    
    public int getY() {
        
        return y;
    }
    
    public String getType() {
        
        return type;
    }
    
    public int getPoints() {
        
        return points;
    }
    
    
}