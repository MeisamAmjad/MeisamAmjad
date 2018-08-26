package unit_test;

import static org.junit.Assert.*;
import solution01.Summation01;
import solution02.Summation02;

import org.junit.Test;

public class SummationTester {

	@Test
	public void test02() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=1602;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation02.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 02 for targer %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation02.count);
		assertEquals(result[0], 1);
		assertEquals(result[1], 31);
		
	}
	
	@Test
	public void test01() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=1602;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation01.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 01 for target %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation01.count);
		assertEquals(result[0], 1);
		assertEquals(result[1], 31);
		
	}

	@Test
	public void test04() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=98000;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation02.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 02 for targer %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation02.count);
		assertEquals(result[0], 16);
		assertEquals(result[1], 15);
		
	}
	
	@Test
	public void test03() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=98000;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation01.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 01 for target %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation01.count);
		assertEquals(result[0], 15);
		assertEquals(result[1], 16);
		
	}

	@Test
	public void test06() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=90100;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation02.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 02 for targer %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation02.count);
		assertEquals(result[0], 16);
		assertEquals(result[1], 18);
		
	}
	
	@Test
	public void test05() {
		int[] input={2,7,11,1,3,-1,15,1000,2000,3000,4000,5000,
                6000,7000,8000,90000,1000,100,200,300,400,500,
                600,700,800,900,1200,1300,1400,1500,1600};
                
		int target=90100;
		long startTime=0;
		long endTime=0;
		
		startTime=System.nanoTime();
		int[] result=new int[2];
		for(int i=0;i<200000;i++)
			result=Summation01.twoSum(input, target);
		endTime=System.nanoTime();
		System.out.printf("Result for Solution 01 for target %d is: %d , %d in %1.5f second, and it got run %d times \n",target,result[0],result[1],(double)(endTime-startTime)/1000000000,Summation01.count);
		assertEquals(result[0], 16);
		assertEquals(result[1], 18);
		
	}
}
