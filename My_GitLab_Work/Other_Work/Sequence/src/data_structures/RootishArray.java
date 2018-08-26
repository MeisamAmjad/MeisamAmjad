package data_structures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public 
final class RootishArray<T extends Comparable<T>> 
					extends Sequence<T> {

	private int head,size;
	private List<T[]> blocks;
	
	@SuppressWarnings("unchecked")
	public RootishArray(T cls){
		super(0);
		this.head=0;
		this.blocks=new ArrayList<>();
		/*	Making a new generic array with the size of one
			for adding into the blocks ArrayList */
		T[] _newGenericArray=(T[]) Array.newInstance(cls.getClass(),1);
		this.blocks.add(_newGenericArray);
		this.size= blocks.size() * (blocks.size()+1) /2;
	}
	
	private boolean checkRange(int index){
		return (index>=0 && index<n)
				?true
				:false;
	}
	
	@SuppressWarnings("unchecked")
	private void grow(){
		Class <?> _elementsClass=blocks.get(0).getClass().getComponentType();
		int _newSize=blocks.size()+1;
		T[] _newGenericArray=(T[]) Array.newInstance(_elementsClass,_newSize);
		blocks.add(_newGenericArray);
		size=blocks.size() * (blocks.size()+1) /2;
	}
	
	/**
	 * Remove the empty blocks from the blocks ArrayList
	 */
	private void shrink(){
		int r=blocks.size();
		while (	r>0  &&  ((r-2) * (--r)/2 >=n)	)
			blocks.remove(blocks.size()-1);
		size=blocks.size() * (blocks.size()+1) /2;
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
	public T get(int index) 
				throws Exception {
		if (!checkRange(index)) throw new Exception("Either the list if empty of the given index is out of bound.");
		int _newIndex=(head+index)%size, _b=index2Block(_newIndex), _j=getJ(_newIndex, _b);
		return blocks.get(_b)[_j];
	}

	@Override
	public T set(int index, T value) 
				throws Exception {
		if (!checkRange(index)) 
			throw new Exception("Either the list if empty of the given index is out of bound.");
		
		int _newIndex=(head+index)%size,
			_b=index2Block(_newIndex),
			_j=getJ(_newIndex, _b);
		T _oldValue=blocks.get(_b)[_j];
		blocks.get(_b)[_j]=value;
		return _oldValue;
	}

	@Override
	public void add(int index, T value) 
					throws Exception {
		if (	n+1 > size	) 
			grow();
		if (index < n/2){
			head=(head==0)
				?size-1
				:head-1;
			for (int i=0;i<index;set(i, get(++i)) );
			n++;
		}else 
			for (int j=n++; j>index; set(j, get(--j)));
		set(index, value);
	}
	
	/**
	 * Adds at the end of the sequence
	 * and acts like a Queue
	 * @param value that needs to be added at the end of the sequence 
	 */
	@Override
	public void add(T value) {
		try{
			add(n, value);
		}
		catch( Exception e){
			e.getStackTrace();
		}
	}

	@Override
	public T remove(int index) 
				throws Exception {
		T _oldValue=get(index);
		if (index < n/2){
			for (int i=index;i>0;set(i, get(--i)));
			head=(head+1)%size;
		}else 
			for (int j=index;j<n-1;set(j, get(++j)));
		n--;
		if ( n <= 	(blocks.size()-2) * (blocks.size()-1) /2   ) 
			shrink();
		return _oldValue;
	}

	@Override
	public T remove() throws Exception {
		return remove(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		this.n=0;
		this.head=0;
		Class<?> _cl=blocks.get(0).getClass().getComponentType();
		this.blocks=new ArrayList<>();
		this.blocks.add((T[]) Array.newInstance(_cl, 1));
		this.size= blocks.size() * (blocks.size()+1) /2;
		
	}

	@Override
	public boolean in(T value) {
		for (int i=0; i< size(); i++)
			try { 
				if (get(i).compareTo(value)==0) return true;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}
}


