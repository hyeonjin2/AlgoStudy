package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1208_Platten {
	static int[] boxs;
	static int count = 0;

	static void dump(int N, int min, int max) {
		// 1. 종료 조건 : n==0 또는 최고점-최저점 = 1
		if (N == 0 || max - min == 1) {
			return;
		}
		// 2. 반복 조건 : 최고점의 박스--, 최저점의 박스++, 최고점, 최저점 찾기 -> 정렬해서 인덱스로 접근
		boxs[0] += 1;
		boxs[99] -= 1;
		count++;
		// 3. 다음 재귀때 달라지는 것 : 최고점, 최저점
		Arrays.sort(boxs);
		min = boxs[0];
		max = boxs[99];

		// 4. 재귀 호출
		dump(N - 1, min, max);

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int num = Integer.parseInt(br.readLine()); // 덤프의 횟수 제한
			boxs = new int[100]; // box의 높이
			StringTokenizer st = new StringTokenizer(br.readLine());
			count = 0;
			// field 배열 초기화
			for (int i = 0; i < 100; i++) {
				boxs[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(boxs);
			dump(num, boxs[0], boxs[99]);

			System.out.println("#" + tc + " " + (boxs[99] - boxs[0]));
		}
	}
}