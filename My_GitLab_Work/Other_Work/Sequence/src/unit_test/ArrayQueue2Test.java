package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.ArrayQueue2;
//import data_structures.Sequence;

public class ArrayQueue2Test {
	
	@Test//(expected=Exception.class)
	public void test01() throws Exception{
		ArrayQueue2<Integer> S=new ArrayQueue2<>(Integer.class);
		for(int i=0;i<10;S.add(i++));
		
		S.add(0,888);
		
		for(int i=0;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
		
		assertTrue(S.remove()==888);
		
		for(int i=0;i<10;assertTrue(S.remove()==(i++)));
		for(int i=1;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
		
		for(int i=0;i<10;S.add(i++));
		for(int i=0;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
		
		
		for (int i=1; i<=4;S.add(i, (i++)*1000));
		for(int i=0;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
		
		S.add(10,1400);
		S.add(9,999);
		
		for(int i=0;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
		
		S.remove(9);
		S.remove(10);
		for(int i=0;i<S.A.length;i++)System.out.print((S.A[i]!=null)?S.A[i]+"\t":null+"\t");
		System.out.println("\nlength= "+S.A.length+"\tn= "+S.size()+"\thead= "+S.head);
	}

}

;