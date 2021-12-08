/**
 * 
 * 
 * @author dkyrgiaf@ece.auth.gr
 * @author slampaki@ece.auth.gr
 *  
 * 
 */

package deliverable1;
import java.util.Random;
/**
 * 
 * Class variables and arrays :
 * N : number of rows
 * M : number of columns
 * W : number of weapons
 * F : number of food
 * T : number of traps
 * 
 * 
 * weaponAreaLimits array : an 4 x 2 array that contains the coordinates in which the weapons must be placed
 * 
 * trapAreaLimits array : an 4 x 2 array that contains the coordinates in which the traps must be placed
 * 
 * foodAreaLimits array : an 4 x 2 array that contains the coordinates in which food must be placed
 * 
 * weapons array : its elements are all the weapons of the game
 *
 * food array : its elements are all the food of the game
 *
 * traps array : its elements are all the traps of the game
 * 
 * 
 * WE CONSIDER THE ADDITION OF THE FOLLOWING THREE VARIABLES HELPFUL AS THEY SIMPLIFY MUCH OF THE CONTROL:
 * 
 * weaponInt : the number needed to define the limits of the area of the the weapons ( NOTE THAT: this integer can create all 4  
 * coordinates of weaponAreaLimits array if we also use its negative form.)
 * 
 * foodInt : the number needed to define the limits of the area of food ( NOTE THAT: this integer can create all 4  
 * coordinates of foodAreaLimits array if we also use its negative form.)
 * 
 * trapInt : the number needed to define the limits of the area of the traps ( NOTE THAT: this integer can create all 4  
 * coordinates of trapAreaLimits array if we also use its negative form.)
 * 
 */
public class Board {
	
	/** 
	 * 
	 * We need the following "rand" object  to generate random integers later on
	 * 
	 */
    Random rand = new Random();
    private int N;
    private int M;
    private int W;
    private int F;
    private int T;
    
    private int[][] weaponAreaLimits = new int [4][2];
    private int[][] foodAreaLimits = new int [4][2];
    private int[][] trapAreaLimits = new int [4][2];
    
    private int weaponInt;
    private int foodInt;
    private int trapInt;
    
    Weapon[] weapons;
    Food[] food;
    Trap[] traps;
   
    /**
     * 
     * Constructor of the class; initializes every variable and every array element to zero, as it doesn't require any arguments.
     * 
     */
    public Board() {
    	System.out.println("Everything initialized to zero!");
    	this.N = 0;
        this.M = 0;
        this.W = 0;
        this.F = 0;
        this.T = 0;
        
        this.weaponInt = 0;
        this.foodInt = 0;
        this.trapInt = 0;
        
        weapons = new Weapon[0];
        food = new Food[0];
        traps = new Trap[0];
        
        weaponAreaLimits = new int[][]{{0,0},{0,0},{0,0},{0,0}};
        foodAreaLimits = new int [][] {{0, 0},{0,0},{0,0},{0,0}};
        trapAreaLimits = new int [][] {{0, 0},{0,0},{0,0},{0,0}};
        }
   /**
    * 
    * Constructor of the class; initializes its n x m size ( NOTE THAT n always equals to m), the number of weapons, food and
    * traps that will be placed on it and three distinctive numbers that define the size of the weapons', food and traps' areas.
    * Also initializes these areas' coordinates in the weaponAreaLimits, foodAreaLimits, trapAreaLimits arrays.
    * 
    * @param N the number of the board's rows
    * @param M the number of the board's columns
    * @param W the number of weapons on the board
    * @param F the number of food on the board
    * @param T the number of traps on the board
    * @param weaponInt the variable that indicates the limits of the the weapons' area
    * @param foodInt the variable that indicates the limits of the food area
    * @param trapInt the variable that indicates the limits of the traps' area
    */
    public Board(int N, int M, int W, int F, int T, int weaponInt,int foodInt,int trapInt){
    	
        this.N = N;
        this.M = M;
        this.W = W;
        this.F = F;
        this.T = T;
        
        this.weaponInt = weaponInt;
        this.foodInt = foodInt;
        this.trapInt = trapInt;
        
        weapons = new Weapon[W];
        food = new Food[F];
        traps = new Trap[T];
        
        weaponAreaLimits = new int[][]{{weaponInt,-weaponInt},{weaponInt,weaponInt},{-weaponInt,weaponInt},{-weaponInt,-weaponInt}};
        foodAreaLimits = new int [][] {{trapInt, -trapInt},{trapInt,trapInt},{-trapInt,trapInt},{-trapInt,-trapInt}};
        trapAreaLimits = new int [][] {{foodInt, -foodInt},{foodInt,foodInt},{-foodInt,foodInt},{-foodInt,-foodInt}};
    }
    
