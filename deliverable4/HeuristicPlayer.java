/**
 * Class denoting the heuristic player.
 */ 
package deliverable4;
import java.util.*;

import java.awt.Image;
import java.lang.Math; 
public class HeuristicPlayer extends Player{
    
    ArrayList<Integer[]> path = new ArrayList<Integer[]>();
    int moves[][] = {
            {0,-1},
            {1,-1},
            {1,0},
            {1,1},
            {0,1},
            {-1,1},
            {-1,0},
            {-1,-1},
    };
    
    
    
    static int r;
    
    /** 
     * Below,some variables that we considered useful are declared and initialized.
     */
    static int diagonalMove1 = 1;
    static int diagonalMove2 = 1;
    static int movesTowardsPlayer2 = 1;
    static int movesTowardsPlayer1 = 1;
    int thisRoundsDice;
    
    int weaponNumber;
    int foodNumber;
    int trapNumber;
    
    /**
     * Constructor of the class; initializes the identity, name, x and y coordinates on the board and the board
     * of the game by calling the constructor of the base class.At the beginning of the game the player doesn't possess any weapon. Variable r is also initialized to indicate the player's
     * visibility field, on the board, to process its information.
     * @param id
     * @param name
     * @param x
     * @param y
     * @param board
     * @param r the player's visibility field
     */
    
    public HeuristicPlayer(int id, String name, int x, int y, Board board, int r ) {
        super( id, name, x, y, board);
        HeuristicPlayer.r = r;
    }
    
    /**
     * Constructor of the class; initializes the identity, name, x and y coordinates on the board and the board
     * of the game by a player given as an argument and by calling the constructor of the base class.
     * At the beginning of the game the player doesn't possess any weapon.
     * @param player
     */
    public HeuristicPlayer(Player player, int r) {
        super(player);
        HeuristicPlayer.r = r;
    }
    
    /**
     * Empty constructor of the class.
     */
    public HeuristicPlayer() {
        super();
    }
    
    /**
     * Firstly, we check if their coordinates in x axis or y axis have different sign, 
     * if that is true, the variable diffXsingn (or diffYsign) is set to 1 and is subtracted from their distance in x or y axis. 
     * @param p the opponent
     * @return distance between the two players in float   
     */
    
    public float  playersDistance(Player  p) {
        
       int diffXsingn;
     
        int diffYsign;
        
        if(x * p.getX() < 0)
        	
        	diffXsingn = 1; 
        else
        	diffXsingn = 0;
        
        if(y * p.getY() < 0)
        	diffYsign = 1;
        else
        	diffYsign = 0;
             
        float distance = (float)Math.sqrt(Math.pow(Math.abs(x - p.getX()) - diffXsingn, 2) + Math.pow(Math.abs(y - p.getY()) - diffYsign, 2));
         
         int distanceInTiles = ( (Math.abs( (x + 0 ) - p.getX()) - diffXsingn ) <  (Math.abs(y - p.getY() ) - diffYsign ) ) ? ( Math.abs(y - p.getY() ) - diffYsign ) : ( Math.abs(x - p.getX() ) - diffXsingn );
         
       
    	/**
    	 * If the calculated distance is more than the radius r ( the player's visibility field), which is not allowed in the game, the function returns - 1
    	 */
         if (distanceInTiles <= r)
             return distance;
         else
             return -1;
     }





    /**
     * Function returning the distance between the players in tiles!
     * We handle zero the same way we did in playersDistance. 
     * @param p
     * @return distanceInTiles
     */
    
    public int tilesDistance( Player p )
    {
    	
    	 int diffXsign;
         
         int diffYsign;
         
         if(x * p.getX() < 0)
         	
        	 diffXsign = 1; 
         else
        	 diffXsign = 0;
         
         if(y * p.getY() < 0)
        	 diffYsign = 1;
         else
        	 diffYsign = 0;
    	int distanceInTiles = ( (Math.abs( x - p.getX()) - diffXsign ) <  (Math.abs(y - p.getY() ) - diffYsign ) ) ? ( Math.abs(y - p.getY() ) - diffYsign ) : ( Math.abs(x - p.getX() ) - diffXsign );
    	
    	 if (distanceInTiles <= r)
             return distanceInTiles;
         else
             return -1;
    
    
    }
    
    
    /**
     *Function returning the player's distance from zero. This helps the player stay relatively close to the central area of 
     * @return distance from ( 0 , 0 )
     */
    public float  playersFromZero() {
        float distance = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            return distance;
            
    }

    
    
