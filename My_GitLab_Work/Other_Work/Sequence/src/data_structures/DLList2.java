package data_structures;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A Node class that holds three items. next and preview Nodes, also a value.
 * This class is used for making a Double Linked List.
 * @author Meijad
 *
 * @param <T>
 */

public 
class DLList2<T extends Comparable <T>> 
		extends Sequence<T> {
	LinkedList<T> L;
	ListIterator<T> it;
	
	public DLList2() {
		super(0);
		L=new LinkedList<T>();
		it=L.listIterator();
	}

	public void push(T value)
					throws Exception{
		L.addLast(value);
		++n;
	}
	
	public T pop()
				throws Exception{
		--n;
		return L.removeLast();
	}
	
	public T peek() 
				throws Exception{
		return L.peekLast();
	}
	
	@Override
	public T get(int index) 
				throws Exception {
		it=L.listIterator(index);
		return it.next();
		
	}

	@Override
	public T set(int index, T value) 
				throws Exception {
		return L.set(index, value);
	}

	@Override
	public void add(int index, T value) 
					throws Exception {
		it=L.listIterator(index);
		it.add(value);
		++n;
	}

	@Override
	public void add(T value) {
		try{add(size()==0?0:n, value);}
		catch(Exception e){e.printStackTrace();}
	}

	@Override
	public T remove(int index) 
				throws Exception {
		it=L.listIterator(index);
		T _oldValue=it.next();
		--n;
		it.remove();
		return _oldValue;
	}

	@Override
	public T remove() 
				throws Exception {
		return remove(0);
	}

	@Override
	public void clear() {
		n=0;
		L=new LinkedList<T>();
		it=L.listIterator();
	}

	@Override
	public boolean in(T value) {
		return L.contains(value);
	}
	
	@Override
	public void push_back(T value){
		add(value);
	}
}