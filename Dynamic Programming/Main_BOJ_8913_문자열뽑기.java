package algorithm.M10.D27;

import java.io.*;
import java.util.*;

public class Main_BOJ_8913_문자열뽑기 {

	static Map<String, Boolean> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		map.put("", true);
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			sb.append(pick(br.readLine()) ? 1 : 0).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static boolean pick(String s) {

		if (map.containsKey(s))
			return map.get(s);

		boolean flag = false;

		int len = s.length();
		List<String> arr = new ArrayList<>();

		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (i != 0 && s.charAt(i - 1) != s.charAt(i)) {
				arr.add(temp.toString());
				temp = new StringBuilder();
			}
			temp.append(s.charAt(i));
		}

		arr.add(temp.toString());

		len = arr.size();

		for (int i = 0; i < len; i++) {
			StringBuilder sub = new StringBuilder();
			if (arr.get(i).length() == 1)
				continue;
			for (int j = 0; j < len; j++) {
				if (i == j)
					continue;
				sub.append(arr.get(j));
			}
			if (pick(sub.toString())) {
				flag = true;
				break;
			}
		}

		map.put(s, flag);
		return flag;
	}
}
