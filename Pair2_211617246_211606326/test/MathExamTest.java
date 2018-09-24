import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathExamTest {

    @Test
    public void testCheckInputRight1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数正常-n x -grade x
        String[] input = new String[] {"-n", "10", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckInputRight2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数正常-grade x -n x
        String[] input = new String[] {"-grade", "3", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckInputParaNumber() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数个数问题
        String[] input = new String[] {};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotPositive1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量为负
        String[] input = new String[] {"-n", "-10", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotPositive2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量为负
        String[] input = new String[] {"-grade", "3", "-n", "-10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNumber1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量范围
        String[] input = new String[] {"-n", "0", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNumber2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量范围
        String[] input = new String[] {"-grade", "3", "-n", "0"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotInteger1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量非整数
        String[] input = new String[] {"-n", "h", "-grade", "3"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputQuestionsNotInteger2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数题目数量非整数
        String[] input = new String[] {"-grade", "3", "-n", "h"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeOut1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数年级选择超出范围
        String[] input = new String[] {"-n", "10", "-grade", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeOut2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数年级选择超出范围
        String[] input = new String[] {"-grade", "10", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeNotPositive1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数年级非正整数
        String[] input = new String[] {"-n", "10", "-grade", "h"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckInputGradeNotPositive2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试参数年级非正整数
        String[] input = new String[] {"-grade", "h", "-n", "10"};
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("checkInput", String[].class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, (Object) input);
        assertEquals(false, result);
    }

    @Test
    public void testNibolan1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试逆波兰表达式生成方法
        List<String> para = new ArrayList<String>();
        para.add("3");
        para.add("*");
        para.add("(");
        para.add("5");
        para.add("+");
        para.add("7");
        para.add("/");
        para.add("9");
        para.add(")");
        List<String> right = new ArrayList<String>();
        right.add("3");
        right.add("5");
        right.add("7");
        right.add("9");
        right.add("/");
        right.add("+");
        right.add("*");
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("nibolan", List.class);
        method.setAccessible(true);
        List<String> result = (List<String>) method.invoke(math, para);
        assertEquals(right, result);
    }
    
    @Test
    public void testNibolan2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试逆波兰表达式生成方法
        List<String> para = new ArrayList<String>();
        para.add("1");
        para.add("+");
        para.add("2");
        para.add("*");
        para.add("3");
        para.add("+");
        para.add("4");
        para.add("*");
        para.add("5");
        List<String> right = new ArrayList<String>();
        right.add("1");
        right.add("2");
        right.add("3");
        right.add("*");
        right.add("+");
        right.add("4");
        right.add("5");
        right.add("*");
        right.add("+");
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("nibolan", List.class);
        method.setAccessible(true);
        List<String> result = (List<String>) method.invoke(math, para);
        assertEquals(right, result);
    }
    
    @Test
    public void testCalc() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试后缀表达式计算方法
        List<String> para = new ArrayList<String>();
        para.add("1");
        para.add("2");
        para.add("+");
        para.add("3");
        para.add("4");
        para.add("*");
        para.add("5");
        para.add("/");
        para.add("-");
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("calc", List.class);
        method.setAccessible(true);
        double result = (double) method.invoke(math, para);
        assertEquals(0.6, result, 0.01);
    }
    
    @Test
    public void testGrade1() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试一年级题目生成方法
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("grade1", int.class);
        method.setAccessible(true);
        method.invoke(math, 10);
        assertNotNull(math.strArrayA);
    }
    
    @Test
    public void testGrade2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试二年级题目生成方法
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("grade2", int.class);
        method.setAccessible(true);
        method.invoke(math, 10);
        assertNotNull(math.strArrayA);
    }
    
    @Test
    public void testGrade3() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试三年级题目生成方法
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("grade3", int.class);
        method.setAccessible(true);
        method.invoke(math, 10);
        assertNotNull(math.strArrayA);
    }
    
    @Test
    public void testCreateTxt() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        // 测试文件生成方法
        MathExam math = new MathExam();
        String filename = math.FILENAME;
        Method method = math.getClass().getDeclaredMethod("createTxt", String.class);
        method.setAccessible(true);
        method.invoke(math, filename);
        
        File file = new File(filename);
        assertEquals(true, file.exists());
    }
    
    @Test
    public void testIsNumber1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试数字判断方法为数字时
        String para = "6";
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("isNumber", String.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, para);
        assertEquals(true, result);
    }
    
    @Test
    public void testIsNumber2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 测试数字判断方法非数字时
        String para = "*";
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("isNumber", String.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(math, para);
        assertEquals(false, result);
    }
    
    @Test
    public void testCmpOper1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试符号优先级比较方法 op1 > op2
        String op1 = "+";
        String op2 = "*";
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("cmpOper", String.class, String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(math, op1, op2);
        assertEquals(-1, result);
    }
    
    @Test
    public void testCmpOper2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试符号优先级比较方法 op1 < op2
        String op1 = "*";
        String op2 = "+";
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("cmpOper", String.class, String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(math, op1, op2);
        assertEquals(1, result);
    }
    
    @Test
    public void testCmpOper3() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 测试符号优先级比较方法 op1 = op2
        String op1 = "*";
        String op2 = "*";
        MathExam math = new MathExam();
        Method method = math.getClass().getDeclaredMethod("cmpOper", String.class, String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(math, op1, op2);
        assertEquals(0, result);
    }
}
