import data_structures.*;

public class main_class {

	public static void main(String[] args) {
		Sequence<String> S = new StringArray();
		S.add(0, "a");
		S.add(1,  "b");
	    System.out.println(S.get(0) + " " + S.get(1) + " " + S.size());
	    S.add(2,  "c");
	    System.out.println(S.get(0) + " " + S.get(1) + " " + S.get(2)+ " " + S.size());
	    S.remove(0);
	    System.out.println(S.get(0) + " " + S.get(1)+ " " + S.size());
	    
	}
}