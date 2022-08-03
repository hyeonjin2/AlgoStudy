package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11659_Sum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 배열 초기화
		int[] arr = new int[N];
		int[] sum = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[i] = arr[i];
				continue;
			}
			sum[i] = sum[i - 1] + arr[i];
		}
		StringBuilder sb = new StringBuilder();
		// M번 만큼 계산
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a - 2 >= 0) {
				sb.append((sum[b - 1] - sum[a - 2]) + "\n");
			} else {
				sb.append(sum[b - 1] + "\n");
			}
		}
		System.out.print(sb);
	}
}
