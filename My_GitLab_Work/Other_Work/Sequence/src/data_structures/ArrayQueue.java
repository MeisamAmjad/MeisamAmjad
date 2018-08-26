package data_structures;

import java.lang.reflect.Array;

/**
 * 
 * @author Meijad
 *
 */
public class ArrayQueue<T extends Comparable <T>> extends Sequence<T>{
	
	public T[] A;
	public int head;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(Class <? extends T> cls){
		super(0);
		this.A=(T[]) Array.newInstance(cls,1);
	}
	
	/**
	 * Check if the index is in Bound.
	 * @param index Desire index
	 * @return true if it is in bound. Otherwise, returns false.
	 *//*
	private boolean checkIndex(int index){
		if (	(index>=0 && index<A.length) &&
				(head<=(head+n)%A.length && index>=head && index<=(head+n)%A.length ) ||
				(head>(head+n)%A.length && (index>=head || index<=(head+n)%A.length) )
			) return true;
		return false;
	}
	*/
	
	@SuppressWarnings("unchecked")
	private void resize(){
		T[] B= (T[]) Array.newInstance(A.getClass().getComponentType(), A.length*2);
		for(int i=0; i<n;B[i]=A[(head+(i++))%A.length]);
		this.head=0;
		this.A=B;
	}
	
	@Override
	public void add( T value) {
		if(n+1>A.length) resize();
		A[(head+(n++))%A.length]=value;
	}
	

	@Override
	public T remove() throws Exception {
		if (n==0) throw new Exception("The Queue is Empty.");
		T v=A[head];
		head=(head+1)%A.length;
		n--;
		return v;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		n=0;
		head=0;
		Class<?> cls=A.getClass().getComponentType();
		A=(T[]) Array.newInstance(cls,1);
	}

	@Override
	public boolean in(T value) {
		for (int i=0;i<n;i++) if (A[(head+i)%A.length].compareTo(value)==0) return true;
		return false;
	}

	@Override
	public T get(int index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(int index, T value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T value) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(int index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}


