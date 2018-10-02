
import java.util.Iterator;
import java.util.LinkedList;

//�沨���󷨺ã�����
//�㷨�ò�����ĺã�����


public class ArithemticS extends Operation{
	 LinkedList<String> shortStack;// ��ʱ�洢����
	 LinkedList<String> postFixStack;// ��׺���ʽ(����)
	 String problem;// ��Ŀ
	 LinkedList<String> runAnswer;//������Ŀ��ջ
	 String reversedNumber;//����Ԫ��
	 boolean isRight;// �Ƿ���ȷ
	 
	 
	public  boolean startProblem(String problem) {
		 //problem="( 15 + 25 ) - ( 3 + 8 ) * ( 5 + 4 )";
		 shortStack=new LinkedList<String>();// ��ʱջ
		 postFixStack=new LinkedList<String>();// ��׺���ʽջ
		String partNumber;// ����һ��Ԫ��
	
		
		String[] problemString=problem.split("\\s");
				
		for(int i= 0;i<problemString.length;i++) {
			partNumber=problemString[i];
			if(partNumber.matches("\\d+")) {	// ����ֱ�Ӽ����׺���ʽ��ջ
			
				postFixStack.push(partNumber); // ֱ��ѹջ�������׺ջ
				//System.out.println("������ջ--��׺ջ��ǰΪ��"+PostfixStack);
			}
			else {
				switch (partNumber) { //����Ƿ���
				case "(":{ 	// ��������
					shortStack.push(partNumber); // ֱ�Ӽ�����ʱջ
					//System.out.println("����������ʱջ��"+shortStack);
					break;
				}					
				case "+":{					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\+\\-\\��\\��]")) {	// ջ��Ϊͬ����Ӽ�
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//����󣬱��
							 }while(shortStack.peek().matches("[\\+\\-\\��\\��]") );
							 shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ
						 }
						 else
							 shortStack.push(partNumber);	// ջ���������ŵ�ʱ�򣬰ѵ�ǰԪ����ջ	
					}
					else
						shortStack.push(partNumber); //�ǿ�ֱ��ѹջ
					//System.out.println("+������ʱջ��"+shortStack);
					//System.out.println("��׺ջ��ǰΪ��"+PostfixStack);
					break;
				}
				case "-":{
					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\+\\-\\��\\��]")) {	// ջ��Ϊͬ����Ӽ�
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//����󣬱��
								 
							 }while(shortStack.peek().matches("[\\+\\-\\��\\��]") );
							 shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ
						 }
						 else
							 shortStack.push(partNumber);	// ջ���������ŵ�ʱ�򣬰ѵ�ǰԪ����ջ	
					}
					else
						shortStack.push(partNumber); //�ǿ�ֱ��ѹջ
					//System.out.println("-������ʱջ��"+shortStack);
					//System.out.println("��׺ջ��ǰΪ��"+PostfixStack);
					break;
				}
				case "��":{ 
					
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\��\\��]") ) {	// ջ��Ϊͬ����Ӽ�
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//����󣬱��
							 }while(shortStack.peek().matches("[\\��\\��]"));
							 shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ
						 }
						 else
						 	shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ,ջ����������
					}
					else
						shortStack.push(partNumber); //�ǿ�ֱ��ѹջ
					//System.out.println("*������ʱջ��"+shortStack);
					//System.out.println("��׺ջ��ǰΪ��"+PostfixStack);
					break;
				}
				case "��":{
					if(!shortStack.isEmpty()) {
						
						 if(shortStack.peek().matches("[\\��\\��]") ) {	// ջ��Ϊͬ����Ӽ�
							 do {
								 postFixStack.push(shortStack.pop());
								 if(shortStack.isEmpty())
									 break;//�����ջ��գ�Ҳ����ѭ��
							 }while(shortStack.peek().matches("[\\��\\��]") );
							 shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ
						 }
						 else
						 	shortStack.push(partNumber);	// �ѵ�ǰԪ����ջ,ջ����������
					}
					else
						shortStack.push(partNumber); //��ǰ���ǿգ�ֱ��ѹջ
					//System.out.println("/������ʱջ��"+shortStack);
					//System.out.println("��׺ջ��ǰΪ��"+PostfixStack);
					break;
				}
				case ")":{
					//System.out.println("�ж�ǰ����ʱ�ˣ�"+shortStack);
					//System.out.println("�ж�ǰ�ĺ�׺�ˣ�"+PostfixStack);
					do {
						
						partNumber=shortStack.pop(); // ���������ţ�ֱ�Ӵ���ʱջ�е�ջ������
						//System.out.println("����һ��Ԫ�ص���ʱ�ˣ�"+shortStack);
						postFixStack.push(partNumber); // ��ջ��Ԫ�أ������׺ջ��
					}while(!shortStack.peekFirst().equals("("));
					//System.out.println("����Ԫ�ص���ʱ�ˣ�"+shortStack);
					shortStack.pop();	
					//System.out.println("����Ԫ�ص���ʱ�ˣ�"+shortStack);
					break;
				}
								
				}
			}
		}
		//System.out.println(shortStack);
		if(!shortStack.isEmpty()) {	//����ʱջ���Ԫ��ȫ�������׺ջ��
			do {
				postFixStack.push(shortStack.pop());
			}while(!shortStack.isEmpty()); // ֱ��ջ��Ϊֹ
						
		}
		//System.out.println("��׺���ʽ��"+PostfixStack);
		
		return solveProblem();// �������㣬�����׺���ʽ	
		
		
	}//class end
	
	public  boolean solveProblem(){
		
		@SuppressWarnings("rawtypes")
		Iterator reversed = postFixStack.descendingIterator();// ���������������
	      // print list with descending order
		
		 runAnswer=new LinkedList<String>( );
		
	      while (reversed.hasNext()) { 				// �������
	    	  reversedNumber= (String) reversed.next();
	    	  if(reversedNumber.matches("\\d+")) {// ��Ŀ��û�и��ŵ���ʽ���Ȳ����Ǻ�׺���ʽ���и��ŵ����
	    		  
	    		  runAnswer.push(reversedNumber);//�����־���ѹջ����
	    		// System.out.println("ѹ�����֣�"+runAnswer);
	    	  }
	    	  else { // �Ƿ��ţ��Ǿ͵����������������ٰѽ��ѹ��ȥ
	    		  firstNumber=Integer.valueOf(runAnswer.pop()); // �ȵ�����������
	    		
				  secondNumber=Integer.valueOf(runAnswer.pop());
				 // System.out.println("1: "+firstNumber+" 2��"+secondNumber);
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
					
					answerNumber=secondNumber-firstNumber;// ����Ǽ������ڶ������Ǽ���
					if(answerNumber<0) {
						return false; // �꼶���ƣ���ֹ���㣬��Ŀ����
					}
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
				case "��":{
					answerNumber=firstNumber*secondNumber;
					if(answerNumber<0) // ���꼶�꼶����
					{
						return false;
					}					
					runAnswer.push(String.valueOf(answerNumber));
					break;
				}
				case "��":{
					try {
						if(secondNumber%firstNumber != 0 || secondNumber==0) { // �꼶���ƣ��������Ҳ�Ϊ����
							return false;
						}
						answerNumber=secondNumber/firstNumber;// ����ͬ���ڶ������Ǳ�����
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
	      	      
	      }//����������
	      setAnswerNumber(Integer.valueOf(runAnswer.get(0)));
	      return true;// ��������꼶���ƣ��������
	     // System.out.println(runAnswer);
	}
	
}


