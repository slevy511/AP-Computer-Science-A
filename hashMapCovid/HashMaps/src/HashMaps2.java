import java.util.HashMap;

public class HashMaps2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, StateUS> map = new HashMap<String, StateUS>();
		map.put("California",new StateUS());
		
		//check if a key exists - (check if a State is already in the map)
		System.out.println(map.containsKey("Michigan"));
		
		//if the state is already in the map
		//grab the Object and update it
		System.out.println(map.get("California"));
		
	}

}

