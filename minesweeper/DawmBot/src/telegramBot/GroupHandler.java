package telegramBot;

import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.objects.Update;

public class GroupHandler {
	private ArrayList<TriggerHandler> triggerHandlers = new ArrayList<TriggerHandler>();
	
	public GroupHandler() {
		
	}
	
	public ArrayList<TriggerHandler> getTriggerHandlers() {
		return triggerHandlers;
	}
	
	public void setTriggerHandlers(ArrayList<TriggerHandler> thandlers) {
		triggerHandlers = thandlers;
	}
	
	public Trigger getResponse(Update update) {
		Long chatId = update.getMessage().getChatId();
		for(int i = 0; i < triggerHandlers.size(); i++) {
			if(chatId == triggerHandlers.get(i).getGroupId()) {
				return triggerHandlers.get(i).getResponseImproved(update.getMessage().getText());
			}
		}
		return null;	
	}
	
	/*
	public Double getDoubleResponse(Update update) {
		Long chatId = update.getMessage().getChatId();
		for(int i = 0; i < triggerHandlers.size(); i++) {
			return triggerHandlers.get(i).getDoubleResponse(update.getMessage().getText());
		}
		return null;
	}
	*/
	
	public TriggerHandler getTriggerHandler(long id) {
		for(TriggerHandler t : triggerHandlers) {
			if(t.getGroupId() == id) {
				return t;
			}
		}
		return null;
	}
	
	public int getTotalNumberOfTriggers() {
		int total = 0;
		for(TriggerHandler t : triggerHandlers) {
			total += t.getTriggers().size();
		}
		return total;
	}
	
	public void createNewTriggerHandler(long id, String name) {
		triggerHandlers.add(new TriggerHandler(id, name));
	}
}
