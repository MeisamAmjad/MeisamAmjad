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
import java.util.ListIterator;
import java.util.Queue;

/**
 * <p><b>Binary search trees (BST),</b> sometimes called ordered or sorted binary trees, are a particular type of container: data structures that store</br>
 * "items" (such as numbers, names etc.) in memory. They allow fast lookup, addition and removal of items, and can be used to implement</br>
 * either dynamic sets of items, or lookup tables that allow finding an item by its key (e.g., finding the phone number of a person by name).</br>
 * Binary search trees keep their keys in sorted order, so that lookup and other operations can use the principle of binary search: when looking</br>
 * for a key in a tree (or a place to insert a new key), they traverse the tree from root to leaf, making comparisons to keys stored in the nodes</br>
 * of the tree and deciding, based on the comparison, to continue searching in the left or right subtrees. On average, this means that each </br>
 * comparison allows the operations to skip about half of the tree, so that each lookup, insertion or deletion takes time proportional to the</br>
 * logarithm of the number of items stored in the tree. This is much better than the linear time required to find items by key in an (unsorted) </br>
 * array, but slower than the corresponding operations on hash tables.</p>
 * <p>For <i><b>programmer</b></i> all non public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 *@see <a href="https://en.wikipedia.org/wiki/Binary_search_tree">Binary Search Tree (Wikipedia)</a
 * @param <K> Generic type for K
 * @param <E> Generic type for V
 */
public class BST<K extends Comparable<K>,V extends Comparable<V>> extends Dictionary<K, V> implements Iterable<PairNode<K, V>>, Comparable<BST<K, V>>{

	/**
	 * <p>Holds the root of the tree. By default it holds null meaning the tree is empty.</p>
	 * @see #getRoot()
	 * @see #setRoot(PairNode root)
	 */
	private PairNode<K, V> root = null;
	
	/**
	 * <p>Constructor.</p>
	 */
	public BST() {
		super();
	}
	
	/**
	 * <p>Constructor.</p>
	 */
	public BST( BST<K, V> newBST ) {
		super();
		setRoot( newBST.getRoot() );
		setN( newBST.size() );
	}
	
	/**
	 * <p><b>Constructor </b>, making a BSTree from the given list without </br>
	 * any duplications. First, it sorts the given list which takes O(N log N), i.e.,</br>
	 * it would be able to create a more balanced KDTree. This constructor takes </br>
	 * O(N) for adding points into the tree.</P> 
	 * @param <Collection> A java.awt.List class.
	 * @param list A list of (x,y) points.
	 * @see	{@link java.util.Collection}
	 * @see {@link java.util.Collections#sort}
	 */
	public BST( Collection<? extends PairNode<K, V>> list ) {
		super();
		if ( !addAll( list ) )
			setRoot( null );
	}
	
	/**
	 * <p>Constructor.</p>
	 */
	protected BST( PairNode<K, V> root ) {
		super();
		setRoot( root );
		setN( countSize( root ) );
	}
	
	/**
	 * <p> Using this constants as an input argument for toCollection( enum E ) method for determining which type of Collections you want for the output.</p>
	 * @see #toCollection(depthFirstSearch)
	 */
	public enum depthFirstSearch { preOrder, inOrder, postOrder; }
	
	/**
	 * <p> Using this constant for determining which type of Collections you want as an output by using toCollection( enum E ) method.<p>
	 * @see #toCollection(breathFirstSearch)
	 */
	public enum breathFirstSearch { bFS; }
	
	/**
	 * <p> Returns three different depth First search order by using depthFirstSearch { preOrder, inOrder, postOrder; } constants as an input argument.</br>
	 * It would return null if the wrong value as an input argument is used.</p>  
	 * @param E
	 * @return
	 * @see #depthFirstSearch
	 */
	public Collection<? extends PairNode<K, V>> toCollection( depthFirstSearch E ) {
		switch( E ) {
			case preOrder:
				return preOrderTraversal();
			case inOrder:
				return inOrderTraversal();
			case postOrder:
				return postOrderRecursive();
		}
		return null;
	}
	
