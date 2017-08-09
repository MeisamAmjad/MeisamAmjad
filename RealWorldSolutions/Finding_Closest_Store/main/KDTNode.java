/*
 * Copyright (c) 2017, 2018.
 * This class is just for educational purpose.
 */
package main;

/**
* <p>For determining the current node is either horizontal(XAxis) or vertical(YAxis). Since each node in KDTNode should</br>
* be either on a XAxis or YAxis this Enum can be used for representing that. Also, while adding, the next() method would </br>
* be used for determining a new KDTree node's axis value based on its parent.</p>
* @author <b> Meisam Amjad</b></br>	amjadm@miamioh.edu
* @see data_structures.KDTree
*/
enum Axis {
   XAxis, YAxis;

   /**
    * <p>Goes to the other axis. It goes to XAxis if the current axis is YAxis and vise versa. </p>
    * @return The next axis.
    */
	public Axis next() {
   	return ( this.equals( Axis.XAxis ) )
   			? Axis.YAxis
   			: Axis.XAxis;
   }
}



/**
 * <p> the <b> KDTNode class </b> defines a node for representing a point inside the K-Dtree holding a point ( x and y representing a coordinate), and</br>
 * its axis (representing this coordinate is located on which axis, either XAxis or Yaxis), also parent, lesser,and greater nodes for connecting</br>
 * nodes to each other.</p>
 * @author <b> Meisam Amjad</b></br>	amjadm@miamioh.edu
 * @see data_structures.KDTree
 */
public class KDTNode implements Comparable<KDTNode>{
	
	/**
	 * <p>Holds a coordinate x and y on the space. By default they both hold 0.0.</p>
	 * @see #getPoint()
	 * @see #setPoint(Coordinate2D point)
	 */
	private Coordinate2D point = new Coordinate2D();
	
	/**
	 * <p>An Enum variable which holds either XAxis or YAxis. By default it holds XAxis.</p>
	 * @see #Axis
	 */
	private Axis axis = Axis.XAxis;
	
	/**
	 * <p>Different nodes for connecting all nodes to each other. They all be initialized by null value.</p>
	 * @see #getLesser()
	 * @see #getParent()
	 * @see #getGreater()
	 * @see #setLesser(Coordinate2D lesser)
	 * @see #setParent(Coordinate2D parent)
	 * @see #setGreater(Coordinate2D greater)
	 */
	private KDTNode lesser = null, parent = null, greater = null;
	
	/**
	 * <p>The <b>Default Constructor</b>. Sets x and y to 0.0 and axis to XAxis</p>
	 */
	public KDTNode() {
		super();
	}
	
	/**
	 * <p>The <b>Copy Constructor</b> which sets the coordinate to the given point's coordinate and axis to XAxis.</p>
	 * @param point The Coordinate2D object.
	 */
	public KDTNode( Coordinate2D point ) {
		super();
		setPoint( point );
		setAxis( Axis.XAxis );
	}
	
	/**
	 * <p>The <b>Copy Constructor</b> which sets everything to the given newNode's details.</p>
	 * @param newNode The KDTNode object.
	 */
	public KDTNode( KDTNode newNode ) {
		super();
		setPoint( newNode.getPoint() );
		setAxis( newNode.getAxis() );
		setLesser( newNode.getLesser() );
		setParent( newNode.getParent() );
		setGreater( newNode.getGreater() );
	}
	/**
	 * @see #point
	 */
	protected Coordinate2D getPoint() { return this.point;	}
	
	/**
	 * @see #point
	 */
	protected void setPoint( Coordinate2D point ) { this.point = point; }

	/**
	 * @see #axis
	 */
	protected Axis getAxis() {	return this.axis; }

	/**
	 * @see #axis
	 */
	protected void setAxis( Axis axis) { this.axis = axis; }

	/**
	 * @see #parent
	 */
	protected KDTNode getParent() { return this.parent; }

	/**
	 * @see #parent
	 */
	protected void setParent( KDTNode parent ) { this.parent = parent; }

	/**
	 * @see #lesser
	 */
	protected KDTNode getLesser() { return this.lesser; }

	/**
	 * @see #lesser
	 */
	protected void setLesser( KDTNode lesser ) { this.lesser = lesser; }

	/**
	 * @see #greater
	 */
	protected KDTNode getGreater() { return this.greater; }

	/**
	 * @see #greater
	 */
	protected void setGreater( KDTNode greater ) { this.greater = greater; }
	
	/**
	 * <p> Returns the difference between 2 points based on their active axis in power of 2.</br>
	 * This method is used for checking if it would be less than equal the best distance,</br>
	 * then it means it should check the other side of the tree as well.</p>
	 * @param other
	 * @return double
	 * @see data_structures.KDTree#findNearest(KDTNode currentNode, KDTNode targetNode, Pair output)
	 */
	protected double diff( KDTNode other ) {
		double diff = ( this.getAxis() == Axis.XAxis )
					? this.getPoint().getX() - other.getPoint().getX()
					: this.getPoint().getY() - other.getPoint().getY();
		return  diff * diff;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31 * ( getPoint().hashCode() + getAxis().hashCode() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "( " + Double.toString( getPoint().getX() ) + ", " + Double.toString( getPoint().getY() ) +
				" ), Axis: " + getAxis().toString() + ".";
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( KDTNode o ) {
		switch( getAxis() ) {
			case XAxis:
				return Coordinate2D.X_Comparator.compare( this.getPoint(), o.getPoint() );
			case YAxis:
				return Coordinate2D.Y_Comparator.compare( this.getPoint(), o.getPoint() );
		}
		return Integer.MIN_VALUE;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( ( obj == null ) || ( this == null ) || !( obj instanceof KDTNode ) )
			return false;
		KDTNode newNode = ( KDTNode ) obj;
		return ( this.compareTo( newNode ) == 0 )
			? true
			: false;
	}
}
