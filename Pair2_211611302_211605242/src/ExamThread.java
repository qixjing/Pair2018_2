import java.util.Random;

public class ExamThread implements Runnable{
	private int grade = 0;
	private int count = 0;
	private int number = 0;
	private StringBuffer topicTxt=new StringBuffer("");
	private StringBuffer answerTxt=new StringBuffer("");
	

	public StringBuffer getTopixTxt() {
		return topicTxt;
	}
	
	public StringBuffer getAnswerTxt() {
		return answerTxt;
	}

	public ExamThread(int grade,int count,int number) {
		this.grade=grade;
		this.count=count;
		this.number=number;
	}

	public ExamThread() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		StringBuffer topic1 = new StringBuffer("");
		StringBuffer topic2 = new StringBuffer("");
		StringBuffer topic3 = new StringBuffer("");
		Random random = new Random();
		if (grade == 1 || grade == 2) {
			for (int i = number; i < count + number; i++) {
				String s=null;
				if (grade == 1) {
					String[] symbol = { "+", "-" };
					s = symbol[random.nextInt(2)];
				} else {
					String[] symbol = { "*", "/" };
					s = symbol[random.nextInt(2)];
				}
				Topic ran = TopicFactory.createRan(s);
				String topic = ran.createTopic();
				PolishNotation po = new PolishNotation(new StringBuffer(topic));
				String result = po.calculate();
				topic2.append("(" + (i) + ") " + topic + "\n");
				topic3.append("(" + (i) + ") " + topic + " = " + result);
//				if (mod != 0) {
//					topic3.append("..." + mod);
//					mod = 0;
//				}
				topic3.append("\n");
				//( 360 - 71 ) / 17 - 17 = 0        ´ý½â¾ö
			}
		} else if (grade == 3) {
			for (int i = number; i < count + number; i++) {
				TopicGradeThree topic = new TopicGradeThree();
				topic1 = topic.createTopic();
				PolishNotation po = new PolishNotation(topic1);
				String result = (po.calculate());
				topic2.append("(" + (i) + ") " + topic1 + "\n");
				topic3.append("(" + (i) + ") " + topic1 + " = " + result + "\n");
			}
		}
		topicTxt.append(topic2);
		answerTxt.append(topic3);
		
		
		
	}
	
	
}