    /**
     * 
     * Constructor of the class; initializes the board by a board object given as an argument.
     * 
     * @param board object
     */
    public Board(Board board){
        this.N = board.N;
        this.M = board.M;
        this.W = board.W;
        this.F = board.F;
        this.T = board.T;
        
        this.weaponInt = board.weaponInt;
        this.foodInt = board.foodInt;
        this.trapInt = board.trapInt;
        
        this.weapons = board.weapons;
        this.food = board.food;
        this.traps = board.traps;
        
        this.weaponAreaLimits = board.weaponAreaLimits;
        this.trapAreaLimits = board.trapAreaLimits;
        this.foodAreaLimits = board.foodAreaLimits;
    }
    

    /**
	 * functions (a.k.a setters) to set the values of the variables in this class
	 * @param N, M, W, F, T, weaponInt, foodInt, trapInt
	 */
    void setN(int N) {
        this.N = N;
    }
    void setM(int M) {
        this.M = M;
    }
    void setW(int W) {
        this.W = W;
    }
    void setF(int F) {
            this.F = F;
    }
    void setT(int T) {
        this.T = T;
    }
    
    void setWeaponInt(int weaponInt) {
    	this.weaponInt = weaponInt;
    }
    
    void setFoodInt(int foodInt) {
    	this.foodInt = foodInt;
    }
    
    void setTrapInt(int trapInt) {
    	this.trapInt = trapInt;
    }
    
    void setWeaponAreaLimits(int weaponInt) {
        weaponAreaLimits = new int[][]{{weaponInt,-weaponInt},{weaponInt,weaponInt},{-weaponInt,weaponInt},{-weaponInt,-weaponInt}};
    }
    
    void setTrapAreaLimits(int trapInt) {
        foodAreaLimits = new int [][] {{trapInt, -trapInt},{trapInt,trapInt},{-trapInt,trapInt},{-trapInt,-trapInt}};
    }
    
    void setFoodAreaLimits(int foodInt) {
        trapAreaLimits = new int [][] {{foodInt, -foodInt},{foodInt,foodInt},{-foodInt,foodInt},{-foodInt,-foodInt}};
    }
    /**
	 * functions (a.k.a getters) to get the values of the variables in this class
	 * @return N, M, W, F, T, weaponInt, foodInt, trapInt, weaponAreaLimits[][[], foodAreaLimits[][], trapAreaLimits[][]
	 */
    int getN() {
        return N;
    }
    
    int getM() {
        return M;
    }
    
    int getW() {
        return W;
    }
    
    int getF() {
        return F;
    }
    
    int getT() {
        return T;
    }
    
    int getWeaponInt() {
    	return weaponInt;
    }
    
    int getFoodInt() {
    	return foodInt;
    }
    
    int getTrapInt() {
    	return trapInt;
    }
    int[][] getWeaponAreaLimits(){
        return weaponAreaLimits;
    }
    int[][] getFoodAreaLimits(){
        return foodAreaLimits;
    }
    
    int[][] getTrapAreaLimits(){
        return trapAreaLimits;
    }
    
    /**
     *  createRandomWeapon function below
     */
    
