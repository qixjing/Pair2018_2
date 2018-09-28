import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class testPartRQquestionsShop {//测试RandomQuestions类中的questionsShop()方法

	@Test
	public void testquestionsShop() {
		RandomQuestions rq=new RandomQuestions();
	    String s1=rq.questionsShop();	
	    String[] a=s1.split(" ");
	    int symbolNumber=0; // 定义符号的个数
	    int digNumber=0; // 定义数字的个数
	    for(int i=0;i<a.length;i++) {
	    	if(a[i].matches("[\\+\\-\\×\\÷]")) {
	    		symbolNumber++;
	    	}
	    	else if(a[i].matches("\\d+")) {
	    		digNumber++;
	    	}
	    }
	    boolean expected=true;
	    boolean right;
	    if(digNumber-symbolNumber==1) {
	    	right=true;
	    }
	    else {
	    	right=false;
	    }
	    assertEquals(expected, right);
	    symbolNumber=0;
	    digNumber=0;
	    System.out.println("当前题目为: "+rq.problemSave); 
	 }
}


