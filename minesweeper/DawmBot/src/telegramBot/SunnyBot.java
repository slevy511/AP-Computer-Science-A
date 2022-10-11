package telegramBot;

//import java.util.ArrayList;
import java.util.Scanner;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SunnyBot extends TelegramLongPollingBot {
	//private int[] userAdmins = {926002299, 905917803, 965783314, 978764041};
	//private int[] currentMenu = {0,0,0,0};
	//private long[] currentGroupIdSet = {-1001193489861L,-1001193489861L,-1001193489861L,-1001193489861L};
	
	//GroupHandler handler = new GroupHandler();
	
	public SunnyBot(){
		super();
		print("initialized", 1);
	}
	
	public Double getDoubleResponse(Update update){
		String message = update.getMessage().getText();
		if(isDoubles(message)) {
			print(String.valueOf(getDoubles(message)), 0);
			return getDoubles(message);
		}
		return null;
	}
	
	public Double getDoubles(String text) {
		Scanner sc = new Scanner(text);
		Double temp = null;
		while(sc.hasNext()) {
			if(sc.hasNextDouble()) {
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
	
	public boolean triggerExists(String trigger){
		if(getDoubles(trigger) != 0) {
			return true;
		}
		return false;
	}
	
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
        	String text = update.getMessage().getText().toLowerCase();
        	boolean trigger = false;
        	SendMessage message = new SendMessage();
        	
        	
        	if(text.indexOf('/') == 0){
        		trigger = true;
        		message = menu(update);
        	}else{
	        	double response = getDoubleResponse(update);
	        	
	        	trigger = true;
	        	//print(update.getMessage().getText());
	        	String conversion = "Hi, Sunny here. " + String.valueOf(response) + " is approximately " + String.valueOf(response / 70.0) + " ASU Application Fees!";
	        	message.setText(conversion);
	        	
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
    
    //tiomthy = 926002299
    //samuel = 905917803
    //ethan = 965783314
    //michael = 978764041
    
    
    public SendMessage menu(Update update){

    	if(update.getMessage().isUserMessage()){
	    	SendMessage reply = new SendMessage().setChatId(update.getMessage().getChatId());
	    	
	    	if(update.getMessage().getText().equals("/help")){
	    		reply.setText("Sunny Here! Add me to a group and make me admin to learn more about the ASU Application Process");
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
        // TODO
        return "SunnyTheBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "917970256:AAFlusf-sTDWOMwiIKlHBgG3wlTmV3zU1uI";
    	//return "1018720380:AAHQeXzsG2oGRWa2TKf2gOMRRj2uUhmT1w0"; (dawmtestbot)
    }
    
}