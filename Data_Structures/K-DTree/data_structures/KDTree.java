/*
 * Copyright (c) 2017, 2018.
 * 
 * FOR EDUCATIONAL PURPOSE ONLY
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>A <b> k-d tree (short for k-dimensional tree)</b> is a space-partitioning data structure for organizing points </br>
 * in a k-dimensional space. k-d trees are a useful data structure for several applications, such as searches </br>
 * involving a multidimensional search key (e.g. range searches and nearest neighbor searches). k-d trees </br>
 * are a special case of binary space partitioning trees. </br>
 * For <i><b>programmer</b></i> all non public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> amjadm@miamioh.edu
 * @param <T> A class of Coordinate2D object.
 * @see <a href="https://en.wikipedia.org/wiki/K-d_tree">K-D Tree (Wikipedia)</a
 * @see java.lang.Iterable
 * @see java.lang.Comparable
 * @see data_structures.KDTNode
 * @see data_structures.Coordinate2D
 */
public class KDTree<T extends Coordinate2D> implements Iterable<T> , Comparable<KDTree<T>> {
	/**
	 * <p>Holds the root of the tree. By default it holds null meaning the tree is empty.</p>
	 */
	private KDTNode root = null;
	/**
	 * <p>Holds the number of the existent points inside the tree. If it is zero meaning the tree is empty.</p>
	 */
	private int n = 0; 
	
	/**
	 * <p>The <b>Default Constructor</b>. Sets the root to null also n to 0 meaning the tree is empty.</p>
	 */
	public KDTree() {
		super();
	}
	
	/**
	 * <p>The <b>Copy Constructor</b> which sets the tree with newKDT.</p>
	 * @param <T> A class of Coordinate2D object.
	 * @param <KDTree> A class of KDTree object.
	 * @param newKDT A KDTree node  
	 */
	public KDTree( KDTree<Coordinate2D> newKDT ) {
		super();
		setRoot( newKDT.getRoot() );
		setN( newKDT.size() );
	}
	
	/**
	 * <p><b>The Move Constructor </b> that makes a KDTree from the given list without </br>
	 * any duplications. The given list <i><b>must be sorted</i></b> since it uses the "median </br>
	 *  of points" algorithm for adding points, i.e., it would be able to create a </br> 
	 *  more balanced KDTree. This constructor takes O(N) for adding points into </br>
	 *  the tree.</P> 
	 * @param <T> A class of Coordinate2D object.
	 * @param <Collection> A java.awt.List class.
	 * @param list A list of (x,y) points.
	 * @see	{@link java.util.Collection}
	 * @see {@link java.util.Collections#sort}
	 */
	public KDTree( Collection<Coordinate2D> list ) {
		super();
		if ( list == null || list.size() == 0 )
			setRoot( null );
		else {
			KDTree<Coordinate2D> T = readList( list );
			setRoot( T.getRoot() );
			setN( T.size() );
		}
	}
	
	/**
	 * @see #root
	 */
	protected final KDTNode getRoot() { return this.root; }
	
	/**
	 * @see #root
	 */
	protected final void setRoot( KDTNode root ) { this.root = root; }
	
	/**
	 * <p> Returns the number of the existent points. </p>
	 * @return <b> int</b> </br>
	 * 		The size of the KDTree
	 * @see #n
	 */
	public final int size() { return this.n; }
	
	/**
	 * @see #n
	 */
	protected final void setN( int n ) { this.n = n; }
	
	/**
	 * <p> Increases one to the size of the tree. It would happen in this class when a new point has been added.</p>
	 * @see #n
	 */
	protected final void increaseSize() { ++ this.n ; }
	
	/**
	 * <p> Decreases the size of the tree by one. It would happen in this class when one point has been removed.</p>
	 * @see #n
	 */
	protected final void decreaseSize() { -- this.n; }
	
