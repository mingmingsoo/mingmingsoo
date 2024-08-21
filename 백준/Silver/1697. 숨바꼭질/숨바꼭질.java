
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int ans = bfs(n, m);
		System.out.println(ans);

	}

	static boolean[] visited = new boolean[100000 + 1];

	private static int bfs(int n, int m) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();

		q.offer(n);

		while (!q.isEmpty()) {

			int size = q.size(); // 1초씩 가야하므로 ( 높이 순대로)
			for (int i = 0; i < size; i++) {
				int node = q.poll();
				int minus1 = node - 1;
				int plus1 = node + 1;
				int mul2 = node * 2;

				if (minus1 >= 0 && visited[minus1] == false) {
					q.offer(minus1);
					visited[minus1] = true;
				}
				if (plus1 <= 100000 && visited[plus1] == false) {
					q.offer(plus1);
					visited[plus1] = true;
				}
				if (mul2 <= 100000 && visited[mul2] == false) {
					q.offer(mul2);
					visited[mul2] = true;
				}
				if (node == m) {
					return cnt;
				}

			}
			cnt++;

		}
		return 0;

	}

}