    public void createRandomWeapon() {
    	
        int i;
        int same_weapon_position = 0;
        
        /**
         * In the following for loops the id and type of each weapon are initialized. Considering that the weapon number ( W ) IS A MULTIPLE OF 3,
         * the type changes every W / 3. Otherwise, different allocations will occur.
         * Therefore, for these weapons the potential owners' identity is also initialized, conformed to the fact that the 
         * players are only two and they need to have at least one weapon of each type.
         */
        for(i = 0; i < W/3; i++) 
            weapons[i] = new Weapon(i+1, "Bow");
        
        for(i = W/3; i < 2*W/3; i++) 
            weapons[i] = new Weapon(i+1, "Pistol");
        
        for(i = 2*W/3; i < W; i++) 
            weapons[i] = new Weapon(i+1, "Sword");
        
        for(i = 0; i < W; i += 2) {
            weapons[i].setPlayerId(1);
            weapons[i + 1].setPlayerId(2);   
        }
        
        /**
         * In the following nested loops the position of every weapon is being initialized. Each of these positions is randomly selected via nextInt() method
         * within the limits set by the coordinates of weapoAreaLimits array and thus weaponInt.
         * 
         */
        for(i = 0; i < W; i++) {
            /**
             * In the following do - while loops if X or Y coordinate is chosen to be zero, a new one is selected, as no coordinate is represented with zero 
             * in the board coordinate system.
             */
            do {
	            do {
	                weapons[i].setX(rand.nextInt(2*weaponInt + 1) - weaponInt);
	            }
	            while( weapons[i].getX() == 0);
	            do {
	                weapons[i].setY(rand.nextInt(2*weaponInt + 1) - weaponInt);
	            }
	            while( weapons[i].getY() == 0);
	            
	            /**
	             * Finally, as a weapon cannot be placed upon another one, should this happen, new coordinates are chosen from scratch until a new pair of them is
	             * selected.
	             */
	            for(int j=0; j < i; j++) {
	                if(weapons[i].getX() == weapons[j].getX() && weapons[i].getY() == weapons[j].getY()) {
	                    same_weapon_position = 1;
	                    break;
	                }
	                else
	                    same_weapon_position = 0;
	            }
            }
            while(same_weapon_position == 1);
            /**
             * Below, every element of the weapon array is printed included its coordinates, its identity and its potential owner's identity,so 
             * as to facilitate control.
             */
            System.out.println("weapons[" + (i) + "] coordinates are : (" + weapons[i].getX() + "," + weapons[i].getY() +")");
            System.out.println("weapons[" + (i) + "] type is : " + weapons[i].getType());
            System.out.println("weapons[" + (i) + "] id is : " + weapons[i].getId());
            System.out.println("weapons[" + (i) + "] potential owner is : " + weapons[i].getPlayerId());
            System.out.println();
        }
        System.out.println();
        return;
     
    }
    
