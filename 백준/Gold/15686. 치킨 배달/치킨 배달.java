
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		n = 0; // 총 2 갯수
		m = sc.nextInt(); // 선택할 2의 갯수
		n1 = 0; // 총 1 갯수
		int[][] grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = sc.nextInt();
				grid[i][j] = tmp;
				if (tmp == 2) {
					n++;
				}
				if (tmp == 1) {
					n1++;
				}
			}
		}
		arrx = new int[n]; // 2위치의 x좌표 담는 배열
		arry = new int[n]; // 2위치의 y좌표 담는 배열

		arrx1 = new int[n1];// 1위치의 x좌표 담는 배열
		arry1 = new int[n1];// 1위치의 y좌표 담는 배열

		int idx = 0;
		int idx1 = 0;
		out: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 2) {
					arrx[idx] = i; // 2 좌표 담아주기
					arry[idx] = j;
					idx++;
				}
				if (grid[i][j] == 1) {
					arrx1[idx1] = i; // 1 좌표 담아주기
					arry1[idx1] = j;
					idx1++;
				}
				if (idx == n && idx1 == n1) { // 조금이라도 더 빠르라고 ..다 채워졌으면 break
					break out;
				}
			}
		}

//		System.out.println(Arrays.toString(arrx));
//		System.out.println(Arrays.toString(arry));
//		System.out.println(Arrays.toString(arrx1));
//		System.out.println(Arrays.toString(arry1));

		selx = new int[m]; // 선택할 좌표 위치
		sely = new int[m];
		ans = Integer.MAX_VALUE;
		track(0, 0);
		System.out.println(ans);

	}

	static int N;
	static int n;
	static int n1;
	static int m;
	static int[] arrx;
	static int[] arry;
	static int[] arrx1;
	static int[] arry1;
	static int[] selx;
	static int[] sely;
	static int ans;

	private static void track(int idx, int sidx) {

		int distance = 0; // 거리 초기화
		// 기저 조건
		if (sidx == m) { // 다 선택 됐으면
			for (int i = 0; i < n1; i++) {
				distance += find2(arrx1[i], arry1[i]); // 1에서부터 가장 가까운 2 위치 거리 더해주기
			}
			ans = Math.min(ans, distance); // 그중 최솟값
			return;
		}

		if (idx == n) {
			return;
		}

		selx[sidx] = arrx[idx]; // 재귀조화
		sely[sidx] = arry[idx];
		track(idx + 1, sidx + 1);
		track(idx + 1, sidx);

	}

	private static int find2(int xidx2, int yidx2) { // 1인 좌표들
		// 가장 가까운 2을 찾아야함
		int cal = Integer.MAX_VALUE;

		for (int i = 0; i < m; i++) {
			int minus = Math.abs(selx[i] - xidx2) + Math.abs(sely[i] - yidx2); //2와의 거리 구해주기
			cal = Math.min(cal, minus);
		}

		return cal;
	}

}
