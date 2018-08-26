package data_structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree DS implements the Dictionary abstract data type
 * 
 * @author Meijad
 *
 * @param <K> Key generic Type
 * @param <E> Value generic type
 */
public class BST<K extends Comparable<K>,V extends Comparable<V>> extends Dictionary<K, V> {

	protected TreeNode<K, V> root;
	
	/**
	 * Constructor
	 */
	public BST(){
		super();
		root=null;
	}
	
	public BST(TreeNode<K, V> root){
		super();
		this.root=root;
		n = countSize( root );
	}
	
	/**
	 * Returns the tree's Root node.
	 * @return Root node.
	 */
	public TreeNode<K, V> getRoot(){
		return root;
	}
	
	/*
	 * Compares two keys.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k22 smaller 
	 */
	private final int compareKeys(K k1,K k2){
		return k1.compareTo(k2);
	}

	/*
	 * Finds the Node that holds the given key or a proper leaf in case 
	 * of it could not find a node with the given key in it.
	 * @param key
	 * @return
	 */
	private final TreeNode<K, V> findNode (K key){
		TreeNode<K, V> currentNode = getRoot(), prevNode = null;
		while (currentNode != null) {
			prevNode = currentNode;
		    int comp = compareKeys(key, currentNode.getKey());
		    if (comp < 0) 
		    	currentNode = currentNode.getLeft();
		    else if (comp > 0) 
		    	currentNode = currentNode.getRight();
		    else 
		    	return currentNode;
		}
		return prevNode;
	}
	
	/*
	 * Inserts the given newNode in proper position inside the tree
	 * depends on the lastFindedNode which would be the newNode's parent.
	 * 
	 * @param lastFindedNode 
	 * @param newNode 
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 */
	private final boolean addChild(TreeNode<K, V> lastFindedNode, TreeNode<K,V> newNode) {
		if (lastFindedNode == null) 
			root = newNode;              // inserting into empty tree
		else {
			int comp = compareKeys(newNode.getKey(), lastFindedNode.getKey());
		    if (comp < 0) 
		    	lastFindedNode.setLeft(newNode);
		    else if (comp > 0) 
		    	lastFindedNode.setRight(newNode);
		    else 
		    	return false;
		}
		n++;
		return true;
	}
	
	/*
	 * Finds the parent Node of the node in which the given key is saved,
	 * or a proper leaf Node in case of it could not find a node with the
	 * given key in it.
	 * @param key
	 * @return
	 */
	private final TreeNode<K, V> findParent(K key){
		TreeNode<K, V> currentNode = getRoot(), parent = null;
		while (currentNode != null) {
			int comp = compareKeys(key, currentNode.getKey());
			if (comp==0) 
				return parent;
			parent = currentNode;
			currentNode= (comp < 0)
					?currentNode.getLeft()
					:currentNode.getRight();
		}
		return parent;
	}
	
	/*
	 * Removes the removedNode based on parentsNode's position. 
	 * Links and swaps the proper nodes to each other    
	 * @param u
	 * @param prev
	 */
	private final void splice(TreeNode<K, V> removingNode, TreeNode<K, V> parentNode) {
		TreeNode<K,V> substitudeNode, p;
		
		// 1- In case of it has either one or two children or even none. 
		// If it has two children we will pick the right child as a substitute.
		// In case of there is no children substitute would be null.
		if ( removingNode.getRight() != null ) 
			substitudeNode = removingNode.getRight();
		else 
			substitudeNode = removingNode.getLeft();
		
		//If the root is been removing
		if ( removingNode == getRoot() ) {
			root = substitudeNode;
			p = null;
		} else {//If another node but root is been removing
			p = parentNode;
			if ( p.getLeft() == removingNode ) 
				p.setLeft(  substitudeNode );
			else 
				p.setRight( substitudeNode ); 
		}
		
		// In case of we have parentNode in treeNode and we need update the value of it at the end.
		// But since our node does not have the parentNode it is here just for demonstration.
		if ( substitudeNode != null ) 
			parentNode = p; // It should be like substituteNode.parentNode = p;
	}
	
