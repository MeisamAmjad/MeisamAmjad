import java.util.ArrayList;
import data_structures.LinkedList;
import data_structures.TreeNode;

public class TreeTraversal {

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary search tree,
	 * return an ArrayList<Integer> holding the keys in order from a post-order traversal.
	 * RESTRICTION: Use recursion for this implementation.
	 * @param r
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer,Integer> r){
		if (r==null) return null;
		ArrayList<Integer> arrayOut=new ArrayList<>();
		return postOrderHelper(r,arrayOut);
	}
	private static ArrayList<Integer> postOrderHelper(TreeNode<Integer,Integer> r, ArrayList<Integer> A){
		if (r==null) return null;
		postOrderHelper(r.left,A);
		postOrderHelper(r.right,A);
		if (A!=null) A.add(r.key);
		return A;
	}
	

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a BFS traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Queue.  (Or
	 * use your Linked List class as a queue.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root){ 
		ArrayList<Integer> arrayOut=new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> Q=new LinkedList<>();//ADT: QUEUE--Data structure: LINKED LIST
		if (root!=null) Q.push_back(root); else return null;
		while (Q.size()!=0){
			TreeNode<Integer, Integer> u=Q.remove(0);
			arrayOut.add(u.key);
			if (u.left!=null) Q.push_back(u.left);
			if (u.right!=null) Q.push_back(u.right);
		}
		return arrayOut;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root){
		ArrayList<Integer> arrayOut=new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> Stack=new LinkedList<>(); //ADT: STACK--Data structure: LINKED LIST
		if (root!=null) Stack.push_back(root); else return null;
		while (Stack.size()!=0){
			TreeNode<Integer, Integer> u=Stack.remove(Stack.size()-1);
			arrayOut.add(u.key);
			if (u.right!=null) Stack.add(Stack.size(),u.right);
			if (u.left!=null) Stack.add(Stack.size(),u.left);
		}
		return arrayOut;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root){
		ArrayList<Integer> arrayOut=new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> Stack=new LinkedList<>();//ADT: STACK--Data structure: LINKED LIST
		TreeNode<Integer, Integer> u=null;
		if (root!=null)	u=root; else return null;
	    while ( u!= null) {
	    	Stack.push_back(u);u = u.left;
	    }
	    while (Stack.size() > 0) {
	    	u = Stack.remove(Stack.size()-1); arrayOut.add(u.key);
	    	if (u.right != null) {
	    		u = u.right;
	    		while (u != null) {
	    			Stack.push_back(u);	u = u.left;
	    		}
	    	}
	    }
		return arrayOut;
	}
}

