import static org.junit.Assert.*;

import org.junit.Test;

public class ConverterTester {

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
	
	//********************* 
	// infix2postfix tests
	@Test
	public void test01() {
		String infix = "1 + 2";
		String correct = "1 2 +";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test02() {
		String infix = "1 - 2";
		String correct = "1 2 -";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}

	@Test
	public void test03() {
		String infix = "1 * 2";
		String correct = "1 2 *";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test04() {
		String infix = "1 / 2";
		String correct = "1 2 /";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test05() {
		String infix = "(1 + 2)";
		String correct = "1 2 +";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test06() {
		String infix = "1 + 2 + 3";
		String correct = "1 2 + 3 +";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test07() {
		String infix = "1 - 2 - 3";
		String correct = "1 2 - 3 -";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test08() {
		String infix = "1 * 2 * 3";
		String correct = "1 2 * 3 *";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test09() {
		String infix = "1 / 2 / 3";
		String correct = "1 2 / 3 /";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test10() {
		String infix = "1 ^ 2 ^ 3";
		String correct = "1 2 3 ^ ^";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test11() {
		String infix = "1 + 2 * 3";
		String correct = "1 2 3 * +";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test12() {
		String infix = "1 * 2 + 3";
		String correct = "1 2 * 3 +";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test13() {
		String infix = "1 * (2 - 3)";
		String correct = "1 2 3 - *";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}

	@Test
	public void test14() {
		String infix = "(1 + 2) / (3 - 4)";
		String correct = "1 2 + 3 4 - /";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test15() {
		String infix = "( (1+2*3) / (4*(5+6+7)) )";
		String correct = "1 2 3 * + 4 5 6 + 7 + * /";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	@Test
	public void test16() {
		String infix = "(1+2) ^ (3*4) ^ (5-6) * 7";
		String correct = "1 2 + 3 4 * 5 6 - ^ ^ 7 *";
		String postfix = PostfixConverter.infix2postfix(infix);
		assertTrue(compareStrings(postfix, correct));
	}
	
	//***************************
	// Testing error handling: Does infix2postfix throw exceptions correctly?
	@Test(expected = IllegalArgumentException.class)
	public void test17() {
		String infix = "(1+2";
		String postfix = PostfixConverter.infix2postfix(infix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test18() {
		String infix = "1+2)";
		String postfix = PostfixConverter.infix2postfix(infix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test19() {
		String infix = "(1+2) / (3*(4/5)";
		String postfix = PostfixConverter.infix2postfix(infix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test20() {
		String infix = "(1+2) / 3*(4/5))";
		String postfix = PostfixConverter.infix2postfix(infix);
	}
	
}
