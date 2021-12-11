package deliverable2;
import java.util.Random;
/**
 * 
 * Class variables and arrays :
 * id : the unique identity of each of the players ( 1 for the first player and 2 for the second)
 * name : the game of the player as given as an argument in the constructor
 * board : the board of the game
 * score :  the score of the player that changes in every round (should he eat something or fall in a trap)
 * x : the x coordinate of the tile the player is on
 * y : the y coordinate of the tile the player is on
 * bow : the weapon of type bow the player might acquire
 * pistol : the weapon of type pistol the player might acquire
 * sword : the weapon of type sword the player might acquire
 *
 *
 */
public class Player {
	
	/** 
	 * 
	 * We need the following "random" object  to generate random integers later on
	 * 
	 */
	
    Random random = new Random();
    int id;
    String name;
    Board board;
    int score;
    int x;
    int y;
    Weapon bow;
    Weapon pistol;
    Weapon sword;
    
    /**
     * 
     * Constructor of the class; initializes the identity, name, x and y coordinates on the board and the board
     * of the game.Also at the beginning of the game the player doesn't possess any weapon.
     * @param id
     * @param name
     * @param x
     * @param y
     * @param board
     */
    public Player(int id, String name,int x,int y,Board board) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.x = x;
        this.y = y;
        
        this.board = board;
        
        bow = null;
        
        pistol = null;
        
