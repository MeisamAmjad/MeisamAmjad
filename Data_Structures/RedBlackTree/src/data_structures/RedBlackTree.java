package data_structures;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * A Red-Black Trees, a version of binary search trees with logarithmic height, and
 * in each node, u, has a color which is either red or black. Red is represented 
 * by the value 0 and black by the value 1.
 * 1. A red-black tree storing n values has height at most 2 log n.
 * 2. The add(x) and remove(x) operations on a red-black tree run in O( logn ) 
 * worst-case time.
 * 3. The amortized number of rotations performed during an add(x) or remove(x) 
 * operation is constant. Even AVL tree does not guarantee this time. 
 * 
 * The first two of these properties already put red-black trees ahead of skiplists,
 * treaps, and scapegoat trees. Skiplists and treaps rely on ran- domization and 
 * their O(log n) running times are only expected. Scapegoat trees have a guaranteed
 * bound on their height, but add(x) and remove(x) only run in O(logn) amortized time.
 *   
 * @author amjadm@miamioh.edu
 *
 * @param <K> Generic type for the Key.
 * @param <V> Generic type for the value.
 */
public class RedBlackTree<K extends Comparable<K>, V extends Comparable<V>> extends BST<K, V> {
	
	private byte red = (byte) 0;
	private byte black = (byte) 1;
	
	/*
	 * Constructors
	 */
	public RedBlackTree() {
		super();
		setRoot( new RBTNode<>(null,null, black, null,null,null) );
	}
	
	public RedBlackTree( RBTNode<K, V> RBT ) {
		super();
		setRoot( RBT );
		this.n = countSize( RBT );
	}
	
	public RedBlackTree( RedBlackTree<K, V> RBT ) {
		super();
		setRoot( RBT.getRoot() );
		this.n = RBT.size();
	}
	
	/*
	 * Takes as input a black node u that has two red children and colors
	 * u red and its two children black.
	 * The pullBlack( u ) method reverses this operation.
	 * @param u
	 */
	private void pushBlack( RBTNode<K, V> u ) {
		u.setColor( ( byte ) ( u.getColor() - 1 ) );
		//if ( u.getLeft() != nullNode )
			u.getLeft().setColor( ( byte ) ( u.getLeft().getColor() + 1 ) );
	//	if ( u.getRight() != nullNode )
			u.getRight().setColor( ( byte ) ( u.getRight().getColor() + 1 ) );
	}
	
	/*
	 * Takes as input a black node u that has two black children and colors
	 * u black and its two children red.
	 * The pushBlack( u ) method reverses this operation.
	 * @param u
	 */
	private void pullBlack( RBTNode<K, V> u ) {
		u.setColor( ( byte ) ( u.getColor() + 1 ) );
		//if ( u.getLeft() != nullNode )
			u.getLeft().setColor( ( byte ) ( u.getLeft().getColor() - 1 ) );
		//if ( u.getRight() != nullNode )
			u.getRight().setColor( ( byte ) ( u.getRight().getColor() - 1 ) );
	}
	
	/*
	 * Swaps the colors of u and u.right then performs a left rotation at u. 
	 * This method reverses the colors of these two nodes as well as their 
	 * parent-child relationship. 
	 * @param u
	 */
	private void flipLeft( RBTNode<K, V> u ) {
		swapColors( u, u.getRight() );
		rotateLeft( u );
	}
	
	/*
	 * Swaps the colors of u and u.left then performs a right rotation at u. 
	 * This method reverses the colors of these two nodes as well as their 
	 * parent-child relationship. 
	 * @param u
	 */
	private void flipRight( RBTNode<K, V> u ) {
		swapColors( u, u.getLeft() );
		rotateRight( u );
	}
	
	private void swapColors ( RBTNode<K, V> u1, RBTNode<K, V> u2 ) {
		byte temp;
		temp = u1.getColor();
		u1.setColor( u2.getColor() );
		u2.setColor( temp );
	}
	
	/*
	 * This mehtod rotates the tree from u to the left.
	 * @param u A node that need to be rotated to the left.
	 * @param parent A parent of node u.
	 */
	private void rotateLeft( RBTNode<K, V> u ) {
		RBTNode<K, V> w = u.getRight();
		w.setParent( u.getParent() );
		if ( w.getParent() != null )
			if ( w.getParent().getLeft() == u )
				w.getParent().setLeft( w );
			else
				w.getParent().setRight( w );
		u.setRight( w.getLeft() );
		u.getRight().setParent( u );
		
		w.setLeft( u );
		w.getLeft().setParent( w );
		if ( u == getRoot() ) {
			setRoot( w );
			getRoot().setParent( null );
		}
	}
	
