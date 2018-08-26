package data_structures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RootishArrayStack<T extends Comparable<T>> extends Sequence<T> {

	private List<T[]> blocks;
	
	@SuppressWarnings("unchecked")
	public RootishArrayStack(Class <? extends T> cls){
		super(0);
		this.blocks=new ArrayList<T[]>();
		this.blocks.add((T[]) Array.newInstance(cls, 1));
	}
	
	private boolean checkIndex(int index){
		if (index>=0 && index<n) return true;
		return false;
	}
	
	private void resize(){
		blocks.add(newArray(blocks.size()+1));
	}
	
	private void shrink(){
		int r=blocks.size();
		while (	r>0  &&  ((r-2) * (--r)/2 >=n)	) blocks.remove(blocks.size()-1);
	}
	
	@SuppressWarnings("unchecked")
	private T[] newArray(int size){
		Class <?> _cl=blocks.get(0).getClass().getComponentType();
		return (T[]) Array.newInstance(_cl, size);
	}
	
	/**
	 * Compute the proper block based on the given index.
	 * (b+1)(b+2)/2 >= i+1 ---> b^2 + 3b - 2i >= 0 ---> b = | (-3 + sqrt(9 + 8i) /2) | 
	 * @param index indicate the index of the value in the sequence
	 * @return Proper block number
	 */
	private int index2Block(int index){
		return (int) Math.ceil(	(-3.0 + Math.sqrt(9+8*index))/2.0	);
	}
	
	/**
	 * Compute the appropriate j based on the given index and block numbers.
	 * j = i -  b (b+!) / 2 
	 * @param index indicate the index of the value in the sequence
	 * @param block indicate the relative block number 
	 * @return Proper j number
	 */
	private int getJ(int index, int block){
		return index - (block* (block+1) /2);
	}
	
	@Override
	public T get(int index) throws Exception {
		if (!checkIndex(index)) throw new Exception("Either the list if empty of the given index is out of bound.");
		int _b=index2Block(index), _j=getJ(index, _b);
		return blocks.get(_b)[_j];
	}

	@Override
	public T set(int index, T value) throws Exception {
		if (!checkIndex(index)) throw new Exception("Either the list if empty of the given index is out of bound.");
		int _b=index2Block(index), _j=getJ(index, _b);
		T _temp=blocks.get(_b)[_j];
		blocks.get(_b)[_j]=value;
		return _temp;
	}

	@Override
	public void add(int index, T value) throws Exception {
		if (	n+1	>	blocks.size() * (blocks.size()+1) /2	) resize();
		for (int j=n++; j>index; set(j, get(--j)));
		set(index, value);
	}

	@Override
	public void add(T value) {
		try{add(n, value);}
		catch( Exception e){ e.getStackTrace();}
	}

	@Override
	public T remove(int index) throws Exception {
		T _temp=get(index);
		for (int j=index; j<n-1;set(j, get(++j)));
		n--;
		if ( n <= 	(blocks.size()-2) * (blocks.size()-1) /2   ) shrink();
		return _temp;
	}

	@Override
	public T remove() throws Exception {
		return remove(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		this.n=0;
		Class<?> _cl=blocks.get(0).getClass().getComponentType();
		this.blocks=new ArrayList<T[]>();
		this.blocks.add((T[]) Array.newInstance(_cl, 1));
		
	}

	@Override
	public boolean in(T value) {
		for (int i=0; i< size(); i++)
			try { if (get(i).compareTo(value)==0) return true;} 
			catch (Exception e) {e.printStackTrace();}
		return false;
	}
}

