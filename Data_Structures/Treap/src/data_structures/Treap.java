package data_structures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Random;
import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * The treap and the randomized binary search tree are two closely related forms of binary 
 * search tree data structures that maintain a dynamic set of ordered keys and allow binary
 * searches among the keys. After any sequence of insertions and deletions of keys, the shape
 * of the tree is a random variable with the same probability distribution as a random binary
 * tree; in particular, with high probability its height is proportional to the logarithm of
 * the number of keys, so that each search, insertion, or deletion operation takes logarithmic
 * O(log n) time to perform.
 * In addition to being a BST, the nodes in a Treap also obey the heap property.
 * The heap and binary search tree conditions together ensure that, once the key (Key) and 
 * priority (P) for each node are defined, the shape of the Treap is completely determined.
 * I.e. the minimum priority has to be the root. Smaller keys are stored in the subtree rooted at
 * root.left and larger keys are stored in the subtree rooted at root.right.
 * 
 * A Treap implements the SSet interface. A Treap supports the operations add(x), remove(x), 
 * and find(x) in O(logn) expected time per operation.
 * 
 * It is worth comparing the Treap data structure to the SkiplistSSet data structure. Both implement
 * the SSet operations in O(logn) expected time per operation. In both data structures, add(x) and 
 * remove(x) involve a search and then a constant number of pointer changes. Thus, for both these 
 * structures, the expected length of the search path is the critical value in assessing their performance.
 * In a SkiplistSSet, the expected length of a search path is 2logn+O(1), In a Treap, the expected length 
 * of a search path is 2lnn+O(1)≈1.386logn+O(1). Thus, the search paths in a Treap are considerably 
 * shorter and this translates into noticeably faster operations on Treaps than Skiplists. We can shows
 * how the expected length of the search path in a Skiplist can be reduced to elnn+O(1)≈1.884logn+O(1)
 * by using biased coin tosses. Even with this optimization, the expected length of search paths in a 
 * SkiplistSSet is noticeably longer than in a Treap. 
 * @author Amjadm@miamioh.edu
 *
 * @param <K> Generic type for Key
 * @param <V> Generic type for Value
 */
public class Treap<K extends Comparable<K>, V extends Comparable<V>> extends BST<K, V> {
	
	// For producing a random priority number
	private Random randInt;
	
	/**
	 * Constructors
	 */
	public Treap(){
		
		super();
		setRandInt( new Random() );
		
	}
	
	public Treap( TreapNode<K, V> root ){
		super();
		setRoot( root );
		setRandInt( new Random() );
		super.n = countSize( root );
	}
	
	public Treap( Treap<K, V> newTreap ){
		super();
		setRoot( newTreap.getRoot() );
		setRandInt( newTreap.randInt );
		super.n = newTreap.n;
	}
	
	/*
	 * Setters and getters 
	 */
	public int getRandInt() { return  ( (int) this.randInt.nextInt(100) ) + 1; }
	
	public void setRandInt( Random randInt ) { this.randInt = randInt; }
	
	@Override
	public TreapNode<K, V> getRoot() { return (TreapNode<K, V>) super.getRoot(); }
	
	public void setRoot( TreapNode<K, V> root) { super.root = root; }
	
	/*
	 * This mehtod rotates the tree from u to the left.
	 * @param u A node that need to be rotated to the left.
	 * @param parent A parent of node u.
	 */
	private void rotateLeft( TreapNode<K, V> u, TreapNode<K, V> parent ) {
		TreapNode<K, V> w = u.getRight();
		if ( parent != null )
			if ( parent.getLeft() == u )
				parent.setLeft( w );
			else
				parent.setRight( w );
		u.setRight( w.getLeft() );
		w.setLeft( u );
		if ( u == getRoot() )
			setRoot( w );
	}
	
	/*
	 * This mehtod rotates the tree from u to the right.
	 * @param u A node that need to be rotated to the right.
	 * @param parent A parent of node u.
	 */
	private void rotateRight( TreapNode<K, V> u, TreapNode<K, V> parent ) {
		TreapNode<K, V> w = u.getLeft();
		if ( parent != null )
			if ( parent.getLeft() == u)
				parent.setLeft( w );
			else 
				parent.setRight( w );
		u.setLeft( w.getRight() );
		w.setRight( u );
		if ( u == getRoot() )
			setRoot( w );
	}
	
