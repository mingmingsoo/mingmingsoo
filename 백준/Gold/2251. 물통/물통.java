import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		l0 = sc.nextInt();

		l1 = sc.nextInt();

		l2 = sc.nextInt();

		arr = new int[] { 0, 0, l2 };
		nums = new int[201];

		list = new ArrayList<>();

		dfs(arr);

		StringBuilder sb = new StringBuilder();
		cnt = 0;
		for (int i = 0; i < 201; i++) {
			if (nums[i] != 0) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);

	}

	static int[] arr;
	static int[] nums;
	static Set<Integer> set;
	static int cnt;
	static int l0;
	static int l1;
	static int l2;
	static List<int[]> list;

	private static void dfs(int[] ar) {

		if (ar[0] == 0) {
			nums[ar[2]]++;
		}

		if (!contains(ar)) {
			return;
		}

		list.add(ar.clone());

		pour(ar, 0, 1, l1);
		pour(ar, 0, 2, l2);
		pour(ar, 1, 0, l0);
		pour(ar, 1, 2, l2);
		pour(ar, 2, 0, l0);
		pour(ar, 2, 1, l1);

	}

    private static void pour(int[] ar, int from, int to, int limit) {
        int[] tmpar = ar.clone(); // 현재 상태 복사

        if (tmpar[from] + tmpar[to] <= limit) {
            tmpar[to] += tmpar[from];
            tmpar[from] = 0;
        } else {
            tmpar[from] -= (limit - tmpar[to]);
            tmpar[to] = limit;
        }

        // 재귀 호출
        dfs(tmpar);
    }

	private static boolean contains(int[] ar) {
		if (list.size() == 0) {
			return true;
		}
		for (int i = 0; i < list.size(); i++) {
			boolean bool = true;
			int[] compare = list.get(i);
			for (int j = 0; j < 3; j++) {
				if (ar[j] != compare[j]) {
					bool = false;
					break;
				}
			}
			if (bool) {
				return false;
			}
		}
		return true;
	}

}