	/**
	 * <p> Returns a BFS order of all elements by using breathFirstSearch { bFS; } constant as an input argument.</br>
	 * It would return null if the wrong value as an input argument is used.<p> 
	 * @param E
	 * @return
	 * @see #depthFirstSearch
	 */
	public Collection<? extends PairNode<K, V>> toCollection( breathFirstSearch E ) {
		return ( E == breathFirstSearch.bFS )
				? BFS()
				: null;
	}
	
	/**
	 * <p>Returns a list of all key-value elements in ascending order by keys.</p>
	 * @return Collection<? extends PairNode<K, V>> object in ascending order.
	 */
	public Collection<? extends PairNode<K, V>> sort() {
		return ( isEmpty() )
				? null
				: toCollection( depthFirstSearch.inOrder );
	}
	
	/**
	 * <p>Returns the smallest value in the tree.  (Return null if empty) </p>
	 * @return key
	 */
	public K min() {
		if ( isEmpty() )
			return null;
		PairNode<K, V> currentNode = getRoot(); 
		while ( currentNode.getLeft() != null ) 
			currentNode = currentNode.getLeft();
		return currentNode.getKey();
	}
	
	/**
	 * <p>Returns the smallest value in the tree.  (Return null if empty) </p>
	 * @return key
	 */
	public K max() {
		if ( isEmpty() )
			return null;
		PairNode<K, V> currentNode = getRoot();
		while ( currentNode.getRight() != null ) 
			currentNode = currentNode.getRight();
		return currentNode.getKey();
	}
	
	/**
	 * <p>Returns a listIterator of all elements. Note that it gives an access to all elements in ascending order.</br>
	 * listIterator has more methods and abilities rather than iterator() method. iterator() returns iterator </br>
	 * to all elements in BFS order.</p>
	 * @see #iterator()
	 * @return null if the tree is empty.
	 */
	public ListIterator<? extends PairNode<K, V>> listIterator() {
		if ( isEmpty() )
			return null;
		Collection<? extends PairNode<K, V>> list = sort();
		return ( ListIterator<? extends PairNode<K, V>> ) list.iterator();
	}
	
