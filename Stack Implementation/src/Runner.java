
public class Runner {

	public static void main(String[] arg) {
		
	/*	Stack<String> myStrs = new Stack<String>();
		myStrs.push("Hello");
		System.out.println(myStrs.pop());
		System.out.println(myStrs.size());
		
		Stack<Integer> myNums= new Stack<Integer>();
		myNums.push(777);
		System.out.println(myNums);
		
		Stack<Dave> daves = new Stack<Dave>();
		daves.push(new Dave());
	
	
	 //stack1 = new Stack<Integer>();

	 //stack1.push(30);
	
		//stack1.peak();
	*/
		
		
		LinkedList<Integer> test = new LinkedList<Integer>();
		
		test.add(1);
		test.add(1);
		test.add(1);
		System.out.println(test.size()); //3
		test.remove();
		test.remove();
		test.remove();
		test.remove();
		test.remove(1);
		System.out.println(test.peek()); //null
		System.out.println(test.size()); //0
		test.add(4);
		System.out.println(test.peek()); //4   ///////////
		test.addFront(10);
		System.out.println(test.peek()); //10
		test.remove();
		System.out.println(test.peek()); //4 ///////////
		test.add(3);
		System.out.println(test.size()); //2 /////////
		System.out.println(test.peek()); //4
		test.remove(0);
		System.out.println(test.size()); //1
		System.out.println(test.peek()); //3
		test.add(5);
		System.out.println(test.size()); //2
		System.out.println(test.peek()); //3
		test.remove(1);
		System.out.println(test.size()); //1
		System.out.println(test.peek()); //3

		System.out.println(test.isCircular()); //false
		
		
	}}
		
		
		
	/*	
	QueQue<Integer> test = new QueQue<Integer>();	
	test.add(1);
	System.out.println(test.peek());
	test.add(2);
	System.out.println(test.peek());
	System.out.println("SIZE:" + test.size());
	test.add(3);
	System.out.println(test.peek());
	test.add(4);
	System.out.println(test.peek());
	test.remove();
	System.out.println(test.peek());
	test.remove();
	System.out.println(test.peek());
	System.out.println("SIZE:" + test.size());
	//System.out.println(test);
		
}}
*/
/*
class Dave{
	
}
*/