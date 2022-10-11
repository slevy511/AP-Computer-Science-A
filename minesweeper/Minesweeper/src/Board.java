import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener{
	
	//backend data structure for traversing entire 2D array of Tiles
	Tile[][] board = new Tile[8][10]; 
	
	/* method to generate 10 mines */
	public void generateMines() {
		
		int count = 0;
		while(count < 10) { //generate 10 mines
			
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
		if(!board[r][c].isMine) return; 
		
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
	

  public void checkWon(){
    //traverse the 2d array of Tiles
    //check if the number of Flagged tiles
    //is the same as the number of total mines


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
	

	public Board() {
		//construct the JFrame hodling the tiles!
		JFrame frame = new JFrame("Minesweeper");
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add a layout manager (grid)
		frame.setLayout(new GridLayout(8,10));
		
		
		//add the board!
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Tile t = new Tile(row, col);		//Create a Tile object with a given row,col value
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
    }

  }




	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//Left click on a Tile means you're marking it not a mine
		if(arg0.getButton()==1) {
			System.out.println("left");
			Tile t = (Tile)(arg0.getComponent());
      if(t.isMine){
        // gameOver();
        displayMines();
      }else{
        reveal(t.r, t.c);
      }
			
		}else if(arg0.getButton()==3) {
			
		//Right-Click on a Tile means you're flagging it
			System.out.println("right");
			Tile t = (Tile)(arg0.getComponent());
      t.toggle();
			//t.setIcon(new ImageIcon("flag.png"));
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
