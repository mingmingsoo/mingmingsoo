import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int tt = 1;
        while (tt <= t) {
            int n = sc.nextInt();
            int cnt = 1;
            int[] count = new int[10];
            while (true) {
                int num = n * cnt;
                while (num >= 10) {
                    count[(num) % 10]++;
                    num /= 10;
                }
                count[num]++;
                cnt++;
                int nozero = 0;
                for (int i = 0; i < count.length; i++) {
                    if (count[i] != 0) {
                        nozero++;
                    }
                }
                if (nozero == 10) {
                    break;
                }
            }
            System.out.println("#" + tt + " " + n * (cnt - 1));
            tt++;
        }
    }
}