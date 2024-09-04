

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int A;
		int B;
		double C;

		public Edge(int a, int b, double c) {
			super();
			A = a;
			B = b;
			C = c;
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", C=" + C + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.C, o.C);
		}

	}

	static int[] parents;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int n = sc.nextInt();
			parents = new int[n];

			int[] X = new int[n];
			int[] Y = new int[n];

			for (int i = 0; i < n; i++) {
				X[i] = sc.nextInt();
			}
			for (int i = 0; i < n; i++) {
				Y[i] = sc.nextInt();
			}
//		System.out.println(Arrays.toString(X));
//		System.out.println(Arrays.toString(Y));

			double E = sc.nextDouble();

			int Edgenums = (n * (n - 1)) / 2;
//		System.out.println(Edgenums);

			Edge[] edgelist = new Edge[Edgenums];

			int idx = 0;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					int from = i; // 0
					int to = j; // 1
					double W = (E * (Math.pow((X[from] - X[to]), 2) + Math.pow((Y[from] - Y[to]), 2)));
//				System.out.println(from + " " + to + " " + W);

					edgelist[idx++] = new Edge(from, to, W);
				}
			}

			Arrays.sort(edgelist);
//		System.out.println(Arrays.toString(edgelist));

			// make set
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}

			int cnt = 0;
			double sum = 0;
			for (int i = 0; i < Edgenums; i++) {
				int ap = findSet(edgelist[i].A);
				int bp = findSet(edgelist[i].B);

				if (ap != bp) {
					union(ap, bp);
					sum += edgelist[i].C;
					cnt++;
				}

				if (cnt == n - 1) {
					break;
				}
			}
			System.out.println("#" + tt + " " + Math.round(sum));

			tt++;
		}

	}

	private static void union(int ap, int bp) {
		int aparent = findSet(ap);
		int bparent = findSet(bp);
		if (ap != bp) {
			parents[bparent] = aparent;
		}

	}

	private static int findSet(int x) {
		if (x != parents[x]) {
			parents[x] = findSet(parents[x]);
		}
		return parents[x];
	}

}
