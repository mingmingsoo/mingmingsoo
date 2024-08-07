

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        int tt = 1;

        while (tt <= t) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int city = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine().trim());
            StringBuilder s = new StringBuilder("1");
            for (int i = 0; i < city; i++) {
                s.append(st.nextToken());
            }
            s.append("1");

            int cnt = 0;
            int dnt = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    dnt = 0;
                } else {
                    dnt++;
                    if (dnt == d) {
                        cnt++;
                        dnt = 0;
                    }
                }
            }

            System.out.println("#" + tt + " " + cnt);
            tt++;
        }
    }
}