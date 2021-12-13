package deliverable4;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.*;

import java.util.Random;

/*
 * Class denoting the graphics of the game
 */
public class graphics {

	Random rand = new Random();
	
	// width and height of jframe
	
	public final int width = 1400, height = 1200;

	String  players[] = {"Random Player","Heuristic Player","MinMax Player"};
	
	// the following is a 2D array of JButton but we call them panels, yeah we know that!
	
	public final JButton[][] panel = new JButton[21][21];
	
	int initialN;
	
	int initialM;
	
	//inserting the pictures
	
	public ImageIcon player1Icon = new ImageIcon("player1.png");
	
	public ImageIcon player2Icon = new ImageIcon("player2.png");
	
	public ImageIcon ropeIcon = new ImageIcon("rope.png");
	
	public ImageIcon tigerIcon = new ImageIcon("tiger.png");
	
	public ImageIcon bearIcon = new ImageIcon("bear.png");
	
	public ImageIcon lionIcon = new ImageIcon("lion.png");
	
	public ImageIcon peachIcon = new ImageIcon("peach.png");
	
	public ImageIcon pineIcon = new ImageIcon("pineapple.png");
	
	public ImageIcon fruitIcon = new ImageIcon("fruit.png");
	
	public ImageIcon pistolIcon1 = new ImageIcon("pistol1.png");
	
	public ImageIcon bowIcon1 = new ImageIcon("bow1.png");
	
	public ImageIcon swordIcon1 = new ImageIcon("sword1.png");
	
	public ImageIcon pistolIcon2 = new ImageIcon("pistol2.png");
	
	public ImageIcon bowIcon2 = new ImageIcon("bow2.png");
	
	public ImageIcon swordIcon2 = new ImageIcon("sword2.png");
	
	//this is for all items inside our frame
	
	JLabel labelRound = new JLabel();
	
	JFrame frame = new JFrame();
	
	JLabel label1 = new JLabel("Player 1");
	
	JLabel label2 = new JLabel("Player 2");
	
	JLabel aTotalScore = new JLabel("Total Score : " );
	
	JLabel aMoveScore = new JLabel("Move Score : " );
	
	JLabel bTotalScore = new JLabel("Total Score : " );
	
	JLabel bMoveScore = new JLabel("Move Score : " );
	
	final JComboBox box1 = new JComboBox(players);

	final JComboBox box2 = new JComboBox(players);

	JButton jButton1 = new JButton("Select Player 1");

	JButton jButton2 = new JButton("Select Player 2");
	
	
	final JButton jbuttonstart = new JButton("Start Game");
	
	final JButton jButtonQuit = new JButton("Quit Game");
	
	// these help us handle the buttons once they have been clicked
	
	 Object contents1;
	
	 Object contents2;
	 
