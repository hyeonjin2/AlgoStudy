package com.day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaekJoon1181_WordSort {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(br.readLine().trim());
		}
		Collections.sort(list, (e1, e2) -> {
			if (e1.length() == e2.length()) {
				return e1.compareTo(e2);
			}
			return e1.length() - e2.length();
		});
		StringBuilder sb = new StringBuilder();
		for (int i = list.size() - 1; i > 0; i--) {
			String tmp = list.get(i);
			if (tmp.equals(list.get(i - 1))) {
				list.remove(tmp);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}
		System.out.println(sb);
	}
}
