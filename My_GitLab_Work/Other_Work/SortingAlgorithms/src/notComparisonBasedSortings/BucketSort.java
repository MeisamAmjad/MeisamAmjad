/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package notComparisonBasedSortings;

/**
* <p><b> Bucket sort, or bin sort,</b> is a sorting algorithm that works by distributing the elements of an array into a number of buckets. Each bucket is then sorted</br>
* individually, either using a different sorting algorithm, or by recursively applying the bucket sorting algorithm. It is a distribution sort, and is a cousin of </br>
* radix sort in the most to least significant digit flavour. Bucket sort is a generalization of pigeonhole sort. Bucket sort can be implemented with comparisons</br>
*  and therefore can also be considered a comparison sort algorithm. The computational complexity estimates involve the number of buckets.</p>
* <p>For <i><b>programmer</b></i> all none public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
* to implement the methods again as they wish.</b></p>
* @author <b>Meisam Amjad </b></br> <amjadm@miamioh.edu>
* @see <a href="https://en.wikipedia.org/wiki/Bucket_sort">Bucket or bin Sort (Wikipedia)</a
*/
public class BucketSort {
	
	public static int[] bucketSort( int[] A , int maxNumber ) {
        int i, j;
        int[] bucket = new int[ maxNumber ];

        for ( i = 0; i < A.length; bucket[ A[i ++ ] ] ++ );

        int index = 0;
        for ( i = 0; i < bucket.length; i ++ )
            for ( j = 0; j< bucket[ i ]; j ++ )
                A[ index ++] = i;
        return A;
    }
}
