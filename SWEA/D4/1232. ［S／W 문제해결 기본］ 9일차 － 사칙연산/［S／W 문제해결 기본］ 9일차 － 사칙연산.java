
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int n;
	static String arr[];
	static int left[];
	static int right[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = 10;
		int tt =1;
		
		while(tt<=t) {
			n = sc.nextInt();
			arr = new String[n + 1];
			left = new int[n + 1];
			right = new int[n + 1];
			sc.nextLine();
			for (int i = 1; i < n + 1; i++) { // 1~7
				String line = sc.nextLine();
				String[] tmp = line.split(" ");
				arr[i] = tmp[1];
				if (tmp.length > 2) {
					left[i] = Integer.parseInt(tmp[2]);
					right[i] = Integer.parseInt(tmp[3]);
				}
			}
//			System.out.println(Arrays.toString(arr));
			
			System.out.println("#"+tt+" "+calcul(1));
			tt++;
		}


	}

	private static int calcul(int root) {

		if (arr[root] == null) { // 다했으면 나가기
			return 0;
		}

		int num;
		int left_num = calcul(left[root]); // 왼쪽가서 계산해와라
		int right_num = calcul(right[root]);// 오른쪽가서 계산해와라

		if (arr[root].equals("/")) {
			num = left_num / right_num;
		}

		else if (arr[root].equals("*")) {
			num = left_num * right_num;
		}

		else if (arr[root].equals("-")) {
			num = left_num - right_num;
		}

		else if (arr[root].equals("+")) {
			num = left_num + right_num;
		} else {
			num = Integer.parseInt(arr[root]); // 마지막 노드들은 숫자로 받아주기
		}

		return num;
	}
}
