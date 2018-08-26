/**
 * 
 */
package data_structures;
import java.lang.IndexOutOfBoundsException;



/**
 * @author karroje
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K,V> root;
	
	/**
	 * 
	 */
	public BST() {
		super();
		root = null;
	}
	
	/**
	 * Compare two keys and increment numOps.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * @return
	 */
	public TreeNode<K,V> getRoot() {
		return root;
	}

	

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		TreeNode<K, V> u=findNode(key); 
		addChild(u, new TreeNode<>(key, value));
	}
	
	private TreeNode<K, V> findNode (K key){
		TreeNode<K, V> w = getRoot(), prev = null;
		while (w != null) {
			prev = w;
		    int comp = compareKeys(key, w.key);
		    if (comp < 0) 
		    	w = w.left;
		    else if (comp > 0) 
		    	w = w.right;
		    else return w;
		}
		return prev;
	}

	private void addChild(TreeNode<K, V> p, TreeNode<K,V> u) {
		if (p == null) 
			root = u;              // inserting into empty tree
		else {
			int comp = compareKeys(u.key, p.key);
		    if (comp < 0) 
		    	p.left = u;
		    else if (comp > 0) 
		    	p.right = u;
		    else 
		    	throw new IndexOutOfBoundsException("Duplication is not allowed");
		}
		this.n++;
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) {
		TreeNode<K, V> prev=findprev(key),u;
		//System.out.println("key= "+key);
		if (prev==null) u=getRoot();
		else{	
			//System.out.println("prev.left.key: "+prev.left.key+"\t The Key: "+key);
			if ( (prev.left!=null) && compareKeys(prev.left.key, key)==0) u=prev.left;
			else u=prev.right;
		}
		//else throw new IndexOutOfBoundsException("There is no such a key");
		
		if (u.left == null || u.right == null) {
			splice(u,prev);
		}
		else {
			TreeNode<K, V> w = u.right;
			prev=u;
			while (w.left != null) {
				prev=w;
				w = w.left;
			}
			u.value = w.value;
			u.key=w.key;
			splice(w,prev);
		}
	}
	
	private TreeNode<K, V> findprev(K key){
		TreeNode<K, V> w = getRoot(), prev = null;
		while (w != null) {
			int comp = compareKeys(key, w.key);
			if (comp==0) return prev;
			prev = w;
		    if (comp < 0) 
		    	w = w.left;
		    else if (comp > 0) 
		    	w = w.right;
		}
		return prev;
	}
	
	private void splice(TreeNode<K, V> u, TreeNode<K, V> prev) {
		TreeNode<K,V> s, p;
		if (u.left != null) 
			s = u.left;
		else 
			s = u.right;
		if (u == getRoot()) {
			this.root=s;
			p = null;
		} else {
			p = prev;
			if (p.left == u) 
				p.left = s;
			else 
				p.right = s; 
		}
		if (s != null) prev = p;
		this.n--; 
	}

	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		return findHelper(getRoot(), key);
	}
	private V findHelper(TreeNode<K , V> r, K key){
		if (r==null) return null;
		else if (r.key.equals(key)) return r.value;
		else if (compareKeys(key, r.key)<0) return findHelper(r.left, key);
		else return findHelper(r.right, key);
	}
	
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K min() {
		TreeNode<K, V> w=getRoot();
		while (w.left!=null) w=w.left;
		return w.key;
	}
	
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K max() {
		TreeNode<K, V> w=getRoot();
		while (w.right!=null) w=w.right;
		return w.key;
	}
	
	
	/**
	 * Return the height of the tree.  
	 * Definition:
	 *    The *depth* of a node is number of edges from the root to that node.
	 *    The *height* of a tree is equal to the depth of the node with the
	 *        greatest depth of all the nodes.
	 * @return int
	 */
	public int height() {
		return heightHelper(getRoot());
	}
	
	private int heightHelper(TreeNode<K, V> r){
		if (r==null) return -1;
		return 1+Math.max(heightHelper(r.left),heightHelper(r.right));
	}
	
	
	boolean isBSTHelper(TreeNode<K,V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0) || (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;
		
		return isBSTHelper(root.left, min_value, root.key) && isBSTHelper(root.right, root.key, max_value);
	}
	
	/**
	 * Check that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K,V> root) {
		return isBSTHelper(root, null, null);
	}
	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	
	void print_structure_helper(TreeNode<K,V> root, int indent, String s) {			
		for (int i=0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(s+"->"+root.key + ": " + root.value);
		print_structure_helper(root.left, indent+1, "left");
		print_structure_helper(root.right, indent+1,"right");
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0,"root");
	}

	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}
	
	/*public static void main (String[] args){
		IntBST test= new IntBST(10);
		test.print_structure();
		test.remove(10);
		System.out.println("size:"+test.size());
		System.out.println("16 removed-------");
		test.print_structure();
		System.out.println("size:"+test.size());
	}*/

}
