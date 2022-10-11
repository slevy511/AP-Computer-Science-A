package telegramBot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DawmBot extends TelegramLongPollingBot {
	
	private ArrayList<UserAdmin> userAdmins = new ArrayList<UserAdmin>();
	
	GroupHandler handler = new GroupHandler();
	
	public DawmBot(){
		super();
		print("initialized", 1);
		
		//tiomthy = 926002299
	    //samuel = 905917803
	    //ethan = 965783314
	    //michael = 978764041
		
		/*userAdmins.add(new UserAdmin(926002299, "Timothy Jacques"));
		userAdmins.add(new UserAdmin(905917803, "Samuel Levy"));
		userAdmins.add(new UserAdmin(965783314, "Ethan Olim"));
		userAdmins.add(new UserAdmin(978764041, "Michael LaBarbera"));*/
		
		
		print("Loading Triggers", 2);
		JSONSaver s = new JSONSaver();
		
		handler.setTriggerHandlers(s.load());
		userAdmins = s.loadAdmins();
		
		print("Done Loading!", 1);
	}
	
	
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
        	UserAdmin user = null;
        	for(int i = 0; i < userAdmins.size(); i++){
	    		if(update.getMessage().getFrom().getId() == userAdmins.get(i).getId()){
	    			user = userAdmins.get(i);
	    			break;
	    		}
	    	}
        	
        	if(update.getMessage().hasText()){
	        	String text = update.getMessage().getText().toLowerCase();
	        	boolean trigger = false;
	        	boolean isPhoto = false;
	        	SendMessage message = new SendMessage();
	        	SendPhoto photo = new SendPhoto();
	        	
	        	if(text.indexOf('/') == 0 || (user.currentMenu != 0 && update.getMessage().isUserMessage())){
	        		trigger = true;
	        		message = menu(update);
	        	}else{
		        	Trigger responseTrigger = handler.getResponse(update);
		        	
		        	if(responseTrigger != null) {
		        		print(responseTrigger.toString(), 0);
			        	if(responseTrigger.isPhoto()) {
			        		isPhoto = true;
			        		trigger = true;
			        		photo.setPhoto(responseTrigger.getMessage());
			        	}else{
			        		trigger = true;
			        		message.setText(responseTrigger.getMessage());
			        	}
		        	}else{
		        		print("null", 0);
		        		
		        		if(text.contains("dawm") || text.contains("dom") || text.contains("?")){
			        		trigger = true;
			        		message.setText(dawm8ballresponse());
			        		
			        	}else if(text.contains("mitch")){
			        		trigger = true;
			        		message.setText(update.getMessage().getFrom().getFirstName() + " is literally crucifying me right now");
			        	}else {
			        		trigger = false;
			        		message.setText("");
			        	}
		        	}
	        		
	        		/*if(response == null){
			        	if(text.contains("dawm") || text.contains("dom")){
			        		trigger = true;
			        		message.setText("no");
			        		
			        		if(text.toLowerCase().contains("nudes")){
			        			message.setText("😍😍😍");
			        		}
			        	}else if(text.contains("mitch")){
			        		trigger = true;
			        		message.setText(update.getMessage().getFrom().getFirstName() + " is literally crucifying me right now");
			        	}
		        	}else{
		        		trigger = true;
		        		message.setText(response);
		        	}*/
	        	}
	        	
	        	
	        	if(trigger && !isPhoto) {
	        		
	        		/////////// SEND TEXT MESSAGE
		        	if(message.getText().length() > 0){
		        		
		        		//split message into multiple messages  if it's too long
		        		if(message.getText().length() > 4096) {
		        			ArrayList<SendMessage> arr = new ArrayList<SendMessage>();
		        			String temp = message.getText();
		        			while(temp.length() > 0){
		        				SendMessage m = new SendMessage();
		        				m.setChatId(update.getMessage().getChatId());
		        				if(temp.length() > 4096) {
		        					m.setText(temp.substring(0, 4095));
		        					arr.add(m);
		        					temp = temp.substring(4096);
		        				}else {
		        					m.setText(temp);
		        					arr.add(m);
		        					temp = "";
		        				}
		        			}
		        			
		        			//send out array of messages;
		        			for(int i = 0; i < arr.size(); i++) {
		        				message = arr.get(i);
		        				try{
		    		        		execute(message);
		    		        	} catch (TelegramApiException e){
		    		        		e.printStackTrace();
		    		        	}
		        			}
		        		}else{
		        			
		        			//send out single message
		        			message.setChatId(update.getMessage().getChatId());
				        	try{
				        		execute(message);
				        	} catch (TelegramApiException e){
				        		e.printStackTrace();
				        	}
		        		}
		        	}
		        	
		        	//////////////// SEND PHOTO MESSAGE
	        	}else if (trigger && isPhoto) {
	        		photo.setChatId(update.getMessage().getChatId());
	        		
	        		try {
	        			execute(photo);
	        		} catch (TelegramApiException e) {
	        			e.printStackTrace();
	        		}
	        	}
        	}
        	
        	if(update.getMessage().hasPhoto() && update.getMessage().isUserMessage() && user.currentMenu == 2) {
        		user.currentMenu = 0;
        		//print("photo received!");
        		
        		handler.getTriggerHandler(user.currentGroupId).getLast().setMessage(update.getMessage().getPhoto().get(0).getFileId());
        		handler.getTriggerHandler(user.currentGroupId).getLast().isPhoto(true);
        		
        		SendMessage message = new SendMessage();
        		message.setChatId(update.getMessage().getChatId());
        		message.setText("Trigger created!");
        		print("New Trigger: " + handler.getTriggerHandler(user.currentGroupId).getLast(), 1);
	        	try{
	        		execute(message);
	        	} catch (TelegramApiException e){
	        		e.printStackTrace();
	        	}
        	}
        }
    }
    
    //tiomthy = 926002299
    //samuel = 905917803
    //ethan = 965783314
    //michael = 978764041
    
    
    public SendMessage menu(Update update){

    	int currentUser = update.getMessage().getFrom().getId();
    	boolean admin = false;
    	UserAdmin user = null;
    	
    	//make sure user that sent message is an admin, and then get their current menuId
    	for(int i = 0; i < userAdmins.size(); i++){
    		if(currentUser == userAdmins.get(i).getId()){
    			admin = true;
    			user = userAdmins.get(i);
    			user.setName(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
    			break;
    		}
    	}
    	
    	if((update.getMessage().isUserMessage() && admin == true) || update.getMessage().getText().equals("/id")){
	    	//create empty sendMessage for the current chat
	    	SendMessage reply = new SendMessage().setChatId(update.getMessage().getChatId());
	    	
	    	TriggerHandler thandler = handler.getTriggerHandler(user.currentGroupId);
	    	
	    	
	    	if(admin){
	    		
	    		//debug - send current menuId
	    		//print(user.currentMenu);
	    		
	    		//check for various states
	    		if(update.getMessage().getText().equals("/cancel")) { //cancel current operation
	    			user.currentMenu = 0;
	    			reply.setText("canceled!");
	    			return reply;
	    		}else if(update.getMessage().getText().equals("/id")){
	    			if(update.getMessage().isUserMessage()) {
	    				reply.setText("Your current group is " + thandler.getGroupName() + ". Send /id in a different group chat to change it.");
	    				return reply;
	    			}else {
		    			user.currentGroupId = update.getMessage().getChatId();
		    			
		    			if(handler.getTriggerHandler(user.currentGroupId) == null) {
		    				handler.createNewTriggerHandler(user.currentGroupId, update.getMessage().getChat().getTitle());
		    			}else{ 
		    				handler.getTriggerHandler(user.currentGroupId).setGroupName(update.getMessage().getChat().getTitle()); 
		    			}
		    			
		    			user.currentMenu = 0;
		    			reply.setText("ID set!");
		    			return reply;
	    			}
	    			
	    		}else if(user.currentGroupId == 0) {
	    			reply.setText("Please set the group ID that you want these settings to apply to. Send /id in that group chat and I'll set it up for you!");
	    			return reply;
	    			
	    		}else if(update.getMessage().getText().equals("/add")){
	    			user.currentMenu = 1;
	    		//TODO: add support to put trigger inside of /add call
	    			
	    		}else if(user.currentMenu == 1){
	    			//create a new trigger for the sent word if it doesn't already exist
	    			try {
		    			if(!thandler.triggerExists(update.getMessage().getText().toLowerCase())){
		    				thandler.add(new Trigger(update.getMessage().getText().toLowerCase(),"", update.getMessage().getFrom().getFirstName()));
			    			user.currentMenu = 2; //go to next menu
		    			}else{
		    				user.currentMenu = 10; //send error
		    			}
	    			} catch(Exception e){
	    				e.printStackTrace();
	    			}
	    		
	    		}else if(user.currentMenu == 2){
	    			//get last trigger and update the message that was set to 
	    			thandler.getLast().setMessage(update.getMessage().getText());
	    			user.currentMenu = 3; // confirmation message
	    		
	    		}else if(user.currentMenu == 15){
	    			
	    			//check if the trigger to be deleted exists & if so, delete it
	    			if(thandler.triggerExists(update.getMessage().getText().toLowerCase())){
	    				thandler.delete(update.getMessage().getText().toLowerCase());
	    				reply.setText("deleted!");
	    				user.currentMenu = 0;
	    				return reply;
	    			}else{
	    				reply.setText("Nothing triggers from that!");
	    				return reply;
	    			}
	    			
	    		}else if(update.getMessage().getText().length() > 10 && update.getMessage().getText().substring(0,9).equals("/addadmin")) {
		    		
	    			int newid = Integer.parseInt(update.getMessage().getText().substring(10));
	    			for(UserAdmin u : userAdmins) {
	    				if(u.getId() == newid) {
	    					reply.setText("That user is already an admin!");
	    					return reply;
	    				}
	    			}
	    			print(update.getMessage().getFrom().getFirstName() + " has added " + newid + " as an admin.", 3);
	    			userAdmins.add(new UserAdmin(newid, ""));
	    			reply.setText("Admin has been added!");
	    			return reply;	
	    			
	    		}else if(update.getMessage().getText().length() > 9 && update.getMessage().getText().substring(0,8).equals("/rmadmin")) {
	    			int newid = Integer.parseInt(update.getMessage().getText().substring(9));
	    			for(int i = 0; i < userAdmins.size(); i++) {
	    				if(userAdmins.get(i).getId() == newid) {
	    					print(update.getMessage().getFrom().getFirstName() + " has removed " + userAdmins.get(i).getName() + " from admin status.", 3);
	    					reply.setText(userAdmins.get(i).getName() + " has been removed as admin");
	    					userAdmins.remove(i);
	    					return reply;
	    				}
	    			}
	    			reply.setText("There is no admin with that ID!");
	    			return reply;
	    			
	    		} else if(update.getMessage().getText().equals("/viewadmins")) {
	    			String temp = "";
	    			for(UserAdmin u : userAdmins) {
	    				temp += u.toString();
	    				temp += "\n";
	    			}
	    			reply.setText(temp);
	    			return reply;
	    			
	    		}else if(update.getMessage().getText().equals("/export")){
	    			String resp = "Group Name: " + thandler.getGroupName();
	    			for(Trigger t : thandler.getTriggers()){
	    				resp += t.toString();
	    				resp += "\n\n";
	    			}
	    			
	    			reply.setText(resp);
	    			return reply;
	    			
	    		}else if(update.getMessage().getText().equals("/save")){
	    			System.out.println("darnit");
	    			new JSONSaver().save(handler);
	    			new JSONSaver().saveAdmins(userAdmins);
	    			reply.setText("saved " + handler.getTotalNumberOfTriggers() + " triggers & " + userAdmins.size() + " admins!");
	    			
	    			return reply;
	    			
	    		}else if(update.getMessage().getText().equals("/delete")){
	    			user.currentMenu = 15;
	    		}else if(update.getMessage().getText().indexOf("/view") == 0) {
	    			String d = thandler.getTrigger(update.getMessage().getText().substring(6)).toString();
	    			if(d.equals("null")) {
	    				reply.setText("That trigger doesn't exist!");
	    			}else {
	    				reply.setText(d);
	    			}
	    			return reply;
	    		}else if(update.getMessage().getText().equals("/exportall")){
	    			String resp = "";
	    			for(int i = 0; i < handler.getTriggerHandlers().size(); i++) {
	    				resp += handler.getTriggerHandlers().get(i).toString() + "\n\n";
	    			}
	    			reply.setText(resp);
	    			return reply;
	    		
	    		}else if(update.getMessage().getText().equals("/help")){
	    			reply.setText(
	    					  "All commands:\n"
	    					+ "/add - add a new trigger to dawmbot\n"
	    					+ "/delete - delete a trigger from dawmbot\n"
	    					+ "/view [trigger]- view message and creator of a trigger\n"
	    					+ "/export - export all triggers for this group\n"
	    					+ "/exportall - export ALL triggers (even those from other groups)\n"
	    					+ "/help - display this help message\n"
	    					+ "/save - save all triggers to server\n"
	    					+ "/id - send this in a group channel with dawmbot in it to change to that group, or directly to dawmbot to view currently selected group");
	    			return reply;
	    		}
	    		
	    		
	    		//TODO: make this much more efficient or at least put things in a more logical order
	    		switch(user.currentMenu){
	    		case 0: //starter text
	    			reply.setText("Send /add to add a new message");
	    			break;
	    		case 1: // sent /add
	    			reply.setText("Okay, your trigger will be for the group called " + thandler.getGroupName() + ". Send me the word that you want to trigger this message");
	    			break;
	    		case 2:
	    			reply.setText("Okay, the trigger will be "+thandler.getLast().getTrigger() + ". Now send me the message reply.");
	    			break;
	    		case 3:
	    			reply.setText("Cool! Your new message is " + thandler.getLast().getMessage()+". You're all set now!");
	    			user.currentMenu = 0;
	    			break;
	    		case 10:
	    			reply.setText("Sorry, that trigger already has a message assigned to it!");
	    			user.currentMenu = 0;
	    			break;
	    		case 15:
	    			reply.setText("Which trigger do you want to delete?");
	    			break;
	    		default:
	    			reply.setText("Oh no! Tell tim he broke something. MenuId:"+user.currentMenu);
	    			break;
	    			
	    		}
	    		
	    		
	    	}else if(update.getMessage().getText().equals("/userid")) {
    			reply.setText("Your User ID is: " + update.getMessage().getFrom().getId());
	    	
	    	}else{
	    		reply.setText("Sorry, you don't have edit access to me! Talk to Tim if you think this is incorrect.");
	    	}
	    	return reply;
    	}else if(update.getMessage().getText().equals("/userid")) {
    		SendMessage reply = new SendMessage().setChatId(update.getMessage().getChatId());
			reply.setText("Your User ID is: " + update.getMessage().getFrom().getId());
			return reply;
    	}
    	
    	return null;
    }
    
    
    private String dawm8ballresponse() {
    	int choice = (int)(Math.random()*17);
    	switch(choice) {
    	case 0:
    		return "Don't count on it.";
    	case 1:
    		return "My reply is no.";
    	case 2:
    		return "My autograder says no.";
    	case 3:
    		return "Such is life.";
    	case 4:
    		return "Shahar, ask Tim.";
    	case 5:
    		return "Your logic score is 100%";
    	case 6:
    		return "Let's take a 15 minute break.";
    	case 7:
    		return "You have Dawm's seal of approval";
    	case 8:
    		return "Hey that's pretty beyotchin!";
    	case 9:
    		return "yes ethan and sammy are gay for each other";
    	case 10:
    		return "Yes.";
    	case 11:
    		return "oo que bueno";
    	case 12:
    		return "No.";
    	case 13:
    		return "I'm not a robot stop asking!";
    	case 14:
    		return "oops fuck";
    	case 15:
    		return "Assaf hacked my server again please come back later";
    	default:
    		return "darnit darnit darnit you're not supposed to bite it!";
    	}
    }
    

    private void print(Object o, int errorstate) {
		BotUtilities.print(getBotUsername(),o.toString(), errorstate);
	}
    
    @Override
    public String getBotUsername() {
        // TODO
        return "dawmbot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "853888001:AAE8pNpoEAIUVv-WsvLzybu_auNyvRh-FGU";
    	//return "1018720380:AAHQeXzsG2oGRWa2TKf2gOMRRj2uUhmT1w0"; //(dawmtestbot)
    }
    
}