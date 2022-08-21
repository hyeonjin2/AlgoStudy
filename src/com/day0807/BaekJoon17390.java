package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon17390 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		Long[] numbers = new Long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = (long) Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers, (e1, e2) -> {
			return (int) (e1 - e2);
		});
		for (int i = 1; i < N; i++) {
			numbers[i] += numbers[i - 1];
		}
		long result = 0;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			if (start == 0) {
				result = numbers[end];
			} else {
				result = numbers[end] - numbers[start - 1];
			}
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}
