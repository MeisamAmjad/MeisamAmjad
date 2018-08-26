package unit_test;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Test;
import data_structures.*;

public class TreapTester {

	private BST<Integer, Integer> makeTreapTree01( Boolean WhichTree, int n ) {
		BST<Integer, Integer> T;
		if ( WhichTree )
			T = new Treap<>();
		else
			T = new BST<>();
		for ( int i = n; i >= 0; -- i ){
			T.insert( i, i*100 );
		}
		return T;
	}
	
	private BST<Integer, Integer> makeTreapTree02( Boolean whichTree, int n ) {
		BST<Integer, Integer> T;
		if ( whichTree )
			T = new Treap<>();
		else
			T = new BST<>();
		TreapMakerHelper( T, 0, n );
		return T;
	}
	
	private void TreapMakerHelper( BST<Integer, Integer> bST, int low, int high ) {
		if ( low < high ) {
			int m = ( low + high ) / 2;
			bST.insert( m, m * 100);
			TreapMakerHelper( bST, low, m );
			TreapMakerHelper( bST, m + 1, high );
		}
	}
	
	private void printTreapTree( TreapNode<Integer, Integer> r ) {
		if ( r == null )
			return;
		System.out.printf( "{ %d, %d, %d }, ", r.getKey(), r.getValue(), r.getP()  );
		printTreapTree( r.getLeft() );
		printTreapTree(  r.getRight() );
	}
	
	@Test
	public void structure_insert_Test01 () {
		BST<Integer, Integer> T;
		int n = 5000;
		T = makeTreapTree01( false, n );
		assertTrue( T.check_structure() );
		assertTrue( T.height() == n );
		T = makeTreapTree01( true, n );
		assertTrue( T.check_structure() );
	}
	
	@Test
	public void structure_insert_Test02(){
		BST<Integer, Integer> T;
		int n = 4000;
		T = makeTreapTree02( false, n );
		assertTrue( T.check_structure() );
		assertTrue( T.height() == ( ( int ) ( Math.log( n ) / Math.log( 2 ) ) ) ); // Height Must be log base 2 of n.
		T = makeTreapTree02( true, n );
		assertTrue( T.check_structure() );
	}
	
	@Test
	public void insert_remove_find_Test(){
		BST<Integer, Integer> T = new Treap<Integer, Integer>();
		int n = 1000;
		T = makeTreapTree02( true, n );
		assertTrue( T.check_structure() );
		assertTrue( T.size() == n );
		for (int i = 0 ; i< 800 ; T.remove( i ++ ));
		assertTrue( T.check_structure() );
		assertTrue( T.size() == 200 );
		for ( int i = 802 ; i < 1000 ; )
			assertTrue( T.find( i) == i ++ * 100 );
		for (int i = 0 ; i< 800 ; )
			assertNull( T.find( i ++ ) );
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeErrorTest(){
		BST<Integer, Integer> T = new Treap<Integer, Integer>();
		int n = 1000;
		T = makeTreapTree02( true, n );
		assertTrue( T.check_structure() );
		assertTrue( T.size() == n );
		for (int i = 0 ; i< 800 ; T.remove( i ++ ));
		assertTrue( T.check_structure() );
		assertTrue( T.size() == 200 );
		T.remove( 799 );
	}
}
