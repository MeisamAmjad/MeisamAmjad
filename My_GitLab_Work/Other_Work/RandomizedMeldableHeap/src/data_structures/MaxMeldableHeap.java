/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
public class MaxMeldableHeap< K extends Comparable<K>, V extends Comparable<V>> implements Iterable<PairNode<K, V>>, MeldableHeap<K, V>, MaxPriorityQueue<K, V> {

	/**
	 * <p>Holds the root of the tree. By default it would be null.</p>
	 * @see #getRoot()
	 * @see #setRoot(PairNode)
	 */
	private PairNode<K, V> root = null;
	
	/**
	 * Holds the number of elements in the tree. By default it would be zero.</p>
	 * @see #getN()
	 * @see #setN(int)
	 * @see #increaseSize()
	 * @see #decreaseSize()
	 */
	private int N = 0;
	
	/**
	 * <p> For tossing a coin to decide whether to merge h2 with h1.leftChild or h1.rightChild.</p>
	 * @see #merge(PairNode, PairNode)
	 */
	private Random rand = new Random();
	
	/**
	 * <p> Constructor.</p>
	 */
	public MaxMeldableHeap() {
		super();
	}
	
	/**
	 * <p> Constructor.</p>
	 */
	public MaxMeldableHeap( Collection<?> C ) {
		super();
		addAll( C );
	}
	
