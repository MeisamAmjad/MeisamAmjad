package data_structures;
import java.util.ArrayList;


class Pair<E,P>{
	private P Priority;
	private E entry;
	public Pair(E entry,P priority) {
		this.Priority=priority;
		this.entry=entry;
	}
	public P getPriority() {
		return Priority;
	}
	public void setPriority(P priority) {
		Priority = priority;
	}
	public E getEntry() {
		return entry;
	}
	public void setEntry(E entry) {
		this.entry = entry;
	}
} 	



public class MinHeap<E, P extends Comparable<P>> implements MinPriorityQueue<E, P> {
	private ArrayList<Pair<E, P>> heap;
	private int numOps;
	public MinHeap(){
		heap=new ArrayList<Pair<E, P>>();
		numOps=0;
	}
	
	private int left(int i) {
		return 2*i + 1;
	}
	private int right(int i) {
		return 2*i + 2;
	}
	private	int parent(int i) {
		return (i-1)/2;
	}
	
	@Override
	public void push(E entry,P priority)throws NullPointerException{
		if (entry==null || priority==null) throw new NullPointerException("adding null data is not allowed");
		heap.add(new Pair<E,P>(entry,priority));
		numOps++;
		if (size()>1) bubleUp(size()-1);
	}
	
	private void bubleUp(int i){
		int p=parent(i);
		while (i>0 && heap.get(i).getPriority().compareTo(heap.get(p).getPriority())<0){
			numOps+=2;
			swap(i,p);
			i=p;
			p=parent(i);
		}
	}
	
	private void swap(int i, int p){
		Pair <E,P> u=heap.get(i);
		heap.set(i, heap.get(p));
		heap.set(p, u);
		numOps+=3;
	}
	
	@Override
	public E pop()throws ArrayIndexOutOfBoundsException{
		if (empty()) throw new ArrayIndexOutOfBoundsException("The list is empty");
		E result=heap.get(0).getEntry();
		heap.set(0, heap.get(size()-1));
		heap.remove(size()-1);
		numOps+=4;
		if (size()>1) trickleDown(0);
		return result;
	}
	
	private void trickleDown(int i) {
		do{
			int j=-1, r=right(i), l=left(i);
			if (r<size() && heap.get(r).getPriority().compareTo(heap.get(i).getPriority())<0){
				j= (heap.get(l).getPriority().compareTo(heap.get(r).getPriority())<0)?l:r;
				numOps+=4;
			}else
				if(l<size() && heap.get(l).getPriority().compareTo(heap.get(i).getPriority())<0){
					j=l;numOps+=2;}
			if (j>=0) swap(i, j);
			i=j;
		}while (i>=0);

	}
	
	@Override
	public E peek()throws ArrayIndexOutOfBoundsException{
		if (empty()) throw new ArrayIndexOutOfBoundsException("The list is empty");
		numOps++;
		return heap.get(0).getEntry();
	}

	@Override
	public int size() {
		return heap.size();
	}
	
	@Override
	public boolean empty() {
		return (size()<=0);
	}

	@Override
	public void clear() {
		heap=new ArrayList<Pair<E,P>>();
	}

	@Override
	public void resetOps() {
		numOps=0;
		
	}

	@Override
	public int numOps() {
		return numOps;
	}
	
	/*private void print_structure(){
		for (int i=0;i<size();i++) System.out.print("["+i+"]= ("+heap.get(i).getEntry()+","+heap.get(i).getPriority()+")\t");
		System.out.println();
	}*/
}
