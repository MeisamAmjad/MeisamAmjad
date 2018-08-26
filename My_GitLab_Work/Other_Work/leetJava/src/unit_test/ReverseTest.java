package unit_test;

import reversDLList.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.junit.Test;

public class ReverseTest {
	
	@Test
	public void SLListTest(){
		LinkedList<String> sLList=new LinkedList<>();
		String[] words={"a","b","c","d","e","f","g","h"};
		sLList.addAll(Arrays.stream(words).collect(Collectors.toList()));
		
		for (int i=0;i<sLList.size();assertTrue(sLList.get(i).compareTo(words[i++])==0) );
		assertEquals(words.length,sLList.size());
		assertTrue(sLList.peekLast().compareTo("h")==0);
		//Checking Reverse method
	}
	
	@Test
	public void DLListTest()
					throws Exception{
		DLList<String> dl=new DLList<>();
		String[] words={"a","b","c","d","e","f","g","h"};
		for (String word:words) dl.push_back(word);
		for (int i=0;i<dl.size();assertTrue(dl.get(i).compareTo(words[i++])==0) );
		assertEquals(words.length,dl.size());
		assertTrue(dl.get(dl.size()-1).compareTo("h")==0);
		//Checking the Reverse method
		Revers.reverseDLList(dl.dummy);
		for (int i=0;i<dl.size();assertTrue(dl.pop().compareTo(words[i++])==0) );
	}
	
}
