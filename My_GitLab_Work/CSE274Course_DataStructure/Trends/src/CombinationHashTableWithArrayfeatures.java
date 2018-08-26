import java.util.ArrayList;
import java.util.Collections;

public class CombinationHashTableWithArrayfeatures<T1,T2,T3> {
	int a;
	int b;
	int m;
	ArrayList<Triple<T1,T2,T3>> table;
	
	CombinationHashTableWithArrayfeatures() {
		//7, 1, 25014
		//1, 0, 5303000
		this(7, 1, 5303000);
	}
	
	CombinationHashTableWithArrayfeatures(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Triple<T1,T2,T3>>(Collections.nCopies(m, null));
	}
	int strHash(String key) {
		 
		int total=0;
		char [] value=((String) key).toCharArray();
		for(char c:value){
			total=(total*256+c*c)%m;
		}
		total+=m;
		total=total%m;
		return total;
	}
	int intHash(Integer key) {
		return (((int)key*a) + b) % m;
	}
	
	int properIndexForAdding(Integer key,String s) {//
		int k=intHash(key);
		int c=0;
		while (table.get(k)!=null && c!=m){
			k=k==m-1?k=0:k+1;
			c++;
		}
		return k;
	}
	int findIndex(Integer key,String s){// It gets a word and returns it's place (index) inside the array
		int k=intHash(key);
		int c=0;
		while (table.get(k)!=null && c!=m){
			Triple<T1, T2,T3> H=table.get(k);
			if (H.second!=null && H.second.equals(s))	return k;
			k=k==m-1?k=0:k+1;
			c++;
		}
		return -1; //It returns -1 if it could not find the word 
	}
}