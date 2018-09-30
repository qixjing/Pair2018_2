


import java.util.Random;

public abstract class Topic {
	static Random random = new Random();
	
	abstract public String createTopic();
	
	static int createRandomNumber(int min,int max){
		// randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
		int ranNumber = random.nextInt(max - min + 1) + min; 
		return ranNumber;
		
	}
}
