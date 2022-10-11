import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class BoardFinal extends JPanel implements MouseListener{
	
	//backend data structure for traversing entire 2D array of Tiles
	TileFinal[][] board = new TileFinal[8][10]; 
	int mineCount = 10;
	int totalFlagged = 0;
	boolean won = true;
	//String fileName = "flag.png";
	//Image flag = getImage(fileName);
	
	/* method to generate 10 mines */
	public void generateMines() {
		
		int count = 0;
		while(count < mineCount) { //generate 10 mines
			
			//generate a random location (row, col)
			int row = (int)(Math.random()*board.length);
			int col = (int)(Math.random()*board[0].length);
			
			//If a generated location is already a mine, generate a new location
			// keep generating locations if the generated location is a mine!
			while(board[row][col].isMine) {
				row = (int)(Math.random()*board.length);
				col = (int)(Math.random()*board[0].length);
			}

			board[row][col].isMine= true; //set this tile to a Mine!
			count++;//increment mine count!
			
		}
		
	}



	
	/* 
	 * Method that will take a given Tile and check to see if it is a mine
	 * If it is a mine, then update the mine count for the surrounding tiles
	 */
	public void updateCount(int r, int c) {
		
		//if no mine at location r, c exit!
		if(!board[r][c].isMine) {
			return; 
		}
		
		for(int row = r-1; row <= r+1; row++) {
			for(int col = c-1; col <= c+1; col++) {
				
				try {
					board[row][col].count++;
				}catch(Exception e) {
					//do nothing! you went out of bounds
				}
			}
		}
		
	}
	/*
	public void paint(Graphics g) {
		
		if(won==true) {
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, 600, 600);
			g.setColor(Color.WHITE);
			g.drawString("YOU WIN!", 250, 250);
		}
		
	}
	*/

  public void checkWon(){
    //traverse the 2d array of Tiles
    //check if the number of Flagged tiles
    //is the same as the number of total mines
	  int FnM = 0;
	  //if(totalFlagged==mineCount) {
		  for(int row = 0; row < board.length; row++) {
				for(int col = 0; col < board[0].length; col++) {
					if(board[row][col].isMine() && board[row][col].isFlagged() ) {
						FnM++;
					}
				}
		//	}
		  //displayMines(); 
		  if(FnM==mineCount);
		  won = true;
		  board[1][1].setText("Y");
		  board[1][2].setText("O");
		  board[1][3].setText("U");
		  board[1][5].setText("W");
		  board[1][6].setText("I");
		  board[1][7].setText("N");
		  board[1][8].setText("!");
		  board[2][1].setText("Y");
		  board[2][2].setText("O");
		  board[2][3].setText("U");
		  board[2][5].setText("W");
		  board[2][6].setText("I");
		  board[2][7].setText("N");
		  board[2][8].setText("!");
		  board[3][1].setText("Y");
		  board[3][2].setText("O");
		  board[3][3].setText("U");
		  board[3][5].setText("W");
		  board[3][6].setText("I");
		  board[3][7].setText("N");
		  board[3][8].setText("!");
		  board[4][1].setText("Y");
		  board[4][2].setText("O");
		  board[4][3].setText("U");
		  board[4][5].setText("W");
		  board[4][6].setText("I");
		  board[4][7].setText("N");
		  board[4][8].setText("!");
		  board[5][1].setText("Y");
		  board[5][2].setText("O");
		  board[5][3].setText("U");
		  board[5][5].setText("W");
		  board[5][6].setText("I");
		  board[5][7].setText("N");
		  board[5][8].setText("!");
		  board[6][1].setText("Y");
		  board[6][2].setText("O");
		  board[6][3].setText("U");
		  board[6][5].setText("W");
		  board[6][6].setText("I");
		  board[6][7].setText("N");
		  board[6][8].setText("!");
		  
		  System.out.println("YOU WON!!!!");
	  }else {
		  won = true;
	  }
	  
	 

  }



	
	/* count the mines per tile*/
	public void countMines() {
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				//Call helper method to update the surrounding Tile
				updateCount(row,col);//update the count 
			}
		}	
		
	}
	
	/**
	 * Method for debugging and display the mines and or the surround mine count for
	 * each Tile object on the GUI
	 */
	public void displayMines() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
					if(board[row][col].isMine) {
						board[row][col].setIcon(new ImageIcon("mine.png"));
            	board[row][col].setText("*");
					}else{
           board[row][col].setText(board[row][col].getCount()+"");
          }
					
			}
		}
		repaint();
	
	}
	

	public BoardFinal() {
		//construct the JFrame hodling the tiles!
		JFrame frame = new JFrame("Minesweeper");
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add a layout manager (grid)
		frame.setLayout(new GridLayout(8,10));
		
		
		//add the board!
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				TileFinal t = new TileFinal(row, col);		//Create a Tile object with a given row,col value
				t.addMouseListener(this);			//connect Tile to an event-listener (Mouse events in this case)
				frame.add(t); 			//add it to GUI
				board[row][col] = t;	//for data structure
				
			}
		}		
		
		//generate mines (call helper method)
		generateMines();
		countMines();
		//displayMines();
		frame.setVisible(true);
	}

  public void gameOver(){
    System.out.println("gameOver");
    for(int r = 0; r < board.length; r++){
      for(int c = 0; c < board[0].length; c++){
        if(board[r][c].isMine){
        	System.out.println("MINECRAFT");
          board[r][c].setIcon(new ImageIcon("mine.png"));
        }
      }
    }
  }


  public void reveal(int r, int c){
    System.out.println("revealing");
   
    //base-case is when you have nothing to do!
    if(r<0 || r>=board.length || c<0|| c>=board[0].length ||
    board[r][c].getText().length()>0 || board[r][c].isEnabled()==false){
      return;
    }else if(board[r][c].count!=0){
      board[r][c].setText(board[r][c].count+"");
      board[r][c].setEnabled(false);
    }else{
      board[r][c].setEnabled(false);
      reveal(r-1,c);//north
      reveal(r+1,c);//south
      reveal(r,c-1);//east
      reveal(r,c+1);//west
      reveal(r-1,c-1);//northwest
      reveal(r-1,c+1);//northeast
      reveal(r+1,c+1);//southeast
      reveal(r+1,c-1);//southwest
    }

  }




	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//Left click on a Tile means you're marking it not a mine
		if(arg0.getButton()==1) {
			System.out.println("left");
			TileFinal t = (TileFinal)(arg0.getComponent());
      if(t.isMine){
        // gameOver();
        displayMines();
      }else{
        reveal(t.r, t.c);
      }
			
		}else if(arg0.getButton()==3 || arg0.getButton()==2) {
			
		//Right-Click on a Tile means you're flagging it
			totalFlagged++;
			System.out.println("right"+ totalFlagged);
			checkWon();
			
			TileFinal t = (TileFinal)(arg0.getComponent());
      t.toggle();
      System.out.println("FLAGCRAFT");
      	//icon.paintIcon(this, g, 0, 0);
			//t.setIcon(new ImageIcon("flag.png"));
  		board[t.r][t.c].setText("F");
  		//board[t.r][t.c].setIcon(new ImageIcon("flag.png"));
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
