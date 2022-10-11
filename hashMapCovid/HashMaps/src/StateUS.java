public class StateUS{
	
	int confirmed = 0;
	int deaths = 0;
	int recovered = 0;
	int active = 0;
	
	public StateUS() {
		
	}
	
	//allow the printing of a State object
	public String toString() {
		//System.out.println("c: "+confirmed);
		//System.out.println("d: "+deaths);
		//System.out.println( "r: "+recovered);
		//System.out.println( "a: "+active);
		return("c: "+confirmed+", d:"+deaths+", r: "+recovered+", a: "+active);
	}
	
	public void add(int c, int d, int r, int a) {
		confirmed+=c;
		deaths+=d;
		recovered+=r;
		active +=a;
	}
	
	
	
	
	
}
