package data_structures;

public abstract class Sequence {
	
	/**
	 * n: Size of array
	 */
	protected int n;
	
	/**
	 * numOps: Number of "basic operations" since last resource reset.  
	 */
	protected int numOps;
	
	
	/**
	 * Sequence construtcor: should be called from any derived 
	 */
	protected Sequence() {
		resetOps();
		n=0;
	}

	//*************
	// Concrete methods implemented in sequence
	
	/**
	 * Return the number of strings contained in the list.
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Add an element to the back of the string.
	 * @param value String being added
	 */
	public void push_back(String value) {
		add(n, value);
	}
	
	/**
	 * Add an element to the front of the string.
	 * @param value String being added.
	 */
	public void push_front(String value) {
		add(0, value);
	}
	
	/**
	 * Remove last element
	 * @return value of last element
	 */
	public String pop_back() {
		return remove(size()-1);
	}
	
	/**
	 * Remove first element
	 * @return value of first element
	 */
	public String pop_front() {
		return remove(0);
	}
	
	
	/**
	 * Search for a query string in the array.
	 * @param query String being seqrched for
	 * @return boolean
	 */
	public boolean in(String query) {
		for (int i=0; i < n; i++)
			if (query.equals(get(i)))
				return true;
		
		return false;
	}
	
	/**
	 * Return True if the container is empty.
	 */
	public boolean isEmpty() {
		return n==0;
	}
	
	/**
	 * Amount of memory used since last resource reset.
	 * @return Number of operations
	 */
	public int numOps() {
		return numOps;
	}
	
	
	public void resetOps() {
		numOps = 0;
	}
	
	//*********** 
	// Abstract methods to be implemented in derived classes.
	
	/**
	 * Get the string i.
	 * @param i Number of accessed element (from 0).
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public String get(int i);

	/**
	 * Set the string at element i to value.
	 * @param i Number of accessed element (from 0).
	 * @param value String value being put in the array
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public String set(int i, String value);
	
	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * @param i Number of inserted element
	 * @param value String valueb eing inserted
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public void add(int i, String value);
	

	
	/**
	 * Remove element i from the array (renumbering everything past it)
	 * @param i  element being removed
	 * @return   value that was removed
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public String remove(int i);
	

	
	/**
	 * Remove all elements.
	 */
	abstract public void clear();
	

}
