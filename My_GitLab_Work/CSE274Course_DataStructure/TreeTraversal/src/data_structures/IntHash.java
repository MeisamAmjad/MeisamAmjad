/**
 * Meisam Amjad (amjadm)
 * 
 * 
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author karroje
 *
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	int m;

	ArrayList<Pair<Integer,value_type>> table;
	
	/**
	 * Hashing function
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int)key*a) + b) % m;
	}
	
	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}
	
	
	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
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
	public void insert(Integer key, value_type value) throws IllegalArgumentException,ArrayIndexOutOfBoundsException,IndexOutOfBoundsException{
		if (value == null || key==null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n==m || find(key)!=null)
			throw new ArrayIndexOutOfBoundsException("Duplicate values not allowed/Or the Hash table is full");
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		int k=hash(key);
		numOps++;
		while(table.get(k)!=null && table.get(k).second!=null){
			k=(k==m-1?k=0:k+1);
			numOps++;
		}
		Pair<Integer, value_type> p=new Pair<Integer, value_type>(key, value);
		n++;
		numOps++;
		table.set(k, p);
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(Integer key) throws IllegalArgumentException,ArrayIndexOutOfBoundsException,IndexOutOfBoundsException{
		
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		
		Pair<Integer, value_type> del=new Pair<Integer, value_type>(key, null);
		int c=0;
		int i=hash(key);
		numOps++;
		while(table.get(i)!=null && c!=m){
			numOps++;
			Pair<Integer, value_type> H=table.get(i);
			if(H.second!=null && H.first.equals(key) ){
				n--;
				numOps++;
				table.set(i,del);
				return;
			}
			i=(i==m-1?i=0:i+1);
			c++;
			numOps++;
		}
		if (c==m) 
			throw new ArrayIndexOutOfBoundsException("There is no such a key");
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	public void clear() {
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
		n=0;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(Integer key) throws IllegalArgumentException,Error{
		if (key < 0 || key==null)
			throw new IllegalArgumentException("Negative and Null keys not allowed");
		int k=hash(key);
		int c=0;
		numOps++;
		while (table.get(k)!=null && c!=m){
			numOps++;
			Pair<Integer, value_type> H=table.get(k);
			if (H.second!=null && H.first.equals(key)){
				return H.second;
			}
			k=k==m-1?k=0:k+1;
			numOps++;
			c++;
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
			Pair<Integer,value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}
}