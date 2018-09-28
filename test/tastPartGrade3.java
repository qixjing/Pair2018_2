import static org.junit.Assert.*;

import org.junit.Test;

public class tastPartGrade3 {

	@Test
	public void test() {
		RandomQuestions rQ=new RandomQuestions();
		ArithemticS aR=new ArithemticS();
		
		String problem=rQ.questionsShop(); //调用随机出题对象，返回题目
		boolean isRight=aR.startProblem(problem); // 传入题目给四则运算对象
		
		if(aR.answerNumber>=9999) {
			isRight=false;						//答案限制不超过9999(三年级要求)
		}
		if(isRight)
			System.out.println("三四年级四则运算测试："+problem+" = "+aR.answerNumber);// 判断出题过程中是否符合年级规范
		else
			System.out.println("当前题目错误！！！不符合年级要求："+problem+" = "+aR.answerNumber);
	}

}
