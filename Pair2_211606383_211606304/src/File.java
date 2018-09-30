import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class File {
	
	public boolean Write(String FileName, String str) {
			PrintWriter writer;
			try {
				writer = new PrintWriter(FileName, "UTF-8");
				writer.println(str);
				writer.close();
				return true;
			} catch (FileNotFoundException e) {
				// �ļ�δ�ҵ�
				System.out.print("Error:File Not Found");
				return false;
			} catch (UnsupportedEncodingException e) {
				// ��֧�ֵ��ļ�����
				System.out.print("Error:Unsupported Encoding");
				return false;
			}
	}
	
}
