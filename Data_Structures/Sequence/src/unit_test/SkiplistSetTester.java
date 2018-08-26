package unit_test;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;

import data_structures.SkiplistSet;

public class SkiplistSetTester {
	
	SkiplistSet<Integer> L;
	

	@Test
	public void testTime02() throws Exception{

		long startTime2=0,
				endTime2=0;
		
		startTime2=System.nanoTime();
		
		
		int [] rands2=new int[10000];
		L=new SkiplistSet<>(300000);
		for(int i=0;i<20000;L.add(i++));
		boolean in2=true;
		for ( int i=0;i<10000;i++){
			do{
				rands2[i]=(int) (20000+Math.random()*10001);
				in2=L.in(rands2[i]);
			}while(in2);
			L.add(rands2[i]); 
		}
		assertTrue(L.size()==30000);
		
		
		endTime2=System.nanoTime();
		System.out.println((double)(endTime2-startTime2)/1000000);
		
		
		
		long startTime=0,
				endTime=0;
		
		startTime=System.nanoTime();
		
		
		int [] rands=new int[10000];
		int[] arrayCheck=new int[30000];
		for(int i=0;i<20000;arrayCheck[i]=i++);
		boolean in=false;
		for ( int i=0;i<10000;i++){
			do{
				in=false;
				rands[i]=(int) (20000+Math.random()*10001);
				for (int j=0;j<30000;j++) if (arrayCheck[j]==rands[i]) {in=true;break;}
			}while(in);
			arrayCheck[i+20000]=rands[i];
		}
		assertTrue(arrayCheck.length==30000);
		Arrays.sort(arrayCheck);
		
		
		endTime=System.nanoTime();
		System.out.println((double)(endTime-startTime)/1000000);
		//for(int i=0;i<30000;assertTrue(L.get(i).equals(arrayCheck[i++])));
		
		L.clear();
		for(int i=arrayCheck.length-1; i>=0;L.addHelper(arrayCheck[i--]));
		assertTrue(arrayCheck.length==L.size());
		for(int i=0;i<L.size();assertTrue(L.get(i).equals(arrayCheck[i++])));
		
		int count=0,
				orgSize=L.size();
		for(int i=0; i<30000;i+=3){
			L.remove(new Integer(i));
			count++;
		}
		assertTrue(orgSize-count==L.size());
		int[] A=new int[L.size()];
		for (int i=L.size()-1;i>=0;A[i]=L.get(i--));
		Arrays.sort(A);
		for(int i=0;i<L.size();assertTrue(L.get(i).equals(A[i++])));
	}
	
	/*
	@Test
	public void testSet() throws Exception{
		L=new SkiplistSet<>(100000);
		for(int i=0;i<100000;L.add(i, i++));
		int [] rands=new int[200];
		for ( int i=0;i<200; L.set(	rands[i]=(int) (Math.random()*201)	, rands[i++])		);
		assertTrue(L.size()==100000);
		for (int i=0;i<200;assertTrue(L.get(rands[i])==rands[i++]));
	}
	
	@Test
	public void testRemove() throws Exception{
		L=new SkiplistSet<>(10000);
		for(int i=0;i<10000;L.add(i, i++));
		for ( int i=0;i<9000; L.remove(0),i++);
		assertTrue(L.size()==1000);
		for (int i=0;i<1000;assertTrue(L.get(i)==(i++ +9000))	);
		for (int i=0;i<1000;L.remove(),i++);
		assertTrue(L.size()==0);
	}
	
	@Test
	public void testClear() throws Exception{
		L=new SkiplistSet<>(100000);
		for(int i=0;i<100000;L.add(i, i++));
	
		L.clear();
		assertTrue(L.size()==0);
	}
	
	@Test(expected=Exception.class)
	public void test04()throws Exception{
		L=new SkiplistSet<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.get(999);
		L.get(1000);
	}
	
	@Test(expected=Exception.class)
	public void test05()throws Exception{
		L=new SkiplistSet<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.set(999,8888);
		assertTrue(L.get(999)==8888);
		L.set(1000,888);
	}
	
	@Test(expected=Exception.class)
	public void test06()throws Exception{
		L=new SkiplistSet<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.add(1000,8888);//Adds at the end of the List
		assertTrue(L.get(1000)==8888);
		L.add(1002,8888);
	}
	
	@Test
	public void testFindNode()throws Exception{
		SkiplistSet<String> L=new SkiplistSet<>(100);
		String[] words={"Dog","cat","cell","max","first","Second","wateR"};
		for(String word:words) L.add(word);
		assertTrue(L.in("wateR"));
		assertFalse(L.in("water"));
		assertTrue(L.in("Dog"));
		assertFalse(L.in("Cell"));
	}
	
	@Test
	public void chekDetails()throws Exception{
		SkiplistSet<String> L=new SkiplistSet<>(100);
		String[] words={"Dog","cat","cell","max","first","Second","wateR"};
		L.add("first");
		L.add("second");
		L.add("third");
		L.remove();
		L.remove();
		L.remove();
		System.out.println("end");
	}*/
}
