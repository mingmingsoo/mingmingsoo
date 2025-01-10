

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 무조건 처음 위치를 뽑을 수 있는데 어떻게 회전시켜야 (최소로) 그 특정 원소를 뽑아낼 수 있는가?
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Deque<Integer> dq = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			dq.add(i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
//			System.out.println("회전 전: "+ dq);
			int ele = Integer.parseInt(st.nextToken());
			if (dq.peekFirst() == ele) {
				dq.pollFirst();
				continue;
			}

			int leftMove = 0;
			for (int num : dq) {
				if (num == ele) {
					break;
				}
				leftMove++;
			}
			int rightMove = dq.size() - leftMove;
			if (leftMove < rightMove) {
//				System.out.println("왼쪽회전");
				int move = 0;
				while(move != leftMove) {
					leftRotation(dq);
					ans++;
					move++;
				}
			} else {
//				System.out.println("오른쪽회전");
				int move = 0;
				while(move != rightMove) {
					rightRotation(dq);
					ans++;
					move++;
				}
			}
//			System.out.println("회전 후: "+ dq);
			dq.poll();
		}
		System.out.println(ans);
	}

	private static void rightRotation(Deque<Integer> dq) {
		int last = dq.pollLast();
		dq.addFirst(last);
		
	}

	private static void leftRotation(Deque<Integer> dq) {
		int first = dq.pollFirst();
		dq.addLast(first);
		
	}

}
