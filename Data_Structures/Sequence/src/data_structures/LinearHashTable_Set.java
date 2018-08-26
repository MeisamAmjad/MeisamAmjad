package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.awt.RenderingHints.Key;
import java.lang.IllegalArgumentException;

public class LinearHashTable_Set<K extends Comparable<K>,E extends Comparable<E>> extends Dictionary<K, E>{
	int a;
	int b;
	int m;
	ArrayList<Pair<K,E>> table;
	
	/**
	 * Default constructor
	 */
	public LinearHashTable_Set() {
		this.a=7;
		this.b=1;
		this.m=25014;
	}
	
	/**
	 * Constructor -- hash values specified.
	 */
	public LinearHashTable_Set(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<K,E>>(Collections.nCopies(m, null));
	}
	
	/**
	 * Hashing function
	 * @param key
	 * @return hash value
	 */
	private int hash(K key) {
		return (key instanceof String)?
				key.hashCode():
				((int)key.hashCode()*a + b) % m;
	}
	
	/**
	 * Insert a value/key pair into the dictionary.  Do not allow duplicate
	 * or null values.
	 * @param key        key to be inserted
	 * @param value      value to be inserted
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 * @exception   Throw IllegalArgumentException if value is null.
	 * @exception   Throw IllegalArgumentException if key < 0.  (Makes life easier.
	 */
	@Override
	public void insert(K key, E value) throws IllegalArgumentException,ArrayIndexOutOfBoundsException,IndexOutOfBoundsException{
		if (value == null || key==null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n==m || find(key)!=null)
			throw new ArrayIndexOutOfBoundsException("Duplicate values not allowed/Or the Hash table is full");
		int index=hash(key);
		while(table.get(index)!=null && table.get(index).getSecond()!=null)
			index=(index+1)%m;
		n++;
		table.set(index, new Pair<K, E>(key, value));
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) throws IllegalArgumentException,ArrayIndexOutOfBoundsException,IndexOutOfBoundsException{
		if (key==null)
			throw new IllegalArgumentException("Negative and Null keys not allowed");
		int index=hash(key);
		Pair<K, E> oldPair=table.get(index);
		for(int i=0;oldPair!=null && i++<m;){
			if(oldPair.getSecond()!=null && oldPair.getFirst().equals(key) ){
				table.set( index, new Pair<K,E>(key,null) );
				n--;
				return;
			}
			index=(index+1)%m;
			oldPair=table.get(index);
		}
		throw new ArrayIndexOutOfBoundsException("There is no such a key");
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		table = new ArrayList<Pair<K,E>>(Collections.nCopies(m, null));
		n=0;
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public E find(K key) throws IllegalArgumentException,Error{
		if (key==null)
			throw new IllegalArgumentException("Negative and Null keys not allowed");
		int index=hash(key);
		Pair<K, E> oldPair=table.get(index);
		for (int i=0;oldPair!=null && i++<m;){
			if (oldPair.getSecond()!=null && oldPair.getFirst().equals(key)){
				return oldPair.getSecond();
			}
			index=(index+1)%m;
			oldPair=table.get(index);
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 * This is not useful for this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i=0; i < m; i++) {
			Pair<K,E> p = table.get(i);
			if (p != null)
				System.out.println("k, h(k), v = " + p.getFirst() + " " + i + " " + p.getSecond());
		}
	}

}
