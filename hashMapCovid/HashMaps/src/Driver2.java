import java.util.ArrayList;

public class Driver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "San Diego,California,12,0,0,0";
		
		int first = test.indexOf(",");
		String city = test.substring(0,first);
		System.out.println(city);
		
		test = test.substring(first+1);
		System.out.println(test);
		
		String state = test.substring(0, test.indexOf(","));
		test = test.substring(test.indexOf(",")+1);
		System.out.println(state);
		
		int confirmed = Integer.valueOf(test.substring(0,test.indexOf(",")));
		System.out.println(confirmed);	
				
		int value = 12340;
		String valueStr = value+"";	
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		for(int i =0; i < valueStr.length();i++) {
			//show that you know to convert a char to a String
			//when using charAt
			//'a' -> 49     'a' != "a"
			arr.add(Integer.valueOf(valueStr.charAt(i)+""));
		}
		
		System.out.println(arr);
		
		
	}

}
