package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.SkiplistList;

public class SkiplistListTester {
	
	SkiplistList<Integer> L;
	
	@Test
	public void testAdd() throws Exception{
		L=new SkiplistList<>(100000);
		for(int i=0;i<100000;L.add(i, i++));
		int [] rands=new int[200];
		for ( int i=0;i<200; L.add(	i , rands[i++]=(int) (Math.random()*201)	)		);
		assertTrue(L.size()==100200);
		for (int i=0;i<200;assertTrue(L.get(i)==rands[i++]));
	}
	
	@Test
	public void testSet() throws Exception{
		L=new SkiplistList<>(100000);
		for(int i=0;i<100000;L.add(i, i++));
		int [] rands=new int[200];
		for ( int i=0;i<200; L.set(	rands[i]=(int) (Math.random()*201)	, rands[i++])		);
		assertTrue(L.size()==100000);
		for (int i=0;i<200;assertTrue(L.get(rands[i])==rands[i++]));
	}
	
	@Test
	public void testRemove() throws Exception{
		L=new SkiplistList<>(10000);
		for(int i=0;i<10000;L.add(i, i++));
		for ( int i=0;i<9000; L.remove(0),i++);
		assertTrue(L.size()==1000);
		for (int i=0;i<1000;assertTrue(L.get(i)==(i++ +9000))	);
		for (int i=0;i<1000;L.remove(),i++);
		assertTrue(L.size()==0);
	}
	
	@Test
	public void testClear() throws Exception{
		L=new SkiplistList<>(100000);
		for(int i=0;i<100000;L.add(i, i++));
	
		L.clear();
		assertTrue(L.size()==0);
	}
	
	@Test(expected=Exception.class)
	public void test04()throws Exception{
		L=new SkiplistList<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.get(999);
		L.get(1000);
	}
	
	@Test(expected=Exception.class)
	public void test05()throws Exception{
		L=new SkiplistList<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.set(999,8888);
		assertTrue(L.get(999)==8888);
		L.set(1000,888);
	}
	
	@Test(expected=Exception.class)
	public void test06()throws Exception{
		L=new SkiplistList<>(1000);
		for(int i=0;i<1000;L.add(i, i++));
		L.add(1000,8888);//Adds at the end of the List
		assertTrue(L.get(1000)==8888);
		L.add(1002,8888);
	}
	
	@Test
	public void testFindNode()throws Exception{
		SkiplistList<String> L=new SkiplistList<>(100);
		String[] words={"Dog","cat","cell","max","first","Second","wateR"};
		for(String word:words) L.add(word);
		assertTrue(L.in("wateR"));
		assertFalse(L.in("water"));
		assertTrue(L.in("Dog"));
		assertFalse(L.in("Cell"));
	}
	
	@Test
	public void chekDetails()throws Exception{
		SkiplistList<String> L=new SkiplistList<>(100);
		String[] words={"Dog","cat","cell","max","first","Second","wateR"};
		L.add("first");
		L.add("second");
		L.add("third");
		L.remove();
		L.remove();
		L.remove();
		System.out.println("end");
	}
}
