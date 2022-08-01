package com.day0801;

import java.util.Scanner;

public class HonoiTest {

//	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	static void honoi(int num, int start, int temp, int end) {
		// 기저 조건
		if (num == 1) {
//			count++;
			sb.append(start + " " + end + "\n");
			return;
		}
		// 유도 파트
		honoi(num - 1, start, end, temp);
//		count++;
		sb.append(start + " " + end + "\n");
		honoi(num - 1, temp, start, end);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sb.append((int) (Math.pow(2, n) - 1) + "\n");
		honoi(n, 1, 2, 3);
//		sb.append(count + "\n");
		System.out.println(sb.toString());
	}
}
