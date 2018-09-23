import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathExamTest {

    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCheckInputRight1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数正常-n x -grade x
        String[] input = new String[] {"-n", "10", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckInputRight2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数正常-grade x -n x
        String[] input = new String[] {"-grade", "3", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckInputParaNumber() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数个数问题
        String[] input = new String[] {};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotPositive1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量为负
        String[] input = new String[] {"-n", "-10", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotPositive2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量为负
        String[] input = new String[] {"-grade", "3", "-n", "-10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNumber1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量范围
        String[] input = new String[] {"-n", "0", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNumber2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量范围
        String[] input = new String[] {"-grade", "3", "-n", "0"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotInteger1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量非整数
        String[] input = new String[] {"-n", "h", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotInteger2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数题目数量非整数
        String[] input = new String[] {"-grade", "3", "-n", "h"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeOut1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数年级选择超出范围
        String[] input = new String[] {"-n", "10", "-grade", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeOut2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数年级选择超出范围
        String[] input = new String[] {"-grade", "10", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeNotPositive1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数年级非正整数
        String[] input = new String[] {"-n", "10", "-grade", "h"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeNotPositive2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //测试参数年级非正整数
        String[] input = new String[] {"-grade", "h", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }

}
