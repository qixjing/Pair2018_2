import java.io.FileOutputStream;
import java.io.PrintStream;

public class Txt {

	public static void createMathExamTxt(String txt) {
		try {
			FileOutputStream fos = new FileOutputStream("out.txt");
			PrintStream ps = new PrintStream(fos);
			ps.println(txt);
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