	/**
	 * Insert a value/key pair into the dictionary.
	 * @param key        key to be inserted
	 * @param value      value to be inserted
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 */
	@Override
	public void insert(K key, V value) {
		TreeNode<K, V> u = findNode(key);
		if ( ! addChild( u, new TreeNode<>( key, value ) ) )
			throw new IndexOutOfBoundsException("Duplication is not allowed");
	}

	/**
	 * Remove a key from the dictionary
	 * @param key  key to remove
	 */
	@Override
	public void remove(K key) {
		if ( getRoot() == null )  // Tree is empty
			throw new IndexOutOfBoundsException("The List is empty.");
		
		/* Setting a node that needs to be removed and its parentNode 
		 * or throw an Error if there is no such a key*/
		TreeNode<K, V> parentNode = findParent(key), removingNode;
		
		if ( parentNode == null )
			parentNode = removingNode = getRoot();
		else if ( parentNode.getLeft() != null && compareKeys( key, parentNode.getLeft().getKey() ) == 0 )
			removingNode = parentNode.getLeft();
		else if ( parentNode.getRight() != null && compareKeys( key, parentNode.getRight().getKey() ) == 0 ) 
			removingNode=parentNode.getRight();
		else
			throw new IndexOutOfBoundsException("There is no such a key.");
		
		// Start removing and splicing 
		if ( removingNode.getLeft() == null || removingNode.getRight() == null ) 
			//Case 1: Either children is null.
			splice(removingNode,parentNode);
		else{
			//Case 2: Neither children is null.
			TreeNode<K, V> substitutrNode = removingNode.getRight();
			parentNode = removingNode;
			while ( substitutrNode.getLeft() != null ) {
				parentNode = substitutrNode;
				substitutrNode = substitutrNode.getLeft();
			}
			removingNode.setValue( substitutrNode.getValue() );
			removingNode.setKey( substitutrNode.getKey() );
			splice( substitutrNode, parentNode );
		}
		--n;
	}
	
	/*
	 * Recursively checks each node if it follows the BST rules.
	 * @param root
	 * @param min_value
	 * @param max_value
	 * @return true if it is BST otherwise false.
	 */
	private final boolean isBSTHelper(TreeNode<K,V> root, K min_value, K max_value) {
		if (root == null)
			return true;
		if ( (min_value != null && compareKeys( root.getKey(), min_value ) <= 0) || (max_value != null && compareKeys( root.getKey(),max_value ) >= 0) )
			return false;
		
		return isBSTHelper( root.getLeft(), min_value, root.getKey() ) 
			&& isBSTHelper( root.getRight(), root.getKey(), max_value );
	}
	
	/*
	 * Check that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 */
	private final boolean isBST(TreeNode<K,V> root) {
		return isBSTHelper( root, null, null );
	}
	
	/*
	 * 
	 * @param root
	 * @param indent
	 * @param s
	 */
	private void print_structure_helper(TreeNode<K,V> root, int indent, String s) {			
		for ( int i = 0; i < indent; i ++ )
			System.out.print("\t");
		if ( root == null ) {
			System.out.println("LEAF");
			return;
		}
		System.out.println( s + "->"+root.getKey() + ": " + root.getValue() );
		print_structure_helper( root.getLeft(), indent + 1, "left" );
		print_structure_helper( root.getRight(), indent + 1, "right" );
	}
	
	/**
	 * Search for a key value
	 * @return value associated with key.  Returns null if key not found.
	 */
	@Override
	public V find(K key) {
		TreeNode<K, V> resultNode = findNode( key );
		return ( resultNode != null && compareKeys( resultNode.getKey(), key ) == 0 )
				? resultNode.getValue()
				: null;
	}

	/**
	 * Check to make sure the implemented structure correct.
	 * (For debugging.)
	 * @return boolean
	 */
	@Override
	public boolean check_structure() {
		return isBST( root );
	}

