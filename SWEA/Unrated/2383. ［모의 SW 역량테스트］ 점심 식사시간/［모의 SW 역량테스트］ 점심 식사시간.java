

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int n = sc.nextInt();
			int[][] grid = new int[n][n];
			personlist = new ArrayList<>();
			stairlist = new Stair[2];
			int idx = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
					if (grid[i][j] == 1) {
						personlist.add(new Person(i, j));
					} else if (grid[i][j] > 1) {
						stairlist[idx++] = new Stair(i, j, grid[i][j]);
					}
				}
			}
//		System.out.println(personlist);
//		System.out.println(Arrays.toString(stairlist));

			ans = Integer.MAX_VALUE;
			subset(0);
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}

	static int ans;
	static List<Person> personlist;
	static Stair[] stairlist;

	private static void subset(int idx) {

		if (idx == personlist.size()) {
			int totaltime = 0;
			// 1. 각 계단마다, 이동시간을 구해줄거임!
			for (int i = 0; i < 2; i++) {
				PriorityQueue<Person> pq = new PriorityQueue<>();
				// 2-1. 거리가 짧은 애부터 q에 넣어주기.
				for (int j = 0; j < personlist.size(); j++) {
					if (personlist.get(j).stair == i) { // 0번 계단인 애들만!
						pq.add(personlist.get(j));
					}
				}
				// 2-2. 가까운 순서부터 계단 내려가는 시간 계산
				int[] timecount = new int[100];
				while (!pq.isEmpty()) {
					Person front = pq.poll();
					int start = front.movetime; // 계단까지 이동거리
					int end = start + stairlist[i].len; // 계단 다 내려가는 시간.
					for (int j = start; j < end; j++) {
						if (timecount[j] == 3) {
							end++;
							continue;
						} else {
							timecount[j]++;
						}
					}

					totaltime = Math.max(totaltime, end);
				}

			}
			ans = Math.min(ans, totaltime);
			return;
		}

		// 0번 계단일 때
		personlist.get(idx).stair = 0;
		personlist.get(idx).movetime = 1 + Math.abs(stairlist[0].x - personlist.get(idx).x)
				+ Math.abs(stairlist[0].y - personlist.get(idx).y);
		subset(idx + 1);

		// 1번 계단일 때
		personlist.get(idx).stair = 1;
		personlist.get(idx).movetime = 1 + Math.abs(stairlist[1].x - personlist.get(idx).x)
				+ Math.abs(stairlist[1].y - personlist.get(idx).y);
		subset(idx + 1);

	}

	static class Person implements Comparable<Person> {
		int x;
		int y;
		int movetime;
		int stair;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.movetime, o.movetime);
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
	}
}
