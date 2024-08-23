
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		Stack<Integer> stack = new Stack<>();

		List<Integer> tmp = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			tmp.add(sc.nextInt());
		}

		for (int i = tmp.size() - 1; i >= 0; i--) {
			stack.push(tmp.get(i));
		}

//		System.out.println(stack);
//		System.out.println(stack.peek());

		Stack<Integer> wait = new Stack<>();
		String ans = "Sad";
		int cnt = 1;
		while (!stack.isEmpty()) {
			if (stack.peek() != cnt) {
				wait.add(stack.pop());
			} else if (stack.peek() == cnt) {
				stack.pop();
				cnt++;
			}

			while (!wait.isEmpty() && wait.peek() == cnt) {
				wait.pop();
				cnt++;
			}

		}
		while (!wait.isEmpty()) {
			if (!wait.isEmpty() && wait.peek() == cnt) {
				wait.pop();
				cnt++;
			}
			else {
				break;
			}
		}

		if (wait.isEmpty()) {
			ans = "Nice";
		}

		System.out.println(ans);
	}

}
