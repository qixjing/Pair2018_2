
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class MathExam {
    
    final static int GRADE1_MAX = 20;
    final static int GRADE2_MAX = 90;
    final static int GRADE3_MAX = 10000;
    final static String FILENAME = "out.txt";
    static int num;
    static int grade;
    static String errMessage;
    static String[] operator = {"+", "-", "*", "/"};// 三年级符号
    static List<String> strArrayQ, strArrayA;// 存放题目和答案用于输出到文件
    
    
    public static void main(String[] args) throws IOException {
        if (checkInput(args)) {
            switch (grade) {
            case 1:
                grade1(num);
                break;
            case 2:
                grade2(num);
                break;
            case 3:
                grade3(num);
                break;
            default:
                break;
            }
            createTxt();
        } else {
            System.out.println(errMessage);
        }
    }
    
    /* 检查命令行输入 */
    private static boolean checkInput(String[] args) {
        /*
         * 1.检查参数个数，要求为4个
         * 2.检查第1，3个参数是否为"-n"或者"-grade"
         * 3.对应其参数检查其输入合理性，-n要求为正整数且不能太大，-grade要求为1、2、3
         */
        if (args.length == 4) {
            if (args[0].equals("-n")) {
                if (args[2].equals("-grade")) {
                    // 参数顺序-n -grade
                    try {
                        num = Integer.parseInt(args[1]);
                        if (num < 0) {
                            errMessage = "题目数量为负！请重新运行！";
                            return false;
                        } else if (num == 0 || num > 50) {
                            errMessage = "请输入合适的题目数量！比如1-50";
                            return false;
                        } else {
                            try {
                                grade = Integer.parseInt(args[3]);
                                if (grade == 1 || grade == 2 || grade == 3) {
                                    return true;
                                } else {
                                    errMessage = "年级选择超出范围，请选择一二三年级。";
                                    return false;
                                }
                            } catch (NumberFormatException e) {
                                errMessage = "年级选择选项非正整数！请重新运行！";
                                return false;
                            }
                        }
                    } catch (NumberFormatException e) {
                        errMessage = "题目数量选项非整数！请重新运行！";
                        return false;
                    }
                }
            } else if (args[0].equals("-grade")) {
                if (args[2].equals("-n")) {
                    // 参数顺序-grade -n
                    try {
                        grade = Integer.parseInt(args[1]);
                        if (grade == 1 || grade == 2 || grade == 3) {
                            try {
                                num = Integer.parseInt(args[3]);
                                if (num < 0) {
                                    errMessage = "题目数量为负！请重新运行！";
                                    return false;
                                } else if (num == 0 || num > 50) {
                                    errMessage = "请输入合适的题目数量！比如1-50";
                                    return false;
                                } else {
                                    return true;
                                }
                            } catch (NumberFormatException e) {
                                errMessage = "题目数量选项非整数！请重新运行！";
                                return false;
                            }
                        } else {
                            errMessage = "年级选择超出范围，请选择一二三年级。";
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        errMessage = "年级选择选项非正整数！请重新运行！";
                        return false;
                    }
                }
            }
        } else {
            errMessage = "参数个数有误！请重新运行！";
        }
        return false;
    }
    
    /* 将中缀表达式转换成后缀表达式 */
    private static List<String> nibolan(List<String> shizi) {
        /*
         * 1.遍历中缀表达式
         * 2.若为操作数，则存入操作数栈
         * 3.若为左括号"("则直接存入运算符栈
         *   若为右括号则输出运算符栈中的运算符到操作数栈，直到遇到左括号为止
         *   若非括号运算符：
         *      1.若运算符栈顶的运算符为括号，则直接存入运算符栈
         *      2.若比运算符栈顶的运算符优先级高，则直接存入运算符栈
         *      3.若比运算符栈顶的运算符优先级低或者相等，则不断输出栈顶运算符到操作数栈，直到栈顶没有运算符的优先级大于或者等于当前运算符，最后将当前运算符压入运算符栈
         * 4.当表达式读取完成后运算符栈中尚有运算符时，则依序取出运算符到操作数栈，直到运算符栈为空
         */
        List<String> list = new ArrayList<String>();
        Stack stackOper = new Stack();// 符号栈
        Stack stackNum = new Stack();// 数字栈
        for (String s : shizi) {
            if (isNumber(s)) {
                stackNum.push(s);
                
            } else if (s.equals("(")) {
                stackOper.push(s);
            } else if (s.equals(")")){
                while (!stackOper.peek().equals("(")) {
                    stackNum.push(stackOper.pop());
                }
                stackOper.pop();
            } else {
                // 当前为+-*/
                if (stackOper.empty()) {
                    // 运算符栈为空
                    stackOper.push(s);
                } else {
                    if (stackOper.peek().equals("(")) {
                        // 运算符栈顶的运算符为括号
                        stackOper.push(s);
                    } else {
                        if (cmpOper(String.valueOf(stackOper.peek()), s) < 0) {
                            // 运算符栈顶优先级小于当前符号，压栈
                            stackOper.push(s);
                        } else {
                            stackNum.push(stackOper.pop());
                            if (stackOper.empty()) {
                                stackOper.push(s);
                            } else {
                                if (cmpOper(String.valueOf(stackOper.peek()), s) >= 0) {
                                    stackNum.push(stackOper.pop());
                                    stackOper.push(s);
                                } else {
                                    stackOper.push(s);
                                }
                            }
                        }
                    }
                }
            }
        }
        while (!stackOper.empty()) {
            stackNum.push(stackOper.pop());
        }
        while (!stackNum.empty()) {
            list.add(String.valueOf(stackNum.pop()));
        }
        Collections.reverse(list);
        return list;
    }
    
    /* 计算后缀表达式的值 */
    private static double calc(List<String> shizi) {
        /*
         * 1.遍历后缀表达式，若为操作数，则入栈，若为操作符，则从栈堆弹出两个操作数num1和num2。注意：后弹出的num2是第一操作数，num1是第二操作数
         * 2.完成计算并将结果压入栈堆
         * 3.依次操作直到遍历结束，栈堆剩下的最后一个元素即为结果
         */
        Stack<String> stack = new Stack<String>();
        double result = 0;// 运算结果
        double number1, number2;// 两个运算数
        String oper;// 运算符号
        for (String s : shizi) {
            if (isNumber(s)) {
                stack.push(s);
            } else {
                oper = String.valueOf(s);
                number1 = Double.parseDouble(stack.pop());
                number2 = Double.parseDouble(stack.pop());
                switch (oper) {
                case "+":
                    result = number2 + number1;
                    break;
                case "-":
                    result = number2 - number1;
                    break;
                case "*":
                    result = number2 * number1;
                    break;
                case "/":
                    result = number2 / number1;
                    break;
                default:
                    break;
                }
                stack.push(String.valueOf(result));
            }
        }
        return Double.parseDouble(stack.pop());
    }
    
    
    /* 生成一年级题目 */
    private static void grade1(int num) {
        /*
         * 1.随机生成运算符，加法或者减法
         * 2.随机生成第一个运算数，加法：第二个无要求；减法：第二个要求小于等于第一个
         * 3.判断题目是否重复
         * 4.存入题目数组并计算结果存入答案数组
         */
        int number1, number2 = 0;
        int fuhao = 0;
        int result = 0;
        // 检查重复
        List<String> check1 = new ArrayList<String>();
        List<String> check2 = new ArrayList<String>();
        String checkRepeat = null;
        
        strArrayQ = new ArrayList<String>();
        strArrayA = new ArrayList<String>();
        
        for (int i = 1; i <= num; i++) {
            fuhao = (int)(Math.random()*2);
            if (fuhao == 0) {
                do {
                    // 保证没有重复的式子生成
                    number1 = (int)(Math.random() * (GRADE1_MAX)+1);
                    number2 = (int)(Math.random() * (GRADE1_MAX)+1);
                    checkRepeat = number1 + "+" + number2;
                } while (check1.contains(checkRepeat) || check2.contains(checkRepeat));
                check1.add(number1 + "+" + number2);
                check2.add(number2 + "+" + number1);
                result = number1 + number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " + " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " + " + number2 + " = " + result);
            } else if (fuhao ==1) {
                do {
                    // 保证结果不为负数以及没有重复的式子生成
                    number1 = (int)(Math.random() * (GRADE1_MAX)+1);
                    number2 = (int)(Math.random() * (GRADE1_MAX)+1);
                    checkRepeat = number1 + "-" + number2;
                } while (number2 > number1 || check1.contains(checkRepeat));
                check1.add(number1 + "-" + number2);
                result = number1 - number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " - " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " - " + number2 + " = " + result);
            }
        }
    }
    
    
    /* 生成二年级题目 */
    private static void grade2(int num) {
        /*
         * 1.随机生成运算符，乘法或者除法
         * 2.随机生成第一个运算数，乘法：第二个无要求；除法：余数为0-9
         * 3.判断题目是否重复
         * 4.存入题目数组并计算结果存入答案数组
         */
        int number1, number2 = 0;
        int fuhao = 0;
        int result = 0;
        int yu = 0;
        // 检查重复
        List<String> check1 = new ArrayList<String>();
        List<String> check2 = new ArrayList<String>();
        String checkRepeat = null;
        
        strArrayQ = new ArrayList<String>();
        strArrayA = new ArrayList<String>();
        for (int i = 1; i <= num; i++) {
            fuhao = (int)(Math.random()*2);
            if (fuhao == 0) {
                do {
                    // 保证没有重复的式子生成
                    number1 = (int)(Math.random() * 10);
                    number2 = (int)(Math.random() * 10);
                    checkRepeat = number1 + "×" + number2;
                } while (check1.contains(checkRepeat) || check2.contains(checkRepeat));
                check1.add(number1 + "×" + number2);
                check2.add(number2 + "×" + number1);
                result = number1 * number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " × " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " × " + number2 + " = " + result);
            } else if (fuhao == 1) {
                do {
                    // 保证除数不为0以及没有重复的式子生成
                    number1 = (int)(Math.random() * GRADE2_MAX);
                    number2 = (int)(Math.random() * 10);
                    checkRepeat = number1 + "÷" + number2;
                } while (number2 <= (number1 / 10) || number2 == 0 || check1.contains(checkRepeat));
                check1.add(number1 + "÷" + number2);
                result = number1 / number2;
                yu = number1 % number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " ÷ " + number2 + " =");
                if (yu == 0) {
                    strArrayA.add("(" + i + ") " + number1 + " ÷ " + number2 + " = " + result);
                } else {
                    strArrayA.add("(" + i + ") " + number1 + " ÷ " + number2 + " = " + result + "..." + yu);
                }
            }
        }
    }
    
    /* 生成三年级题目 */
    private static void grade3(int num) {
        /*
         * 1.随机生成符号数量:2-4
         * 2.根据符号数量随机生成符号
         * 3.若生成括号则插入括号到合适的位置
         * 4.将中缀表达式转换成后缀表达式并计算结果
         * 5.保存到out.txt
         * 未完成：
         * 1.括号的数量可以为0到2对，只实现了生成一对括号并且括号中只包含一个运算符
         * 2.运算过程中的减法运算不能保证没有负数
         * 3.运算过程中的除法运算不能有余数
         */
        int number;
        String shizi;
        // 检查重复
        List<String> check1 = new ArrayList<String>();
        StringBuffer checkRepeat;
        Set<String> checkOper;
        
        strArrayQ = new ArrayList<String>();
        strArrayA = new ArrayList<String>();
        for (int j = 1; j <= num; j++) {
            shizi = "";
            List<String> exp;// 生成出来的中缀表达式
            checkRepeat = new StringBuffer();
            do {
                // 符号数量2-4
                int operNum = (int)(Math.random()*3) + 2;
                checkOper = new HashSet<String>();
                exp = new ArrayList<String>();
                number = (int)(Math.random() * 501);
                exp.add(String.valueOf(number));
                checkRepeat.append(String.valueOf(number));
                for (int i = 0; i < operNum; i++) {
                    String temp = operator[(int)(Math.random() * 4)];
                    checkOper.add(temp);
                    exp.add(temp);
                    checkRepeat.append(temp);
                    // 根据运算符合理生成数字
                    switch (temp) {
                    case "+":
                        number = (int)(Math.random() * 1001);
                        break;
                    case "-":
                        number = (int)(Math.random() * 1001);
                        break;
                    case "*":
                        number = (int)(Math.random() * 100);
                        break;
                    case "/":
                        number = (int)(Math.random() * 10);
                        break;
                    default:break;   
                    }
                    exp.add(String.valueOf(number));
                    checkRepeat.append(String.valueOf(number));
                }
                check1.add(checkRepeat.toString());
                int size = exp.size();
                boolean kuohao = Math.random() > 0.5;
                if (kuohao) {
                    // 生成括号
                    int index1 = (int)(Math.random() * (size - 1));
                    if (index1 % 2 == 0) {
                    } else {
                        index1 -= 1;
                    }
                    int index2 = index1 + 3;
                    exp.add(index1, "(");
                    exp.add(index2 + 1, ")");
                } 
            } while (calc(nibolan(exp)) < 0 || calc(nibolan(exp)) % 1 !=0 || calc(nibolan(exp)) > GRADE3_MAX || checkOper.size() < 2 || check1.contains(checkRepeat));// 最终结果不能小于0和小数
            for (String s : exp) {
                shizi += s;
                shizi += " ";
            }
            strArrayQ.add("(" + j + ") " + shizi.replace("*", "×").replace("/", "÷").trim());
            strArrayA.add("(" + j + ") " + shizi.replace("*", "×").replace("/", "÷") + "= " + (int) calc(nibolan(exp)));
        }
    }
    
    
    /* 输出到out.txt文件 */
    private static void createTxt() throws IOException {
        /*
         * 1.创建指定文件名的文件
         * 2.依次向文件写入内容
         */
        File file = new File(FILENAME);
        file.createNewFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            // 具体写入内容
            for (String s : strArrayQ) {
                fw.write(s);
                fw.write("\r\n");
            }
            fw.write("\r\n");
            for (String s : strArrayA) {
                fw.write(s);
                fw.write("\r\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /* 判断字符串是否为数字  */
    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /* 比较两个运算符的优先级 */
    private static int cmpOper(String op1, String op2) {
        if (op1.equals("+") || op1.equals("-")) {
            if (op2.equals("+") || op2.equals("-")) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (op2.equals("+") || op2.equals("-")) {
                return 1;
            } else {
                return 0;
            }
        } 
    }
    
}
