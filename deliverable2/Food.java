package deliverable2;
/**
 * 
 * Class denoting Food on the board.
 *
 */
public class Food {
		private int id;
		private int x;
		private int y;
		private int points;
		/**
		 * 
		 * Constructor of the class; initializes its identity (counting starts from one).
		 * 
		 * NOTE THAT :  its x and y coordinates on the board aren't needed as arguments as they will be set in 
		 * function createRandomFood in class Board, so for now they are temporarily set to 0.
		 * Points (that will be given to the player after he falls upon a food's tile),so they are temporarily
		 * set to 0 as their value will be defined (randomly) in function createRandomFood in class
		 * Board.
		 *  
		 * @param id the unique identifier of each specific food object
		 */
		
		 Food(int id){
			this.id = id;
			this.x = 0;
			this.y = 0;
			this.points = 0;
		}
		/**
		 * Constructor of the class; initializes its variables by a Food object given as 
		 * an argument.
		 * @param food object
		 */
		
		public Food(Food food){
			this.id = food.id;
			this.x = food.x;
			this.y = food.y;
			this.points = food.points;
			
		}
		
		/**
		 * functions (a.k.a setters) to set the values of the variables in the class
		 * @param id, x, y, points
		 */
		
		public void setId(int id){
			this.id = id;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public void setPoints(int a) {
			points = a;
		}
		
		/**
		 * functions (a.k.a getters) to get the values of the variables in the class
		 * @return id, x, y, points
		 */
		
		public int getId(){
			return id;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getPoints(){
			return points;
		}
		
		
}