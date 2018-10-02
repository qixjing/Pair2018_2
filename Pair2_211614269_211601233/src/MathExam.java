
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
		// symΪ1��Ϊ��,2��Ϊ��
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
		// symΪ1��Ϊ��,2��Ϊ��
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

	// �ж���������Ƿ�Ϸ�
	public static boolean checkInput(String args[]) {
		if (args.length != 4) {
			return false;
		} else {
			if (args[0].equals("-n") && args[2].equals("-grade") || args[0].equals("-grade") && args[2].equals("-n")) {
				if (args[0].equals("-n")) {
					// �޶�����Ϊ0-999
					if (isNum(args[1]) && args[1].length() <= 3 && isNum(args[3]) && args[3].length() == 1
							&& Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) <= 3) {
						return true;
					} else {
						return false;
					}
				} else {
					if (isNum(args[3]) && args[3].length() <= 3 && isNum(args[1]) && args[1].length() == 1
							&& Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) <= 3) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
	}

	// �������������ȡ�������꼶
	public static int[] inform(String args[]) {
		// a[0]����꼶 a[1]�������
		int[] a = new int[2];
		if (args[0].equals("-n")) {
			a[0] = Integer.parseInt(args[3]);
			a[1] = Integer.parseInt(args[1]);
		} else {
			a[0] = Integer.parseInt(args[1]);
			a[1] = Integer.parseInt(args[3]);
		}
		return a;
	}

	public static void mathOne(int n) {
		// ����ŵ���Ŀ
		String prob;
		// ������ŵ���Ŀ
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			do {
				end = checkOne();
			} while (!end);
			// symΪ1��Ϊ��,2��Ϊ��
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

		write(probs, anss);
	}

	public static void mathTwo(int n) {
		// ����ŵ���Ŀ
		String prob;
		// ������ŵ���Ŀ
		String prob1;
		String ans;
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		boolean end = false;
		for (int i = 1; i <= n; i++) {
			// ����
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
		write(probs, anss);
	}

	public static void mathThr(int n) {
		String sym;

		// ����ŵ���Ŀ
		String prob;
		// ������ŵ���Ŀ
		String prob1;
		String ans;
		// ������ŵ������Ŀ,�������,������
		ArrayList<String> pro = new ArrayList<String>();
		// �������ȫ����Ŀ
		ArrayList<String> probs = new ArrayList<String>();
		ArrayList<String> anss = new ArrayList<String>();
		for (int j = 0; j < n; j++) {
			// ����������ŵĸ���2-4��
			int p = (int) (2 + Math.random() * (4 - 2 + 1));
			prob = "(" + (j + 1) + ")";
			prob1 = "";
			// ��ֵλ���ӷ���λ��Ӧ����(2*����λ��+1)
			for (int i = 0; i < (2 * p) + 1; i++) {
				// �����ж���ֵλӦ�ó��ֵ�λ��
				if (i % 2 == 0) {
					int number = (int) (0 + Math.random() * (99 - 0 + 1));
					pro.add(String.valueOf(number));
				} // ��ʱӦ������
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
						// �ҵ�������
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

			// ������ֵ���Ŀת�ɲ�����ŵ��ַ���
			for (String str : pro) {
				prob1 += str;
			}
			// ������ֵ���Ŀת�ɴ���ŵ��ַ���
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

		write(probs, anss);
	}

	public static void write(ArrayList<String> probs, ArrayList<String> anss) {
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
				System.out.println("�ļ�����ʧ��,������");
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
					System.out.println("д�����!");
				}
			}
			String fg = "\r\n";
			data = fg.getBytes();
			try {
				out.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("д�����!");
			}
			for (String str : anss) {
				str += "\r\n";
				data = str.getBytes();
				try {
					out.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("д�����!");
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("io����");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�Ҳ����ļ�,������");
		}
	}

	public static void main(String[] args) {
		if (checkInput(args)) {
			int[] a = inform(args);
			if (a[0] == 1) {
				mathOne(a[1]);
			} else if (a[0] == 2) {
				mathTwo(a[1]);
			} else {
				mathThr(a[1]);
			}
		} else {
			System.out.println("��������,����������");
		}

	}
}
