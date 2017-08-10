/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
public class MaxHeap<K extends Comparable<K>, V extends Comparable<V>> implements Iterable<PairNode<K, V>>, BinaryHeap<K, V>, MaxPriorityQueue<K, V>{
	/**
	 * <p> Holds all pairs in which each pair holding key (priority) and value (entity).</p> 
	 * @see #getHeap()
	 * @see #setHeap(ArrayList)
	 */
	private ArrayList<PairNode<K, V>> heap;

	/**
	 * <p>Constructor.</p>
	 */
	public MaxHeap() {
		setHeap( new ArrayList< PairNode<K, V>>() );
	}
	
	/**
	 * <p>Constructor.</p>
	 */
	public MaxHeap( Collection<?> C ) {
		setHeap( new ArrayList< PairNode<K, V>>() );
		addAll( C );
	}
	
	/**
	 * <p>Constructor.</p>
	 */
	protected MaxHeap( MaxHeap<K, V> newMBH ) {
		setHeap( newMBH.getHeap() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#remove()
	 */
	@Override
	public V remove() throws ArrayIndexOutOfBoundsException {
		if ( isEmpty() )
			throw new ArrayIndexOutOfBoundsException( "The list is empty" );
		V result = getHeap().get( 0 ).getValue();
		getHeap().set( 0, heap.get( size() - 1 ) ); // Replacing the first value with the last pair in the list.
		getHeap().remove( size() - 1 ); //Removing the last pair of the list.
		if ( size() > 1 )
			trickleDown( 0 );
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#removeAll(java.util.Collection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean removeAll( Collection<?> C ) {
		if ( C == null || isEmpty() )
			return false;
		int oldSize = size();
		for ( PairNode<K, V> element : ( List<PairNode<K, V>> ) C ) {
			if ( isEmpty() )
				break;
			int index = getHeap().indexOf( element );
			if ( index != -1 ){
				getHeap().set( index, getHeap().get( size() - 1 ) );
				getHeap().remove( size() - 1 );
				if ( size() > 1 )
					trickleDown( index );
			}
		}
		return ( oldSize > size() )
				? true
				: false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#retainAll(java.util.Collection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean retainAll( Collection<?> C ) {
		if ( C == null || isEmpty() )
			return false;
		int oldSize = size();
		List<PairNode<K, V>> list = ( List<PairNode<K, V>> ) C;
		List<PairNode<K, V>> removingList = new ArrayList<>();
		for ( int i = 0; !isEmpty() && i < size() ; i ++ ) {
			boolean contain = list.contains( getHeap().get( i ) );
			if ( !contain )
				removingList.add( getHeap().get( i ) );
		}
		removeAll( removingList );
		return ( oldSize > size() )
				? true
				: false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#peek()
	 */
	@Override
	public V peek() throws ArrayIndexOutOfBoundsException {
		if ( isEmpty() )
			throw new ArrayIndexOutOfBoundsException( "The list is empty" );
		return getHeap().get( 0 ).getValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#add(java.lang.Comparable, java.lang.Comparable)
	 */
	@Override
	public boolean add( V entry, K priority ) {
		if ( entry == null || priority == null)
			return false;
		getHeap().add( new PairNode<K,V>( priority, entry ) );
		if ( size() > 1 )
			bubleUp( size() - 1 );
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#addAll(java.util.Collection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean addAll( Collection<?> C ) {
		if ( C == null )
			return false;
		int oldSize = size();
		for ( PairNode<K, V> element : ( List<PairNode<K, V>> ) C )
			add( element.getValue(), element.getKey() );
		return ( oldSize < size() )
				? true
				: false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#find(java.lang.Comparable)
	 */
	@Override
	public K find( V entity ) {
		if ( isEmpty() )
			return null;
		for ( PairNode<K, V> element : getHeap() )
			if ( element.getValue().equals( entity ) )
				return element.getKey();
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains( V entity ) {
		return ( find( entity ) != null )
				? true
				: false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#size()
	 */
	@Override
	public int size() {
		return getHeap().size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return ( size() == 0 );
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BinaryHeap#clear()
	 */
	@Override
	public void clear() {
		for ( int i = 0; i < size() ; getHeap().set( i ++, null ) ); 
		setHeap( null );
		setHeap( new ArrayList<PairNode<K,V>>() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getHeap().hashCode();
	};
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ( isEmpty() )
				? "null"
				: makeString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#iterator()
	 */
	@Override
	public Iterator<PairNode<K, V>> iterator() {
		return getHeap().iterator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if ( isEmpty() )
			throw new CloneNotSupportedException( "** The object is EMPTY, meaning can not be cloned right now. **" );
		ArrayList<PairNode<K, V>> newList = new ArrayList<>( size() );
		for ( int i = 0 ; i < size() ; i ++ ) {
			PairNode<K, V> pair = new PairNode<K, V>( getHeap().get( i ).getKey(), getHeap().get( i).getValue() );
			newList.add( pair );
		}
		MaxHeap<K, V> newHeap = new MaxHeap<>();
		newHeap.setHeap( newList );
		return newHeap;
	};
	
	/**
	 * <p> Builds a string from all pairs inside the list.</p>
	 * @return String
	 * @see #toString()
	 */
	protected String makeString() {
		StringBuilder builder = new StringBuilder();
		for ( int i = 0 ; i < size() ; builder.append( "[" + i + "]: " + getHeap().get( i ++ ).toString() + ", "  ) );
		return builder.toString();
	}
	
	/**
	 * <p> Returns the value of the heap. </p>
	 * @see #heap
	 */
	protected ArrayList<PairNode<K, V>> getHeap() { return this.heap; }
	
	/**
	 * <p> Sets a new value into heap.</p>
	 * @see #heap
	 */
	protected void setHeap( ArrayList<PairNode<K, V>> heap ) { this.heap = heap; }
		
	/**
	 * <p> Returns the left child's index.</p>
	 * @param index
	 * @return index of the left child.
	 */
	protected int left( int index ) {
		return 2 * index + 1;
	}
	
	/**
	 * <p> Returns the right child's index.</p>
	 * @param index
	 * @return index of the right child.
	 */
	protected int right( int index ) {
		return 2 * index + 2;
	}
	
	/**
	 * <p> Returns the parent's index.</p>
	 * @param index
	 * @return index of the parent.
	 */
	protected int parent( int index ) {
		return ( index - 1 ) / 2;
	}
	
	/**
	 * <p> Checks the pair at the given index with its parent and repeatedly swapping them until no longer smaller than its parent.</p>
	 * @param index
	 */
	protected void bubleUp( int index ) {
		int p = parent( index );
		while ( index > 0 && getHeap().get( index ).compareTo( getHeap().get( p ).getKey() ) > 0 ) {
			swap( index , p );
			index = p ;
			p = parent( index );
		}
	}
	
	/**
	 * <p> Swaps the value of the 2 given indexes.</p>
	 * @param index1
	 * @param index2
	 */
	protected void swap( int index1, int index2 ) {
		PairNode <K,V> temp = getHeap().get( index1 );
		getHeap().set( index1, getHeap().get( index2 ) );
		getHeap().set( index2, temp );
	}
	
	/**
	 * <p> Compares the pair in the given index repeatedly with its two children and swaps them until no longer bigger than its children. </p>
	 * @param index
	 */
	protected void trickleDown( int index ) {
		do {
			int j = -1, right = right( index ), left = left( index );
			if ( right < size() && getHeap().get( right ).compareTo( getHeap().get( index ).getKey() ) > 0 )
				j = ( getHeap().get( left ).compareTo( getHeap().get( right ).getKey() ) > 0 )
						? left
						: right ;
			else if( left < size() && getHeap().get( left ).compareTo( getHeap().get( index ).getKey() ) > 0 )
					j = left;
			if ( j >= 0 )
				swap( index, j );
			index = j;
		} while ( index >= 0 );
	}
}