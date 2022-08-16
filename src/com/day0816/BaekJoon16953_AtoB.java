package com.day0816;

import java.util.Scanner;

public class BaekJoon16953_AtoB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		long count = 1;
		while (b > a) {
			String str = String.valueOf(b);
			// b가 2로 나누어 떨어지지 않고 끝자리가 1이 아니면 반복을 빠져나온다.
			if (str.charAt(str.length() - 1) != '1' && b % 2 != 0) {
				count = -1;
				break;
				// 2로 나누어 떨어지면 2로 나눈다.
			} else if (b % 2 == 0) {
				b /= 2;
				count++;
				// 2로 나눠지지않고 끝자리가 1이라면 1을 오른쪽에서 뺀다.
			} else {
				b = (b - 1) / 10;
				count++;
			}
		}
		if (b < a)
			count = -1;
		System.out.println(count);
	}
}
