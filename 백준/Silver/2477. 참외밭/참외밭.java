import java.util.Arrays;
import java.util.Scanner;

public class  Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int ea = sc.nextInt();
		int[] dir = new int[6]; // direction
		int[] len = new int[6]; // length

		for (int i = 0; i < 6; i++) {
			dir[i] = sc.nextInt();
			len[i] = sc.nextInt();
		}
//		System.out.println(Arrays.toString(dir));

		while (true) {
			int tmp = dir[0];
			int tmp_len = len[0];
			for (int i = 0; i < 5; i++) {
				dir[i] =  dir[i+1];
				len[i] =  len[i+1];
			}
			dir[5] =tmp;
			len[5] =tmp_len;
			if(dir[0] == dir[2] && dir[1] == dir[3]) {
				break;
			}
		}

//		System.out.println(Arrays.toString(dir));
//		System.out.println(Arrays.toString(len));
		System.out.println((len[4]*len[5]-len[1]*len[2])*ea);

	}

}