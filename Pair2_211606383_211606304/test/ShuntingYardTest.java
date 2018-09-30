import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EmptyStackException;
public class ShuntingYardTest {

	@Test
	public void Calc() {
		int Result;
		try {
			Result = Integer.valueOf(new ShuntingYard().Calc("(1 +2) ¡Á3¡Á(1 +2)", 3));
    	}
    	catch(EmptyStackException e){
    		Result = -1;
    	}
		assertEquals(27, Result);
	}

}
