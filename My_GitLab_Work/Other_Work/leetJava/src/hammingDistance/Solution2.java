package hammingDistance;

public class Solution2 {
	public static int hammingDistance(int x, int y){
		int xor = x ^ y, count = 0;
        
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
	}

}
