

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		long b = System.currentTimeMillis();

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {

			personlist = new ArrayList<>();
			stairs = new Stair[2];
			n = sc.nextInt();
			grid = new int[n][n];
			int idx = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
					if (grid[i][j] == 1) {
						personlist.add(new Person(i, j));
					}
					if (grid[i][j] > 1) {
						stairs[idx++] = new Stair(i, j, grid[i][j]);
					}
				}
			}
//			System.out.println(personlist);
//			System.out.println(Arrays.toString(stairs));

			ans = Integer.MAX_VALUE;
			subset(0);
			System.out.println("#" + tt + " " + ans);
			tt++;

		}

		long a = System.currentTimeMillis();
		long Time = (a - b);
//		System.out.println("시간차이(m) : " + Time);

	}

	static int n;
	static int[][] grid;
	static int perIdx;
	static int ans;
	static List<Person> personlist;
	static Stair[] stairs;
	static int cnt;

	private static void subset(int idx) {
		if (cnt > Math.pow(2, personlist.size() - 1)) {
			return;
		}
		if (idx == personlist.size()) { // 6명
			int totaltime = 0;
			// 각 계단마다 사람 이동시간 구할거임.
			for (int i = 0; i < 2; i++) { // 0번 계단 -> 1번 계단 순으로.

				// 계단이 가까운 순으로 넣을 거임.
				PriorityQueue<Person> pq = new PriorityQueue<>();
				for (int j = 0; j < personlist.size(); j++) {
					if (personlist.get(j).stair == i) {
						pq.add(personlist.get(j));
					}
				}
//				System.out.println(personlist);

				// 가까운 순서부터 계단 내려가는 시간 계산.
				int[] timearr = new int[100]; // 매 분마다 사람 수 저장.
				while (!pq.isEmpty()) {
					Person front = pq.poll();
					int start = front.moveTime; // 계단까지 이동 거리
					int end = start + stairs[i].len; // 계단 다 내려가는 시간.
					for (int j = start; j < end; j++) {
						if (timearr[j] == 3) {
							end++;
							continue;
						} else {
							timearr[j]++;
						}
					}
					totaltime = Math.max(totaltime, end); // 최종 시간은 end의 마지막 값. 0과 1의 end값 중 큰 게 최종 시간임.

				}

			}
			ans = Math.min(totaltime, ans);
			return;
		}

		// 0번 계단 선택
		personlist.get(idx).stair = 0; // 0 번 계단 선택. // sel[idx] = 0 // 과 동일/.
		personlist.get(idx).moveTime = 1 + Math.abs(personlist.get(idx).x - stairs[0].x)
				+ Math.abs(personlist.get(idx).y - stairs[0].y);
		subset(idx + 1);

		// 1번 계단 선택
		personlist.get(idx).stair = 1;
		personlist.get(idx).moveTime = 1 + Math.abs(personlist.get(idx).x - stairs[1].x)
				+ Math.abs(personlist.get(idx).y - stairs[1].y);
		subset(idx + 1);
	}

	static class Person implements Comparable<Person> {
		int x;
		int y;
		int moveTime; // 계단과의 거리
		int stair; // 선택한 계단

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}

		@Override
		public String toString() {
			return "Person [x=" + x + ", y=" + y + ", moveTime=" + moveTime + ", stair=" + stair + "]";
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.moveTime, o.moveTime);
		}
	}

	static class Stair {
		int x;
		int y;
		int len;

		public Stair(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Stair [x=" + x + ", y=" + y + ", len=" + len + "]";
		}

	}
}
