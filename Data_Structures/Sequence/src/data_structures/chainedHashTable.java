package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class chainedHashTable<K extends Comparable<K>,E extends Comparable<E>> extends Dictionary<K, E>{
	int a;
	int b;
	int m;
	ArrayList<ArrayList<Pair<K,E>>> table;
	
	/**
	 * Default constructor
	 */
	public chainedHashTable() {
		this.a=7;
		this.b=1;
		this.m=25014;
	}
	
	/**
	 * Constructor -- hash values specified.
	 */
	public chainedHashTable(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<ArrayList<Pair<K,E>>>(Collections.nCopies(m,  new ArrayList<Pair<K, E>>()) );
				
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
		n++;
		table.get(index).add(new Pair<K, E>(key, value));
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) throws IllegalArgumentException,ArrayIndexOutOfBoundsException,IndexOutOfBoundsException{
		if (key==null)
			throw new IllegalArgumentException("Negative and Null keys not allowed");
		int index=hash(key);
		if (table.get(index)!=null){
			ArrayList<Pair<K, E>> pairs=table.get(index);
			for(int i=0;i<pairs.size();i++)
				if (pairs.get(i).getFirst().equals(key)){
					pairs.remove(i);
					--n;
					return;
				}
		}
		throw new ArrayIndexOutOfBoundsException("There is no such a key");
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	public void clear() {
		table = new ArrayList<ArrayList<Pair<K,E>>>(Collections.nCopies(m,  null) );
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
		if (table.get(index)!=null)
			for(Pair<K,E> pairs:table.get(index)){
			if (pairs.getFirst().equals(key))
				return pairs.getSecond();
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
			ArrayList<Pair<K,E>> p = table.get(i);
			if (p != null)
				for(Pair<K, E> pairs:p)
				System.out.println("k, h(k), v = " + pairs.getFirst() + " " + i + " " + pairs.getSecond());
		}
	}
}
