package unit_test;
import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.Sequence;
import data_structures.ArrayDeque;

public class ArrayaDequeTest {
	@Test
	public void test01() throws Exception{
		Sequence<String> S=new ArrayDeque<String>(String.class);
		String[] words={"a","b","c","d","e","f","g","h"};
		for(String word:words) S.add(word);
		
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		S.remove(2);
		words=new String[]{"a","b","d","e","f","g","h"};
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		S.add(4,"x");
		words=new String[]{"a","b","d","e","x","f","g","h"};
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		S.add(3,"y");
		words=new String[]{"a","b","d","y","e","x","f","g","h"};
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		S.add(4,"z");
		words=new String[]{"a","b","d","y","z","e","x","f","g","h"};
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		S.remove(0);
		words=new String[]{"b","d","y","z","e","x","f","g","h"};
		for (int i=0; i<S.size();assertTrue(S.get(i).equals(words[i++]))	);
		
		while (!S.isEmpty()) S.remove();
		assertTrue(S.isEmpty());
	}
	
	@Test(expected=Exception.class)
	public void test02() throws Exception{
		Sequence<String> S=new ArrayDeque<String>(String.class);
		String[] words={"a","b","c","d","e","f","g","h"};
		for(String word:words) S.add(word);
		S.add(9,"i");
		
	}
	@Test(expected=Exception.class)
	public void test03() throws Exception{
		Sequence<String> S=new ArrayDeque<String>(String.class);
		String[] words={"a","b","c","d","e","f","g","h"};
		for(String word:words) S.add(word);
		S.remove(9);
		
	}
}


