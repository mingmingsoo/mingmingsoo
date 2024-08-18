

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int a = 3;
		int b = 5;

		List<Integer> alist = new LinkedList<>();
		List<Integer> blist = new LinkedList<>();

		for (int i = 0; i <= n; i++) {
			if (i % a == 0) {
				alist.add(i);
			}
			if (i % b == 0) {
				blist.add(i);
			}
		}

//		System.out.println(alist);
//		System.out.println(blist);

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < alist.size(); i++) {
			int min = 0;
			
			int x = n - alist.get(i); 

			int y1 = a; 
			int y2 = b; 
			if (y1 % a == 0 && x % b == 0) {
				y1 = alist.get(i) / a;
				y2 = x / b;
				min = y1 + y2;
			}
			else {
				min = Integer.MAX_VALUE;
			}
			ans = Math.min(ans, min);
		}
		
		if( ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		
		System.out.println(ans);

	}
}
