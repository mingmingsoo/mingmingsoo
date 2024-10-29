

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int orders = sc.nextInt();
		int oNum = 1;

		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		while (oNum <= orders) {
			String order = sc.next();
			if (order.equals("push")) {
				int ele = sc.nextInt();
				stack.add(ele);
			} else if (order.equals("pop")) {
				if (!stack.isEmpty()) {
					sb.append(stack.pop()+"\n");
				} else {
					sb.append(-1+"\n");
				}
			} else if (order.equals("size")) {
				sb.append(stack.size()+"\n");
			} else if (order.equals("empty")) {
				if (stack.isEmpty()) {
					sb.append(1+"\n");
				} else {
					sb.append(0+"\n");
				}
			} else if (order.equals("top")) {
				if (stack.isEmpty()) {
					sb.append(-1+"\n");
				} else {
					sb.append(stack.peek()+"\n");
				}
			}
			oNum++;
		}
		System.out.println(sb);

	}

}
