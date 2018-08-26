import java.lang.IllegalStateException;

public class CharStack {

	private int maxSize;
	private int top;
	private char[] stack;
	
	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	public CharStack(int maxSize) {
		if (maxSize<1) {
			throw new IllegalStateException("The size of the Stack is out of range");
		}
		this.maxSize=maxSize;
		this.top=-1;
		this.stack=new char[maxSize];
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(Character value) {
		if (isFull()){
			throw new IllegalStateException("The Stack is Full");
		}
		top++;
		stack[top]=value;
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stacke
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public char pop() {
		if (isEmpty()){
			throw new IllegalStateException("The Stack is Empty");
		}
		return stack[top--];
		
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public char peek() {
		if (isEmpty()){
			throw new IllegalStateException("The Stack is Empty");
		}
		return stack[top];
	}
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty() {
		return (top==-1);
	}
	
	/**
	 * Determine if the stack is at capacity.
	 * @return True if at capacity.
	 */
	public boolean isFull() {
		return (top==maxSize-1);
	}
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		return top+1;
	}
	 
}