    /**
     * Function similar to playersDistance, but for weapons.
     * 
     * @param w the weapon we want distance from
     * @param board
     * @return distance between heuristic player and the weapon argument
     */

public int weaponDistance(Weapon w, Board board) {
        
        int diffXsign;
        
        int diffYsign;
        
        if(x * w.getX() < 0)
        	diffXsign = 1;
       else
    	   diffXsign = 0;
       
       if(y * w.getY() < 0)
    	   diffYsign = 1;
       else
    	   diffYsign = 0;
  
        int distanceInTiles = ((Math.abs(x - w.getX() )- diffXsign)) < ((Math.abs(y - w.getY() )- diffYsign)) ? ((Math.abs(y - w.getY())- diffYsign) ): ((Math.abs(x - w.getX()) - diffXsign));
        
      
        if( r >= distanceInTiles) {
        	return distanceInTiles;
        }
        else
            return -1;
    }


    /**
     * In the following function we evaluate the move provided by the dice by increasing or decreasing moveScore variable.
     * The strategy is being explained progressively.Our main goal sums up to acquiring the pistol and chasing the opponent.
     * @param dice that indicates the particular move 
     * @param p the opponent
     * @return moceScore the coefficient of the particular move
     */
public double evaluate(int dice, Player p) {
        
	
	   /**
       *  Initially, movesScore is set to 0.
	   */
       double moveScore = 0;
       
       /**
        * At first, heuristic player moves diagonally so as to reach the central area with the weapons and food.
        * NOTE THAT: We don't mind if we MIGHT fall in one trap in case it is positioned on our path to reach the central area.
        * For player 1, this might happen on the upper left corner of the central area, and for player 2 on the lower right  corner of the central area. 
        */
       
       if (diagonalMove1 <= 7 && diagonalMove2 <= 6) {
           
            if((x < 0) && (y < 0) && (x < -board.getTrapInt()) && (y < - board.getTrapInt())) {
                if(dice == 4) {
                    moveScore += 100.0;
                    diagonalMove1++;
                    return moveScore;
                }
                else 
                    return -100.0;
  
            }
            else if((x > 0) && (y > 0) && (x > board.getTrapInt()) && (y > board.getTrapInt())) {
                if(dice == 8) {
                    moveScore += 100.0;
                    diagonalMove2++;
                    return moveScore;
                }
                else 
                    return -100.0;
                
            }
            
            
       }
       
      
       
       
       /**
        * If the heuristic player has the pistol, he moves diagonally to find his opponent assuming that his opponent hasn't moved much from 
        * his initial position as he moves randomly, and therefore it didn't take long for him (the heuristic player) to acquire the pistol.
        * This function loses its effectiveness when the opponent is not a random player. So, the following code is executed only if the 
        * opponent is a random player.
        */
       if(p.getName()=="Stavroula Siachalou (r)" || p.getName()=="Maria Kotouza (r)") {
	       if ( movesTowardsPlayer1 <= 5 && movesTowardsPlayer2 <= 5) {
	    	   
	    	   if( id == 1 && pistol != null) {
	    		   if( dice == 4) {
	    			   moveScore  += 1000.0;
	    			   movesTowardsPlayer2++;
	    			   return moveScore;
	    		   }
	    		   else 
	    			   return - 100.0;
	    	   }
	    	   
	    	   if( id == 2 && pistol != null) {
	    		   if ( dice == 8 ) {
	    			   moveScore += 1000.0;
	    			   movesTowardsPlayer2 ++;
	    			   return moveScore;
	    		   }
	    		   
	    		   else
	    			   return - 100.0;
	    	   }
	    	   
	       }
       }
      
       /**
        * Below, some distance values are being saved, so as to use them in comparisons with the potential next moves.
        */
       float initZeroDistance = playersFromZero();
       int pistol1InitDist = weaponDistance(board.weapons[2],board);
       int pistol2InitDist = weaponDistance(board.weapons[3],board);
       int playersInitDist = tilesDistance(p);
       
       /**
        * Below, the dice's move is temporarily set as the player's position in order to determine how effective as a move is.
        * Therefore, the player's current position is being kept, and being set again in the end of the function.
        */
       
       int[] previousCoordinates = new int[2];
       int[] newPosition = new int[2];
       previousCoordinates[0] = getX();
       previousCoordinates[1] = getY();
       
          
               
       newPosition[0] =  getX() + moves[dice - 1][0];
               
       newPosition[1] = getY() + moves[dice - 1][1];
               
       if(newPosition[0] == 0 && previousCoordinates[0] == 1) 
    	   newPosition[0] = -1;
       else if(newPosition[0] == 0 && previousCoordinates[0] == -1)
    	   newPosition[0] = 1;
           	
       if(newPosition[1] == 0 && previousCoordinates[1] == -1)
    	   newPosition[1] = 1;
       else if(newPosition[1] == 0 && previousCoordinates[1] == 1)
           newPosition[1] = -1;
               
       setX(newPosition[0]);
       setY(newPosition[1]);
       
       
       /**
        * If the move is going to be out of bounds, moveScore is decreased by - 50000.
        * This function works for whoever player calls it.
        */
       if( (getX() > board.getN() / 2) || (getX() < - board.getN() / 2) || (getY() > board.getM() / 2) || (getY() < -board.getM() / 2) ) {
        	  moveScore -= 50000.0;
           }
       
       
       /**
        * Generally, we prefer staying at the centre of the board.
        */
       float newZeroDistance = playersFromZero();
       
       if( initZeroDistance > newZeroDistance ) {
           moveScore += 0.5;
       }
       
       /**
        * After scanning all the weapons on the board, if the weapons's playerId is the same as the player's id, the weapon is not positioned
        * more than r (set to 3) tiles away from the player and if the weapon hasn't been acquired before, moveScore is increased by 100.
        */
       for(int i = 0; i < board.getW(); i ++) {
    	   if( getX() == board.weapons[i].getX() && getY() == board.weapons[i].getY() ) {
	    	  if((board.weapons[i].getPlayerId() == id) && (weaponDistance(board.weapons[i],board ) >= 0 )  && board.weapons[i].getX() != 0 && board.weapons[i].getY() != 0 ) {
	               moveScore += 100.0;
	               if(board.weapons[i].getType()=="Pistol")
	            	   moveScore +=500;
	               
	           }
    	   }
       }
       
        /**
         * If food or a trap is positioned where the dice indicates, moveScore is increased by the points of the particular food, or
         * decreased by the points of the particular trap.
         */
       for(int i = 0; i < board.getF(); i++) {
           if( getX() == board.food[i].getX() && getY() == board.food[i].getY() ) {
        	   
               moveScore += (float)board.food[i].getPoints();
           }
       }
       
       for(int i = 0; i < board.getT(); i++) {
           if( getX() == board.traps[i].getX() && getY() == board.traps[i].getY() ) {
        	  
               moveScore += (float)board.traps[i].getPoints();
           }
       }
       
       /**
        * In case the player that called the function doesn't have a gun and the distance between him and the opponent is r ( r = 3 ) or less, the move gets a - 500 coefficient.
        */
       if( tilesDistance(p) <= 2 && pistol == null && tilesDistance(p) > 0) {
           moveScore -= 500.0;
       }
       
       /**
        * In case the player that called the function does have a gun and the distance between him and the opponent is r ( r = 3 ) or less, the move gets a + 700 coefficient. 
        */
       if( tilesDistance(p) < 2 && pistol != null && tilesDistance(p) > 0) {
           moveScore += 700.0;
       }
       
      /**
       * If player 1 or 2 respectively don't have the pistol, and it is positioned in r or less tiles away from them, moveScore is increased by 500, 
       * as we consider acquire the pistol our top priority. Moreover, if with the next move the player's distance from the weapon has decreased,
       * moveScore is increased additionally by 150.
       */
       if( (id == 1) && (weaponDistance(board.weapons[2],board) >= 0) && pistol == null) {
    	   moveScore += 500.0;
    	   if(pistol1InitDist > weaponDistance(board.weapons[2],board) && (pistol1InitDist> 0) && weaponDistance(board.weapons[2], board) >= 0) {
    		   moveScore += 150.0;
    		   }
       }
       
       if( (id == 2) && (weaponDistance(board.weapons[3],board) >= 0) && pistol == null) {   
    	   moveScore += 500.0;
    	   if(pistol2InitDist > weaponDistance(board.weapons[3],board) && (pistol2InitDist> 0) && weaponDistance(board.weapons[3], board) >= 0) {
    		   moveScore += 150.0;
    		   }
       }
       
       /**
        * If the player has the pistol and the distance between the players is decreasing, the player that called the function moves towards 
        * his opponent to kill him.
        */
       if( pistol != null && ( tilesDistance(p) < playersInitDist ) && playersInitDist > 0 && tilesDistance(p) > 0) {
    	   moveScore += 1000.0;
       }
       
       /**
        * If the player doesn't possess the pistol but his opponent does, we make sure that the distance is kept less than r tiles.
        */
       if( pistol == null && p.getPistol() != null && tilesDistance(p) >= 0) {
    	   moveScore -= 1000.0;
       }
       
       
       setX(previousCoordinates[0]);
       setY(previousCoordinates[1]);
        return moveScore;
    }



/**
 * In the following function, it is checked if the distance between the two players is less than argument d, so as the player given as the first 
 * argument can kill his opponent should he have a pistol.
 * @param player1 player that called the function
 * @param player2 his opponent
 * @param d that defines the distance in which the player can kill its opponent with the pistol
 * @return true if the opponent has been killed, otherwise returns false
 */
    public static boolean kill(Player player1,Player player2, float d) {
    	if( new HeuristicPlayer(player1 , r).playersDistance(player2) <= d && new HeuristicPlayer(player1, r).playersDistance(player2) >= 0 ) {
            if( player1.getPistol() != null )
                return true;
        }
        return false;
    }
    
    
    
/**
 * The following function uses the returning values of function evaluate for each of the eight moves and decides which to execute based on 
 * choosing the one with the biggest coefficient.For that matter, all eight moves are saved in a map, possibleMoves.Therefore, just as move 
 * function in class Player, a message is printed if a player falls in a trap, gets a weapon or eats food. 
 * The function has been updated in order to support graphics class.
 * @param p the opponent
 * @param g
 * @return newPosition array with the player's new x and y coordinates
 */
    
