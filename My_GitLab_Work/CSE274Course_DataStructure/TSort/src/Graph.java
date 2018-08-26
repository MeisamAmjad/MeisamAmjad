import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Undirected Graph class.
 * @author karroje
 *
 */

public abstract class Graph {
	protected int num_nodes;
	public int num_edges;
	private ArrayList<String> node2name;
	private Hashtable<String,Integer> name2node;
	
	public Graph(int n) {
		num_nodes = n;
		node2name = new ArrayList<>();
		for (int i=0; i < n; i++)
			node2name.add(null);
		name2node = new Hashtable<>();
	}
	
	/**
	 * Return number of nodes in graph.
	 * @return
	 */
	int numNodes() {
		return num_nodes;
	}
	
	/**
	 * Return number of edges in graph.
	 * @return
	 */
	int numEdges() {
		return num_edges;
	}
	
	/**
	 * Assign a node a name.
	 * @param i
	 * @param s
	 */
	public void addName(int i, String s) {
		node2name.set(i,s);
		name2node.put(s,i);
	}
	
	/**
	 * Get name of node i
	 * @param i
	 * @return
	 */
	public String getName(int i) {
		return node2name.get(i);
	}
	
	/**
	 * Is the name a name of a node?
	 * @param name
	 * @return
	 */
	public boolean isName(String name) {
		return name2node.get(name) != null;
	}

	/**
	 * Return weight of edge between node i to node j, or null if it doesn't exist..
	 * @param i
	 * @param j
	 * @return double
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract Double getWeight(int i, int j);
	
	/**
	 * Get weight of edge using node names.  (Node names must be created first.)
	 * @param u
	 * @param v
	 * @return
	 */
	Double getWeight(String u, String v) {
		return getWeight(name2node.get(u), name2node.get(v));
	}
	
	
	/** 
	 * Set weight of edge  from node i to node j.
	 * @param i
	 * @param j
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract void setWeight(int i, int j, Double weight);
	
	/**
	 * Set weight of edge by node name.  (Node names must be created first.) 
	 * @param u
	 * @param v
	 * @param weight
	 */
	void setWeight(String u, String v, Double weight) {
		setWeight(name2node.get(u), name2node.get(v), weight);
	}
	
	/**
	 * Return a list of all node adjacent to i.
	 * @param i
	 * @ return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract ArrayList<Pair<Integer,Double>> adjacentNodes(int i);
	
	/**
	 * Call adjacentNodes by node name.
	 * @param u
	 * @return
	 */
	ArrayList<Pair<Integer,Double>> adjacentNodes(String u) {
		return adjacentNodes(name2node.get(u));
	}
	
	/**
	 * Return the degree of node i (the number of edge going "out" of i).
	 * @return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract int degree(int i);
	
	/**
	 * Call degree by node name.
	 * @param u
	 * @return
	 */
	int degree(String u) {
		return degree(name2node.get(u));
	}

}
