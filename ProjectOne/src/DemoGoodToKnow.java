import java.util.ArrayDeque;

public class DemoGoodToKnow {
	public static void main(String[] blah) {
		
		int val = 90002;
		
		switch(val) {
		
		case 0:
			System.out.println("val is 0");
			break; //- what if you forget a break for a case? continues into next case
		
		case 1: System.out.println("3");
			break;
		
		case 5:
			System.out.println("found the cake");
			break;
		default:
			System.out.println("default case - all other cases");
			
		}
		
		
		//Using Queues in Java!
		
		ArrayDeque<Integer> myQ = new ArrayDeque<Integer>();
		
		//now myQ behaves like a queue
		//first in first out
		//look through helper methods to see which ones could be helpful
		myQ.add(1);
		myQ.add(12);
		myQ.add(13);
		//myQ.remove();
		System.out.println(myQ.peekFirst()+"should be 1");
		/*
		myQ.push(1);
		myQ.push(12);
		myQ.push(13);
		System.out.println(myQ.peekFirst()+"should be 1");
		*/
		
		//Try catches
		try {
			//when something throws and exception
			//or you might want to handle exceptions in this way
			
			int[] x = null;
			//x[1] = 5;
			int x2 = 0;
			int x3 = 1/x2;
		}catch(NullPointerException n) {

			System.out.println("null pointer exception handler");
			
		}catch(Exception e) { //Exception covers all cases
			
			
		
			System.out.println("handled all other exceptions "+e);
			
			
		}
		
		
		
		
		
	}
}
