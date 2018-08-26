package unit_test;



import data_structures.ArrayQueue;
import data_structures.Sequence;

import static org.junit.Assert.*;
import org.junit.Test;


public class ArrayQueueTest {
	@Test
	public void test01() throws Exception{
		Sequence<Integer> S=new ArrayQueue<>(Integer.class);
		for(int i=0;i<100;S.add(i++));
		for(int i=0;i<100;assertTrue(S.remove()==(i++)));
	}
	
	@Test
	public void test02() throws Exception{
		ArrayQueue<String> S=new ArrayQueue<>(String.class);
		for(int i=100;i<200;S.add(String.valueOf(i++)));
		for(int i=1;i<51;S.remove(),i++);
		//Size of the Array is 128 so far.
		for(int i=1;i<79;S.add(String.valueOf(i++)));
		assertTrue("the size is not correct",S.A.length==128 && S.size()==128 && S.head==50);// It still has the size of 128 and 128 item inside
		S.add("nnn");
		assertTrue("the size is not correct",S.A.length==128*2 && S.size()==129 && S.head==0);
		S.remove();
		assertTrue(S.head==1);
	}
}
