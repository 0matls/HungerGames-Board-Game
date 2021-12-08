/**
 * 
 * 
 * @author dkyrgiaf@ece.auth.gr
 * @author slampaki@ece.auth.gr
 *  
 * 
 */
package deliverable1;
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
  
  Player p1;
  Player p2;
  
  board.setWeaponAreaLimits(2);
  board.setTrapAreaLimits(4);
  board.setFoodAreaLimits(3);
  
  board.createBoard();
  
  /**
   * Players are initialized and placed anti - diametrically on the board.
   */
  p1 = new Player(1, "Stavroula Siachalou", -10, -10, board);
  p2 = new Player(2, "Maria Kotouza", 10, 10, board);
  
  System.out.println("--------------------------------------------------------THE BOARD IS SET!!!!--------------------------------------------------------------------------");
  /**
   * At first, the board with the players in their initial positions is printed.
   */
  board.getStringRepresentation(p1, p2);
  /**
   * The following arrays, although they aren't used, contain the players' information.
   */
  //int[] infoP1 = new int[5];
  //int[] infoP2 = new int[5];


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
  
  while (board.getN() > 4) {
   System.out.println("--------------------------------------------------------CURRENT ROUND IS : " + game.getRound() + "-------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------PLAYER 1 NOW PLAYS!!!!------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");
   p1.move();
   System.out.println("--------------------------------------------------------PLAYER 2 NOW PLAYS!!!!------------------------------------------------------------------");
   System.out.println("--------------------------------------------------------   .     .      .     ------------------------------------------------------------------");
   System.out.println("-------------------------------------------------------AND THEIR MOVEMENTS ARE : ---------------------------------------------------------------");
   p2.move();
   board.getStringRepresentation(p1, p2);
   System.out.println("\n");
   p1.printMe();
   System.out.println("\n");
   p2.printMe();
   
   if (game.round % 3 == 0) {
       board.resizeBoard(p1, p2);
   }
   
   TimeUnit.MILLISECONDS.sleep(750);
   game.setRound(++game.round);
   
  } 
  
  
  
  /**
   * The final board is printed, and the winner is announced.
   */
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




