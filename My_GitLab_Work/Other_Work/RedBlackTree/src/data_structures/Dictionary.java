package data_structures;

public abstract class Dictionary<K extends Comparable <K>,E extends Comparable <E>> extends Container<K>{
	public Dictionary() {
		super();
	}
	
	/**
	 * Insert a value/key pair into the dictionary.
	 * @param key        key to be inserted
	 * @param value      value to be inserted
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 */
	public abstract void insert(K key, E value);
	
	/**
	 * Remove a key from the dictionary
	 * @param key  key to remove
	 */
	public abstract void remove(K key);
	
	/**
	 * Search for a key value
	 * @return value associated with key.  Returns null if key not found.
	 */
	public abstract E find(K key);
	
	/* (non-Javadoc)
	 * @see data_structures.Container#in()
	 */
	@Override 
	public boolean in(K key) {
		return find(key) != null;
	}
	
	/**
	 * Check to make sure the implemented structure correct.
	 * (For debugging.)
	 * @return boolean
	 */
	public abstract boolean check_structure();
	
	/**
	 * Print out the structure in whatever way makes snese.
	 * (For debugging.)
	 */
	public abstract void print_structure();
}
