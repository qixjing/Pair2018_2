import static org.junit.Assert.*;

import org.junit.Test;

public class FileTest {

	@Test
	public void Write() {
		assertTrue(new File().Write("Test.txt", "Test"));
	}

}
