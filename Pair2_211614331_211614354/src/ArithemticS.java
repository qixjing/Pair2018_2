
import java.util.Iterator;
import java.util.LinkedList;

//逆波兰大法好！！！
//算法好才是真的好！！！


public class ArithemticS extends Operation{
	 LinkedList<String> shortStack;// 临时存储符号
	 LinkedList<String> postFixStack;// 后缀表达式(集合)
	 String problem;// 题目
	 LinkedList<String> runAnswer;//计算题目的栈
	 String reversedNumber;//倒序元素
	 boolean isRight;// 是否正确
	 
	 
	public  boolean startProblem(String problem) {
		 //problem="( 15 + 25 ) - ( 3 + 8 ) * ( 5 + 4 )";
		 shortStack=new LinkedList<String>();// 临时栈
		 postFixStack=new LinkedList<String>();// 后缀表达式栈
		String partNumber;// 其中一个元素
	
		
		String[] problemString=problem.split("\\s");
				
		for(int i= 0;i<problemString.length;i++) {
			partNumber=problemString[i];
			if(partNumber.matches("\\d+")) {	// 数字直接加入后缀表达式的栈
			
				postFixStack.push(partNumber); // 直接压栈，进入后缀栈
				//System.out.println("数字入栈--后缀栈当前为："+PostfixStack);
			}
			else {
				switch (partNumber) { //如果是符号
				case "(":{ 	// 是左括号
					shortStack.push(partNumber); // 直接加入临时栈
					//System.out.println("左括号入临时栈："+shortStack);
					break;
				}					
				case "+":{					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\+\\-\\×\\÷]")) {	// 栈顶为同级或加减
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//弹玩后，变空
							 }while(shortStack.peek().matches("[\\+\\-\\×\\÷]") );
							 shortStack.push(partNumber);	// 把当前元素入栈
						 }
						 else
							 shortStack.push(partNumber);	// 栈顶就是括号的时候，把当前元素入栈	
					}
					else
						shortStack.push(partNumber); //是空直接压栈
					//System.out.println("+号入临时栈："+shortStack);
					//System.out.println("后缀栈当前为："+PostfixStack);
					break;
				}
				case "-":{
					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\+\\-\\×\\÷]")) {	// 栈顶为同级或加减
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//弹玩后，变空
								 
							 }while(shortStack.peek().matches("[\\+\\-\\×\\÷]") );
							 shortStack.push(partNumber);	// 把当前元素入栈
						 }
						 else
							 shortStack.push(partNumber);	// 栈顶就是括号的时候，把当前元素入栈	
					}
					else
						shortStack.push(partNumber); //是空直接压栈
					//System.out.println("-号入临时栈："+shortStack);
					//System.out.println("后缀栈当前为："+PostfixStack);
					break;
				}
				case "×":{ 
					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\×\\÷]") ) {	// 栈顶为同级或加减
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//弹玩后，变空
							 }while(shortStack.peek().matches("[\\×\\÷]"));
							 shortStack.push(partNumber);	// 把当前元素入栈
						 }
						 else
						 	shortStack.push(partNumber);	// 把当前元素入栈,栈顶是左括号
					}
					else
						shortStack.push(partNumber); //是空直接压栈
					//System.out.println("*号入临时栈："+shortStack);
					//System.out.println("后缀栈当前为："+PostfixStack);
					break;
				}
				case "÷":{
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\×\\÷]") ) {	// 栈顶为同级或加减
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//弹玩后，栈变空，也结束循环
							 }while(shortStack.peek().matches("[\\×\\÷]") );
							 shortStack.push(partNumber);	// 把当前元素入栈
						 }
						 else
						 	shortStack.push(partNumber);	// 把当前元素入栈,栈顶是左括号
					}
					else
						shortStack.push(partNumber); //当前就是空，直接压栈
					//System.out.println("/号入临时栈："+shortStack);
					//System.out.println("后缀栈当前为："+PostfixStack);
					break;
				}
				case ")":{
					//System.out.println("判断前的临时账："+shortStack);
					//System.out.println("判断前的后缀账："+PostfixStack);
					do {
						
						partNumber=shortStack.pop(); // 不管右括号，直接从临时栈中弹栈顶出来
						//System.out.println("弹了一个元素的临时账："+shortStack);
						postFixStack.push(partNumber); // 把栈顶元素，填入后缀栈中
					}while(!shortStack.peekFirst().equals("("));
					//System.out.println("弹完元素的临时账："+shortStack);
					shortStack.pop();	
					//System.out.println("弹完元素的临时账："+shortStack);
					break;
				}
								
				}
			}
		}
		//System.out.println(shortStack);
		if(!shortStack.isEmpty()) {	//把临时栈里的元素全部加入后缀栈中
			do {
				postFixStack.push(shortStack.pop());
			}while(!shortStack.isEmpty()); // 直到栈空为止
						
		}
		//System.out.println("后缀表达式："+PostfixStack);
		
		return solveProblem();// 调用运算，运算后缀表达式	
		
		
	}//class end
	
	public  boolean solveProblem(){
		
		@SuppressWarnings("rawtypes")
		Iterator reversed = postFixStack.descendingIterator();// 迭代器，倒序输出
	      // print list with descending order
		
		 runAnswer=new LinkedList<String>( );
		
	      while (reversed.hasNext()) { 				// 倒序遍历
	    	  reversedNumber= (String) reversed.next();
	    	  if(reversedNumber.matches("\\d+")) {// 题目中没有负号的形式，先不考虑后缀表达式中有负号的情况
	    		  
	    		  runAnswer.push(reversedNumber);//是数字就做压栈夫人
	    		// System.out.println("压入数字："+runAnswer);
	    	  }
	    	  else { // 是符号，那就弹两个，算出结果后，再把结果压回去
	    		  firstNumber=Integer.valueOf(runAnswer.pop()); // 先弹两个数出来
	    		
				  secondNumber=Integer.valueOf(runAnswer.pop());
				 // System.out.println("1: "+firstNumber+" 2："+secondNumber);
	    		  switch (reversedNumber) {
				case "+":{
					
					answerNumber=firstNumber+secondNumber;
					if(answerNumber<0)
					{
						return false;
					}					
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
				case "-":{
					
					answerNumber=secondNumber-firstNumber;// 如果是减法，第二个才是减数
					if(answerNumber<0) {
						return false; // 年级限制，终止运算，题目出错
					}
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
				case "×":{
					answerNumber=firstNumber*secondNumber;
					if(answerNumber<0) // 三年级年级限制
					{
						return false;
					}					
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
				case "÷":{
					try {
						if(secondNumber%firstNumber != 0 || secondNumber==0) { // 年级限制，整除，且不为负数
							return false;
						}
						answerNumber=secondNumber/firstNumber;// 除法同理，第二个才是被除数
						if(answerNumber<0)
						{
							return false;
						}					
					} catch (Exception e) {
						// TODO: handle exception
						return false;
					}
					
					
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
					
				}
	    	  }
	      	      
	      }//迭代器结束
	      setAnswerNumber(Integer.valueOf(runAnswer.get(0)));
	      return true;// 结果满足年级限制，可以输出
	     // System.out.println(runAnswer);
	}
	
}


