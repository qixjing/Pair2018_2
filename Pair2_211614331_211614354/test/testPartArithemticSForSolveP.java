import static org.junit.Assert.*;

import org.junit.Test;

public class testPartArithemticSForSolveP {

	@Test
	public void testSolveProblem() {
		
		Object[][] test=new Object[][] { // �洢������Ҫ�������
			// �洢�������ݣ�����������Ŀ,
			// �洢SolveProblem���ص�Ԥ�ڽ����true false����
			// �洢�����������𰸣�����Ϊtrue�����𰸵�ֵӦ���Ƕ���
			
			{"( 15 + 25 ) - ( 3 + 8 ) �� ( 5 + 4 )",false},
			{"15 �� 4 �� ( 3 - 2 )",false},// �������꼶Ҫ��ֱ�ӷ���false,�޴𰸲���
			{"2 �� ( 3 + 2 ) �� 4",false},
			{"2 + 3 �� 4",false},
			{"5 - 6 + 3",false},
			
			// �����꼶Ҫ�󣬷���true,�д𰸲���
			{"2 + 3 + 6 - 4",true,7},
			{"( 5 �� 6 ) �� 5 + ( 8 - 6 )",true,8},
			{"( 7 - 6 ) �� 9 �� 3",true,3},
			{"( ( 2 + 3 ) �� 6 ) - 5",true,25},
			{"4 �� 3 - 2",true,10}
		};
		
		ArithemticS ar=new ArithemticS();
		for(int i = 0;i<test.length;i++) {
			
			if(i<=4) {
				// ����ֵ����solveProblem�ó��Ľ����true false
				System.out.println("Ԥ�ڽ����"+test[i][1]+" ���ؽ����"+ar.startProblem((String)test[i][0]));
				assertEquals(test[i][1],ar.startProblem((String)test[i][0]));
				
			}else {
			
				System.out.println("���д𰸣���Ԥ�ڽ����"+test[i][1]+" ���ؽ����"+ar.startProblem((String)test[i][0]));
				System.out.println("Ԥ�ڴ𰸣�"+test[i][2]+" ���ش𰸣�"+ar.getAnswerNumber());
				assertEquals(test[i][1],ar.startProblem((String)test[i][0]));
				assertEquals(test[i][2],ar.getAnswerNumber());
			}
		}
		
		
		
	}

}
