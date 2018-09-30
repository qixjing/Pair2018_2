


public class OperationAdd extends Operation {
//	public void calculate() {
//		int result = bean.getNumberA() + bean.getNumberB();
//		bean.setResult(result);
//	}

	@Override
	public int calculate(int a, int b) {
		int result = a + b;
		return result;
	}

}
