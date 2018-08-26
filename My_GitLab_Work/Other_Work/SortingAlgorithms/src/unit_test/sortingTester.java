package unit_test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

import comparisonBasedSortings.BubbleSort_SinkingSort;
import comparisonBasedSortings.HeapSort;
import comparisonBasedSortings.InsertionSort;
import comparisonBasedSortings.MergeSort;
import comparisonBasedSortings.QuickSort;
import comparisonBasedSortings.TimSort;
import notComparisonBasedSortings.BucketSort;
import notComparisonBasedSortings.CountingSort;
import notComparisonBasedSortings.RadixSort;

public class sortingTester {
	
	private Comparator<Object> IntComparator = new Comparator<Object>() {
		
		public int compare( Integer o1, Integer o2 ) {
			if ( o1 < o2 )
				return -1;
			else if ( o1 == o2 )
				return 0;
			else 
				return 1;
		}

		@Override
		public int compare( Object o1, Object o2 ) {
			if ( o1 instanceof Integer )
				return compare( ( Integer ) o1, ( Integer ) o2 );
			return -2;
		};
	};
	
	@Test
	public void test01() {
		Integer[] numbers = new Integer[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 5000 ; i ++ ) {
			int j = rand.nextInt( 5000 );
			Integer temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		MergeSort.mergeSort( numbers, IntComparator );
		for ( int i = 0; i < 10000 ; assertEquals( ( Integer ) numbers [ i ], ( Integer ) i ++ ) );
	}
	
	@Test
	public void test02() {
		Integer[] numbers = new Integer[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 5000 ; i ++ ) {
			int j = rand.nextInt( 5000 );
			Integer temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		QuickSort.quickSort( numbers, IntComparator );
		for ( int i = 0; i < 10000 ; assertEquals( ( Integer ) numbers [ i ], ( Integer ) i ++ ) );
	}
	
	@Test
	public void test03() {
		Integer[] numbers = new Integer[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 10000 ; i ++ ) {
			int j = rand.nextInt( 10000 );
			Integer temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		HeapSort.heapSort( numbers );
		for ( int i = 0; i < 10000 ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) i );
	}
	
	@Test
	public void test04() {
		int[] numbers = new int[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 10000 ; i ++ ) {
			int j = rand.nextInt( 10000 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		numbers = CountingSort.countingSort( numbers, 10000 );
		for ( int i = 0; i < 10000 ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) i );
			
	}
	
	@Test
	public void test05() {
		int[] numbers = new int[ 10 ];
		for ( int i = 0 ; i < 10 ; numbers [ i ] = i ++ - 5 );
		Random rand = new Random();
		for ( int i = 0; i < 10 ; i ++ ) {
			int j = rand.nextInt( 10 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		numbers = RadixSort.lSD_RadixSort( numbers , 8, 2 );
		for ( int i = 0; i < 5 ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) ( i ) );
		for ( int i = 5; i < 10 ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) ( i - 10 ) );
		
		numbers = new int[ (23457800 - 2345780) ];
		for ( int i = 0 ; i < (23457800 - 2345780) ; numbers [ i ] = i ++ );
		for ( int i = 0; i < (23457800 - 2345780) ; i ++ ) {
			int j = 0 + rand.nextInt( 23457800 - 2345780 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		numbers = RadixSort.lSD_RadixSort( numbers , 32, 16 );
		for ( int i = 0; i < (23457800 - 2345780) ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) ( i ) );
		
		
		numbers = new int[ 1000 ];
		for ( int i = 0 ; i < 1000 ; numbers [ i ] = i ++ );
		for ( int i = 0; i < 1000 ; i ++ ) {
			int j = rand.nextInt( 1000 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		numbers = BucketSort.bucketSort( numbers , 1000 );
		for ( int i = 0; i < 1000 ; i ++ )
			assertEquals( ( Integer ) numbers [ i ], ( Integer ) ( i ) );
	}
	
	@Test
	public void test06() {
		int[] numbers = new int[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 10000 ; i ++ ) {
			int j = rand.nextInt( 10000 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		ArrayList<Integer> A = new ArrayList<>( numbers.length );
		for ( int num : numbers )
			A.add( num );
		A = ( ArrayList<Integer> ) InsertionSort.insertionSort( A );
		for ( int i = 0; i < 10000 ; i ++ )
			assertEquals( ( Integer ) A.get( i ), ( Integer ) i );
			
	}
	
	@Test
	public void test07() {
		Integer[] numbers = new Integer[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 10000 ; i ++ ) {
			int j = rand.nextInt( 10000 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		
		numbers = BubbleSort_SinkingSort.bubbleSort( numbers );
		for ( int i = 0; i < 10000 ; i ++ )
			assertEquals( ( Integer ) numbers[ i ], ( Integer ) i );
			
	}
	
	@Test
	public void test08() {
		Integer[] numbers = new Integer[ 10000 ];
		for ( int i = 0 ; i < 10000 ; numbers [ i ] = i ++ );
		Random rand = new Random();
		for ( int i = 0; i < 10000 ; i ++ ) {
			int j = rand.nextInt( 10000 );
			int temp = numbers [ i ];
			numbers [ i ] = numbers [ j ];
			numbers [ j ] = temp;
		}
		
		TimSort.sort( numbers, IntComparator);
		for ( int i = 0; i < 10000 ; i ++ )
			assertEquals( ( Integer ) numbers[ i ], ( Integer ) i );
			
	}
}
