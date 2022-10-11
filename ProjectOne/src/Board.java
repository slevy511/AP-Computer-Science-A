
public class Board {

	private int roomCount;
	private int height;
	private int width;
	private String line;
	private char temp;
	private char[][][] coord;
	
	public Board() {
		height = 0;
		width = 0;
		roomCount = 0;
		coord = null;
	}
	
	public Board(int height, int width, int roomCount, char[][][] coord) {
			
			this.height = height;
			this.width = width;
			this.roomCount = roomCount;
			this.coord = coord;
			
		}
	
	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setCoord(char[][][] coord) {
		this.coord = coord;
	}

	
	
	public char[][][] getCoord(){
		return coord;
	}
	
	public void toPrintableString() {
		

		System.out.println(height+" "+width+" "+roomCount); 	//first line of coordinate map
		
		for(int i = 0; i < roomCount; i++) {
			for(int j = 0; j < height; j++) {
				
			//	line = sc.next();
				
				
				for(int k = 0; k < width; k++) {
					
					coord[j][k][i] = temp; 
					
					if(temp != '.') {
						System.out.println(temp+" "+j+" "+k);
					}
				}
			}
		}
		
		
	}
	
}
