/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package comparisonBasedSortings;

import java.util.Comparator;
import java.util.Random;

/**
 * <p><b>Quick Sort</b> (sometimes called partition-exchange sort) is an efficient sorting algorithm, serving as a systematic method for placing the elements of an array in order.</br>
 * Developed by Tony Hoare in 1959 and published in 1961, it is still a commonly used algorithm for sorting. When implemented well, it can be about two or three times</br>
 * faster than its main competitors, merge sort and heapsort.</p>
 * Quicksort is a comparison sort, meaning that it can sort items of any type for which a "less-than" relation (formally, a total order) is defined. In efficient implementations</br>
 * it is not a stable sort, meaning that the relative order of equal sort items is not preserved. Quicksort can operate in-place on an array, requiring small additional amounts</br>
 *  of memory to perform the sorting.</p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quick Sort (Wikipedia)</a
 */
public class QuickSort {
	
	protected static Random rand;
	/**
	 * <p> It receives an array and sort it in place and return it. For comparison it uses c Comparator.</p>
	 * @param A
	 * @param c
	 */
	public static <T> void quickSort( T[] A, Comparator<T> c ) {
		rand = new Random();
		quickSort( A, 0, A.length, c );
	}

	/**
	 * <p> This method picks a random pivot element, and divides the partition in three sets and recursively sorts the first and third sets in the partition.</p>
	 * @param A
	 * @param i
	 * @param n
	 * @param c
	 */
	protected static <T> void quickSort( T[] A, int start, int n, Comparator<T> c ) {
		if ( A == null )
			return;
		if ( n <= 1) // It has only one element and it does not need sorting.
			return;
		T pivot = A [ start + rand.nextInt( n ) ];
		int leftIndex = start - 1, j = start, rightIndex = start + n;
		while ( j < rightIndex ) {
			int comp = c.compare( A[ j ], pivot );
			if ( comp < 0 )	// Move to beginning of the array
				swap ( A, j ++ , ++ leftIndex );
			else if ( comp > 0 )	// Move to end of array
				swap( A, j, -- rightIndex );
			else 
				++ j;	// keep in the middle
		}
		// So far: A[ start..leftIndex ] < pivot, A[ leftIndex + 1 .. rightIndex - 1 ] == pivot, A[ rightIndex..start + n - 1 } > pivot.
		quickSort ( A, start, leftIndex - start + 1, c );
		quickSort ( A, rightIndex, n - ( rightIndex - start ), c );
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