	/**
	 * <p> The <b> add </b> method adds a new point(x,y) into the KDTree. First, it looks for a proper place</br>
	 * for the given new point then, it adds it up into the KDTree. If it finds <b>duplication</b>, it would</br>
	 * return false otherwise gets the job done, increases the size and returns true.</p>
	 * @param p Coordinate2D object holding a coordinate x and y. 
	 * @return <b> boolean </br> true</b> if it gets completed successfully.</br> <b>false</b> if it finds <b> Duplication</b>.
	 * @see #findNode(KDTNode newNode)
	 * @see #addChild(KDTNode parentNode, KDTNode newNode)
	 */
	public boolean add( Coordinate2D p ) {
		KDTNode newNode = new KDTNode( p );
		KDTNode u = findNode( newNode );
		return addChild ( u, newNode );
	}
	
	/**
	 * @param x A double value for x coordinate.
	 * @param y A double value for y coordinate.
	 * @see #add( Coordinate2D p )
	 */
	public boolean add( double x, double y ) {
		return add( new Coordinate2D( x, y ) );
	}
	
	/**
	 * <p> The <b> remove </b> method removes a point from the KDTree. First, it looks for a replacement which would be a leaf with </br>
	 * the same Axis property. If it has been successful it would swap the points and splices the founded leaf. The worst</br>
	 * case is when there is no replacement founded for the node. Then it would remove the node and recreates that part</br>
	 * of the tree again, which is a very slow approach. </br>
	 * Check the comments inside the code for more clarification.</p>
	 * @param x A double value for x coordinate
	 * @param y A double value for y coordinate.
	 * @return <b> boolean </br> true</b> if it gets completed successfully.</br> <b>false</b> Otherwise.
	 * @see #findNode(KDTNode newNode)
	 * @see #splice(KDTNode removingNode)
	 * @see #findReplacement(KDTNode r)
	 * @see #recreatingTree(KDTNode r)
	 */
	public boolean remove( Coordinate2D p ) {
		if ( isEmpty() )
			return false;
		/* (Phase 1): Finding a node that holds the given p. */
		KDTNode removingNode = findNode( new KDTNode( p ) );
		if ( removingNode == null || !removingNode.getPoint().equals( p ) )// Could not find the point in tree
			return false;
		/* (Phase 2): Start removing which includes 4 basic levels:
		 * 							1- If the Node is a leaf: 
		 * 											Just removes the leaf (splice it).
		 * 							2- If the Node is not a leaf and there is a replacement for that:
		 * 											Looks for a replacement which first, would be a leaf, and second
		 * 											it would have the same Axis as the given p. Then swaps the 
		 * 											points and then removes the leaf (splice it).
		 * 							3- If the Node neither is not a leaf or there is no any replacement for that:
		 * 											First, retrieves all children of the node. Second, from that, it
		 * 											recreates a new balanced tree, and then it attaches the new tree
		 * 											into the tree instead of the old part that it has been recreated.
		 * 							4- No matter which 3 levels of the above has implemented, at the end it decreases
		 * 								the size and return the true. 
		 */
		if ( removingNode.getLesser() == null && removingNode.getGreater() == null ) // Implementing level 1 in O(1).
			splice( removingNode );
		else{ // Implementing level 2. O(lgN) for finding a replacement and O(1) for splicing.
			KDTNode replacementNode = findReplacement( removingNode ); 
			if ( replacementNode != null ){
				removingNode.setPoint( replacementNode.getPoint() );
				splice( replacementNode );
			}else{// Implementing level 3 in linear Time. ( this level takes a lot of work so it would be slow).
				KDTNode parent = removingNode.getParent();
				KDTree<Coordinate2D> newTree = recreatingTree( removingNode ); // O(N)
				if ( removingNode == getRoot() ) // Replacing a newTree.
					setRoot( newTree.getRoot() );
				else {
					if ( parent.getLesser() == removingNode )
						parent.setLesser( newTree.getRoot() );
					else
						parent.setGreater( newTree.getRoot() );
					newTree.getRoot().setParent( parent );
				}
			}
		}
		decreaseSize();// Implementing level 4.
		return true;
	}
	
	/**
	 * @see #remove( Coordinate2D point )
	 * @param x A double value for x coordinate.
	 * @param y A double value for Y coordinate.
	 */
	public boolean remove( double x, double y ) {
		return remove( new Coordinate2D( x, y ) ); 
	}
	
