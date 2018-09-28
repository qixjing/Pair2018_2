
public class Parameter {
	
	private int Grade;
	private int Number;
	
	//构造函数
	public Parameter(String[] args) {
		DealData(args);
	}
	//处理数据
	public boolean DealData(String[] args) {
		//检查参数个数是否正确
		if(args.length != 4) {
			System.out.println("Error:Parameter Number Error");
			return false;
		}
		//检查参数格式是否正确
		if(args[0].equals("-n") == false && args[0].equals("-Grade") == false) {
			System.out.println("Error:Parameter Format Error");
			return false;
		}
		//检查参数格式是否正确
		if(args[2].equals("-n") == false && args[2].equals("-Grade") == false) {
			System.out.println("Error:Parameter Format Error");
			return false;
		}
		// 判断题目个数是否为纯数字
		if (isNum(args[1]) == false || isNum(args[3]) == false) {
			System.out.println("Error:Parameter Type Error");
			return false;
		}
		//获取数据
		if(args[0].equals("-n")) {
			Grade = Integer.valueOf(args[3]);
			Number = Integer.valueOf(args[1]);
		}else
		{
			Grade = Integer.valueOf(args[1]);
			Number = Integer.valueOf(args[3]);
		}
		//检查数据格式
		if(Grade > 3 || Grade < 1 || Number < 1) {
			System.out.println("Error:Parameter Range Error");
			return false;
		}
		return true;
	}
	
	//获取年级
	public int GetGrade() {
		return Grade;
	}
	
	//获取数量
	public int GetNumber() {
		return Number;
	}
	
	//判断字符串是否为纯数字
	private boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

}
