import java.util.ArrayList;

public class Queue<T> {

//FIFO - Fist in, First out (for stacks)

	private ArrayList<T> data;
	private int size;

	

	public Queue() {
		data = new ArrayList<T>();
	}
	//enqueue
	public void add(T t) {
		data.add(t);
		size++;
	}
	
	//dequeue
	//first-in first-out
	public T remove() {
		T t = data.remove(0);
		size--;
		return t;
		//return data.remove(size);
	}

	// write the method peek which returns the last element
	// but does not remove it from the list

	public T peek() {
		return data.get(0);
	}

	// write the isEmpty method which returns true if the stack is empty

	

	public int size() { // following arraylist size naming
		return size;
	}

	public int getSize() {
		return size;
	}

	public String toString() {
		return data.toString();
	}
	
}
