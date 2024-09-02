
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = 2;
		int C = sc.nextInt(); // 2

		int r = sc.nextInt(); // 3
		int c = sc.nextInt(); // 1

		int size = 분할정복(N, C);
//		System.out.println(size);
		find(r, c, size);
		System.out.println(value);

	}

	private static void find(int r, int c, int size) {
		if (size == 1) {
			return;
		}
		int newsize = size/2;
		if(r< newsize && c<newsize) {
			find(r,c,newsize);
		}
		else if( r< newsize && c>= newsize) {
			find(r,c-newsize,newsize);
			value += (size * size) / 4 * 1;
		}
		else if( r>= newsize && c< newsize) {
			find(r-newsize,c,newsize);
			value += (size * size) / 4 * 2;
		}
		else {
			find(r-newsize,c-newsize,newsize);
			value += (size * size) / 4 * 3;
		}

	}

	static int value;

	private static int 분할정복(int N, int C) {
		if (C == 1) {
			return N;
		}

		if (C % 2 == 0) {
			int tmp = 분할정복(N, (C / 2));
			return tmp * tmp;
		} else {
			int tmp = 분할정복(N, ((C - 1) / 2));
			return tmp * tmp * N;
		}
	}

}
