
import java.io.IOException;
import java.io.PrintWriter;


public class OutPutSet {                //������������е���Ŀ

	public   OutPutSet(int n) {               //n�����ж��м�����Ŀ
			
		
		try ( PrintWriter w = new PrintWriter("out.txt")) //��ӡ��out.txt�ĵ�
		{
			
		
			for(int i=0;i<=(2*n-2);i=i+2)
				w.println(MathExam.questionAndAnswer.get(i));  //�����Ŀ
			
			w.println("");
			
			for(int i=0;i<=(2*n-2);i=i+2)
			{
				w.print(MathExam.questionAndAnswer.get(i));      //�����Ŀ
				w.println(MathExam.questionAndAnswer.get(i+1));     //�����
				
			}
			w.close();//�ر���
		} catch(IOException e){
			
		}
				
	}
}
