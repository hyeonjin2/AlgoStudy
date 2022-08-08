package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW1228_Password {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			List<Integer> numbers = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < K; i++) {
				String tmp = st.nextToken();
				if (tmp.equals("I")) {
					int start = Integer.parseInt(st.nextToken());
					int num = Integer.parseInt(st.nextToken());
					for (int j = 0; j < num; j++) {
						numbers.add(start + j, Integer.parseInt(st.nextToken()));
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				sb.append(numbers.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
