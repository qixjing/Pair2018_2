
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam{
	private static int grade = 0; //年级
	private static int topicCount = 0; // 出题数
	static boolean flag = true;  //为测试方便，不设置成private

	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();  //测试时间
		
		checkInput(args); //检查输入是否符合规范
		String txt = createThread();//创建线程
		Txt.createMathExamTxt(txt);//生成文本
		
		long timeEnd = System.currentTimeMillis();//测试时间
//		System.out.println(txt);
//		System.out.println(timeEnd - timeStart + " ms"); 
	}

	private static String createThread() {
		int number = 1; //题目的序号,从1开始
		int threadCount=2; //线程数
		if(topicCount<10) {
			threadCount=1; //如果题目小于10，只启用一个线程
		}
		int count = topicCount/threadCount ; //每个线程负责的题数
		StringBuffer topicTxt=new StringBuffer("");
		StringBuffer answerTxt=new StringBuffer("");
		Thread[] threads = new Thread[threadCount];
		ExamThread [] exam = new ExamThread[threadCount];
		
		for (int i = 0; i < threadCount; i++) {
			if(i==threadCount-1) {
				//最后一个线程
				count = count + (topicCount % threadCount);
			}
			exam[i] = new ExamThread(grade,count,number);
			threads[i] = new Thread(exam[i]);
			threads[i].start();
			number = count + number;
		}
		for (int i = 0; i < threadCount; i++) {
			//每个线程执行完毕后，就获取生成的题目
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			topicTxt.append(exam[i].getTopixTxt());
			answerTxt.append(exam[i].getAnswerTxt());
		}
		String txt = String.valueOf(topicTxt) + "\n" + String.valueOf(answerTxt);
		txt=txt.replaceAll("\\*","×");
		txt=txt.replaceAll("\\/","÷");
		return txt;
	}

	// 检查输入的参数是否符合规范
	private static void checkInput(String[] args) {
		if (args.length != 4) {
			exception();
		}
		String input = args[0] + " " + args[1] + " " + args[2] + " " + args[3];
		// -grade和-n的顺序可以颠倒
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
			// 找到数字，设置出题数
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
		System.out.println("输入有误,程序结束。");
		flag=false;
		System.exit(0);
	}

	// 产生txt文本
	
}
