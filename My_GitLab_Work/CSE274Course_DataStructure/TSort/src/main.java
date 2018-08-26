import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Graph G = GraphAlgs.readNamedGraph("Data/cse.txt", true);
		ArrayList<Integer> A = GraphAlgs.topologicalSort(G);
		for (int n : A)
			System.out.print(G.getName(n) + " ");
		System.out.println("");
	}

}