    /**
     *  createRandomFood function below
     */
    public void createRandomFood() {
        int same_food_position = 0;
        /**
         * In the following for loop the id and points of each food are initialized.
         */
        
        for(int i=0; i < F; i++) {
            food[i] = new Food(i+1);             
            food[i].setPoints(rand.nextInt(10) + 1);     
            
            /**
             * In the following nested loops the position of every food is initialized. Each of these positions is randomly selected via nextInt() method
             * RIGHT on the limits of the food area,set by the coordinates of foodAreaLimits array and thus foodInt.
             * 
             */
            do {
                do {
                    food[i].setX(rand.nextInt(2*foodInt +1) - foodInt);
                    food[i].setY(rand.nextInt(2*foodInt +1) - foodInt);
                    /**
                     * NOTE THAT: As shown in the conditions below, if X or Y coordinate is chosen to be zero, a new pair is selected, as no coordinate is represented with zero
                     * in the board coordinate system.
                     */
             
                }
                while((!(food[i].getX() == foodInt || food[i].getX() == -foodInt || food[i].getY() == foodInt || food[i].getY() == -foodInt)) || (food[i].getX() == 0 || food[i].getY() == 0)); 
                /**
	             * Finally, as food cannot be placed one upon another, should this happen, new coordinates are chosen from scratch until a new pair of them is
	             * selected.
	             */
                for(int k = 0; k < i;k++) {
                    if(food[i].getX() == food[k].getX() && food[i].getY() == food[k].getY()) {
                        same_food_position = 1;
                        break;
                    }
                    else
                        same_food_position = 0;
                }
            }
            while(same_food_position == 1);
            /**
             * Below, every element of the food array is printed included its coordinates its identity and its points, so 
             * as to facilitate control.
             */
            System.out.println("food[" + (i) + "] coordinates are : (" + food[i].getX() + "," + food[i].getY() + ")" );
            System.out.println("food[" + (i) + "] id is :" + food[i].getId());
            System.out.println("food[" + (i) + "] random points is :" + food[i].getPoints());
            System.out.println();
        }
        	System.out.println();
        return;
   }
    
