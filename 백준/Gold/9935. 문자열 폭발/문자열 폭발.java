

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();  // 입력 문자열
        String bomb = sc.next(); // 폭탄 문자열
        int bombLength = bomb.length(); // 폭탄 문자열의 길이

        char[] result = new char[str.length()]; // 스택처럼 사용할 배열
        int index = 0; // 스택의 현재 인덱스

        for (int i = 0; i < str.length(); i++) {
            result[index] = str.charAt(i); // 스택에 문자 추가
            index++;

            // 스택의 마지막 부분이 폭탄 문자열과 일치하는지 확인
            if (index >= bombLength) {
                boolean isBomb = true;

                // 스택의 마지막 부분이 폭탄 문자열과 일치하는지 확인
                for (int j = 0; j < bombLength; j++) {
                    if (result[index - bombLength + j] != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                // 폭탄 문자열과 일치하면 스택에서 해당 부분 제거
                if (isBomb) {
                    index -= bombLength; // 스택에서 폭탄 문자열 길이만큼 제거
                }
            }
        }

        // 결과가 비어 있으면 "FRULA" 출력
        if (index == 0) {
            System.out.println("FRULA");
        } else {
            // 스택의 남은 부분 출력
            System.out.println(new String(result, 0, index));
        }
    }
}