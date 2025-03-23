class RunCalculator {
	public static void main(String[] args) {
		String[] input = new String[3];
		input[0] = "1";
		input[1] = "0";
		input[2] = "+";
		
		int x = 0;
		int y = 0;
		
		Calculator cal = new Calculator();
		
		try {
			
			x = Integer.parseInt(input[0]);
			y = Integer.parseInt(input[1]);
			
		} catch(NumberFormatException e) {
			
			System.out.println("Use number in arifmetics operations");
			
			return;
		}
		
		if (input[2].equals("+")) {
			
			System.out.println(cal.addition(x,y));
			
		} else if (input[2].equals("-")) {
			
			System.out.println(cal.subtraction(x,y));
			
		} else if (input[2].equals("*")) {
			
			System.out.println(cal.multiplication(x,y));
			
		} else if (input[2].equals("/")) {
			
			try {
				
				System.out.println(cal.division(x,y));
				
			} catch(ArithmeticException e) {
				
				System.out.println("You divide by zero");
				
				return;
			}
			
		}
	}
}
class Calculator {
	
	public int addition(int x, int y) {
		int c = x + y;
		return c;
	}
	public int subtraction(int x, int y) {
		int c = x - y;
		return c;
	}
	public int multiplication(int x, int y) {
		int c = x * y;
		return c;
	}
	public int division(int x, int y) throws ArithmeticException {
		int c = x / y;
		return c;
	}
} 