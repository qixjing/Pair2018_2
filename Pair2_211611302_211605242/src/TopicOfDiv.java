
public class TopicOfDiv extends Topic {
	// ���꼶�ĳ�����
	// AΪһλ��������λ��,B��Ϊ��λ��
	// A/B=C,CӦ��Ϊ��λ��

	public String createTopic() {
		int result = 0;
		int randomB = 0;
		int randomA = 0;
		do {
			// ��A��ȡֵ��Χ����Ϊ10��99
			randomA = 10 + random.nextInt(90);
			// ��B��ȡֵ��Χ����Ϊ1��9
			randomB = 1 + random.nextInt(9);
			result = randomA / randomB;
		} while (result >= 10);
		
		
		return (randomA + " / " + randomB);
 	}


}
