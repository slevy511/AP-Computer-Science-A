package telegramBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/*   ARGUMENTS:
 *    -dawm for dawm
 *    -sunny for sunny
 *    -hannahlee for hannahlee
 *    -spice for spicebot
 * 		-awo for awobot
 */

public class Main {
	private static boolean dawm = false;
	private static boolean sunny = false;
	private static boolean hannahlee = false;
	private static boolean spice = false;
	private static boolean awo = false;
	private static boolean ititit = false;
	
	
	public static void main(String[] args) {
		BotUtilities.print("MAIN", "Build Date: 00:13, 4/25/2020", 2);
		
		ApiContextInitializer.init();
		
		for(String i : args) {
			if(i.equals("-dawm")) {
				dawm = true;
			}else if(i.equals("-sunny")) {
				sunny = true;
			}else if(i.equals("-hannahlee")) {
				hannahlee = true;
			}else if(i.equals("-spice")){
				spice = true;
			}else if(i.equals("-awo")){
				awo = true;
			}else if(i.equals("-ititit")) {
				ititit = true;
			}else{
				BotUtilities.print("MAIN", "Use -dawm for Dawmbot, -sunny for SunnyBot, -hannahlee for HannahLeeBot, -spice for SpiessBot, -awo for AwOBot, and -ititit for ItItItBot.", 0);
				System.exit(0);
			}
		}
		
		if(!(dawm || sunny || hannahlee || spice || awo || ititit)) {
			BotUtilities.print("MAIN", "No bots are selected! Run again with -help to view valid bot arguments.", 3);
		}
		
		
		TelegramBotsApi botApi = new TelegramBotsApi();
		
		if(spice) {
			BotUtilities.print("SpiceTheBot", "initializing scheduler . . .", 2);
			new Scheduler();
			BotUtilities.print("SpiceTheBot", "initialized!", 1);
		}
		
		try {
			if(dawm) {
				BotUtilities.print("dawmbot", "initializing . . .", 2);
            	botApi.registerBot(new DawmBot());
			}
            
            //botApi.registerBot(new KirilBot());
			
			if(sunny) {
	            BotUtilities.print("SunnyTheBot", "initializing . . .", 2);
	            botApi.registerBot(new SunnyBot());
			}
            
			if(hannahlee) {
	            BotUtilities.print("HannahLeeBot", "initializing . . .", 2);
	            botApi.registerBot(new HannahLeeBot());
			}
            
			if(spice) {
				BotUtilities.print("SpiceTheBot", "initializing . . .", 2);
            	botApi.registerBot(new SpiessBot());
			}
			
			if(awo) {
				BotUtilities.print("DonDawnDomDawmBot", "initializing . . .", 2);
            	botApi.registerBot(new AwOBot());
			}
			
			if(ititit) {
				BotUtilities.print("ItItItBot", "initializing . . .", 2);
				botApi.registerBot(new ItItItBot());
			}

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
	}

}
