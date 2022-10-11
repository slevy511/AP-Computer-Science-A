import java.util.ArrayList;

public class Stack<T> { ////<T> allows you to generalize the TYPE stored in the class 
		//doesnt have to be T - could be anything - could even use TBA_TYPE
	private ArrayList<T> data; // <T> works because of line 3 which represents the type as T
	private int size;
	
	public Stack() {
		data = new ArrayList<T>();
		
	}
	
	public void push(T t) {
		data.add(t);
		size++;
		
	}
	
	//write the pop method
	//to remove the top element from the list
	
	public T pop() {
		size--;
		return data.remove(size);
	}
	
	//write the method peek which returns the last element
	//but does not remove it from the list
	
	public T peek() {
		return data.get(size-1);
	}
	
	
	//write the isEmpty method which returns true if the stack is empty
	
	public boolean isEmpty() {
		if(data.size()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int size() { //following arraylist size naming
		return size;
	}
	
	public int getSize() {
		return size;
	}
	
	/*
	 * Override so that stack objects can be printed
	 */
	public String toString() {
		return data.toString();
	}
}
