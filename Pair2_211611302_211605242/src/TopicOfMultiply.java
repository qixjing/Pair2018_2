
public class TopicOfMultiply extends Topic {
	//���꼶�ĳ˷�
	//(A*B)Ӧ���ھžų˷�����
	// �� A��B��Ӧ��Ϊ��λ��
	public String createTopic() {
		int randomA = 1 + random.nextInt(9);
		int randomB = 1 + random.nextInt(9);
		return randomA + " * " + randomB;
	}

}
