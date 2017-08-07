package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;
import data_structures.*;

public class BSTTester01 {
	
	private void create_helper( BST<Integer,Integer> D, int a, int b ) {
		if ( a == b - 1 ) {
			D.add( new Integer( 2 * a ), new Integer( 2 * a + 1 ) );
		}
		else if ( a < b - 1 ) {
			int m = ( a + b ) / 2;
			D.add( new Integer( 2 * m ), new Integer( 2 * m + 1 ) );
			create_helper( D, a, m );
			create_helper( D, m + 1, b ) ;
		}
	}
	
	private BST<Integer,Integer> create_BST(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	@Test
	public void testMin() {
		BST<Integer,Integer> T = create_BST( 64 );
		assertTrue( T.validate() );
		assertEquals( "Min should be 0, not " + ( int ) T.min(), T.min(), new Integer( 0 ) );
	}
	
	@Test
	public void testMin2() {
		BST<Integer,Integer> T = new BST<>();
		for ( int i = 20; i < 40; i ++ ) {
			T.add( new Integer( i ), i );
			assertTrue( T.validate() );
		}
		assertEquals( "Min should be 20, not " + ( int ) T.min(), T.min(), new Integer( 20 ) );
	}
	
	@Test
	public void testMax() {
		BST<Integer,Integer> T = create_BST( 64 );
		assertTrue( T.validate() );
		assertEquals( "Max should be 126, not " + ( int ) T.max(), T.max(), new Integer( 126 ) );
	}
	
	@Test
	public void testMax2() {
		BST<Integer,Integer> T = new BST<>();
		for ( int i = 20; i < 40; i ++ ) {
			T.add( new Integer( 40 - i ), i );
			assertTrue( T.validate() );
		}
		assertEquals( "Max should be 20, not " + ( int ) T.max(), T.max(), new Integer( 20 ) );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deepCopyTester(){
		BST<Integer, Integer> T = create_BST( 7 );
		try {
			BST<Integer, Integer> newT1 = (BST<Integer, Integer> ) T.clone();
			BST<Integer, Integer> newT2 = (BST<Integer, Integer> ) newT1.clone();
			assertTrue( T.validate() && newT1.validate() && newT2.validate() );
			newT1.remove( 4 );
			newT2.remove( 10 );
			assertTrue( T.contains( 4) );
			assertTrue( T.contains( 10 ) );
			assertFalse( newT1.contains( 4) );
			assertTrue( newT2.contains( 4) );
			assertFalse( newT2.contains( 10) );
			T.remove( 0 );
			assertTrue( newT1.contains( 0 ) );
			assertTrue( newT2.contains( 0 ) );
			assertFalse( T.contains( 0 ) );
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}