import java.util.ArrayList;
import java.util.Random;

public class RandomQuestions extends Operation{
	
	 String  sProblem = "";	// �洢��Ŀ
	 boolean needBracket = false;// �Ƿ���Ҫ����
	 int howMany = 0;// ��Ҫ���������1
	 int bracketNumber = 0; // �����Ҫ���ţ�Ҫ����
	 int whichSymbol = 0; // �ж�����һ���������
	 int number = 0;// �������������
	 ArrayList<String> problemSet; // ����Ŀ�ļ���
	 int isAdd = 0;// �����ǲ���ͬ����
	 int isDivison = 0; // ��
	 int isMult = 0; // ��
	 int isSub = 0;// ��
	 String positionCode = "";// ��λ����
	 String problem= ""; // ��Ŀ�ַ���Դ�봫������������
	 String problemSave="";// ����Ϊ��Ŀ����ӡ���ĵ�
		
	public String  questionsShop() {		// ���������Ŀ�ķ���(���꼶ר��)
		problemSave="";
		problemSave="";
		Random rand = new Random();
		
		ArrayList<String> problemSet = new ArrayList<String>();
		//ArrayList<Object> problemSet = new ArrayList<Object>();
		//problemSet = new ArrayList<String>(); 
		howMany = rand.nextInt(3)+2;	// ��Ҫ���������
		
		do {
			problemSet.clear();
			for (int i = 0; i < 2 * howMany + 1; i++) { // �������

				if (i % 2 == 0) {
					number = rand.nextInt(40) + 1;// ��С��Ŀ����
					problemSet.add(String.valueOf(number));
					//problemSet.add(String.valueOf(number));
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
						problemSet.add("��");
						isMult++;
						break;
					}
					case 3: {
						problemSet.add("��");
						isDivison++;
						break;
					}

					}

					//////
				}
			}
		} while (isAdd == howMany || isSub == howMany || isMult == howMany || isDivison == howMany);
		
		isAdd=0; // ��ռ�����
		isSub=0;
		isMult=0;
		isDivison=0;
		
		needBracket = rand.nextBoolean(); // �Ƿ���Ҫ����
		
		if(needBracket) {	// ��Ҫ����
			positionCode(howMany); // �������ű�׼��λ��
		
			for(int i = 0;i < positionCode.length(); i++) {
				if(String.valueOf(positionCode.charAt(i)).equals("("))	// ɨ�赽������
					
					problemSet.add(i, String.valueOf(positionCode.charAt(i)));	// ��ӽ�����
				else if(String.valueOf(positionCode.charAt(i)).equals(")"))	// ɨ�赽������
					{
					
					problemSet.add(i, String.valueOf(positionCode.charAt(i))); // ��ӽ�����
					
					}
				}
		
			}
		
		
			/*
			 ���£������ַ���������Ŀ�����ü��ϰ���Ŀ������Ҫ���õ����У�
			 �ַ���ֻ�ǵ����ı�����Ŀ
			 
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
	
	public  void positionCode(int howMany) {	// ��ȡ���ű�׼��λ��
		
		
		positionCode="";
		Random rand = new Random();
		int order = 0;
		//System.out.println("Ps+"+problemSet);
		switch (howMany) {
			case 2:{
				order=rand.nextInt(2)+1;
				if(order==1)	 positionCode="(---)";
				else   positionCode="--(---)";
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
