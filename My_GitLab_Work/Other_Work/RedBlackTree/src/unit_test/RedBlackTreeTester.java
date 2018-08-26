package unit_test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;

import data_structures.*;

public class RedBlackTreeTester {
	
	@Test
	public void addTest01() {
		RedBlackTree<Double, Integer> T = new RedBlackTree<Double, Integer>();
		for (int i = 0 ; i < 10 ; T.add( i * 1.0, i ++ * 100) );
		T.add( -1.0, -100 );
		T.add( 3.5, 350 );
		T.add( -2.0, -200 );
		T.add( 2.5, 250 );
		T.add( 2.7, 270 );
		T.add( 7.0, 700 );
		T.add( 5.5, 550 );
		T.add( 5.4, 540 );
		assertTrue( T.height() - 1  <= 2 * ( ( int ) ( Math.log( T.size() ) / Math.log( 2.0 ) ) ) );
		assertTrue( T.check_structure() );
	}
	
	public void addTest02() {
		RedBlackTree<Integer, Integer> T = new RedBlackTree<Integer, Integer>();
		for ( int i = 1000 ; i > 10 ; assertTrue( T.add( i, i -- * 10 ) ) );
		assertFalse( T.add( null, 60 ) );
	}
	
	@Test
	public void addTest03() {
		RedBlackTree<Integer, Integer> T = new RedBlackTree<Integer, Integer>();
		for ( int i = 10000 ; i > 0 ; ) {
			T.add( i, i -- * 10 );
			assertTrue( T.height() - 1  <= ( 2 * ( ( int ) ( Math.log( T.size() ) / Math.log( 2.0 ) ) ) )  );
		}
		assertTrue( T.check_structure() );
	}
	
	@Test
	public void add_find_inorderTest01() {
		RedBlackTree<Integer, Integer> T = new RedBlackTree<Integer, Integer>();
		Random rnd = new Random();
		int rndKey, maxNumber = 100000;
		for ( int i = maxNumber ; i > 0 ; ) {
			T.add( i, i -- );
			rndKey =  i +  ( ( int ) rnd.nextInt( maxNumber - i ) ) + 1 ;
			assertTrue( rndKey == T.find( rndKey ) );
		}
		ArrayList<BSTNode<Integer, Integer>> A = T.inOrderSortTravers();
		for ( int i = 1 ; i <= maxNumber ; assertTrue( A.get( i - 1 ).getKey() == i ++  ) );
	}
	
	@Test
	public void removeTest01() {
		RedBlackTree<Integer, Integer> T = new RedBlackTree<Integer, Integer>();
		Random rnd = new Random();
		int rndKey, maxNumber = 2000;
		T.add( 0, 0 );
		for ( int i = 1 ; i < maxNumber ; i ++ ) {
			assertTrue( T.add( i, i ) );
			rndKey = (  ( int ) rnd.nextInt( i ) + 1 );
			while ( T.find( rndKey ) == null )
				rndKey = (  ( int ) rnd.nextInt( maxNumber ) + 1 );
			assertTrue( T.delete( rndKey ) );
			assertTrue( T.height() - 1  <= ( 2 * ( ( int ) ( Math.log( T.size() ) / Math.log( 2.0 ) ) ) )  );
		}
	}
	
	@Test
	public void isRBTTester() {
		RedBlackTree<Integer, Integer> T = new RedBlackTree<Integer, Integer>();
		Random rnd = new Random();
		int rndKey, maxNumber = 2000;
		T.add( 0, 0 );
		for ( int i = 1 ; i < maxNumber ; i ++ ) {
			assertTrue( T.add( i, i ) );
			
			assertTrue( T.check_structure() );
			assertTrue( T.isRBT( ( RBTNode<Integer, Integer> ) T.getRoot() ) );
			
			rndKey = (  ( int ) rnd.nextInt( i ) + 1 );
			while ( T.find( rndKey ) == null )
				rndKey = (  ( int ) rnd.nextInt( maxNumber ) + 1 );
			assertTrue( T.delete( rndKey ) );
			assertTrue( T.height() - 1  <= ( 2 * ( ( int ) ( Math.log( T.size() ) / Math.log( 2.0 ) ) ) )  );
			
			assertTrue( T.check_structure() );
			assertTrue( T.isRBT( ( RBTNode<Integer, Integer> ) T.getRoot() ) );
		}
	}
}
