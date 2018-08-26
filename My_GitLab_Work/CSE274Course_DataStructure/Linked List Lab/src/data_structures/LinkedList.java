/**
 * 
 */
package data_structures;

/**
 * @author karroje
 *
 */

/**
 * The ListNode<value_type> is a helper class for your 
 * LinkedList<value_type> class.  As its not intended for use
 * outside the LinkeList class, we are keeping it simple -- the
 * two properties will be access directly, instead of going through
 * inspectors and mutators.
 * 
 * DO NOT MODIFY THIST CLASS.
 * 
 * @param <value_type>  The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;
		
	public ListNode(value_type v) {
		value = v;
		next = null;
	}
	
	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}
	
}


/*
 * We will implement this as a single linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {
	
	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;
	private ListNode<value_type> tail;
	                      
	
	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		head=null;
		tail=null;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException("Wrong range for the Link");
		
		ListNode <value_type>w=head;
		for(int j=1;j<=i;j++){
			w=w.next;
			numOps++;
		}
		numOps++;
		return w.value;   
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException("Wrong range for the Link");
		
		ListNode <value_type> w=head;
		for(int j=1;j<=i;j++){
			w=w.next;
			numOps++;
		}
		value_type v=w.value;
		numOps++;
		w.value=value;
		numOps++;
		return v;  
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException("Wrong range for the Link");
		
		ListNode <value_type> u=new ListNode<value_type>(value);
		numOps++;
		if (isEmpty()){
			head=u;
			tail=u;
		}else{
			ListNode <value_type> w=head;
			for(int j=1; j<i;j++){
				w=w.next;
				numOps++;
			}
			if (i==0){
				u.next=w;
				numOps++;
				head=u;
			}else{
				u.next=w.next;
				numOps++;
				numOps++;
				w.next=u;
				if(i==size()){ tail=u;}
				numOps++;
			}
			
		}
		this.n++;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#remove(int)
	 */
	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException("Wrong range for the Link");

		ListNode <value_type> w=head;
		for(int j=1; j<i;j++){
			w=w.next;
			numOps++;
		}
		value_type v=null;
		if (i==0){
			v=w.value;
			numOps++;
			head=w.next;
			if(i==size()-1){tail=head;}
			numOps++;
		}else{
			v=w.next.value;
			numOps++;
			w.next=w.next.next;
			numOps++;
			numOps++;
			numOps++;
			if(i==size()-1){tail=w;}
		}
		this.n--;
		return v;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		head=null;
		numOps++;
		this.n=0;
	}
	@Override
	public boolean in(value_type query) {
		if (isEmpty()) return false;
		ListNode <value_type> w=head;	
		for(int j=0;j<size();j++){
			numOps++;
			if (w.value.equals(query)){return true;}
			w=w.next;
			numOps++;
		}
		return false;
	}
	@Override
	public void push_back(value_type value) {
		ListNode <value_type> u=new ListNode<value_type>(value);
		numOps++;
		if(isEmpty()){
			head=u;
		}else{
			tail.next=u;
			numOps++;
		}
		tail=u;
		this.n++;
	}

}
