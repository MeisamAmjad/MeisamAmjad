import java.util.ArrayList;

/**
 * @author karroje
 *
 */
public class ListGraph extends Graph {
	ArrayList<Pair<Integer, Double>>[] A;
	
	@SuppressWarnings("unchecked")
	public ListGraph(int n) {
		super(n);
		A=(ArrayList<Pair<Integer, Double>>[]) new ArrayList[n];
		for (int i=0;i<n;i++) A[i]=new ArrayList<Pair<Integer, Double>>();
	}


	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (i<0 || i>=num_nodes || j<0 || j>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		Double result=null;
		//Iterator<Pair<Integer, Double>> el=A[i].iterator();
		for (Pair<Integer, Double> el:A[i]) 
			if(el.first==j) result=el.second;
		return result;
	}



	/* (non-Javadoc)
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		if (i<0 || i>=num_nodes || j<0 || j>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		A[i].add(new Pair<Integer, Double>(j, weight));
		num_edges++;
	}
		
	/* (non-javadoc)
	 * @see Graph#AdjacentNodes
	 */
	ArrayList<Pair<Integer,Double>> adjacentNodes(int i) {
		if (i<0 || i>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		return A[i];
		
	}
	 
	/* (non-javadoc)
	 * @see Graph#degree
	 */
	int degree(int i) {
		if (i<0 || i>=num_nodes) throw new IndexOutOfBoundsException("It's out of bound");
		int count=0;
		for (int j = 0; j < num_nodes; j++)
			for (Pair<Integer, Double> el:A[j])
				if (el.first==i) count++;
		return count;
	}

}
