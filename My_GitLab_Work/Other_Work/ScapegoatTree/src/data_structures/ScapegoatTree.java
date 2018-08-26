package data_structures;

import java.lang.reflect.Array;

import javax.swing.tree.TreeNode;

/**
 * This DS keeps itself balanced by partial rebuilding operations.  
 * During a partial rebuilding operation, an entire subtree is deconstructed and
 * rebuilt into a perfectly balanced subtree.
 * A ScapegoatTree implements the SSet interface. Ignoring the cost of rebuild(u) 
 * operations, a ScapegoatTree supports the operations add(x), remove(x), and find(x)
 * in O(logn) time per operation. 
 * Furthermore, beginning with an empty ScapegoatTree, any sequence of m add(x) and 
 * remove(x) operations results in a total of O(m log m) time spent during all calls
 * to rebuild(u).
 * 
 * This gap in performance is due to the fact that, unlike the other SSet implementations
 * like Skiplist and Treap, a ScapegoatTree can spend a lot of time restructuring itself.
 * Although, height bound of log3/2q≈1.709logn+O(1) is better than the expected length of
 * a search path in a Skiplist and not too far from that of a Treap. 
 * @author amjadm@miamioh.edu
 *
 * @param <K>
 * @param <V>
 */
public class ScapegoatTree<K extends Comparable<K>, V extends Comparable<V>> extends BST<K, V> {
	// Maintains an upper-bound on the number of nodes 
	private int q;
	
	/*
	 * Constructors
	 */
	public ScapegoatTree() {
		super();
		setQ( 0 );
	}
	
	public ScapegoatTree( BSTNode<K, V> root ) {
		super( root );
		setQ( ( int ) ( Math.log( size() ) / Math.log( 2 ) ) );
	}
	
	public ScapegoatTree( ScapegoatTree<K, V> spgT ) {
		super( spgT.getRoot() );
		setQ( spgT.getQ() );
	}
	
	public int getQ() { return this.q; }
	
	public void setQ( int q ) { this.q = q; }
	
	/*
	 * This method recursively makes an array holding all the nodes in sorted.
	 * @param u Making an array starts from this node.
	 * @param a An array of tree's size().
	 * @param index Would be from 0 to tree'size()-1
	 * @return
	 */
	private int packIntoArray( BSTNode<K, V> currentNode, BSTNode<K, V>[] a, int index ) {
		if ( currentNode == null )
			return index;
		index = packIntoArray( currentNode.getLeft(), a, index );
		a[ index ++ ] = currentNode;
		return packIntoArray( currentNode.getRight(), a, index );
	}
	
	/**
	 * This method makes a new balanced BST from a given array which it's root starts from 
	 * a [ low + ( high / 2 ) ].
	 * @param a
	 * @param low
	 * @param high
	 * @return The root of the new balanced BST.
	 */
	private BSTNode<K, V> buildBalanced( BSTNode<K, V>[] a, int low, int high ) {
		if ( high == 0 )
			return null;
		int m = high / 2;
		a[ low + m ].setLeft( buildBalanced( a, low, m ) );
		a[ low + m ].setRight( buildBalanced( a, low + m + 1, high - m -1 ) );
		return a[ low + m ];
	}
	
	/*
	 * Reads all the nodes and saving them inside an array, then rebuilds a balanced BST from
	 * the array. 
	 * @param uNode A root of the BST that we want to rebuild. 
	 * @param parentNode A parent of uNode.
	 */
	@SuppressWarnings( "unchecked" )
	private void rebuild( BSTNode<K, V> uNode, BSTNode<K, V> parentNode ) {
		int n = countSize( uNode );
		BSTNode<K, V>[] A = ( BSTNode<K, V>[] ) Array.newInstance( BSTNode.class, n );
		packIntoArray( uNode, A, 0 );
		if ( parentNode == null )
			super.root = buildBalanced( A, 0, n);
		else if ( parentNode.getLeft() == uNode )
			parentNode.setLeft( buildBalanced( A, 0, n) );
		else 
			parentNode.setRight( buildBalanced( A, 0, n) );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#insert(java.lang.Comparable, java.lang.Comparable)
	 */
	@Override
	public void insert( K key, V value ) {
		super.insert( key, value );
		setQ( ( int ) ( Math.log( this.size() ) / Math.log( 2 ) ) );
		if ( height() > getQ() ){
			BSTNode<K, V> parent = findParent( key ), u;
			parent = findParent( key );
			if ( parent.getLeft() != null && compareKeys( key, parent.getLeft().getKey() ) == 0 )
				u = parent.getLeft();
			else 
				u = parent.getRight();
			while ( 3 * countSize( u ) <= 2 * countSize( parent )  && parent != null ){
				u = parent;
				parent = findParent( parent.getKey() );
			}
			rebuild( u, parent );	
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#remove(java.lang.Comparable)
	 */
	@Override
	public void remove( K key ) {
		super.remove( key );
		if ( 2 * n < getQ() ){
			rebuild( getRoot(), null );
			setQ( ( int ) ( Math.log( this.size() ) / Math.log( 2 ) ) );
		}
	}
}
