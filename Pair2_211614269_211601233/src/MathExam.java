
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MathExam {
	static int a;
	static int b;
	static int sym;

	public static boolean checkOne() {
		a = (int) (0 + Math.random() * (100 - 0 + 1));
		b = (int) (0 + Math.random() * (100 - 0 + 1));
		// sym为1即为正,2即为负
		sym = (int) (1 + Math.random() * (2 - 1 + 1));
		if (sym == 1) {
			if ((a + b) > 100) {
				return false;
			} else {
				return true;
			}
		} else {
			if ((a - b) < 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static boolean checkTwo() {
		// sym为1即为乘,2即为除
		sym = (int) (1 + Math.random() * (2 - 1 + 1));
		if (sym == 1) {
			a = (int) (0 + Math.random() * (9 - 0 + 1));
			b = (int) (0 + Math.random() * (9 - 0 + 1));
			return true;
		} else {
			a = (int) (1 + Math.random() * (99 - 1 + 1));
			b = (int) (1 + Math.random() * (9 - 1 + 1));
			if (a < b) {
				return false;
			} else {
				if (a / b >= 10) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void mathOne(int n) {
		// 带题号的题目
		String prob;
		// 不带题号的题目
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			do {
				end = checkOne();
			} while (!end);
			// sym为1即为正,2即为负
			if (sym == 1) {
				prob1 = a + " " + "+" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "+" + " " + b;

				ans = prob + " " + "=" + " " + Calculate.ans(prob1)[1];
			} else {
				prob1 = a + " " + "-" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "-" + " " + b;
				ans = prob + " " + "=" + " " + Calculate.ans(prob1)[1];
			}
			probs.add(prob);
			anss.add(ans);

		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建失败,请重试");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			String fg = "-----------------标准答案-----------------" + "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("写入错误!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io错误");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到文件,请重试");
		}
	}

	public static void mathTwo(int n) {
		// 带题号的题目
		String prob;
		// 不带题号的题目
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			// 符号
			do {
				end = checkTwo();
			} while (!end);
			if (sym == 1) {
				prob1 = a + " " + "*" + " " + b;
				prob = "(" + i + ")" + " " + a + " " + "*" + " " + b;
				ans = prob + " " + "=" + " " + Calculate.ans(prob1)[1];
			} else {
				prob = "(" + i + ")" + " " + a + " " + "/" + " " + b;
				if (a % b != 0) {
					ans = "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + (a / b) + "..." + (a % b);
				} else {
					prob1 = a + " " + "/" + " " + b;
					ans = prob + " " + "=" + " " + Calculate.ans(prob1)[1];
				}
			}
			probs.add(prob);
			anss.add(ans);
		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建失败,请重试");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			String fg = "-----------------标准答案-----------------" + "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("写入错误!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io错误");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到文件,请重试");
		}
	}

	public static void mathThr(int n) {
		String sym;

		// 带题号的题目
		String prob;
		// 不带题号的题目
		String prob1;
		String ans;
		// 用来存放单题的题目,拆分数字,符号用
		ArrayList<String> pro = new ArrayList<String>();
		// 用来存放全部题目
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		for (int j = 0; j < n; j++) {
			// 用来随机符号的个数2-4个
			int p = (int) (2 + Math.random() * (4 - 2 + 1));
			prob = "(" + (j + 1) + ")";
			prob1 = "";
			// 数值位数加符号位数应该是(2*符号位数+1)
			for (int i = 0; i < (2 * p) + 1; i++) {
				// 用来判断数值位应该出现的位置
				if (i % 2 == 0) {
					int number = (int) (0 + Math.random() * (99 - 0 + 1));
					pro.add(String.valueOf(number));
				} // 此时应出符号
				else {
					int x = (int) (1 + Math.random() * (4 - 1 + 1));
					if (x == 1) {
						sym = "+";
						pro.add(sym);
					} else if (x == 2) {
						sym = "-";
						pro.add(sym);
					} else if (x == 3) {
						if (pro.size() == 3
								&& (pro.get(pro.size() - 2).equals("+") || pro.get(pro.size() - 2).equals("-"))) {
							pro.add(pro.size() - 3, "(");
							pro.add(")");
						}
						sym = "*";
						pro.add(sym);
					} else {
						if (pro.size() == 3
								&& (pro.get(pro.size() - 2).equals("+") || pro.get(pro.size() - 2).equals("-"))) {
							pro.add(pro.size() - 3, "(");
							pro.add(")");
						}
						sym = "/";
						// 找到被除数
						if (pro.get(pro.size() - 1).equals(")")) {
							int bcs;
							int a = Integer.parseInt(pro.get(pro.size() - 4));
							int b = Integer.parseInt(pro.get(pro.size() - 2));
							if (pro.get(pro.size() - 3).equals("+")) {
								bcs = a + b;
							} else {
								bcs = a - b;
							}
							if (bcs <= 0) {
								pro.set(pro.size() - 4, String.valueOf(a + (-bcs + 1)));
								bcs += (-bcs + 1);
							}
							pro.add(sym);
							int cs = (int) (1 + Math.random() * (99 - 1 + 1));
							while (bcs % cs != 0) {
								cs = (int) (1 + Math.random() * (99 - 1 + 1));
							}
							pro.add(String.valueOf(cs));
							i++;

						} else {
							int bcs = Integer.parseInt(pro.get(pro.size() - 1));
							if (bcs <= 0) {
								pro.set(pro.size() - 1, String.valueOf(bcs + (-bcs + 1)));
								bcs += (-bcs + 1);
							}
							pro.add(sym);

							int cs = (int) (1 + Math.random() * (99 - 1 + 1));
							while (bcs % cs != 0) {
								cs = (int) (1 + Math.random() * (99 - 1 + 1));
							}

							pro.add(String.valueOf(cs));
							i++;
						}

					}
				}
			}
			/*
			 * // 随机是否出括号 int kh = (int) (1 + Math.random() * (2 - 1 + 1)); if (kh == 1) {
			 * for (int i = 1; i < pro.size() - 1; i += 2) { if ((pro.get(i).equals("+") ||
			 * pro.get(i).equals("-")) && i + 2 < (pro.size() - 1) && (pro.get(i +
			 * 2).equals("*") || pro.get(i + 2).equals("/"))) { pro.add(i - 1, "(");
			 * pro.add(i + 3, ")"); break;
			 * 
			 * for(int k=pro.size()-3;k>=i-1;k--) { pro.set(k+1, pro.get(k)); } pro.set(i-1,
			 * "(");
			 * 
			 * // 添加右括号
			 * 
			 * str1=pro.get(pro.size()-1); pro.add(str1); for(int k=pro.size()-3;k>=i+3;k--)
			 * { pro.set(k+1, pro.get(k)); } pro.set(i+3, ")");
			 * 
			 * } } }
			 */
			// 将被拆分的题目转成不带题号的字符串
			for (String str : pro) {
				prob1 += str;
			}
			// 将被拆分的题目转成带题号的字符串
			for (String str : pro) {
				prob += " ";
				prob += str;
			}
			pro.clear();
			int a[] = Calculate.ans(prob1);
			if (a[0] == 1) {
				ans = prob + " " + "=" + " " + Calculate.ans(prob1)[1];
				probs.add(prob);
				anss.add(ans);
			} else {
				j--;
			}

		}

		File file = new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建失败,请重试");
			}
		}
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] data;
			for (String str : probs) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			String fg = "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("写入错误!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("写入错误!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io错误");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到文件,请重试");
		}
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("输入错误");
		} else {
			if (args[0].equals("-n") && args[2].equals("-grade") || args[0].equals("-grade") && args[2].equals("-n")) {
				if (args[0].equals("-n")) {
					// 限定题数为0-999
					if (isNum(args[1]) && args[1].length() <= 3 && isNum(args[3]) && args[3].length() == 1
							&& Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) <= 3) {
						if (args[3].equals("1")) {
							mathOne(Integer.parseInt(args[1]));
						} else if (args[3].equals("2")) {
							mathTwo(Integer.parseInt(args[1]));
						} else {
							mathThr(Integer.parseInt(args[1]));
						}
					} else {
						System.out.println("输入错误");
					}
				} else {
					if (isNum(args[3]) && args[3].length() <= 3 && isNum(args[1]) && args[1].length() == 1
							&& Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) <= 3) {
						if (args[1].equals("1")) {
							mathOne(Integer.parseInt(args[3]));
						} else if (args[1].equals("2")) {
							mathTwo(Integer.parseInt(args[3]));
						} else {
							mathThr(Integer.parseInt(args[3]));
						}
					} else {
						System.out.println("输入错误");
					}
				}
			} else {
				System.out.println("输入错误");
			}
		}

	}
}
