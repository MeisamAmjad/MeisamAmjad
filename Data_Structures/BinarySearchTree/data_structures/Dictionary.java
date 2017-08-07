/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.Collection;

/**
 * <p> <b> Dictionary</b> is an abstract data type composed of a collection of (key, value) pairs, such that each possible key appears at most once in the collection.</p>
 * <p>For <i><b>programmer</b></i> all non public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @see <a href="https://en.wikipedia.org/wiki/Associative_array"> Dictionary (data structure) (wikipedia)</a>
 * @author Meijad
 * @param <K> Generic type for K
 * @param <V> Generic type for E
 */
public abstract class Dictionary<K extends Comparable <K>,V extends Comparable <V>> extends Container<K> {
	
	/**
	 * <p>Constructor.</p>
	 * @see data_structures.Container
	 */
	public Dictionary() {
		super();
	}
	
	/**
	 * <p>Insert a value/key pair into the dictionary.</p>
	 * @param key A key to be inserted
	 * @param value A value to be inserted
	 */
	public abstract boolean add( K key, V value );
	
	/**
	 * <p>Adds all of the elements in the specified collection to this dictionary (optional operation). The behavior of this operation is undefined if </br>
	 * the specified collection is modified while the operation is in progress. (This implies that the behavior of this call is undefined if the specified</br>
	 * collection is this dictionary, and this collection is nonempty.)</p> 
	 * @param C collection containing elements to be added to this collection.
	 * @return 	<b>true</b> if this collection changed as a result of the call.</br>
	 * 			<b> false</b> if the C is null or nothing changes.
	 * @throws UnsupportedOperationException if the class of an element of the specified collection prevents it from being added to this collection.
	 * @see #add(key,value)
	 */
	public abstract boolean addAll( Collection<?> C );
	
	/**
	 * <p>Remove a key from the dictionary.</p>
	 * @param key  key to remove
	 */
	public abstract boolean remove( K key );
	
	/**
	 *<p>Removes all of this dictionary's elements that are also contained in the specified collection (optional operation). After this call returns, this </br>
	 * collection will contain no elements in common with the specified collection.</p>
	 * @param C collection containing elements to be removed from this collection
	 * @return 	<b> true </b>if this collection changed as a result of the call. </br>
	 * 			<b> false</b> when the C is null, the dictionary is empty, or nothing changes.
	 * @throws UnsupportedOperationException if the class of an element of the specified collection is a different type.
	 * @see #remove(key)
	 */
	public abstract boolean removeAll( Collection<?> C );
	
	/**
	 * <p> Sets a new value for the given key and returns null if there is no such a key or the old Value.</p>
	 * @param key A key that needs to be set by a new value.
	 * @param newValue A given new value for the given key.
	 * @return <b>null</b> if either the list is empty or there is no such a key.</br>
	 * the <b>old value</b> if it gets done successfully.
	 */
	public abstract V set( K key, V newValue );
	
	/**
	 * <p>Retains only the elements in this dictionary that are contained in the specified collection (optional operation). In other words, removes from this dictionary</br>
	 * all of its elements that are not contained in the specified collection. it takes O(N) time.</p>
	 * 
	 * @param C collection containing elements to be retained in this collection.
	 * @return true when it's done.
	 * @throws UnsupportedOperationException if the class of an element of the specified collection is a different type.
	 * @see #removeAll(Collection)
	 */
	public abstract boolean retainAll ( Collection<?> C );
	
	/**
	 * Search for a key value
	 * @return value associated with key.  Returns null if key not found.
	 */
	public abstract V find( K key );
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public abstract int hashCode();
	
	/**
	 * <p>Returns true if this collection contains all of the elements in the specified collection.</p>
	 * @param C collection to be checked for containment in this collection
	 * @return 
	 * @see #contains(key)
	 * @throws ClassCastException if the type of the collection or its elements is not compatible with the dictionary's elements.
	 */
	public abstract boolean containsAll( Collection<?> C );
	
	/* (non-Javadoc)
	 * @see data_structures.Container#in()
	 */
	@Override 
	public boolean contains( K key ) {
		return find( key ) != null;
	}
}