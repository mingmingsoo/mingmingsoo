

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		LinkedList<Integer> list = new LinkedList<>();

		int n = sc.nextInt();
		int k = sc.nextInt();

		for (int i = 1; i < n + 1; i++) {
			list.add(i);
		}
//		System.out.println(list);
		int cnt = 0;
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (cnt < n) {
			idx = (idx + (k - 1)) % list.size();

			sb.append(list.get(idx));
			if (cnt < n - 1) {
				sb.append(", ");
			}

			list.remove(idx);
			cnt++;
		}
		sb.append(">");
		System.out.println(sb);

	}

}