        /**
         * createRandomTrap function below
         */
    
    
        public void createRandomTrap() {
        
        int same_trap_position = 0;
        /**
         * In the following for loop the id, points and type of each food are initialized.NOTE THAT: the first half of the traps are all ropes and the second
         * half are all animals.
         */
        for(int j = 0;j < T/2; j++) {
            traps[j] = new Trap(j+1,"Rope");
            traps[T - j - 1] = new Trap(T - j,"Animal");
            traps[j].setPoints(-(rand.nextInt(10)+1));
            traps[T - j -1].setPoints(-(rand.nextInt(10)+1));
        }  
        /**
         * In the following nested loops the position of every trap is initialized. Each of these positions is randomly selected via nextInt() method
         * RIGHT on the limits of the trap area,set by the coordinates of trapAreaLimits array and thus trapInt.
         * 
         */
         for(int i = 0;i < T;i++) {
            do {
                do {
                    traps[i].setX(rand.nextInt(2*trapInt +1) - trapInt);
                    traps[i].setY(rand.nextInt(2*trapInt +1) - trapInt);
                    /**
                     * NOTE THAT: As shown in the conditions below, if X or Y coordinate is chosen to be zero, a new pair is selected, as no coordinate is represented with zero 
                     * in the board coordinate system.
                     */
                }
                while((!(traps[i].getX() == trapInt || traps[i].getX() == -trapInt || traps[i].getY() == trapInt || traps[i].getY() == -trapInt)) || (traps[i].getX() == 0 || traps[i].getY() == 0)); 
                
                /**
	             * Finally, as a trap cannot be placed upon another one, should this happen, new coordinates are chosen from scratch until a new pair of them is
	             * selected.
	             */
                for(int k = 0; k < i;k++) {
                    if(traps[i].getX() == traps[k].getX() && traps[i].getY() == traps[k].getY()) {
                        same_trap_position = 1;
                        
                        break;
                    }
                    else
                        same_trap_position = 0;
                }
            }
            while(same_trap_position == 1);
            /**
             * Below, every element of the trap array is printed included its coordinates its identity and its points, so 
             * as to facilitate control.
             */
            System.out.println("traps[" + (i) + "] coordinates are : (" + traps[i].getX() + "," + traps[i].getY() + ")");
            System.out.println("traps[" + (i) + "] type is : " + traps[i].getType() );
            System.out.println("traps[" + (i) + "] id is : " + traps[i].getId());
            System.out.println("traps[" + (i) + "] random points is : " + traps[i].getPoints());
            System.out.println();
            
         }
         System.out.println();
         return;
        }
    /**
     * createBoard function below uses the three functions above (createRandomWeapon, createRandomFood, createRandomTrap)
     * to create the board.
     */
    public void createBoard() {
        createRandomFood();
        createRandomWeapon();
        createRandomTrap();
        return;
    }
    /**
     * resizeBoard function below 
     * Given as arguments the two players, it is decided whether or not the board will be resized. If at least one player is on the outside perimeter of the board
     * the size of the board doesn't change. Otherwise, every one of the four sides is reduced by one tile. Every time, an appropriate message is printed.
     * @param player1
     * @param player2
     */
    public void resizeBoard(Player player1 ,Player player2) {
        if (player1.getX() == N/2 || player1.getX() == (-N/2) || player1.getY() == M/2 || player1.getY() == (-M/2) || player2.getX() == N/2 || 
                player2.getX() == (-N/2) || player2.getY() == M/2 || player2.getY() == (-M/2) ) {
            System.out.println("There is a player on the outside perimeter. Board cannot be resized!");
            return;
        }
        else {
            N -= 2;
            M -= 2;
            System.out.println("Board successfully resized!");
            return;
        }
        
    }
    /**
     * getStringRepresentation below
     * @param player1
     * @param player2
     * @return BoardRepresented
     */
    public String[][] getStringRepresentation(Player player1,Player player2){
        String [][] BoardRepresented = new String[N+1][M+1];
        /**
         * In the following nested loops the whole board as it will appear is saved in the returning array, a.k.a. BoardRepresented. This means that every
         * weapon, food and trap on its position on the board.
         * NOTE THAT: BoardRepresented is a zero - based array with only positive coordinates.
         */
        for(int i = -N/2; i <=N/2 ;i++) {
            if( i == 0)
                continue;
            for(int j = -M/2; j <= M/2 ;j++) {
                if(j == 0)
                    continue;
                for(int k = 0; k < F;k++) {
                    if(food[k].getX() == i && food[k].getY() == j) {
                        BoardRepresented[i + N/2][j + M/2] = "F" + food[k].getId();
                    }
                    
                        
                }
                for(int l = 0; l < T; l++) {
                    if(traps[l].getX() == i && traps[l].getY() == j) {
                        BoardRepresented[i + N/2][j + M/2] = "T" + traps[l].getId();
                    }
                    
                }
                for(int m = 0; m < W; m++) {
                    if( weapons[m].getX() == i && weapons[m].getY() == j) {
                        BoardRepresented[i + N/2][j + M/2] = "W" + (weapons[m].getPlayerId() + "." + weapons[m].getId() );
                    }
                  /** 
                   * NOTE THAT: We considered the addition of the players' positions very useful, so as to be able to control whether or not their movements 
                   * are the desired and within the eight possible ones.
                   * NOTE ALSO THAT: In case both players stand on the same tile, only " P2 " is represented as a result of checking first if player 1 is 
                   * positioned on this specific tile, and then player 2.
                   */
                   
                if(player1.getX() == i && player1.getY() == j)
                    BoardRepresented[ i + N/2][j + M/2] =  "P1";
                if(player2.getX() == i && player2.getY() == j)
                    BoardRepresented[ i + N/2][j + M/2] = "P2";
                /**
                 * Finally, if nothing lays on a specific tile, this tile is represented with " # ".
                 */
                if(BoardRepresented[i+ N/2][ j + M/2] == null)
                    BoardRepresented[i+ N/2][ j + M/2] = " # ";
                }
                
            }
 
        }
        /**
         * To illustrate the board, the transpose of the BoardRepresented is actually needed, as a result of the fact that we add N / 2 to every x coordinate 
         * and M /2 to every y coordinate so as BoardRepresented is a zero - based array with only positive coordinates.
         * Therefore, we considered more useful to print the array inside this function, rather than returning the BoardRepresented array and then 
         * printing it inside main function.
         */
        for(int k = 0;k <= N; k++) {
            if( k == N/2)
                continue;
            for(int j = 0;j <= N; j++) {
                if(j==N/2)
                    continue; 
                System.out.print(BoardRepresented[j][k]);
                System.out.print("\t");
            }
            System.out.println();
        } 
        return BoardRepresented;
        
    }
}