	/* For satisfying Heap properties, this methods does proper left and right rotations 
	 * until the addedNode becomes either the root or addedNode.p gets bigger than or equal
	 * to parentNode.p.
	 * @param addedNode The node that should be bubbled up. 
	 * @param parentNode The parent of the added node.
	 */
	private void bubbleUp ( TreapNode<K, V> addedNode, TreapNode<K, V> parentNode) {
		while ( parentNode != null && parentNode.getP() > addedNode.getP() ){
			if ( parentNode.getRight() == addedNode )
				rotateLeft( parentNode, ( parentNode == null ) ? null : (TreapNode<K, V>) findParent( parentNode.getKey() ) ) ;
			else 
				rotateRight( parentNode, ( parentNode == null ) ? null : (TreapNode<K, V>) findParent( parentNode.getKey() ) );
			// Because TreapNode does not have a parent node, it needed to find a new parent
			// node every time. Therefore, the following line adds another O(log n) expected 
			// time to this method just for finding a new parent.
			parentNode = ( TreapNode<K, V> ) super.findParent( addedNode.getKey() );
		}
	}
	
	/* This methods moves the u node downwards until it become a leaf, and then it splices
	 * the u from the treap. For moving the u node downwards, this methods does the proper 
	 * left and right rotations.
	 * @param u The given node that needs to be removed.
	 * @param parentNode the parent of u node.
	 */
	private void trickleDown( TreapNode<K, V> u, TreapNode<K, V> parentNode) {
		while ( u.getLeft() != null || u.getRight() != null ){
			if ( u.getLeft() == null )
				rotateLeft( u, parentNode );
			else if ( u.getRight() == null || u.getLeft().getP() < u.getRight().getP() )
				rotateRight( u, parentNode );
			else
				rotateLeft( u, parentNode );
			// Because TreapNode does not have a parent node, it needed to find a new parent
			// node every time. Therefore, the following line adds another O(log n) expected 
			// time to this method just for finding a new parent.
			parentNode = ( TreapNode<K, V> ) super.findParent( u.getKey() );
		}
	}
	
	/*
	 * Recursively visits each node checking all treap properties.
	 * @param root
	 * @param min_value
	 * @param max_value
	 * @return
	 */
	private boolean isTreap( TreapNode<K, V> root, K min_value, K max_value ) {
		if ( root == null )
			return true;
		if ( ( root.getLeft() != null && root.getP() > root.getLeft().getP() ) || ( root.getRight() != null && root.getP() > root.getRight().getP() ) )
			return false;
		if ( ( min_value != null && compareKeys( root.getKey(), min_value ) <= 0 ) || ( max_value != null && compareKeys( root.getKey(),max_value ) >= 0 ) )
			return false;
		return isTreap( root.getLeft(), min_value, root.getKey() ) &&
				isTreap( root.getRight(), root.getKey(), max_value );
	}
	
	/*
	 * Print out the structure on the display.
	 * (For debugging.)
	 */
	private void print_structure_helper( TreapNode<K,V> root, int indent, String s) {			
		for ( int i = 0; i < indent; i ++ )
			System.out.print("\t");
		if ( root == null ) {
			System.out.println("LEAF");
			return;
		}
		System.out.println( s + "->("+root.getKey() + "," + root.getP() +"):" + root.getValue() );
		print_structure_helper( root.getLeft(), indent + 1, "left" );
		print_structure_helper( root.getRight(), indent + 1, "right" );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#insert(java.lang.Comparable, java.lang.Comparable)
	 */
	@Override
	public void insert(K key, V value) {
		TreapNode<K, V> u = ( TreapNode<K, V> ) super.findNode( key ),
				newNode = new TreapNode<K, V>( key, value, getRandInt() );
		
		if ( ! super.addChild( u, newNode ) )
			throw new KeyAlreadyExistsException( "Duplication is not allowed" );
		bubbleUp( newNode , u );
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#remove(java.lang.Comparable)
	 */
	@Override
	public void remove(K key) {
		if ( getRoot() == null )
			throw new EmptyStackException();
		TreapNode<K, V> parentNode = ( TreapNode<K, V> ) super.findParent( key ),
						currentNode = null;
		if ( parentNode == null )
			currentNode = getRoot();
		else if ( parentNode.getLeft() != null && super.compareKeys( key, parentNode.getLeft().getKey() ) == 0 )
			currentNode = parentNode.getLeft();
		else if ( parentNode.getRight() != null && super.compareKeys( key, parentNode.getRight().getKey() ) == 0 )
			currentNode = parentNode.getRight();
		else 
			throw new NoSuchElementException();
		
		trickleDown( currentNode, parentNode );
		super.splice( currentNode, (TreapNode<K, V>) findParent( currentNode.getKey() ) );
		-- super.n;
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#find(java.lang.Comparable)
	 */
	@Override
	public V find(K key) {
		return super.find( key );
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isTreap( getRoot(), null, null );
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper ( getRoot(), 0, "Root" );
	}

	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#clear()
	 */
	@Override
	public void clear() {
		setRandInt( new Random() );
		super.clear();
	}
}
