package solution02;
import java.util.Map;
import java.util.HashMap;

public class Summation02{
	public static int count=0;
	public static int[] twoSum(int[] list, int target){
		// Check if the list is empty or has only one element
        int length=list.length;
        int mid=length/2;
        count=0;
        if (length<2) throw new Error("The List either has only one element or ot is empty");
        // Define a HashMap for saving each element of the list gradually 
        Map<Integer, Integer> map=new HashMap<Integer,Integer>();
        // The main loop that goes through each element of the list and check for finding the target
        // among the list list in O(n) time
        for (int i = 0; i < mid || mid+i<length; i++){
		    if (i!=mid){
		    	map.put(list[i],i+1);
		    	count++;
		    	if (map.containsKey(target-list[i])) 
		    		return new int[]{map.get(target-list[i]) , i + 1};
        	}
        	if (mid+i<length) {
        		map.put(list[mid+i],mid+i+1);
        		count++;
        		if (map.containsKey(target-list[mid+i]))
        			return new int[]{map.get(target-list[mid+i]) , mid+i+1 };
        	}
		}
       /*
        for (int i = 0; i <= length/2; i++){
		    map.put(list[i],i+1);
		    map.put(list[length-i-1],length-i);
		    count++;
        	if (map.containsKey(target-list[i])) 
		        return new int[]{map.get(target-list[i]) , i + 1};
        	count++;
			if (map.containsKey(target-list[length-i-1]))
				return new int[]{map.get(target-list[length-i-1]) , length-i };
		}
		*/
        // Return the two dimention array with zero values inside
        return  new int[]{0,0};
	}
}
