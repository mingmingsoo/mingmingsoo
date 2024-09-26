
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
// 다익스트라 시작점 설정 문제
public class Main {
	static class Node { // 단방향이라 목적지와 가중치만 있음
		int V;
		int W;

		public Node(int v, int w) {
			V = v;
			W = w;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		X = sc.nextInt();

		adj = new ArrayList[V + 1];

		for (int i = 0; i < V + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE); // 최소 거리를 계산해서 넣어줄거임
		// 그래서 큰 값 넣어주기
		for (int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			adj[A].add(new Node(B, W));
		}

		dij(X); // 집 갈때 얼마나 걸리는지 dist 배열에 담겨있음
		int[] gohome = dist.clone(); // 일단 복사해줌

		// 파티갈 때도 계산해줘야함
		for (int i = 1; i < V + 1; i++) {
			if (i != X) { // 파티장소 제외하고 계산해주기!
				Arrays.fill(dist, Integer.MAX_VALUE); // dist 초기화
				dij(i); // 목적지까지의 거리 정보가 dist에 담기는데
				gohome[i] += dist[X]; // 우리집 정보에 맞게 gohome에 매칭해주기
			}
		}

//		System.out.println(Arrays.toString(gohome));
		int max = 0;
		for (int i = 1; i < V + 1; i++) {
			if (max < gohome[i]) {
				max = gohome[i];
			}
		}
		System.out.println(max); // 가장 먼 곳 출력
	}
	
	static int V;
	static int E;
	static int X;
	static List<Node>[] adj; // 인접리스트
	static int[] dist;

	private static void dij(int start) {
		boolean[] visited = new boolean[V + 1];

		dist[start] = 0; // 시작지점 길이 0으로 초기화

		for (int i = 1; i < V + 1; i++) {
			int min = Integer.MAX_VALUE;
			int idx = -1;

			for (int j = 1; j < V + 1; j++) {
				if (!visited[j] && min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			}

			if (idx == -1) { // 연결이 안되어있으면 return
				return;
			}

			visited[idx] = true;

			for (Node node : adj[idx]) {
				if (!visited[node.V] && dist[node.V] > dist[idx] + node.W) {
					dist[node.V] = dist[idx] + node.W; // 최소 거리로 갱신
				}
			}
		}

	}

}
