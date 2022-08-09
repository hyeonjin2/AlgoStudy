package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1233_ValidTest {
// 자식노드가 있는데 숫자가 나오면 안된다
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] tree = new String[N + 1];
			int result = 1;
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int nodeNum = Integer.parseInt(st.nextToken()) - 1;
				tree[nodeNum] = st.nextToken();
				try {
					String tmp = st.nextToken();
					if (!(tree[nodeNum].charAt(0) < 48)) {
						result = 0;
						continue;
					}
				} catch (Exception e) {
					if (tree[nodeNum].charAt(0) < 48) {
						result = 0;
					}
					continue;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}
}
