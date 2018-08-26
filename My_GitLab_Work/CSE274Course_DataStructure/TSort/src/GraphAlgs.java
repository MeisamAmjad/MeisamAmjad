import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import org.hamcrest.core.Is;

public class GraphAlgs {
	
	/**
	 * Read a graph from a file where nodes are labeled by integers from 0 to n-1.
	 * @param file
	 * @param list_graph
	 * @return
	 */
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
	
	/**
	 * Read a graph from a file where nodes are labeled by strings.
	 * @param file
	 * @param list_graph
	 * @return
	 */
	static public Graph readNamedGraph(String file, boolean list_graph) {
		Graph G;
		try {
			Scanner S = new Scanner(new File(file));
			int n = S.nextInt();
			G = list_graph ? new ListGraph(n) : new MatrixGraph(n);
			for (int i=0; i < n; i++)
				G.addName(i, S.next());
			
			while (S.hasNext()) {
				String u = S.next();
				String v = S.next();
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
	
	
	/**
	 * Write a graph to a file using node numbers.
	 * @param G
	 * @param file
	 */
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
	
	/**
	 * Write a graph to a file using strings.
	 * @param G
	 * @param file
	 */
	static public void writeNamedGraph(Graph G, String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(G.numNodes());
			for (int i=0; i < G.numNodes(); i++)
				out.println(G.getName(i));
			for (int i=0; i < G.numNodes(); i++) {
				ArrayList<Pair<Integer,Double>> P = G.adjacentNodes(i);
				for (Pair<Integer,Double> j : P) {
					if (i < j.first) {
						out.println(G.getName(i) + " " + G.getName(j.first) + " " + j.second);
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
	
	/**
	 * Given a directed graph, return an array list of node numbers that have been topologically sorted.
	 * (That is, if there is an edge from node a to node b, b must come after a in the graph.)
	 * You may assume that there are no cycles in G (so it is always possible to sort.)
	 * EXTRA CREDIT: Do not assume there are no cycles, and return an empty array if that is the case.
	 * @param G
	 * @return
	 */
	static public ArrayList<Integer> topologicalSort(Graph G) {
		int[] inDegree=new int[G.num_nodes];
		Queue<Integer> zeroInDegreeQ=new LinkedList<>();
		ArrayList<Integer> sortedList=new ArrayList<>();
		int _howManyVisited=0;
		
		inDegree(G,inDegree);
		fillupQ(G,inDegree,zeroInDegreeQ);
		
		while (!zeroInDegreeQ.isEmpty()){
			int j=zeroInDegreeQ.poll();
			sortedList.add(j);
			for (Pair<Integer, Double> nodes:G.adjacentNodes(j))
				if (--inDegree[nodes.first]<=0) zeroInDegreeQ.add(nodes.first);
			_howManyVisited++;
		}
		return checkCycle(G.num_nodes,_howManyVisited)?new ArrayList<>():sortedList;
	}
	/**
	 * checks all adjacentNodes and add a degree depends on its number
	 * @param G the original graph
	 * @param iD the array that keeps the inDegree numbers
	 */
	private static void inDegree(Graph G, int[] iD){
		for (int i=0;i<iD.length;i++)
			for(Pair<Integer, Double> adj:G.adjacentNodes(i)) 
				iD[adj.first]++;
	}
	/**
	 * Create a queue of vertices with no incoming edges. (zero inDegree)
	 * @param G the original graph.
	 * @param iD the array of inDegree elements.
	 * @param Q the queue that should be filled up.
	 */
	private static void fillupQ(Graph G, int[] iD, Queue<Integer> Q){
		for (int i=0;i<iD.length;i++) if (iD[i]==0) Q.add(i);
	}
	/**
	 * it checks whether there is any cycle in graph
	 * @param V the number of the nodes in graph
	 * @param hMV number visited nodes
	 * @return
	 */
	private static boolean checkCycle(int V, int hMV){
		return V!=hMV;
	}
}
