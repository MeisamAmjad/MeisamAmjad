package solution01;

import java.util.Map;
import java.util.HashMap;

public class Summation01{
	public static int count=0;
    public static int[] twoSum(int[] list, int target){
        // Check if the list is empty or has only one element
        int length=list.length;
        count=0;
        if (length<2) throw new Error("The List either has only one element or ot is empty");
        // Define a HashMap for saving each element of the list gradually 
        Map<Integer, Integer> map=new HashMap<Integer,Integer>();
        // The main loop that goes through each element of the list and check for finding the target
        // among the list numbers in O(n) time
        for (int i=0; i<length; map.put(list[i],++i)){
        	count++;
            if (map.containsKey(target-list[i]))
                return new int[]{map.get(target-list[i]),i+1};
        }
        // Return the two dimention array with zero values inside
        return  new int[]{0,0};

    }
}
