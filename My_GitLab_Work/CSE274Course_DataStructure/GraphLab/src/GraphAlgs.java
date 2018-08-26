import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GraphAlgs {
	
	static public Graph readGraph(String file, boolean list_graph) {
		Graph G;
		try {
			Scanner S = new Scanner(new File(file));
			int n = S.nextInt();
			G = list_graph ? new ListGraph(n) : new MatrixGraph(n);
			
			while (S.hasNext()) {
				int u = S.nextInt();
				int v = S.nextInt();
				Double w = S.nextDouble();
				G.setWeight(u, v, w);
			}
			S.close();
			return G;
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		return null;
	}
	
	static public void writeGraph(Graph G, String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(G.numNodes());
			for (int i=0; i < G.numNodes(); i++) {
				ArrayList<Pair<Integer,Double>> P = G.adjacentNodes(i);
				for (Pair<Integer,Double> j : P) {
					if (i < j.first) {
						out.println(i + " " + j.first + " " + j.second);
					}
				}
				
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static public Graph randomGraph(int n, double p, boolean list_graph) {
		Random rng = new Random();
		Graph G = GraphFactory.make_graph(n, list_graph);
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++)
				if (i != j && rng.nextDouble() < p)
					G.setWeight(i, j, ((double)(rng.nextInt(19)+1)));
		}
		return G;
	}
	
}
