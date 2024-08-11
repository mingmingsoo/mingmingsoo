
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[][] dice = new int[n][6];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 6; j++) {
				dice[i][j] = sc.nextInt();
			}
		}
		int ans = 0;
		for (int j = 0; j < 6; j++) {
			ans = Math.max(max_side(dice, 0, dice[0][j], n),ans); // max 값
		}
		System.out.println(ans);
	}

	private static int max_side(int[][] dice, int i, int j, int n) {
		if (i == n) {
			return 0;
		}
		int max = 0;
		
		for(int w = 0; w<6;w++) {
			if(j == dice[i][w]) {
				j = w;
				break;
			}
		}
		
		
		
		int j_bottom = 0;
		if (j == 0 || j == 5) {
			j_bottom = 5 - j;
		} else {
			if (j < 3) {
				j_bottom = j + 2;
			} else if (j < 5) {
				j_bottom = j - 2;
			}
		}

		for (int k = 0; k < 6; k++) {
			if (k != j && k != j_bottom) {
				max = Math.max(max, dice[i][k]);
			}
		}
		return max + max_side(dice, i + 1, dice[i][j_bottom], n);
	}

}
