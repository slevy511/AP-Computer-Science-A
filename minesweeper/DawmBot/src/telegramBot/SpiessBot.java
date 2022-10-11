package telegramBot;

//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Timer;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SpiessBot extends TelegramLongPollingBot {
	//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    //private ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
	//private ZonedDateTime nextRun = now.withHour(14).withMinute(52).withSecond(0);
	
	static ArrayList<Long> chatIDs = new ArrayList<>();
	//Runnable runnable = new Runnable();
	
	public SpiessBot() {
		super();
		print("Initialized", 1);
	}
	
	public void sender() {
    	SendMessage message = new SendMessage();
		message.setText("Don't do drugs!");
		print("Don't do drugs!", 0);
		
		for(int i = 0; i < chatIDs.size(); i++) {
			message.setChatId(chatIDs.get(i));
			try{
	    		execute(message);
	    	} catch (TelegramApiException e){
	    		e.printStackTrace();
	    	}
		}
	}
	
	public SendMessage menu(Update update){

    	if(update.getMessage().isUserMessage()){
	    	SendMessage reply = new SendMessage().setChatId(update.getMessage().getChatId());
	    	
	    	if(update.getMessage().getText().equals("/help")){
	    		reply.setText("Hey I'm SpiceBot, add me to a chat, make me admin, and send '/start' for some helpful reminders! Send the following in your chat for more options:/n"
	    				+ "'/start' - Activate SpiceBot in your chat./n"
	    				+ "'/end' - Deactivate SpiceBot in your chat./n"
	    				+ "'/status' - Returns the current status of SpiceBot in your chat.");
	    	}else {
	    		return null;
	    	}
	    	return reply;
    	}
    	return null;
    }
	
	private void print(Object o, int errorstate) {
		BotUtilities.print(getBotUsername(),o.toString(), errorstate);
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "SpiceTheBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		if(update.hasMessage() && update.getMessage().hasText()) {
			String text = update.getMessage().getText();
			boolean trigger = false;
			SendMessage message = new SendMessage();

			boolean IDExists = false;
			int IDIndex = -1;
			if(chatIDs.size() > 0) {
				for(int i = 0; i < chatIDs.size(); i++) {
					if(update.getMessage().getChatId().equals(chatIDs.get(i))) {
						IDExists = true;
						IDIndex = i;
					}
				}
			}
			//System.out.println("here: " + update.getMessage().getChatId() + " IDs: " + " size: " + chatIDs.size());
			
			if(text.equals("/start")) {
				if(!IDExists) {
					trigger = true;
					message.setText("Started");
					chatIDs.add(update.getMessage().getChatId());
				}else {
					trigger = true;
					message.setText("Oops! This chat has already been added!");
				}
			}else if(text.equals("/end")) {
				if(IDExists) {
					trigger = true;
					message.setText("Your chat has been deactivated, Don't do Drugs!");
					chatIDs.remove(IDIndex);
				}else {
					trigger = true;
					message.setText("Your chat is not currently active and cannot be deactivated. Send '/start' to activate it!");
				}
			}else if(text.contentEquals("/status")) {
				if(IDExists) {
					trigger = true;
					message.setText("Your chat is currently active! Send '/end' to deactivate it!");
				}else {
					trigger = true;
					message.setText("Your chat is not currently active! Send '/start' to activate it!");
				}
			}
			
			if(trigger && message.getText().length() > 0){
	    		
	    		message.setChatId(update.getMessage().getChatId());
	        	try{
	        		execute(message);
	        	} catch (TelegramApiException e){
	        		e.printStackTrace();
	        	}
	    	}
		}	
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "976981146:AAH6FN3GFxVT9WuGUUvvl0XmssAM5uVuDkc";
		//return "1147486859:AAE4mUeVmYlm9Eyf4IWW7vUfmFpmP7uZbUs"; //spicetestbot
	}
	
}
