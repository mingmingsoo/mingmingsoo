
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();
			limit = sc.nextInt();
			int k = sc.nextInt();

			xlist = new ArrayList<>();
			ylist = new ArrayList<>();
			vlist = new ArrayList<>();
			dlist = new ArrayList<>();
			for (int i = 0; i < k; i++) {
				xlist.add(sc.nextInt());
				ylist.add(sc.nextInt());
				vlist.add(sc.nextInt());
				dlist.add(sc.nextInt());
			}
			q = new LinkedList<>();

			for (int i = 0; i < k; i++) {
				q.add(new int[] { xlist.get(i), ylist.get(i), vlist.get(i), dlist.get(i) });
			}
			vsum = 0;
			bfs();
			for (int i = 0; i < vlist.size(); i++) {
				vsum += vlist.get(i);
			}
			System.out.println("#" + tt + " " + vsum);
			tt++;
		}

	}

	static int limit;
	static int vsum;
	static Queue<int[]> q;
	static List<Integer> vlist;
	static List<Integer> dlist;
	static List<Integer> xlist;
	static List<Integer> ylist;
	static int n;

	private static void bfs() {
		// 기본 이동.
		// 모두 동시에 방향대로 가는데 x, y 가 0이나 n-1이 되면 미생물 반타작 & 위치 변경.
		// 만약 x y 위치가 같은 애들이 생기면 vlist 합쳐지고 방향은 제일 큰 애들 따른다. ( 합쳐지면 x,y -1 로 바꾸기)

		int time = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			time++;

			int idx = 0;
			for (int i = 0; i < size; i++) {
				int[] xy = q.poll();
				int x = xy[0]; // 1
				int y = xy[1]; // 1 이 뽑혔음.
				int v = xy[2]; // 미생물 수
				int d = xy[3]; // 방향

				int[] move_ = move(x, y, v, d);
				x = move_[0];
				y = move_[1];
				v = move_[2];
				d = move_[3];

				int[] redzone_ = redzone(x, y, v, d);
				x = redzone_[0];
				y = redzone_[1];
				v = redzone_[2];
				d = redzone_[3];

				xlist.set(idx, x);
				ylist.set(idx, y);
				vlist.set(idx, v);
				dlist.set(idx, d);
				idx++;


			}
			merge();
			if (time == limit) {
				return;
			}
		}

	}

	private static int[] move(int x, int y, int v, int d) {
		if (d == 1) {
			x--;
		} else if (d == 2) {
			x++;
		} else if (d == 3) {
			y--;
		} else {
			y++;
		}
		return new int[] { x, y, v, d };

	}

	private static void merge() {

		for (int i = 0; i < xlist.size(); i++) {
			List<Integer> tmpidx = new ArrayList<>();
			tmpidx.add(i);
			for (int j = i + 1; j < xlist.size(); j++) {
				if (xlist.get(i) == xlist.get(j) && ylist.get(i) == ylist.get(j)) {
					tmpidx.add(j);
				}
			}
			if (tmpidx.size() > 1) {

				// 미생물 크기 비교.
				// 미생물 합.
				int maxvirus = 0;
				int maxidx = -1;
				int sumvirus = 0;
				int remove = 0;
				for (int j = 0; j < tmpidx.size(); j++) {
					sumvirus += vlist.get(tmpidx.get(j));
					if (maxvirus < vlist.get(tmpidx.get(j))) {
						maxvirus = vlist.get(tmpidx.get(j));
						maxidx = tmpidx.get(j);
						remove = j;
					}
				}
				tmpidx.remove(remove);
				// 가장 큰 미생물을 가지는 위치를 알게 됐음 = maxidx // 나머지 kill
				vlist.set(maxidx, sumvirus);
				// 예를 들어 죽여야되는 위치가 2, 3 이라고 해보자..
				// 위치 안꼬이게 내림차순 정렬하고 삭제
				Collections.sort(tmpidx, Collections.reverseOrder());
				for (int j = 0; j < tmpidx.size(); j++) {
					xlist.remove((int) tmpidx.get(j));
					ylist.remove((int) tmpidx.get(j));
					vlist.remove((int) tmpidx.get(j));
					dlist.remove((int) tmpidx.get(j));
				}
				i--;
			}
		}

		for (int i = 0; i < xlist.size(); i++) {
			q.add(new int[] { xlist.get(i), ylist.get(i), vlist.get(i), dlist.get(i) });
		}

	}

	private static int[] redzone(int x, int y, int v, int d) {
		if (x == 0 || x == n - 1 || y == 0 || y == n - 1) {
			v /= 2;
			if (d == 1) {
				d = 2;
			} else if (d == 2) {
				d = 1;
			} else if (d == 3) {
				d = 4;
			} else if (d == 4) {
				d = 3;
			}
		}
		return new int[] { x, y, v, d };

	}

}