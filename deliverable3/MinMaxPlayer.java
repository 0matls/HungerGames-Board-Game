/**
 * Class denoting the in min - max player.
 */ 
package deliverable3;
import java.util.*;



import java.lang.Math; 
public class MinMaxPlayer extends Player{
    
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
    
    
   int round;
    
    /** 
     * Below,some variables that we considered useful are declared and initialized.
     */
    int thisRoundsDice;
    static int roundActually = 0;
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
     */
    
    public MinMaxPlayer(int id, String name, int x, int y, Board board ) {
        super( id, name, x, y, board);
    }
    
    /**
     * Constructor of the class; initializes the identity, name, x and y coordinates on the board and the board
     * of the game by a player given as an argument and by calling the constructor of the base class.
     * At the beginning of the game the player doesn't possess any weapon.
     * @param player
     */
    public MinMaxPlayer(Player player, Board board) {
        super(player);
        this.board = board;
    }
    
    public MinMaxPlayer(Player player) {
        super(player);
    }
    
    /**
     * Empty constructor of the class.
     */
    public MinMaxPlayer() {
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
       
             return distance;
        
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
    	
    	 
             return distanceInTiles;
    
    }
    
    
    /**
     *Function returning the player's ( that its coordinates are given as an argument ) distance from zero in float. 
     *This helps the player stay relatively close to the central area of
     * @param xPos, x position to calculate the distance from
     * @param yPos, y position to calculate the distance from
     * @return distance from ( 0 , 0 )
     */
    public float  playersFromZero(int xPos, int yPos) {
        float distance = (float)Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
            return distance;
            
    }

    
    
    /**
     * Function similar to playersDistance, but for weapons.
     * 
     * @param w the weapon we want distance from
     * @param board
     * @param xPos, x position to calculate the distance from
     * @param yPos, y position to calculate the distance from
     * @return distance between heuristic player and the weapon argument
     */

    public int weaponDistance(Weapon w, Board board, int xPos, int yPos) {
        
        int diffXsign;
         
        int diffYsign;
         
        if(xPos * w.getX() < 0)
         	diffXsign = 1;
        else
     	   diffXsign = 0;
        
        if(yPos * w.getY() < 0)
     
     	   diffYsign = 1;
        else
     	   diffYsign = 0;
   
         int distanceInTiles = ((Math.abs(xPos - w.getX() )- diffXsign)) < ((Math.abs(yPos - w.getY() )- diffYsign)) ? ((Math.abs(yPos - w.getY())- diffYsign) ): ((Math.abs(xPos - w.getX()) - diffXsign));
         
       
         return distanceInTiles;
        
     }