	 Object contentsStart;
	 /**
	  * Constructor of the class. Personalizes all the objects inside the frame.
	  * @param N
	  * @param M
	  */
	public graphics(int N, int M) {

		box1.setBackground(Color.red);
		box2.setBackground(Color.blue);
		jButton1.setBackground(Color.red);
		jButton2.setBackground(Color.blue);
		jButton1.setBorder(BorderFactory.createSoftBevelBorder(0));
		jButton2.setBorder(BorderFactory.createSoftBevelBorder(0));
		
		initialN = N;
		
		initialM = M;

		frame.setTitle("Hunger Games");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(null);   

		frame.getContentPane().setBackground(new Color(50,50,50));
		
		//ComboBoxes
		box1.setBounds(50, 700,200,50);    

	    frame.add(box1);    

	    box2.setBounds(1130, 700,200,50);    
	    
	    frame.add(box2);
		
	  
	   //buttons below ComboBoxes
	    jButton1.setBounds(50,750,200,50);

	    jButton2.setBounds(1130,750,200,50);

	    frame.add(jButton1);

	    frame.add(jButton2);

	    frame.setVisible(true);
	    
		frame.setSize(width,height);
		
		

		// start and quit buttons
		
		jbuttonstart.setBounds(50,200,150,30);

		frame.add(jbuttonstart);
		jbuttonstart.setBackground(new Color(0,198,0));
		

		jButtonQuit.setBounds(50,400,150,30);
		
		jButtonQuit.setBackground(new Color(245,0,0));
		
		frame.add(jButtonQuit);
		
		//instruction buttons, that appear before the game begins
		
		JLabel instructions1 = new JLabel("SELECT PLAYERS 1 AND 2 OF YOUR PREFERENCE");
		
		JLabel instructions2 = new JLabel("THEN PRESS START TO LET THE GAME BEGIN!");
		
		JLabel instructions3 = new JLabel("AT ANY TIME, PRESS QUIT TO EXIT GAME");
		
		instructions1.setBounds(300,150,800,100);
		
		instructions2.setBounds(300,300,800,100);
		
		instructions3.setBounds(300,450,800,100);
		
		instructions1.setForeground(new Color(230,208,178));
		
		instructions2.setForeground(new Color(230,208,178));
		
		instructions3.setForeground(new Color(230,208,178));
		
		instructions1.setFont(new Font ("Serif", Font.BOLD, 30));
		
		instructions2.setFont(new Font ("Serif", Font.BOLD, 30));
		
		instructions3.setFont(new Font ("Serif", Font.BOLD, 30));
		
		instructions1.setVisible(true);
		
		instructions2.setVisible(true);
		
		instructions3.setVisible(true);
		
		frame.add(instructions1);
		
		frame.add(instructions2);
		
		frame.add(instructions3);
		
	
	    

		//function to determine if jbutton1 has been clicked
		
	    for(;;) {
	    jButton1.addActionListener(new java.awt.event.ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		        
	    	contents1 = box1.getSelectedItem();
    	  
    	  System.out.println(contents1);
    	
    		  
    		  
    	  
	    	}
	    });

	    //function to determine if jbutton2 has been clicked

	    jButton2.addActionListener(new java.awt.event.ActionListener() {

		      public void actionPerformed(ActionEvent e) {

		    	  contents2 = box2.getSelectedItem();

		        System.out.println(contents2);
		      }
	    });
	    
