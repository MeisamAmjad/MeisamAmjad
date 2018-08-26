package hammingDistance;

public class Solution1 {
	public static int hammingDistance(int x, int y){
		return Integer.bitCount(x^y);
	}

}
