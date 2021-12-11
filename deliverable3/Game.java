package deliverable3;

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

     

  Game game = new Game(1);

  

  

  /**

   * NOTICE THE ARGUMENTS!!!!!

   */

  Board board = new Board(20, 20, 6, 10, 8, 2, 3, 4);

  

  MinMaxPlayer p1;

  Player p2;

  

  board.setWeaponAreaLimits(2);

  board.setTrapAreaLimits(4);

  board.setFoodAreaLimits(3);

  

  board.createBoard();

  

  /**

   * Players are initialized and placed in the same spot!

   */

  p1 = new MinMaxPlayer(1, "Stavroula Siachalou", 10, 10, board);
  
  p1.setScore(15);
  

  p2 = new Player(2, "Maria Kotouza", 10, 10, board);
  p2.setScore(15);

  

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

  while (board.getN() > 4) {

   System.out.println("--------------------------------------------------------CURRENT ROUND IS : " + game.getRound() + "-------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------PLAYER 1 NOW PLAYS!!!!------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");

   p1.getNextMove(p2, game.round);

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

       break;

   }

   System.out.println("--------------------------------------------------------PLAYER 2 NOW PLAYS!!!!------------------------------------------------------------------");

   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");

   System.out.println("-------------------------------------------------------AND THEIR MOVEMENTS ARE : ---------------------------------------------------------------");

   p2.move();

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

       break;

   }

   board.getStringRepresentation(p1, p2);

   System.out.println();

   p1.statistics();

   System.out.println();

   p2.printMe();

   

   if (game.round % 3 == 0) {

       board.resizeBoard(p1, p2);

   }

   

   TimeUnit.MILLISECONDS.sleep(750);

   game.setRound(++game.round);

   p1.playersDistance(p2);

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

      

      

      System.out.println("***********************************THE END************************************************");

     }

}

}


/*
 package deliverable3;
import java.util.concurrent.TimeUnit;
public class Game {
 private int round;
 public Game() {
  this.round = 1;
 }
 public Game(int round) {
  this.round = round;
 }
 void setRound(int round) {
  this.round = round;
 }
 int getRound() {
  return round;
 }
 public static void main(String[] args) throws InterruptedException {
     int gameRounds = 1000;
     int p1wins = 0;
     int p2wins = 0;
     int itsatie = 0;
     while(gameRounds > 0) {
     Game game = new Game(1);
  Board board = new Board(20, 20, 6, 10, 8, 2, 3, 4);
  MinMaxPlayer p1;
  Player p2;
  board.setWeaponAreaLimits(2);
  board.setTrapAreaLimits(4);
  board.setFoodAreaLimits(3);
  board.createBoard();
  p1 = new MinMaxPlayer(1, "Stavroula Siachalou", -10, -10, board);
  p2 = new Player(2, "Maria Kotouza", 10, 10, board);
  System.out.println("--------------------------------------------------------THE BOARD IS SET!!!!--------------------------------------------------------------------------");
  board.getStringRepresentation(p1, p2);
  int[] infoP1 = new int[2];
  System.out.println("--------------------------------------------------------LET THE GAME BEGIN!!!!------------------------------------------------------------------");
  int hasAnyoneDied = 0;
  while (board.getN() > 4) {
   System.out.println("--------------------------------------------------------CURRENT ROUND IS : " + game.getRound() + "-------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------PLAYER 1 NOW PLAYS!!!!------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");
   p1.getNextMove(p2, game.round);
   if( HeuristicPlayer.kill(p1,p2,2) ) {
       hasAnyoneDied = 1;
       System.out.println("Player " + p1.getName() + " has killed " + p2.getName() + ". Player " + p1.getName() + " has won!");
       p1wins++;
       break;
   }
   System.out.println("--------------------------------------------------------PLAYER 2 NOW PLAYS!!!!------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");
   System.out.println("-------------------------------------------------------AND THEIR MOVEMENTS ARE : ---------------------------------------------------------------");
   p2.move();
   if( HeuristicPlayer.kill(p2,p1,2) ) {
       hasAnyoneDied = 1;
       System.out.println("Player " + p2.getName() + " has killed " + p1.getName() + ". Player " + p2.getName() + " has won!");
       p2wins++;
       break;
   }
   board.getStringRepresentation(p1, p2);
   System.out.println("\n");
   p1.printMe();
   System.out.println("\n");
   p2.printMe();
   if (game.round % 3 == 0) {
       board.resizeBoard(p1, p2);
   }
   TimeUnit.MILLISECONDS.sleep(0);
   game.setRound(++game.round);
   System.out.println("**************************************************test printing******************************************************************************");
   p1.playersDistance(p2);
}
  if(hasAnyoneDied == 0) {
      board.getStringRepresentation(p1, p2);
      if (p1.getScore() < p2.getScore()) {
          System.out.println("---- " + p2.getName() +" wins ---- " + "CONGRATULATIONS " + p2.getName() + " !!!!!!!");
          p2wins++;
      }
      else if(p1.getScore() > p2.getScore()) {
          System.out.println("---- " + p1.getName() +" wins ----" + " CONGRATULATIONS " + p1.getName() + " !!!!!!!");
          p1wins++;
      }
      else {
          System.out.println("----------------IT'S A TIE!!!-----------------");
          itsatie++;
      }
      System.out.println("***********************************THE END************************************************");
        HeuristicPlayer.roundActually = 0;
        HeuristicPlayer.diagonalMove1 = 1;
        HeuristicPlayer.diagonalMove2 = 1;
     }
    HeuristicPlayer.roundActually = 0;
    HeuristicPlayer.diagonalMove1 = 1;
    HeuristicPlayer.diagonalMove2 = 1;
        gameRounds--;
        }
        System.out.println("player 1 won: " +p1wins +"times");
        System.out.println("player 2 won: " +p2wins +"times");
        System.out.println("it was a tie: " +itsatie +"times");
    }
}
*/
