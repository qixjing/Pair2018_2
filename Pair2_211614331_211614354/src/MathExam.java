import java.util.ArrayList;
import java.util.Random;


public class MathExam extends Operation{

	static boolean isRight;//�������Ƿ�Ϸ�
	static ArrayList<String> questionAndAnswer;// �洢��Ŀ�ʹ𰸵ļ���
	static int testNumber;// �������Ŀ��
	static String errorMessage="";
	static boolean isError=true;
	static String	whereIsN="";
	static String whereIsGrade="";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		int n=9999;// �����������������Ŀ����
		int grade=3; //  
		String problem;// ���ճ�������
		questionAndAnswer=new ArrayList<String>();// ������Ŀ�ʹ𰸣�Ȼ���뵽���ģ��	
		RandomQuestions rQ=new RandomQuestions(); // ����������
		ArithemticS aR=new ArithemticS(); // �����������			
		Random rand=new Random();// ���һ���꼶����Ŀ���Ǽӻ��Ǽ�
		
		
			
			inputTest(args);//�жϽ���
			
			if(isError) {
				n=Integer.valueOf(whereIsN);// 
				grade=Integer.valueOf(whereIsGrade);
				if(grade==1) {
					grade=0;
				}else if(grade==2) {
					grade=2;
				}
				
			if(grade==0||grade==2) {
				for(int i=1;i<=n;i++) {
					int s=rand.nextInt(2);
					s=s+grade;
					switch(s) {
						case 0:                          //Ϊ�Ӻ�ʱ
						{
							//testNumber++;
							AddOperation opp=new AddOperation(questionAndAnswer,i);
							break;
						}
						case 1:                      //Ϊ����ʱ
						{
							//testNumber++;
							Subtraction opp=new Subtraction(questionAndAnswer,i);
							break;
						}
						case 2:                    //�˷�
						{
							//testNumber++;
							Multiplication opp=new Multiplication(questionAndAnswer,i);
							break;
						}
						case 3:                  //����
						{
							testNumber++;
							Division opp=new Division(questionAndAnswer,i);
							break;
						}
					}
					
				}
				
			}else if(grade==3){
				for(int i=0;i<n;i++) {
				//���꼶���ô���
						do {
							
						problem=rQ.questionsShop(); //�������������󣬷�����Ŀ
						isRight=aR.startProblem(problem); // ������Ŀ�������������
						if(answerNumber>=9999) {
							isRight=false;						//�����Ʋ�����9999(���꼶Ҫ��)
						}
						}while(!isRight);// �жϳ���������Ƿ�����꼶�淶
						questionAndAnswer.add("("+(i+1)+") "+problem);// ���꼶��Ŀ���뼯��
						questionAndAnswer.add(" = "+answerNumber);// ���꼶�𰸼��뼯��
						//���꼶���ô���
				}
			}
				//System.out.println(questionAndAnswer);
				OutPutSet outPut=new OutPutSet(n); //�������
				
			}else {
				System.out.println(errorMessage);
			}	
						
		}// main end
	
	public static boolean inputTest(String args[]) {
		
		isError=true;// Ҫ�ָ���ʼ״̬��testPartInput�з��ֵ����⣩
		errorMessage=""; // Ҫ�ָ���ʼ״̬
		
		if (args.length != 4) {
			isError = false;
			errorMessage = "��������,����Ĳ������������Ϊ4";
		}
		else if(args[0].matches("\\d+") || args[2].matches("\\d+")||!args[1].matches("\\d+") || !args[3].matches("\\d+")) {
			isError = false;
			errorMessage = "������������Ĳ���λ�ò���";
		}
		else if (args[0].equals("-n") && args[2].equals("-grade")) {
			//�����һ������������,�ڶ����������꼶
			whereIsN=args[1];// ��һ����Ŀ
			whereIsGrade=args[3];
			if (args[1].length() >= 5 || args[1].length()==0) {
				isError = false;
				errorMessage = "�����������������Ĳ��������ǳ�����λ���Ҳ���Ϊ0λ��";
				}
			else if ((!args[1].matches("\\d+")) || args[1].matches("[0]\\d*")) {
					isError = false;
					errorMessage = "��������,����������Ĳ������붼Ϊ����,������Ĳ����в�����0��ͷ,��������ΪС��";
				}
			 else if (!args[3].matches("[123]")) {
				isError = false;
				errorMessage = "��������,�����꼶�Ĳ�������Ϊ123�����е�����һ��";
			}
		} 
		else if (args[0].equals("-grade") && args[2].equals("-n")) {
			//����ĵ�һ���������꼶,�ڶ�������������
			whereIsN=args[3];// 
			whereIsGrade=args[1];
			if (!args[1].matches("[123]")) {
				isError = false;
				errorMessage = "��������,�����꼶�Ĳ�������Ϊ123�����е�����һ��";
			} 
			else if (args[3].length() >= 5 || args[3].length()==0) {
				isError = false;
				errorMessage = "�����������������Ĳ��������ǳ�����λ���Ҳ���Ϊ0λ��";}
			else if (!(args[3].matches("\\d+")) || !args[3].matches("[^0]\\d*")) {//bug�޸�:�޷�ʶ���꼶Ϊ001
					isError = false;
					errorMessage = "��������,���������Ĳ������붼Ϊ����,������Ĳ����в�������0��ͷ,��������ΪС��";
				}
			
		}
		
		//System.out.println(isError);
		return isError;
	}

}// end
		
	



