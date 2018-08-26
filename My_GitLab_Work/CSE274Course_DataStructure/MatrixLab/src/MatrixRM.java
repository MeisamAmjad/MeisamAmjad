/**
 * Lab 2:Matrices
 * CSE274
 * @author Meisam Amjad (amjadm@miamioh.edu)
 */

/**
 * @author karroje
 *
 */
public class MatrixRM extends Matrix {
	int [] A;
	
	/**
	 * Constructor.  Should initialize all values to 0.
	 * @param num_rows
	 * @param num_cols
	 */
	public MatrixRM(int num_rows, int num_cols) {
		super(num_rows, num_cols);
		
		A = new int[num_rows*num_cols];
		for(int row=0;row<num_rows*num_cols-1;row++){
			A[row]=0;
		}
	}

	/* (non-Javadoc)
	 * @see Matrix#get(int, int)
	 */
	@Override
	public int get(int i, int j) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols())){
			throw new ArrayIndexOutOfBoundsException();
		}
		return A[i*numCols()+j];
	}

	/* (non-Javadoc)
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, int val) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols())){
			throw new ArrayIndexOutOfBoundsException();
		}
		A[i*numCols()+j]=val;
	}
	
	/* (non-Javadoc)
	 * @see Matrix#find(int)
	 */
	public int[] find(int val) {
		
		int[] result=new int[2];
		for (int row=0;row<numRows();row++){
			for (int col=0;col<numCols();col++){
				if (A[row*numCols()+col]==val){
					result[0]=row;
					result[1]=col;
					return result;
				}
			}
		}
		return null;
	}

}
