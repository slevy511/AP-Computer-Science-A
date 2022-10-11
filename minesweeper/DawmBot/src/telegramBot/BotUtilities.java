package telegramBot;

import java.sql.Timestamp;

public final class BotUtilities {
	
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RED = "\u001b[31m";
	public static final String ANSI_YELLOW = "\u001b[33m";
	public static final String ANSI_GREEN = "\u001b[32m";
	
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	public static void printlnx(String className, String str) {
		print(className, str, 0);
	}
	
	public static void printSuccess(String className, String str) {
		print(className, str, 1);
	}
	
	public static void printError(String className, String str) {
		print(className, str, 3);
	}
	
	
	
	
	//colorstate === 0 is normal (white text), 1 is success (green text), 2 is problem (yellow text), 3 is error (red text);
	public static void print(String className, String str, int colorstate) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String textColor;
		switch(colorstate) {
		case 0:
			textColor = ANSI_RESET;
			break;
		case 1:
			textColor = ANSI_GREEN;
			break;
		case 2:
			textColor = ANSI_YELLOW;
			break;
		case 3:
			textColor = ANSI_RED;
			break;
		default:
			textColor = ANSI_RESET;
		}
		
		String prefix = ANSI_CYAN + timestamp.toString().substring(11,19) + " [" + className + "]: " + textColor;
		System.out.print(prefix);
		
		for(int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
			if(str.charAt(i) == '\n') {
				System.out.print(prefix);
			}
		}
		System.out.print("\n" + ANSI_RESET);
	}
}
