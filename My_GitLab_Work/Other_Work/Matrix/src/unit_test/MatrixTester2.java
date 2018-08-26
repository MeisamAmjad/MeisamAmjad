package unit_test;

import data_structures.Matrix;
import data_structures.MatrixRM;
import data_structures.Array2D;
import static org.junit.Assert.*;
import org.junit.Test;

public class MatrixTester2 {
	static boolean useArray2D = false;
	
	private Matrix<Integer> create_matrix(int n, int m) {
		Matrix<Integer> M;
		if (useArray2D)
			M = new Array2D<Integer>(Integer.class,n,m);
		else 
			M = new MatrixRM<Integer>(Integer.class,n,m);
		return M;
	}
	
	private void initiate(Matrix<Integer> A,Integer value){
		for (int i=0; i < A.numRows(); i++)
			for (int j=0; j < A.numCols(); A.set(i, j++, new Integer(value)));
	}
	
	@Test
	public void test01() {
		Matrix<Integer> M = create_matrix(10,15);
		assertEquals(M.numRows(),10);
	}
	
	@Test
	public void test02() {
		Matrix<Integer> M = create_matrix(11,17);
		assertEquals(M.numCols(), 17);
	}
	
	@Test 
	public void test03() {
		int n=5, m=7;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				assertEquals(M.get(i, j), new Integer(0));
			}
	}
	
	
	@Test 
	public void test04() {
		int n=12, m=14;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,100);
		M.set(2, 3, 10);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				Integer v = M.get(i, j);
				if (i==2 && j==3)
					assertEquals(v, new Integer(10));
				else
					assertEquals(v, new Integer(100));
			}
	}
	
	@Test 
	public void test05() {
		int n=2, m=3;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		M.set(0, 0, 42);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				Integer v = M.get(i, j);
				if (i==0 && j==0)
					assertEquals(v, new Integer(42));
				else
					assertEquals(v, new Integer(0));
			}
	}
	
	@Test 
	public void test06() {
		int n=17, m=15;
		Matrix<Integer> M = create_matrix(n,m);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; M.set(i, j, new Integer(j++)));
		M.set(14, 6, 12);
		M.set(6, 14, 103);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				Integer v = M.get(i, j);
				if (i==14 && j==6)
					assertEquals(v, new Integer(12));
				else if (i==6 && j==14)
					assertEquals(v, new Integer(103));
				else
					assertEquals(v, new Integer(j));
			}
			
	}
	
	@Test
	public void test07() {
		int n=9, m=9;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		M.set(5, 7, 13);;
		int[] R = M.find(13);
		assertTrue(R[0] == 5 && R[1] == 7);
	}
	
	@Test
	public void test08() {
		int n=5, m=4;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		M.set(1, 2, 12345);
		int[] R = M.find(12345);
		assertTrue(R[0] == 1 && R[1] == 2);
	}
	
	@Test
	public void test09() {
		int n=12, m=13;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		M.set(11, 12, 2);
		int[] R = M.find(2);
		assertTrue(R[0] == 11 && R[1] == 12);
	}
	
	@Test
	public void test10() {
		int n=21, m = 8;
		Matrix<Integer> M = create_matrix(n,m);
		initiate(M,0);
		assertNull(M.find(1));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test11() {
		Matrix<Integer> M = create_matrix(3,3);
		M.set(-1, 0, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test12() {
		Matrix<Integer> M = create_matrix(3,3);
		M.set(0, -1, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test13() {
		Matrix<Integer> M = create_matrix(3,3);
		M.set(3, 0, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test14() {
		Matrix<Integer> M = create_matrix(3,3);
		M.set(0, 3, 1);
	}
		
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test15() {
		Matrix<Integer> M = create_matrix(3,3);
		M.set(0, 4, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test16() {
		Matrix<Integer> M = create_matrix(3,3);
		M.get(-1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test17() {
		Matrix<Integer> M = create_matrix(3,3);
		M.get(0, -1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test18() {
		Matrix<Integer> M = create_matrix(3,3);
		M.get(3, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test19() {
		Matrix<Integer> M = create_matrix(3,3);
		M.get(0, 3);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test20() {
		Matrix<Integer> M = create_matrix(3,3);
		M.get(6, 0);
	}

}
