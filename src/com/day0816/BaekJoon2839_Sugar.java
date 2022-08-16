package com.day0816;

import java.util.Scanner;

public class BaekJoon2839_Sugar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();
		int count = 0;
		while (weight > 0) {
			// 5의 배수가 아니라면
			if (weight % 5 != 0) {
				// 3보다 크거나 같다면 3빼기
				if (weight >= 3) {
					weight -= 3;
					count++;
				// 0보다 크고 3보다 작다면 만들 수 없는 경우이므로 -1로 바꾸고 반복 탈출
				} else {
					count = -1;
					break;
				}
			// 5의 배수라면
			} else {
				// 5보다 크거나 같다면 5빼기
				if (weight >= 5) {
					weight -= 5;
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
