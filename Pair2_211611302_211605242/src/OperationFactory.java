


public class OperationFactory {
	public static Operation createOperate(String symbol) {
		Operation oper = null;
		switch (symbol) {
		case "+":
			oper = new OperationAdd();
			break;
		case "-":
			oper = new OperationSubtract();
			break;
		case "*":
			oper = new OperationMultiply();
			break;
		case "/":
			oper = new OperationDiv();
			break;
		}

		return oper;
	}
}