 public int[] Move(Player p,graphics g){
        
        trapNumber = -1;
        foodNumber = -1;
        weaponNumber = -1;
        
        
        Map<Integer,Double> possibleMoves = new HashMap<Integer,Double>();
        for( int i = 1; i < 9; i++) {
            possibleMoves.put( i , evaluate( i, p ));
        }
        double max = -1000000.0;
        int positionInMap = 0;
        for(int i = 1; i < 9; i++ ) {
            if(possibleMoves.get(i) > max ) {
                max = possibleMoves.get(i);
                positionInMap = i;
            }
        }
        int[] newPosition = new int[2];
        Integer [] informationArray ;
        
        /**
         * An array with the previous coordinates is needed to ensure that the player doesn't stay in his previous position.
         */
        int[] previousCoordinates = new int[2];
        
        previousCoordinates[0] = getX();
        previousCoordinates[1] = getY();
        // this is for graphics
        graphics.eraseIcon(previousCoordinates[0],previousCoordinates[1],g);
        
        for(int i = 1; i < 9; i++) {
            
            if( i == positionInMap) {
                
           
            	
            	newPosition[0] =  getX() + moves[i - 1][0];
                newPosition[1] = getY() + moves[i - 1][1];
              
                
                if(newPosition[0] == 0 && previousCoordinates[0] == 1) 
                    newPosition[0] = -1;
                else if(newPosition[0] == 0 && previousCoordinates[0] == -1)
                    newPosition[0] = 1;
                
                if(newPosition[1] == 0 && previousCoordinates[1] == -1)
                    newPosition[1] = 1;
                else if(newPosition[1] == 0 && previousCoordinates[1] == 1)
                    newPosition[1] = -1;
                
                setX(newPosition[0]);
                setY(newPosition[1]);
                
                thisRoundsDice = i;
            }
        }
        /**
         * Below, variables that count weapons, food and traps, which the player came across during his movement so as to be used in the returning array, are declared.
         */
        /**
         * In the following for loops, we check all the objects on the board to figure out the effect of the player's movement.
         */
        int prevScore = score;
        
        for(int i = 0; i < board.getW(); i++) {
            if(board.weapons[i].getX() == newPosition[0] && board.weapons[i].getY() == newPosition[1] && board.weapons[i].getPlayerId() == id) {
                System.out.println("Player " + name + " got a weapon of type" + board.weapons[i].getType());
                weaponNumber = i;
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
                trapNumber = i;
                System.out.println("Player " + name + " fell in a trap!");
                if(board.traps[i].getType() == "Animal") {
                    if(bow == null) {
                        score += board.traps[i].getPoints();
                        
                        //this is for graphics
                    	g.updatePlayerMoveScore(this,board.traps[i].getPoints());
                    }
                    else
                        System.out.println("Player " + name + " killed the animal with her bow!!!");
                }
               
                else if(board.traps[i].getType() == "Rope") {
                    if(sword == null) {
                        score += board.traps[i].getPoints();
                    
                    	//this is for graphics
                    g.updatePlayerMoveScore(this,board.traps[i].getPoints());
                }
                    else
                        System.out.println("Player " + name + " cut the rope with her sword!!!");
                }
            }
        }
        for(int i = 0; i < board.getF(); i++) {
            if(board.food[i].getX() == newPosition[0] && board.food[i].getY() == newPosition[1]) {
                foodNumber = i;
                System.out.println("Player " + name + " ate something!!!");
                score += board.food[i].getPoints();
                //this is for graphics
                g.updatePlayerMoveScore(this,board.food[i].getPoints());
                /**
                 * When a food object is eaten, its coordinates become (0,0)
                 */
                board.food[i].setX(0);
                board.food[i].setY(0);
            }
        }
        
      //this is for graphics
        if(prevScore == score) {
        	
        	g.updatePlayerMoveScore(this,0);
        	
        }
        
        informationArray = new Integer [] {newPosition[0],newPosition[1],weaponNumber,trapNumber,foodNumber};
        path.add(informationArray);
        
        graphics.putIcon(newPosition[0], newPosition[1], this, g);
        
        graphics.putIcon(newPosition[0], newPosition[1], this, g);
        
        return newPosition;
    }

/**
 * The following function prints what player accomplished in the last round.Variable thisRoundsDice gets the value
 * of dice in function move and prints it here. Variable weaponNumber indicates the index of the weapon that the player acquired
 * in the last round and uses it here to print its data.(If they are less than zero he didn't get any objects of this type in the last round!)
 * (if they are less than zero we didn't get any objects of this type in the last round!).Therefore, the same happens for food and traps.
 * Then the players' score is printed and variable roudActually,which counts rounds inside heuristicPlayer class, is increased.
 * Finally, some data same as in PrintMe function are printed.
 */


