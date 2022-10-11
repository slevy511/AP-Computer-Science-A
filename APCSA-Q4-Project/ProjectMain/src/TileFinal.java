import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TileFinal extends JButton{
	 /**
	 * 
	 */
	//private static final long serialVersionUID = -7867449409881421944L;
	int r,c;   //position
	 int count; //how many surrounding bomb does it have?
	boolean isMine;
  boolean flagged;
	
	public TileFinal(int r, int c) {
		this.r = r;
		this.c = c;
    flagged = false;
	}

  public void toggle(){
    flagged = !flagged;
    if(flagged){
      this.setIcon(new ImageIcon("flag.png"));
    }else{
      this.setIcon(null);
    }
  }

	public boolean isMine() {
	return isMine;
}

public void setMine(boolean isMine) {
	this.isMine = isMine;
}

public boolean isFlagged() {
	return flagged;
}

public void setFlagged(boolean flagged) {
	this.flagged = flagged;
}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	
	
	
}