import java.util.HashMap;
import java.util.Stack;
 public class ShuntingYard {
	
	HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
	final double  EPS =  1e-9 ;
	Node[] node = new Node[10005];
	char[] str = new char[10005];
	
	//
	ShuntingYard(){
		mp.put('(',0);
		mp.put('-',1);
		mp.put('+',1);
		mp.put('*',2);//��
		mp.put('/',2);//��
	}
	
	//���ȳ��㷨
	public double shuntYardAlgo(char[] str) throws ArithmeticException
	{
		Stack<Character> oper = new Stack<Character>() ;
	 
		//inNum��ǵ�ǰ�Ƿ������������
		boolean inNum = true;
		//hasDot����Ƿ��Ѿ�����С����
		boolean hasDot = true;
		
		int p = 0;    //ɨ��ָ��λ��
		int cnt = 0;  //���Ż������ּ�����
		int sign = 1; //��ʾ��������
	 
		while(p < str.length){
			//������Ϊ��ʱ����ʾ���ֵ��ַ���ת��Ϊ����
			if(Character.isDigit(str[p]) || str[p] == '.'){
				if(inNum){
					double val = 0;
					double w = 1;
					if(str[p] == '.'){
						hasDot = true;
						val = 0;
					}else{
						hasDot = false;
						val = str[p] - '0';
					}
					p++;
					while(p < str.length && (Character.isDigit(str[p]) || str[p] == '.' )){
						if(str[p] == '.'){
							if(hasDot) throw new ArithmeticException();//һ������������С����
							hasDot = true;
						}else{
							if(hasDot){
								w *= 0.1;
								val += (str[p] - '0') * w;
							}else
								val = val * 10 + str[p] - '0';
						}
						p++;
					}
					p--;
					node[cnt++] = new Node(val * sign, ' ');
					sign = 1;
					inNum = false;
				}else throw new ArithmeticException();
			}else{
				switch(str[p]){
				case '(':
					oper.push(str[p]);
					break;
				case ')':
					while(!oper.empty() && oper.peek() != '('){
						node[cnt++] = new Node(0, oper.peek());
						oper.pop();
					}
					if(oper.empty())
						throw new ArithmeticException();
					oper.pop();
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					// �ж����ֵ�������
					if(inNum){
						if(str[p] != '+' && str[p] != '-') 
							throw new ArithmeticException();  //����ֻ����Ϊ������Ϊ��
						while(str[p] == '+' || str[p] == '-'){
							if(str[p] == '-') 
								sign *= -1;
							p++;
						}
						p--;
					}else{
						while(!oper.empty() && mp.get(str[p]) <= mp.get(oper.peek())){
							node[cnt++] = new Node(0, oper.pop());
						}
						oper.push(str[p]);
						inNum = true;
					}
					break;
				}
			}
			p++;
		}
		while(!oper.empty()){
			node[cnt++] = new Node(0, oper.peek());
			oper.pop();
		}
//		for(int i = 0; i < cnt; i++) {
//			if(node[i].val!=0) {
//				System.out.print(node[i].val);
//			}else {
//				System.out.print(node[i].opt);
//			}
//			
//		}
		return CalPoland(node, cnt);
	}
	
	//�沨�����ʽ�ļ���
	double CalPoland(Node node[], int n) throws ArithmeticException
	{
		Stack<Double> s = new Stack<Double>();
		for(int i = 0; i < n; i++)
		{
			if(node[i].opt == ' ')
				s.push(node[i].val);
			else
			{
				double a = s.peek();
				s.pop();
				double b = s.peek();
				s.pop();
				switch(node[i].opt)
				{
					case '+':
						s.push(b + a);
						break;
					case '-':
						s.push(b - a);
						break;
					case '*':
						s.push(b * a);
						break;
					case '/':
						if(Math.abs(a) < EPS || a == 0) {
							throw new ArithmeticException();
						}
						s.push(b / a);
						break;
				}
			}
		}
		return s.peek();
	}
}