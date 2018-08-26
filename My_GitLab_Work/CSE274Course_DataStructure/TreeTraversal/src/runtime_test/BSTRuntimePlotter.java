package runtime_test;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import data_structures.*;
import support_code.CurvePlotter;


public class BSTRuntimePlotter {
	
	/**
	 * Reset D and add all integer values from 0 to n-1 (as doubles).
	 * @param D   Dictionary to be modified.
	 * @param n   Limit on size.
	 * @return    Reference to dictionary.
	 */
	static private void dictionary_generator(Dictionary<Integer,String> D, int n, int c, Random rng) {
		D.clear();
		D.resetOps();
		
		ArrayList<Integer> L = new ArrayList<Integer>();
		for (int i=0; i < n; i++)
			L.add(new Integer(c*i));
		for (int i=0; i < n; i++) {
			int j = rng.nextInt(n);
			if (i != j)
				Collections.swap(L,  i,  j);
		}
		
		for (int i=0; i < n; i++)
			D.insert(new Integer(L.get(i)), "" + i);
		
	}
	
	static private void dictionary_generator_linear(Dictionary<Integer,String> D, int n, int c) {
		D.clear();
		D.resetOps();
		
		for (int i=0; i < n; i++)
			D.insert(new Integer(c*i), "" + i);
		
	}

	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_find_random() {
		int size_limit = 2000;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 50, "Runtime: find (random insertion)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator(D, size-1, 1, rng);
				D.resetOps();
				Integer val = new Integer(rng.nextInt()+1);
				D.find(val);
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	
	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_find_linear() {
		int size_limit = 500;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 500, "Runtime: find (linear insertion)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator_linear(D, size-1, 1);
				D.resetOps();
				Integer val = new Integer(rng.nextInt()+1);
				D.find(val);
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	
	static void plot_insert_random() {
		int size_limit = 2000;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 50, "Runtime: insert (random)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator(D, size-1, 2, rng);
				D.resetOps();
				Integer val = new Integer(2*rng.nextInt(size)+1);    
				D.insert(val, "X");
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	
	static void plot_insert_linear() {
		int size_limit = 500;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 500, "Runtime: insert (linear)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator_linear(D, size, 2);
				D.resetOps();
				Integer val = new Integer(2*rng.nextInt(size)+1);    
				D.insert(val, "X");
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	
	static void plot_remove_random() {
		int size_limit = 2000;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 50, "Runtime: remove (random)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator(D, size, 1, rng);
				D.resetOps();
				Integer val = new Integer(rng.nextInt(size)); 
				D.remove(val);
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	
	static void plot_remove_linear() {
		int size_limit = 500;
		int step_size = 20;
		int num_trials = 500;
		
		CurvePlotter C = new CurvePlotter(0, size_limit, 0, 500, "Runtime: remove (linear)");
		Dictionary<Integer,String> D = new BST<Integer,String>();
		
		Random rng = new Random();
		

		for (int size=20; size < size_limit; size += step_size) {
			int total = 0;
			int max = 0;
			int min = 100000;

			for (int trial=0; trial < num_trials; trial++) {
				dictionary_generator_linear(D, size, 1);
				D.resetOps();
				Integer val = new Integer(rng.nextInt(size));    
				D.remove(val);
				int n = D.numOps();
				
				total += n;
				max = Math.max(max, n);
				min = Math.min(min, n);
			}
			C.add_point(size, total/num_trials, "red");
			C.add_point(size,  max, "blue");
			C.add_point(size, min, "green");
		}

		C.draw();
	}
	

	
	public static void main(String[] args) {
		plot_find_random();
		plot_find_linear();
		plot_insert_random();
		plot_insert_linear();
		plot_remove_random();
		plot_remove_linear();
	}
}
