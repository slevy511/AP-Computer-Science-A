import java.util.ArrayDeque;
import java.util.ArrayList;


public class QueueAlgorithm {

	ArrayDeque<Location> Q = new ArrayDeque<Location>();	//tiles being checked
	
	ArrayDeque<Location> D = new ArrayDeque<Location>(); //possible tiles
	
	//Board board;
	//coord coord;
	
	boolean foundKirby = false;
	boolean foundCake = false; 
	
	public QueueAlgorithm(/*Board board*/) {
		//this.board = board; 
		
	}
	
	
	
	public ArrayDeque<Location> findTiles(Board board) {
		try {
		for(int k = 0; k < board.getRoomCount(); k++) {	
			
			for(int i = 0; i < board.getHeight(); i++) {
				
				for(int j = 0; j <board.getWidth(); j++) {
					if(board.getCoord()[i][j][k]=='K' && foundKirby==false && foundCake==false) {
						//found kirby! start the path-finding algorithm
						Location temp = new Location('K', i, j, k, board.getCoord());
						Q.add(temp);
						foundKirby=true;
						D.add(temp);
						Q.remove(temp);
					}
					if(foundKirby==true && foundCake==false) {
						if(i>0) {
						if(board.getCoord()[i-1][j][k]=='C') {
							//found cake!
							k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('C', i-1, j, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if(board.getCoord()[i-1][j][k]=='|') {
							//k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('|', i-1, j, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if (board.getCoord()[i - 1][j][k] == '.') {
							Location temp = new Location('.', i-1, j, k, board.getCoord());	
							Q.add(temp);					
						}}
						if(i<board.getHeight()-1) {
						if(board.getCoord()[i+1][j][k]=='C') {
							//found cake!
							k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('C', i+1, j, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if(board.getCoord()[i+1][j][k]=='|') {
							//k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('|', i+1, j, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if (board.getCoord()[i + 1][j][k] == '.') {
							Location temp = new Location('.', i+1, j, k, board.getCoord());	
							Q.add(temp);					
						}}
						if(j<board.getWidth()-1) {
						if(board.getCoord()[i][j+1][k]=='C') {
							//found cake!
							k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('C', i, j+1, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if(board.getCoord()[i][j+1][k]=='|') {
							//k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('|', i, j+1, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if (board.getCoord()[i][j+1][k] == '.') {
							Location temp = new Location('.', i, j+1, k, board.getCoord());	
							Q.add(temp);					
						}}
						if(j>0)
						if(board.getCoord()[i][j-1][k]=='C') {
							//found cake!
							k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('C', i, j-1, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if(board.getCoord()[i][j-1][k]=='|') {
							//k = board.getRoomCount();
							i = board.getHeight();
							j = board.getWidth();
						Location temp = new Location('|', i, j-1, k, board.getCoord());
							Q.add(temp);
							D.add(temp);	//double check on this line
							Q.remove(temp); //double check on this line
						}else if (board.getCoord()[i][j-1][k] == '.') {
							Location temp = new Location('.', i, j-1, k, board.getCoord());	
							Q.add(temp);					
						}
						
						Location temp = Q.peekFirst();
						D.add(temp);
						Q.remove();
						
						i = temp.getHeight()-1;
						j = temp.getWidth()-1;
						k = temp.getRoomCount() - 1;
						
						
						}
					}
				}
		}
		System.out.println(D);
		return D;
		
		}catch(Exception e) {
			
		
			System.out.println("darnit! darnit! darnit!");
			e.printStackTrace();
			
			return D;
		}
			}
	
		
	
	
	
	//now myQ behaves like a queue
	//first in first out
	//look through helper methods to see which ones could be helpful
	//myQ.add(1);
	//myQ.add(12);
	//myQ.add(13);
	//System.out.println(myQ.peekFirst()+"should be 1");
	
	
}
