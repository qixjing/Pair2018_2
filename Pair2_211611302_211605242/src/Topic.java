


import java.util.Random;

public abstract class Topic {
	static Random random = new Random();
	
	abstract public String createTopic();
	
	static int createRandomNumber(int min,int max){
		// randNumber ������ֵΪһ�� MIN �� MAX ��Χ�ڵ������
		int ranNumber = random.nextInt(max - min + 1) + min; 
		return ranNumber;
		
	}
}
