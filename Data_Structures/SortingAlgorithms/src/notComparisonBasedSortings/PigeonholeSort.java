/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package notComparisonBasedSortings;

/**
* <p><b> Pigeonhole sorting </b>is a sorting algorithm that is suitable for sorting lists of elements where the number of elements (n) and the length of the range of possible</br>
* key values (N) are approximately the same. It requires O(n + N) time. It is similar to counting sort, but differs in that it "moves items twice: once to the bucket </br>
* array and again to the final destination [whereas] counting sort builds an auxiliary array then uses the array to compute each item's final destination and move </br>
* the item there.</p>
* @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
* @see <a href="https://en.wikipedia.org/wiki/Pigeonhole_sort">Pigeonhole Sort (Wikipedia)</a
*/
public class PigeonholeSort {
	
	public static int[] pigeonholeSort( int[] A , int maxNumber ) {
        int[] b = new int[ maxNumber ];

        for ( int i = 0; i < A.length; b [ A [ i ++ ] ] ++ );

        int index = 0;
        for ( int i = 0; i < b.length; i ++ )
            for ( int j = 0; j< b[ i ]; j ++ )
                A[ index ++] = i;
        return A;
    }
}
