import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String[] arr = str.split(" ");
        if(arr.length == 0){
            System.out.println(0);
            return;
        }
		if (arr[0] == "" || arr[arr.length - 1] == "") {
			System.out.println(arr.length - 1);
			return;
		}
		System.out.println(arr.length);
	}
}
