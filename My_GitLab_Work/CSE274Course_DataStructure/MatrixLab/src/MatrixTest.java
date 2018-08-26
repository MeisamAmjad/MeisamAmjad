import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {

	static boolean useArray2D = true;
	
	Matrix create_matrix(int n, int m) {
		Matrix M;
		if (useArray2D)
			M = new Array2D(n,m);
		else 
			M = new MatrixRM(n,m);
		return M;
	}
	
	@Test
	public void test01() {
		Matrix M = create_matrix(10,15);
		assertEquals(M.numRows(),10);
	}
	
	@Test
	public void test02() {
		Matrix M = create_matrix(11,17);
		assertEquals(M.numCols(), 17);
	}
	
	@Test 
	public void test03() {
		int n=5, m=7;
		Matrix M = create_matrix(n,m);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				assertEquals(M.get(i, j), 0);
			}
	}
	
	
	@Test 
	public void test04() {
		int n=12, m=14;
		Matrix M = create_matrix(n,m);
		M.set(2, 3, 10);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				int v = M.get(i, j);
				if (i==2 && j==3)
					assertEquals(v, 10);
				else
					assertEquals(v, 0);
			}
	}
	
	@Test 
	public void test05() {
		int n=2, m=3;
		Matrix M = create_matrix(n,m);
		M.set(0, 0, 42);
		for (int i=0; i < n; i++)
			for (int j=0; j < m; j++) {
				int v = M.get(i, j);
				if (i==0 && j==0)
					assertEquals(v, 42);
				else
					assertEquals(v, 0);
			}
	}
	
	@Test 
	public void test06() {
		int n=17, m=15;
		Matrix M = create_matrix(n,m);
		M.set(14, 6, 12);
		M.set(6, 14, 103);
		for (int i=0; i < n; i++){
			for (int j=0; j < m; j++) {
				int v = M.get(i, j);
				if (i==14 && j==6)
					assertEquals(v, 12);
				else if (i==6 && j==14)
					assertEquals(v, 103);
				else
					assertEquals(v, 0);
			}
		}	
	}
	
	@Test
	public void test07() {
		int n=9, m=9;
		Matrix M = create_matrix(n,m);
		M.set(5, 7, 13);;
		int[] R = M.find(13);
		assertTrue(R[0] == 5 && R[1] == 7);
	}
	
	@Test
	public void test08() {
		int n=5, m=4;
		Matrix M = create_matrix(n,m);
		M.set(1, 2, 12345);;
		int[] R = M.find(12345);
		assertTrue(R[0] == 1 && R[1] == 2);
	}
	
	@Test
	public void test09() {
		int n=12, m=13;
		Matrix M = create_matrix(n,m);
		M.set(11, 12, 2);;
		int[] R = M.find(2);
		assertTrue(R[0] == 11 && R[1] == 12);
	}
	
	@Test
	public void test10() {
		int n=21, m = 8;
		Matrix M = create_matrix(n,m);
		assertNull(M.find(1));
	}
}
