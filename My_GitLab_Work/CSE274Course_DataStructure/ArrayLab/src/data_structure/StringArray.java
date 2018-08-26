/**
 *  cse274-lab 3
 *  Meisam Amjad- amjadm@miamioh.edu
*/

import java.lang.IndexOutOfBoundsException;
/**
 * StringArray: An implementation of a dynamic-sized string array.
 * 
 * @author TODO: Add your name here
 *
 */

public class StringArray {

	/**
	 * A:Contains the actual array.
	 */
	private String[] A;
	
	/**
	 * n: Size of array
	 */
	int n;

	
	/**
	 * Basic constructor: Creates an "empty" list.
	 */
	public StringArray() {
		A = new String[1];
		n = 0;
	}

	/**
	 * Return the number of strings contained in the list.
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Get the string i.
	 * @param i Number of accessed element (from 0).
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public String get(int i) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException("Number of the element is out of the range");}
		return A[i];
	}

	/**
	 * Set the string at element i to value.
	 * @param i Number of accessed element (from 0).
	 * @param value String value being put in the array
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public String set(int i, String value) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException("Number of the element is out of the range");}
		String y = A[i];
		A[i] = value;
		return y;
	}
	
	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * @param i Number of inserted element
	 * @param value String valueb eing inserted
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public void add(int i, String value) throws IndexOutOfBoundsException {
		if (i < 0 || i > size()){
			throw new IndexOutOfBoundsException("Number of the element is out of the range");}		

		resize();  // This will make sure A is large enough to hold the new element
		for (int j=n;j>i;j--){
			A[j]=A[j-1];
		}
		A[i]=value;
		n++;
	}
	
	/**
	 * Add an element to the back of the string.
	 * @param value String being added
	 */
	public void push_back(String value) {
		add(n, value);
	}
	
	/**
	 * Add an element to the front of the string.
	 * @param value String being added.
	 */
	public void push_front(String value) {
		add(0, value);
	}
	
	/**
	 * Remove element i from the array (renumbering everything past it)
	 * @param i element being removed
	 * @return   value that was removed
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public String remove(int i) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException("Number of the element is out of the range");}		
		String value = A[i];
		for(int j=i;j<n-1;j++){
			A[j]=A[j+1];
		}
		n--;
		return value;
	}
	
	/**
	 * Remove last element
	 * @return value of last element
	 */
	public String pop_back() {
		return remove(size()-1);
	}
	
	/**
	 * Remove first element
	 * @return value of first element
	 */
	public String pop_front() {
		return remove(0);
	}
	
	/**
	 * If the array is full (that is: A.length == n), resize it so it can hold one more element.
	 */
	private void resize() {
		 
		if (n+1>=A.length){
			String [] B=new String[n+1];
			for (int j=0;j<n;j++){
				B[j]=A[j];
			}
			A=B;
		}
			
	}
	
	/**
	 * Search for a query string in the array.
	 * @param query String being seqrched for
	 * @return boolean
	 */
	public boolean in(String query) {
		for(int i=0;i<n;i++){
			if(A[i].equals(query)){return true;} 
		}
		return false;
	}
	
	/**
	 * Remove all elements.
	 */
	public void clear() {
		this.A = new String[1];
		this.n = 0;
	}
	
	/**
	 * Return True if the container is empty.
	 */
	public boolean isEmpty() {
		return n==0;
	}
}
