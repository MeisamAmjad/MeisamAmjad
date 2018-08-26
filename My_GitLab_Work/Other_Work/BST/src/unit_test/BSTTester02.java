package unit_test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import data_structures.BST;
import data_structures.TreeNode;

public class BSTTester02 {
	ArrayList<int []> bfsOutArrIns=new ArrayList<>(),
						bfsOutArrRmv=new ArrayList<>();
	
	ArrayList<int []> inOrderOutArrIns=new ArrayList<>(),
						inOrderOutArrRmv=new ArrayList<>();
	
	ArrayList<int []> preOrderOutArrIns=new ArrayList<>(),
						preOrderOutArrRmv=new ArrayList<>();
	
	ArrayList<int []> postOrderOutArrIns=new ArrayList<>(),
						postOrderOutArrRmv=new ArrayList<>();
	
	public BSTTester02(){
		bfsOutArrIns.add( new int[]{4,2,8,0,6});
		bfsOutArrIns.add( new int[]{10,4,16,2,8,14,18,0,6,12});
		bfsOutArrIns.add( new int[]{20,10,30,4,16,26,36,2,8,14,18,24,28,34,38,0,6,12,22,32});
		bfsOutArrIns.add( new int[]{50,24,76,12,38,64,88,6,18,32,44,58,70,82,94,2,10,16,22,28,36,42,48,54,62,68,74,80,86,92,98,0,4,8,14,20,26,30,34,40,46,52,56,60,66,72,78,84,90,96});
		bfsOutArrIns.add( new int[]{70,34,106,16,52,88,124,8,26,44,62,80,98,116,132,4,12,22,30,40,48,58,66,76,84,94,102,112,120,128,136,2,6,10,14,20,24,28,32,38,42,46,50,56,60,64,68,74,78,82,86,92,96,100,104,110,114,118,122,126,130,134,138,0,18,36,54,72,90,108});
		
		bfsOutArrRmv.add( new int[]{6,2,0});
		bfsOutArrRmv.add( new int[]{12,4,18,2,8,14,0,6});
		bfsOutArrRmv.add( new int[]{20,10,32,6,16,26,36,0,8,14,18,24,28,34,38,12,22});
		bfsOutArrRmv.add( new int[]{52,24,76,12,40,64,88,6,18,32,46,58,70,82,94,2,10,16,22,28,36,42,48,54,62,68,74,80,86,92,98,0,4,8,14,20,26,30,34,56,60,66,72,78,84,90,96});
		bfsOutArrRmv.add( new int[]{70,34,106,16,52,88,124,8,26,44,62,80,98,118,132,4,12,22,30,40,48,58,66,76,84,94,104,112,120,128,138,2,6,10,14,20,24,28,32,38,42,46,50,56,60,64,68,74,78,82,86,92,96,100,110,114,122,126,130,134,0,18,36,54,90,108});
		
		inOrderOutArrIns.add( new int[]{0,2,4,6,8});
		inOrderOutArrIns.add( new int[]{0,2,4,6,8,10,12,14,16,18});
		inOrderOutArrIns.add( new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38});
		inOrderOutArrIns.add( new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98});
		inOrderOutArrIns.add( new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98,100,102,104,106,108,110,112,114,116,118,120,122,124,126,128,130,132,134,136,138});
		
		inOrderOutArrRmv.add( new int[]{0,2,6});
		inOrderOutArrRmv.add( new int[]{0,2,4,6,8,12,14,18});
		inOrderOutArrRmv.add( new int[]{0,6,8,10,12,14,16,18,20,22,24,26,28,32,34,36,38});
		inOrderOutArrRmv.add( new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,40,42,46,48,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98});
		inOrderOutArrRmv.add( new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,74,76,78,80,82,84,86,88,90,92,94,96,98,100,104,106,108,110,112,114,118,120,122,124,126,128,130,132,134,138});
		
		preOrderOutArrIns.add( new int[]{4,2,0,8,6});
		preOrderOutArrIns.add( new int[]{10,4,2,0,8,6,16,14,12,18});
		preOrderOutArrIns.add( new int[]{20,10,4,2,0,8,6,16,14,12,18,30,26,24,22,28,36,34,32,38});
		preOrderOutArrIns.add( new int[]{50,24,12,6,2,0,4,10,8,18,16,14,22,20,38,32,28,26,30,36,34,44,42,40,48,46,76,64,58,54,52,56,62,60,70,68,66,74,72,88,82,80,78,86,84,94,92,90,98,96,});
		preOrderOutArrIns.add( new int[]{70,34,16,8,4,2,0,6,12,10,14,26,22,20,18,24,30,28,32,52,44,40,38,36,42,48,46,50,62,58,56,54,60,66,64,68,106,88,80,76,74,72,78,84,82,86,98,94,92,90,96,102,100,104,124,116,112,110,108,114,120,118,122,132,128,126,130,136,134,138});
		
		preOrderOutArrRmv.add( new int[]{6,2,0});
		preOrderOutArrRmv.add( new int[]{12,4,2,0,8,6,18,14});
		preOrderOutArrRmv.add( new int[]{20,10,6,0,8,16,14,12,18,32,26,24,22,28,36,34,38});
		preOrderOutArrRmv.add( new int[]{52,24,12,6,2,0,4,10,8,18,16,14,22,20,40,32,28,26,30,36,34,46,42,48,76,64,58,54,56,62,60,70,68,66,74,72,88,82,80,78,86,84,94,92,90,98,96});
		preOrderOutArrRmv.add( new int[]{70,34,16,8,4,2,0,6,12,10,14,26,22,20,18,24,30,28,32,52,44,40,38,36,42,48,46,50,62,58,56,54,60,66,64,68,106,88,80,76,74,78,84,82,86,98,94,92,90,96,104,100,124,118,112,110,108,114,120,122,132,128,126,130,138,134});
		
		postOrderOutArrIns.add( new int[]{0,2,6,8,4});
		postOrderOutArrIns.add( new int[]{0,2,6,8,4,12,14,18,16,10});
		postOrderOutArrIns.add( new int[]{0,2,6,8,4,12,14,18,16,10,22,24,28,26,32,34,38,36,30,20});
		postOrderOutArrIns.add( new int[]{0,4,2,8,10,6,14,16,20,22,18,12,26,30,28,34,36,32,40,42,46,48,44,38,24,52,56,54,60,62,58,66,68,72,74,70,64,78,80,84,86,82,90,92,96,98,94,88,76,50});
		postOrderOutArrIns.add( new int[]{0,2,6,4,10,14,12,8,18,20,24,22,28,32,30,26,16,36,38,42,40,46,50,48,44,54,56,60,58,64,68,66,62,52,34,72,74,78,76,82,86,84,80,90,92,96,94,100,104,102,98,88,108,110,114,112,118,122,120,116,126,130,128,134,138,136,132,124,106,70});
		
		postOrderOutArrRmv.add( new int[]{0,2,6});
		postOrderOutArrRmv.add( new int[]{0,2,6,8,4,14,18,12});
		postOrderOutArrRmv.add( new int[]{0,8,6,12,14,18,16,10,22,24,28,26,34,38,36,32,20});
		postOrderOutArrRmv.add( new int[]{0,4,2,8,10,6,14,16,20,22,18,12,26,30,28,34,36,32,42,48,46,40,24,56,54,60,62,58,66,68,72,74,70,64,78,80,84,86,82,90,92,96,98,94,88,76,52});
		postOrderOutArrRmv.add( new int[]{0,2,6,4,10,14,12,8,18,20,24,22,28,32,30,26,16,36,38,42,40,46,50,48,44,54,56,60,58,64,68,66,62,52,34,74,78,76,82,86,84,80,90,92,96,94,100,104,98,88,108,110,114,112,122,120,118,126,130,128,134,138,132,124,106,70});
	}

	private static void create_helper(BST<Integer,Integer> T, int low, int high) {
		if ( low < high ) {
			int m = ( low + high ) / 2;
			T.insert( new Integer( 2 * m ), new Integer( 2 * m + 1 ) );
			create_helper( T, low, m);
			create_helper( T, m + 1, high);
		}
	}
	
	private static BST<Integer,Integer> create_BST( int n ) {
		BST<Integer,Integer> T = new BST<Integer,Integer>();
		create_helper( T, 0, n);
		return T;
	}
	
	public void _testHelper( int N, int I, int[] rmvItems, String whichMethod ){
		BSTTester02 out = new BSTTester02();
		BST<Integer,Integer> T = create_BST(N);
		ArrayList<TreeNode<Integer, Integer>> A = new ArrayList<>();
		if ( "BFS".equals( whichMethod ) )	
			A = T.BFS();
		else if ( "preOrderRecursive".equals( whichMethod ) )
			A = T.preOrderRecursive();
		else if ( "preOrderTravers".equals( whichMethod ) )
			A = T.preOrderTravers();
		else if ( "inOrderSortTravers".equals( whichMethod ) )
			A = T.inOrderSortTravers();
		else if ( "inOrderSortRecursive".equals( whichMethod ) )
			A = T.inOrderSortRecursive();
		else if ( "postOrderRecursive".equals( whichMethod ) )
			A = T.postOrderRecursive();
		//else if ( "postOrderTravers".equals( whichMethod ) )
		//	A = T.postOrderTravers();
		assertTrue( "The size of the Tree should be:" + N, T.size() == N );
		assertTrue( "The size of the ArrayList should be:" + N, A.size() == N );
		
		// Tests the output ArrayList after Inserting
		for ( int i = 0; i < A.size(); i ++ )
			if( "BFS".equals( whichMethod ) )
				assertTrue( "The number [" + i + "] should be:" + out.bfsOutArrIns.get( I )[ i ], A.get(i).getKey() == out.bfsOutArrIns.get(I)[i] );
			else if ( "preOrderRecursive".equals( whichMethod ) || "preOrderTravers".equals( whichMethod ) )
				assertTrue( "The number [" + i + "] should be:" + out.preOrderOutArrIns.get(I)[i], A.get(i).getKey() == out.preOrderOutArrIns.get(I)[i] );
			else if ( "inOrderSortTravers".equals( whichMethod ) || "inOrderSortRecursive".equals( whichMethod ) ) 
				assertTrue( "The number [" + i + "] should be:" + out.inOrderOutArrIns.get(I)[i], A.get(i).getKey() == out.inOrderOutArrIns.get(I)[i] );
			else if ( "postOrderRecursive".equals( whichMethod ) ) 
				assertTrue( "The number [" + i + "] should be:" + out.postOrderOutArrIns.get(I)[i], A.get(i).getKey() ==out.postOrderOutArrIns.get(I)[i]);
		
		// Remove some nodes from the Tree, then making a new Arraylist and test 
		// it's value based on the used method.
		for ( int el : rmvItems ) T.remove(el);
		if ( "BFS".equals( whichMethod ) )	
			A = T.BFS();
		else if ( "preOrderRecursive".equals( whichMethod ) )
			A = T.preOrderRecursive();
		else if ( "preOrderTravers".equals( whichMethod ) )
			A = T.preOrderTravers();
		else if ( "inOrderSortTravers".equals( whichMethod ) )
			A = T.inOrderSortTravers();
		else if ( "inOrderSortRecursive".equals( whichMethod ) )
			A = T.inOrderSortRecursive();
		else if ( "postOrderRecursive".equals( whichMethod ) )
			A = T.postOrderRecursive();
		// Tests the output ArrayList after removing some nodes from the tree
		for ( int i = 0; i < A.size(); i ++ )
			if( "BFS".equals( whichMethod ) )
				assertTrue( "The number [" + i + "] should be:" + out.bfsOutArrRmv.get( I )[ i ], A.get(i).getKey() == out.bfsOutArrRmv.get(I)[i] );
			else if ( "preOrderRecursive".equals( whichMethod ) || "preOrderTravers".equals( whichMethod ) )
				assertTrue( "The number [" + i + "] should be:" + out.preOrderOutArrRmv.get(I)[i], A.get(i).getKey() == out.preOrderOutArrRmv.get(I)[i] );
			else if ( "inOrderSortTravers".equals( whichMethod ) || "inOrderSortRecursive".equals( whichMethod ) ) 
				assertTrue( "The number [" + i + "] should be:" + out.inOrderOutArrRmv.get(I)[i], A.get(i).getKey() == out.inOrderOutArrRmv.get(I)[i] );
			else if ( "postOrderRecursive".equals( whichMethod ) ) 
				assertTrue( "The number [" + i + "] should be:" + out.postOrderOutArrRmv.get(I)[i], A.get(i).getKey() ==out.postOrderOutArrRmv.get(I)[i]);
	}
	
	
	
	/**
	 * Check constructor and size 
	 */
	@Test
	public void constructor() {
		
		BST<Integer,Integer> T = create_BST( 10 );
		assertNotNull( "D should not be null", T );
		assertTrue( "check_structure failed", T.check_structure() );
		assertTrue( "Dictionary size should be 10", T.size() == 10);
	}
	
	
	@Test
	public void BFS_test(){
		
		// Part 01 - Change number of Nodes to 5
		int[] rmvItems = new int[]{ 4, 8 };
		_testHelper( 5, 0, rmvItems, "BFS" );
		
		// Part 02 - Change number of Nodes to 10
		rmvItems = new int[]{ 16, 10 };
		_testHelper( 10, 1, rmvItems, "BFS" );
		
		// Part 03 - Change number of Nodes to 20
		rmvItems = new int[]{ 30, 2, 4 };
		_testHelper( 20, 2, rmvItems, "BFS" );
		
		// Part 03 - Change the number of Nodes to 50
		rmvItems = new int[]{ 44, 38, 50 };
		_testHelper( 50, 3, rmvItems, "BFS" );
		
		// Part 04 - Change the number of Nodes to 70
		rmvItems = new int[]{ 116, 72, 136, 102 };
		_testHelper( 70, 4, rmvItems, "BFS" );
	}
	
	@Test
	public void PreOrder_test(){
		// Part 01 - Change number of Nodes to 5
		int[] rmvItems = new int[]{ 4, 8 };
		_testHelper(5, 0, rmvItems, "preOrderRecursive" );
		_testHelper(5, 0, rmvItems, "preOrderTravers" );
			
		// Part 02 - Change number of Nodes to 10
		rmvItems = new int[]{ 16, 10 };
		_testHelper( 10, 1, rmvItems, "preOrderRecursive" );
		_testHelper( 10, 1, rmvItems, "preOrderTravers" );
				
		// Part 03 - Change number of Nodes to 20
		rmvItems = new int[]{ 30, 2, 4 };
		_testHelper( 20, 2, rmvItems, "preOrderRecursive" );
		_testHelper( 20, 2, rmvItems, "preOrderTravers" );
		// Part 03 - Change the number of Nodes to 50
		rmvItems = new int[]{ 44, 38, 50 };
		_testHelper( 50, 3, rmvItems, "preOrderRecursive" );
		_testHelper( 50, 3, rmvItems, "preOrderTravers" );
				
		// Part 04 - Change the number of Nodes to 70
		rmvItems = new int[]{ 116, 72, 136, 102 };
		_testHelper( 70, 4, rmvItems, "preOrderRecursive" );
		_testHelper( 70, 4, rmvItems, "preOrderTravers" );
	}

	@Test
	public void InOrder_test(){
		// Part 01 - Change number of Nodes to 5
		int[] rmvItems = new int[]{ 4, 8 };
		_testHelper( 5, 0, rmvItems, "inOrderSortRecursive" );
		_testHelper( 5, 0, rmvItems, "inOrderSortTravers" );
			
		// Part 02 - Change number of Nodes to 10
		rmvItems = new int[]{ 16, 10 };
		_testHelper( 10, 1, rmvItems, "inOrderSortRecursive" );
		_testHelper( 10, 1, rmvItems, "inOrderSortTravers" );
				
		// Part 03 - Change number of Nodes to 20
		rmvItems = new int[]{ 30, 2, 4 };
		_testHelper( 20, 2, rmvItems, "inOrderSortRecursive" );
		_testHelper( 20, 2, rmvItems, "inOrderSortTravers" );
		
		// Part 03 - Change the number of Nodes to 50
		rmvItems = new int[]{ 44, 38, 50};
		_testHelper( 50, 3, rmvItems, "inOrderSortRecursive" );
		_testHelper( 50, 3, rmvItems, "inOrderSortTravers" );
				
		// Part 04 - Change the number of Nodes to 70
		rmvItems = new int[]{ 116, 72, 136, 102 };
		_testHelper( 70, 4, rmvItems, "inOrderSortRecursive" );
		_testHelper( 70, 4, rmvItems, "inOrderSortTravers" );
	}
	
	@Test
	public void PostOrder_test(){
		// Part 01 - Change number of Nodes to 5
		int[] rmvItems = new int[]{ 4, 8 };
		_testHelper( 5, 0, rmvItems, "postOrderRecursive" );
			
		// Part 02 - Change number of Nodes to 10
		rmvItems = new int[]{ 16, 10 };
		_testHelper( 10, 1, rmvItems, "postOrderRecursive" );
				
		// Part 03 - Change number of Nodes to 20
		rmvItems = new int[]{ 30, 2, 4 };
		_testHelper( 20, 2, rmvItems, "postOrderRecursive" );
		
		// Part 03 - Change the number of Nodes to 50
		rmvItems = new int[]{ 44, 38, 50 };
		_testHelper( 50, 3, rmvItems, "postOrderRecursive" );	
				
		// Part 04 - Change the number of Nodes to 70
		rmvItems = new int[]{ 116, 72, 136, 102 };
		_testHelper( 70, 4, rmvItems, "postOrderRecursive" );
	}
	
	
	@Test
	public void rootTest(){
		BST<Integer, Integer> T = new BST<>(null);
		assertNull( "It should return null", T.BFS() );
		assertNull( "It should return null", T.postOrderRecursive() );
		assertNull( "It should return null", T.inOrderSortRecursive() );
		assertNull( "It should return null", T.preOrderRecursive() );
	}

}
