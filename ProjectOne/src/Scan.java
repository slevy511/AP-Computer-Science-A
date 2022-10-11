import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Scan {

	private File filePath; // pont to file

	private int roomCount;
	private int height;
	private int width;
	private String line;
	private char temp;
	private char[][][] coord;
	String temp1 = "";
	String temp2 = "";
	String temp3 = "";
	boolean nextLine = true;
	
	Board board = new Board();
	
	ArrayDeque<Location> Q = new ArrayDeque<Location>();	//tiles being checked
	
	ArrayDeque<Location> D = new ArrayDeque<Location>(); //possible tiles
	
	QueueAlgorithm que = new QueueAlgorithm(/*board*/);

	////File file = new File("map5");  //point to file

	public Scan(File filePath) throws FileNotFoundException {
		this.filePath = filePath;
	}
	

		public void scannerReader(String fileName) throws FileNotFoundException { //go from map-based to coord-based
			
			Scanner sc = new Scanner(filePath); // setup scanner
			
			height = sc.nextInt();
			width = sc.nextInt();
			roomCount = sc.nextInt();
			
			coord = new char[height][width][roomCount];
			
			System.out.println(height+" "+width+" "+roomCount); 	//first line of coordinate map
			
			for(int i = 0; i < roomCount; i++) {
				for(int j = 0; j < height; j++) {
					
					line = sc.next();
					
					for(int k = 0; k < width; k++) {
						temp = line.charAt(k);
						coord[j][k][i] = temp; 
						
						if(temp != '.') {
							System.out.println(temp+" "+j+" "+k);
						}
					}
				}
			}
			
			board.setHeight(height);
			board.setWidth(width);;
			board.setRoomCount(roomCount);
			board.setCoord(coord);
			
			sc.close(); // done with scanner
			
			
		}
		
		public void scannerReaderCM(String fileName) throws FileNotFoundException { //go from  coord-based to map-based 
					
					Scanner sc = new Scanner(filePath); // setup scanner
					
					height = sc.nextInt();
					width = sc.nextInt();
					roomCount = sc.nextInt();
					
					coord = new char[height][width][roomCount];
					
					System.out.println(height+" "+width+" "+roomCount); 	//first line of coordinate map
					
					for(int i = 0; i < roomCount; i++) {
						for(int j = 0; j < height; j++) {
							
							for(int k = 0; k < width; k++) {
								//line = sc.next();
							if(nextLine == true) {
								temp1 = sc.next();
								//System.out.println(temp1);
								temp2 = sc.next();
								//System.out.println(temp2);
								temp3 = sc.next();
								//System.out.println(temp3);
								line = (temp1+" "+temp2+" "+temp3);
							}

						//		System.out.println("LINE:"+j+k+i+line);
				
								if(line.indexOf(j+" "+k)==-1) {
									coord[j][k][i] = '.';
									nextLine = false;
									//coord[j][k][i] = '.';
								}else{
									coord[j][k][i] = line.charAt(0);
									nextLine = true;
									//coord[j][k][i] = 'C';
								}
								
								if(k==width-1) {
									System.out.println(coord[j][k][i]);
								}else {
									System.out.print(coord[j][k][i]);
								}
								
								
								board.setHeight(height);
								board.setWidth(width);;
								board.setRoomCount(roomCount);
								board.setCoord(coord);
								
								/*if(coord[j] != '.') {
									
									System.out.println(temp+" "+j+" "+k);
								}*/
							}
						}
					}
					
					sc.close(); // done with scanner
					
					
				}
		
	public ArrayDeque<Location> findPossibleTiles(/*Board board*/){
		return que.findTiles(board);
	}
	
		
		/*
		public Board Reader() {
			
			Scanner sc = scannerLoader(filePath); //need to add scannerLoader method
			
			Board board;
			
			width = sc.nextInt();
			
			height = sc.nextInt();
			
			roomCount = sc.nextInt();
			
			coord = new char[height][width][roomCount];
			
		System.out.println(height+" "+width+" "+roomCount); 	//first line of coordinate map
			
			for(int i = 0; i < roomCount; i++) {
				for(int j = 0; j < height; j++) {
					
					line = sc.next();
					
					for(int k = 0; k < width; k++) {
						temp = line.charAt(k);
						coord[j][k][i] = temp; 
						
						if(temp != '.') {
							System.out.println(temp+" "+j+" "+k);
						}
					}
				}
			}
			
			sc.close(); // done with scanner

			
		}
		
		*/
		
		//board class stores board and has board to string method to print it out
	
		// TODO Auto-generated method stub

		//public void maptoCoord(File file) {
			
		
			
		
		// keep input file in the parent folder for the project
		// not in the src folder
		//File file = new File("map5"); // point to file
		
		/*
	
		try {

			Scanner sc = new Scanner(filePath); // setup scanner
			
			height = sc.nextInt();
			width = sc.nextInt();
			roomCount = sc.nextInt();
			
			coord = new char[height][width][roomCount];
			
			System.out.println(height+" "+width+" "+roomCount); 	//first line of coordinate map
			
			for(int i = 0; i < roomCount; i++) {
				for(int j = 0; j < height; j++) {
					
					line = sc.next();
					
					for(int k = 0; k < width; k++) {
						temp = line.charAt(k);
						coord[j][k][i] = temp; 
						
						if(temp != '.') {
							System.out.println(temp+" "+j+" "+k);
						}
					}
				}
			}
			
			sc.close(); // done with scanner

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		

	}

	public char[][][] getBoard() {
		return board;
	}*/
}
