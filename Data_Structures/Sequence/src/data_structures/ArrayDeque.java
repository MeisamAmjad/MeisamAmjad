package data_structures;

import java.lang.reflect.Array;


public class ArrayDeque <T extends Comparable<T>> extends Sequence<T>{
	
	public T[] A;
	public int head;
	
	@SuppressWarnings("unchecked")
	public ArrayDeque(Class <? extends T> cls){
		super(0);
		this.A=(T[]) Array.newInstance(cls,1);
	}
	
	/**
	 * Check if the index is in Bound.
	 * @param index Desire index
	 * @return true if it is in bound. Otherwise, returns false.
	 */
	private boolean checkIndex(int index){
		if (index>=0 && index<n ) return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(){
		T[] B= (T[]) Array.newInstance(A.getClass().getComponentType(), A.length*2);
		for(int i=0; i<n;B[i]=A[(head+(i++))%A.length]);
		this.head=0;
		this.A=B;
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
		if (!checkIndex(index)) throw new Exception("Either the List is Empty, or the index is out of Bound.");
		return A[(head+index)%A.length];
	}

	@Override
	public T set(int index, T value) throws Exception {
		if (!checkIndex(index)) throw new Exception("Either the List is Empty, or the index is out of Bound.");
		T _temp=A[(head+index)%A.length];
		A[(head+index)%A.length]=value;
		return _temp;
	}

	@Override
	public void add(int index, T value) throws Exception {
		if (n+1>A.length) resize();
		if (index < n/2){
			head=(head==0)?A.length-1:head-1;
			for (int i=0;i<index;set(i,get(++i)));
			n++;
		}else for (int i=n++;i>index;set(i,get(--i)));
		set(index, value);
	}
	
	@Override
	public void add( T value) {
		try{add(n,value);}
		catch(Exception e){ e.getStackTrace();}
	}

	@Override
	public T remove(int index) throws Exception {
		T v=get(index);
		
		if (index < n/2){
			for (int i=index;i>0;set(i, get(--i)) );
			head=(head+1)%A.length;
		}else for (int i=index;i<n-1;set(i, get(++i)));
		n--;
		return v;
	}

	@Override
	public T remove() throws Exception {
		return remove(0);
	}

}

