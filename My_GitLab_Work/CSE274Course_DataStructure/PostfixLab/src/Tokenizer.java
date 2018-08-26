
public class Tokenizer {

	public Tokenizer(String s) {
		this.s = s;
		index = 0;
	}
	
	public String next() {
		while (index < s.length() && s.charAt(index) == ' ')
			index++;
		
		if (index == s.length())
			return "";
		
		if (isDigit(s.charAt(index))) {
			int i= index + 1;
			while (i < s.length() && isDigit(s.charAt(i)))
				i++;
			String s2 = s.substring(index, i);
			index = i;
			return s2;
		}
		
		char c = s.charAt(index);
		if (!(c=='(' || c==')' || isOperator(c))) {
			throw new IllegalStateException("Bad character: " + c);
		}
		index++;
		return "" + c;
	}

	public static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
	
	public static boolean isDigit(String s) {
		return s.length() == 1 && isDigit(s.charAt(0));
	}
	
	public static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}
	
	public static boolean isOperator(String s) {
		return s.length() == 1 && isOperator(s.charAt(0));
	}
	
	public static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (!isDigit(s.charAt(i)))
				return false;
		return true;
	}
	
	public static int operatorPrioity(char c) {		
		if (c == '+')
			return 0;
		if (c == '-')
			return 0;
		if (c == '*')
			return 1;
		if (c == '/')
			return 1;
		if (c == '^')
			return 2;
		
		throw new IllegalStateException("Not an operator: " + c);
	}
	

	
	// Private variable instances
	private String s;
	private int index;
}
