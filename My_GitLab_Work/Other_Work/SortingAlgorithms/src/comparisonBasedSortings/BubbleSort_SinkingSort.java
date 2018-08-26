/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package comparisonBasedSortings;

/**
 * <p> Bubble sort</b>, sometimes referred to as <b>sinking sort</b>, is a simple sorting algorithm that repeatedly steps through the list to be sorted, compares each pair of adjacent</br>
 * items and swaps them if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which indicates that the list is sorted. The algorithm,</br>
 * which is a comparison sort, is named for the way smaller or larger elements "bubble" to the top of the list. Although the algorithm is simple, it is too slow and impractical</br>
 * for most problems even when compared to insertion sort.[2] It can be practical if the input is usually in sorted order but may occasionally have some out-of-order </br>
 * elements nearly in position. </p>
 * @see <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble Sort or Sinking Sort (Wikipedia)</a
 */
public class BubbleSort_SinkingSort {
	
	public static <T extends Comparable<T>> T[] bubbleSort( T[] A ) {
		boolean swapped = true;
        int length = A.length;
        while ( swapped ) {
            swapped = false;
            for ( int i = 1; i < length; i++ ) {
                if ( A[i].compareTo( A[ i - 1 ] ) < 0 ) {
                    swap( A, i, i - 1 );
                    swapped = true;
                }
            }
            length--;
        }
        return A;
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