	    if (contents1 != null && contents2 != null)
	    	break;
	    
	    }
	    

	    // function to determine if start button has been clicked
		for(;;) {
			jbuttonstart.addActionListener(new java.awt.event.ActionListener() {
	
			      public void actionPerformed(ActionEvent e)
	
			      {
	
			    	  if (jbuttonstart.isEnabled()) {
			    		  contentsStart = 1;
			              System.out.println("Start Button is pressed");
			          }
	
			      }
	
			    });   

		    	if(contentsStart!=null)
		    		break;

		}

		//code below to remove instructions before the game is about to start
		
		frame.remove(instructions1);
		
		frame.remove(instructions2);
		
		frame.remove(instructions3);
		
		frame.validate();
		frame.repaint();
		
		// function to determine if quit button has been clicked and if so to terminate the game
		
		jButtonQuit.addActionListener(new java.awt.event.ActionListener() {

		      public void actionPerformed(ActionEvent e)


		      {

		      if (e.getSource() == jButtonQuit )
		      {

		    	  System.out.println("The quit button has been clicked");


		    	   System.exit(0);

		      }

		      }
  }); 

		 
		//in the following lines, JLabes' properties and locations are being set
			

			label1.setBounds(70, 450, 120, 30);

			label1.setForeground(Color.RED);

			label1.setFont(new Font("Serif", Font.BOLD, 25));
			
			label1.setVisible(true);

			frame.add(label1);

			

			label2.setBounds(1150, 450, 120, 30);
			
			label2.setForeground(Color.BLUE);

			label2.setFont(new Font("Serif", Font.BOLD, 25));
			
			label2.setVisible(true);
			
			frame.add(label2);

			
			
			labelRound.setBounds(50, 50, 200, 30);

			labelRound.setForeground(new Color(100,0,255));

			labelRound.setFont(new Font("Serif", Font.BOLD, 35));

			frame.add(labelRound);

			

			aTotalScore.setBounds(70, 550, 200, 30);

			aTotalScore.setForeground(Color.red);

			aTotalScore.setFont(new Font("Serif", Font.BOLD, 20));

			frame.add(aTotalScore);

			
			
			aMoveScore.setBounds(70, 600, 200, 30);

			aMoveScore.setForeground(Color.red);

			aMoveScore.setFont(new Font("Serif", Font.ITALIC, 20));

			frame.add(aMoveScore);

			

			bTotalScore.setBounds(1150, 550, 200, 30);

			bTotalScore.setForeground(Color.blue);

			bTotalScore.setFont(new Font("Serif", Font.BOLD, 20));

			frame.add(bTotalScore);

			

			bMoveScore.setBounds(1150, 600, 200, 30);

			bMoveScore.setForeground(Color.blue);

			bMoveScore.setFont(new Font("Serif", Font.ITALIC, 20));

			frame.add(bMoveScore);

			
			
			
			//below the board of the game is being created, and some of its areas are painted with different colors
			
			int i,j;

			for(i = 0; i < 10; i++) {


				for(j = 0; j < 10; j++) {

					panel[i][j] = new JButton();

					panel[i][j].setVisible(true);

					panel[i][j].setLayout(null);

					panel[i][j].setBounds(300 + 40*i,20 + 40*j, 40, 40);

					

					if((j == 6 && i > 5 && i < 14) || (j == 14 && i > 5 && i < 14) || (i == 6 && j > 5 && j < 15) || (i == 13 && j > 5 && j < 15)) {

						panel[i][j].setBackground(new Color(225,0,0));

					}

					

					else if((j == 7 && i > 6 && i < 13) || (j == 12 && i > 6 && i < 13) || (i == 7 && j > 6 && j < 13) || (i == 12 && j > 6 && j < 13)) {

						panel[i][j].setBackground(new Color(1,139,0));

					}

					

					else if( j > 7 && j < 12 && i > 7 && i < 12) {

						panel[i][j].setBackground(new Color(28,89,95));

					}

					

					else {

						panel[i][j].setBackground(new Color(64,138,182));

					}

					
					frame.add(panel[i][j]);

					panel[i][j].setBorder(BorderFactory.createSoftBevelBorder(0));

				}
				
				for(j = 11; j < 21; j++) {

					panel[i][j] = new JButton();

					panel[i][j].setVisible(true);

					panel[i][j].setLayout(null);

					panel[i][j].setBounds(300 + 40*i,-20 + 40*j, 40, 40);

					

					if((j == 6 && i > 5 && i < 14) || (j == 14 && i > 5 && i < 14) || (i == 6 && j > 5 && j < 15) || (i == 13 && j > 5 && j < 15)) {

						

						panel[i][j].setBackground(new Color(225,0,0));

					}

					

					else if((j == 7 && i > 6 && i < 14) || (j == 13 && i > 6 && i < 13) || (i == 7 && j > 6 && j < 14) || (i == 12 && j > 6 && j < 14)) {

						

						panel[i][j].setBackground(new Color(1,139,0));

					}

					

					else if( j > 7 && j < 13 && i > 7 && i < 12) {

						

						panel[i][j].setBackground(new Color(28,89,95));

						

					}

					

					else {

						panel[i][j].setBackground(new Color(64,138,182));

					}

					
					frame.add(panel[i][j]);

					panel[i][j].setBorder(BorderFactory.createSoftBevelBorder(0));
				}

			}
			
			for(i = 11; i < 21; i++) {


				for(j = 0; j < 10; j++) {

					panel[i][j] = new JButton();

					panel[i][j].setVisible(true);

					panel[i][j].setLayout(null);

					panel[i][j].setBounds(260 + 40*i,20 + 40*j, 40, 40);

					

					if((j == 6 && i > 5 && i < 15) || (j == 14 && i > 5 && i < 15) || (i == 6 && j > 5 && j < 15) || (i == 14 && j > 5 && j < 15)) {

						

						panel[i][j].setBackground(new Color(225,0,0));

					}

					

					else if((j == 7 && i > 6 && i < 14) || (j == 12 && i > 6 && i < 14) || (i == 7 && j > 6 && j < 13) || (i == 13 && j > 6 && j < 13)) {

						

						panel[i][j].setBackground(new Color(1,139,0));

					}

					

					else if( j > 7 && j < 12 && i > 7 && i < 13) {

						

						panel[i][j].setBackground(new Color(28,89,95));

						

					}

					

					else {

						panel[i][j].setBackground(new Color(64,138,182));

					}

					
					frame.add(panel[i][j]);

					panel[i][j].setBorder(BorderFactory.createSoftBevelBorder(0));

				}
				
				for(j = 11; j < 21; j++) {

					panel[i][j] = new JButton();

					panel[i][j].setVisible(true);

					panel[i][j].setLayout(null);

					panel[i][j].setBounds(260 + 40*i,-20 + 40*j, 40, 40);

					

					if((j == 6 && i > 5 && i < 15) || (j == 14 && i > 5 && i < 15) || (i == 6 && j > 5 && j < 15) || (i == 14 && j > 5 && j < 15)) {

						

						panel[i][j].setBackground(new Color(225,0,0));

					}

					

					else if((j == 7 && i > 6 && i < 14) || (j == 13 && i > 6 && i < 14) || (i == 7 && j > 6 && j < 13) || (i == 13 && j > 6 && j < 13)) {

						

						panel[i][j].setBackground(new Color(1,139,0));

					}

					

					else if( j > 7 && j < 13 && i > 7 && i < 13) {

						

						panel[i][j].setBackground(new Color(28,89,95));

						

					}

					

					else {

						panel[i][j].setBackground(new Color(64,138,182));

					}

					frame.add(panel[i][j]);
					
					panel[i][j].setBorder(BorderFactory.createSoftBevelBorder(0));
				}

			}
			
			for(i = 0; i < 21; i++) {
				
				panel[i][10] = new JButton();
				
			}
			
			for(i = 0; i < 21; i++) {
				
				panel[10][i] = new JButton();
				
			}

			frame.validate();

	} 
	//below,some functions are being declared so as to be able to put/place and erase icon on the board
	public static void eraseIcon(int x, int y,graphics g) {
	    
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(null);
		
	}
	
	public static void putIcon(int x, int y, Player p,graphics g) {
		
		if(p.getId() == 1) {
			g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.player1Icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		}
		else {
			g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.player2Icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		}
	}
		
	public static void placePineApple(int x, int y,graphics g, Board board) {
			
			g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.pineIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
			
		}
	
	public static void placeFruit(int x, int y,graphics g, Board board) {
		
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.fruitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	public static void placePeach(int x, int y,graphics g, Board board) {
		
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.peachIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	
	public static void placeBear(int x, int y,graphics g, Board board) {
	
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.bearIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	public static void placeTiger(int x, int y,graphics g, Board board) {
	
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.tigerIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	public static void placeLion(int x, int y,graphics g, Board board) {
	
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.lionIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	public static void placeRope(int x, int y,graphics g, Board board) {
	
		g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.ropeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
	}
	
	//function to resize the board
	
	public static void resizeGraphics(int N, int M,graphics g) {
		
		int i;
		for(i = 0; i <= g.initialM; i++) {
			if(i == 10)
				continue;
			{g.panel[-N/2 + 10][i].setVisible(false);
			 g.panel[N/2 + 10][i].setVisible(false);
			 g.panel[i][-N/2 + 10].setVisible(false);
			 g.panel[i][N/2 + 10].setVisible(false);
			 }
		}
		
	}
	
	//functions to update the round and the players' scores
	
	public void updateRound(int Round) {
		
		labelRound.setText("Round: " + Round);
	}
	
	public void updatePlayerFinalScore(Player p) {
		
		if(p.getId() == 1) {
			aTotalScore.setText("Total Score : " + p.getScore());
		}
		else if (p.getId() == 2){
			bTotalScore.setText("Total Score : " + p.getScore());
		}
	}
	
	public void updatePlayerMoveScore(Player p,int points) {
		
		if(p.getId() == 1) {
			aMoveScore.setText("Move Score :" + points);
		}
		else if(p.getId() == 2){
			bMoveScore.setText("Move Score :" + points);
		}
		
	}
	
	// functions to place the weapons as the players have weapons of different colors ( red or blue )
	
	public static void placeWeapon(int x, int y, graphics g, Weapon w) {
		
		if(w.getType() == "Sword") {
			
			if(w.getPlayerId() == 1) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.swordIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			if(w.getPlayerId() == 2) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.swordIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			
		}
		else if(w.getType() == "Bow") {
			
			if(w.getPlayerId() == 1) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.bowIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			if(w.getPlayerId() == 2) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.bowIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			
		}
		
		else if(w.getType() == "Pistol") {
			
			if(w.getPlayerId() == 1) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.pistolIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			if(w.getPlayerId() == 2) {
				
				g.panel[x + g.initialN/2][y + g.initialM/2].setIcon(new ImageIcon(g.pistolIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				
			}
			
		}
	}
	// function to determine who plays first
	
		public int setTurn() {
			return (rand.nextInt(2) + 1);
		}
		
		
		/** this function is called every time a player plays
		 * it iterates traps and weapons and replaces them as long as they are not located in the same position as one of the two players
		 */
		public void replaceItems(Player p1,Player p2, Board board, graphics g) {
			//this is for opponent's weapons not to be erased
		    int a;
		    for(a = 0; a < board.getW(); a++) {
		    	if(board.weapons[a].getX() != p1.getX() && board.weapons[a].getY() != p1.getY() && board.weapons[a].getX() != p2.getX() && board.weapons[a].getY() != p2.getY())
		        placeWeapon(board.weapons[a].getX(),board.weapons[a].getY(),g,board.weapons[a]);
		    }
		    //this is for traps not to be erased
		    int b;
		    for(b = 0; b < board.getT(); b++) {
		    	if(board.traps[b].getType() == "Animal") {
		    		if(board.traps[b].getX() != p1.getX() && board.traps[b].getY() != p1.getY() && board.traps[b].getX() != p2.getX() && board.traps[b].getY() != p2.getY()) {
		    		if(b%3 == 0)
		    			graphics.placeBear(board.traps[b].getX(),board.traps[b].getY(),g,board);
		    		if(b%3 == 1)
		        		graphics.placeTiger(board.traps[b].getX(),board.traps[b].getY(),g,board);
		    		if(b%3 == 2)
		        		graphics.placeLion(board.traps[b].getX(),board.traps[b].getY(),g,board);
		    		}
		    	}
		    	else if(board.traps[b].getType() == "Rope") {
		    		if(board.traps[b].getX() != p1.getX() && board.traps[b].getY() != p1.getY() && board.traps[b].getX() != p1.getX() && board.traps[b].getY() != p1.getY()) {
		    			placeRope(board.traps[b].getX(),board.traps[b].getY(),g,board);
		    		}
		    	}
		    }
		}
		// functions to announce the winner or the player that killed its opponent
		public void announceWinner(int score1, int score2) {
			JLabel winner = new JLabel(" ");
			winner.setBounds(400,0,1000,100);
			winner.setFont(new Font("Serif", Font.BOLD, 50));
			winner.setForeground(new Color(230,208,178));
			winner.setVisible(true);
			frame.add(winner);
			if(score1 > score2) {
				winner.setText("PLAYER 1 WINS!REMATCH?");
			}
			else if(score2 > score1) {
				winner.setText("PLAYER 2 WINS!REMATCH?");
			}
			else {
				winner.setText("IT'S A TIE!REMATCH?");
			}
		}
		public void announceKiller(int id) {
			JLabel killer = new JLabel(" ");
			killer.setBounds(350,0,1000,100);
			killer.setFont(new Font("Serif", Font.BOLD, 50));
			killer.setForeground(new Color(230,208,178));
			killer.setVisible(true);
			frame.add(killer);
			if(id == 1) {
				killer.setText("PLAYER 1 SHOT PLAYER 2!REMATCH?");
			}
			else if(id == 2) {
				killer.setText("PLAYER 2 SHOT PLAYER 1!REMATCH?");
			}
		}

}