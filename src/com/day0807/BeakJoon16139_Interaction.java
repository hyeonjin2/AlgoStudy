package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon16139_Interaction {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] sum = new int[str.length()];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String target = st.nextToken();
			// target에 대한 누적합 배열 초기화
			for (int j = 0; j < str.length(); j++) {
				if (j == 0) {
					if (target.equals(str.charAt(j) + ""))
						sum[j] = 1;
					else {
						sum[j] = 0;
					}
					continue;
				}
				if (target.equals(str.charAt(j) + "")) {
					sum[j] = sum[j - 1] + 1;
				} else {
					sum[j] = sum[j - 1];
				}
			}
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (start == 0) {
				System.out.println(sum[end]);
			} else {
				System.out.println(sum[end] - sum[start - 1]);
			}
		}
	}
}
