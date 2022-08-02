package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1208_Platten2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int num = Integer.parseInt(br.readLine()); // 덤프의 횟수 제한
			int[] boxs = new int[100]; // box의 높이
			StringTokenizer st = new StringTokenizer(br.readLine());
			int count = 0;
			// field 배열 초기화
			for (int i = 0; i < 100; i++) {
				boxs[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(boxs);
			int idx_max = 99;
			int idx_min = 0;
			while (count < num) {
				if (boxs[idx_max] - boxs[idx_min] == 1) {
					break;
				}
				// dump 수행
				boxs[idx_max] -= 1;
				boxs[idx_min] += 1;
				count++;
				// 최고점, 최저점 다시 찾기
				for (int i = 0; i < 100; i++) {
					if (boxs[idx_max] < boxs[i]) {
						idx_max = i;
					}
					if (boxs[idx_min] > boxs[i]) {
						idx_min = i;
					}
				}
			}
			System.out.println("#" + tc + " " + (boxs[idx_max] - boxs[idx_min]));
		}
	}
}
