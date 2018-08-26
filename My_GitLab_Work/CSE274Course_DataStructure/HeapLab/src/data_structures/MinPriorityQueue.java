/**
 * 
 */
package data_structures;

/**
 * @author karroje
 *
 */
public interface MinPriorityQueue<E, P extends Comparable<P>> {
	
	/**
	 * Push an element e with priority p into the priority queue
	 * @param e  The element being put in the PriorityQueue.
	 * @param p  The priority of the e
	 */
	abstract void push(E e, P v);
	
	/**
	 * Remove the smallest element of the priority queue from the queue 
	 * and return the element.  Ties broken arbitrarily.
	 * @return E
	 * @exception ArrayIndexOutOfBoundsException  Thrown when called on an empty heap.
	 */
	abstract E pop();
	
	/**
	 * Return the smallest element of the priority queue (but to not
	 * remove it from the queue.
	 * @return E
	 */
	abstract E peek();
	
	/**
	 * Return the size of the queue.
	 * @return int
	 */
	abstract int size();
	
	/**
	 * Test whether heap is empty
	 */
	abstract boolean empty();
	
	/**
	 * Clear structure
	 */
	abstract void clear();
	
	/**
	 * Reset numOp count.
	 */
	public void resetOps();
	
	/**
	 * Get number of basic operations
	 * @return Number of operations
	 */
	public int numOps();
	 
}
