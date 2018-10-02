import static org.junit.Assert.*;

import org.junit.Test;

public class testPartArithemticSForSolveP {

	@Test
	public void testSolveProblem() {
		
		Object[][] test=new Object[][] { // 存储不符合要求的数据
			// 存储测试数据，四则运算题目,
			// 存储SolveProblem返回的预期结果（true false），
			// 存储如果可以输出答案（返回为true），答案的值应该是多少
			
			{"( 15 + 25 ) - ( 3 + 8 ) × ( 5 + 4 )",false},
			{"15 ÷ 4 × ( 3 - 2 )",false},// 不符合年级要求，直接返回false,无答案产生
			{"2 × ( 3 + 2 ) ÷ 4",false},
			{"2 + 3 ÷ 4",false},
			{"5 - 6 + 3",false},
			
			// 符合年级要求，返回true,有答案产生
			{"2 + 3 + 6 - 4",true,7},
			{"( 5 × 6 ) ÷ 5 + ( 8 - 6 )",true,8},
			{"( 7 - 6 ) × 9 ÷ 3",true,3},
			{"( ( 2 + 3 ) × 6 ) - 5",true,25},
			{"4 × 3 - 2",true,10}
		};
		
		ArithemticS ar=new ArithemticS();
		for(int i = 0;i<test.length;i++) {
			
			if(i<=4) {
				// 返回值就是solveProblem得出的结果，true false
				System.out.println("预期结果："+test[i][1]+" 返回结果："+ar.startProblem((String)test[i][0]));
				assertEquals(test[i][1],ar.startProblem((String)test[i][0]));
				
			}else {
			
				System.out.println("（有答案！）预期结果："+test[i][1]+" 返回结果："+ar.startProblem((String)test[i][0]));
				System.out.println("预期答案："+test[i][2]+" 返回答案："+ar.getAnswerNumber());
				assertEquals(test[i][1],ar.startProblem((String)test[i][0]));
				assertEquals(test[i][2],ar.getAnswerNumber());
			}
		}
		
		
		
	}

}
