package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.Stack;
import data_structures.ArrayStack;
import data_structures.RootishArrayStack;

public class RootishArrayStackTester {
	
	private static boolean ArrayStack=false;
	
	private Stack<Integer> createStack(){
		Stack<Integer> S=null;
		if (ArrayStack) 
			S=new ArrayStack<>(Integer.class);
		else
			S=new RootishArrayStack<>(Integer.class);
		return S;
	}
	
	@Test
	public void test01(){
		Stack<Integer> S=createStack();
		assertEquals(S.size(),0);
	}
	
	@Test
	public void test02(){
		Stack<Integer> S=createStack();
		for (int i=0;i<10000;S.push((i++)*2));
		for (int i=9999;i>=0;assertEquals("that's a wrong answer",S.pop(),new Integer((i--)*2)));
	}
	
	@Test
	public void test03(){
		Stack<Integer> S=createStack();
		for (int i=0;i<10000;S.push((i++)*2));
		assertEquals("that's a wrong answer",S.peek(),new Integer(9999*2));
		assertTrue(S.size()==10000);
	}
	
	public void test04(){
		Stack<Integer> S=createStack();
		assertTrue(S.peek()==null);
	}
	
	public void test05(){
		Stack<Integer> S=createStack();
		assertTrue(S.pop()==null);
	}
	
	public void test06(){
		Stack<Integer> S=createStack();
		for (int i=0;i<10000;S.push(i++));
		for (int i=0;i<10000;S.pop(),i++);
		assertTrue(S.pop()==null);
	}
}
