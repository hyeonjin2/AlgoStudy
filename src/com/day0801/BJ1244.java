package com.day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 스위치의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] switches = new int[N]; // 스위치의 현재 상태를 담을 int 배열
		// 스위치 배열 초기화
		for (int i = 0; i < N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		int student = Integer.parseInt(br.readLine()); // 학생의 수를 받을 변수
		int gender; // 성별을 구별할 변수
		int number; // 받은 스위치 번호를 저장할 변수
		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			// 남학생이라면 받은 번호의 배수의 스위치 상태 변경
			number = Integer.parseInt(st.nextToken());
			if (gender == 1) {
				for (int j = 0; j < N; j++) {
					// 배수 검사
					if ((j + 1) % number == 0) {
						// 스위치 상태 바꾸기
						if (switches[j] == 0) {
							switches[j] = 1;
						} else {
							switches[j] = 0;
						}
					}
				}
			}
			// 여학생이라면 대칭이 최대가 되는 스위치들 모두 상태 바꾸기
			else {
				// 받은 번호의 스위치 상태 바꾸기
				if (switches[number - 1] == 0) {
					switches[number - 1] = 1;
				} else {
					switches[number - 1] = 0;
				}
				int start = number - 2; // 대칭의 시작이 되는 인덱스
				int end = number; // 대칭의 끝이 되는 인덱스
				// 대칭이 최대가 되는 인덱스 찾기
				while (start >= 0 && end < N) {
					// 스위치의 상태가 같다면 스위치 상태 바꾸기
					if (switches[start] == switches[end]) {
						if (switches[start] == 0) {
							switches[start] = 1;
							switches[end] = 1;
						} else {
							switches[start] = 0;
							switches[end] = 0;
						}
					} else {
						break;
					}
					start--;
					end++;
				}
			}
		}
		System.out.print(switches[0]);
		for (int i = 1; i < N; i++) {
			if (i % 20 == 0) {
				System.out.println();
				System.out.print(switches[i]);
			} else {
				System.out.print(" " + switches[i]);
			}
		}
	}
}
