import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int range;
	static int up;
	static int down;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		range = sc.nextInt();
		int pos = sc.nextInt();
		int go = sc.nextInt();
		up = sc.nextInt();
		down = sc.nextInt();

		// 범위는 0부터 range까지 제한

		System.out.println(bfs(pos, go));

	}

	static boolean[] visited = new boolean[1000000 + 1];

	private static String bfs(int pos, int go) {
		
		String ans = "use the stairs";
		int cnt = 0;

		visited[pos] = true;

		Queue<Integer> q = new LinkedList<>();

		q.offer(pos);

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int node = q.poll();
				int up_pos = node + up;
				int down_pos = node - down;

				if (up_pos <= range && visited[up_pos] == false) {
					visited[up_pos] = true;
					q.offer(up_pos);
				}
				if (down_pos > 0 && visited[down_pos] == false) {
					visited[down_pos] = true;
					q.offer(down_pos);
				}
				if(node ==go) {
					ans =  String.valueOf(cnt);
				}
			}
			cnt++;
			
		}

		return ans;
	}

}
