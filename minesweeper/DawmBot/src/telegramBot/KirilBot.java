package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class KirilBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
        	String text = update.getMessage().getFrom().getFirstName();
        	System.out.println(text);
        	/*if(text.toLowerCase().contains("ethan") || text.toLowerCase().contains("samuel")){
        		SendMessage message = new SendMessage();
        		message.setChatId(update.getMessage().getChatId());
        		message.setText("**YOU**");
        		
        		
        		try{
            		execute(message);
            	} catch (TelegramApiException e){
            		e.printStackTrace();
            	}
        	}else*/ if(update.getMessage().getText().toLowerCase().contains("gay")){
            	SendMessage message = new SendMessage();
            	message.setChatId(update.getMessage().getChatId());
            	message.setText("did you say gay? they both dumped their girlfriends at the same time a month apart and must've gone gay for each other I know those two are gay they have to be because why else would"
            			+ "they do it at the same time i'm actually going to kill you");
            	
            	try{
            		execute(message);
            	} catch (TelegramApiException e){
            		e.printStackTrace();
            	}
            }
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "kirril_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "1047488180:AAFz37j6ivD5Gs1qKMICtvSZz1uGW28n2nc";
    }
    
}