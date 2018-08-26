/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package comparisonBasedSortings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p> <b>Merge Sort</b> (also commonly spelled mergesort) is an efficient, general-purpose, comparison-based sorting algorithm. Most implementations produce a stable sort, </br>
 * which means that the implementation preserves the input order of equal elements in the sorted output. Mergesort is a divide and conquer algorithm that was invented </br>
 * by John von Neumann in 1945. A detailed description and analysis of bottom-up mergesort appeared in a report by Goldstine and Neumann as early as 1948.</p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge Sort (Wikipedia)</a
 */
public class MergeSort {
	
	/**
	 * <p> Static method that receives an array and a Comparator for comparing each elements of the array and sort the given array.<br>
	 * Note that the given array would be sorted after this method is done and it take O(n) space for doing that..</p>
	 * @param A
	 * @param c Comparator
	 */
	public static <T> void mergeSort( T[] A, Comparator<T> c ) {
		if ( A == null )
			return;
		if ( A.length <= 1 ) // It has only one element and it does not need sorting.
			return;
		int median =  A.length >> 1;
		T[] a0 = Arrays.copyOfRange( A, 0, median );
		T[] a1 = Arrays.copyOfRange( A, median, A.length );
		mergeSort( a0, c );
		mergeSort( a1, c );
		merge( a0, a1, A, c );
	}
	
	/**
	 * <p> This method merge arrays a0 and a1 into A in ascending order and uses the c Comparator for comparisons.</p>
	 * @param a0
	 * @param a1
	 * @param A
	 * @param c Comparator
	 */
	protected static <T> void merge ( T[] a0, T[] a1, T[] A, Comparator<T> c ) {
		int a0Index = 0, a1Index = 0;
		for ( int index = 0; index < A.length; index ++ ) {
			if ( a0Index == a0.length )
				A[ index ] = a1[ a1Index ++ ];
			else if ( a1Index == a1.length )
				A[ index ] = a0[ a0Index ++ ];
			else if ( c.compare( a0[ a0Index ], a1[ a1Index ] ) < 0 )
				A[ index ] = a0[ a0Index ++ ];
			else
				A[ index ] = a1[ a1Index ++ ];
		}
	}
}
