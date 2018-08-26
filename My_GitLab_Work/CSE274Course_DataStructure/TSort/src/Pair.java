

public class Pair<type1 extends Comparable<type1>, type2> implements Comparable<Pair<type1,type2>> {
	type1 first;
	type2 second;
	
	public Pair(type1 first, type2 second) {
		this.first = first;
		this.second = second;
	}
	
	public int compareTo(Pair<type1, type2> p) {
		return first.compareTo(p.first);
	}

}
