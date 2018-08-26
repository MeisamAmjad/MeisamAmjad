package runtime_test;



import data_structures.MinHeap;
import support_code.CurvePlotter;

public class HeapRuntimePlotter {
	

	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_push() {
		int size_limit = 2000;
		int step_size = 20;		
		CurvePlotter C = new CurvePlotter(0, 2000, 0, 80, "Runtime: push");
		
				
		MinHeap<String,Integer> H = new MinHeap<>();
		for (int size=20; size < size_limit; size += step_size) {
	
			H.clear();
			for (int i=size; i > 0; i--)
				H.push("tmp", i);
			H.resetOps();
			H.push("tmp", 0);
			C.add_point(size, H.numOps(), "blue");	
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_pop() {
		int size_limit = 2000;
		int step_size = 20;		
		CurvePlotter C = new CurvePlotter(0, 2000, 0, 80, "Runtime: pop");
				
		MinHeap<String,Integer> H = new MinHeap<>();
		for (int size=20; size < size_limit; size += step_size) {
	
			H.clear();
			for (int i=0; i < size; i++)
				H.push("tmp", i);
			H.resetOps();
			H.pop();
			C.add_point(size, H.numOps(), "blue");	
		}
		
		C.draw();
	}
	
	/**
	 * Plot the runtime for calling get on a list of size n.
	 */
	static void plot_peek() {
		int size_limit = 2000;
		int step_size = 20;		
		CurvePlotter C = new CurvePlotter(0, 2000, 0, 5, "Runtime: peek");
				
		MinHeap<String,Integer> H = new MinHeap<>();
		for (int size=20; size < size_limit; size += step_size) {
	
			H.clear();
			for (int i=0; i < size; i++)
				H.push("tmp", i);
			H.resetOps();
			H.peek();
			C.add_point(size, H.numOps(), "blue");	
		}
		
		C.draw();
	}
	

	
	public static void main(String[] args) {
		plot_push();
		plot_pop();
		plot_peek();
	}
}
