
//运算父类

public class Operation {
	 int firstNumber; //第一个随机数
	 int secondNumber; //第二个随机数
	 int remainder;//余数
	 static int answerNumber; //答案
	
	public int getAnswerNumber() {
		return answerNumber;
	}
	
	@SuppressWarnings("static-access")
	public  void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
	
}
