

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Deque<Integer> dq = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 명령어 수
		for (int i = 0; i < N; i++) {
			String order = br.readLine();
			if (order.contains("push_front")) {
				String[] str = order.split(" ");
				String o = str[0];
				int n  = Integer.parseInt(str[1]);
				dq.addFirst(n);
			} else if (order.contains("push_back")) {
				String[] str = order.split(" ");
				String o = str[0];
				int n  = Integer.parseInt(str[1]);
				dq.addLast(n);

			} else if (order.equals("pop_front")) {
				if (!dq.isEmpty()) {
					sb.append(dq.pollFirst()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}

			} else if (order.equals("pop_back")) {
				if (!dq.isEmpty()) {
					sb.append(dq.pollLast()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}

			} else if (order.equals("size")) {
				sb.append(dq.size()).append("\n");
			} else if (order.equals("empty")) {
				if (dq.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if (order.equals("front")) {
				if (!dq.isEmpty()) {
					sb.append(dq.peekFirst()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}

			} else if (order.equals("back")) {
				if (!dq.isEmpty()) {
					sb.append(dq.peekLast()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}
			}
		}
		System.out.println(sb);

	}
}
