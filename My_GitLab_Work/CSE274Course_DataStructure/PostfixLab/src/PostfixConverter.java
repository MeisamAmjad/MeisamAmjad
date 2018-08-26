/**
 * Project: Infix -> Postfix conversion
 * @author Meisam Amjad (amjadm@miamioh.edu)
 *
 */

public class PostfixConverter {

	/**
	 * Convert an infix expression to a postfix expression.  Assumes the expression uses
	 * only integers, parenthesies, and the operator set {+, -, *, /, ^}.
  	 * @param formula   The infix expession.
	 * @return          The postfix expression.
	 * @exception       Throw Illegal Argument exception if the parenthesis
	 *                  don't match up correctly.
	 */
	public static String infix2postfix(String formula) {
		String r="";
		CharStack S=new CharStack(20);
		Tokenizer token=new Tokenizer(formula);
		String c=token.next();
		int parMatch=0;
		
		while (!c.equals("")){
			//If it's an expressions which starts with '('
			// check with parMatch variable at the end to see if the parenthesis are match.
			if(c.equals("(")){
				parMatch++;
				S.push('(');
			}
			//Pop from S until '('
			if(c.equals(")")){
				parMatch--;
				while (S.peek()!='('){
					r=r+S.pop()+" ";
					if (S.isEmpty()){ throw new IllegalArgumentException("Unmatch Parenthesis");}
				}
				S.pop();
			}
			
			//If it's the number, adds it to r
			if (Tokenizer.isNumber(c)){
				r=r+c+" ";
			}
			// If it's an operator
			if (Tokenizer.isOperator(c)){
				if (S.isEmpty() || S.peek()=='('){
					S.push(c.charAt(0));
				}else{
					while (Tokenizer.operatorPrioity(S.peek())>=Tokenizer.operatorPrioity(c.charAt(0)) &&
																	!(c.equals("^") && S.peek()=='^' )   ){
							r=r+S.pop()+" ";
							if (S.isEmpty() || S.peek()=='('){break;}
					}
					S.push(c.charAt(0));;
				}
			}
			c=token.next();
		}
		if (parMatch!=0) { throw new IllegalArgumentException("Unmatch Parenthesis");}
		while (!S.isEmpty()) {
			r=r+S.pop()+" ";
		}
		return r;
	}
	
	/**
	 * Given postfix expression, calculate the value.
	 * @param s      The postfix expression.
	 * @return       double: the calculated value.
	 */
	public static double evaluatePostfix(String s) {
		DoubleStack S=new DoubleStack(20);
		Tokenizer t=new Tokenizer(s);
		String c=t.next();
		Double result=0.0;
		double d2=0.0;
		double d1=0.0;
		
		//Check until the input string ends
		while(!c.equals("")){
			//Throws an error if it has parenthesis.
			if (c.equals("(") || c.equals(")")) {throw new IllegalArgumentException("Unmatch expression");}
			//If it's a number it pushes it to the S.
			if (Tokenizer.isNumber(c)){
				S.push(Double.parseDouble(c));
			}
			if(Tokenizer.isOperator(c)){
				
				//If the stack is empty but we have an operator, it throws an Error
				if (S.isEmpty()) {throw new IllegalArgumentException("Unmatch expression");}
				else{d2=S.pop();}
				if (S.isEmpty()) {throw new IllegalArgumentException("Unmatch expression");}
				else{d1=S.pop();}
				
				//executes two numbers depends on the operator
				switch (c) {
					case "+":
						result=d1+d2;
						break;
					case "-":
						result=d1-d2;
						break;
					case "*":
						result=d1*d2;
						break;
					case "/":
						result=d1/d2;
						break;
					case "^":
						result=Math.pow(d1, d2);
						break;
				}
				//Add the result of the execution to the Stack S
				S.push(result);
			}
			//goes to a next token
			c=t.next();
			
		}
		//If it has more than one value in the Stack, it means it had an extra number without any operator.
		//So, it throws an Error
		if (S.size()>1){throw new IllegalArgumentException("Unmatch expression");}
		//keep the final result
		result=S.pop();
		
		return result;
	}
	
}

