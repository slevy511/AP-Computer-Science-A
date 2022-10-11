
public class Location extends Board{
	
	private int roomCount;
	private int height;
	private int width;
	private String line;
	private char[][][] coord;
	private char temp;

public Location(char temp, int height, int width, int roomCount, char[][][] coord) {
	
	super(height,width,roomCount,coord);
	this.temp=temp;
}
}
