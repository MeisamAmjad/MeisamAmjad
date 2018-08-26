package data_structures;

/**
 * This class adds a unique numerical value to BST node for implementing Treap data structure. 
 * That is, the nodes in Treap can obey both BST rules and Heap property rules.
 * @author Amjadm@Miamioh.edu
 *
 * @param <K> Generic type for Key
 * @param <V> Generic Type for Value
 */
public class TreapNode<K extends Comparable<K>, V extends Comparable<V>> extends BSTNode<K, V> {
	
	/** It is unique numerical priority that is assigned at random*/
	private Integer P;
	
	public TreapNode(){
		super( null, null );
		setP(  null  );
	}
	
	public TreapNode( K key, V value ) {
		super( key, value, null, null );
		setP(  null  );
	}
	
	public TreapNode( K key, V value , Integer p) {
		
		super( key, value, null, null );
		setP( p );
	}
	
	public TreapNode( K key, V value, TreapNode<K, V> left, TreapNode<K, V> right){
		super( key, value, left, right);
		setP(  null );
	}
	
	public TreapNode( TreapNode<K, V> T){
		super( T.getKey(), T.getValue(), T.getLeft(), T.getRight());
		setP( T.getP() );
	}
	
	public int getP() { return this.P; }
	
	public void setP( Integer p ) { this.P = p; }
	
	public void setLeft( TreapNode<K, V> left ) { super.setLeft( left ); }
	
	public void setRight( TreapNode<K, V> right ) { super.setRight( right ); }
	
	@Override
	public TreapNode<K, V> getLeft() { return (TreapNode<K, V>) super.getLeft(); }
	
	@Override
	public TreapNode<K, V> getRight() { return (TreapNode<K, V>) super.getRight(); }
}
