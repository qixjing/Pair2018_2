
public class TopicOfSubtract extends Topic {
	// ����һ�꼶�����������Ŀ A-B
	// AӦ�ô���B
	public String createTopic() {
		// Ϊ����B��ȡֵ����A��ȡֵ��Χ���ó� [5-9] �� [10 20 30 ....90]
		int randomA = 0;
		int randomB = 0;
		//�� trueʱ������ [5-9], falseʱ������ [10 20 30 ....90]
		if (random.nextBoolean()) {
			randomA = random.nextInt(5) + 5;
			randomB = random.nextInt(randomA+1);
		} else {
			randomA = 10 * (random.nextInt(9) + 1);
			randomB = 1+ random.nextInt(9);
		}
		return randomA + " - " + randomB;
	}

}
