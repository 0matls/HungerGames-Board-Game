package deliverable4;

import java.util.concurrent.TimeUnit;

/**

 * Class denoting the game.

 * 

 */

public class Game {

    

 private int round;

 

 /**

  * Constructor of the class; initializes the round to one.

  */

 public Game() {

  this.round = 1;

 }

 /**

  * Constructor of the class; initializes the round by the argument it is given.

  */

 public Game(int round) {

  this.round = round;

  

 }

 /**

     * function (a.k.a setter) to set the value of the round variable in this class

     * @param round

     */

 void setRound(int round) {

  this.round = round;

 }

 /**

     * function (a.k.a getter) to get the value of the round variable in this class

     * @return round

     */

 int getRound() {

  return round;

 }


 

 public static void main(String[] args) throws InterruptedException {

     
	/**
	 * First, we create the board and the graphics object
	 */
    Game game = new Game(1);

 

      /**

	   * NOTICE THE ARGUMENTS!!!!!

	   */

    Board board = new Board(20, 20, 6, 10, 8, 2, 3, 4);
	graphics g = new graphics(20,20);
	  
  	board.setWeaponAreaLimits(2);

    board.setTrapAreaLimits(4);

    board.setFoodAreaLimits(3);

    
    
    board.createBoard(g);
   /**
    * We only need two players, both players can be of any type
    */
    Player p1,p2;
    
    //here we determine who plays first
    
    int whoPlaysFirst = g.setTurn();
    /**
     * Based on the user's input, the chosen player types are created. In order for us to be able to recognize the 
     * type they belong, after the name we add in parenthesis the first letter of their type. Note also that player 
     * 1 always has the name "Stavroula Siachalou" and player 2 always has the name "Maria Kotouza" too.
     */
	    if(g.contents1 == "Random Player") {
	    	  p1 = new Player(1, "Stavroula Siachalou (r)", -10, -10,board);
			  p1.setScore(15);
		  }
	   else if(g.contents1 == "Heuristic Player") {
	   	p1 = new HeuristicPlayer(1, "Stavroula Siachalou (h)", -10, -10,board,3);
	   	p1.setScore(15);
	   }
	   else {
	  	p1 = new MinMaxPlayer(1, "Stavroula Siachalou (m)", 10, 10, board);
	   	p1.setScore(15);
	   }
	   	
	   if(g.contents2 == "Random Player") {
			
			  p2 = new Player(2, "Maria Kotouza (r)", 10, 10, board);
			  p2.setScore(15);
		  }
	  else if(g.contents2 == "Heuristic Player") {
		
	  	p2 = new HeuristicPlayer(2, "Maria Kotouza (h)", 10, 10, board,3);
	  	p2.setScore(15);
	  }
	  else {
	  	
	  	p2 = new MinMaxPlayer(2, "Maria Kotouza (m)", 10, 10, board);
	  	p2.setScore(15);
	  }
 	
 

  

  System.out.println("--------------------------------------------------------THE BOARD IS SET!!!!--------------------------------------------------------------------------");

  /**

   * At first, the board with the players in their initial positions is printed.

   */

  board.getStringRepresentation(p1, p2);

  /**

   * The following arrays, although they aren't used, contain the players' information.

   */

  
  /**

   * FINALLY, in the following loop the game is carried out!

   */

  

  /**

   * The number of round is printed at first.Then, player 1 and player 2 play and the board with their movements is printed. Afterwards, all information about

   * the players' state is printed, then it's checked if it's time to resize the board. A small delay was also added, as to be easier to watch the match.

   * Finally, round is increased by one.

   * ENJOY!

   */

  System.out.println("--------------------------------------------------------LET THE GAME BEGIN!!!!------------------------------------------------------------------");

  int hasAnyoneDied = 0;
  
  /**
   * If the board has a size of 4 x 4 or 100 rounds have passed and we still don't have a winner, the game ends and whoever have the biggest 
   * score wins.
   */
  while (board.getN() > 4  && game.getRound() <= 100) {
	  
	//this is to update round in graphics
	  g.updateRound(game.getRound());

   System.out.println("--------------------------------------------------------CURRENT ROUND IS : " + game.getRound() + "-------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------PLAYER 1 NOW PLAYS!!!!------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");

   /**
    * The following code allows the player that has been chosen to play first to make the first move etc.
    */
   if(whoPlaysFirst == 1) {
	   if(g.contents1 == "Random Player") {
			 
			  p1.move(g);
		  }
		 else if(g.contents1 == "Heuristic Player") {
		 	
		 	  ((HeuristicPlayer)p1).Move(p2,g);
		 }
		 else if(g.contents1 == "MinMax Player"){
			 
			  ((MinMaxPlayer)p1).getNextMove(p2,game.getRound(),g);
		 }
	   	
	    g.replaceItems(p1,p2,board,g);
	
	   /**
	
	    * Checking if player1 has shot player2.
	
	    */
	
	   if( HeuristicPlayer.kill(p1,p2,2) ) {
	
	       hasAnyoneDied = 1;
	
	      
	
	       System.out.println();
	
	       p1.printMe();
	
	       System.out.println();
	
	       p2.printMe();
	
	       System.out.println("Player " + p1.getName() + " has killed " + p2.getName() + ". Player " + p1.getName() + " has won!");
	       
	       g.announceKiller(p1.getId());
	       
	       break;
	
	   }
   }
   else {   
	   if(g.contents2 == "Random Player") {
			 
			  p2.move(g);
		  }
		 else if(g.contents2 == "Heuristic Player") {
		 	
		 	  ((HeuristicPlayer)p2).Move(p1,g);
		 }
		 else if(g.contents2 == "MinMax Player"){
			 
			  ((MinMaxPlayer)p2).getNextMove(p1,game.getRound(),g);
		 }
	   g.replaceItems(p1,p2,board,g);


	   /**

	    * Checking if player2 has shot player1.

	    */

	   if( HeuristicPlayer.kill(p2,p1,2) ) {

	       hasAnyoneDied = 1;

	      

	       System.out.println();

	       p1.printMe();

	       System.out.println();

	       p2.printMe();

	       System.out.println("Player " + p2.getName() + " has killed " + p1.getName() + ". Player " + p2.getName() + " has won!");
	       
	       g.announceKiller(p2.getId());
	       
	       break;

	   }
	   
   }
   
   //this is to update score of player1
   g.updatePlayerFinalScore(p1);

   System.out.println("--------------------------------------------------------PLAYER 2 NOW PLAYS!!!!------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");

   System.out.println("-------------------------------------------------------AND THEIR MOVEMENTS ARE : ---------------------------------------------------------------");

   
   /**
    * The following code allows the player that has not been chosen to play first to move after the player that has.
    */
   if(whoPlaysFirst == 2) {
	   if(g.contents1 == "Random Player") {
			 
			  p1.move(g);
		  }
		 else if(g.contents1 == "Heuristic Player") {
		 	
		 	  ((HeuristicPlayer)p1).Move(p2,g);
		 }
		 else if(g.contents1 == "MinMax Player"){
			 
			  ((MinMaxPlayer)p1).getNextMove(p2,game.getRound(),g);
		 }
	   
	     g.replaceItems(p1,p2,board,g);
	
	   /**
	
	    * Checking if player1 has shot player2.
	
	    */
	
	   if( HeuristicPlayer.kill(p1,p2,2) ) {
	
	       hasAnyoneDied = 1;
	
	      
	
	       System.out.println();
	
	       p1.printMe();
	
	       System.out.println();
	
	       p2.printMe();
	
	       System.out.println("Player " + p1.getName() + " has killed " + p2.getName() + ". Player " + p1.getName() + " has won!");
	       
	       g.announceKiller(p1.getId());
	       
	       break;
	
	   }
   }
   else {   
	   if(g.contents2 == "Random Player") {
			 
			  p2.move(g);
		  }
		 else if(g.contents2 == "Heuristic Player") {
		 	
		 	  ((HeuristicPlayer)p2).Move(p1,g);
		 }
		 else if(g.contents2 == "MinMax Player"){
			 
			  ((MinMaxPlayer)p2).getNextMove(p1,game.getRound(),g);
		 }

	   g.replaceItems(p1,p2,board,g);

	   /**

	    * Checking if player2 has shot player1.

	    */

	   if( HeuristicPlayer.kill(p2,p1,2) ) {

	       hasAnyoneDied = 1;

	      

	       System.out.println();

	       p1.printMe();

	       System.out.println();

	       p2.printMe();

	       System.out.println("Player " + p2.getName() + " has killed " + p1.getName() + ". Player " + p2.getName() + " has won!");
	       
	       g.announceKiller(p2.getId());
	       break;

	   }
	   
   }

   board.getStringRepresentation(p1, p2);

   System.out.println();
	/**
	 * The players' state is printed.
	 */
   if(g.contents1 == "Random Player") {
		 
		  p1.printMe();
	  }
	 else if(g.contents1 == "Heuristic Player") {
	 	
		 ((HeuristicPlayer)p1).statistics(game.getRound());
	 }
	 else if(g.contents1 == "MinMax Player"){
		
		 ((MinMaxPlayer)p1).statistics(game.getRound());
	 }


   System.out.println();
   
   if(g.contents2 == "Random Player") {
		 
		  p2.printMe();
	  }
	 else if(g.contents2 == "Heuristic Player") {
	 	
		 ((HeuristicPlayer)p2).statistics(game.getRound());
	 }
	 else if(g.contents2 == "MinMax Player"){
		
		 ((MinMaxPlayer)p2).statistics(game.getRound());
	 }

  
   
 //this is to update score of player2
   g.updatePlayerFinalScore(p2);
   
   

   

   if (game.round % 3 == 0) {

       board.resizeBoard(p1, p2,g);

   }

   

   TimeUnit.MILLISECONDS.sleep(1000);

   game.setRound(++game.round);

   //if a player has a negative score, he automatically loses.
   
   if(p1.getScore() < 0 || p2.getScore() < 0)
	   break;

  } 

  

  

  

  /**

   * The final board is printed, and the winner is announced.

   */

  if(hasAnyoneDied == 0) {

      board.getStringRepresentation(p1, p2);

      

      if (p1.getScore() < p2.getScore()) {

          

          System.out.println("---- " + p2.getName() +" wins ---- " + "CONGRATULATIONS " + p2.getName() + " !!!!!!!");

       

      }

      else if(p1.getScore() > p2.getScore()) {

          

          System.out.println("---- " + p1.getName() +" wins ----" + " CONGRATULATIONS " + p1.getName() + " !!!!!!!");

          

      }

      else {

          

          System.out.println("----------------IT'S A TIE!!!-----------------");

          

      }

      
      // so as to also announce the winner on the frame
      g.announceWinner(p1.getScore(), p2.getScore());

      System.out.println("***********************************THE END************************************************");

     }

}

}

