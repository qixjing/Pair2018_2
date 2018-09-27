import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class testPartArithemticSForStartP { // 测试Arithemitics中的startProblem能否产生正确的后缀表达式
	
	@Test
	void testStartProblem() {
		
		String[][] test=new String[][] { // 存储测试数据，及正确后缀表达式
			{"-×+45+83+2515","( 15 + 25 ) - ( 3 + 8 ) × ( 5 + 4 )"},
			{"×-23÷415","15 ÷ 4 × ( 3 - 2 )"},
			{"÷4×+232","2 × ( 3 + 2 ) ÷ 4"},
			{"+÷432","2 + 3 ÷ 4"}
			/*
			{"-×+45+83+2515","( 15 + 25 ) - ( 3 + 8 ) × ( 5 + 4 )"},
			{"×-23÷415","15 ÷ 4 × ( 3 - 2 )"},
			{"÷4×+232","2 × ( 3 + 2 ) ÷ 4"},
			{"+÷432","2 + 3 ÷ 4"} //改变正确结果，验证能否被检测出来
			*/
		};
		
		ArithemticS as=new ArithemticS();
		String s1="";
		
		for(int i=0;i<test.length;i++) {
			s1="";
			as.startProblem(test[i][1]);
			for(int j=0;j<as.postFixStack.size();j++) {
				s1=s1+as.postFixStack.get(j);
			}
			//System.out.println("第"+(i+1)+"组数据："+s1.equals(test[i][0])); // 判断后缀表达式和正确结果是否相同			
			assertEquals(test[i][0], s1);// 判断后缀表达式和正确结果是否相同			
		
		}
		
		
	}

}
