
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt =1;
		while(tt<=t) {
			a = new int[9];
			b = new int[9];
			visited = new boolean[9];
			boolean[] bool = new boolean[19];
			for (int i = 0; i < 9; i++) {
				a[i] = sc.nextInt();
				bool[a[i]] = true;
			}
			int bidx = 0;
			for (int i = 1; i < 19; i++) {
				if (!bool[i]) {
					b[bidx++] = i;
				}
			}
			wincount = 0;
			losecount = 0;
			game(0, 0, 0); // idx awin bwin
			System.out.println("#"+tt+" "+wincount + " " + losecount);
			tt++;
		}
	}

	static int wincount;
	static int losecount;
	static boolean[] visited;
	static int[] a;
	static int[] b;

	private static void game(int idx, int atotal, int btotal) {
		if (idx == 9) {
			if (atotal > btotal) {
				wincount++;
			} else if (atotal < btotal) {
				losecount++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if(!visited[i]) {
				if (a[idx] > b[i]) {
					visited[i] = true;
					game(idx + 1, atotal + a[idx] + b[i], btotal);
					visited[i] = false;
				} else if (a[idx] < b[i]) {
					visited[i] = true;
					game(idx + 1, atotal, btotal + a[idx] + b[i]);
					visited[i] = false;
				}
			}
		}

	}

}