	/**
	 * <p>Return true if and only if this KDTree contains the given point otherwise returns false. </br>
	 * It also returns false if the KDTree is empty.</p>
	 * @param point A point holds x and y.
	 * @return <b>boolean </br> true </b> if the point will be founded otherwise <b>false</b>.
	 * @see #findNode(KDTNode newNode)
	 */
	public boolean contains( Coordinate2D point ) {
		KDTNode u = new KDTNode( point );
		KDTNode foundedNode = findNode( u );
		return ( foundedNode != null && foundedNode.getPoint().equals( point ) )
				? true
				: false;
	}
	
	/**
	 * @see #contains( Coordinate2D point )
	 * @param x A double value for x coordinate.
	 * @param y A double value for y coordinate.
	 */
	public boolean contains( double x, double y ) {
		return contains(  new Coordinate2D( x, y ) );
	}
	
	/**
	 * <p> The <b> findNearest</b> method aims to find a point in the tree that is nearest to a given input point.</p>
	 * It returns a point if it would be successful, otherwise returns null.
	 * @param point Coordinate2D object that this method looks for its nearest node.
	 * @return <b> Coordinate2D</b></br> A point which has the closest distance to the given point or null.
	 * @see #findNearest( KDTNode r, Coordinate2D point )
	 */
	public Coordinate2D findNearest( Coordinate2D point ) {
		if ( isEmpty() || point == null )
			return null;
		return findNearest( getRoot(), point );
	}
	
	/**
	 * @see #findNearest( Coordinate2D point )
	 * @param x A double value for x coordinate.
	 * @param y A double value for y coordinate.
	 */
	public Coordinate2D findNearest( double x, double y ) {
		return findNearest( new Coordinate2D( x, y ) );
	}
	
	/**
	 * <p> Checks if there is any points inside the tree. It returns true if there is any otherwise returns false. </p>
	 * @return <b>boolean</br> true </b> if the tree is empty otherwise <b> false</b>.
	 * @see #size()
	 */
	public boolean isEmpty() {
		return (  size() == 0 )
				? true
				: false;
	}
	
	/**
	 * <p> Calculates the height of the tree. It returns -1 in case of the tree is empty.</br>
	 * <b>This method is used by hashCode() method.</b></p>
	 * @return <b> int </b></br> The height of the tree.
	 * @see #height(KDTNode r)
	 * @see #hashCode()
	 */
	protected final int height() {
		return height( getRoot() );
	}
	
