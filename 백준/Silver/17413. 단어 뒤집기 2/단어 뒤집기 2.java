import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '<') {
				while (s.charAt(i) != '>') {
					System.out.print(s.charAt(i++));
				}
				if (s.charAt(i) == '>')
					System.out.print(s.charAt(i));
			}

			else {
				while (i < s.length() && s.charAt(i) != ' ' && s.charAt(i) != '<') {
					stack.add(s.charAt(i));
					i++;
				}

				while (!stack.isEmpty()) {
					System.out.print(stack.pop());
				}
				if (i < s.length() && s.charAt(i) == ' ')
					System.out.print(s.charAt(i));
				else {
					i -= 1;
				}
			}

		}
	}

}
