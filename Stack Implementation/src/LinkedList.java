
public class LinkedList<T> { // <T> allows us to use any data type

	private int size; // number of elements in the list

	private Node<T> head; // headNodeReference

	public LinkedList() { // using a default constructor

	}

	/* return the number of elements in the list */
	public int size() {
		return size; // returns the int that keeps track of size
	}

	/* returns the head reference */
	public Node<T> peek() {
		if (size == 0) { // returns null if the LinkedList is empty
			return null;
		} else { // otherwise returns the head node
			return head;
		}

	}

	/* add a node at the end of the list! */
	public void add(T t) {

		size++; // adding an element causes size to increase!

		if (head == null) {			//if the head doesn't exist
			head = new Node<T>(t); 	//creates a new node that becomes the head and adds the specified element
			
		} else {

			Node<T> temp = head;
			while (temp.next != null) {
				
				temp = temp.next;
			}

			// if we hit the end of the loop, we know next is null!
			// aka end of the list!

			temp.next = new Node<T>(t); // done!

		}
	}

	/* add a node at the beginnning of the list */
	public void addFront(T t) {
		size++;
		Node<T> temp = new Node<T>(t);
		temp.next = head;
		head = temp;
		// head = new Node<T>(t);

		// head.next = temp;
	}

	/* remove the head of the list */
	public Node<T> remove() {

		Node<T> temp = head;
		if (head != null) {
			head = head.next();
			size--;
		}
		return temp;
		///////////
		/*
		 * if(size<=0) { size=0; }else { size--; }
		 * 
		 * if(size>0) { Node<T> temp = head; if (head == null) { //head = temp.next;
		 * return null; } else if (head != null) { head = temp.next; size--; }
		 * 
		 * return temp; }else { return null; } // return new Node<T>(-1);
		 * 
		 */
	}

	/*
	 * remove node at index i requires i < size
	 */

	public Node<T> remove(int i) {
		// idk what any of this is - it's just copypasted from add- for this we need to
		// traverse through the node until we find element i ?
		// size--; // adding an element causes size to increase!
		/*
		 * if(size<=0) { size=0; }
		 * 
		 * 
		 * Node<T> temp = head;
		 */
		if (i == 0) {

			return remove();
		} else {
			if (i + 1 <= size && i > 0) {
				size--;
				int z = 0;

				Node<T> temp = head;
				while (z != i - 1) {
					temp = temp.next();
					z++;
				}
				Node<T> wanted = temp.next();
				temp.next = wanted.next();

				return wanted;
			}
		}
		return null;

		/*
		 * for (int j = 0; j < i-1; j++) {
		 * 
		 * temp = temp.next;
		 * 
		 * }
		 * 
		 * Node<T> toRemove = temp.next; temp = toRemove.next;
		 * 
		 * return toRemove;
		 * 
		 * }else { return null; }
		 */
	}

	/* THIS MIGHT BE ON THE EXAM */
	public boolean isCircular() { // checks if the linked list eventally goes back to the beginning of the lsist
		return false;
	}

}
