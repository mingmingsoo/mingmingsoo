
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while (true) {
			int sum = sc.nextInt();
			int min = sc.nextInt();
			if (sum == 0 && min == 0) {
				break;
			}
			int sumWh = 0;
			if (sum <= 200) {
				sumWh += sum / 2;
				sum -= sumWh * 2;
			} else if (sum > 200 && sum <= 30000) {
				sumWh += 100;
				sum -= 100 * 2;
				sumWh += sum / 3;
				sum -= sumWh * 3;

			} else if (sum > 30000 && sum <= 5000000) {
				// 30515원 10123wh

				sumWh += 100;
				// 30515원 100wh

				sum -= 100 * 2;
				// 30315원 100wh

				sumWh += 9900;
				// 30315원 10000wh

				sum -= 9900 * 3;
				// 615원 10000wh

				sumWh += sum / 5;
				// 615원 10123wh
				sum -= sumWh * 5;

			} else {
				sumWh += 100;

				sum -= 100 * 2;
				// 30315원 100wh

				sumWh += 9900;
				// 30315원 10000wh

				sum -= 9900 * 3;
				// 615원 10000wh

				sumWh += 990000;
				// 30315원 10000wh

				sum -= 990000 * 5;
				// 615원 10000wh

				sumWh += sum / 7;
				// 615원 10123wh
				sum -= sumWh * 7;

			}
//		System.out.println(sumWh);

			int left = 0;
			int right = sumWh;

			while (left <= right) {
				int midleft = (left + right) / 2;
				int midright = sumWh - midleft;

				int leftfee = cal(midleft);
				int rightfee = cal(midright);
				int minus = Math.abs(leftfee - rightfee);
				if (minus == min) {
					left = midleft;
					right = midright;
					break;
				}
				if (minus < min) {
					right = midleft - 1;
				} else {
					left = midleft + 1;
				}
			}

//		System.out.println(left + " " + right);
			sb.append(cal(left) + "\n");
		}
		System.out.println(sb);
	}

	private static int cal(int wh) {
		int fee = 0;
		if (wh <= 100) {
			fee = 2 * wh;
		} else if (wh > 100 && wh <= 10000) {
			fee = 2 * 100;
			fee += (wh - 100) * 3;
		} else if (wh > 10000 && wh <= 1000000) {
			fee = 2 * 100;
			fee += 3 * 9900;
			fee += (wh - 10000) * 5;
		} else {
			fee = 2 * 100;
			fee += 3 * 9900;
			fee += 5 * 990000;
			fee += (wh - 1000000) * 7;
		}

		return fee;
	}

}
