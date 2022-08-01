package com.day0801;

import java.util.Scanner;

public class SW1289_MemoryRecovery {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // testcase 수

		for (int tc = 1; tc <= T; tc++) {
			String data = sc.next(); // 0011 원래의 상태 값
			int count = 0; // 수정해야 되는 횟수 카운트
			
			// 시작이 1이면 바뀐것 -> count++
			if (data.startsWith("1")) {
				count++;
			}
			
			for (int i = 0; i < data.length() - 1; i++) {
				if (data.charAt(i) != data.charAt(i + 1)) {
					count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
}
