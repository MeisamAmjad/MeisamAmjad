package data_structures;

public class RBTNode<K extends Comparable<K>, V extends Comparable<V>> extends BSTNode<K, V>{
	
	private byte color;
	
	public RBTNode(){
		super( null, null );
		setColor( (byte) 1 );
	}
	public RBTNode( K key, V value ) {
		
		super( key, value );
		setColor( (byte) 0 );
	}
	
	public RBTNode( K key, V value, Byte color, RBTNode<K,V> parent ,RBTNode<K,V> left, RBTNode<K,V> right ) {
		super( key, value, parent, left, right );
		setColor( color );
	}
	
	public RBTNode( RBTNode<K, V> newNode) {
		super( newNode );
		setColor( newNode.getColor() );
	}
	
	public byte getColor() { return this.color; }
	
	public void setColor( byte color ) { this.color = color; }
	
	@Override
	public RBTNode<K, V> getParent() { return ( RBTNode<K, V> ) super.getParent(); }
	
	@Override
	public RBTNode<K, V> getLeft() { return ( RBTNode<K, V> ) super.getLeft(); }
	
	@Override
	public RBTNode<K, V> getRight() { return ( RBTNode<K, V> ) super.getRight(); }
	
	public void setParent( RBTNode<K, V> parent ) { super.setParent( parent ); }
	
	public void setLeft( RBTNode<K, V> left ) { super.setLeft( left ); }
	
	public void setRight( RBTNode<K, V> right ) { super.setRight( right ); }
}