

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Stack2 {

	public static void main(String [] args){
		//TODO add documentation from other file, account for exceptions
		
		ArrayList<ArrayList<String>> b = new ArrayList<ArrayList<String>>();
		readAndInsertData(b);
		solveAll(b);
		
		
	}//end main
	
	public static boolean isInfix(ArrayList<String> x){
		if (x.get(0).equals("(")) return true;

		if (isNumber(x.get(0))) return false;
		else return false;
	}//end isInfix method
	
	public static void solveAll(ArrayList<ArrayList<String>> x){
		for (ArrayList<String> s: x){
			if (isInfix(s))	System.out.println(evalInfix(s));
			else System.out.println(evalPostFix(s));
		}//end for each statement

	}//end solveAll method
	/**
	 * Reads the data from a text file and inserts it into the ArrayList.
	 * @param x the ArrayList that the data is inserted into
	 */
	public static void readAndInsertData(ArrayList<ArrayList<String>> x){
		Scanner reader = null; 
		try{reader = new Scanner(new FileReader("expressions.txt"));
		}//end try statement
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}//end catch statement
		while (reader.hasNext()){
			x.add(tokenize(reader.nextLine()));
		}//end while loop
	}//end readAndInsertData method

	/**
	 * Checks the string to see if it represents an operator
	 * @param o the string representing the expression
	 * @return true if it is an operator, false if not
	 */
	public static boolean isOperator(String o) {
		return "-+*/%".indexOf(o) != -1;
	}//end isOperator method
	
	/**
	 * Checks the string to see if it represents a number
	 * @param str the string that could potentially contain a number
	 * @return true if a number, else if not
	 */
	public static boolean isNumber(String str) {
	    try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
	}//end isNumber method
	
	/**
	 * Evaluates the infix expression using stacks
	 * @param e the ArrayList with the parts of the expression
	 * @return the double of the evaluated expression
	 */
	public static Double evalInfix(ArrayList<String> e){
		Stack<Double> nums = new Stack<Double>();
		Stack<Character> operators  = new Stack<Character>();
		Double result = (double) 0, a, b;
		
		for (int i=0; i<e.size(); i++){
			String temp = e.get(i);
			if (isOperator(temp)){
				operators.push(temp.charAt(0));
				
			}//end if
			else if (isNumber(temp)){
				nums.add(Double.parseDouble(temp));
			}//end if
			else if(temp.equals(")")){
				Character q = operators.pop();
				if (nums.size()>=2){
					a = nums.pop();
					b= nums.pop();
					result = compute(q,b,a);
				}//end if
				nums.push(result);
			}//end if
		}//end for
	
		return nums.pop();
	}//end evalInfix method
	
	/**
	 * Evaluates the postfix expression using stacks
	 * @param e the ArrayList with the parts of the expression
	 * @return the double of the evaluated expression
	 */
	public static Double evalPostFix(ArrayList<String> e){
		Stack<Double> nums = new Stack<Double>();
		Double result = (double) 0, a, b;
		
		for (int i=0; i<e.size(); i++){
			String temp = e.get(i);
		
			if (isNumber(temp)){
				nums.add(Double.parseDouble(temp));
			}//end if
			if (isOperator(temp)){
				Character q = temp.charAt(0);
				if (nums.size()>=2){
					a = nums.pop();
					b= nums.pop();
					result = compute(q,b,a);
				}//end if
				nums.push(result);
			}//end if
			if (temp.equals(" ")){
				//do nothing
			}//end if
		}//end for loop 
		return nums.pop();
	}//end evalPostfix method

	/**
	 * Splits up the expression into a ArrayList containing strings representing numbers and operators
	 * @param e the string to be split up
	 * @return an ArrayList with the separated parts (tokens)
	 */
	public static ArrayList<String> tokenize(String e) {
		char c = 0;
		String token = "";
		ArrayList<String> tokenList = new ArrayList<String>();

		while (c < e.length()) {
			char ch = e.charAt(c++);
			if (Character.isWhitespace(ch)) {

				//if there is a token, add it to the list and empty the temporary token string
				if (token.length() > 0) {
					tokenList.add(token);
					token = "";
				}//end if
			}//end if

			else if (Character.isDigit(ch) || ch == '.') {
				token = token + ch;
			}//end if

			else if (isOperator(ch+"") || ch == '(' || ch == ')') {
				if (token.length() > 0) {
					tokenList.add(token);
					token = "";
				}//end if    		     
				tokenList.add(Character.toString(ch));
			}//end if
			else {
				System.out.println("Bad expression!");
			}//end else

		}//end while

		if (token.length() > 0)
			tokenList.add(token);

		return tokenList;
	}//end tokenize method

	/**
	 * Evaluates the expression using the two doubles and the Character provided
	 * @param c the character that represents the operator
	 * @param a one of the operands
	 * @param b the other operand
	 * @return the double result
	 */
	public static Double compute(Character c, Double a, Double b){
		double result =0;
		switch(c){
		case '/': result = a/b; break;
		case '*': result = a*b; break;
		case '+': result = a+b; break;
		case '-': result = a-b; break;
		case '%': result = a%b; break;
		default: System.out.println("This is an invalid expression!"); System.exit(0); break;
		}//end switch statement

		return result;
	}// end compute method

}//end class

