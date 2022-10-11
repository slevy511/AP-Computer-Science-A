/*To make a stack act like a queue, you create two stacks and have one of them be filled with objects. 
Then you fill the other stack by popping the top element off the first stack, and pushing it to the second stack
one by one. After doing this for all elements, then when you pop an element off the second stack, it will be the 
same as taking the element from the bottom of the first stack, ie. dequeing it. 
*/

//^DESCRIPTION FOR HOW TO MAKE QUEQUE^


public class QueQue<T> {                //<T> allows you to generalize the TYPE stored in the class 
                                        //has to use add, remove, peak, and size


	private Stack<T> stack1;            //declares first stack
	
	private Stack<T> stack2;            //declares second stack
	
	private int size;                   //keeps track of stack size
	
	public QueQue(){
	
		stack1 = new Stack();           //instantiates first stack
		stack2 = new Stack();           //instantiates second stack
		
	}
	
	public void add(T t) {
		
		stack1.push(t);                 //adds new objects to the top of stack 1
		size++;                         //increments size
	
	}
	
	public T remove() {
		
		if(stack1.size()==0) {          //won't let you remove elements from an empty stack
			return null;
		}
		
		while(stack1.size() >0) {       //transfers stack1 into stack2 upside-down
			stack2.push(stack1.pop());
		}
		
		T temp = stack2.peek();         //keeps track of the element that is to be removed
		stack2.pop();                   //removes the element that is to be removed
		size--;
		
		while(stack2.size() > 0) {      //returns the contents of stack2 to stack1 in its original orientation
			stack1.push(stack2.pop());
		}
		return temp;                    //returns the removed element
		
		
	}
	
	public T peek() {
		
		if(stack1.size()==0) {          //won't try to peek at an empty stack
			return null;
		}
		
		while(stack1.size()>0) {        //moves the contents of stack1 to stack2 but inverts it
			stack2.push(stack1.pop());	
		}
		
		T temp = stack2.peek();         //keeps track of the element to be peeked at
		
		while(stack2.size()>0) {        //returns the stack to its original orientation
			stack1.push(stack2.pop());
		}
		return temp;                    //returns the element to be peeked at
	}
	
	

	
	

	public int size() {                 //following arraylist size naming
		return size;
	}
	
	public int getSize() {              //returns the size of the stack
		return size;
	}
	

	public String toString() {          //Override so that stack objects can be printed
		return stack1.toString();
	}
	
	
}

	

		                   
		        		                   
		        