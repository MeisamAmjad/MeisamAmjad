/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package notComparisonBasedSortings;

/**
 * <p><b> Counting Sort </b>is an algorithm for sorting a collection of objects according to keys that are small integers; that is, it is an integer sorting algorithm. It operates </br>
 * by counting the number of objects that have each distinct key value, and using arithmetic on those counts to determine the positions of each key value in the </br>
 * output sequence. Its running time is linear in the number of items and the difference between the maximum and minimum key values, so it is only suitable for </br>
 * direct use in situations where the variation in keys is not significantly greater than the number of items. However, it is often used as a subroutine in another </br>
 * sorting algorithm, radix sort, that can handle larger keys more efficiently.</p>
 * <p>Because counting sort uses key values as indexes into an array, it is not a comparison sort, and the Ω(n log n) lower bound for comparison sorting does not </br>
 * apply to it. Bucket sort may be used for many of the same tasks as counting sort, with a similar time analysis; however, compared to counting sort, bucket sort </br>
 * requires linked lists, dynamic arrays or a large amount of preallocated memory to hold the sets of items within each bucket, whereas counting sort instead </br>
 * stores a single number (the count of items) per bucket.</p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Counting_sort">Counting Sort (Wikipedia)</a
 */
public class CountingSort {
	
	/**
	 * <p> For each i ∈ {0,...,k−1}, count the number of occurrences of i in a and store this in c[i]. Now, after sorting, the output will look like c[0] occurrences of 0,</br>
	 * followed by c[1] occurrences of 1, followed by c[2] occurrences of 2,. . . , followed by c[k − 1] occurrences of k − 1....</p>
	 * @param A
	 * @param k
	 * @return
	 */
	public static int[] countingSort( int[] A, int k ) {
		if ( A == null || A.length == 0 )
			return null;
		int length = A.length;
		int[] c = new int[ k ], result = new int[ length ];
		for ( int i = 0; i < length; c[ A[ i ++ ] ] ++ );
		for ( int i = 1; i < k; c[ i ] += c[ i ++ - 1 ] );
		for ( int i = length - 1; i >= 0; result[ -- c[ A[ i ] ] ] = A[ i -- ]);
		return result;
	}
}
