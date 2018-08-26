package data_structures;

import java.lang.reflect.Array;

/**
 * Implementing Matrix abstract by 2D Array data structure
 * 
 * @author Meisam Amjad (amjadm@miamioh.edu)
 * 
 */
public class Array2D<elements_type extends Comparable <elements_type>> extends Matrix<elements_type > {
	private elements_type[][] A;
	
	/**
	 * Constructor.  Initialize all values with null.
	 * @param num_rows
	 * @param num_cols
	 */
	@SuppressWarnings("unchecked")
	public Array2D(Class <? extends elements_type> cls,int num_rows, int num_cols) {
		super(num_rows, num_cols);
		this.A= (elements_type[][]) Array.newInstance(cls, numRows(), numCols());
	}
	
	/* (non-Javadoc)
	 * @see Matrix#get(int, int)
	 */
	@Override
	public elements_type get(int i, int j) throws ArrayIndexOutOfBoundsException {
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols()))
			throw new ArrayIndexOutOfBoundsException();
		return A[i][j];
	}
	
	/* (non-Javadoc)
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, elements_type val) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols()))
			throw new ArrayIndexOutOfBoundsException();
		this.A[i][j]=val;
	
	}
	
	/* (non-Javadoc)
	 * @see Matrix#find(int)
	 */
	@Override
	public int[] find(elements_type val) {
		for (int row=0;row<numRows();row++)
			for (int col=0;col<numCols();col++)
				if(val.compareTo(A[row][col])==0)return new int[]{row,col};
		return null;
	}
	
}