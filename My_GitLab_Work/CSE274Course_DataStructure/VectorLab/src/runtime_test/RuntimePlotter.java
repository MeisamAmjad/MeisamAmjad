package runtime_test;

import data_structures.*;
import support_code.CurvePlotter;

public class RuntimePlotter {
	static private Sequence sequence_generator(Sequence S, int n) {
		S.clear();
		S.resetOps();
		
		for (int i=0; i < n; i++)
			S.push_back("" + i);
		
		return S;
	}

	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_get() {
		
		// First: Create the Canvas on which we will plot runtime, and specify 
		// the bounds on the x-axis (0 to 500) and on the y-axis (0 to 5), as
		// well as a title for the graph.
		CurvePlotter C = new CurvePlotter(0, 500, 0, 5, "Runtime: get");

		//Create the sequence object we are working with.
		Sequence S = new StringVector();
		
		for (int n=1; n < 500; n ++) {   // For problem sizes ranging from 1 to 500: 
			sequence_generator(S, n);    //    Clear the sequence and add n elements
			S.resetOps();                //    Reset the number of basic ops 
			S.get(S.size()/2);           //    Get an element from the middle
			C.add_point(n,  S.numOps(), "blue");   // Plot the number of ops that get required
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_set() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 5, "Runtime: set");
		Sequence S = new StringVector();
		
		for (int n=1; n < 500; n ++) {
			sequence_generator(S, n);    //     Clear the sequence and add n elements
			S.resetOps();                //     Reset the number of basic ops 
			S.set((S.size()/2),"0");		 //		set element n/2 in a sequence
			C.add_point(n,  S.numOps(), "blue");   // Plot the number of ops that get required
			
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling push_back on a list of size n
	 */
	static void plot_single_push_back() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: single push back");
		Sequence S = new StringVector();
		
		for (int n=0; n < 500; n++) {
			sequence_generator(S, n);                // Create list of n elements.
			S.resetOps();
			S.push_back("0");
			C.add_point(n, S.numOps(), "blue");      // Plot number of ops push_back required
		}
		
		C.draw();
	}
	
	/*
	 * Plot the runtime for calling push_front on a list of size n
	 */
	static void plot_single_push_front() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: single push front");
		Sequence S = new StringVector();
		
		for (int n=0; n < 500; n++) {
			sequence_generator(S, n);                // Create list of n elements.
			S.resetOps();
			S.push_front("0");
			C.add_point(n, S.numOps(), "blue");		 // Plot number of ops push_front required
			
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling push_back n times
	 */
	static void plot_multi_push_back() {
		CurvePlotter C = new CurvePlotter(0, 50, 0, 1000, "Runtime: multiple push back");
		Sequence S = new StringVector();
		
		for (int n=0; n < 500; n++) {
			S.push_back("0");                       // Push the next element
			C.add_point(n, S.numOps(), "blue");     // Plot number of ops its taken, in total,
		}	                                        // to push all n elements.
		
		
		C.draw();
	}

	/**
	 * Plot the runtime for calling push_front n times
	 */
	static void plot_multi_push_front() {
		CurvePlotter C = new CurvePlotter(0, 50, 0, 1000, "Runtime: multiple push front");
		Sequence S = new StringVector();
		

		for (int n=0; n < 500; n++) {
			S.push_front("0");                       // Push the next element
			C.add_point(n, S.numOps(), "blue");     // Plot number of ops its taken, in total,
		}
		// TO DO: Emulate the last method for push_front.
		
		C.draw();
	}
	
	
	public static void main(String[] args) {
		plot_get();
		plot_set();
		plot_single_push_back();
		plot_single_push_front();
		plot_multi_push_back();
		plot_multi_push_front();
	}
	
}
