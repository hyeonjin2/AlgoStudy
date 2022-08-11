package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2961_Food {

	static int N, sour, bitter, diff;
	static int[][] ingredient;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ingredient = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredient[i][0] = Integer.parseInt(st.nextToken()); // 신맛 : 곱하기
			ingredient[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛 : 더하기
		}
		isSelected = new boolean[N];
		diff = Integer.MAX_VALUE;
		subSet(0);
		System.out.println(diff);
	}
	// 부분 집합을 구하는 함수
	private static void subSet(int index) {
		if (index == N) {
			calc();
			return;
		}
		isSelected[index] = true;
		subSet(index + 1);
		isSelected[index] = false;
		subSet(index + 1);
	}
	// 신맛과 쓴맛을 계산하여 결과를 계산하는 함수
	private static void calc() {
		sour = 1; // 신맛은 값을 곱해야하기 때문에 1로 초기화
		bitter = 0;
		// 신맛, 쓴맛 계산하기
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				sour *= ingredient[i][0];
				bitter += ingredient[i][1];
			}
		}
		// 둘 다 적어도 하나씩은 골라야하기 때문에 둘 중에 하나라도 0이 되면 안된다 -> return으로 건너뛰기
		if (sour == 0 || bitter == 0) {
			return;
		}
		// 최솟값 계산
		int tmp = Math.abs(sour - bitter);
		diff = Math.min(diff, tmp);
	}
}
