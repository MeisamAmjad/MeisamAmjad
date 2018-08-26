
package unit_test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Random;
import data_structures.MinHeap;
import data_structures.MinPriorityQueue;
import org.junit.Test;

public class MinHeapTester {

	@Test
	public void test01() { //Check the method size()
		MinPriorityQueue<String,Integer> H = new MinHeap<>();
		assertEquals(H.size(), 0);
	}
	
	@Test
	public void test02() { //Check the methods Clear() and Empty()
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		for (int i=0;i<100;i++) H.push(i+1, i);
		H.clear();
		assertTrue(" it should be True", H.empty());
	}
	
	@Test
	public void test03(){ //Check the method Peek()
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		Random rand = new Random();int max=500;int min=10;
		for (int i=0;i<199;i++) H.push(i,(int )(rand.nextInt((max - min) + 1) + min));
		H.push(2000, 0);
		assertEquals("It must be Zero", (int) H.peek(),2000);
	}
	
	
	@Test
	public void test04(){//Check the method Push()/Pop()
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		Random rand = new Random();int max=2000;int min=0;
		int[] pt=new int[4000];
		for (int i=0;i<4000;i++) {
			int p=(int )(rand.nextInt((max - min) + 1) + min);
			H.push(p,p);
			pt[i]=p;
		}
		Arrays.sort(pt);
		for (int i=0;i<4000;i++) {
			int min02=(int)H.pop();
			assertTrue("It must be"+pt[i], pt[i]==min02);
		} 
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void test05(){ //Check the method Peek() when the list is empty
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		Random rand = new Random();int max=50;int min=1;
		for (int i=0;i<10;i++) H.push(i,(int )(rand.nextInt((max - min) + 1) + min));
		H.clear();
		assertEquals((int) H.peek(), 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void test06(){//check the method Push() when having null entity
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		H.push(null, 10);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void test07(){//Check the method Pop() when the list is empty
		MinPriorityQueue<Integer, Integer> H=new MinHeap<>();
		H.pop();
	}
	

}
