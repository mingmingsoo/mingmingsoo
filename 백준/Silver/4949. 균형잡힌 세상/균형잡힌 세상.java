
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		out: while (true) {
			String str = br.readLine();
			if (str.equals(".")) {
				break;
			}
			
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ('(') || str.charAt(i) == ('[')) {
					stack.add(str.charAt(i));
				} else if (str.charAt(i) == (')')) {
					if (stack.size() == 0 || stack.peek() != '(') {
						sb.append("no").append("\n");
						continue out;
					} else {
						stack.pop();
					}
				} else if (str.charAt(i) == (']')) {
					if (stack.size() == 0 || stack.peek() != '[') {
						sb.append("no").append("\n");
						continue out;
					} else {
						stack.pop();
					}
				}
			}
			if (stack.isEmpty()) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}

		}
		System.out.println(sb);

	}
}
