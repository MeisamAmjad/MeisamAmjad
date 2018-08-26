package reversDLList;


public class Revers {
	public static <T> void reverseDLList(Node<T> dummy){
		Node<T> _nodes=dummy.next;
		Node<T> _tempNode;
		// Swap the next and prev in all nodes.
		while(_nodes != dummy){
			_tempNode=_nodes.next;
			_nodes.next=_nodes.prev;
			_nodes.prev=_tempNode;
			_nodes=_nodes.prev;
		}
		// Swap the next and prev in the dummy node.
		_tempNode=dummy.next;
		dummy.next=dummy.prev;
		dummy.prev=_tempNode;
	}
	
	public static <T> void reverseSLList(Node<T> first,Node<T> last){
		Node<T> _nodes=first,_tempNode=last,_nextNode;
		while (_nodes!=null){
			_nextNode=_nodes.next;
			_nodes.next=_tempNode.next;
			_tempNode.next=_nodes;
			_nodes=_nextNode;
		}
		_tempNode=first;
		first=last;
		last=_tempNode;
	}
}
