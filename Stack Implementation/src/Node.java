
public class Node<T> {

	private T data;
	
	public Node<T> next;
	
	public Node(T t) {
		
		data = t;
		this.next=null;
		
	}
	/*returns next ref */
	public Node<T> next(){
		return next;
	}
	
	/*returns next ref */
	public T getData() {
		return data;
	}
	
}