	/**
	 * <p> Constructor.</p>
	 */
	protected MaxMeldableHeap ( MaxMeldableHeap<K, V> newMH ) {
		super();
		setRoot( newMH.getRoot() );
		setN( newMH.getN() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#remove()
	 */
	@Override
	public V remove() {
		if ( isEmpty() )
			return null;
		V value = getRoot().getValue();
		setRoot( merge( getRoot().getLeft(), getRoot().getRight() ) );
		if ( getRoot() != null )
			getRoot().setParent( null );
		decreaseSize();
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#removeAll(java.util.Collection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean removeAll( Collection<?> C ) {
		if ( C == null )
			return false;
		int oldSize = size();
		for ( PairNode<K, V> element : ( List<PairNode<K, V>> ) C ) {
			PairNode<K, V> u = find( element.getValue(), getRoot(), null );
			if ( u != null ) {
				PairNode<K, V> prev = u.getParent(), w = merge( u.getLeft(), u.getRight() );
				if ( u == getRoot() )
					setRoot( w );	
				else if ( prev.getLeft() != null && prev.getLeft() == u )
					prev.setLeft( w );
				else
					prev.setRight( w );
				if ( w != null )
					w.setParent( prev );
				decreaseSize();
			}
		}
		return ( oldSize > size() )
				? true
				: false;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#retainAll(java.util.Collection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean retainAll( Collection<?> C ) {
		if ( C == null || isEmpty() )
			return false;
		int oldSize = size();
		List<PairNode<K, V>> list = ( List<PairNode<K,V>> ) C;
		List<PairNode<K, V>> removingList = new ArrayList<>();
		List<PairNode<K, V>> tree = preOrderTravers();
		for ( int i = 0; i < size(); i ++ )
			if ( ! list.contains( tree.get( i ) ) )
				removingList.add( tree.get( i ) );
		removeAll( removingList );
		return ( oldSize > size() )
				? true
				: false;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#peek()
	 */
	@Override
	public V peek() {
		return ( isEmpty() )
				? null
				: getRoot().getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#add(java.lang.Comparable, java.lang.Comparable)
	 */
	@Override
	public boolean add( V entity, K priority ) {
		if ( entity == null || priority == null )
			return false;
		PairNode<K, V> newNode = new PairNode<K, V>( priority, entity );
		setRoot( merge( newNode, getRoot() ) );
		getRoot().setParent( null );
		increaseSize();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#addAll(java.util.Collection)
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
	 * @see data_structures.MeldableHeap#find(java.lang.Comparable)
	 */
	@Override
	public K find( V entity ) {
		if ( isEmpty() )
			return null;
		PairNode<K, V> foundedNode = find( entity, getRoot() , null );
		return ( foundedNode == null )
				? null
				: foundedNode.getKey();
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains( V entity ) {
		return ( isEmpty() || find( entity ) == null )
				? false
				: true;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#size()
	 */
	@Override
	public int size() {
		return getN();
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.MeldableHeap#clear()
	 */
	@Override
	public void clear() {
		setRoot( null );
		setN( 0 );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<PairNode<K, V>> iterator() {
		if ( isEmpty() )
			return null;
		ArrayList< PairNode<K, V>> list = preOrderTravers();
		return list.iterator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if ( isEmpty() )
			throw new CloneNotSupportedException( "** The Object is EMPTY, meaning can not be cloned right now. **" );
		MaxMeldableHeap<K, V> newHeap = new MaxMeldableHeap<>();
		newHeap.setRoot( deepCopy( getRoot() ) );
		newHeap.setN( size() );
		return newHeap;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return  31 * ( getRoot().hashCode() * size() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ( isEmpty() )
				? null
				: printTree( getRoot(), 0, "ROOT" );
	}
	
	/**
	 * <p>Returns the root of the tree.</p>
	 * @see #root
	 */
	protected PairNode<K, V> getRoot() { return this.root; }
	
	/**
	 * <p> Sets a given value into the root.</p>
	 * @param root
	 * @see #root
	 */
	protected void setRoot( PairNode<K, V> root ) { this.root = root; }
	
	/**
	 * <p> Returns the value of the N.</p>
	 * @see #N
	 */
	protected int getN() { return this.N; }
	
	/**
	 * <p> Sets a given value into the N.</p>
	 * @param n int
	 * @see #N
	 */
	protected void setN( int n ) { this.N = n; }
	
	/**
	 * <p> Adde up one to the size of the tree.<p>
	 * @see #N
	 */
	protected void increaseSize() { ++ this.N; }
	
	/**
	 * <p> Deducts one from the size of the tree.</p>
	 * @see #N
	 */
	protected void decreaseSize() { -- this.N; }
	
	/**
	 * <p> A recursive method which takes two Nodes h1 and h2 and merge them, returning a node that is the root of the heap that contains all elements in the subtree</br>
	 * rooted at h1 and all elements in the subtree rooted at h2. The returned root holds the Min/Max priority value.<p> 
	 * @param h1
	 * @param h2
	 * @return PairNode object that represents the root. 
	 * @see #add(Comparable, Comparable)
	 * @see #remove()
	 */
	protected PairNode<K, V> merge( PairNode<K, V> h1, PairNode<K, V> h2 ) {
		if ( h1 == null )
			return h2;
		if ( h2 == null )
			return h1;
		if ( h2.compareTo( h1.getKey() ) > 0 )
			return merge( h2, h1 ); 
		if ( rand.nextBoolean() ) {
			h1.setLeft( merge( h1.getLeft(), h2 ) );
			h1.getLeft().setParent( h1 );
		} else {
			h1.setRight( merge( h1.getRight(), h2 ) );
			h1.getRight().setParent( h1 );
		}
		return h1;
	}
	
	/**
	 * <p> A recursive helper method that goes through all the nodes in pre_order and stops whenever finds a node that holds the given entity and returns the node.</br>
	 * It returns null if that won't find anything.</p>
	 * @param entity
	 * @param r
	 * @param result
	 * @return
	 * @see #find(Comparable)
	 */
	protected PairNode<K, V> find ( V entity, PairNode<K, V> r , PairNode<K, V> result ) {
		if ( r == null )
			return null;
		if ( r.getValue().equals( entity ) )
			result = r;
		if ( result == null )
			result = find( entity, r.getLeft() , result );
		if ( result == null )
			result = find( entity, r.getRight() ,result );
		return result;
	}
	
	/**
	 * <p> A helper method for Iterator method. Returns an ArrayList holding all the nodes from PRE-ORDER traversal.</p>
	 * @return ArrayList<PairNode<K, V>>
	 * @see #iterator()
	 */
	public ArrayList<PairNode<K, V>> preOrderTravers(){
		if ( getRoot() == null )
			 return null;
		LinkedList<PairNode<K, V>> stack = new LinkedList<PairNode<K,V>>();
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		stack.push( getRoot() );
		while( ! stack.isEmpty() ){
			PairNode<K, V> u = stack.pop();
			outputList.add( u );
			if ( u.getRight() != null ) stack.push( u.getRight() );
			if ( u.getLeft() != null ) stack.push( u.getLeft() );
		}
		return outputList;
	}
	
	/**
	 * <p> This method starts from the given r node and builds a string containing tree .</br>
	 * <b>This method is used by toString() method.</b><p> 
	 * @return <b> String</br> The whole tree</b> holding all points.
	 * @see java.lang.StringBuilder
	 * @see java.lang.String
	 * @see #toString()
	 */
	protected String printTree( PairNode<K,V> root, int indent, String s ) {
		StringBuilder builder = new StringBuilder();
		for ( int i = 0; i < indent; i ++ )
			builder.append( "\t" );
		if ( root == null ) {
			builder.append( "LEAF" + "\n" );
			return builder.toString();
		}
		builder.append( s + "->" + root.getKey() + ": " + root.getValue() + "\n" );
		builder.append( printTree( root.getLeft(), indent + 1, "left" ) );
		builder.append( printTree( root.getRight(), indent + 1, "right" ) );
		return builder.toString();
    }
	
	/**
	 * <p>Return PairNode holding a new tree's root which is a deep copy of the 
	 * original tree.</p>
	 * @param root
	 * @return
	 * @see #deepCopyRecursive_preOrder()
	 * @see #deepCopyRecursive_preOrder(PairNode)
	 */
	protected PairNode<K, V> deepCopy( PairNode<K, V> u ) {
		if ( u == null )
				return null;
		PairNode<K, V> r = new PairNode<K, V>( u.getKey(), u.getValue(), deepCopy( u.getLeft() ), null, deepCopy( u.getRight() ) );
		if ( r.getLeft() != null ) 
			r.getLeft().setParent( r );
		if ( r.getRight() != null )
			r.getRight().setParent( r );
		return r;
	}
}
