import java.util.ArrayList;
import java.util.Random;

public class RandomQuestions extends Operation{
	
	 String  sProblem = "";	// 存储题目
	 boolean needBracket = false;// 是否需要括号
	 int howMany = 0;// 需要几个运算符1
	 int bracketNumber = 0; // 如果需要括号，要几个
	 int whichSymbol = 0; // 判断是哪一个运算符号
	 int number = 0;// 随机产生的数字
	 ArrayList<String> problemSet; // 存题目的集合
	 int isAdd = 0;// 符号是不相同的吗
	 int isDivison = 0; // 除
	 int isMult = 0; // 乘
	 int isSub = 0;// 减
	 String positionCode = "";// 定位代码
	 String problem= ""; // 题目字符串源码传给其他类运算
	 String problemSave="";// 保存为题目，打印到文档
		
	public String  questionsShop() {		// 随机产生题目的方法(三年级专用)
		problemSave="";
		problemSave="";
		Random rand = new Random();
		ArrayList<String> problemSet = new ArrayList<String>(); 
		howMany = rand.nextInt(3)+2;	// 需要几个运算符
		
		do {
			problemSet.clear();
			for (int i = 0; i < 2 * howMany + 1; i++) { // 运算次数

				if (i % 2 == 0) {
					number = rand.nextInt(100) + 1;
					problemSet.add(String.valueOf(number));
				} else {
					whichSymbol = rand.nextInt(4);
					switch (whichSymbol) {
					case 0: {
						problemSet.add("+");
						isAdd++;
						break;
					}
					case 1: {
						problemSet.add("-");
						isSub++;
						break;
					}
					case 2: {
						problemSet.add("×");
						isMult++;
						break;
					}
					case 3: {
						problemSet.add("÷");
						isDivison++;
						break;
					}

					}

					//////
				}
			}
		} while (isAdd == howMany || isSub == howMany || isMult == howMany || isDivison == howMany);
		
		isAdd=0; // 清空计数器
		isSub=0;
		isMult=0;
		isDivison=0;
		
		needBracket = rand.nextBoolean(); // 是否需要括号
		
		if(needBracket) {	// 需要括号
			positionCode(howMany); // 设置括号标准定位码
			for(int i = 0;i < positionCode.length(); i++) {
				if(String.valueOf(positionCode.charAt(i)).equals("("))	// 扫描到左括号
					problemSet.add(i, String.valueOf(positionCode.charAt(i)));	// 添加进集合
				else if(String.valueOf(positionCode.charAt(i)).equals(")"))	// 扫描到右括号
					{
					
					problemSet.add(i, String.valueOf(positionCode.charAt(i))); // 添加进集合
					
					}
				}
			}
		
		
			/*
			 更新，不用字符串传递题目，改用集合把题目传入需要调用的类中，
			 字符串只是单纯的保留题目
			 
			*/
			
			for (int i = 0; i < problemSet.size(); i++) {

				if (i == problemSet.size() - 1) {
					problemSave = problemSave + problemSet.get(i);
				} else
					problemSave = problemSave + problemSet.get(i) + " ";

			}

		//	System.out.println(problemSave);

			return problemSave;
		
		
			
			
			
			
			
		}
		
		
		
		
		
		
		
//}
	
	public  void positionCode(int howMany) {	// 获取括号标准定位码
		
		
		positionCode="";
		Random rand = new Random();
		int order = 0;
		
		switch (howMany) {
			case 2:{
				order=rand.nextInt(2)+1;
				if(order==1)	positionCode="(---)";
				else positionCode="--(---)";
				break;
			}
			case 3:{
				order=rand.nextInt(5)+1;
				switch (order) {
				case 1:{
					positionCode="(---)-(---)";
					break;
				}
				case 2:{
					positionCode="(-----)";
					break;
				}
				case 3:{
					positionCode="(---)";
					break;
				}
				case 4:{
					positionCode="--(---)";
					break;
				}
				case 5:{
					positionCode="--(--(---))";
					break;
				}
								
				}
				break;
			}
			case 4:{
				order=rand.nextInt(7)+1;
				switch (order) {
				case 1:{
					positionCode="(---)-(---)";
					break;
				}
				case 2:{
					positionCode="--(---)-(---)";
					break;
				}
				case 3:{
					positionCode="--((---)--)";
					break;
				}
				case 4:{
					positionCode="--((---)-(---))";
					break;
				}
				case 5:{
					positionCode="(-----)";
					break;
				}
				case 6:{
					positionCode="--(-----)";
					break;
				}
				case 7:{
					positionCode="((---)--)-(---)";
					break;
				}
					
					
				}
				break;
			}

		
		}
		
		
	}
    	
}
