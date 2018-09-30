
public class TopicFactory {

	public static Topic createRan(String symbol) {
		Topic ran = null;
		switch (symbol) {
		case "+":
			ran = new TopicOfAdd();
			break;
		case "-":
			ran = new TopicOfSubtract();
			break;
		case "*":
			ran = new TopicOfMultiply();
			break;
		case "/":
			ran = new TopicOfDiv();
			break;
		}
		return ran;
	}

}
