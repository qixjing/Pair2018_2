import static org.junit.Assert.*;
import org.junit.Test;

public class MathEaxmtest {
	@Test
	public void test2() {
		String[] abc= {"-n","10","-grade","2"};
		MathExam1253.main(abc);
	}
	@Test
	public void test3() {
		String[] abc= {"-n","10","-grade","3"};
		MathExam1253.main(abc);
	}
	@Test
	public void test1() {
		String[] abc= {"-n","10","-grade","1"};
		MathExam1253.main(abc);
	}
}
