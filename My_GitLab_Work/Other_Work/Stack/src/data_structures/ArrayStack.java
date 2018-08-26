package data_structures;
import java.lang.reflect.Array;


public class ArrayStack<T extends Comparable <T>> implements Stack<T> {
	
	private int top;
	private T[] stack;
	
	/**
	 * Constructor
	 *   
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(Class <? extends T> cls) {
		this.top=-1;
		this.stack=(T[]) Array.newInstance(cls, 1);
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	
	@Override
	public void push(T value) {
		if (top+1>=this.stack.length) resize();
		stack[++top]=value;
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stacke
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public T pop() throws IllegalStateException{
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
	public T peek() throws IllegalStateException {
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
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		return top+1;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() { 
		T[] B=(T[]) Array.newInstance(stack.getClass().getComponentType(),stack.length*2);
		System.arraycopy(stack, 0, B, 0, size());
		//for (int j=0;j<stack.length;B[j]=this.stack[j++]);
		this.stack=B;
	}

}
