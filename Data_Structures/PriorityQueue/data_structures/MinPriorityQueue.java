/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>******** This class had been implemented by MinHeap.java class from <b>BinaryHeap</b> {@link MinHeap} ************** </p>
 * <p>a priority queue is an abstract data type which is like a regular queue or stack data structure, but where additionally each element has a "priority" associated with it.</br>
 *  In a priority queue, an element with high priority is served before an element with low priority. If two elements have the same priority, they are served according to </br>
 *  their order in the queue. While priority queues are often implemented with heaps, they are conceptually distinct from heaps. A priority queue is an abstract concept</br>
 *  like "a list" or "a map"; just as a list can be implemented with a linked list or an array, a priority queue can be implemented with a heap or a variety of other methods</br>
 *  such as an unordered array.</p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Priority_queue">Priority Queue (Wikipedia)</a
 * @param <K>
 * @param <V>
 */
public interface MinPriorityQueue<K extends Comparable<K>, V extends Comparable<V>> {
	
	/**
	 * <p> Removes the entity with the minimum priority and returns it. Also, reorganizes the queue for maintaining the Priority Queue property.</p>   
	 * @return
	 * @throws ArrayIndexOutOfBoundsException if the list is Empty.
	 * @see #removeAll(Collection)
	 */
	abstract V remove();
	
	/**
	 *<p>Removes all of queue's elements that are also contained in the specified collection (optional operation). After this call returns, this </br>
	 * collection will contain no elements in common with the specified collection.</p>
	 * @param C collection containing elements to be removed from this collection.
	 * @return 	<b> true </b>if this collection changed as a result of the call. </br>
	 * 			<b> false</b> when the C is null, the dictionary is empty, or nothing changes.
	 * @throws UnsupportedOperationException if the class of an element of the specified collection is a different type.
	 * @see #remove(key)
	 */
	abstract boolean removeAll( Collection<?> C );
	
	/**
	 * <p>Retains only the elements in this queue that are contained in the specified collection (optional operation). In other words, removes from this queue</br>
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
	 * <p> Adds an entity at the end of the list. Ensuring the list maintains the Min Priority Queue property.</p>
	 * @param entry
	 * @param priority
	 * @see #addAll(Collection)
	 */
	abstract boolean add( V entry, K priority );
	
	/**
	 * <p>Adds all of the elements in the specified collection to this queue (optional operation). The behavior of this operation is undefined if </br>
	 * the specified collection is modified while the operation is in progress.</p> 
	 * @param C collection containing entities to be added.
	 * @return 	<b>true</b> if this queue changed as a result of the call.</br>
	 * 			<b> false</b> if the C is null or nothing changes.
	 * @see #add(key,value)
	 */
	abstract boolean addAll( Collection<?> C );
	
	/**
	 * <p> Finds the priority value of the given entity. It just returns the value of it and does not mean the real priority in the queue.<p>
	 * @param entity
	 * @return
	 * {@link #contains(Comparable)}
	 */
	abstract K find( V entity );
	
	/**
	 * <p> Returns true if the queue contains the given entity. Otherwise returns false.</p>
	 * @param entity
	 * @return
	 * @see #find(Comparable)
	 */
	abstract boolean contains( V entity );
	
	/**
	 * <p> Returns the size of the queue.</p>
	 * @return int
	 */
	abstract int size();
	
	/**
	 * <p> Returns true only and only if the queue is empty. Otherwise, returns false.</p>
	 * @return
	 */
	abstract boolean isEmpty();
	
	/**
	 * <p> Makes the queue empty.</p>
	 */
	abstract void clear();
	
	/**
	 * <p> Makes a deep copy of the Min Priority Queue Object.</p>
	 * @return Deep copy of MinQueue Object.
	 * @throws CloneNotSupportedException if the queue is empty.
	 */
	abstract Object clone() throws CloneNotSupportedException;
	
	abstract int hashCode();
	
	abstract String toString();
	
	abstract Iterator<PairNode<K, V>> iterator();
}
