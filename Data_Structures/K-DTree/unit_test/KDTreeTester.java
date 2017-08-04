package unit_test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import data_structures.*;

public class KDTreeTester {
	
	int[][] points = { {2, 3}, {5, 6}, {5, -4}, {-1, 1}, {0, 0}, {1, 3}, {-3, -2}, {-2, 0}, {4, 3}, {6, -5}, {0, 2}, {-4, 1}, {2, -3} };
	Coordinate2D[] allCoordinates = null; 
	
	private static Coordinate2D bruteGetNearest( Coordinate2D[] allCoordinates, double lng, double lat){
		Coordinate2D desirePoint = new Coordinate2D( lng, lat );
		double dist = desirePoint.GeographicalDistance( allCoordinates[ 0 ] );
		int index = 0;
		for( int i = 1; i < allCoordinates.length; i ++ ) {
			double cdist = desirePoint.GeographicalDistance( allCoordinates[ i ] );
			if( cdist < dist ) {
				index = i;
				dist = cdist;
			}
		}
		return allCoordinates[ index ];
	}

	@Test
	public void test01Add_Contains(){
		KDTree<Coordinate2D> T = new KDTree<>();
		assertTrue( T.isEmpty() );
        for ( int[] point : points){
        	assertTrue( T.add( point[0], point[1] ) );
        }
        assertFalse( T.add( 5, -4 ) );
        assertTrue( T.add( -4, 5 ) );
		assertTrue( T.contains( -1, 1 ) );
		assertFalse( T.contains( 1, -1 ) );
		assertTrue( T.checkChildren() );
	}
	
	@Test
	public void test02Add_Contains() {
		List<Coordinate2D> list = new ArrayList<>();
		for ( int[] point : points )
			list.add( new Coordinate2D( point[0], point[1] ) );
		KDTree<Coordinate2D> T = new KDTree<>( list );
		assertTrue( T.size() == list.size() );
		for ( Coordinate2D point : list )
			assertTrue( T.contains( point ) );
		assertFalse( T.contains( -5, 6 ) );
		assertTrue( T.checkChildren() );
	}
	
	@Test
	public void add_iterator_containsTest(){
		List<Coordinate2D> coordinates = new ArrayList<>();
		for ( int[] point : points )
			coordinates.add( new Coordinate2D( point[0], point[1] ) );
		/*
		 * Building a tree.
		 * Testing constructor.
		 */
		KDTree<Coordinate2D> T = new KDTree<>( coordinates );
		for ( Coordinate2D point : coordinates ) // Checking if all elements exist in T.
			assertTrue( T.contains( point ) );
		/*
		 * Testing the Iterator
		 */
		Iterator<Coordinate2D> L = T.iterator();
		int c = 0;
		while ( L.hasNext() ) {
			++ c;
			assertTrue( T.contains( L.next() ) );
		}
		assertTrue( c == T.size() );
		assertTrue( T.checkChildren() );
	}
	
	@Test
	public void removeTest() {
		List<Coordinate2D> list = new ArrayList<>();
		for ( int[] point : points )
			list.add( new Coordinate2D( point[0], point[1] ) );
		KDTree<Coordinate2D> T = new KDTree<>( list );
		int oldSize = T.size();
		assertTrue( T.remove( 1.0, 3.0 ) );
		 -- oldSize;
		 assertTrue( T.checkChildren() );
		 
		assertTrue( T.remove( -1.0, 1 ) );
		-- oldSize;
		assertTrue( T.checkChildren() );
		
		assertTrue( T.remove( 0.0, 2.0 ) );
		assertTrue( T.size() == -- oldSize );
		assertTrue( T.checkChildren() );
		
		assertTrue( T.remove( 2, -3 ) );
		assertTrue( T.size() == -- oldSize );
		assertTrue( T.checkChildren() );
		
		assertTrue( T.remove( -4, 1 ) );
		assertTrue( T.size() == -- oldSize );
		assertTrue( T.checkChildren() );
		
		assertTrue( T.remove( 5, -4 ) );
		assertTrue( T.size() == -- oldSize );
		assertTrue( T.checkChildren() );
		
		assertTrue( T.remove( 0, 0 ) );
		assertTrue( T.size() == -- oldSize );
		assertTrue( T.checkChildren() );
		
		assertFalse( T.contains( 2,  -3 ) );
		assertFalse( T.contains( 0,  2 ) );
		assertTrue( T.checkChildren() );
		
		assertTrue( T.add( 0, 2 ) );
		assertTrue( T.size() == ++ oldSize );
		assertTrue( T.contains( 0,  2 ) );
		assertTrue( T.checkChildren() );
	}
	
	@Test
	public void findNearestTest() {
		List<Coordinate2D> list = new ArrayList<>();
		for ( int[] point : points )
			list.add( new Coordinate2D( point[0], point[1] ) );
		KDTree<Coordinate2D> T = new KDTree<>( list );
		Coordinate2D[] ps = new Coordinate2D [ list.size() ];
		Coordinate2D result1 = bruteGetNearest( list.toArray( ps ), -2, -0.5 );
		Coordinate2D result2 = T.findNearest( -2, -0.5 );
		assertTrue( result1.equals( result2) );
		assertTrue( T.checkChildren() );
	}
}
