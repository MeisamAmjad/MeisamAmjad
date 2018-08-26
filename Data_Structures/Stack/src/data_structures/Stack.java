package data_structures;

public interface Stack<T extends Comparable<T>> {
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(T value);
		
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stacke
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public T pop();
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public T peek(); 
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty();
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size();
	
}

