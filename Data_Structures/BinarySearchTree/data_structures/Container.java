/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

/**
 * <p>In computer science,<b> a container</b> is a class, a data structure, or an abstract data type (ADT) whose instances are collections of other objects.</br>
 * In other words, they store objects in an organized way that follows specific access rules. The size of the container depends on the number of objects</br>
 * (elements) it contains. Underlying (inherited) implementations of various container types may vary in size and complexity, and provide flexibility in</br>
 * choosing the right implementation for any given scenario. </p>
 * <p>For <i><b>programmer</b></i> all non public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @see <a href="https://en.wikipedia.org/wiki/Container_(abstract_data_type)"> Container (abstract data type) (wikipedia)</a>
 * @author Meijad
 * @param <K> Generic type for K
 */
public abstract class Container<K extends Comparable<K>> {
	/**
	 * <p>n: Size of container.</p>
	 * @see #getN()
	 * @see #setN(int n)
	 */
	private int n;
	
	/**
	 * <p>Constructor.</p>
	 */
	public Container() {
		n=0;
	}
	
	/**
	 * <p>Return the number of element_types contained in the list.</p>
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * <p>Return True if the container is empty.</p>
	 */
	public boolean isEmpty() {
		return n==0;
	}
	
	/**
	 * <p>Remove all elements.</p>
	 */
	public abstract void clear();
	
	/**
	 * <p>Search for a query element_type in the array.</p>
	 * @param query element_type being seqrched for
	 * @return boolean
	 */
	public abstract boolean contains(K query);
	
	/**
	 * <p> Endorse whether or not the object is validated. Returns true if it would be positive, otherwise returns false.</p>
	 * <p> <b>This method checks following conditions:</b></p>
	 * <p>	1- If the object is null it always returns false.</br>
	 * 		2- If the object is specific data structure by overriding this method it would check the whole structure of that or some specific details. </br>
	 * 		3- If the object is a Collection<?> it returns false if it is empty.</p> 
	 * @return
	 */
	public abstract boolean validate( Object o );
	
	/**
	 * @see #n
	 */
	protected int getN() { return this.n; }
	
	/**
	 * @see #n
	 */
	protected void setN( int n ) { this.n = n; }
	
	/**
	 * <p> Increases one to n.</p>
	 * @see #n
	 */
	protected final void increaseSize() { ++ this.n ; }
	
	/**
	 * <p> Deducts one from n.</p>
	 * @see #n
	 */
	protected final void decreaseSize() { -- this.n; }
}