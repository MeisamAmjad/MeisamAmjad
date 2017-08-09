package main;

/**
 * <p> This class uses K-D Tree data structure for finding the nearest .</p>
 * <P> I used the K-D Tree data structure that I implemented while ago and it would be accessible from my github: /MeisamAmjad/Data_Structures/K-DTree</br>
 * This data structure contains three files: Coordinate2D.java, KDTNode.java, KDTree.java which I just copy and past them in the same package as this current class and used them.</p>
 * <p> For running all these the main.java file needs to be run.</p>
 * 
 * @author amjadm
 *
 */
public class YourSolutionStores extends Stores {
	
	KDTree<Coordinate2D> locations;
	
	public YourSolutionStores() {
		super();
		locations = new KDTree<>();
	}

	@Override
	public void build( StoresLocation[] allLocations ) {
		for ( StoresLocation location: allLocations) {
			Coordinate2D loc = new Coordinate2D( location.lng, location.lat );
			this.locations.add( loc );
		}
	}

	@Override
	public StoresLocation getNearest( double lng, double lat ) {
		Coordinate2D loc = new Coordinate2D( this.locations.findNearest( lng, lat ) );
		StoresLocation st = new StoresLocation( "", "", loc.getX(), loc.getY() );
		return st;
	}

}
