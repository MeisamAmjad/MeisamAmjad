import org.junit.Test;

public class MatrixTest2 {

	static boolean useArray2D = true;
	
	Matrix create_matrix(int n, int m) {
		Matrix M;
		if (useArray2D)
			M = new Array2D(n,m);
		else 
			M = new MatrixRM(n,m);
		return M;
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test01() {
		Matrix M = create_matrix(3,3);
		M.set(-1, 0, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test02() {
		Matrix M = create_matrix(3,3);
		M.set(0, -1, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test03() {
		Matrix M = create_matrix(3,3);
		M.set(3, 0, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test04() {
		Matrix M = create_matrix(3,3);
		M.set(0, 3, 1);
	}
		
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test05() {
		Matrix M = create_matrix(3,3);
		M.set(0, 4, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test06() {
		Matrix M = create_matrix(3,3);
		M.get(-1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test07() {
		Matrix M = create_matrix(3,3);
		M.get(0, -1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test08() {
		Matrix M = create_matrix(3,3);
		M.get(3, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test09() {
		Matrix M = create_matrix(3,3);
		M.get(0, 3);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test10() {
		Matrix M = create_matrix(3,3);
		M.get(6, 0);
	}

}
