package data_structures;


/**
 * 
 * @author Meijad - amjadm@miamioh.edu
 *
 * @param <T>
 */

public abstract class Sequence<T extends Comparable<T>> {
	/**
	 * n:  size of array
	 */
	protected int n;
	
	public Sequence(int n){
		this.n=n;
	}
	
	/**
	 * Return the number of elements contained in the sequence
	 * @return int
	 */
	public int size(){
		return n;
	}
	
	/**
	 * Add an element to the back (tail) of the sequence
	 * @param value the T value that being added
	 * @throws IndexOutOfBoundException if index is out of bound
	 */
	public void push_back(T value) throws Exception{
		add(this.n,value);
	}
	
	/**
	 * Add an element to the front (head) of the sequence
	 * @param value the T value that being added
	 * @throws IndexOutOfBoundException if index is out of bound
	 */
	public void push_front(T value) throws Exception{
		add(0,value);
	}
	
	/**
	 * Return true if the list if empty. Otherwise false.
	 * @return
	 */
	public boolean isEmpty(){
		return n==0;
	}
	
	//Abstract methods to be implemented in derived class.
	
	/**
	 * Get the T value of index
	 * @param index The number of accessed element (from 0)
	 * @return T
	 * @throws IndexOutOfBoundException if index is out of bound
	 */
	abstract public T get(int index) throws Exception;
	
	/**
	 * Set the T value at a given index
	 * @param index number of accessed element
	 * @param value T value that being put in the list
	 * @return T Old value that was at given index
	 * @throws Exception IndexOutOfBoundException if index is out of bound
	 */
	abstract public T set(int index, T value)throws Exception;
	
	/**
	 * Insert a new value at index 
	 * @param index The new position for inserted element
	 * @param value T value that being inserted
	 * @throws Exception IndexOutOfBoundException if index is out of bound
	 */
	abstract public void add(int index, T value) throws Exception;
	abstract public void add(T value);
	
	/**
	 * Remove element at index from the sequence 
	 * @param index element that being removed
	 * @return T value that was removed
	 * @throws IndexOutOfBoundException if index is out of bound
	 */
	abstract public T remove(int index) throws Exception;
	abstract public T remove() throws Exception;
	
	/**
	 * Remove all elements
	 */
	abstract public void clear();
	
	/**
	 * Search if the given value is inside the sequence
	 * Time is O(n)
	 * @param value
	 */
	abstract public boolean in(T value);
	
	
}
