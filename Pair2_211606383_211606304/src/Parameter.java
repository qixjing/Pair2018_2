
public class Parameter {
	
	private int Grade;
	private int Number;
	
	//���캯��
	public Parameter(String[] args) {
		DealData(args);
	}
	//��������
	public boolean DealData(String[] args) {
		//�����������Ƿ���ȷ
		if(args.length != 4) {
			System.out.println("Error:Parameter Number Error");
			return false;
		}
		//��������ʽ�Ƿ���ȷ
		if(args[0].equals("-n") == false && args[0].equals("-Grade") == false) {
			System.out.println("Error:Parameter Format Error");
			return false;
		}
		//��������ʽ�Ƿ���ȷ
		if(args[2].equals("-n") == false && args[2].equals("-Grade") == false) {
			System.out.println("Error:Parameter Format Error");
			return false;
		}
		// �ж���Ŀ�����Ƿ�Ϊ������
		if (isNum(args[1]) == false || isNum(args[3]) == false) {
			System.out.println("Error:Parameter Type Error");
			return false;
		}
		//��ȡ����
		if(args[0].equals("-n")) {
			Grade = Integer.valueOf(args[3]);
			Number = Integer.valueOf(args[1]);
		}else
		{
			Grade = Integer.valueOf(args[1]);
			Number = Integer.valueOf(args[3]);
		}
		//������ݸ�ʽ
		if(Grade > 3 || Grade < 1 || Number < 1) {
			System.out.println("Error:Parameter Range Error");
			return false;
		}
		return true;
	}
	
	//��ȡ�꼶
	public int GetGrade() {
		return Grade;
	}
	
	//��ȡ����
	public int GetNumber() {
		return Number;
	}
	
	//�ж��ַ����Ƿ�Ϊ������
	private boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

}
