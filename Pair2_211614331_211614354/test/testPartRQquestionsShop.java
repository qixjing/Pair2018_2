import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class testPartRQquestionsShop {//����RandomQuestions���е�questionsShop()����

	@Test
	public void testquestionsShop() {
		RandomQuestions rq=new RandomQuestions();
	    String s1=rq.questionsShop();	
	    String[] a=s1.split(" ");
	    int symbolNumber=0; // ������ŵĸ���
	    int digNumber=0; // �������ֵĸ���
	    for(int i=0;i<a.length;i++) {
	    	if(a[i].matches("[\\+\\-\\��\\��]")) {
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
	    System.out.println("��ǰ��ĿΪ: "+rq.problemSave); 
	 }
}