	/*
	 * This mehtod rotates the tree from u to the right.
	 * @param u A node that need to be rotated to the right.
	 * @param parent A parent of node u.
	 */
	private void rotateRight( RBTNode<K, V> u ) {
		RBTNode<K, V> w = u.getLeft();
		w.setParent( u.getParent() );
		if (  w.getParent() != null )
			if ( w.getParent().getLeft() == u )
				w.getParent().setLeft( w );
			else 
				w.getParent().setRight( w );
		u.setLeft( w.getRight() );
		u.getLeft().setParent( u );
		
		w.setRight( u );
		w.getRight().setParent( w );
		if ( u == getRoot() ){
			setRoot( w );
			getRoot().setParent( null );
		}
	}
	
	/* Impelements all 
	 * 
	 * @param u
	 */
	private void addFixup( RBTNode<K, V> u ) {
		while ( u.getColor() == red ){
			if ( u == getRoot() ) { // u is the root - done
				u.setColor( black );
				return;
			}
			RBTNode<K, V> w = u.getParent();
			if ( w.getLeft().getColor() == black ) { // ensure left-leaning
				flipLeft( w );
				u = w;
				w = u.getParent();
			}
			if ( w.getColor() == black )
				return; // no red-red edge = done
			RBTNode<K, V> g = w.getParent(); // grandparent of u. Here we have red-red edge case
			if ( g.getRight().getColor() == black ) {
				flipRight( g );
				return;
			} else { // Both g's children are red
				pushBlack( g ); // There is Red edges
				u = g;
			}
		}
	}
	
	/**
	 * Adds a new node into the tree.
	 * Then fixes up the red_black tree properties. 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add( K key, V value ) {
		if ( key == null || value == null )
			return false;
		RBTNode<K, V> u = ( RBTNode<K, V> ) findLast( key ),
				newNode = new RBTNode<>( key, value, red, null , new RBTNode<>(null,null, black, null,null,null), new RBTNode<>(null,null, black, null,null,null) );
		boolean added = super.addChild( u, newNode );
		if ( added )
			addFixup ( newNode );
		return added;
	}
	
	private RBTNode<K, V> findLast( K key ) {
		RBTNode<K, V> currentNode = ( RBTNode<K, V> ) getRoot(), prevNode = null;
		while ( currentNode.getKey() != null ) {
			prevNode = currentNode;
		    int comp = compareKeys( key, currentNode.getKey() );
		    if ( comp < 0 ) 
		    	currentNode = currentNode.getLeft();
		    else if ( comp > 0 ) 
		    	currentNode = currentNode.getRight();
		    else 
		    	return currentNode;
		}
		return prevNode;
	}
	
	/*
	 * u's sibling , v, is black, and u is the left child of its parent.
	 * We call pullBlack, making u black, v red, and darkening the color
	 * of w to black or double black. At this point, w does not satisfy
	 * the left-leaning property, so we call flipLeft(w) to fix this.
	 * At this point, w is red and v is the root of subtree with which we
	 * started. We need to check if w causes the no-red-edge property to
	 * be violated. We do this with inspecting w.s right child, q. If q is
	 * black, then w satisfied the no-red-edge property and we can continue
	 * the next iteration with u = v.
	 * otherwise (q is red), so both the no-red-edge property and the left
	 * -leaning property are violated at q and w, respectively. The left-
	 * leaning is restored with rotateLeft(w), but the no-red-edge is still
	 * violated. At this point, q is the left child of v, w is the left 
	 * child of q, q and w aare both red, and v is black or double-black. A
	 * flipLeft(v) makes q the parent of both v and w. Then pushBlack sets
	 * both v and w to black and the color of q back to the original color
	 * of w.
	 * Only one possible remains: the right child of v may be red, in which
	 * case the left-leaning property would be violated and it can be fixed
	 * by flipLeft(v) if it is necessary.   
	 * @param u
	 * @return
	 */
	private RBTNode<K, V> removeFixupCase2( RBTNode<K, V> u ){
		RBTNode<K, V> w = u.getParent(),
					v = w.getRight();
		pullBlack( w ); // v becomes red, u becomes black ( ? because u was double black )
		flipLeft( w ); // w is now red
		RBTNode<K, V> q = w.getRight();
		if ( q.getColor() == red ) { // q-w is red-red
			rotateLeft( w );
			flipRight( v );
			pushBlack( q );
			if ( v.getRight().getColor() == red ) // left-leaning violation 
				flipLeft( v );
			return q;
		} else 
			return v;
	}
	