 public void statistics(int Round){
	    
     System.out.println("HELLO! My id is: " + id +"\n" + "My name is: " + name);
     System.out.println("Dice for this Round: " + thisRoundsDice );
     System.out.println("My position is (" + x + "," + y + ")"); 
     //prints all the information about the weapons
     if(path.get(Round - 1)[2] >= 0)
         System.out.println("I got a weapon of type: " + board.weapons[weaponNumber].getType() );
     else
         System.out.println("I didn't get any weapon");
     
     //prints all the information about the traps
     if(path.get(Round - 1)[3] >= 0)
         System.out.println("I fell in a trap and I lost points : " + board.traps[trapNumber].getPoints() );
     else
         System.out.println("I didn't fall in any trap");
     
     //prints all the information about food
     if(path.get(Round - 1)[4] >= 0)
         System.out.println("I ate food and I gained points: " + board.food[foodNumber].getPoints() );
     else
         System.out.println("I didn't eat any food");
     
     //prints score
     System.out.println("My score is:" + score );
     
     
     // same as printMe of class Player stuff
     if(bow == null)
         System.out.println("I don't have a bow");
     else if(bow != null)
         System.out.println("My bow is:" + bow.getId());
     if(sword == null)
         System.out.println("I don't have a sword");
     else if(sword != null)
         System.out.println("My sword is:" + sword.getId());
     if(pistol == null)
         System.out.println("I don't have a pistol");
     else if(pistol != null)
         System.out.println("My pistol is:" + pistol.getId());
  }
    }
