package unit_test;

import data_structures.Matrix;
import data_structures.MatrixRM;
import data_structures.Array2D;
import static org.junit.Assert.*;
import org.junit.Test;

public class MatrixTester3 {
	static boolean useArray2D = false;
	
	private Matrix<String> create_matrix(int n, int m) {
		Matrix<String> M;
		if (useArray2D)
			M = new Array2D<String>(String.class,n,m);
		else 
			M = new MatrixRM<String>(String.class,n,m);
		return M;
	}
	
	private void initiate(Matrix<String> A,String value){
		for (int i=0; i < A.numRows(); i++)
			for (int j=0; j < A.numCols(); A.set(i, j++, new String(value)));
	}
	
	@Test
	public void test01() {
		Matrix<String> M = create_matrix(10,15);
		assertEquals(M.numRows(),10);
	}
	
	@Test
	public void test02() {
		Matrix<String> M = create_matrix(11,17);
		assertEquals(M.numCols(), 17);
	}
	
	@Test 
	public void test03() {
		int n=5, m=7;
		Matrix<String> M = create_matrix(n,m);
		initiate(M,"");
		
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				assertEquals(M.get(i, j), new String(""));
			}
	}
	
	
	@Test 
	public void test04() {
		int n=12, m=14;
		Matrix<String> M = create_matrix(n,m);
		initiate(M,"Shahrooz");
		M.set(2, 3, "shahrooz");
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				String v = M.get(i, j);
				if (i==2 && j==3)
					assertEquals(v, new String("shahrooz"));
				else
					assertEquals(v, new String("Shahrooz"));
			}
	}
	
	@Test 
	public void test05() {
		int n=2, m=3;
		Matrix<String> M = create_matrix(n,m);
		initiate(M,"");
		M.set(0, 0, "For Test");
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				String v = M.get(i, j);
				if (i==0 && j==0)
					assertEquals(v, new String("For Test"));
				else
					assertEquals(v, new String(""));
			}
	}
	
	@Test 
	public void test06() {
		int n=17, m=15;
		Matrix<String> M = create_matrix(n,m);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; M.set(i, j++, new String("")));
		M.set(14, 6, "12");
		M.set(6, 14, "103");
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				String v = M.get(i, j);
				if (i==14 && j==6)
					assertEquals(v, new String("12"));
				else if (i==6 && j==14)
					assertEquals(v, new String("103"));
				else
					assertEquals(v, new String(""));
			}
			
	}
	
	@Test
	public void test07() {
		int n=9, m=9;
		Matrix<String> M = create_matrix(n,m);
		initiate(M," ");
		M.set(5, 7, "  ");;
		int[] R = M.find("  ");
		assertTrue(R[0] == 5 && R[1] == 7);
	}
	
	@Test
	public void test08() {
		int n=5, m=4;
		Matrix<String> M = create_matrix(n,m);
		initiate(M,"");
		M.set(1, 2, "12345.0");
		int[] R = M.find("12345.0");
		assertTrue(R[0] == 1 && R[1] == 2);
	}
	
	@Test
	public void test09() {
		int n=12, m=13;
		Matrix<String> M = create_matrix(n,m);
		initiate(M,"eE eE");
		M.set(11, 12, "ee eE");
		int[] R = M.find("ee eE");
		assertTrue(R[0] == 11 && R[1] == 12);
	}
	
	@Test
	public void test10() {
		int n=21, m = 8;
		Matrix<String> M = create_matrix(n,m);
		initiate(M," ");
		assertNull(M.find(""));
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test11() {
		Matrix<String> M = create_matrix(3,3);
		M.set(-1, 0, "");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test12() {
		Matrix<String> M = create_matrix(3,3);
		M.set(0, -1, "");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test13() {
		Matrix<String> M = create_matrix(3,3);
		M.set(3, 0, "");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test14() {
		Matrix<String> M = create_matrix(3,3);
		M.set(0, 3, "");
	}
		
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test15() {
		Matrix<String> M = create_matrix(3,3);
		M.set(0, 4, " ");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test16() {
		Matrix<String> M = create_matrix(3,3);
		M.get(-1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test17() {
		Matrix<String> M = create_matrix(3,3);
		M.get(0, -1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test18() {
		Matrix<String> M = create_matrix(3,3);
		M.get(3, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test19() {
		Matrix<String> M = create_matrix(3,3);
		M.get(0, 3);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test20() {
		Matrix<String> M = create_matrix(3,3);
		M.get(6, 0);
	}

}