	/*
	 * Given u's sibling is black and u is right child of its parent.
	 * This case is symmetric to Case 2 and is handled mostly the same way.
	 * The only differences come from the fact that the left-leaning 
	 * property is asymmetric, so it requires different handling.  
	 * @param u
	 * @return
	 */
	private RBTNode<K, V> removeFixupCase3( RBTNode<K, V> u ){
		RBTNode<K, V> w = u.getParent(),
				v = w.getLeft();
		pullBlack( w ); 
		flipRight( w ); // w is now red
		RBTNode<K, V> q = w.getLeft();
		if ( q.getColor() == red) { // q-w is red-red
			rotateRight( w );
			flipLeft( v );
			pushBlack( q );
			return q;
		} else 
			if ( v.getLeft().getColor() == red ) { // v has two red children
				pushBlack( v ); // Pushing back extra black by pushBlack
				return v;
			} else { // v's left child is black. It violated the left-leaning
				flipLeft( v );
				return w;
			}
	}
	
	private void removeFixup( RBTNode<K, V> u ) {
		while ( u.getColor() > black ) {
			if ( u == getRoot() ) // Case 0
				u.setColor( black );
			else if ( u.getParent().getLeft().getColor() == red ) // Case 1- left children of u, v, is red
				flipRight( u.getParent() ); // Leads immediately to Case 3 
			else if ( u == u.getParent().getLeft() ) // Case 2. u is left child of its parent and red
				u = removeFixupCase2( u );
			else // Case 3
				u = removeFixupCase3( u ); // u's parent is black. u is a right child and black like its sibling, v.
		}
		if ( u != getRoot() ) { // Restore left-leaning property if needed
			RBTNode<K, V> w = u.getParent();
			if (  w.getLeft().getColor() == black && w.getRight().getColor() == red )
				flipLeft( w );
		}
	}
	
	/*
	 * Removes the removedNode based on parentsNode's position. 
	 * Links and swaps the proper nodes to each other    
	 * @param u
	 * @param prev
	 */
	private void spliceNode( RBTNode<K, V> removingNode ) {
		RBTNode<K,V> substitudeNode, p;
		
		// 1- In case of it has either one or two children or even none. 
		// If it has two children we will pick the right child as a substitute.
		// In case of there is no children substitute would be null.
		if ( removingNode.getLeft().getKey() != null ) 
			substitudeNode = removingNode.getLeft();
		else 
			substitudeNode = removingNode.getRight();
		//If the root is been removing
		if ( removingNode == getRoot() ) {
			setRoot( substitudeNode );
			p = null; 
		} else {//If another node but root is been removing
			p = removingNode.getParent();
			if ( p.getLeft() == removingNode ) 
				p.setLeft( substitudeNode );
			else 
				p.setRight( substitudeNode ); 
		}
		if ( substitudeNode != null ) 
			substitudeNode.setParent( p );
	}
	
	/**
	 * Remove the node whose key is given. 
	 * @param key
	 * @return
	 */
	public boolean delete ( K key ) {
		RBTNode<K, V> u = ( RBTNode<K, V> ) findLast( key );
		if ( size() == 0 || compareKeys( key,  u.getKey() ) != 0 )
			return false;
		RBTNode<K, V> w = u.getRight();
		if ( w.getKey() == null ) {
			w = u;
			if ( w.getLeft().getKey() != null )
				u = w.getLeft();
			else 
				u = w.getRight();
		} else {
			while ( w.getLeft().getKey() != null )
				w = w.getLeft();
			u.setKey( w.getKey() );
			u.setValue( w.getValue() );
			u = w.getRight();
		}
		spliceNode( w );
		u.setColor(  ( byte ) ( u.getColor() + w.getColor() ) );
		removeFixup( u );
		
		-- this.n;
		return true;
	}
	