	/**
	 * Print out the structure in whatever way makes snese.
	 * (For debugging.)
	 */
	@Override
	public void print_structure() {
		print_structure_helper( root, 0,"root" );
		
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K min() {
		TreeNode<K, V> currentNode = getRoot();
		if ( isEmpty() ) 
			return null;
		while ( currentNode.getLeft() != null ) 
			currentNode = currentNode.getLeft();
		return currentNode.getKey();
	}
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K max() {
		TreeNode<K, V> currentNode = getRoot();
		if ( isEmpty() ) 
			return null;
		while ( currentNode.getRight() != null ) 
			currentNode = currentNode.getRight();
		return currentNode.getKey();
	}
	
	/**
	 * Return the height of the tree.  
	 * Definition:
	 *    The *depth* of a node is number of edges from the root to that node.
	 *    The *height* of a tree is equal to the depth of the node with the
	 *        greatest depth of all the nodes.
	 * @return int
	 */
	public int height() {
		return heightHelper( getRoot() );
	}
	
	/*
	 * Private method for height()
	 * @return int
	 */
	private int heightHelper( TreeNode<K, V> r ){
		if ( r == null ) 
			return -1;
		return 1 + Math.max( heightHelper( r.getLeft() ), heightHelper( r.getRight() ) );
	}
	
	public int countSize( TreeNode<K, V> root ){
		if ( root == null )
			return 0;
		return 1 + countSize ( root.getLeft() ) + countSize( root.getRight() );
	}
	
	/**
	 * This method recursively goes through the tree and visits all nodes in PRE-ORDER
	 * and returns an Arraylist of all nodes.   
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> postOrderRecursive(){
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null )
				? null
				: postOrderRecursiveHelper( getRoot(), outputList );	
	}
	
	/*
	 * The private method for postOrderReverse().
	 * @return ArrayList<TreeNode<K, V>>  
	 */
	private final ArrayList<TreeNode<K, V>> postOrderRecursiveHelper(TreeNode<K, V> r, ArrayList<TreeNode<K, V>> outputLst){
		if ( r == null) return null;
		postOrderRecursiveHelper( r.getLeft(), outputLst );
		postOrderRecursiveHelper( r.getRight(), outputLst );
		outputLst.add( r );
		return outputLst;
	}
	
	/**
	 * This method goes through the tree and visits all nodes in traversal POST-ORDER mode
	 * and returns an Arraylist of all nodes.  
	 *   ****** Important *******
	 *   Notice that before applying this method you need to make a deep copy of the
	 *   tree (using deepcopy method in this class) and have another BST object ready 
	 *   for that, because this method manipulate all nodes and consequently the 
	 *   original tree will be changed. 
	 *   ************************ 
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> postOrderTravers(){
		if ( getRoot() == null )
			 return null;
		LinkedList<TreeNode<K, V>> stack = new LinkedList<TreeNode<K,V>>();
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		TreeNode<K, V> u = getRoot();
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
	 * Return TreeNode holding a new tree's root which is a deep copy of the 
	 * original tree 
	 * @param root
	 * @return
	 */
	public TreeNode<K, V> deepCopy( TreeNode<K, V> root ){
		return ( root == null )
				? null
				: new TreeNode<K, V>( root.getKey(), root.getValue(), deepCopy( root.getLeft() ), deepCopy( root.getRight() ) );
	}
	/**
	 * Makes a deep copy of the given tree with root r and returns the 
	 * root of the new tree.
	 * This method recursively visits all nodes in PRE-ORDER.
	 * @param r
	 * @return TreeNode<K, V>
	 */
	public TreeNode<K, V> deepCopyRecursive_preOrder(TreeNode<K, V> r){
		TreeNode<K, V> newTree=null;
		return ( r == null )
			? null
			: deepCopyRecursiveHelper( r, newTree );
	}
	
	/**
	 * Acts like the above method except it starts from the original root 
	 * (not the given root like the above method) and makes a deep copy of
	 * the whole original tree. 
	 * @param r
	 * @return TreeNode<K, V>
	 */
	public TreeNode<K, V> deepCopyRecursive_preOrder(){
		TreeNode<K, V> newTree=null;
		return ( getRoot() == null )
			? null
			: deepCopyRecursiveHelper( getRoot(), newTree );
	}
	
	/*
	 * The private method for deepCopyRecursive_preOrder method which acts 
	 * recursively and returns a TreeNode which is the new tree's root. 
	 * @param r root
	 * @param newT newTree
	 * @return TreeNode<K, V>
	 */
	private final TreeNode<K, V> deepCopyRecursiveHelper(TreeNode<K, V> r, TreeNode<K, V> newT){
		if ( r == null )
			return null;
		TreeNode<K, V> u=new TreeNode<K, V>( r.getKey(), r.getValue() );
		newT=u;
		if ( r.getLeft() != null ) newT.setLeft( deepCopyRecursiveHelper( r.getLeft(),newT ) );
		if ( r.getRight() != null ) newT.setRight( deepCopyRecursiveHelper( r.getRight(),newT ) );
		return newT;
	}
	
	/**
	 * This method recursively goes through the tree and visits all nodes in IN-ORDER
	 * and returns an Arraylist of all nodes.   
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> inOrderSortRecursive(){
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null )
				? null
				: inOrderSortRecursiveHelper( getRoot(), outputList );	
	}
	
	/*
	 * A private method for inOrderSortRecursive() method.
	 * @param r root
	 * @param outputLst 
	 * @return ArrayList<TreeNode<K, V>>
	 */
	private final ArrayList<TreeNode<K, V>> inOrderSortRecursiveHelper( TreeNode<K, V> r, ArrayList<TreeNode<K, V>> outputLst ){
		if ( r == null ) 
			return null;
		inOrderSortRecursiveHelper( r.getLeft(), outputLst );
		outputLst.add( r );
		inOrderSortRecursiveHelper( r.getRight(), outputLst );
		return outputLst;
	}
	
	/**
	 * This method goes through the tree and visits all nodes in traversal IN-ORDER mode
	 * and returns an Arraylist of all nodes.
	 * The list that would be returned is actually the sorted list of all keys. 
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> inOrderSortTravers(){
		if ( getRoot() == null )
			 return null;
		LinkedList<TreeNode<K, V>> stack = new LinkedList<TreeNode<K,V>>();
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		TreeNode<K, V> u = getRoot();
		while( u != null ){
			stack.push(u);
			u = u.getLeft();
		}
		while( ! stack.isEmpty() ){
			u = stack.pop();
			outputList.add( u );
			if ( u.getRight() != null ){
				u = u.getRight();
				while( u != null ){
					stack.push( u );
					u = u.getLeft();
				}
			}
		}
		return outputList;
		
	}
	
	/**
	 * Returns an ArrayList holding all the nodes from PRE-ORDER traversal.
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> preOrderTravers(){
		if ( getRoot() == null )
			 return null;
		LinkedList<TreeNode<K, V>> stack = new LinkedList<TreeNode<K,V>>();
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		stack.push( getRoot() );
		while( ! stack.isEmpty() ){
			TreeNode<K, V> u = stack.pop();
			outputList.add( u );
			if ( u.getRight() != null ) stack.push( u.getRight() );
			if ( u.getLeft() != null ) stack.push( u.getLeft() );
		}
		return outputList;
	}
	
	/**
	 * This Method returns an ArrayList holding all the nodes from PRE-ORDER Recursion.
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> preOrderRecursive(){
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		return ( getRoot() == null) 
				? null
				: preOrderRecursiveHelper(getRoot(), outputList);
	}
	
	/**
	 * The private method for preOrderReverse() which recursively visits all the nodes in PRE-ORDER.
	 * @param r root
	 * @param outLst
	 * @return ArrayList<TreeNode<K, V>>
	 */
	private final ArrayList<TreeNode<K, V>> preOrderRecursiveHelper(TreeNode<K, V> r, ArrayList<TreeNode<K, V>> outLst){
		if ( r == null )
			return null;
		outLst.add( r );
		preOrderRecursiveHelper( r.getLeft(), outLst );
		preOrderRecursiveHelper( r.getRight(), outLst );
		return outLst;
	}
	
	/**
	 * Returns an ArrayList holding all nodes from a BFS traversal.
	 * @return ArrayList<TreeNode<K, V>>
	 */
	public ArrayList<TreeNode<K, V>> BFS(){
		if ( getRoot() == null )
			 return null;
		Queue<TreeNode<K, V>> Q = new LinkedList<TreeNode<K,V>>();
		ArrayList<TreeNode<K, V>> outputList = new ArrayList<>();
		Q.add( getRoot() );
		while( ! Q.isEmpty() ){
			TreeNode<K, V> u = Q.remove();
			outputList.add( u );
			if ( u.getLeft() != null ) Q.add( u.getLeft() );
			if ( u.getRight() != null ) Q.add( u.getRight() );
		}
		return outputList;		
	}
}
