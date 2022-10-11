import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Scanner;


/* Samuel Levy
 * p.1 Data Structures
 * Hashmap Project
 */
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File text = new File("covid417.csv");
		HashMap<String, StateUS> map = new HashMap<String, StateUS>();					//creates new Hashmap of State objects with strings as keys (which will be state names)

		// Creating Scanner instnace to read File in Java
		try {
			Scanner scanner = new Scanner(text);
			scanner.nextLine();															//skips the column titles

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();										//stores the current city being scanned
				int first = line.indexOf(",");											
				String city = line.substring(0, first);
				
				line = line.substring(first + 1);										//takes the city name off the front of the string
				
				String state = line.substring(0, line.indexOf(","));					//stores the state name for later
				
				line = line.substring(line.indexOf(",") + 1);							//takes the state name off the front of the strong
				
				if (line.substring(0, 1).equals(" ")) {									//gets rid of potential space so conversion to int works
					line = line.substring(1);
					
				}
				
				int confirmed = Integer.valueOf(line.substring(0, line.indexOf(",")));	//stores number of confirmed cases for later
				
				line = line.substring(line.indexOf(",") + 1);							//takes the number of confirmed cases off the front of the String
				if (line.substring(0, 1).equals(" ")) {									//gets rid of potential space so conversion to int works
					line = line.substring(1);
				
				}
				int deaths = Integer.valueOf(line.substring(0, line.indexOf(",")));		//stores number of deaths for later
				
				line = line.substring(line.indexOf(",") + 1);							//takes the number of deaths off the front of the String
				if (line.substring(0, 1).equals(" ")) {									//gets rid of potential space so conversion to int works
					line = line.substring(1);
					
				}
				int recovered = Integer.valueOf(line.substring(0, line.indexOf(",")));	//stores number of recoveries for later
				line = line.substring(line.indexOf(",") + 1);							//takes the number of recoveries off the front of the String
				if (line.substring(0, 1).equals(" ")) {									//gets rid of potential space so conversion to int works
					line = line.substring(1);
					
				}

				int active = Integer.valueOf(line);										//stores number of active cases for later
				
				if (map.containsKey(state) == false) {									//checks if the state is new to hashmap
					map.put(state, new StateUS());										//adds the state if it doesn't exist in the map
					map.get(state).add(confirmed, deaths, recovered, active);			//adds this city's data to the state it's in
					//System.out.println(state + ": " + map.get(state).toString());
				} else {
					map.get(state).add(confirmed, deaths, recovered, active);			//adds this city's data to the state it's in
					//System.out.println(state + ": " + map.get(state).toString());
					
				}

				// System.out.println(scanner.nextLine());
				// handle one line

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		System.out.println(map.get("California"));
		System.out.println(map.get("Texas"));
		System.out.println(map.get("New York"));
		System.out.println(map.get("Florida"));
		System.out.println(map.get("Mississippi"));
		*/
	}

}
