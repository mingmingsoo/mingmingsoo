
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		물고기 한마리씩 잡아 먹으면서
//		갈 수 있는 모든 곳을 pq에 넣음
//		pq에서 뽑아내는데, 조건에 해당하면 먹을 위치 뱉어줌

		n = sc.nextInt();
		grid = new int[n][n];

		int r = -1;
		int c = -1; // 상어 위치

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] == 9) {
					r = i;
					c = j;
					grid[i][j] = 0; // 걸리적 거리지 않게 위치만 저장하고 0 처리
				}
			}
		}

		shark = 2;
		int eat = 0; // 상어 크기만큼 먹으면 커질거임
		int time = 0;
		while (true) {
			int[] fish = find(r, c); // 이동할 위치(물고기냠)
			if (fish == null) { // 이동 못하면 끝!
				break;
			}

			r = fish[0];
			c = fish[1];
			time += fish[2];
			eat++;
			if (eat == shark) {
				shark++; // 상어 커지기
				eat = 0; 
			}
		}
		System.out.println(time);

	}

	static int n;
	static int shark;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int[][] grid;

	private static int[] find(int r, int c) {
		boolean[][] visited = new boolean[n][n];
		visited[r][c] = true;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(int[] o1, int[] o2) { // 뱉어낼 조건 설정
				if (o1[2] == o2[2]) {
					if (o1[0] == o2[0]) {
						return Integer.compare(o1[1], o2[1]); // 3 r도 같으면 c 작은 순
					}
					return Integer.compare(o1[0], o2[0]); // 2. 거리 같으면 r 작은 순
				}

				return Integer.compare(o1[2], o2[2]); // 1. 거리순
			}
		});
		pq.add(new int[] { r, c, 0 }); // 초기 상어 위치 넣어주기

		while (!pq.isEmpty()) {
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				int[] node = pq.poll();
				int x = node[0];
				int y = node[1];
				int d = node[2];
				if (grid[x][y] > 0 && grid[x][y] < shark) { // 뱉어낼 조건 해당하면 뱉어내기(알아서 우선순위에 맞게 뱉어낼거임)
					grid[x][y] = 0;
					return new int[] { x, y, d };
				}
				for (int k = 0; k < 4; k++) {
					int nr = x + row[k];
					int nc = y + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] <= shark) { // 이동할 수 있는 위치 다 pq에 넣음
						visited[nr][nc] = true; // pq에 다 들어가도 잡아먹을 수 있는 애를 뱉어낼거기 때문에 이동할 수 있는 곳 다 넣어도 상관 없음.
						pq.add(new int[] { nr, nc, d + 1 });
					}
				}
			}

		}

		return null;
	}
}
