package deliverable2;
/**
 * Class denoting a weapon on the board.Weapons can be bows, pistols or swords.
 * 
 */
public class Weapon {
	private int id; 
	private int x; 
	private int y;
	private int playerId;
	private String type;
	/**
	 * Constructor of the class; initializes its identity (counting starts from one)
	 * and its type (bow, pistol or arrow).
	 *
	 * NOTE THAT :  its x and y coordinates on the board aren't needed as arguments as they will be set in 
	 * function createRandomWeapon in class Board, so for now they are temporarily set to 0.
	 * PlayerId is also temporarily set to 0, and will change only in case some player acquires it.
	 * 
	 * @param id the unique identifier of the weapon 
	 * @param type (i.e bow , pistol or sword)
	 */
	
	public Weapon(int id, String type) {
		this.id = id;
		this.x = 0;
		this.y = 0;
		this.playerId = 0 ;
		this.type = type;
		
	}
	/**
	 * Constructor of the class; initializes a weapon by a weapon object given as an argument
	 * @param weapon object
	 */
	public Weapon(Weapon weapon) {
		this.id = weapon.id;
		this.x = weapon.x;
		this.y = weapon.y;
		this.playerId = weapon.playerId;
		this.type = weapon.type;
	}
	/**
	 * functions (a.k.a setters) to set the values of the variables in this class
	 * @param id, x, y, playerId, type 
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
	
	public void setPlayerId(int playerId) {
		
		this.playerId = playerId;
	}
	
	public void setType(String type) {
		
		this.type = type;
	}
	
	/**
	 * functions (a.k.a getters) to get the values of the variables in this class
	 * @return id, x, y, playerId, type
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
	
	public int getPlayerId() {
		
		return playerId;
	}
	
	public String getType() {
		
		return type;
	}

}