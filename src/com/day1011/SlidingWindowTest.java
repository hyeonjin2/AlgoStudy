package com.day1011;

// 고정된 크기의 윈도우를 이동시키면서 윈도우 안의 데이터를 이용해 문제를 푸는 알고리즘
// 배열이나 리스트의 요소에 대해 일정 범위를 지정해 작업할 때 사용됨.
// 윈도우 크기를 3으로 하는 경우에 연속된 수들의 최대 합
public class SlidingWindowTest {

	public static void main(String[] args) {
		int[] arr = { 3, 1, 5, 6, 4, 8, 2, 3, 7, 1 };
		int n = 3; // 윈도우 크기

		int max = 0;
		int left = 0;
		int right = n; // 3
		int sum = 0; // 각 윈도우마다 계산되는 임시 합

		// 1. 초기 윈도우 값 작업
		for (int i = 0; i < right; i++) {
			sum += arr[i]; // 인덱스 0~2까지의 합. 첫 윈도우 작업 결과
		}
		// 2. 첫 작업 결과를 최종 답으로 지정
		max = sum;

		// 3. 윈도우를 움직이면서 작업
		while (right < arr.length) {
			sum -= arr[left++]; // 윈도우 왼쪽값 제거
			sum += arr[right++]; // 윈도우 오른쪽 값 추가

			if (sum > max) {
				max = sum;
			}
		}
		System.out.println(max);
	}
}
