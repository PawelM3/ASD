package s14926PMStos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class MyStack {
	int maxSize;
	String[] stackArray;
	int top;

	public MyStack() {
		stackArray = new String[1000];
		top = -1;
	}

	public void push(String value) {
		stackArray[++top] = value;
	}

	public String pop() {
		return stackArray[top--];
	}

	public boolean empty() {
		return (top == -1);
	}

	public String top() {
		return stackArray[top];
	}

	@Override
	public String toString() {
		return "MyStack [=" + Arrays.toString(stackArray) + "]";
	}

}

public class Main {

	static MyStack stack = new MyStack();
	private final static Set<String> UNARYOPERATORS = new HashSet<String>(
			Arrays.asList(new String[] { "!", "sin", "cos", "tg", "ctg", "log" })); // possible unary operations
	private final static Set<String> BINARYOPERATORS = new HashSet<String>(
			Arrays.asList(new String[] { "+", "-", "/", "*", "^" })); // possible binary operations

	public static void demo() {
		System.out.println("==============================");
		System.out.println("===Some sample operations===");
		calculate(new StringTokenizer("2 3 +"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("5 3 -"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("2 3 *"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("2 3 /"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("2 3 ^"));
		System.out.println("Result: " + stack.pop());
		System.out.println("===Unary operaitons===");
		calculate(new StringTokenizer("3.14 sin"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("0 cos"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("3.14 tg"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("0.89 ctg"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("5 !"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("4 log"));
		System.out.println("Result: " + stack.pop());
		System.out.println("===Some more complex operations===");
		calculate(new StringTokenizer("15 7 1 1 + - / 3 * 2 1 1 + + -"));
		System.out.println("Result: " + stack.pop());
		calculate(new StringTokenizer("12 2 3 4 * 10 5 / + * +"));
		System.out.println("Result: " + stack.pop());
		System.out.println("==============================");
	}

	public static void inputProcessing() {
		System.out.println("Enter your own operation");
		System.out.println("You can use binary operators: " + BINARYOPERATORS.toString());
		System.out.println("or unary operators: " + UNARYOPERATORS.toString());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		try {
			st = new StringTokenizer(br.readLine());
		} catch (IOException e) {
			System.out.println("You need to enter valid RPN expression");
			e.printStackTrace();
		} // read the input
		calculate(st);
		System.out.println("Result: " + stack.pop());
		System.out.println("====================");
	}

	public static void calculate(StringTokenizer st) {
		StringBuffer operation = new StringBuffer();
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			try {
				Double.parseDouble(s);
				stack.push(s);
			} catch (NumberFormatException e) {
				if (BINARYOPERATORS.contains(s)) {
					String result = Double.toString(operatorTwoArguments(s));
					stack.push(result);
				} else if (UNARYOPERATORS.contains(s)) {
					String result = Double.toString(operatorOneArgument(s));
					stack.push(result);
				} else {
					System.out.println("Unknown operation");
					System.exit(1);
				}
			}
			operation.append(" ").append(s);
		}
		System.out.println("Operation :" + operation);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("The program implements the following operators:");
		System.out.println("Binary operators: +, -, *, /, ^");
		System.out.println("Unary operators: !, sin, cos, tg, ctg, natural logarithm log");
		demo();
		inputProcessing();
		System.out.println("The End");
	}

	private static Double operatorOneArgument(String operator) {
		Double k = Double.parseDouble(stack.pop());// { "!", "sin", "cos", "tg", "ctg", "log" }));
		Double answear;
		switch (operator) {
		case "!":
			answear = (double) factorial(k);
			break;
		case "sin":
			answear = Math.sin(k);
			break;
		case "cos":
			answear = Math.cos(k);
			break;
		case "tg":
			answear = Math.sin(k) / Math.cos(k);
			break;
		case "ctg":
			answear = Math.cos(k) / Math.sin(k);
			break;
		case "log":
			answear = Math.log(k);
			break;
		default:
			answear = 0.0;
			break;
		}
		return answear;
	}

	public static Double operatorTwoArguments(String operator) {
		Double k = Double.parseDouble(stack.pop());
		Double l = Double.parseDouble(stack.pop());
		Double answear;
		switch (operator) {
		case "+":
			answear = l + k;
			break;

		case "-":
			answear = l - k;
			break;

		case "*":
			answear = l * k;
			break;

		case "/":
			answear = l / k;
			break;

		case "^":
			answear = Math.pow(l, k);
			break;

		default:
			answear = 0.0;
			break;
		}
		return answear;
	}

	public static Double factorial(Double k) {
		Double result = 1.0;
		for (Double factor = 2.0; factor <= k; factor++) {
			result *= factor;
		}
		return result;
	}
}
