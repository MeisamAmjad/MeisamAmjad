/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package comparisonBasedSortings;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p><b>Heap Sort</b> is a comparison-based sorting algorithm. Heapsort can be thought of as an improved selection sort: like that algorithm, it divides its input into</br>
 *  a sorted and an unsorted region, and it iteratively shrinks the unsorted region by extracting the largest element and moving that to the sorted region. The </br>
 *  improvement consists of the use of a heap data structure rather than a linear-time search to find the maximum. Although somewhat slower in practice on</br>
 *  most machines than a well-implemented quicksort, it has the advantage of a more favorable worst-case O(n log n) runtime. Heapsort is an in-place algorithm, </br>
 *  but it is not a stable sort.</p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Heapsort">Heap Sort (Wikipedia)</a
 */
public class HeapSort {
	
	/**
	 * <p> Uses binary heaps data structure to repeatedly extracts the minimum value.</p>
	 * @param numbers
	 * @param intComparator
	 */
	public static <T extends Comparable<T>> void heapSort( T[] A ) {
		if ( A == null || A.length == 0 )
			return;
		BinaryHeap( A );
		for ( int size = A.length; size >= 1 ; ) {
			swap( A, -- size, 0 );
			trickleDown( A, size , 0 );
		}
		Collections.reverse( Arrays.asList( A ) );
	}
	
	/**
	 * <p> Instead of calling the BinaryHeap add() repeatedly this bottom-up algorithm would do the better job. Since the elements a[[n/2]...a[n-1]] have no children it</br>
	 * starts from the element at (n/2-1) towards 0.</br> 
	 * @param A
	 */
	protected static <T extends Comparable<T>> void BinaryHeap( T[] A ) {
		int n = A.length;
		for ( int i = ( n / 2 - 1 ); i >= 0; i -- )
			trickleDown( A, n, i );
			
	}
	
	/**
	 * <p> Compares the pair in the given index repeatedly with its two children and swaps them until no longer bigger than its children. </p>
	 * @param index
	 */
	protected static <T extends Comparable<T>> void trickleDown( T[] A, int length, int index ) {
		do {
			int j = -1, right = index * 2 + 2, left = index * 2 + 1;
			if ( right < length && A[ right ].compareTo( A[ index ] ) < 0 )
				j = ( A[ left ].compareTo( A[ right ] ) < 0 )
						? left
						: right ;
			else if( left < length && A[ left ].compareTo( A[ index ] ) < 0 )
					j = left;
			if ( j >= 0 )
				swap( A, index, j );
			index = j;
		} while ( index >= 0 );
	}
	
	/**
	 * <p> Swaps the value of the two given indexes.</p>
	 * @param A
	 * @param i
	 * @param j
	 */
	protected static <T> void swap( T[] A, int i, int j ) {
		T temp = A [ i ];
		A[ i ] = A [ j ];
		A[ j ] = temp;
	}
}
