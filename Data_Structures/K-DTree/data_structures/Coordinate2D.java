package data_structures;
/*
 * Copyright (c) 2017, 2018.
 * This Class is just for educational purpose. 
 */

import java.util.Comparator;

/**
 * <p>The <b>Coordinate2D class</b> defines a point representing a location or a coordinate (x,y) on space. </br>
 * <b>This class would be used by KDTree class</b></p>
 * @author	<b> Meisam Amjad </b> </br> amjadm@miamioh.edu
 * @see {@link java.awt.geom.Point2D}
 * @see data_structures.KDTree
 */
public class Coordinate2D implements Comparable<Coordinate2D>{
	/**
	 * <p>X and Y representing a point in 2 dimensional space. Each holds a <b> double</b> value as a coordinates.
	 * @see #getX()
	 * @see #setX(double x)
	 * @see #getY()
	 * @see #setY(double y)
	 */
	private double X = 0.0, Y = 0.0;
	
	/**
	 * <p>The <b>Default Constructor</b>. Sets x and y to 0.0.</p>
	 */
	public Coordinate2D() {
		super();
	}
	
	/**
	 * <p>The <b>Constructor</b> which sets x and y to new given x and y.</p>
	 * @param x A new double value for x coordinate.
	 * @param y A new double value for y coordinate.
	 */
	public Coordinate2D( double x, double y ) {
		super();
		setX( x );
		setY( y );
	}
	
	/**
	 * <p>The <b>Copy Constructor</b> which sets x and y to new coordinates from the given newPoint</p>
	 * @param newNode Coordinate2D object.
	 */
	public Coordinate2D ( Coordinate2D newPoint ) {
		super();
		setX( newPoint.getX() );
		setY( newPoint.getY() );
	}
	
	/**
	 * @see #X
	 */
	protected double getX() { return this.X; }

	/**
	 * @see #X
	 */
	protected void setX( double x ) { this.X = x; }

	/**
	 * @see #Y
	 */
	protected double getY() { return this.Y; }

	/**
	 * @see #Y
	 */
	protected void setY( double y ) { this.Y = y; }
	
	/**
	 * <p> Acts as a copy constructor and sets the location of this Coordinate2D to the given double coordinates.</p>
	 * @param x A new double value for x coordinate.
	 * @param y A new double value for y coordinate.
	 */
	protected void setLocation( double x, double y ) {
		
	}
	/**
	 * <p>Measures the distance from this point to the given p2</p>
	 * @param p2 The specified point to be measured against this Point2D.
	 * @return <b> double</b></br>The distance between 2 points.
	 * @see data_structures.KDTree#findNearest(KDTNode currentNode, KDTNode targetNode, Pair output)
	 */
	public double distance( Coordinate2D p2 ) {
		if ( ( this == null ) || ( p2 == null ) )
			throw new IllegalArgumentException( "**The given points can not be null!**" );
		double px = p2.getX() - getX(),
				py = p2.getY() - getY();
		return Math.sqrt( px * px + py * py );
	}
	
	/**
	 * <p>Given the longitude and latitude of two points, returns their distance in <b>kilometer</b>. All Longs and Lats are in  </br>.
	 * degrees meaning they all need to change to Radius first.</p>
	 * @param long1 longitude (x1) of first point.
	 * @param lat1 latitude (y1) of first point.
	 * @param long2 longitude (x2) of second point.
	 * @param lat2 latitude (y2) of second point.
	 * @return <b>double </b></br> Distance in <b>km</b>.
	 * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">Geographical distance (Wikipedia)</a
	 */
	public double GeographicalDistance( double long1, double lat1, double long2, double lat2 ) {
		double R = 6371.009; // radius of earth, in km
		double dLat = ( lat2 - lat1 ) / 180 * Math.PI ; // Δϕ = ϕ2 − ϕ1 in Radians
		double dLon = ( long2 - long1 ) / 180 * Math.PI; // Δλ	= λ2 − λ	1 in Radians
		// Convert from Degrees to Radians
		lat1 = lat1 / 180 * Math.PI;
		lat2 = lat2 / 180 * Math.PI;
		double a = Math.sin( dLat / 2 ) * Math.sin( dLat / 2 ) +
			Math.sin( dLon / 2 ) * Math.sin( dLon / 2 ) * Math.cos( lat1 ) * Math.cos( lat2 ); 
		double c = 2 * Math.atan2( Math.sqrt( a ), Math.sqrt( 1 - a ) ); 
		double d = R * c;
		return d;
	}
	
	/**
	 * <p>Given the two points, returns their distance in <b>kilometer</b>.</p> 
	 * @param p1 Coordinate2D object
	 * @param p2 Coordinate2D object
	 * @return <b>double </b></br> Distance in <b>km</b>.
	 * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">Geographical distance (Wikipedia)</a
	 */
	public double GeographicalDistance( Coordinate2D p1, Coordinate2D p2 ) {
		double long1 = p1.getX(), lat1 = p1.getY(), long2 = p2.getX(), lat2 = p2.getY();
		return GeographicalDistance( long1, lat1, long2, lat2 );
	}
	
	/**
	 * <p>Given point, returns the distance to it in <b>kilometer</b>.</p> 
	 * @param other Coordinate2D object
	 * @return <b>double </b></br> Distance in <b>km</b>.
	 * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">Geographical distance (Wikipedia)</a
	 */
	public double GeographicalDistance( Coordinate2D other ) {
		double long2 = other.getX(), lat2 = other.getY();
		return GeographicalDistance( getX(), getY(), long2, lat2 );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31 * (int) ( getX() + getY() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ( this == null )
				? "( null )"
				: "( " + Double.toString( getX() ) + ", " + Double.toString( getY() ) + " )";
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( Coordinate2D p2 ) {
		if ( ( p2 == null ) || ( this == null ) )
			throw new IllegalArgumentException( "** Illegal object. Either null or different types.**" );
		int xComp = X_Comparator.compare( this, p2 );
		if ( xComp != 0 )
			return xComp;
		int	yComp = Y_Comparator.compare( this, p2 );
		return yComp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( ( obj == null ) || ( this == null ) || !( obj instanceof Coordinate2D ) )
			return false;
		Coordinate2D point = ( Coordinate2D ) obj;
		return ( this.compareTo( point ) == 0)
				? true
				: false;
	}
	
	/**
	 * <p> Defining a new comparator which only compares X coordinates between 2 points. 
	 * @return <b> -1</b> If the first point's x coordinate is less than the second point's x coordinate.</br>
	 * 			<b> 0 </b> If both points have the same x coordinates.</br>
	 * 			<b> 1 </b> otherwise.
	 */
	public static Comparator<Coordinate2D> X_Comparator = new Comparator<Coordinate2D>() {

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Coordinate2D o1, Coordinate2D o2) {
			if ( o1.getX() < o2.getX() )
				return -1;
			if ( o1.getX() > o2.getX() )
				return 1;
			return 0;
		}
	};
	
	/**
	 * <p> Defining a new comparator which only compares Y coordinates between 2 points. 
	 * @return <b> -1</b> If the first point's y coordinate is less than the second point's y coordinate.</br>
	 * 			<b> 0 </b> If both points have the same y coordinates.</br>
	 * 			<b> 1 </b> otherwise.
	 */
	public static Comparator<Coordinate2D> Y_Comparator = new Comparator<Coordinate2D>() {

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Coordinate2D o1, Coordinate2D o2) {
			if ( o1.getY() < o2.getY() )
				return -1;
			if ( o1.getY() > o2.getY() )
				return 1;
			return 0;
		}
	};
}

