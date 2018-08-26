package data_structures;


/**
 * A Node class that holds three items. next and preview Nodes, also a value.
 * This class is used for making a Double Linked List.
 * @author Meijad
 *
 * @param <T>
 */
class Node<T>{
	
	public Node<T> next,prev;
	public T value;
	
	public Node(){
		this.next=null;
		this.prev=null;
		this.value=null;
	}
	
	public Node(T value){
		this.next=null;
		this.prev=null;
		this.value=value;
	}
	
	public Node(Node<T> newNode){
		this.next=newNode.next;
		this.prev=newNode.prev;
		this.value=newNode.value;
	}
}


public 
class DLList<T extends Comparable <T>> 
		extends Sequence<T> {
	Node<T> dummy;
	
	public DLList() {
		super(0);
		this.dummy=new Node<>();
		this.dummy.next=dummy;
		this.dummy.prev=dummy;
	}

	public void push(T value)
					throws Exception{
		add(value);
	}
	
	public T pop()
				throws Exception{
		return remove(n-1);
	}
	
	public T peek() 
				throws Exception{
		return getNode(n-1).value;
	}
	
	private Node<T> getNode(int index) 
						throws Exception {
		if (index<0 || index>=n) 
			throw new IndexOutOfBoundsException("The given index is out of Bound.");
		Node<T> _returnNode=dummy;
		if(index < n>>1) 
			for(int i=-1;i<index;_returnNode=_returnNode.next,i++);
		else
			for(int i=n;i>index;_returnNode=_returnNode.prev,i--);
		return _returnNode;
	}
	
	private void addBefore(Node<T> _W,T value){
		Node<T> _newNode=new Node<>(value);
		_newNode.prev=_W.prev;
		_newNode.next=_W;
		_newNode.prev.next=_newNode;
		_newNode.next.prev=_newNode;
		++n;
	}
	
	private void removeNode(Node<T>_W){
		_W.prev.next=_W.next;
		_W.next.prev=_W.prev;
		--n;
	}
	
	@Override
	public T get(int index) 
				throws Exception {
		return getNode(index).value;
	}

	@Override
	public T set(int index, T value) 
				throws Exception {
		Node<T> _node=getNode(index);
		T _oldValue=_node.value;
		_node.value=value;
		return _oldValue;
	}

	@Override
	public void add(int index, T value) 
					throws Exception {
		Node<T> _node=getNode(index);
		addBefore(_node, value);
	}

	@Override
	public void add(T value) {
		addBefore(dummy, value);
	}

	@Override
	public T remove(int index) 
				throws Exception {
		Node<T> _node=getNode(index);
		removeNode(_node);
		return _node.value;
	}

	@Override
	public T remove() 
				throws Exception {
		return remove(0);
	}

	@Override
	public void clear() {
		n=0;
		this.dummy=new Node<>();
		this.dummy.next=dummy;
		this.dummy.prev=dummy;
	}

	@Override
	public boolean in(T value) {
		Node<T> _node=dummy.next;
		while (_node != dummy){
			if (_node.value.equals(value)) 
				return true;
			_node=_node.next;
		}
		return false;
	}
	
	@Override
	public void push_back(T value){
		add(value);
	}
}