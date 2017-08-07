/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package data_structures;

/**
 * <p> This class implements a Node that holds a ( key, pair ) elements (or a dictionary) to be used by the binary search tree (Dictionary) data structure.</br>
 * For being used by the binary search tree data structure, this node should have left, parent, and right nodes as well along with holding a key </br>
 * and a value.</p>
 * <p>For <i><b>programmer</b></i> all non public methods defined in <i><b>protected</b></i> mode, that way anybody would be able </br>
 * to implement the methods again as they wish.</b></p>
 * @author Meijad
 * @param <K> Generic type for K
 * @param <V> Generic type for V
 */
public class PairNode<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<K> {
	/**
	 * <p> Holds a value of the key. By default it would be null. </p>
	 * @see #getKey()
	 * @see #setKey(key)
	 */
	private K key = null;
	
	/**
	 * <p> Holds a value of the value. By default it would be null. </p>
	 * @see #getValue()
	 * @see #setValue(value)
	 */
	private V value = null;
	
	/**
	 * <p> Holds the address of the parent Node. By default it would be null.</p>
	 * #see #getParent()
	 * #see #setParent(PairNode parent)
	 */
	private PairNode<K, V> parent = null;
	
	/**
	 * <p> Holds the address of the left Node. By default it would be null.</p>
	 * #see #getLeft()
	 * #see #setLeft(PairNode left)
	 */
	private PairNode<K,V> left;
	
	/**
	 * <p> Holds the address of the right Node. By default it would be null.</p>
	 * #see #getRight()
	 * #see #setRight(PairNode right)
	 */
	private PairNode<K,V> right;
	
	/**
	 * <p> Constructor.</p>
	 */
	public PairNode() {
		super();
	}
	
	/**
	 * <p> Constructor.</p>
	 * @param key A new key
	 * @param value A new value
	 */
	public PairNode( K key, V value ) {
		super();
		setKey( key );
		setValue( value );
	}
	
	/**
	 * <p> Constructor.</p>
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	protected PairNode( K key, V value, PairNode<K,V> left, PairNode<K, V> parent, PairNode<K,V> right ) {
		super();
		setKey( key );
		setValue( value );
		setLeft( left );
		setRight( right );
		setParent( parent );
	}
	
	/**
	 * <p> Constructor.</p>
	 */
	protected PairNode( PairNode<K, V> newNode ) {
		super();
		setKey( newNode.getKey() );
		setValue( newNode.getValue() );
		setLeft( newNode.getLeft() );
		setRight( newNode.getRight() );
		setParent( newNode.getParent() );
	}

	/**
	 * @see #key
	 */
	public K getKey() { return this.key; }

	/**
	 * @see #key
	 */
	public void setKey( K key ) { this.key = key; }

	/**
	 * @see #value
	 */
	public V getValue() { return this.value; }

	/**
	 * @see #value
	 */
	public void setValue( V value ) { this.value = value; }

	/**
	 * @see #parent
	 */
	protected PairNode<K, V> getParent() { return this.parent; }
	
	/**
	 * @see #parent
	 */
	protected void setParent( PairNode<K, V> parent ) { this.parent = parent; }
	
	/**
	 * @see #left
	 */
	protected PairNode<K, V> getLeft() { return this.left; }

	/**
	 * @see #left
	 */
	protected void setLeft( PairNode<K, V> left ) { this.left = left; }

	/**
	 * @see #right
	 */
	protected PairNode<K, V> getRight() { return this.right; }

	/**
	 * @see #right
	 */
	protected void setRight( PairNode<K, V> right ) { this.right = right; }
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( K k ) {
		if ( getKey() == null || k == null )
			throw new IllegalArgumentException( "** Null Objects Error...**" );
		return getKey().compareTo( k );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public boolean equals( Object obj ) {
		if ( this == null || obj == null  || !( obj instanceof PairNode ) )
			return false;
		PairNode<K, V> o2 = ( PairNode<K, V> ) obj;
		return ( ( getKey().compareTo( o2.getKey() ) == 0 ) &&  ( getValue().compareTo( o2.getValue() ) == 0 ) );
	};
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return ( int ) 31 * ( getKey().hashCode() + getValue().hashCode() );
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + getKey().toString() + ", " + getValue().toString() + ")";
	}
}
