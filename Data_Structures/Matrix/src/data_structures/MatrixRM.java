package data_structures;

import java.lang.reflect.Array;

/**
 * Implementing Matrix abstract by row-major order data structure
 * 
 * @author Meisam Amjad (amjadm@miamioh.edu)
 */
public class MatrixRM<elements_type extends Comparable <elements_type>> extends Matrix<elements_type> {
	private elements_type [] A;
	
	/**
	 * Constructor.  Should initialize all values to 0.
	 * @param num_rows
	 * @param num_cols
	 */
	@SuppressWarnings("unchecked")
	public MatrixRM(Class<? extends elements_type> cls,int num_rows, int num_cols) {
		super(num_rows, num_cols);
		
		this.A = (elements_type[]) Array.newInstance(cls,num_rows*num_cols);
		
	}
	
	/* (non-Javadoc)
	 * @see Matrix#get(int, int)
	 */
	@Override
	public elements_type get(int i, int j) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols()))
			throw new ArrayIndexOutOfBoundsException();
		return A[i*numCols()+j];
	}
	
	/* (non-Javadoc)
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, elements_type val) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols()))
			throw new ArrayIndexOutOfBoundsException();
		A[i*numCols()+j]=val;
	}
	
	/* (non-Javadoc)
	 * @see Matrix#find(int)
	 */
	@Override
	public int[] find(elements_type val) {
		for (int row=0;row<numRows();row++)
			for (int col=0;col<numCols();col++)
				if (val.compareTo(A[row*numCols()+col])==0) return new int[]{row,col};
		return null;
	}
}
