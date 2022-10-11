package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ItItItBot extends TelegramLongPollingBot {

	int spaceIndex;
	int itIndex;
	boolean hasRun;
	String whatItL;

	boolean containsIt = false;
// boolean willRespond = true;

	public ItItItBot() {
		super();
		print("initialized", 1);
	}

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage()) {
			if (update.getMessage().hasText()) {

				String text = update.getMessage().getText().toLowerCase();
				String response = "";

				String whatIt;

				if (text.contains("it")) {

					for (int i = 0; i < text.length() - 1; i++) {
						if (text.substring(i, i + 2).equals("it")) {
	
							itIndex = i;
	
							if (!text.substring(itIndex - 1, itIndex).equals(" ")) {
								for (int j = i; j >= 0; j--) {
									if (text.substring(j, j + 1).equals(" ")) {
										spaceIndex = j;
										break;
									} else {
										spaceIndex = 0;
									}
								}
							} else if (text.length() == 2) {
								response = " ";
							} else {
								for (int j = i - 2; j >= 0; j--) {
									if (text.substring(j, j + 1).equals(" ")) {
										spaceIndex = j;
										break;
									} else {
										spaceIndex = 0;
									}
								}
	
							}
						}
					}
	
					if (itIndex != 0) {
	
						whatIt = text.substring(spaceIndex, itIndex);
						whatIt = whatIt.substring(0, 1).toUpperCase() + whatIt.substring(1).toLowerCase();
	
					} else if (text.length() >= 2) {
	
						whatIt = text.substring(2);
						whatIt = whatIt.substring(0, 1).toUpperCase() + whatIt.substring(1).toLowerCase();
	
					} else {
						whatIt = " ";
					}
	
					whatItL = whatIt.toLowerCase();
	
					response += "Ohh " + whatItL + " it! " + whatIt + " it! " + whatIt + " it!";
	
					for (int i = 0; i < response.length(); i++) {
	
						if (response.substring(i, i + 1).equals(" ") && response.substring(i + 1, i + 2).equals(" ")) {
	
							String responseTemp = response;
	
							response = responseTemp.substring(0, i) + responseTemp.substring(i + 1, responseTemp.length());
	
							if (response.substring(i, i + 1).equals(" ") && response.substring(i + 1, i + 2).equals(" ")) {
	
								String responseTemp2 = response;
	
								response = responseTemp2.substring(0, i)
										+ responseTemp2.substring(i + 1, responseTemp2.length());
	
							}
	
						}
	
					}
	
					print("final message: " + response, 0);
	
					SendMessage message = new SendMessage();
					message.setChatId(update.getMessage().getChatId());
	
					message.setText(response);
	
					message.setChatId(update.getMessage().getChatId());
					if(message.getText() != "") {
						try {
							execute(message);
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

// }

	private void print(Object o, int errorstate) {
		BotUtilities.print(getBotUsername(), o.toString(), errorstate);
	}

	@Override
	public String getBotUsername() {
		return "ItItItBot";
	}

	@Override
	public String getBotToken() {
		return "1203754383:AAF2b8XmjeyEqiZNaPEbRuHStn448fF0lBM";
	}

}