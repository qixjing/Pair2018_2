
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam{
	private static int grade = 0; //�꼶
	private static int topicCount = 0; // ������
	static boolean flag = true;  //Ϊ���Է��㣬�����ó�private

	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();  //����ʱ��
		
		checkInput(args); //��������Ƿ���Ϲ淶
		String txt = createThread();//�����߳�
		Txt.createMathExamTxt(txt);//�����ı�
		
		long timeEnd = System.currentTimeMillis();//����ʱ��
//		System.out.println(txt);
//		System.out.println(timeEnd - timeStart + " ms"); 
	}

	private static String createThread() {
		int number = 1; //��Ŀ�����,��1��ʼ
		int threadCount=2; //�߳���
		if(topicCount<10) {
			threadCount=1; //�����ĿС��10��ֻ����һ���߳�
		}
		int count = topicCount/threadCount ; //ÿ���̸߳��������
		StringBuffer topicTxt=new StringBuffer("");
		StringBuffer answerTxt=new StringBuffer("");
		Thread[] threads = new Thread[threadCount];
		ExamThread [] exam = new ExamThread[threadCount];
		
		for (int i = 0; i < threadCount; i++) {
			if(i==threadCount-1) {
				//���һ���߳�
				count = count + (topicCount % threadCount);
			}
			exam[i] = new ExamThread(grade,count,number);
			threads[i] = new Thread(exam[i]);
			threads[i].start();
			number = count + number;
		}
		for (int i = 0; i < threadCount; i++) {
			//ÿ���߳�ִ����Ϻ󣬾ͻ�ȡ���ɵ���Ŀ
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			topicTxt.append(exam[i].getTopixTxt());
			answerTxt.append(exam[i].getAnswerTxt());
		}
		String txt = String.valueOf(topicTxt) + "\n" + String.valueOf(answerTxt);
		txt=txt.replaceAll("\\*","��");
		txt=txt.replaceAll("\\/","��");
		return txt;
	}

	// �������Ĳ����Ƿ���Ϲ淶
	private static void checkInput(String[] args) {
		if (args.length != 4) {
			exception();
		}
		String input = args[0] + " " + args[1] + " " + args[2] + " " + args[3];
		// -grade��-n��˳����Եߵ�
		if(input.matches("(\\-grade [123] \\-n [1-9]\\d*)|(\\-n [1-9]\\d* \\-grade [123])"))
			;
		else {
			exception();
		}
		
		Matcher m = Pattern.compile("\\-grade [123]").matcher(input);

		if (m.find()) {
			String n = m.group();
			// System.out.println(n); //test
			m = Pattern.compile("[123]").matcher(n);
			if (m.find()) {
				grade = Integer.valueOf(m.group());
			}

		} else {
			exception();
		}
		m = Pattern.compile("\\-n [1-9]\\d*").matcher(input);
		if (m.find()) {
			// �ҵ����֣����ó�����
			String n = m.group();
			// System.out.println(n); //test
			m = Pattern.compile("[1-9]\\d*").matcher(n);
			String digit = null;
			if (m.find()) {
				digit = m.group();
				if (digit.length() >= 5) {
					exception();
				}
			}
			topicCount = Integer.valueOf(digit);
		} else {
			exception();
		}

	}

	private static void exception() {
		System.out.println("��������,���������");
		flag=false;
		System.exit(0);
	}

	// ����txt�ı�
	
}
