package telegramBot;

import java.util.ArrayList;

public class TriggerHandler {
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();
	private long groupId;
	private String groupName;
	
	public TriggerHandler(long id, String name){
		groupId = id;
		groupName = name;
	}
	
	public TriggerHandler(ArrayList<Trigger> start, long id, String name) {
		triggers = start;
		groupId = id;
		groupName = name;
	}
	
	public Trigger getResponse(String message){
		for(Trigger t : triggers){
			if(message.toLowerCase().contains(t.getTrigger())){
				return t;
			}
		}
		
		return null;
	}
	
	/*
	public Double getDoubleResponse(String message){
		if(isDoubles(message)) {
			System.out.println(String.valueOf(getDoubles(message)));
			return getDoubles(message);
		}
		return null;
	}
	*/
	
	public Trigger getResponseImproved(String message) {
		ArrayList<Trigger> matches = new ArrayList<Trigger>();
		for(Trigger t : triggers) {
			if(message.toLowerCase().contains(t.getTrigger())) {
				matches.add(t);
			}
		}
		
		int minRuns = Integer.MAX_VALUE;
		Trigger resp = null;
		
		for(Trigger t : matches) {
			if(t.getTimesRun() <= minRuns) {
				resp = t;
				minRuns = t.getTimesRun();
			}
		}
		
		return resp;
	}
	
	public long getGroupId() {
		return groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String name) {
		groupName = name;
	}
	
	public void setTriggers(ArrayList<Trigger> t){
		triggers = t;
	}
	
	
	public Trigger getLast(){
		return triggers.get(triggers.size()-1);
	}
	
	public ArrayList<Trigger> getTriggers(){
		return triggers;
	}
	
	public Trigger getTrigger(String text) {
		for(Trigger t : triggers){
			if(text.contentEquals(t.getTrigger())){
				return t;
			}
		}
		return null;
	}
	
	/*
	public Double getDoubles(String text) {
		Scanner sc = new Scanner(text);
		Double temp = null;
		while(sc.hasNext()) {
			if(sc.hasNextDouble()) {
				//double temp = sc.nextDouble();
				//sc.close();
				temp = sc.nextDouble();
				break;
			}
			sc.next();
		}
		sc.close();
		return temp;
		
	}
	
	public boolean isDoubles(String text) {
		if(getDoubles(text) != null) {
			return true;
		}
		return false;
	}
	
	public String getDoublesString(String text) {
		return String.valueOf(getDoubles(text));
	}
	*/
	
	public boolean triggerExists(String trigger){
		for(Trigger t : triggers){
			if(trigger.contentEquals(t.getTrigger())){
				return true;
			}
		}
		return false;
	}
	
	public void add(Trigger t){
		triggers.add(t);
	}
	
	public void delete(String trigger){
		for(int i = 0; i < triggers.size(); i++){
			if(trigger.contentEquals(triggers.get(i).getTrigger())){
				triggers.remove(i);
				break;
			}
		}
	}
	
	
	public String toString() {
		String reply = "Group ID: ";
		reply += groupId + " Group Name: " + groupName + "\n";
		for(int i = 0; i < triggers.size(); i++) {
			reply += triggers.get(i).toString() + "\n";
		}
		return reply;
	}
}
