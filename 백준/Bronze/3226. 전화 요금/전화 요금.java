

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	/*
	 * 7:00부터 19:00까지 전화 요금은 1분에 10원이다. 19:00부터 7:00까지 전화 요금은 1분에 5원이다.
	 * 
	 * 20:05 12 - 60 06:45 30 - 15분은 5원, 15원은 10원 - 75 + 150 = 225 13:08 15 - 150
	 * 
	 */

	public static void main(String[] args) {

		// 전화요금 구하기
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 전화 건 횟수
		String[] whens = new String[n];
		int[] times = new int[n];
		for (int i = 0; i < n; i++) {
			whens[i] = sc.next(); // 전화 건 시간.
			times[i] = sc.nextInt();
		}
//		System.out.println(Arrays.toString(whens));
//		System.out.println(Arrays.toString(times));

		int fee = 0;
		for (int i = 0; i < n; i++) {
			// 06:45 30
			// 45을 60까지 더하고 60이 되면 0으로 바꿈
			// 6에서 + 1 해줌
			// 만약 hour가 7이거나 ~ 그시간이 해당되면 ..
			// 30에서 15 빼줌
			// 남은 15에서 또 반복 - while
			// 0이되면 stop
			String tmp = whens[i];
			String[] split = tmp.split(":");
			int hour = Integer.parseInt(split[0]);
			int minute = Integer.parseInt(split[1]);
			int time = times[i]; // 45분
//			System.out.println(hour + "-" + minute + "-" + time);
			while (time > 0) { // 29
				int m = 0;
				while (minute < 60) { // 46
					if (time == 0) {
						break;
					}
					minute++; // 47
					time--; // 28
					m++; // 2
				}
//				System.out.println("minute:" + minute + " time:" + time + " m:" + m);
				if (hour >= 7 && hour < 19) {
					fee += 10 * m;
				} else {
					fee += 5 * m;
				}
				hour++;
				minute = 0;
//				System.out.println("fee: " + fee + "hour: " + hour);
			}
		}
		System.out.println(fee);
	}
}
