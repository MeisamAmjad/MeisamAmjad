// This is only intended to test your class for very basic errors.  Basically, it 
// tests whether your Queue code will compile and run, but does not test whether it works.
public class Main {

	public static void main(String[] args) {
		String infix = "2 + 3 * 4";
		//String infix = "1+11/((2+3^2)+4/3/4)/5";
		System.out.println("Infix: " + infix);
		String postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		double val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "(2 + 3) * 4";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "10 - 5 - 2";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
		infix = "10 - (5 - 2)";
		System.out.println("Infix: " + infix);
		postfix = PostfixConverter.infix2postfix(infix);
		System.out.println("Postfix: " + postfix);
		val = PostfixConverter.evaluatePostfix(postfix);
		System.out.println("Value: " + val + "\n");
		
	}

}
