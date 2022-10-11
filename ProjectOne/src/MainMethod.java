import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
//9:29
public class MainMethod {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		int count = 0;
		boolean isStack = false;
		boolean isQueue = false;
		boolean isOpt = false;
		Board board;
		
		
		
		
		String[] args2 = {"--Stack", "Cmap5"};
		
		String fileName = args2[args2.length-1];
				// keep input file in the parent folder for the project
				// not in the src folder
				File filePath = new File(fileName); // point to file
				//Board board = new Board(height, width, roomCount, coord);
				Scan scan = new Scan(filePath);
				//Board board = new Board(height, width, roomCount, coord);
		
				
				
				//map to coord
			//	scan.scannerReader(fileName);
				
				//coord to map
				scan.scannerReaderCM(fileName);
				
				
				
				scan.findPossibleTiles(/*board*/);
				
				
	}

}











/*
public class p1 {
	public static void main(String[] args) {
		int count = 0;
		boolean isStack = false;
		boolean isQueue = false;
		boolean isOpt = false;
		Tile[][] world = null;
		Tile[][] world2 = null;
		File file;
		int r = 0, c = 0;
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("--Stack") || args[i].equals("--Queue") || args[i].equals("--Opt")) {
				count++;
			}
			
			if(args[i].equals("--Stack")) {
				isStack = true;
			}
			
			if(args[i].equals("--Queue")) {
				isQueue = true;
			}
			
			if(args[i].equals("--Opt")) {
				isOpt = true;
			}
			
			if(args[i].equals("Incoordinate")) {
				
			}
			
			if(args[i].equals("Outcoordinate")) {
				
			}
			
		}
		if(count != 1) {
			System.out.println("Legal command line inputs must include either --Stack, --Queue, or --Opt");
			System.exit(-1);
		}
		
		file =  new File("kirbymap");
		
		try{
			Scanner sc = new Scanner (file);  //set up scanner
			r = sc.nextInt();
		    c = sc.nextInt();
			world = new Tile[r][c];
			sc.nextLine(); //
			int rec = 0;
			boolean isHere = false;
			while(sc.hasNextLine()&& rec < r){
				String line = sc.nextLine();
				for(int i = 0; i < c; i++){
					world[rec][i] = new Tile();
					world[rec][i].placeTile(rec, i, line.charAt(i));
					if(line.charAt(i) == 'C'){
						isHere=true;
					}
				}
				rec++;
			}
			
			
			if(isHere==false){
				System.out.println("The cake is a lie");
				System.exit(-1);
			}
			sc.close();
		}
		
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		
		
		System.out.println("Text-Based Solution:");
		
		System.out.println("Original:");
		printTextBased(world);
		
		if(isStack) {
			StackSolution stacksol = new StackSolution(r, c, world);
			world = stacksol.findPath();
			System.out.println("Solution: ");
			printTextBased(world);
			
		}
		
		if(isQueue) {
			QueueSolution qsol = new QueueSolution(r, c, world);
			world = qsol.findPath();
			System.out.println();
			System.out.println("Solution:");
			printTextBased(world);
		}
			
	}
	
	public static void printTextBased(Tile[][] og) {
		for(int i = 0; i < og.length; i++) {
			for(int j = 0; j < og[0].length; j++) {
				System.out.print(og[i][j].getC());
			}
			System.out.println();
		}

	}
	
	public static void printCoordinateBased() {
		//create tile array
		
	}
	
}
*/