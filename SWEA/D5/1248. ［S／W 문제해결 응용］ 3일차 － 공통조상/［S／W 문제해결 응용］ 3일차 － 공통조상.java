
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	static int v;
	static int[] left;
	static int[] right;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {

			v = sc.nextInt();
			int e = sc.nextInt();

			int x = sc.nextInt();
			int y = sc.nextInt();
			
			left = new int[v + 1];
			right = new int[v + 1];

			for (int i = 0; i < e; i++) {
				int parent = sc.nextInt();
				int child = sc.nextInt();
				if (left[parent] == 0) {
					left[parent] = child;
				} else if (right[parent] == 0) {
					right[parent] = child;
				}
			}
//			System.out.println(Arrays.toString(left));
//			System.out.println(Arrays.toString(right));
			

			List<Integer> xlist = new ArrayList<>();
			List<Integer> ylist = new ArrayList<>();

			// 공통 조상부터 구해야지
			for (int i = v; i >= 0; i--) { // i = 13부터 뒤에서
				while (true) {
					if (left[i] == x) { // left[13--] = 13 이면
//						System.out.println(i); // i = 11 v 가 다시 11이 됐으면 좋겠어
						xlist.add(i);
						x = i;
						i = v;
					} else if (right[i] == x) {
//						System.out.println(i);
						xlist.add(i);
						x = i;
						i = v;
					} else {
						break;
					}
				}
			}
			System.out.println();

			for (int i = v; i >= 0; i--) { // i = 13부터 뒤에서
				while (true) {
					if (left[i] == y) { // left[13--] = 13 이면
//						System.out.println(i); // i = 11 v 가 다시 11이 됐으면 좋겠어
						ylist.add(i);
						y = i;
						i = v;
					} else if (right[i] == y) {
//						System.out.println(i);
						ylist.add(i);
						y = i;
						i = v;
					} else {
						break;
					}
				}
			}

//			System.out.println(xlist);
//			System.out.println(ylist);

			List<Integer> samelist = new ArrayList<>();

			for (int i = 0; i < xlist.size(); i++) {
				for (int j = 0; j < ylist.size(); j++) {
					if (xlist.get(i).equals(ylist.get(j))) {
						samelist.add(xlist.get(i));
					}
				}
			}

//			System.out.println("samelist"+samelist);
			int same_ele = samelist.get(0);
			System.out.print("#"+tt+" "+same_ele+" ");

			System.out.print(preorder(same_ele));
			tt++;
		}
	}


	private static int preorder(int root) {
		int cnt = 1;
		if (root > v || root == 0 ) {
			return 0;
		}

		return cnt+preorder(left[root])+preorder(right[root]);
	}
}