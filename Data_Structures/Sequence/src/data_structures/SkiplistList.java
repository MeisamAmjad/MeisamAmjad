package data_structures;

import java.lang.reflect.Array;
import java.util.Random;


class NodeL<T>{
	
	T value;
	NodeL<T>[] next;
	int[] length;
	
	@SuppressWarnings("unchecked")
	public NodeL(T value, int height){
		this.value=value;
		next=(NodeL<T>[]) Array.newInstance(NodeL.class, height+1);
		length=new int[height+1];
	}
	
	public int height(){
		return next.length-1;
	}
}


public class SkiplistList<T extends Comparable <T>> extends Sequence<T> {

	private NodeL<T> sentinel;
	private int maxHeight;
	private Random rand;
	
	public SkiplistList(int h){
		super(0);
		sentinel=new NodeL<T>(null,h);
		maxHeight=0;
		rand=new Random();
	}
	
	private NodeL<T> findPred(int index)throws Exception{
		if (index<0 || index>=n)
			throw new Exception ("The given index is out of Bound");
		NodeL<T> u=(NodeL<T>) sentinel;
		int h=maxHeight;
		int j=-1; //Index of the current NodeL in list 0
		while (h>=0){
			while(u.next[h] != null && j+u.length[h]<index){
				j += u.length[h];
				u=u.next[h];
			}
			h--;
		}
		return u;
	}
	
	private NodeL<T> findNode(T value)throws Exception{//This method is not right because, the elements are not sorted
		if (isEmpty()) throw new Exception("The List is Empty.");
		NodeL<T> u=(NodeL<T>) sentinel;
		int h=maxHeight;
		while (h>=0){
			while(u.next[h] != null && u.next[h].value.compareTo(value)<0){
				u=u.next[h];
			}
			--h;
		}	
		return u;
	}
	
	@Override
	public T get(int index) throws Exception {
		return findPred(index).next[0].value;
	}
	
	@Override
	public T set(int index, T value) throws Exception {
		NodeL<T> u= findPred(index).next[0];
		T _oldValue=u.value;
		u.value=value;
		return _oldValue;
	}
	
	private boolean add(int index, NodeL<T> newNodeL)throws Exception{
		if ( index<0 || index>=++n)
			throw new Exception("Index for add method is out of bound.");
		boolean result=false;
		NodeL<T> currentNodeL=(NodeL<T>) sentinel;
		int newNodeLHeight=newNodeL.height(), h=maxHeight,j=-1;
		while (h>=0){
			while (currentNodeL.next[h] != null && j+currentNodeL.length[h] < index){
				j+=currentNodeL.length[h];
				currentNodeL=currentNodeL.next[h];
			}
			currentNodeL.length[h]++; //Accounts for new NodeL in list 0
			if(h<=newNodeLHeight){
				newNodeL.next[h]=currentNodeL.next[h];
				currentNodeL.next[h]=newNodeL;
				newNodeL.length[h]=currentNodeL.length[h]- (index-j);
				currentNodeL.length[h]=index-j;// Or currentNodeL.length[h]-newNodeL.length[h]
				result=true;
			}
			h--;
		}
		return result;
	}
	
	@Override
	public void add(int index, T value) throws Exception {
		NodeL<T> _newNodeL=new NodeL<T>(value, (size()==0)?0:pickHeight());
		if (_newNodeL.height() > maxHeight) 
			maxHeight=_newNodeL.height();
		add(index, _newNodeL);
	}
	
	private int pickHeight(){
		int _rNum=rand.nextInt(),counter=0,m=1;
		while ( (_rNum & m) != 0 ){
			counter++;
			m <<= 1;
		}
		return counter;
	}
	
	@Override
	public void add(T value) {
		try{add(size(),value);}
		catch(Exception e){e.printStackTrace();}
	}
	
	@Override
	public T remove(int index) throws Exception {
		if ( index<0 || index>=n)
			throw new Exception("Index for remove method is out of bound.");
		T oldValue=null;
		NodeL<T> currentNodeL=(NodeL<T>) sentinel;
		int h=maxHeight,j=-1;
		while(h>=0){
			while(currentNodeL.next[h]!=null && j+currentNodeL.length[h]<index){
				j+=currentNodeL.length[h];
				currentNodeL=currentNodeL.next[h];
			}
			if(j+currentNodeL.length[h]--==index && currentNodeL.next[h]!=null){
				oldValue=currentNodeL.next[h].value;
				currentNodeL.length[h]+=currentNodeL.next[h].length[h];
				currentNodeL.next[h]=currentNodeL.next[h].next[h];
				if (currentNodeL==sentinel && currentNodeL.next[h]==null)
					maxHeight--;
			}
			h--;
		}
		n--;
		return oldValue;
	}
	
	@Override
	public T remove() throws Exception {
		return remove(0);
	}
	
	@Override
	public void clear() {
		n=0;
		int h=sentinel.height();
		sentinel=new NodeL<T>(null,h);
		maxHeight=0;
		rand=new Random();
	}
	
	@Override
	public boolean in(T value) {//This method is not right, because elements are not sorted also, so we need to do it in O(n) not O(log n)
		NodeL<T> result=null;
		try{result=findNode(value);}
		catch(Exception e){e.printStackTrace();}
		return (result.next[0]!=null && result.next[0].value.equals(value) )? true: false;
	}
	
}
