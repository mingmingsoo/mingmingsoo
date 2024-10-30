
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		// 8
		int num = 1;
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while (tt <= t) {
//			System.out.println(stack);
			int ele = sc.nextInt();

			if (stack.isEmpty() && ele < num) {
				System.out.println("NO");
				return;
			}

			if (ele < num && ele > stack.peek()) {
				System.out.println("NO");
				return;
			}

			while (ele >= num) {
				stack.add(num++);
				sb.append("+\n");
			}

			while (!stack.isEmpty() && ele <= stack.peek()) {
				stack.pop();
				sb.append("-\n");
			}

			tt++;
		}
		System.out.println(sb);

	}

}
//
//5
//1
//5
//3
//4
//2

// 1 2 5 55555555555555555
