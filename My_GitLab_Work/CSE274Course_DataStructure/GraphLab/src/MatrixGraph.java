import java.util.ArrayList;


/**
 * @author karroje
 *
 */
public class MatrixGraph extends Graph {
	Double [][] A;
			
	public MatrixGraph(int n) {
		super(n);
		A=new Double[numNodes()][numNodes()];
	}

	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (i<0 || i>=num_nodes || j<0 || j>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		return A[i][j];
	}

	/* (non-Javadoc)
	 * @see Graph#setWeight(int, int, java.lang.Double)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		if (i<0 || i>=num_nodes || j<0 || j>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		A[i][j]=weight;
		A[j][i]=weight;
		num_edges+=weight;
	}

	/* (non-Javadoc)
	 * @see Graph#adjacentNodes(int)
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		if (i<0 || i>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		ArrayList<Pair<Integer,Double>> nodes = new ArrayList<>();
		for (int j = 0; j < num_nodes; j++)
			if (A[j][i]!=null) nodes.add(new Pair<>(j, A[i][j]));
		return nodes;
	}

	/* (non-Javadoc)
	 * @see Graph#degree(int)
	 */
	@Override
	int degree(int i) {
		if (i<0 || i>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		int count=0;
		for (int j = 0; j < num_nodes; j++)
			if (A[i][j]!=null) count++;
		return count;
	}

}
