


public class OperationDiv extends Operation {
	int mod = 0;

	@Override
	public int calculate(int a, int b) {
		mod = 0;
		int result = a / b;
		mod = a % b;
		return result;
	}
	
	public int returnMod() {
		return mod;
	}

}
