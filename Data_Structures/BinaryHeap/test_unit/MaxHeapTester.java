package test_unit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import org.junit.Test;
import data_structures.*;

/**
 * <p>This class is for testing the <b>Max-Heap</b>.</p>
 * @author Meijad
 */
public class MaxHeapTester {
	@Test
	public void test01() { //Check the method size()
		BinaryHeap<String,Integer> H = new MaxHeap<>();
		assertEquals( H.size(), 0 );
	}
	
	@Test
	public void test02() { //Check the methods Clear() and Empty()
		BinaryHeap<Integer, Integer> H = new MaxHeap<>();
		for ( int i = 0 ; i < 100 ; i ++ ) H.add( i + 1, i );
		H.clear();
		assertTrue( "It should be True", H.isEmpty() );
	}
	
	@Test
	public void test03() { //Check the method Peek() and add(entity, priority)
		BinaryHeap<Integer, Integer> H = new MaxHeap<>();
		Random rand = new Random();
		int max = 500, min = 10;
		for ( int i = 0; i < 199; i ++ )
			H.add( i, ( int ) ( rand.nextInt( ( max - min ) + 1 ) + min ) );
		H.add( 2000, 1000 );
		assertEquals( "It must be Zero", ( int ) H.peek() , 2000 );
	}
	
	@Test
	public void test04() { //Check the method add()/remove()
		BinaryHeap<Integer, Integer> H=new MaxHeap<>();
		Random rand = new Random();
		int max = 2000, min = 0;
		int[] pt=new int[ 4000 ];
		for ( int i = 0; i < 4000; i ++ ) {
			int p = ( int ) ( rand.nextInt( ( max - min ) + 1 ) + min );
			H.add( p, p );
			pt[ i ] = p;
		}
		Arrays.sort( pt );
		for ( int i = 3999; i <= 0 ; i -- ) {
			int entity = ( int ) H.remove();
			assertTrue( "It must be" + pt[ i ], pt[ i ] == entity );
		} 
	}
	
	@Test( expected=ArrayIndexOutOfBoundsException.class )
	public void test05() { //Check the method clear() alone, and peek() when the list is empty
		BinaryHeap<Integer, Integer> H=new MaxHeap<>();
		Random rand = new Random();
		int max = 50, min = 1;
		for ( int i = 0; i < 10; i ++ ) {
			int p = ( int ) ( rand.nextInt( ( max - min ) + 1 ) + min );
			H.add( p, p );
		}
		H.clear();
		assertEquals( ( int ) H.peek(), 0 );
	}
	
	@Test
	public void test06(){ //check the method add() when having null entity
		BinaryHeap<Integer, Integer> H=new MaxHeap<>();
		assertFalse( H.add( null, 10 ) );
	}
	
	@Test( expected=ArrayIndexOutOfBoundsException.class )
	public void test07(){ //Check the method remove() when the list is empty
		BinaryHeap<Integer, Integer> H=new MaxHeap<>();
		H.remove();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test08() { //check Iterator(), clone(), find(), and contains()
		BinaryHeap<Integer, Integer> H = new MaxHeap<>();
		Random rand = new Random();
		int max = 500, min = 0;
		for ( int i = 0; i < 500; i ++ )
			H.add( i, ( int ) ( rand.nextInt( ( max - min ) + 1 ) + min ) );
		BinaryHeap<Integer, Integer> H2 = new MaxHeap<>();
		try {
			H2 = ( MaxHeap<Integer, Integer> ) H.clone();
			assertEquals( H.size() , H2.size() ); // checking both sizes.
			Iterator<PairNode<Integer, Integer>> HItr = H2.iterator();
			while ( HItr.hasNext() ){ // checking all elements.
				assertTrue( H.contains( HItr.next().getValue() ) );
			}
			assertNull( H2.find( 10000 ) );
			assertNull( H.find( 10000 ) );
			H2.remove(); H2.remove();
			assertEquals( H2.size(), H.size() - 2 );
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test09() { //check addAll()
		BinaryHeap<Integer, Integer> H = new MaxHeap<>();
		Random rand = new Random();
		ArrayList<PairNode<Integer, Integer>> list = new ArrayList<>();
		int max = 500, min = 0;
		for ( int i = 0; i < 500; i ++ ) {
			int rnd = ( int ) ( rand.nextInt( ( max - min ) + 1 ) + min );
			list.add( new PairNode<>( rnd, rnd * 10 ) );
		}
		assertTrue( H.isEmpty() );
		assertTrue( H.addAll( list ) );
		assertEquals( H.size(), list.size() );
		for ( int i = 0; i < 500; i ++ )
			assertEquals( H.find( list.get( i ).getValue() ), list.get( i ).getKey() );
	}
	
	@Test
	public void tes10() { //check removeAll() and retainAll()
		BinaryHeap<Integer, Integer> H = new MaxHeap<>();
		ArrayList<PairNode<Integer, Integer>> list = new ArrayList<>();
		for ( int i = 0; i < 450; i ++ ) {
			H.add( i * 10, i );
		}
		for ( int i = 451; i <= 500; i ++ ) {
			list.add( new PairNode<>( i , i * 10 ) );
		}
		assertFalse( H.removeAll( list ) );
		list.clear();
		for ( int i = 50; i < 100; i ++ ) {
			list.add( new PairNode<>( i, i * 10 ) );
		}
		assertTrue( H.removeAll( list ) );
		for ( int i = 0; i < 50; i ++ )
			assertFalse( H.contains( list.get( i ).getValue() ) );
		assertTrue( H.contains( 1000 ) );
		list.add( new PairNode<>( 49 , 490 ) );
		assertTrue( H.retainAll( list ) );
		assertTrue( H.size() == 1 );
	}
}