        sword = null;
    }
    /**
     * Constructor of the class; initializes the player by a player object given as an argument.
     * 
     * @param player object
     */
    public Player(Player player) {
        this.id = player.id;
        this.name = player.name;
        this.score = player.score;
        this.x = player.x;
        this.y = player.y;
        this.bow = player.bow;
        this.sword = player.sword;
        this.pistol = player.pistol;
    }
    /*
     * Empty constructor of the class.
     */
    public Player() {
        this.id = 0;
        this.name = null;
        this.score = 0;
        this.x = 0;
        this.y = 0;
        this.bow = null;
        this.sword = null;
        this.pistol = null;
    }
    
    /**
	 * functions (a.k.a setters) to set the values of the variables in this class
	 * @param id, name, board, score, x, y, bow, pistol, sword
	 */
    
    public void setId(int id) {
        
        this.id = id ;
    }
    
    public void setName(String name) {
        
        this.name = name;
    }
    
    public void setBoard(Board board) {
        
        this.board = board;
    }
    
    public void setScore(int score) {
        
        this.score = score;
    }
    
    public void setX(int x) {
        
        this.x = x;
    }
    
    public void setY(int y) {
        
        this.y = y;
    }
    
    public void setBow(Weapon bow) {
        
        this.bow = bow;
    }
    
    public void setPistol(Weapon pistol) {
        
        this.pistol = pistol;
    }
    
    public void setSword(Weapon sword) {
        
        this.sword = sword;
    }
    
    /**
	 * functions (a.k.a getters) to get the values of the variables in this class
	 * @return id, name, board, score, x, y, bow, pistol, sword
	 */
    
    public int getId() {
        
        return id;
    }
    
    public String getName() {
        
        return name;
    }
    
    public Board getBoard() {
        
        return board;
    }
    
    public int getScore() {
        
        return score;
    }
    
    public int getX() {
        
        return x;
    }
    
    public int getY() {
        
        return y;
    }
    
    public Weapon getBow() {
        
        return bow;
    }
    
    public Weapon getPistol() {
        
        return pistol;
    }
    
    public Weapon getSword() {
        
        return sword;
    }
    
    /**
     * getRandomMove function below
     * 
     * The following function defines randomly if the player is going to move upwards ,downwards , to the left, to the right or diagonally.
     * Summing up, there are eight possible movements and the function equals to a dice randomly returning a value between one and eight.
     * NOTE THAT : If the player stands on the outside perimeter, the possible movements are less than eight.
     * @return the (x,y) coordinates of the player's next position
     */
    public int[] getRandomMove(){
        
        int[] coordinates = new int[2];
        /**
         * An array with the previous coordinates is needed to ensure that the player doesn't stay in his previous position.
         */
        int[] previousCoordinates = new int[2];
        
        previousCoordinates[0] = getX();
        previousCoordinates[1] = getY();
        
        do {
        	/**
             * In case the player doesn't stand on the outside perimeter.
             */
            if( !(x == ( board.getN()/2) || x == (-( board.getN()/2))) ) {
                
                coordinates[0] = x + random.nextInt(3) - 1;
                
                if(coordinates[0] == 0 && previousCoordinates[0] == 1) 
                    coordinates[0] = -1;
                else if(coordinates[0] == 0 && previousCoordinates[0] == -1)
                    coordinates[0] = 1;
            }
            /**
             * In case the player stands on the outside right perimeter. Here the possible movements are less than eight.
             */
            else if( x == ( board.getN()/2)){
                do {
                    coordinates[0] = x + random.nextInt(2) - 1;
                }while(coordinates[0] == 0); // needless but ok
            }
            /**
             * In case the player stands on the outside left perimeter. Here the possible movements are less than eight.
             */
            else if(x == -( board.getN()/2)){
                do {
                    coordinates[0] = x + random.nextInt(2);
                }while(coordinates[0] == 0); //  needless but ok
            }   
            /**
             * In case the player doesn't stand on the outside perimeter.
             */
            if( !(y == (board.getM()/2) || y == (-(board.getM()/2))) ) {
                
                coordinates[1] = y + random.nextInt(3) - 1; 
                if(coordinates[1] == 0 && previousCoordinates[1] == -1)
                    coordinates[1] = 1;
                else if(coordinates[1] == 0 && previousCoordinates[1] == 1)
                    coordinates[1] = -1;
            }
            /**
             * In case the player stands on the outside upper perimeter. Here the possible movements are less than eight.
             */
            else if( y == (board.getM()/2)) {
                do {
                    coordinates[1] = y + random.nextInt(2) - 1;
                }while(coordinates[1] == 0);    // needless as well
            }
            /**
             * In case the player stands on the outside down perimeter. Here the possible movements are less than eight.
             */
            else if( y == - (board.getM()/2) ) {
                do {
                    coordinates[1] = y + random.nextInt(2);
                }while( coordinates[1] == 0);  //needless as well
            }
        }while(previousCoordinates[0] == coordinates[0] && previousCoordinates[1] == coordinates[1]);
        
        return coordinates;
    }
    
    
    /**
     * move function below
     * This function uses the returning array of function getRandomMove to define the player's next position and then executes the movement.
     * Therefore, a message is printed if a player falls in a trap, gets a weapon or eats food.
     * @return informationArray an array containing all the information about the player's movement and what he came across while moving.
     */
    
    public int[] move() {
    	
    int [] informationArray ;
    int[] newPosition = new int [2];
    newPosition = getRandomMove();
    setX(newPosition[0]);
    setY(newPosition[1]);
    /**
     * Below, variables that count weapons, food and traps, which the player came across during his movement so as to be used in the returning array, are declared.
     */
    int weaponNumber = 0;
    int foodNumber = 0;
    int trapNumber = 0;
    /**
     * In the following for loops, we check all the objects on the board to figure out the effect of the player's movement.
     */
   
    for(int i = 0; i < board.getW(); i++) {
        if(board.weapons[i].getX() == newPosition[0] && board.weapons[i].getY() == newPosition[1] && board.weapons[i].getPlayerId() == id) {
            weaponNumber++;
            System.out.println("Player " + name + " got a weapon of type" + board.weapons[i].getType());
            /**
             * When, a weapon is acquired its coordinates become (0,0).
             */
            board.weapons[i].setX(0);
            board.weapons[i].setY(0);
            /**
             * Below, the weapon is given to its owner.
             */
            if (board.weapons[i].getType() == "Bow") {
                
                System.out.println("I, " + name + " got a BOW!!!");
                setBow(board.weapons[i]);
                }
            else if(board.weapons[i].getType() == "Sword") {
                
            	System.out.println("I, " + name + " got a SWORD!!!");
                
                setSword(board.weapons[i]);
                
            }
            else if(board.weapons[i].getType() == "Pistol") {
                setPistol(board.weapons[i]);
                
                System.out.println("I, " + name + " got a PISTOL!!!");
            }
            
        }
    }
    for(int i = 0; i < board.getT(); i++) {
        if(board.traps[i].getX() == newPosition[0] && board.traps[i].getY() == newPosition[1]) {
            trapNumber++;
            System.out.println("Player " + name + " fell in a trap!");
            if(board.traps[i].getType() == "Animal") {
                if(bow == null)
                    score += board.traps[i].getPoints();
                else
                	System.out.println("Player " + name + " killed the animal with her bow!!!");
            }
           
            else if(board.traps[i].getType() == "Rope") {
                if(sword == null)
                    score += board.traps[i].getPoints();
                else
                	System.out.println("Player " + name + " cut the rope with her sword!!!");
            }
        }
    }
    
    
    for(int i = 0; i < board.getF(); i++) {
        if(board.food[i].getX() == newPosition[0] && board.food[i].getY() == newPosition[1]) {
            foodNumber++;
            System.out.println("Player " + name + " ate something!");
            score += board.food[i].getPoints();
            /**
             * When a food object is eaten, its coordinates become (0,0)
             */
            board.food[i].setX(0);
            board.food[i].setY(0);
        }
    }
    
    
    informationArray = new int [] {newPosition[0],newPosition[1],weaponNumber,trapNumber,foodNumber};
    
    return informationArray;
    
    
}
    /**
     * printMe function below 
     * We consider the addition of this function very helpful, as it summarizes the player's state as a whole.
     */
    void printMe() {
        System.out.println("HELLO! My id is: " + id +"\n" + "My name is: " + name);
        System.out.println("My position is (" + x + "," + y +")"); 
        System.out.println("My score is:" + score);
        if(bow == null)
            System.out.println("I don't have a bow");
        else if(bow != null)
            System.out.println("My bow is: " + bow.getId());
        if(sword == null)
            System.out.println("I don't have a sword");
        else if(sword != null)
            System.out.println("My sword is: " + sword.getId());
        if(pistol == null)
            System.out.println("I don't have a pistol");
        else if(pistol != null)
            System.out.println("My pistol is: " + pistol.getId());
    }
    
}