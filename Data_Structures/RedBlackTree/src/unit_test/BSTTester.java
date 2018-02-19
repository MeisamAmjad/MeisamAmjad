package unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import data_structures.BST;
import data_structures.BSTNode;
public class BSTTester {
	private void create_helper( BST<Integer,Integer> D, int a, int b ) {
		if ( a == b - 1 ) {
			D.insert( new Integer( 2 * a ), new Integer( 2 * a + 1 ) );
		}
		else if ( a < b - 1 ) {
			int m = ( a + b ) / 2;
			D.insert( new Integer( 2 * m ), new Integer( 2 * m + 1 ) );
			create_helper( D, a, m );
			create_helper( D, m + 1, b ) ;
		}
	}
	
	private BST<Integer,Integer> create_BST(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	
	@Test
	public void testHeight() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());
		assertEquals("Height should be 6, not " + T.height(), T.height(), 6);
	}
	
	@Test
	public void testMin() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());
		assertEquals("Min should be 0, not " + (int)T.min(), T.min(), new Integer(0));
	}
	
	@Test
	public void testMin2() {
		BST<Integer,Integer> T = new BST<>();
		for (int i=20; i < 40; i++) {
			T.insert(new Integer(i), i);
			assertTrue(T.check_structure());
		}
		assertEquals("Min should be 20, not " + (int)T.min(), T.min(), new Integer(20));
	}
	
	@Test
	public void testMax() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());
		assertEquals("Max should be 126, not " + (int)T.max(), T.max(), new Integer(126));
	}
	
	@Test
	public void testMax2() {
		BST<Integer,Integer> T = new BST<>();
		for (int i=20; i < 40; i++) {
			T.insert(new Integer(40-i), i);
			assertTrue(T.check_structure());
		}
		assertEquals("Max should be 20, not " + (int)T.max(), T.max(), new Integer(20));
	}
	
	@Test
	public void testInOrder(){
		BST<Integer, Integer> T=create_BST(50);
		assertTrue(T.check_structure());
		ArrayList<BSTNode<Integer, Integer>> RList=T.inOrderSortRecursive();
		ArrayList<BSTNode<Integer, Integer>> RTravers=T.inOrderSortTravers();
		assertTrue(RList.size()==RTravers.size());
		for(int i=0;i<RList.size();i++){
			assertTrue( RList.get(i).getKey()==RTravers.get(i).getKey() 
					&&	RList.get(i).getValue()==RTravers.get(i).getValue() );
		}	
	}
	
	@Test
	public void testPreOrder(){
		BST<Integer, Integer> T=create_BST(50);
		assertTrue(T.check_structure());
		ArrayList<BSTNode<Integer, Integer>> RList=T.preOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> RTravers=T.preOrderTravers();
		assertTrue(RList.size()==RTravers.size());
		for(int i=0;i<RList.size();i++){
			assertTrue( RList.get(i).getKey()==RTravers.get(i).getKey() 
					&&	RList.get(i).getValue()==RTravers.get(i).getValue() );
		}	
	}
	
	@Test
	public void testDeepCopy(){
		BST<Integer,Integer> T=create_BST(150);
		assertTrue(T.check_structure());
		
		BSTNode<Integer, Integer> dcpyT = T.deepCopyRecursive_preOrder();
		BST<Integer, Integer> newT=new BST<>(dcpyT);
		
		ArrayList<BSTNode<Integer, Integer>> originList=T.preOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> newList=newT.preOrderRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
		
		originList=T.inOrderSortRecursive();
		newList=newT.inOrderSortRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
		
		originList=T.postOrderRecursive();
		newList=newT.postOrderRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
	}
	
	@Test
	public void testPostOrder(){
		BST<Integer, Integer> T=create_BST(1150);
		assertTrue(T.check_structure());
		ArrayList<BSTNode<Integer, Integer>> preLOld=T.preOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> inLOld=T.inOrderSortRecursive();
		
		BSTNode<Integer, Integer> dcpyT = T.deepCopyRecursive_preOrder();
		BST<Integer, Integer> newT=new BST<>(dcpyT);
		
		ArrayList<BSTNode<Integer, Integer>> RList=newT.postOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> RTravers=newT.postOrderTravers();
		assertTrue(RList.size()==RTravers.size());
		for(int i=0;i<RList.size();i++){
			assertTrue( RList.get(i).getKey()==RTravers.get(i).getKey() 
					&&	RList.get(i).getValue()==RTravers.get(i).getValue() );
		}	
		
		
		ArrayList<BSTNode<Integer, Integer>> preLNew=T.preOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> inLNew=T.inOrderSortRecursive();
		assertTrue(preLOld.size()==preLNew.size());
		for(int i=0;i<preLOld.size();i++){
			assertTrue( preLOld.get(i).getKey()==preLNew.get(i).getKey() 
					&&	preLOld.get(i).getValue()==preLNew.get(i).getValue() );
		}
		assertTrue(inLOld.size()==inLNew.size());
		for(int i=0;i<inLOld.size();i++){
			assertTrue( inLOld.get(i).getKey()==inLNew.get(i).getKey() 
					&&	inLOld.get(i).getValue()==inLNew.get(i).getValue() );
		}
	}
	
	@Test
	public void testDeepCopyTraverse_preOrder(){
		BST<Integer,Integer> T=create_BST(1150);
		assertTrue(T.check_structure());
		
		BSTNode<Integer, Integer> dcpyT = T.deepCopyRecursive_preOrder();
		BST<Integer, Integer> newT=new BST<>(dcpyT);
		
		ArrayList<BSTNode<Integer, Integer>> originList=T.preOrderRecursive();
		ArrayList<BSTNode<Integer, Integer>> newList=newT.preOrderRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
		
		originList=T.inOrderSortRecursive();
		newList=newT.inOrderSortRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
		
		originList=T.postOrderRecursive();
		newList=newT.postOrderRecursive();
		assertTrue(originList.size() == newList.size());
		for(int i = 0; i < originList.size(); i ++){
			assertTrue( originList.get(i).getKey() == newList.get(i).getKey() 
					&&	originList.get(i).getValue() == newList.get(i).getValue() );
		}
	}
	
	@Test
	public void deepCopyTester(){
		BST<Integer, Integer> T = create_BST( 7 );
		
		BST<Integer, Integer> newT1 = new BST<>( T.deepCopy( T.getRoot() ) );
		
		BST<Integer, Integer> newT2 = new BST<>( T.deepCopy( T.getRoot() ) );
		
		int originsize = T.size();
		assertTrue( T.check_structure() && newT1.check_structure() && newT2.check_structure() );
		
		assertTrue( compareTrees( T.getRoot(), newT1.getRoot() ) );
		assertTrue( compareTrees( T.getRoot(), newT2.getRoot() ) );
		assertTrue( compareTrees( newT1.getRoot(), newT2.getRoot() ) );
		
		ArrayList<BSTNode<Integer, Integer>> postOrderT = newT1.postOrderTravers();
		assertTrue( compareTrees( T.getRoot(), newT2.getRoot() ) );
		
		// The following Must be False now.
		assertFalse( compareTrees( T.getRoot(), newT1.getRoot() ) ); // It got changed because we did post-order traversal over it.
		assertFalse( compareTrees( newT2.getRoot(), newT1.getRoot() ) ); // It got changed because we did post-order traversal over it.
	}
	
	@Test
	public void randomTreemakerTest(){
		BST<Integer, Integer> T1 = create_BST( 501 );
		BST<Integer, Integer> T2 = create_BST( 501 );
		assertTrue(  compareTrees( T1.getRoot(), T2.getRoot() ) );
		//T1.print_structure();
		//System.out.println("--------");
		//T2.print_structure();
	}
	
	private boolean compareTrees( BSTNode<Integer, Integer> T1, BSTNode<Integer, Integer> T2){
		return ( T1 == null && T2 == null )
				? true
				: ( (T1 == null && T2 != null ) || (T1 != null && T2 == null) || ( T1.getKey().compareTo( T2.getKey() ) != 0 ) )
					? false
					: compareTrees( T1.getLeft(), T2.getLeft() ) && compareTrees( T1.getRight(), T2.getRight() );
		
	}
}