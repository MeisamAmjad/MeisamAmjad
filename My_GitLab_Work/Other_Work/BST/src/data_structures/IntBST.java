package data_structures;

public class IntBST extends BST<Integer, Integer> {
	/**
	 * Builds a BST with all keys from 0 to n (mapping to themselves).
	 * For debugging purposes.
	 * @param n: The size of the tree.
	 */
	public IntBST( int n ) {
		super();
		this.root = TreeMaker01( 0, n );
		this.n = n;
	}
	
	private TreeNode<Integer,Integer> TreeMaker01( int lower, int upper ) {
		if ( lower >= upper )
			return null;
		
		int m = ( lower + upper ) / 2;
		return new TreeNode<Integer,Integer>( 2 * m, 2 * m + 1, TreeMaker01( lower, m ), TreeMaker01( m + 1, upper ) );
	}
	
}
