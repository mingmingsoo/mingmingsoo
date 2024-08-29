
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt =1;
		while(tt<=t) {
			a = new int[card];
			b = new int[card];
			visited_b = new boolean[card];
			boolean acard[] = new boolean[card * 2 + 1];
			for (int i = 0; i < card; i++) {
				int tmp = sc.nextInt();
				a[i] = tmp;
				acard[tmp] = true;
			}
			int idx = 0;
			for (int i = 1; i < acard.length; i++) {
				if (!acard[i]) {
					b[idx++] = i;
				}
			}
			
//			System.out.println(Arrays.toString(a));
//			System.out.println(Arrays.toString(b));
			
			win = 0;
			lose = 0;
			game(0, 0, 0); // a가 몇번 카드 냈는지, a 합산 점수, b 합산 점수
			System.out.println("#"+tt+" "+win + " " + lose);
			tt++;
			
		}
				

	}

	static int[] a;
	static int[] b;
	static int card = 9;
	static boolean visited_b[];
	static int win;
	static int lose;

	private static void game(int idx, int atotal, int btotal) {
		// a는 고정이고 b는 유동적
		// a는 순서대로가므로 idx, b의 모든 경우의 수를 따져줘야함
		// idx는 재귀에 태우고 b는 포문으로

		// 기저조건
		if (idx == card) {
			if (atotal > btotal) {
				win++;
			} else if (atotal < btotal) {
				lose++;
			}

			return;
		}

		for (int j = 0; j < card; j++) {
			if (!visited_b[j]) {
				visited_b[j] = true;
				if (a[idx] > b[j]) {
					game(idx + 1, atotal + a[idx] + b[j], btotal);
				} else {
					game(idx + 1, atotal, btotal + a[idx] + b[j]);
				}
				visited_b[j] = false;
			}

		}

	}
}
