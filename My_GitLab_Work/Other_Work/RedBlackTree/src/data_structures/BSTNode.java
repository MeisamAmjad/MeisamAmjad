package data_structures;

public class BSTNode<K extends Comparable<K>, V extends Comparable<V>> {
	private K key;
	private V value;
	private BSTNode<K, V> parent;
	private BSTNode<K, V> left;
	private BSTNode<K, V> right;
	
	public BSTNode( K key, V value ) {
		
		setKey( key );
		setValue( value );
		setParent( null );
		setLeft( null );
		setRight( null );
	}
	
	public BSTNode( K key, V value, BSTNode<K,V> parent ,BSTNode<K,V> left, BSTNode<K,V> right ) {
		setKey( key );
		setValue( value );
		setParent( parent );
		setLeft( left );
		setRight( right );
	}
	
	public BSTNode( BSTNode<K, V> newNode) {
		setKey( newNode.getKey() );
		setValue( newNode.getValue() );
		setParent( newNode.getParent() );
		setLeft( newNode.getLeft() );
		setRight( newNode.getRight() );
	}

	public K getKey() { return this.key; }

	public void setKey( K key ) { this.key = key; }

	public V getValue() { return this.value; }

	public void setValue( V value ) { this.value = value; }

	public BSTNode<K, V> getParent() { return this.parent; }
	
	public void setParent ( BSTNode<K, V> parent ) { this.parent = parent; }
	
	public BSTNode<K, V> getLeft() { return this.left; }

	public void setLeft( BSTNode<K, V> left ) { this.left = left; }

	public BSTNode<K, V> getRight() { return this.right; }

	public void setRight( BSTNode<K, V> right ) { this.right = right; }
}