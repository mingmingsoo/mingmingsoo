import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int cnt = sc.nextInt();
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < cnt; i++) {
				int n = sc.nextInt();
				int m = 0;
				if (n != 0) {
					m = sc.nextInt();
				}
				if (n == 1) {
					if (!stack.isEmpty())
						stack.push(m + stack.peek());
					else {
						stack.push(m);
					}
				} else if (n == 2) {
					if (!stack.isEmpty())
						if (stack.peek() - m >= 0) {
							stack.push(stack.peek() - m);
						}

				} else { // 0 일때
					if (!stack.isEmpty())
						stack.push(stack.peek());
					else {
						stack.push(m);
					}
				}
			}
			int sum = 0;
			while (!stack.isEmpty()) {
				sum += stack.pop();
			}
			System.out.println("#" + tt + " " + sum);
			tt++;
		}
	}
}