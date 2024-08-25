
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();

		for (int i = 1; i <= 10000; i++) {
			String dn_str = String.valueOf(i);
			int dn = i;
			for (int j = 0; j < dn_str.length(); j++) {
				dn += dn_str.charAt(j) - '0';
			}
			list.add(dn);

		}
		Collections.sort(list);
//		System.out.println(list);
		
		con: for(int i = 1; i<=10000;i++) {
			boolean check = true;
			for(int j = 0; j<list.size();j++) {
				if(i==list.get(j)) {
					check = false;
					continue con;
				}
			}
			if (check) {
				System.out.println(i);
				continue con;
			}
		}

	}

}
