
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int n = sc.nextInt();

			int k = sc.nextInt();

			int size = n / 4; // 한변에 들어가는 문자 갯수

			Set<String> set = new LinkedHashSet<>();

			String line = sc.next(); // 1B3B3B81F75E

			int rotation = 0;

			int start = 0;
			while (rotation <= size) {
				for (int i = 0; i < 4; i++) {
					// 글자 size개
					String tmp = "";
					for (int j = start; j < start + size; j++) {
						tmp += line.charAt(j % line.length());
					}
					set.add(tmp);
					start += size;
				}
//				System.out.println(set);
				start--;
				rotation++;
			}
			List<Integer> ans = new ArrayList<>();

			for (String ele : set) {
				ans.add(Integer.parseInt(ele, 16));
			}

			Collections.sort(ans, Collections.reverseOrder());
//			System.out.println(ans);
			System.out.println("#" + tt + " " + ans.get(k - 1));
			tt++;
		}
	}

}