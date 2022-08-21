package com.day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1722_PermutationOrder2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 순열의 크기 입력 받기
		long[] fact = new long[21]; // 팩토리얼을 계산해서 넣어 둘 배열
		boolean[] selected = new boolean[21]; // 중복을 확인할 배열
		int[] numbers = new int[N];
		// 팩토리얼 계산
		Arrays.fill(fact, 1);
		for (int i = 1; i <= 20; i++) {
			fact[i] = fact[i - 1] * i;
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int type = Integer.parseInt(st.nextToken());
		if (type == 1) { // 1이 들어오면 K번째 순열 출력
			long K = Long.parseLong(st.nextToken());
			// N번째 자리까지 반복
			for (int i = 0; i < N; i++) {
				// j번째 숫자가 배열에 있는지 검사
				for (int j = 1; j <= N; j++) {
					if (selected[j]) {
						continue;
					}
					// K가 더 크면 팩토리얼 빼기
					if (fact[N - i - 1] < K) {
						K -= fact[N - i - 1];
						// K가 더 작으면 숫자 저정하고 반복 끝내기
					} else {
						numbers[i] = j;
						selected[j] = true;
						break;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			System.out.println(sb);
		} else if (type == 2) { // 2가 들어오면 해당 순열의 순서 출력
			// 순열 입력 받기
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			long count = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < numbers[i]; j++) {
					if (!selected[j])
						count += fact[N - i - 1];
				}
				selected[numbers[i]] = true;
			}
			System.out.println(count);
		}
	}
}
