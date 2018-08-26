import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class TopologicalSortTester {

	// Check that the array has all elements from 0 to size()-1
	boolean hasAllElements(ArrayList<Integer> A) {
		ArrayList<Integer> B = (ArrayList<Integer>) A.clone();
		Collections.sort(B);
		for (int i=0; i < B.size(); i++) {
			if (!B.get(i).equals(new Integer(i))) {
				return false;
			}
		}
				
		return true;
	}
	
	// Return the index of an element in the array (or -1 if not present)
	boolean checkSort(ArrayList<Integer> A, Graph G) {
		for (int i=0; i < A.size(); i++)
			for (int j=i+1; j < A.size(); j++)
				if (G.getWeight(A.get(j), A.get(i)) != null)
					return false;
		return true;
	}
	
	@Test
	public void test1() {
		Graph G = new ListGraph(2);
		G.setWeight(0, 1, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	@Test
	public void test2() {
		Graph G = new ListGraph(2);
		G.setWeight(1, 0, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	@Test
	public void test3() {
		Graph G = new ListGraph(3);
		G.setWeight(0, 1, 1.0);
		G.setWeight(0, 2, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	@Test
	public void test4() {
		Graph G = new ListGraph(3);
		G.setWeight(1, 2, 1.0);
		G.setWeight(1, 0, 1.0);
		G.setWeight(2, 0, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	@Test
	public void test5() {
		Graph G = new ListGraph(6);
		G.setWeight(5, 4, 1.0);
		G.setWeight(5, 3, 1.0);
		G.setWeight(4, 2, 1.0);
		G.setWeight(2, 1, 1.0);
		G.setWeight(4, 3, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	@Test
	public void test6() {
		Graph G = new ListGraph(6);
		G.setWeight(5, 4, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), G.numNodes());
		assertTrue(hasAllElements(A));
		assertTrue(checkSort(A,G));
	}
	
	// EXTRA CREDIT -- no penalty if it fails.
	@Test
	public void test7() {
		Graph G = new ListGraph(4);
		G.setWeight(1, 2, 1.0);
		G.setWeight(2, 3, 1.0);
		G.setWeight(3, 1, 1.0);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		assertEquals(A.size(), 0);
	}


}