	/*
	 * Show the whole tree on the display.
	 * @param root
	 * @param indent
	 * @param s
	 */
	private void print_structure_helper( RBTNode<K,V> root, int indent, String s ) {			
		for ( int i = 0; i < indent; i ++ )
			System.out.print("\t");
		if ( root.getKey() == null ) {
			System.out.println( "LEAF" );
			return;
		}
		System.out.println( s + "->"+root.getKey() + ": " + root.getValue() + ": "+ 
					( ( root.getColor() < 0 ) ? "white" : ( root.getColor() == 0 ) ? "Red" : ( root.getColor() > 1 ) ? "Double-black" : "Black"  ) );
		print_structure_helper( root.getLeft(), indent + 1, "left" );
		print_structure_helper( root.getRight(), indent + 1, "right" );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#print_structure()
	 */
	@Override
	public void print_structure () {
		print_structure_helper( ( RBTNode<K, V> ) getRoot(), 0 , "ROOT" );
	}
	
	@Override
	public ArrayList<BSTNode<K, V>> inOrderSortTravers() {
		if ( getRoot().getKey() == null )
			 return null;
		LinkedList<BSTNode<K, V>> stack = new LinkedList<BSTNode<K,V>>();
		ArrayList<BSTNode<K, V>> outputList = new ArrayList<>();
		BSTNode<K, V> u = getRoot();
		while( u.getKey() != null ) {
			stack.push(u);
			u = u.getLeft();
		}
		while( ! stack.isEmpty() ) {
			u = stack.pop();
			outputList.add( u );
			if ( u.getRight().getKey() != null ) {
				u = u.getRight();
				while( u.getKey() != null ) {
					stack.push( u );
					u = u.getLeft();
				}
			}
		}
		return outputList;
	}
	
	/**
	 * Finds the Node that holds the given key or a proper leaf in case 
	 * of it could not find a node with the given key in it.
	 * @param key
	 * @return
	 */
	private RBTNode<K, V> findHelper( K key ) {
		RBTNode<K, V> currentNode = ( RBTNode<K, V> ) getRoot(), prevNode = null;
		while ( currentNode.getKey() != null ) {
			prevNode = currentNode;
		    int comp = compareKeys( key, currentNode.getKey() );
		    if ( comp < 0 ) 
		    	currentNode = currentNode.getLeft();
		    else if ( comp > 0 ) 
		    	currentNode = currentNode.getRight();
		    else 
		    	return currentNode;
		}
		return prevNode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.BST#find(java.lang.Comparable)
	 */
	@Override
	public V find( K key ) {
		if ( isEmpty() )
			throw new EmptyStackException();
		
		RBTNode<K, V> resultNode = findHelper( key );
		return ( compareKeys( resultNode.getKey(), key ) == 0 )
				? resultNode.getValue()
				: null;
	} 
	
	private boolean isBST( BSTNode<K,V> root, K min_value, K max_value ) {
		if ( root.getKey() == null )
			return true;
		if ( ( min_value != null && compareKeys( root.getKey(), min_value ) <= 0 ) || ( max_value != null && compareKeys( root.getKey(),max_value ) >= 0 ) )
			return false;
		
		return isBST( root.getLeft(), min_value, root.getKey() ) 
			&& isBST( root.getRight(), root.getKey(), max_value );
	}
	
	private final boolean isBST( BSTNode<K,V> root ) {
		return isBST( root, null, null );
	}
	
	@Override
	public boolean check_structure() {
		return isBST( root );
	}
	
	public boolean isRBT( RBTNode<K, V> root ) {
		return ( ( root == super.getRoot() && ( ( RBTNode<K, V> ) ( super.getRoot() ) ).getColor()  == black )
				&& isRBTHelper( ( RBTNode<K, V> ) getRoot().getLeft() ) == isRBTHelper( ( RBTNode<K, V> ) getRoot().getLeft() ) )
				? true
				: false;
	}
	
	private int isRBTHelper( RBTNode<K, V> r ) {
		if ( r == null )
			return 0;
		if ( r.getKey() == null) 
			return 1;
		return 0 + isRBTHelper( ( RBTNode<K, V> ) r.getLeft() ) + isRBTHelper( ( RBTNode<K, V> ) r.getRight() );
	}
}
