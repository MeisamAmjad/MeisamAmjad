package runtime_test;

import data_structures.*;
import support_code.CurvePlotter;


public class RuntimePlotter {
	static private Sequence<String> sequence_generator(Sequence<String> S, int n) {
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
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: get");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=1; n < 500; n ++) {
			sequence_generator(S, n);
			S.resetOps();
			S.get(S.size()/2);
			C.add_point(n,  S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_set() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: set");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=1; n < 500; n ++) {
			sequence_generator(S, n);
			S.resetOps();
			S.set(S.size()/2, "0");
			C.add_point(n,  S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling push_back on a list of size n
	 */
	static void plot_single_push_back() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: single push back");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=0; n < 500; n++) {
			sequence_generator(S, n);
			S.resetOps();
			S.push_back("0");
			C.add_point(n, S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		
		C.draw();
	}
	
	/*
	 * Plot the runtime for calling push_front on a list of size n
	 */
	static void plot_single_push_front() {
		CurvePlotter C = new CurvePlotter(0, 500, 0, 500, "Runtime: single push front");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=0; n < 500; n++) {
			sequence_generator(S, n);
			S.resetOps();
			S.push_front("0");
			C.add_point(n, S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling push_back n times
	 */
	static void plot_multi_push_back() {
		CurvePlotter C = new CurvePlotter(0, 50, 0, 1000, "Runtime: multiple push back");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=0; n < 500; n++) {
			S.push_back("0");
			C.add_point(n, S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
//		System.out.println(S.size()+"  "+S.numOps());
		C.draw();
	}

	/**
	 * Plot the runtime for calling push_front n times
	 */
	static void plot_multi_push_front() {
		CurvePlotter C = new CurvePlotter(0, 50, 0, 500, "Runtime: multiple push front");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=0; n < 500; n++) {
			S.push_front("0");
			C.add_point(n, S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		C.draw();
	}
	
	/**
	 */
	static void plot_in() {
		CurvePlotter C = new CurvePlotter(0, 50, 0, 500, "Runtime: in");
		Sequence<String> S = new LinkedList<String>();
		
		for (int n=1; n < 500; n++) {
			sequence_generator(S,n);
			S.resetOps();
			S.in("-1");     // Worst case scenario
			C.add_point(n,  S.numOps(), "blue");
			//System.out.println(S.size()+"  "+S.numOps());
		}
		
		C.draw();
	}
	
	public static void main(String[] args) {
		plot_get();
		plot_set();
		plot_single_push_back();
		plot_single_push_front();
		plot_multi_push_back();
		plot_multi_push_front();
		plot_in();
	}
}
