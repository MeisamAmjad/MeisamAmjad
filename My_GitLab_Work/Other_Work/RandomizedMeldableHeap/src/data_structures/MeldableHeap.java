/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>A <b>randomized meldable heap</b> (also <b>Meldable Heap or Randomized Meldable Priority Queue</b>) is a priority queue based data structure in which the underlying structure is</br>
 * also a heap-ordered binary tree. However, there are no restrictions on the shape of the underlying binary tree. This approach has a number of advantages over similar data </br>
 * structures. It offers greater simplicity: all operations for the randomized meldable heap are easy to implement and the constant factors in their complexity bounds are small. </br>
 * There is also no need to preserve balance conditions and no satellite information within the nodes is necessary. Lastly, this structure has good worst-case time efficiency. The</br>
 * execution time of each individual operation is at most logarithmic with high probability.</p>
 * <p><b> *** This class uses the PairNode class (for holding entry and its priority ) that is being used for Binary Search or BST. *** </b></p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Randomized_meldable_heap">Randomized Meldable Heap (Wikipedia)</a
 * @param <K>
 * @param <V>
 */
public interface MeldableHeap<K extends Comparable<K>, V extends Comparable<V>> {
	
	/**
	 * <p> Removes the entity with the minimum or maximun priority and returns it. Also, reorganizes the Heap for maintaining the Binary Heap property.</p>   
	 * @return
	 * @throws ArrayIndexOutOfBoundsException if the list is Empty.
	 * @see #removeAll(Collection)
	 */
	abstract V remove();
	
	/**
	 *<p>Removes all of Heap's elements that are also contained in the specified collection (optional operation). After this call returns, this </br>
	 * collection will contain no elements in common with the specified collection.</p>
	 * @param C collection containing elements to be removed from this collection.
	 * @return 	<b> true </b>if this collection changed as a result of the call. </br>
	 * 			<b> false</b> when the C is null, the dictionary is empty, or nothing changes.
	 * @throws UnsupportedOperationException if the class of an element of the specified collection is a different type.
	 * @see #remove(key)
	 */
	abstract boolean removeAll( Collection<?> C );
	
	/**
	 * <p>Retains only the elements in this Binary Heap that are contained in the specified collection (optional operation). In other words, removes from this Heap</br>
	 * all of its elements that are not contained in the specified collection. it takes O(N) time.</p>
	 * @param C collection containing elements to be retained in this collection.
	 * @return true when it's done.
	 * @see #removeAll(Collection)
	 */
	abstract boolean retainAll( Collection<?> C );
	
	/**
	 * <p> Returns an entity with the minimum priority. But, not moving it.</p>
	 * @return An entity with the minimum priority.
	 * @throws ArrayIndexOutOfBoundsException if the list is Empty.
	 */
	abstract V peek();
	
	/**
	 * <p> Adds an entity at the end of the list. Ensuring the list maintains the heap property.</p>
	 * @param entry
	 * @param priority
	 * @see #addAll(Collection)
	 */
	abstract boolean add( V entity, K priority );
	
	/**
	 * <p>Adds all of the elements in the specified collection to this Binary Heap (optional operation). The behavior of this operation is undefined if </br>
	 * the specified collection is modified while the operation is in progress.</p> 
	 * @param C collection containing entities to be added.
	 * @return 	<b>true</b> if this Heap changed as a result of the call.</br>
	 * 			<b> false</b> if the C is null or nothing changes.
	 * @see #add(key,value)
	 */
	abstract boolean addAll( Collection<?> C );
	
	/**
	 * <p> Finds the priority value of the given entity. It just returns the value of it and does not mean the real priority in the Heap.<p>
	 * @param entity
	 * @return
	 * {@link #contains(Comparable)}
	 */
	abstract K find( V entity );
	
	/**
	 * <p> Returns true if the Heap contains the given entity. Otherwise returns false.</p>
	 * @param entity
	 * @return
	 * @see #find(Comparable)
	 */
	abstract boolean contains( V entity );
	
	/**
	 * <p> Return the size of the Minimum Binary Heap.</p>
	 * @return int
	 */
	abstract int size();
	
	/**
	 * <p> Returns true only and only if the Heap is empty. Otherwise, returns false.</p>
	 * @return
	 */
	abstract boolean isEmpty();
	
	/**
	 * <p> Makes the Binary Heap empty.</p>
	 */
	abstract void clear();
	
	/**
	 * <p> Makes a deep copy of the MinHeap Object.</p>
	 * @return Deep copy of MinHeap Object.
	 * @throws CloneNotSupportedException if the heap is empty.
	 */
	abstract Object clone() throws CloneNotSupportedException;
	
	abstract int hashCode();
	
	abstract String toString();
	
	abstract Iterator<PairNode<K, V>> iterator();
}
