/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package comparisonBasedSortings;

import java.util.Collection;
import java.util.List;

/**
 * <p> <b>Insertion sort</b> is a simple sorting algorithm that builds the final sorted array (or list) one item at a time.</br>
 * It only requires a constant amount O(1) of additional memory space. Also, it is efficient for data sets that</br>
 * are already substantially sorted. e.g., the time complexity is O(nk) when each element in the input is no</br>
 * more than k places away from its sorted position. </p>
 * @param ith
 * @see <a href="https://en.wikipedia.org/wiki/Insertion_sort">Insetion sort (Wikipedia)</a
 */
public class InsertionSort {
	
	public static <T extends Comparable<T>> Collection<T> insertionSort( Collection<T> list ) {
		List<T> lst = ( List<T> ) list;
	    for ( int i = 1; i < lst.size() ; i ++ ) {
	    	T item = lst.get( i );
	    	int iH = i;
	    	while ( iH > 0 && item.compareTo( lst.get( iH - 1 ) ) < 0 )
	    		swap( lst, iH, --iH );	// Shifting elements  
	    	lst.set( iH, item );
	    }
	    return lst;
	}
	
	/**
	 * <p> Swaps the value of the two given indexes.</p>
	 * @param A
	 * @param i
	 * @param j
	 */
	protected static <T> void swap( List<T> A, int i, int j ) {
		T temp = A.get( i );
		A.set( i, A.get( j ) );
		A.set( j, temp );
	}

	
}
