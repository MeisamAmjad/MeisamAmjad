/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package notComparisonBasedSortings;

/**
 * <p><b> Radix Sort </b> is a non-comparative integer sorting algorithm that sorts data with integer keys by grouping keys by the individual digits which share the same</br>
 * significant position and value. A positional notation is required, but because integers can represent strings of characters (e.g., names or dates) and specially</br>
 * formatted floating point numbers, radix sort is not limited to integers. Radix sort dates back as far as 1887 to the work of Herman Hollerith on tabulating </br>
 * machines.</p>
 * <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
 * @see <a href="https://en.wikipedia.org/wiki/Radix_sort">Radix Sort (Wikipedia)</a
 */
public class RadixSort {
	
	/**
	 * <p>LSD radix sorts typically use the following sorting order: short keys come before longer keys, and keys of the same length are sorted lexicographically.</br>
	 * This coincides with the normal order of integer representations, such as the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11.</p>
	 * @param a
	 * @param w_bit_integer
	 * @param d_bit_integer
	 * @return
	 */
	public static int[] lSD_RadixSort( int[] a , int w_bit_integer, int d_bit_integer) {
		if ( a == null || a.length == 0 )
			return null;
		int[] b = null;
		int length = a.length;
		for ( int p = 0; p < w_bit_integer / d_bit_integer ; p ++ ) {
		   int k = 1 << d_bit_integer; 
		   int c[] = new int[ k ];
		   // the next three for loops implement counting-sort
		   b = new int[ length ];
		   for ( int i = 0; i < length; i ++ ) {
			   int index = ( a[ i ] >>> d_bit_integer * p ) & ( k - 1 ); // & (k -1) means % (k-1)
			   c[ index ] ++;
		   }
		   for ( int i = 1; i < k ; i ++ )
			   c[ i ] += c[ i - 1 ];
		   for ( int i = length - 1; i >= 0; i -- ) {
			   int index = ( a[ i ] >>> d_bit_integer * p ) & ( k - 1 );
			   b[ -- c[ index ] ] = a[ i ];
		   }
		   a = b;
		} 
		return b;
	}
	
	/**
	 * <p>MSD radix sorts use lexicographic order, which is suitable for sorting strings, such as words, or fixed-length integer representations. A sequence such as </br>
	 * "b, c, d, e, f, g, h, i, j, ba" would be lexicographically sorted as "b, ba, c, d, e, f, g, h, i, j". If lexicographic ordering is used to sort variable-length integer</br>
	 * representations, then the representations of the numbers from 1 to 10 would be output as 1, 10, 2, 3, 4, 5, 6, 7, 8, 9, as if the shorter keys were left-justified</br>
	 * and padded on the right with blank characters to make the shorter keys as long as the longest key for the purpose of determining sorted order.</p>
	 * @param a
	 * @param w_bit_integer
	 * @param d_bit_integer
	 * @return
	 */
	public static int[] mSD_RadixSort( int[] a , int w_bit_integer, int d_bit_integer) {
		//TO DO:...
		return a;
	} 
}
