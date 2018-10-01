
public class RegexTest {
	public boolean test(String s) {
		if(s.matches("(\\-grade [123] \\-n [1-9]\\d*)|(\\-n [1-9]\\d* \\-grade [123])"))
			return true;
		return false;
	
		
	}
}
