package telegramBot;

public class Trigger {
	private String text;
	private String reply;
	private String creator;
	private int timesRun;
	private boolean isPhoto;
	
	public Trigger(String t, String m, String creator, boolean isPhoto) {
		text = t;
		reply = m;
		this.creator = creator;
		this.isPhoto = isPhoto;
		timesRun = 0;
	}
	
	
	///TODO: delete once JSONSaver is migrated
	public Trigger(String t, String m, String creator) {
		text = t;
		reply = m;
		this.creator = creator;
		this.isPhoto = false;
		timesRun = 0;
	}
	
	public String getMessage(){
		timesRun++;
		return reply;
	}
	
	public String getTrigger(){
		return text;
	}
	
	public void setMessage(String s){
		reply = s;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public boolean isPhoto() {
		return isPhoto;
	}
	
	public void isPhoto(boolean p) {
		isPhoto = p;
	}
	
	public int getTimesRun() {
		return timesRun;
	}
	
	public String toString() {
		if(isPhoto) {
			return "trigger: "+ text + " photo ID: "+ reply + " creator: "+ creator + " timesRun: " + timesRun;
		}
		return "trigger: "+ text + " message: "+ reply + " creator: "+ creator + " timesRun: " + timesRun;
	}
}
