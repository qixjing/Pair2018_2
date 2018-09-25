import java.util.ArrayList;
import java.util.Random;


public class MathExam extends Operation{

	static boolean isRight;//运算中是否合法
	static ArrayList<String> questionAndAnswer;// 存储题目和答案的集合
	static int testNumber;// 输入的题目数
	static String errorMessage="";
	static boolean isError=true;
	static String	whereIsN="";
	static String whereIsGrade="";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		int n=9999;// 接收命令行输入的题目参数
		int grade=3; //  
		String problem;// 接收出题内容
		questionAndAnswer=new ArrayList<String>();// 接收题目和答案，然后导入到输出模块	
		RandomQuestions rQ=new RandomQuestions(); // 随机出题对象
		ArithemticS aR=new ArithemticS(); // 四则运算对象			
		Random rand=new Random();// 随机一二年级的题目，是加还是减
		
		
		
			if (args.length != 4) {
				isError = false;
				errorMessage = "参数错误,输入的参数个数必须得为4";
			}
			else if (args[0].equals("-n") && args[2].equals("-grade")) {
				//输入的一个参数是题数,第二个参数是年级
				whereIsN=args[1];// 第一是题目
				whereIsGrade=args[3];
				if (args[1].length() >= 5 || args[1].length()==0) {
					isError = false;
					errorMessage = "参数错误，输入题数的参数不能是超过四位数且不能为0位数";
					}
				else if ((!args[1].matches("\\d+")) || args[1].matches("[0]\\d*")) {
						isError = false;
						errorMessage = "参数错误,输入的题数的参数必须都为数字,且输入的参数中不能以0开头,参数不能为小数";
					}
				 else if (!args[3].matches("[123]")) {
					isError = false;
					errorMessage = "参数错误,输入年级的参数必须为123数字中的其中一个";
				}
			} 
			else if (args[0].equals("-grade") && args[2].equals("-n")) {
				//输入的第一个参数是年级,第二个参数是题数
				whereIsN=args[3];// 
				whereIsGrade=args[1];
				if (!args[1].matches("[123]")) {
					isError = false;
					errorMessage = "参数错误,输入年级的参数必须为123数字中的其中一个";
				} 
				else if (args[3].length() >= 5 || args[3].length()==0) {
					isError = false;
					errorMessage = "参数错误，输入题数的参数不能是超过四位数且不能为0位数";}
				else if (!(args[3].matches("\\d+")) || !args[3].matches("[^0]\\d*")) {//bug修复:无法识别年级为001
						isError = false;
						errorMessage = "参数错误,输入题数的参数必须都为数字,且输入的参数中不能有以0开头,参数不能为小数";
					}
				
			}
			else if(args[0].matches("\\d+") || args[2].matches("\\d+")) {
				isError = false;
				errorMessage = "参数错误，输入的参数位置不对";
			}//判断结束
			
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
						case 0:                          //为加号时
						{
							//testNumber++;
							AddOperation opp=new AddOperation(questionAndAnswer,i);
							break;
						}
						case 1:                      //为减号时
						{
							//testNumber++;
							Subtraction opp=new Subtraction(questionAndAnswer,i);
							break;
						}
						case 2:                    //乘法
						{
							//testNumber++;
							Multiplication opp=new Multiplication(questionAndAnswer,i);
							break;
						}
						case 3:                  //除法
						{
							testNumber++;
							Division opp=new Division(questionAndAnswer,i);
							break;
						}
					}
					
				}
				
			}else if(grade==3){
				for(int i=0;i<n;i++) {
				//三年级调用代码
						do {
							
						problem=rQ.questionsShop(); //调用随机出题对象，返回题目
						isRight=aR.startProblem(problem); // 传入题目给四则运算对象
						if(answerNumber>=9999) {
							isRight=false;						//答案限制不超过9999(三年级要求)
						}
						}while(!isRight);// 判断出题过程中是否符合年级规范
						questionAndAnswer.add("("+(i+1)+") "+problem);// 三年级题目加入集合
						questionAndAnswer.add(" = "+answerNumber);// 三年级答案加入集合
						//三年级调用代码
				}
			}
				//System.out.println(questionAndAnswer);
				OutPutSet outPut=new OutPutSet(n); //调用输出
			}else {
				System.out.println(errorMessage);
			}	
						
		}// main end
}// end
		
		




