
/**
 * Lab 2:Matrices
 * CSE274
 * @author Meisam Amjad (amjadm@miamioh.edu)
 * 
 */

/**
 * @author karroje
 *
 */
public class Array2D extends Matrix {
	public int[][] A;
	
	/**
	 * Constructor.  Should initialize all values to 0.
	 * @param num_rows
	 * @param num_cols
	 */
	public Array2D(int num_rows, int num_cols) {
		
		super(num_rows, num_cols);
	
		A = new int[num_rows][];
		for (int i=0; i < num_rows; i++){
			A[i] = new int[num_cols];
			for(int j=0; j<num_cols; j++){
				A[i][j]=0;
			};
		}	
	}
	
	
	

	/* (non-Javadoc)
	 * @see Matrix#get(int, int)
	 */
	@Override
	public int get(int i, int j) throws ArrayIndexOutOfBoundsException {
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols())){
			throw new ArrayIndexOutOfBoundsException();
		}
		return A[i][j];
	}

	/* (non-Javadoc)
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, int val) throws ArrayIndexOutOfBoundsException{
		if (!(i>=0 && i<numRows() && j>=0 && j<numCols())){
			throw new ArrayIndexOutOfBoundsException();
		}
		A[i][j]=val;
	
	}
	
	/* (non-Javadoc)
	 * @see Matrix#find(int)
	 */
	public int[] find(int val) {
		int [] result=new int[2];
		for (int row=0;row<numRows();row++){
			for (int col=0;col<numCols();col++){
				if(A[row][col]==val){
					result[0]=row;
					result[1]=col;
					return result;
				}
			}
		}
		return null;
	}
	
}
