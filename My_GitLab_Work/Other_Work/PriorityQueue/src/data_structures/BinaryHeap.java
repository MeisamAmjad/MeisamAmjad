/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>A binary heap is a heap data structure that takes the form of a binary tree. Binary heaps are a common way of implementing priority queues. The binary heap was introduced by J. W. J. Williams in 1964,</br>
 * as a data structure for heapsort.</p>
 * <p>A binary heap is defined as a binary tree with two additional constraints:</br>
 * 1- Shape property: a binary heap is a complete binary tree; that is, all levels of the tree, except possibly the last one (deepest) are fully filled, and, if the last level of the tree</br>
 * is not complete, the nodes of that level are filled from left to right.</br>
 * 2- Heap property: the key stored in each node is either greater than or equal to (≥) or less than or equal to (≤) the keys in the node's children, according to some total order.</p>
 * <p>Heaps where the parent key is greater than or equal to (≥) the child keys are called max-heaps; those where it is less than or equal to (≤) are called min-heaps. Efficient (logarithmic time) algorithms are </br>
 * known for the two operations needed to implement a priority queue on a binary heap: inserting an element, and removing the smallest (largest) element from a min-heap (max-heap). Binary heaps are also</br>
 * commonly employed in the heapsort sorting algorithm, which is an in-place algorithm owing to the fact that binary heaps can be implemented as an implicit data structure, storing keys in an array and using</br>
 * their relative positions within that array to represent child-parent relationships.</p>
 * <p><b> *** This class uses the PairNode class (for holding entry and its priority ) that is being used for Binary Search or BST. *** </b></p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_heap">Binary Heap (Wikipedia)</a
 * @param <K>
 * @param <V>
 */
public interface BinaryHeap<K extends Comparable<K>, V extends Comparable<V>> {
	
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
	abstract boolean add( V entry, K priority );
	
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