    /**
     * In the following function we evaluate the move provided by the dice by increasing or decreasing moveScore variable.
     * The strategy is being explained progressively.Our main goal sums up to acquiring the pistol and chasing the opponent.
     * This function works for our player, player 1 thats uses the Min - Max Algorithm
     * @param dice that indicates the particular move 
     * @param p the opponent
     * @param xPos the player's ( that called the function ) x coordinate
     * @param yPos the player's ( that called the function ) y coordinate
     * @return moceScore the coefficient of the particular move
     */
public double evaluate(int dice, Player p, int xPos , int yPos) {
        
	
	 /**
     *  Initially, movesScore is set to 0.
	 */
     double moveScore = 0;
     
     /**
      * At first, the player moves diagonally so as to reach the central area with the weapons and food.
      * NOTE THAT: We don't mind if we MIGHT fall in one trap in case it is positioned on our path to reach the central area.In the worst case our points we be reduced by 10,
      * so we will still have 5 ( > 0 ), thus we don't lose.
      * For both players, this might happen on the lower right corner of the central area. 
      * To do that, we need the variable round from class game, which is passed to MinMaxPlayer class via getNextMove function, because the evaluate function is being called
      * many times for multiple testing and for that matter we cannot use static variables.
      */
     
     if (round < 7) {
         
           if( (xPos > board.getTrapInt()) && (yPos > board.getTrapInt())) {
              if(dice == 8) {
                  moveScore += 100.0;
                  return moveScore;
              }
              else 
                  return -100.0;
          }
          
          
     }
     
     
       /**
        * Below, some distance values are being saved, so as to use them in comparisons with the potential next moves.
        */
       float initZeroDistance = playersFromZero(xPos, yPos);
       int pistol1InitDist = weaponDistance(board.weapons[2],board,xPos, yPos);
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
       xPos= newPosition[0];
       yPos = newPosition[1];
       
       /**
        * If the move is going to be out of bounds, moveScore is decreased by - 50000.
        */
       if( (getX() > board.getN() / 2) || (getX() < - board.getN() / 2) || (getY() > board.getM() / 2) || (getY() < -board.getM() / 2) ) {
        	  moveScore -= 50000.0;
           }
       /**
        * Generally, we prefer staying at the centre of the board.
        */
       float newZeroDistance = playersFromZero(xPos, yPos);
       
       if( initZeroDistance > newZeroDistance ) {
           moveScore += 0.5;
       }
       
       /**
        * After scanning all the weapons on the board, if the weapons's playerId is the same as the player's id,we opt to get closer to the weapon if
        * it hasn't been acquired before, and moveScore is increased by 100.
        */
       for(int i = 0; i < board.getW(); i ++) {
    	  if((board.weapons[i].getPlayerId() == id)  && board.weapons[i].getX() != 0 && board.weapons[i].getY() != 0 ) {
               moveScore += 100.0;
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
        * In case the player 1 ( us ) doesn't have a gun and the distance between him and the opponent is 3 or less, the move gets a - 500 coefficient.
        */
       if( tilesDistance(p) <= 3 && pistol == null ) {
           moveScore -= 500.0;
       }
       
       /**
        * In case the player that called the function does have a gun and the distance between him and the opponent is 3 or less, the move gets a + 700 coefficient. 
        */
       if( tilesDistance(p) <= 3 && pistol != null ) {
           moveScore += 700.0;
       }
       
       /**
        * If player 1 doesn't have the pistol, moveScore is increased, so as to acquire the pistol.
        */
       if( (id == 1)  && pistol == null) {
    	   moveScore += 500.0;
    	   if(pistol1InitDist > weaponDistance(board.weapons[2],board,xPos, yPos) && (pistol1InitDist> 0) && weaponDistance(board.weapons[2], board,xPos, yPos) >= 0) {
    		   moveScore += 150.0;
    		   }
       }
       
       /**
        * If the player has the pistol and the distance between the players is decreasing, the player that called the function moves towards 
        * his opponent to kill him.
        */
       if( pistol != null && ( tilesDistance(p) < playersInitDist ) ) {
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


public double evaluateOp(int dice, Player p, int xPos , int yPos) {
    
	
	 /**
    *  Initially, movesScore is set to 0.
	   */
    double moveScore = 0;
    
    /**
     * At first, the player moves diagonally so as to reach the central area with the weapons and food.
     * NOTE THAT: We don't mind if we MIGHT fall in one trap in case it is positioned on our path to reach the central area.In the worst case our points we be reduced by 10,
     * so we will still have 5 ( > 0 ), thus we don't lose.
     * For both players, this might happen on the lower right corner of the central area. 
     * To do that, we need the variable round from class game, which is passed to MinMaxPlayer class via getNextMove function, because the evaluate function is being called
     * many times for multiple testing and for that matter we cannot use static variables.
     */
    
    if (round < 7) {
        
          if( (xPos > board.getTrapInt()) && (yPos > board.getTrapInt())) {
             if(dice == 8) {
                 moveScore += 100.0;
                 return moveScore;
             }
             else 
                 return -100.0;
         }
         
         
    }
    
      /**
       * Below, some distance values are being saved, so as to use them in comparisons with the potential next moves.
       */
      float initZeroDistance = playersFromZero(xPos, yPos);
      int pistol2InitDist = weaponDistance(board.weapons[3],board, xPos,yPos);
      int playersInitDist = tilesDistance(p);
      
      /**
       * Below, the dice's move is temporarily set as the player's position in order to determine how effective as a move is.
       * Therefore, the player's current position is being kept, and being set again in the end of the function.
       */
      
      int[] previousCoordinates = new int[2];
      int[] newPosition = new int[2];
      previousCoordinates[0] = p.getX();
      previousCoordinates[1] = p.getY();
      
         
              
      newPosition[0] =  p.getX() + moves[dice - 1][0];
              
      newPosition[1] = p.getY() + moves[dice - 1][1];
              
      if(newPosition[0] == 0 && previousCoordinates[0] == 1) 
   	   newPosition[0] = -1;
      else if(newPosition[0] == 0 && previousCoordinates[0] == -1)
   	   newPosition[0] = 1;
          	
      if(newPosition[1] == 0 && previousCoordinates[1] == -1)
   	   newPosition[1] = 1;
      else if(newPosition[1] == 0 && previousCoordinates[1] == 1)
          newPosition[1] = -1;
              
      p.setX(newPosition[0]);
      p.setY(newPosition[1]);
      xPos= newPosition[0];
      yPos = newPosition[1];
      
      /**
       * If the move is going to be out of bounds, moveScore is decreased by - 50000.
       */
      if( (p.getX() > board.getN() / 2) || (p.getX() < - board.getN() / 2) || (p.getY() > board.getM() / 2) || (p.getY() < -board.getM() / 2) ) {
       	  moveScore -= 50000.0;
          }
      /**
       * Generally, we prefer staying at the centre of the board.
       */
      float newZeroDistance = playersFromZero(xPos, yPos);
      
      if( initZeroDistance > newZeroDistance ) {
          moveScore += 0.5;
      }
      
      /**
       * After scanning all the weapons on the board, if the weapons's playerId is the same as the player's id, the weapon is not positioned
       * more than r (set to 3) tiles away from the player and if the weapon hasn't been acquired before, moveScore is increased by 100.
       */
      for(int i = 0; i < board.getW(); i ++) {
   	  if((board.weapons[i].getPlayerId() == p.getId())   && board.weapons[i].getX() != 0 && board.weapons[i].getY() != 0 ) {
              moveScore += 100.0;
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
       * In case the player that called the function doesn't have a gun and the distance between him and the opponent is 3 or less, the move gets a - 500 coefficient.
       */
      if( tilesDistance(p) <= 3 && p.getPistol() == null ) {
          moveScore -= 500.0;
      }
      
      /**
       * In case the player that called the function does have a gun and the distance between him and the opponent is 3 or less, the move gets a + 700 coefficient. 
       */
      if( tilesDistance(p) <= 3 && p.getPistol() != null ) {
          moveScore += 700.0;
      }
      
      /**
       * If player 2 doesn't have the pistol, moveScore is increased, so as to acquire the pistol.
       */
       if( (id == 2)  && pistol == null) {
    	   moveScore += 500.0;
    	   if(pistol2InitDist > weaponDistance(board.weapons[3],board,xPos, yPos) && (pistol2InitDist> 0) && weaponDistance(board.weapons[2], board,xPos, yPos) >= 0) {
    		   moveScore += 150.0;
    		   }
       }
      
      /**
       * If the player has the pistol and the distance between the players is decreasing, the player that called the function moves towards 
       * his opponent to kill him.
       */
      if( p.getPistol() != null && ( tilesDistance(p) < playersInitDist ) ) {
   	   moveScore += 1000.0;
      }
      
      /**
       * If the player doesn't possess the pistol but his opponent does, we make sure that the distance is kept less than r tiles.
       */
      if( p.getPistol() == null &&  pistol != null && tilesDistance(p) >= 0) {
   	   moveScore -= 1000.0;
      }
      
      
      p.setX(previousCoordinates[0]);
      p.setY(previousCoordinates[1]);
      
       return moveScore;
   }
	/**
	 * The following function chooses the best dice possible by comparing the values on the tree following the min -  max algorithm's rules.
	 * @param root
	 * @return
	 */
	int chooseMinMaxMove(Node root) {
	    
	    ArrayList<Node> children = new ArrayList<Node>();
	    
	    children = root.getArrayList();
	    
	    int bestDice = 0;
	    
	    int childrenNumber = children.size();
	    
	    for(int i = 0; i < childrenNumber; i++) {
	        
	        double min = Double.POSITIVE_INFINITY;
	        
	        ArrayList<Node> grandchildren = children.get(i).getArrayList();
	        for(int j = 0; j < grandchildren.size(); j++) {
	            min = Math.min(min, grandchildren.get(j).getNodeEvaluation());
	        }
	        children.get(i).setNodeEvaluation(min);
	    }
	    
	    double max = Double.NEGATIVE_INFINITY;
	    
	    for (int i = 0; i < childrenNumber; i++) {
	        
	        double nodeValue = children.get(i).getNodeEvaluation();
	        
	        if(max < nodeValue) {
	            max = nodeValue;
	            bestDice = children.get(i).getNodeMove()[0];
	        }
	    }
	    return bestDice;
	    
	}
	/**
	 * The following function calculates the possible moves so as not to get off the board by returning an array 
	 * that indicates clockwise the first and the last possible move, as they are always in a row.
	 * NOTE THAT : the returning values can be grater that 8 ( max dice ).
	 * @param curX, current x coordinate
	 * @param curY, current y coordinate
	 * @return a two - element array with the first and the last move
	 */
	int [] returnPossibleMoves(int curX, int curY) {
	        
	        int caseChooser = 0;
	        int startDice, endDice;
	        int [] startToEnd = new int[2];
	        
	        if(curX == -board.getN()/2 && curY == -board.getM()/2)
	            caseChooser = 1;
	        else if(curX != board.getN()/2 && curX != -board.getN()/2 && curY == -board.getM()/2 )
	            caseChooser = 2;
	        else if(curX == board.getN()/2 && curY == -board.getM()/2) 
	            caseChooser = 3;
	        else if (curX == board.getN()/2 && curY != board.getM()/2 && curY != -board.getM()/2)
	            caseChooser = 4;
	        else if(curX == board.getN()/2 && curY == board.getM()/2)
	            caseChooser = 5;
	        else if(curX != board.getN()/2 && curX != -board.getN()/2 && curY == board.getM()/2)
	            caseChooser = 6;
	        else if(curX == -board.getN()/2 && curY == board.getM()/2)
	            caseChooser = 7;
	        else if (curX == -board.getN()/2 && curY != board.getM()/2 && curY != -board.getM()/2)
	            caseChooser = 8;
	        else if(curX != board.getN()/2 && curX != -board.getN()/2 && curY != board.getM()/2 && curY != -board.getM()/2)
	            caseChooser = 9;
	        
	        switch(caseChooser) {
	        case 1:
	            startDice = 3;
	            endDice = 5;
	            break;
	        case 2:
	            startDice = 3;
	            endDice = 7;
	            break;
	        case 3:
	            startDice = 5;
	            endDice = 7;
	            break;
	        case 4:
	            startDice = 5;
	            endDice = 9;
	            break;
	        case 5:
	            startDice = 7;
	            endDice = 9;
	            break;
	        case 6:
	            startDice = 7;
	            endDice = 11;
	        case 7:
	            startDice = 1;
	            endDice = 3;
	            break;
	        case 8:
	            startDice = 1;
	            endDice = 5;
	            break;
	        case 9:
	            startDice = 1;
	            endDice = 8;
	            break;
	        default:
	            startDice = 0;
	            endDice = 0;
	            break;
	            
	        }
	        
	        startToEnd[0] = startDice;
	        startToEnd[1] = endDice;
	       
	        return startToEnd;
	 }
	/**
	 * This function creates the opponent's subtree by following the given instructions.
	 * @param parent
	 * @param depth
	 * @param xCurrentPos
	 * @param yCurrentPos
	 * @param xOpponentCurrentPos
	 * @param yOpponentCurrentPos
	 * @param p the opponent ( player 2 ) 
	 */
	void createOpponentSubtree(Node parent, int depth, int xCurrentPos, int yCurrentPos,int xOpponentCurrentPos, int yOpponentCurrentPos, Player p) {
        int [] possibleMoves = new int [2];
        possibleMoves = returnPossibleMoves(xOpponentCurrentPos,yOpponentCurrentPos);
        for(int i = possibleMoves[0]; i < possibleMoves[1]; i++) {
            Node child2 = new Node();
            int temp = 0;
            /**
             * Because the dice has values from 1 to 8, we make sure that the returning values of returnPossibleMoves function stay 
             * within that restriction.
             */
            if( i == 8)
                temp = i;
            else if (i != 8 )
                temp = i % 8;
            child2.setNodeEvaluation(parent.getNodeEvaluation() - evaluateOp(temp,p,p.getX(),p.getY()));
            child2.setParent(parent);
            child2.setNodeDepth(2);
            int [] nodeMove = new int [3];
            nodeMove[0] = temp;
            nodeMove[1] = moves[temp - 1][0];
            nodeMove[2] = moves[temp - 1][1];
            child2.setNodeMove(nodeMove);
            parent.addChild(child2);
        
        }
    }
    /**
     * This function creates the opponent's subtree by following the given instructions.
     * @param root
     * @param depth
     * @param xCurrentPos
     * @param yCurrentPos
     * @param xOpponentCurrentPos
     * @param yOpponentCurrentPos
     * @param p the opponent ( player 2 )
     */
    void createMySubtree(Node root, int depth, int xCurrentPos, int yCurrentPos, int xOpponentCurrentPos, int yOpponentCurrentPos, Player p) {
        
        int [] possibleMoves = new int [2];
        possibleMoves = returnPossibleMoves(x,y);
        Board clonedBoard = new Board(root.getNodeboard());
        for(int i = possibleMoves[0]; i < possibleMoves[1]; i++) {
            MinMaxPlayer dublicate =  new MinMaxPlayer(3, "clonedUs", xCurrentPos, yCurrentPos, clonedBoard );
            Node child1 = new Node();
            int temp = 0;
            if( i == 8)
                temp = i;
            else if (i != 8 )
                temp = i % 8;
            child1.setNodeEvaluation(evaluate(temp,p,x,y));
            child1.setParent(root);
            child1.setNodeDepth(1);
            int [] nodeMove = new int [3];
            nodeMove[0] = temp;
            nodeMove[1] = moves[temp - 1][0];
            nodeMove[2] = moves[temp - 1][1];
            child1.setNodeMove(nodeMove);
            dublicate.move(p,temp);
            root.addChild(child1);
            createOpponentSubtree(child1, 2, dublicate.getX(), dublicate.getY(),p.getX(), p.getY(),p);
            
        }
    }
/**
 * This function is responsible of creating the tree.
 * @param p
 * @param round, game round so as to have that variable inside the class.
 * @return coord coordinates of the best move
 */
int [] getNextMove(Player p, int round) {
    	
	    this.thisRoundsDice = round;
    	Node root = new Node();
    	root.setParent(null);
    	root.setNodeDepth(0);
    	root.setNodeBoard(board);
    	root.setNodeMove(null);
    	root.setNodeEvaluation(0);
    	createMySubtree(root, 1, x, y, p.getX(),p.getY(),p );
    	int move = chooseMinMaxMove(root);
    	int [] coord = moves[move - 1];
    	move(p,move);
    	
    	return coord;
    	
    }
    
/**
 * The following function uses the returning values of function getNextMovepossibleMoves and the movement is executed. Therefore, just as move 
 * function in class Player, a message is printed if a player falls in a trap, gets a weapon or eats food. 
 * @param p the opponent
 * @param dice
 * @return newPosition array with the player's new x and y coordinates
 */

public int[] move(Player p,int dice){
       
       trapNumber = -1;
       foodNumber = -1;
       weaponNumber = -1;
       
       
       int[] newPosition = new int[2];
       Integer [] informationArray ;
       
       /**
        * An array with the previous coordinates is needed to ensure that the player doesn't stay in his previous position.
        */
       int[] previousCoordinates = new int[2];
       
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
               
               thisRoundsDice = dice ;
          
       /**
        * Below, variables that count weapons, food and traps, which the player came across during his movement so as to be used in the returning array, are declared.
        */
       /**
        * In the following for loops, we check all the objects on the board to figure out the effect of the player's movement.
        */
      
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
               foodNumber = i;
               System.out.println("Player " + name + " ate something!!!");
               score += board.food[i].getPoints();
               /**
                * When a food object is eaten, its coordinates become (0,0)
                */
               board.food[i].setX(0);
               board.food[i].setY(0);
           }
       }
       
       informationArray = new Integer [] {newPosition[0],newPosition[1],weaponNumber,trapNumber,foodNumber};
       path.add(informationArray);
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


 public void statistics(){
    
     System.out.println("HELLO! My id is: " + id +"\n" + "My name is: " + name);
     System.out.println("Dice for this Round: " + thisRoundsDice );
     System.out.println("My position is (" + x + "," + y + ")"); 
     //prints all the information about the weapons
     if(path.get(roundActually)[2] >= 0)
         System.out.println("I got a weapon of type: " + board.weapons[weaponNumber].getType() );
     else
         System.out.println("I didn't get any weapon");
     
     //prints all the information about the traps
     if(path.get(roundActually)[3] >= 0)
         System.out.println("I fell in a trap and I lost points : " + board.traps[trapNumber].getPoints() );
     else
         System.out.println("I didn't fall in any trap");
     
     //prints all the information about food
     if(path.get(roundActually)[4] >= 0)
         System.out.println("I ate food and I gained points: " + board.food[foodNumber].getPoints() );
     else
         System.out.println("I didn't eat any food");
     
     //prints score
     System.out.println("My score is:" + score );
     
     //increases round counter
     roundActually++;
     
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