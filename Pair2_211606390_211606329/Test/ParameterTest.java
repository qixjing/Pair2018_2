import static org.junit.Assert.*;		

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParameterTest{
	int expected=0;
	String input1="";
	String input2="";
	String input3="";
	String input4="";


	@Parameters
	public static Collection<Objcet[]>t(){
		return Arrays.asList(new Object[][]{
			{0,"-n","-grade","2","2"},
			{1,"-n","10","-grade","1"},
			{1,"-grade","1","-n","10"},
			{0,"-n","-1","-grade","1"},
			{0,"-n","10","-grade","7"},
			{0,"-n","0","-grade","1"},
			{0,"-n","1002","-grade","2"}
		});
}

public ParameterTest(int expected,String input1,String input2,String input3,String input4){
	this.expected=expected;
	this.input1=input1;
	this.input2=input2;
	this.input3=input3;
	this.input4=input4;
	}
}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		start=0;
	}


	@Test
	public void Mathtest() {
		assertEquals(expected, new MathExam6329(input1,input2,input3,input4).start);;
	}


