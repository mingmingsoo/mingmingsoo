
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt(); // 변의 길이
			limit = sc.nextInt(); // 격리 시간
			k = sc.nextInt(); // 미생물 군집 수

			rarr = new int[k]; // x
			carr = new int[k]; // y
			varr = new int[k]; // virus
			darr = new int[k];

			for (int i = 0; i < k; i++) {
				rarr[i] = sc.nextInt();
				carr[i] = sc.nextInt();
				varr[i] = sc.nextInt();
				darr[i] = sc.nextInt();
			}
			int vsum = 0;
			move(); // time
			for (int i = 0; i < k; i++) {
				vsum += varr[i];
			}
			System.out.println("#" + tt + " " + vsum);
			tt++;

		}

	}

	static int n;
	static int limit;
	static int k;

	static int[] rarr;
	static int[] carr;
	static int[] varr;
	static int[] darr;
	static Queue<int[]> q;

	private static void move() {

		int time = 0;

		q = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			q.add(new int[] { rarr[i], carr[i], varr[i], darr[i] });
		}

		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();

				int[] node1 = move(node);
				node = node1;

				int[] node2 = redzone(node);
				node = node2;

				rarr[i] = node[0];
				carr[i] = node[1];
				varr[i] = node[2];
				darr[i] = node[3];
			}
			merge();
			if (time == limit) {
				return;
			}
		}

	}

	private static void merge() {

		for (int i = 0; i < k; i++) {
			int[] tmp = new int[k];
			tmp[i]++;
			int virustotal = varr[i];
			int maxvirus = varr[i];
			int maxidx = i;

			for (int j = i + 1; j < k; j++) {
				if (rarr[i] == rarr[j] && carr[i] == carr[j]) {
					tmp[j]++;
					virustotal += varr[j];
					if (maxvirus < varr[j]) {
						maxvirus = varr[j];
						maxidx = j;
					}
				}
			}

			varr[maxidx] = virustotal;
			for (int j = 0; j < k; j++) {
				if (tmp[j] == 1 && j != maxidx) {
					varr[j] = 0;
				}
			}

		}

		for (int i = 0; i < k; i++) {
			q.add(new int[] { rarr[i], carr[i], varr[i], darr[i] });
		}

	}

	private static int[] redzone(int[] node) {
		int r = node[0];
		int c = node[1];
		int v = node[2];
		int d = node[3];

		if (r == 0 || r == n - 1 || c == 0 || c == n - 1) {
			v /= 2;
			if (d == 1) {
				d = 2;
			} else if (d == 2) {
				d = 1;
			} else if (d == 3) {
				d = 4;
			} else {
				d = 3;
			}
		}

		return new int[] { r, c, v, d };
	}

	private static int[] move(int[] node) {
		int r = node[0];
		int c = node[1];
		if (node[3] == 1) {
			r--;
		} else if (node[3] == 2) {
			r++;
		} else if (node[3] == 3) {
			c--;
		} else {
			c++;
		}

		return new int[] { r, c, node[2], node[3] };
	}

}
