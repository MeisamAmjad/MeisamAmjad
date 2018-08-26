import static org.junit.Assert.*;

import org.junit.Test;

public class EvalTester {

	/**
	 * Check that the two strings represent are the same (except possibly for spacing)
	 * @param s1   A string representing a formula
	 * @param s2   A string representing a formula
	 * @return     True/False
	 */
	public boolean compareStrings(String s1, String s2) {
		Tokenizer T1 = new Tokenizer(s1);
		Tokenizer T2 = new Tokenizer(s2);
		
		String a = T1.next();
		String b = T2.next();
		while (!a.equals("") && !b.equals("")) {
			if (!a.equals(b))
				return false;
			a = T1.next();
			b = T2.next();
		}
		
		return a.equals("") && b.equals("");
	}
	
	/**
	 * Returns True if there are no parenthesis.
	 * @paren s   The string being checked.
	 * @return    True/false
	 */
	public boolean parenFree(String s) {
		for (int i=0; i < s.length(); i++)
			if (s.charAt(i) == '(' || s.charAt(i) == ')')
				return false;
		return true;
	}
	

	//***************************
	// Testing: evaluatePostfix
	@Test
	public void test21() {
		String postfix = "3 4 +";
		double correct = 7.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001); // The 0.0001 is "margin of error" to account for rounding
	}
	
	@Test
	public void test22() {
		String postfix = "3 4 -";
		double correct = -1.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test23() {
		String postfix = "3 4 *";
		double correct = 12.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test24() {
		String postfix = "3 4 /";
		double correct = 0.75;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test25() {
		String postfix = "2 10 ^";
		double correct = 1024.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test26() {
		String postfix = "2 3 + 4 *";
		double correct = 20.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test27() {
		String postfix = "2 3 4 + *";
		double correct = 14.0;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test28() {
		String postfix = "6 5 4 3 2 + + + -";
		double correct = -8;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	@Test
	public void test29() {
		String postfix = "6 5 4 3 2 + + + -";
		double correct = -8;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}

	@Test
	public void test30() {
		String postfix = "6 5 4 * 1 1 + / *";
		double correct = 60;
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, correct, 0.00001);
	}
	
	//************************
	// Check exceptions
	@Test(expected = IllegalArgumentException.class)
	public void test31() {
		String postfix = "3 4 5 +";
		double val = PostfixConverter.evaluatePostfix(postfix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test32() {
		String postfix = "3 4 5 + + +";
		double val = PostfixConverter.evaluatePostfix(postfix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test33() {
		String postfix = "3 4 5) + +";
		double val = PostfixConverter.evaluatePostfix(postfix);
	}
	
	
	//************************
	// Check the evaluation of a combined expression
	@Test
	public void test34() {
		String infix = " ( 5*(6+4) / ((3+2)*5) ) ^ (20 - (2 + 4 * 2))";
		String postfix = PostfixConverter.infix2postfix(infix);
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, 1024, 0.00001);
	}
	
	//************************
	// Check the evaluation of a combined expression
	@Test
	public void test35() {
		String infix = " ((4^2 + 3^2) / (10 - 5))^3";
		String postfix = PostfixConverter.infix2postfix(infix);
		double val = PostfixConverter.evaluatePostfix(postfix);
		assertEquals(val, 125, 0.00001);
	}
}
