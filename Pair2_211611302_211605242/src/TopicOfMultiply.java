
public class TopicOfMultiply extends Topic {
	//二年级的乘法
	//(A*B)应该在九九乘法表中
	// 故 A和B都应该为个位数
	public String createTopic() {
		int randomA = 1 + random.nextInt(9);
		int randomB = 1 + random.nextInt(9);
		return randomA + " * " + randomB;
	}

}