	/**
	 * <p> Returns true if and only if the tree contains the given key.</p>
	 * @see data_structures.Dictionary#in(K)
	 * @param key
	 * @return
	 */
	@Override
	public boolean contains( K key ) {
		return ( !validate( key ) )
				? false
				: find( key ) != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#containsAll(java.util.Collection)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public boolean containsAll( Collection<?> C ) {
		if ( !validate( C ) )
			return false;
		try{
			Collection <PairNode<K, V>> elements = ( Collection <PairNode<K, V>> ) C;
			for ( PairNode<K, V> element: elements )
				if ( !contains( element.getKey() ) )
					return false;
			return true;
		} catch ( Exception E ){
			throw new ClassCastException();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#set(java.lang.Comparable, java.lang.Comparable)
	 */
	@Override
	public V set( K key, V newValue ) {
		if ( !validate( key ) )
			return null;
		PairNode<K, V> resultNode = findNode( key );
		if ( resultNode == null || resultNode.getKey().compareTo( key ) != 0 )
				return( null );
		V oldValue = resultNode.getValue();
		resultNode.setValue( newValue );
		return oldValue;
	}
	
	/**
	 * <p> Returns an iterator of all elements inside the tree. It goes through all elements in BFS order.</p>
	 * @see #listIterator()
	 */
	@Override
	public Iterator<PairNode<K, V>> iterator() {
		return ( isEmpty() )
				? null
				: new BSTIterator<PairNode<K, V>>();
	}
	
	/**
	 * <p> Note that add method prevents duplication.</p>
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#add(java.lang.Comparable, java.lang.Comparable)
	 * @see #add(PairNode)
	 */
	@Override
	public boolean add( K key, V value ) {
		return ( !validate( key ) )
				? false
				: add( new PairNode<K, V>( key, value ) );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#addAll(java.util.Collection)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public boolean addAll( Collection<?> C ) {
		if ( !validate( C ) )
			return false;
		try{
			Collection <PairNode<K, V>> elements = ( Collection <PairNode<K, V>> ) C;
			int originalSize = size();
			readList( this, ( List<PairNode<K,V>> ) elements, 0, elements.size() );
			return ( originalSize < size() ) ? true : false;
		}catch ( Exception E ){
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * <p>Removes a key from the dictionary.</p>
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Comparable)
	 * @see #remove(PairNode)
	 */
	@Override
	public boolean remove( K key ) {
		return ( !validate( key ) || isEmpty() )
				? false
				: remove( new PairNode<K, V>( key, null ) );
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#removeAll(java.util.Collection)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public boolean removeAll( Collection<?> C) {
		if ( !validate( C ) || isEmpty() )
			return false;
		try{
			Collection <PairNode<K, V>> elements = ( Collection <PairNode<K, V>> ) C;
			int originalSize = size();
			for ( PairNode<K, V> element: elements)
				remove( element );
			return ( originalSize > size() ) ? true : false ;
		}catch ( Exception E ){
			throw new UnsupportedOperationException();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#retainAll(java.util.Collection)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public boolean retainAll( Collection<?> C ) {
		if ( !validate( C ) || isEmpty() )
			return false;
		try{
			Collection <PairNode<K, V>> elements = ( Collection <PairNode<K, V>> ) C;
			if ( C.isEmpty() )
				return false;
			BST<K,V> newTree = readList( elements );
			setRoot( newTree.getRoot() );
			setN( newTree.size() );
			return ( size() > 0 ) ? true : false;
		}catch ( Exception E ){
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * <p>Search for a key value.</p>
	 * @see data_structures.Dictionary#find(java.lang.Comparable)
	 * @return value associated with key.  Returns null if key not found.
	 */
	@Override
	public V find( K key ) {
		if ( !validate( key ) )
			return null;
		PairNode<K, V> resultNode = findNode( key );
		return ( resultNode != null && resultNode.getKey().compareTo( key ) == 0 )
				? resultNode.getValue()
				: null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ( isEmpty() )
				? "null"
				: printTree( getRoot(), "", true );
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return ( isEmpty() )
				? 0
				: 31 * ( getRoot().hashCode() + size() + height() );
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	@SuppressWarnings("unused")
	public void clear() {
		Iterator<PairNode<K, V>> list = iterator();
		if ( list == null )
			return;
		while ( list.hasNext() ) {
			PairNode<K, V> x = list.next();
			x = null;
		}
		setN( 0 );
		setRoot( null );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( BST<K, V> other ) {
		if ( ( this == null ) || ( other == null )  || !( other instanceof BST<?, ?> ) )
			throw new IllegalArgumentException( "**Objects can not be null!**" );
		int h1 = height() , h2 = other.height(), 
			r1 = size() / h1, r2 = other.size() / h2; // Calculating ( size / height ) for each KD-tree.
		return ( r1 > r2 ) ? 1 : ( r1 < r2 ) ? -1 : 0;
	}
	
	/**
	 * <p> Makes a deep copy of the BST if it is not empty.</p>
	 * @see java.lang.Object#clone()
	 * @see #deepCopy(PairNode)
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if ( isEmpty() )
			throw new CloneNotSupportedException( "** The object is EMPTY, meaning cannot be clone right now. **" );
		BST<K, V> newBST = new BST<>();
		newBST.setRoot( deepCopy( getRoot() ) );
		newBST.setN( size() );
		return newBST;
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Container#validate()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean validate( Object o ) {
		if ( o != null ) {
			if ( o instanceof PairNode<?, ?> ) {
				PairNode<K, V> obj = ( PairNode<K, V> ) o;
				if ( obj.getKey() != null )
					return true; 
			} else if ( o instanceof Collection<?> ) {
				Collection<? extends PairNode<K, V>> obj = ( Collection<? extends PairNode<K, V>> ) o;
				if ( !obj.isEmpty() )
					return true;
			} else if ( o instanceof BST ){
				return isBST( ( ( BST<K, V> ) o ).getRoot() );
			}else
				return true;
		}
		return false; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see data_structures.Dictionary#validate()
	 */
	@Override
	public boolean validate() {
		return isBST( getRoot() );
	}
	
	/**
	 * <p>Sets the tree's Root node a new value.</p>
	 * @see #root
	 */
	protected void setRoot( PairNode<K, V> root ) { this.root = root; }
	
	/**
	 * <p>Returns the tree's Root node.</p>
	 * @see #root
	 */
	protected PairNode<K, V> getRoot() { return this.root; }
	
	/**
	 * <p> Adds a new node into the tree preventing duplication.</p>
	 * @see data_structures.Dictionary#add(java.lang.Comparable, java.lang.Comparable)
	 * @see #findNode(key)
	 * @see #addChild(PairNode,PairNode)
	 */
	protected boolean add( PairNode<K, V> newNode ) {
		PairNode<K, V> u = findNode( newNode.getKey() );
		if ( addChild( u, newNode ) ) {
			increaseSize();
			return true;
		}
		return false;
	}

	/**
	 * <p>Finds the Node that holds the given key or a proper leaf in case 
	 * of it could not find a node with the given key in it.</p>
	 * @param key
	 * @return PairNode object.
	 * @see #add(key,value)
	 * @see #remove(key)
	 * @see #find(key)
	 */
	protected PairNode<K, V> findNode ( K key ) {
		PairNode<K, V> currentNode = getRoot(), prevNode = null;
		while ( currentNode != null ) {
			prevNode = currentNode;
		    int comp = currentNode.getKey().compareTo( key );
		    if ( comp > 0 ) 
		    	currentNode = currentNode.getLeft();
		    else if ( comp < 0 ) 
		    	currentNode = currentNode.getRight();
		    else 
		    	return currentNode;
		}
		return prevNode;
	}
	
	/**
	 * <p>Inserts the given newNode in proper position inside the tree
	 * depends on the lastFindedNode which would be the newNode's parent.</p>
	 * @param lastFindedNode 
	 * @param newNode 
	 * @see #add(key,valu)
	 * @see #findNode(key)
	 */
	protected boolean addChild( PairNode<K, V> lastFindedNode, PairNode<K,V> newNode ) {
		if ( !validate( lastFindedNode ) ) 
			setRoot( newNode );// Inserting into an empty tree
		else {
			int comp = newNode.getKey().compareTo( lastFindedNode.getKey() );
		    if ( comp < 0 ) 
		    	lastFindedNode.setLeft( newNode );
		    else if ( comp > 0 ) 
		    	lastFindedNode.setRight( newNode );
		    else 
		    		return false;
		}
		newNode.setParent( lastFindedNode );
		return true;
	}
	
	/**
	 * <p>Removes a specific node from the tree.</p>
	 * @param removingNode 
	 * @return <b> true </b> if it gets done successfully, otherwise returns <b> false</b>.
	 * @see #remove(key)
	 * @see #remove(PairNode)
	 */
	protected boolean removeChild( PairNode<K, V> removingNode ){
		if ( removingNode.getLeft() == null || removingNode.getRight() == null ) 
			//Case 1: Either it is a leaf or has only one child.
			splice( removingNode );
		else{
			//Case 2: It is not either leaf nor has only one child.
			PairNode<K, V> substitutrNode = removingNode.getRight();
			while ( substitutrNode.getLeft() != null ) {
				substitutrNode = substitutrNode.getLeft();
			}
			removingNode.setValue( substitutrNode.getValue() );
			removingNode.setKey( substitutrNode.getKey() );
			splice( substitutrNode );
		}
		return true;
	}
	
	/**
	 * <p>Removes a node from the dictionary.</p>
	 * @param u a given node that is been removing.
	 * @return <b> true </b> if it gets done successfully, otherwise returns <b> false</b>.
	 * @see #removeChild(PairNode)
	 * @see #remove(key)
	 */
	protected boolean remove ( PairNode<K, V> u ) {
		PairNode<K, V> removingNode = findNode( u.getKey() );
		if ( removingNode.getKey().compareTo( u.getKey() ) == 0 )
				if ( removeChild( removingNode ) ) {
					decreaseSize();
					return false;
				}
		return false;
	}
	
	/**
	 * <p>Removes a leaf or a node with one child and links the proper nodes to each other as needed.</p>   
	 * @param removingNode
	 * @see #remove(key)
	 */
	protected void splice( PairNode<K, V> removingNode ) {
		PairNode<K,V> substitudeNode, p = removingNode.getParent();
		/*
		 * In case of it has one child or it is a leaf. 
		 * If it has a one child it picks that as a substitute.
		 * In case of there is no children the substitute would be null.
		 */
		if ( removingNode.getRight() != null ) 
			substitudeNode = removingNode.getRight();
		else 
			substitudeNode = removingNode.getLeft();
		//If the root is been removing
		if ( removingNode == getRoot() ) {
			setRoot( substitudeNode );
			getRoot().setParent( null );
		} else {//If another node but root is been removing
			if ( p.getLeft() == removingNode ) 
				p.setLeft(  substitudeNode );
			else 
				p.setRight( substitudeNode ); 
		}
		if ( substitudeNode != null ) 
			substitudeNode.setParent( p );
	}
	
	/**
	 * <p>This method rotates the tree from u to the left.</p>
	 * @param u A node that needs to be rotated to the left.
	 * @param parent A parent of node u.
	 */
	protected void rotateLeft( PairNode<K, V> u ) {
		PairNode<K, V> w = u.getRight();
		w.setParent( u.getParent() );
		if ( w.getParent() != null )
			if ( w.getParent().getLeft() == u )
				w.getParent().setLeft( w );
			else
				w.getParent().setRight( w );
		u.setRight( w.getLeft() );
		if ( u.getRight() != null )
			u.getRight().setParent( u );
		w.setLeft( u );
		w.getLeft().setParent( w );
		if ( u == getRoot() )
			setRoot( w );
	}
	
	/**
	 * <p>This method rotates the tree from u to the right.</p>
	 * @param u A node that need to be rotated to the right.
	 * @param parent A parent of node u.
	 */
	protected void rotateRight( PairNode<K, V> u ) {
		PairNode<K, V> w = u.getLeft();
		w.setParent( u. getParent() );
		if ( w.getParent() != null )
			if ( w.getParent().getLeft() == u)
				w.getParent().setLeft( w );
			else 
				w.getParent().setRight( w );
		u.setLeft( w.getRight() );
		if ( u.getLeft() != null )
			u.getLeft().setParent( u );
		w.setRight( u );
		w.getRight().setParent( w );
		if ( u == getRoot() )
			setRoot( w );
	}
	
	
	/**
	 * <p>Recursively checks each node if it follows the BST rules.</p>
	 * @param root
	 * @param min_value
	 * @param max_value
	 * @return true if it is BST otherwise false.
	 * @see #isBST(PairNode)
	 */
	protected boolean isBST( PairNode<K,V> root, K min_value, K max_value ) {
		if ( root == null )
			return true;
		if ( ( min_value != null && root.getKey().compareTo( min_value ) <= 0 ) ||  ( max_value != null && root.getKey().compareTo( max_value ) >= 0 ) )
			return false;
		return isBST( root.getLeft(), min_value, root.getKey() ) 
			&& isBST( root.getRight(), root.getKey(), max_value );
	}
	
	/**
	 * Checks that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 * @see #isBSTHelper(PairNode,k,k) 
	 */
	protected boolean isBST( PairNode<K,V> root ) {
		return isBST( root, null, null );
	}
	
	/**
	 * <p> This method starts from the given r node (which by default would be the root), builds a String starting with prefixMessage </br>
	 * (which by default would be a white space) and containing all details of left and right subtrees in which all points are saved.</br>
	 * <b>This method is used by toString() method.</b><p> 
	 * @param r A starting node.
	 * @param prefixMessage A message that would be shown at the beginning of the each node.
	 * @param isTail A helper for determining if the current node would be the leaf.
	 * @return <b> String</br> The whole tree</b> holding all points.
	 * @see java.lang.StringBuilder
	 * @see java.lang.String
	 * @see #toString()
	 */
	protected String printTree( PairNode<K, V> r, String prefixMessage, boolean isTail ) {
		StringBuilder builder = new StringBuilder();
        if ( r.getParent() != null ) {
            String side = "left";
            if ( r.getKey().compareTo( r.getParent().getRight().getKey() ) == 0 )
                side = "right";
            builder.append( prefixMessage + ( isTail ? "└── " : "├── " ) + "(" + side + ") " + r.toString() + "\n" );
        }else
            builder.append( prefixMessage + ( isTail ? "└── " : "├── " ) + r.toString() + "\n" );
        List<PairNode<K, V>> children = null;
        if ( r.getLeft() != null || r.getRight() != null ) {
            children = new ArrayList<PairNode<K, V>>(2);
            if ( r.getLeft() != null )
                children.add( r.getLeft() );
            if ( r.getRight() != null )
                children.add( r.getRight() );
        }
        if ( children != null ) {
            for ( int i = 0; i < children.size() - 1; i ++ )
                builder.append( printTree( children.get(i), prefixMessage + ( isTail ? "    " : "│   " ), false ) );
            if (children.size() >= 1)
                builder.append( printTree( children.get( children.size() - 1 ), prefixMessage + ( isTail ? "    " : "│   " ), true ) );
        }
        return builder.toString();
    }

	/**
	 * <p>Return the height of the tree.</br>  
	 * Definition:</br>
	 *    The <b>depth</b> of a node is number of edges from the root to that node.</br>
	 *    The <b>height</b> of a tree is equal to the depth of the node with the greatest depth of all the nodes.</br>
	 * @return int
	 */
	protected int height() {
		return height( getRoot() );
	}
	
	/**
	 * <p>Private method for height().</p>
	 * @return int
	 */
	protected int height( PairNode<K, V> r ) {
		if ( r == null ) 
			return -1;
		return 1 + Math.max( height( r.getLeft() ), height( r.getRight() ) );
	}
	
	/**
	 * <p> Counts the number of the elements inside the tree from the given root. </p>
	 * @param root
	 * @return int
	 * @see #size()
	 * @see #getN()
	 */
	protected int countSize( PairNode<K, V> root ) {
		if ( root == null )
			return 0;
		return 1 + countSize ( root.getLeft() ) + countSize( root.getRight() );
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
	
	/**
	 * <p>This method recursively goes through the tree and visits all nodes in PRE-ORDER
	 * and returns an ArrayList of all nodes.</p>   
	 * @return ArrayList<PairNode<K, V>>
	 * @see #toCollection
	 */
	protected ArrayList<PairNode<K, V>> postOrderRecursive() {
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null )
				? null
				: postOrderRecursive( getRoot(), outputList );	
	}
	
	/**
	 * <p>A helper method for postOrderReverse().</p>
	 * @return ArrayList<PairNode<K, V>>
	 * @see #postOrderRecursive() 
	 */
	protected ArrayList<PairNode<K, V>> postOrderRecursive( PairNode<K, V> r, ArrayList<PairNode<K, V>> outputLst ) {
		if ( r == null) return null;
		postOrderRecursive( r.getLeft(), outputLst );
		postOrderRecursive( r.getRight(), outputLst );
		outputLst.add( r );
		return outputLst;
	}
	
	/**
	 * <p>This method goes through the tree and visits all nodes in traversal IN-ORDER mode
	 * and returns an ArrayList of all nodes.</p>
	 * <p>The list that would be returned is actually the sorted list of all keys.</p> 
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> inOrderTraversal() {
		if ( getRoot() == null )
			 return null;
		LinkedList<PairNode<K, V>> stack = new LinkedList<PairNode<K,V>>();
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		PairNode<K, V> u = getRoot();
		while( u != null ){
			stack.push(u);
			u = u.getLeft();
		}
		while( ! stack.isEmpty() ) {
			u = stack.pop();
			outputList.add( u );
			if ( u.getRight() != null ){
				u = u.getRight();
				while( u != null ) {
					stack.push( u );
					u = u.getLeft();
				}
			}
		}
		return outputList;
	}
	
	/**
	 * <p>Returns an ArrayList holding all the nodes from PRE-ORDER traversal.</p>
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> preOrderTraversal(){
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
	 * <p>Returns an ArrayList holding all nodes from a BFS traversal.</p>
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> BFS(){
		if ( getRoot() == null )
			 return null;
		Queue<PairNode<K, V>> Q = new LinkedList<PairNode<K,V>>();
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		Q.add( getRoot() );
		while( ! Q.isEmpty() ){
			PairNode<K, V> u = Q.remove();
			outputList.add( u );
			if ( u.getLeft() != null ) Q.add( u.getLeft() );
			if ( u.getRight() != null ) Q.add( u.getRight() );
		}
		return outputList;		
	}
	
	/**
	 * <p>Reads all elements from the given list and uses the "median of points" algorithm for adding them, i.e., it </br>
	 * would be able to create a more balanced KDTree. </p>
	 * @param list A List object.
	 * @Return <b>KDTree<T></b></br> A new KDtree object
	 * @see #readList(KDTree T, List list, int low, int high)
	 * @see java.util.Collection
	 * @see java.util.List
	 */
	protected BST<K, V> readList( Collection<? extends PairNode<K, V>> list ) {
		BST<K, V> Tree = new BST<>();
		readList( Tree, ( List<? extends PairNode<K, V>> ) list, 0, list.size() );
		return Tree;
	}
	
	/**
	 * <p>A helper method for readList( List ) method by which all elements are read and get added to the BSTree. </p> 
	 * @param list An arrayList objects holding all the elements. 
	 * @param low int variable for determining the low bound of the list.
	 * @param high int variable for determining the high bound of the list.
	 * @see #readList(Collection list)
	 */
	protected void readList( BST<K, V> Tree, List<? extends PairNode<K, V>> list, int low, int high ) {
		if ( low < high ){
			int median = ( low + high ) / 2;
			K key = list.get( median ).getKey();
			V value = list.get( median ).getValue();
			Tree.add( key, value );
			readList( Tree, list, low, median );
			readList( Tree, list, median + 1, high );
		}
	}
	
	/**
	 * <p> An Iterator designed for iterating through all elements in the tree. Gives access for iterating in BFS order. </p>
	 * For having an access in sorted order to all elements listIterator() would be the better choice which has more methods and </br>
	 * abilities on elements.</p>
	 * @see #listIterator()
	 */
	protected class BSTIterator<T extends PairNode<K, V>> implements Iterator<T> {

        /**
         * <p>Holds the last element that was returned by next()</p>
         */
		private T lastReturned = null;
		
		/**
		 * <p>Holds the next element that would be returning by next().</p>
		 */
        private T next = null;
        
        /**
         * <p> Holds the Index of the next elements.</p>
         */
        private int nextIndex = 0;
        
        /**
         * <p>A linked list holding next elements for being used by next() method.</p>
         */
        private LinkedList<T>  elements= new LinkedList<>();
        
        /**
         * <p>Constructor. Initializes the iterator from the tree.</p>
         */
        @SuppressWarnings("unchecked")
		protected BSTIterator() {
        	super();
            this.next = ( T ) getRoot();
            if ( next != null )
            	elements.push( next );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size(); 
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
        	if ( !hasNext() )
                return null;
        	lastReturned = next;
            if ( elements.size() > 0 ) {
            	next = elements.removeFirst();
                if ( next.getLeft() != null )
                	elements.push( ( T ) next.getLeft() );
                if ( next.getRight() != null )
                	elements.push( ( T ) next.getRight() );
            }
            ++ nextIndex;
            return lastReturned;
        }
        
        /**
         * <p> Returns the last element which had been returned by next(). </p>
         * @return T
         */
        public T lastReturn() {
        	return this.lastReturned;
        }
    }
	
	/************************************************************** EXTRA METHODS FOR PROGRAMMERS *************************/
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>Acts like the above method except it starts from the original root 
	 * (not the given root like the above method) and makes a deep copy of
	 * the whole original tree.</p> 
	 * @param r
	 * @return PairNode<K, V>
	 * @see #deepCopyRecursive_preOrder(PairNode)
	 */
	protected PairNode<K, V> deepCopyRecursive_preOrder() {
		PairNode<K, V> newTree = new PairNode<>();
		return ( getRoot() == null )
			? null
			: deepCopyRecursive_preOrder( getRoot(), newTree );
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>The private method for deepCopyRecursive_preOrder method which acts 
	 * recursively and returns a PairNode which is the new tree's root.</p> 
	 * @param r root
	 * @param newT newTree
	 * @return PairNode<K, V>
	 */
	protected PairNode<K, V> deepCopyRecursive_preOrder( PairNode<K, V> r, PairNode<K, V> newT ) {
		if ( r == null )
			return null;
		PairNode<K, V> u = new PairNode<K, V>( r.getKey(), r.getValue() );
		newT = u;
		if ( r.getLeft() != null )
			newT.setLeft( deepCopyRecursive_preOrder( r.getLeft(), newT.getLeft() ) );
		if ( newT.getLeft() != null )
			newT.getLeft().setParent( u );
		if ( r.getRight() != null ) newT.setRight( deepCopyRecursive_preOrder( r.getRight(), newT.getRight() ) );
		if ( newT.getRight() != null )
			newT.getRight().setParent( u );
		return newT;
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * This method goes through the tree and visits all nodes in traversal POST-ORDER mode
	 * and returns an ArrayList of all nodes.  
	 *   ****** Important *******
	 *   Notice that before applying this method you need to make a deep copy of the
	 *   tree (using deepCopy method in this class) and have another BST object ready 
	 *   for that, because this method manipulate all nodes and consequently the 
	 *   original tree will be changed. 
	 *   ************************ 
	 * @return ArrayList<PairNode<K, V>>
	 */
	public ArrayList<PairNode<K, V>> postOrderTravers() {
		if ( getRoot() == null )
			 return null;
		LinkedList<PairNode<K, V>> stack = new LinkedList<PairNode<K,V>>();
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		PairNode<K, V> u = getRoot();
		while( u != null ){
			stack.push( u );
			u = u.getLeft();
		}
		while( ! stack.isEmpty() ){
			u = stack.peek();
			if ( u.getRight() != null ){
				stack.push( u.getRight() );
				u.setRight( null );
				u = stack.peek();
				while( u.getLeft() != null ){
					u = u.getLeft();
					stack.push( u );
				}
			} else {
				u = stack.pop();
				outputList.add( u );
			}
		}
		return outputList;		
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>This method recursively goes through the tree and visits all nodes in IN-ORDER
	 * and returns an ArrayList of all nodes.</p>   
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> inOrderSortRecursive() {
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null )
				? null
				: inOrderSortRecursive( getRoot(), outputList );	
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>A private method for inOrderSortRecursive() method.
	 * @param r root</p>
	 * @param outputLst 
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> inOrderSortRecursive( PairNode<K, V> r, ArrayList<PairNode<K, V>> outputLst ) {
		if ( r == null ) 
			return null;
		inOrderSortRecursive( r.getLeft(), outputLst );
		outputLst.add( r );
		inOrderSortRecursive( r.getRight(), outputLst );
		return outputLst;
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>This Method returns an ArrayList holding all the nodes from PRE-ORDER Recursion.</p>
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> preOrderRecursive() {
		ArrayList<PairNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null) 
				? null
				: preOrderRecursive(getRoot(), outputList);
	}
	
	/**
	 * <p><b> EXTRA METHOD for programmers.</p></b>
	 * <p>The private method for preOrderReverse() which recursively visits all the nodes in PRE-ORDER.</p>
	 * @param r root
	 * @param outLst
	 * @return ArrayList<PairNode<K, V>>
	 */
	protected ArrayList<PairNode<K, V>> preOrderRecursive(PairNode<K, V> r, ArrayList<PairNode<K, V>> outLst) {
		if ( r == null )
			return null;
		outLst.add( r );
		preOrderRecursive( r.getLeft(), outLst );
		preOrderRecursive( r.getRight(), outLst );
		return outLst;
	}
}
