package data_structures;

public abstract class Container<K extends Comparable<K>> {
	/**
	 * n: Size of container
	 */
	protected int n;
	
	protected Container() {
		n=0;
	}
	
	/**
	 * Return the number of element_types contained in the list.
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Remove all elements.
	 */
	abstract public void clear();
	
	/**
	 * Search for a query element_type in the array.
	 * @param query element_type being seqrched for
	 * @return boolean
	 */
	abstract public boolean in(K query);
	
	/**
	 * Return True if the container is empty.
	 */
	public boolean isEmpty() {
		return n==0;
	}
}