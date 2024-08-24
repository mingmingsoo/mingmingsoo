
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PriorityQueue<absInteger> pq = new PriorityQueue<>();

		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			int order = sc.nextInt();
			if (order != 0) {
				pq.add(new absInteger(order));
			}

			else {
				if (!pq.isEmpty()) {
					System.out.println(pq.poll().x);
				} else {
					System.out.println(0);
				}
			}

		}

	}

	public static class absInteger implements Comparable<absInteger> {
		int x;

		absInteger(int x) {
			this.x = x;
		}

		@Override
		public int compareTo(absInteger o) {

			if (Math.abs(this.x) == Math.abs(o.x)) {
				return this.x - o.x;
			}

			return Math.abs(this.x) - Math.abs(o.x);
		}

	}

}
