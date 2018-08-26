package data_structures;

import java.lang.reflect.Array;
import java.util.Random;

class NodeL2<T>{
	
	T value;
	NodeL2<T>[] next;
	
	@SuppressWarnings("unchecked")
	public NodeL2(T value, int height){
		this.value=value;
		next=(NodeL2<T>[]) Array.newInstance(NodeL2.class, height+1);
	}
	
	public int height(){
		return next.length-1;
	}
}


public class SkiplistSet<T extends Comparable <T>> extends Sequence<T> {
	
	private NodeL2<T> sentinel;
	private int maxHeight;
	private Random rand;
	
	public SkiplistSet(int h){
		super(0);
		sentinel=new NodeL2<T>(null,h);
		maxHeight=0;
		rand=new Random();
	}
	
	private NodeL2<T> findPreNodeL2(T value)throws Exception{
		if (isEmpty()) throw new Exception("The List is Empty.");
		NodeL2<T> u=(NodeL2<T>) sentinel;
		int h=maxHeight;
		while(h>=0){
			while(u.next[h]!=null && u.next[h].value.compareTo(value)<0)
				u=u.next[h];
			--h;
		}
		return u;
	}
	
	public T find(T value)throws Exception{
		NodeL2<T> result=findPreNodeL2(value);
		return (result.next[0]!=null && result.next[0].value.equals(value)) ? value:null;
	}
	
	@Override
	public T get(int index) throws Exception {
		if (index<0 || index>=n)throw new Exception("The index is out of bound.");
		NodeL2<T> u=sentinel;
		for (int i=0;i<=index;u=u.next[0],i++);
		return u.value;
	}
	
	@Override
	public T set(int index, T value) throws Exception {
		//TODO...
		return null;
	}
	
	@Override
	public void add(int index, T value) throws Exception {
		//TODO...
	}
	
	private int pickHeight(){
		int _rNum=rand.nextInt(),counter=0,m=1;
		while ( (_rNum & m) != 0 ){
			counter++;
			m <<= 1;
		}
		return counter;
	}
	
	//@SuppressWarnings("unchecked")
	public boolean addHelper(T value) {
		NodeL2<T> u=sentinel;
		NodeL2<T> newNodeL2=new NodeL2<T>(value, (size()==0)?0:pickHeight());
		int comp=0;
		
		
		int h=maxHeight=(maxHeight<newNodeL2.height())
				?newNodeL2.height()
				:maxHeight;	
		
		while(h>=0){
			while(u.next[h]!=null && (comp=u.next[h].value.compareTo(value))<0 )
				u=u.next[h];
			if (u.next[h]!=null && comp==0) return false;
			if (h<=newNodeL2.height()){
				newNodeL2.next[h]=u.next[h];
				u.next[h]=newNodeL2;
			}
			--h;
		}
		
		
		
		/*int h=maxHeight;
		NodeL2<T>[] stack=(NodeL2<T>[]) Array.newInstance(NodeL2.class, ( h<newNodeL2.height()?newNodeL2.height()+1:h+1) );
		while(h>=0){
			while(u.next[h]!=null && (comp=u.next[h].value.compareTo(value))<0 )
				u=u.next[h];
			if (u.next[h]!=null && comp==0) return false;
			stack[h--]=u;
		}
		while(maxHeight<newNodeL2.height())
			stack[++maxHeight]=sentinel;
		for(int i=0;i<newNodeL2.next.length;i++){
			newNodeL2.next[i]=stack[i].next[i];
			stack[i].next[i]=newNodeL2;
		}*/
		
		
		++n;
		return true;
	}
	
	
	@Override
	public void add(T value) {
		try{addHelper(value);}
		catch(Exception e){e.printStackTrace();}
	}
	
	public boolean remove(T value)throws Exception{
		if (isEmpty()) throw new Exception("The list is empty.");
		boolean removed=false;
		NodeL2<T> u=sentinel;
		int h=maxHeight,comp=0;
		while(h>=0){
			while(u.next[h]!=null && (comp=u.next[h].value.compareTo(value))<0 )
				u=u.next[h];
			if (u.next[h]!=null && comp==0){
				removed=true;
				u.next[h]=u.next[h].next[h];
				if (u==sentinel && u.next[h]==null)
					--maxHeight;
			}
			--h;
		}
		if (removed) --n;
		return removed;
	}
	
	@Override
	public T remove(int index) throws Exception {
		//TODO...
		return null;
	}
	
	@Override
	public T remove() throws Exception {
		return remove(0);
	}
	
	@Override
	public void clear() {
		n=0;
		int h=sentinel.height();
		sentinel=new NodeL2<T>(null,h);
		maxHeight=0;
		rand=new Random();
	}
	
	@Override
	public boolean in(T value) {
		T result=null;
		try{result=find(value);}
		catch(Exception e){e.printStackTrace();}
		return (result!=null && result.equals(value))? true: false;
	}
	
}
