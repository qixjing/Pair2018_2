import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MathExamTest1 {
	boolean expected = false;
	String[] args;
	Character[] t;
	boolean expected2 = false;
	int len;
	int grade;
	
	@Parameterized.Parameters
	public static Collection<Object[]> t(){
		return Arrays.asList(new Object[][]{
			
			{false,new String[] {},new Character[] {null,null,'+',null,'¡Á'},false},
			{false, new String[] {"100"},new Character[] {null,null,'¡Â',null,null},true},
			{true,new String[] {"-n", "10","-grade","1"},new Character[] {null,null,'¡Â',null,null,'+',null},true},
			{false,new String[] {"-n", "10.5","-grade","1"},new Character[] {null,null,'¡Â','+',null,null,'+'},true},
			{false,new String[] {"-n", "ascc", "-grade", "2"},new Character[] {null,null,null,'+','¡Â',null,'+'},false},
			{false,new String[] {"-n", "10", "-grade", "vsdv"},new Character[] {null,null,'¡Â','¡Â',null,null,null},true},
			{true,new String[] {"-n", "00001","-grade", "3"},new Character[] {null,null,'¡Â','¡Á',null,null,'+'},true},
			{false,new String[] {"-n", "1000","-grade","2.3"},new Character[] {null,null,'-',null,'¡Á'},false},
			{true,new String[] {"-n", "10","-grade","002"},new Character[] {null,null,null,'+','¡Â',null,null},true},
			{false,new String[] {"-n", "10000","-grade","3"},new Character[] {null,null,'¡Á','¡Á',null,null,'+'},true},
			{false,new String[] {"-n", "-1","-grade","3"},new Character[] {null,null,'+',null,null},true},
			{false,new String[] {"1000", "-n","-grade","2"},new Character[] {null,null,null,'-','-',null,'¡Á'},false},
			{false,new String[] {"-n", "10","-grade","-3"},new Character[] {null,null,null,'¡Á','-'},false},
			{false,new String[] {"-n", "1000","2","-grade"},new Character[] {null,null,'¡Á','¡Á',null,null,'+'},true},
			{true,new String[] {"-grade", "2", "-n", "1000"},new Character[] {null,null,'-','¡Á',null,null,'+'},true},
			{false,new String[] {"-grade", "0.1", "-n", "800"},new Character[] {null,null,'+','+',null,null,'¡Â'},true},
			{false,new String[] {"-grade", "a1", "-n", "10"},new Character[] {null,null,'¡Á',null,'¡Â'},false},
			{true,new String[] {"-grade", "001", "-n", "20"},new Character[] {null,null,null,null,'¡Á','¡Â',null},true},
			{true,new String[] {"-grade", "1", "-n", "0000000002"},new Character[] {null,null,null,null,'+','¡Á',null},true},
			{false,new String[] {"-grade", "0.1", "-n", "0.1"},new Character[] {null,null,'-','¡Á',null,null,'-'},true},
			{false,new String[] {"-n", "a1","-grade","a1"},new Character[] {null,null,'¡Á',null,'-',null,'-'},false}

		});
	}
	
	public MathExamTest1(boolean expected,String[] args,Character[] t,boolean expected2) {
		this.expected = expected;
		this.args = args;
		this.t = t;
		this.expected2 = expected2;
	}
	
	@Test
	public void testJudgmentParameter() {
		assertEquals(expected, MathExam.judgmentParameter(args));
	}
	
	@Test(timeout = 200)
	public void testGeneratingTopic() {
		Character[] topic = MathExam.generatingTopic(3);
		assertEquals(true, (topic != null));
	}
	
	@Test
	public void testIsInfixExpre() {
		assertEquals(expected2, MathExam.isInfixExpre(t));
	}




}
