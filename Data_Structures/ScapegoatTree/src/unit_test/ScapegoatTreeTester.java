package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import data_structures.BST;
import data_structures.BSTNode;
import data_structures.ScapegoatTree;

public class ScapegoatTreeTester {
	
	Random random = new Random();

	private BST<Integer, Integer> makeTree( Boolean whichTree, int n ) {
		BST<Integer, Integer> T;
		if ( whichTree )
			T = new ScapegoatTree<>();
		else
			T = new BST<>();
		TreapMakerHelper( T, n );
		return T;
	}
	
	private void TreapMakerHelper( BST<Integer, Integer> bST, int n ) {
		for ( int i = 0; i < n; ++ i ) {
			int key = ( ( int ) random.nextInt( 1000 ) ) + 1;
			bST.insert( key, key+1 );
		}
	}
	
	@Test( expected = IndexOutOfBoundsException.class )
	public void insert_structureTest(){
		BST<Integer, Integer> T; 
		int n = 15;
		T = makeTree( false, n );
		assertTrue( T.check_structure() );
		assertTrue( T.height() > ( ( int ) ( Math.log( n ) / Math.log( 2 ) ) ) );
		
		T = makeTree( true, n );
		assertTrue( T.check_structure() );
		assertTrue( T.height() == ( ( int ) ( Math.log( n ) / Math.log( 2 ) ) ) ); // Height Must be log base 2 of n.
		T.insert( T.getRoot().getKey(), 0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class )
	public void remove_insert_find_Test(){
		BST<Integer, Integer> T; 
		int n = 5000;
		T = makeTree( true, n );
		assertTrue( T.check_structure() );
		assertTrue( T.height() == ( ( int ) ( Math.log( n ) / Math.log( 2 ) ) ) ); // Height Must be log base 2 of n.
		
		T.remove( T.getRoot().getLeft().getRight().getKey() );
		T.remove( T.getRoot().getLeft().getLeft().getKey() );
		for ( int i = 0; i< 10; ++i )
			T.remove( T.getRoot().getKey() );
		assertTrue( T.check_structure() );
		assertTrue( T.height() == ( ( int ) ( Math.log( n ) / Math.log( 2 ) ) ) ); // Height Must be log base 2 of n.
		
		Integer f = T.getRoot().getLeft().getLeft().getKey();
		assertNull( T.find( f ) );
		for ( BSTNode<Integer, Integer> r = T.getRoot(); r != null; r = r.getLeft())
			assertTrue( T.find( r.getKey() ) == r.getKey() + 1 );
		T.remove( 2000 );
	}
}
