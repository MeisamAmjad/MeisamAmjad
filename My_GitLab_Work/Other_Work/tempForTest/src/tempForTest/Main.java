package tempForTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static String toBinary(int intValue){
		return Integer.toBinaryString(intValue);
	}
	
	public static void printNumberIn2Styles(int num){
		System.out.printf("(%32s)<=(%5d) \n",toBinary(num),num);
	}
	
	public static int bitCount( int i ) {
	    // HD, Figure 5-2 
		//System.out.println(toBinary(i) + "\t" + i + "\n" );
		i -= ( ( i >>> 1 ) & 0x55555555 ); // Put count of each 2 bits into those 2 bits. 
		//System.out.println(toBinary(i) + "\t" + i + "\n" );
	    i = ( i & 0x33333333 ) + ( ( i >>> 2 ) & 0x33333333 ); // Put count of each 4 bits into those 4 bits
	    //System.out.println(toBinary(i) + "\t" + i + "\n" );
	    i = ( i + ( i >>> 4 ) ) & 0x0f0f0f0f; // Put count of each 8 bits into those 8 bits
	    //System.out.println(toBinary(i) + "\t" + i + "\n" );
	    i = i + ( i >>> 8 ); // Put count of each 8 bits into those 4 bits
	    //System.out.println(toBinary(i) + "\t" + i + "\n" );
	    i = i + ( i >>> 16 ); // Put count of each 8 bits into those 4 bits
	    //System.out.println(toBinary(i) + "\t" + i + "\n" );
	    i = i & 0x3f;
	    //System.out.println(toBinary(i) + "\t" + i + "\n" );
	    return i;
	}
	private static void swap(List<Integer> lst, int i, int j) {
		Integer temp = lst.get(i);
		lst.set(i, lst.get(j));
		lst.set(j, temp);
	}
	
	public static void main( String[] args ) {
		/*for ( int j = 21478300; j < 21478317 ; j ++ ) {
			int a = j >>> 16;
			int b = ( new Integer(j) ).hashCode();
			int c = b ^ a;
			System.out.println( j + "\t" + toBinary( j ) + "\t" + ( j % 10 ) + "\t" + toBinary( j & 1023 ) );
			System.out.println( c + "\t" + c % 16 + "\t" + ( c & 15 ) );
			
		}*/
		Random rand = new Random();
		List<Integer> lst = new ArrayList<>();
		for (int i = 0; i < 10 ; lst.add(i, rand.nextInt(100)),System.out.printf("number[%d]: %d\n", i, lst.get(i ++)));
		int index = 0;
		for (int i = 0; i < 10 ; i ++) {
			Integer num = lst.get(i);
			index = (num ^ index) %10;
			System.out.print(index + "\t");
			if ( lst.get(index) > num && index < num)
				swap(lst, i, index);
		}
		System.out.println("\n***********");
		for (int i = 0; i< 10; System.out.printf("number[%d]: %d\n", i, lst.get(i ++) ));
	}
	
}
