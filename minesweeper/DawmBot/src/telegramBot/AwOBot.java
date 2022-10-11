package telegramBot;

import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class AwOBot extends TelegramLongPollingBot{

	public AwOBot() {
		super();
		print("initialized", 1);
	}
	

	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage()) {
			if(update.getMessage().hasText()) {
				
				//print("Received!");
				String text = update.getMessage().getText().toLowerCase();
				String response = "";
				
				
				for (int i = 0; i < text.length() - 1; i++) {

					// print(text.substring(i,i+2));
					if (text.substring(i, i + 2).equals("on")) {
						print("found on at index: " + i, 0);

						for (int j = i; j >= 1; j--) {
							if (text.charAt(j - 1) == ' ' || text.charAt(j - 1) == '\n' || j == 1) {
								String temp = "";
								if (j == 1) {
									temp = text.substring(0, i);
								} else {
									temp = text.substring(j, i);
								}
								temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
								temp += "awn";
								response += "\n" + temp;
								// print(temp);
								// print(response);
								break;
							}
						}
					}

					if (i < text.length() - 2) {
						if (text.substring(i, i + 3).equals("awn")) {
							print("found awn at index: " + i, 0);

							for (int j = i - 1; j >= 1; j--) {
								if (text.charAt(j - 1) == ' ' || text.charAt(j - 1) == '\n' || j == 1) {
									String temp = "";
									if (j == 1) {
										temp = text.substring(0, i - 1);
									} else {
										temp = text.substring(j, i - 1);
									}
									temp += "on";
									response += "\n" + temp;
									break;
								}
							}
						}
					}
					
					// print(text.substring(i,i+2));
					if (text.substring(i, i + 2).equals("om")) {
						print("found om at index: " + i, 0);

						for (int j = i; j >= 1; j--) {
							if (text.charAt(j - 1) == ' ' || text.charAt(j - 1) == '\n' || j == 1) {
								String temp = "";
								if (j == 1) {
									temp = text.substring(0, i);
								} else {
									temp = text.substring(j, i);
								}
								temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
								temp += "awm";
								response += "\n" + temp;
								// print(temp);
								// print(response);
								break;
							}
						}
					}

					if (i < text.length() - 2) {
						if (text.substring(i, i + 3).equals("awm")) {
							print("found awm at index: " + i, 0);

							for (int j = i - 1; j >= 1; j--) {
								if (text.charAt(j - 1) == ' ' || text.charAt(j - 1) == '\n' || j == 1) {
									String temp = "";
									if (j == 1) {
										temp = text.substring(0, i - 1);
									} else {
										temp = text.substring(j, i - 1);
									}
									temp += "om";
									response += "\n" + temp;
									break;
								}
							}
						}
					}

				}

					
					print("final message: " + response, 0);
					
					SendMessage message = new SendMessage();
					message.setChatId(update.getMessage().getChatId());
					message.setText(response);
					
					if(response.length() > 20000) {
						message.setText("fuck off");
					}
					
					
					///split up message if it's too long
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
			}
		}
	
	
	
    private void print(Object o, int errorstate) {
		BotUtilities.print(getBotUsername(),o.toString(), errorstate);
	}
	
	
	@Override
	public String getBotUsername() {
		return "DonDawnDomDawmBot";
	}

	@Override
	public String getBotToken() {
		return "1190444760:AAHatCaMXVsMG0YwpW-vYvIEv60L_Jywn6w";
	}

}