	/**
	 * <p> A Helper method for the height() method. This method starts from given root r and recursively </br>
	 * counts the largest path from the r to the leaf (which determines the height of the tree). </p> 
	 * @param r A root of a subtree.
	 * @return <b> int </br></b> The height of the tree. Returns -1 if the tree is empty.
	 * @see #height()
	 * @see java.lang.Math#max(int a, int b)
	 * @see #hashCode()
	 */
	protected int height( KDTNode r ) {
		return ( r == null )
				? -1
				: 1 + Math.max( height( r.getLesser() ),  height( r.getGreater() ) );
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31 * ( getRoot().hashCode() + size() + height() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		Collection<T> BFSList= BFSTravers( getRoot() ); // It takes O(N) to all points. 
		return BFSList.iterator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( KDTree<T> other ) {
		if ( ( this == null ) || ( other == null )  || !( other instanceof KDTree<?> ) )
			throw new IllegalArgumentException( "**The given points can not be null!**" );
		int h1 = height() , h2 = other.height(), 
			r1 = size() / h1, r2 = other.size() / h2; // Calculating ( size / height ) for each KD-tree.
		return ( r1 > r2 ) ? 1 : ( r1 < r2 ) ? -1 : 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return ( isEmpty() )
				? "null"
				: printTreeHelper( getRoot(), " ", true );
	}
	
	/**
	 * <p>Looks for newNode through the KDTree and returns either the same node </br>
	 * as newNode (which we can then interpret it as a Duplication) or a suitable </br>
	 * parentNode for adding a newNode to that (which is useful for add method).</p>
	 * @param <KDTNode> A class of KDTNode object.
	 * @param newNode A new KDTNode that the method looks for.
	 * @return <b>KDTNode</b> Either the same node as newNode or a proper place for </br>
	 * 		adding a newNode under it.
	 */
	protected KDTNode findNode( KDTNode newNode ) {
		KDTNode currentNode = getRoot(), parentNode = null;
		while ( currentNode != null ) {
			parentNode = currentNode;
			/*
			 *  It does the comparison based on currentNode's Axis,
			 *  which would be either X_Comparitor or Y_Comparitor.
			 *  
			 *  For more details check the KDTNode.java: 
			 *  	@Override 
			 *  	public int compareTo( KDTNode o ) {... 
			 */
			int comp = currentNode.compareTo( newNode );
			if ( comp > 0 )
				currentNode = currentNode.getLesser();
			else if ( comp < 0 )
				currentNode = currentNode.getGreater();
			else {
				/*
				 * They might have only the same either X or Y.
				 * But, we need to look into their both X and Y checking 
				 * for real duplication. also finding a proper side 
				 * for adding a newNode.
				 * 
				 * Coordinate2D.Y.Comparator and X.Comparator compares both points' X and Y.
				 * For more details check the Coordinate2D.java:
				 * 		public static Comparator<Coordinate2D> X_Comparator = new Comparator<Coordinate2D>() {...
				 * 		public static Comparator<Coordinate2D> Y_Comparator = new Comparator<Coordinate2D>() {...
				 */
				int comp2 = ( currentNode.getAxis() == Axis.XAxis )
						? Coordinate2D.Y_Comparator.compare( currentNode.getPoint() , newNode.getPoint() )
						: Coordinate2D.X_Comparator.compare( currentNode.getPoint() , newNode.getPoint() );
				if ( comp2 == 0 )
						return currentNode; // There is Duplication.
				else // In case of the same value in current Axis it would choose the greater child.
					currentNode = currentNode.getGreater();
			}
			
		}
	return parentNode;
	}
	
	/**
	 * <p>Adds the given newNode in proper position under the given parentNode and adds up the size of </br>
	 * the tree.</p>
	 * @param <KDTNode>	A lass of KDTNode object.
	 * @param parentNode A proper node for adding the given newNode under that.
	 * @param newNode A new node for adding into the KDTree.
	 * @return <b> boolean </br> true</b> if it gets completed successfully.</br> <b>false</b> if it finds <b> Duplication</b>.
	 * @see data_structures.Coordinate2D#X_Comparator
	 * @see data_structures.Coordinate2D#Y_Comparator
	 */
	protected boolean addChild( KDTNode parentNode, KDTNode newNode ) {
		if ( parentNode == null ){// Inserting into an empty tree
			setRoot( newNode );
			getRoot().setParent( null );
		} else { // Finding either a left or right side for newNode to be added under parentNode.
			int comp = parentNode.compareTo( newNode );
			newNode.setAxis( parentNode.getAxis().next() );// Gives the other Axis to the newNode.
			if ( comp < 0 )
				parentNode.setGreater( newNode );
			else if ( comp > 0 )
				parentNode.setLesser( newNode );
			else{
				/* They might have only the same either X or Y.
				 * But, we need to look into their both X and Y checking 
				 * for real duplication, also finding a proper side 
				 * for adding a newNode.
				 * So far, it already compares two nodes' Axis based on the 
				 * parentNode's Axis. So, it needs to only compare their
				 * other Axis.
				 */
				int comp2 = ( parentNode.getAxis() == Axis.XAxis )  
						? Coordinate2D.Y_Comparator.compare( parentNode.getPoint() , newNode.getPoint() )
						: Coordinate2D.X_Comparator.compare( parentNode.getPoint() , newNode.getPoint() );
				if ( comp2 == 0 )
						return false; // Duplication exists
				else // In case of the same value in current Axis, it would be added as the greater child.
					parentNode.setGreater( newNode );
			}		
			newNode.setParent( parentNode );
		}
		increaseSize();
		return true;
	}
	
	/**
	 * <p>Removes the given node and fill it with null.</p>   
	 * @param removingNode
	 * @return true or false based on it would be successful or not.
	 * @see #remove( Coordinate2D point ) 
	 */
	protected boolean splice( KDTNode removingNode ) {
		KDTNode parent = removingNode.getParent();
		if ( parent.getLesser() == removingNode )
			parent.setLesser( null );
		else
			parent.setGreater( null );
		return true;
	}
	
	/**
	 * <p> Looks for the proper replacements which would be leaf and has the same Axis as r.</p>
	 * @param r KDTNode object that needs a replacement. 
	 * @return <b>KDTNode</b> if it finds a replacement, otherwise returns <b> null </b>.
	 * @see #remove( Coordinate2D point ) 
	 */
	protected KDTNode findReplacement ( KDTNode r ) {
		KDTNode u = r.getGreater();
		if ( u == null )
			return null;
		while ( u.getLesser() != null )
			u = u.getLesser();
		
		return (  r.getAxis() == u.getAxis() && u.getGreater() == null )
				? u
				: null;
	}
	
	/**
	 * <p> The <b> recreatingTree </b> method makes a new tree from the given r node. It retrieves all data from the tree to in-order traversal</br>
	 * which returns a sorted list in linear time, then it removes the r and it makes a new balanced tree from the reminded list and </br>
	 * returns it.</p> 
	 * @param r The root of the old tree. 
	 * @return <b>KDTree</b></br> A new tree without given r.
	 * @see #remove( Coordinate2D point )
	 * @see #inOrderTravers(KDTNode root)
	 * @see #InsertionSort(List points)
	 * @see #readList(KDTree T, List list, int low, int high)
	 */
	protected KDTree<Coordinate2D> recreatingTree( KDTNode r ) {
		List<Coordinate2D> tempList = inOrderTravers( r ); // Retrieving all points as a list, O(N).
		tempList.remove( r.getPoint() ); // Removing the node, O(N).
		/*
		 * Some comments for what have implemented so far:
		 * it retrieved all points from the tree and removed the removing point from it.Note that,
		 * the tempList was already sorted because it got all points by in-order traversal. 
		 * The following codes use the median algorithm to make a balanced tree.
		 * Before letting the readList method does the job, the new root (as a replacement for the
		 * removingNode which was r) needs to be set with the same Axis as r (or removingNode) had.
		 * Then, the readList method would make the rest of the tree based on the root's Axis.
		 */
		int median = tempList.size() / 2;
		KDTNode newRoot = new KDTNode( tempList.get( median ) );
		if  ( r.getAxis() != newRoot.getAxis() ) // Setting the newRoot Axis based on r's Axis.
			newRoot.setAxis( newRoot.getAxis().next() );
		/*
		 * Now that the root's Axis is set, it would continue to make a new tree from the tempList.
		 */
		KDTree<Coordinate2D> newTree = new KDTree<>( );
		newTree.setRoot( newRoot ); // Put the node inside the newTree and increase the size.
		newTree.increaseSize();
		readList( newTree, tempList, 0, tempList.size() ); // Completing the newTree and creating a balanced tree from the list, O(N).
		return newTree; 
	}
	
	/**
	 * <p> A helper method for finding a nearest node by its distance to the given point.</br>
	 * This method recursively checks proper nodes and avoid checking unnecessary nodes.</p>
	 * @param r KDTNode object which is a starting node for the search.
	 * @param point Coordinate2D object that this method looks for its nearest node.
	 * @return <b> Coordinate2D</b></br> A point which has the closest distance to the given point.
	 * @see #findNearest( Coordinate2D point )
	 * @see data_structures.Coordinate2D#GeographicalDistance(Coordinate2D p1, Coordinate2D p2)
	 * @see #pair()
	 */
	protected Coordinate2D findNearest( KDTNode r, Coordinate2D point ) {
		KDTNode targetNode = new KDTNode( point );
		Pair output = new Pair( null , Double.MAX_VALUE ); // This object holds the Best Node and its Distance to the given point.
		findNearest( r, targetNode, output );
		return output.bestCurrent.getPoint();
	}
	
	/**
	 * <p> Recursive method for finding the nearest neighbor.</p>
	 * @see #findNearest( KDTNode r, Coordinate2D point )
	 * @param currentNode A KDTNode.
	 * @param targetNode A KDTNode object that the method is looking for its closest node.
	 * @param output A class of Pair object which holds the best Node and the it's distance to the target node.
	 */
	protected void findNearest( KDTNode currentNode, KDTNode targetNode, Pair output ) {
		if ( currentNode == null )
			return;							
		
		// (Phase 1: continue to get to the leaf )
		KDTNode parentNode = currentNode;
		int comp = currentNode.compareTo( targetNode );
		if ( comp > 0 )
			currentNode = currentNode.getLesser();
		else if ( comp < 0 )
				currentNode = currentNode.getGreater();
		else {
			int comp2 = ( currentNode.getAxis() == Axis.XAxis )
					? Coordinate2D.Y_Comparator.compare( currentNode.getPoint() , targetNode.getPoint() )
					: Coordinate2D.X_Comparator.compare( currentNode.getPoint() , targetNode.getPoint() );
			if ( comp2 == 0 ) {
				output.bestCurrent = currentNode; // The currentNode is the complete Match with the target point.
				return;
			} else 
				currentNode = currentNode.getGreater();
		}
		findNearest( currentNode, targetNode, output );
		// ( Phase 2: Now it got to the leaf and it should start measuring the distance from the leaf to the targetNode.
		double distance = parentNode.getPoint().GeographicalDistance( targetNode.getPoint() );
		if ( distance < output.bestDistance ) {// Every time it finds the a closer node, it would save it inside the output object.
			output.bestDistance = distance;
			output.bestCurrent = parentNode;
		}
		// ( Phase 3: It should check the sibling node in case of if any closer node might exist in the other side of the tree. 
		KDTNode otherNode = ( parentNode.getLesser() == currentNode ) ? parentNode.getGreater(): parentNode.getLesser();
		if ( otherNode != null) {
			double diff = parentNode.diff( targetNode );
			if ( diff <= output.bestDistance )// Based on the active Axis, it checks if the other node has a closer axis to the targetNode.
				findNearest( otherNode, targetNode, output ); // If there is a chance it would check the sibling as well.
		}	
	}
	
	/**
	 * <p> This class is a private class for the use of the findNearest method.</br>
	 * This class holds the bestNode and its distance as pair.</p>
	 * @see #findNearest(KDTNode currentNode, KDTNode targetNode, Pair output)
	 */
	private class Pair{
		KDTNode bestCurrent = null;
		double bestDistance = Double.MAX_VALUE;
		Pair( KDTNode bestCurrent, double bestDistance ) {
			this.bestCurrent = bestCurrent;
			this.bestDistance = bestDistance;
		}
	}
	
	/**
	 * <p>Reads all points from the given list and uses the "median of points" algorithm for adding points, i.e., it </br>
	 * would be able to create a more balanced KDTree. </p>
	 * @param list A List object.
	 * @Return <b>KDTree<T></b></br> A new KDtree object
	 * @see #readList(KDTree T, List list, int low, int high)
	 * @see java.util.Collection
	 * @see java.util.List
	 */
	protected KDTree<Coordinate2D> readList( Collection<Coordinate2D> list ) {
		KDTree<Coordinate2D> Tree = new KDTree<>();
		readList( Tree, (List<Coordinate2D>) list, 0, list.size() );
		return Tree;
	}
	
	/**
	 * <p>A helper method for readList( List ) method by which all points are read and get added to the KDTree. </p> 
	 * @param T A KDTree object.
	 * @param list An arrayList objects holding all the points. 
	 * @param low int variable for determining the low bound of the list.
	 * @param high int variable for determining the high bound of the list.
	 * @see #readList(Collection list)
	 */
	protected void readList( KDTree<Coordinate2D> Tree, List<Coordinate2D> list, int low, int high ) {
		if ( low < high ){
			int median = ( low + high ) / 2;
			double x = list.get( median ).getX();
			double y = list.get( median ).getY();
			Tree.add( x, y );
			readList( Tree, list, low, median );
			readList( Tree, list, median + 1, high );
		}
	}
	
	/**
	 * <p>Returns an List holding all points from a BFS traversal. It takes O(N) time to retrieve all points.</br>
	 * <b>This method is used by Iterator() method for making an Iterable list from all points in the tree.</b></p>
	 * @param <T> A class of Coordinate2D object.
	 * @return <b>List<T></b></br> A list from All coordinates.
	 * @see java.util.Queue
	 * @see java.util.LinkedList
	 * @see #iterator()
	 */
	@SuppressWarnings( "unchecked" )
	protected List<T> BFSTravers( KDTNode root ) {
		if ( root == null )
			 return null;
		Queue<KDTNode> Q = new LinkedList<>();
		List<Coordinate2D> outputList = new ArrayList<>();
		Q.add( root );
		while( ! Q.isEmpty() ) {
			KDTNode u = Q.remove();
			outputList.add( u.getPoint() );
			if ( u.getLesser() != null ) Q.add( u.getLesser() );
			if ( u.getGreater() != null ) Q.add( u.getGreater() );
		}
		return (List<T>) outputList;
	}
	
	/**
	 * <p>Returns an List holding all points from a in-order traversal. It takes O(N) time to retrieve all points.</br>
	 * <b>This method is used by remove() method and recreating a new tree.</b></p>
	 * @param 
	 * @param <T> A class of Coordinate2D object.
	 * @return <b>List<T></b></br> A list from All coordinates.
	 * @see #add( Coordinate2D point )
	 * @see java.util.LinkedList
	 * @see java.util.ArrayList
	 * @see java.util.List
	 * @see #remove(Coordinate2D point)
	 */
	protected List<Coordinate2D> inOrderTravers( KDTNode root ) {
		if ( root == null )
			 return null;
		LinkedList<KDTNode> stack = new LinkedList<>();
		List<Coordinate2D> outputList = new ArrayList<>();
		KDTNode u = root;
		while( u != null ) {
			stack.push( u );
			u = u.getLesser();
		}
		while( ! stack.isEmpty() ) {
			u = stack.pop();
			outputList.add( u.getPoint() );
			if ( u.getGreater() != null ) {
				u = u.getGreater();
				while( u != null ) {
					stack.push( u );
					u = u.getLesser();
				}
			}
		}
		return outputList;
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
	protected String printTreeHelper(KDTNode r, String prefixMessage, boolean isTail) {
        StringBuilder builder = new StringBuilder();
        if ( r.getParent() != null ) {
            String side = "left";
            if ( r.getParent().getGreater() != null && r.getParent().getGreater().getPoint().equals( r.getPoint() ) )
                side = "right";
            builder.append( prefixMessage + ( isTail ? "└── " : "├── " ) + "[" + side + "] " + r.getAxis() + " "
                    + r.getPoint().toString() + "\n" );
        } else
            builder.append( prefixMessage + ( isTail ? "└── " : "├── " ) + " ROOT " + r.getAxis() + " " + r.getPoint().toString() + "\n" );
        List<KDTNode> children = null;
        if ( r.getLesser() != null || r.getGreater() != null ) {
            children = new ArrayList<KDTNode>(2);
            if ( r.getLesser() != null )
                children.add( r.getLesser() );
            if ( r.getGreater() != null )
                children.add( r.getGreater() );
        }
        if ( children != null ) {
            for ( int i = 0; i < children.size() - 1; i++ )
                builder.append( printTreeHelper( children.get( i ), prefixMessage + ( isTail ? "    " : "│   " ), false ) );
            if ( children.size() >= 1 )
                builder.append( printTreeHelper( children.get( children.size() - 1 ), prefixMessage + ( isTail ? "    " : "│   " ), true ) );
        }
        return builder.toString();
    }
	
	/**
	 * <p> A helper method just for programmers. It checks all children to theirs parent and returns true </br>
	 * if all children are in a right spot and if the tree follows the BSTree rules based on an active Axis</br></p> 
	 * @return
	 */
	public boolean checkChildren( ) {
		return checkChildren( getRoot() );
	}
	
	/**
	 * @see #checkChildren( )
	 * @param r
	 * @return
	 */
	public boolean checkChildren( KDTNode r ) {
		if ( r == null )
			return true;
		if ( r.getLesser() != null && r.compareTo( r.getLesser() ) <= 0 )
			return false;
		if ( r.getGreater() != null && r.compareTo( r.getGreater() ) > 0 )
			return false;
		return ( checkChildren( r.getLesser() ) && checkChildren( r. getGreater() ) );
	}
	
	
}
