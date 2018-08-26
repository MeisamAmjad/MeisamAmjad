package data_structures;

public abstract class Matrix<elements_type extends Comparable <elements_type>> {
	
	private int n;
	private int m;
	
	public Matrix(int num_rows,int num_cols){
		this.n=num_rows;
		this.m=num_cols;
	}
	
	/**
	 * Return number of rows.
	 * @return Number of row.
	 */
	public int numRows(){
		return this.n;
	}
	
	/**
	 * Return number of cols.
	 * @return number of columns.
	 */
	public int numCols(){
		return this.m;
	}

	/**
	 * Return element on ith row, jth column (0 <= i <=numRows(), 0 <= j < numCols())
	 * @param i    Row index
	 * @param j    Column index
	 * @return     Value of this[row][column]
	 * @throws ArrayIndexOutOfBoundsException
	 */
	abstract public elements_type get(int i, int j);
	
	/**
	 * Return element on ith row, jth column (0 <= i <=numRows(), 0 <= j < numCols())
	 * @param i    Row index
	 * @param j    Column index
	 * @param val  Set this[i][j] to val
	 * @throws ArrayIndexOutOfBoundsException
	 */
	abstract public void set(int i, int j, elements_type val);
	
	/**
	 * Return the i, j such that A[i][j] == val.  Return null if not found.
	 * That is, return a 2 element array R such that A[R[0]][AR[1]] == val;
	 * @param i    Row index
	 * @param j    Column index
	 * @param val  Value being searchedfor
	 */
	abstract public int[] find(elements_type val);
}
