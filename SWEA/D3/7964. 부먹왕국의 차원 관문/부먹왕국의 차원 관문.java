

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt = 1;
		while(tt<=t) {
			int city = sc.nextInt();
			int d = sc.nextInt();
			int[] arr = new int[city+2];
			for (int i = 1; i < city+1; i++) {
				arr[i] = sc.nextInt();
			}
			arr[0]=1; arr[city+1]=1;
			int cnt = 0;

			for (int i = 0; i < city+2; i++) {
				if (arr[i] == 1) {
					
					int ni = i + 1;
					int dnt = 0;
					while (ni < city+2 && arr[ni] == 0 && dnt <= d) {
						dnt++;
						ni++;
						if (dnt == d&&i+dnt<city+2) {
							if (arr[i + dnt] != 1) {
								arr[i + dnt] = 1;
								cnt++;
								i+=dnt-1;
								break;
							}
						}
					}
					
				}

			}
//		System.out.println(Arrays.toString(arr));
			System.out.println("#"+tt+" "+cnt);
			tt++;
		}

	}
}