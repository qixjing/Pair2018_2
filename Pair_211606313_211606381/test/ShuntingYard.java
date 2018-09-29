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
		mp.put('*',2);//×
		mp.put('/',2);//÷
	}
	
	//调度场算法
	public double shuntYardAlgo(char[] str) throws ArithmeticException
	{
		Stack<Character> oper = new Stack<Character>() ;
	 
		//inNum标记当前是否可以输入数字
		boolean inNum = true;
		//hasDot标记是否已经输入小数点
		boolean hasDot = true;
		
		int p = 0;    //扫描指针位置
		int cnt = 0;  //符号或者数字计数器
		int sign = 1; //表示正负符号
	 
		while(p < str.length){
			//当条件为真时将表示数字的字符串转化为数字
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
							if(hasDot) throw new ArithmeticException();//一个数字有两个小数点
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
					// 判断数字的正负性
					if(inNum){
						if(str[p] != '+' && str[p] != '-') 
							throw new ArithmeticException();  //数字只可能为正或者为负
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
	
	//逆波兰表达式的计算
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