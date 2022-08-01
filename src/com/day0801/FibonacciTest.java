package com.day0801;

import java.util.Scanner;

public class FibonacciTest {
	static int[] arr;

	static int fibonacci1(int n) {
		if (arr[n] == 0 && n < 2) {
			arr[n] = n;
		} else {
			if (arr[n] == 0)
				arr[n] = fibonacci1(n - 1) + fibonacci1(n - 2);
		}
		return arr[n];
	}

	static int fibonacci2(int n) {
		if (n < 2)
			return n;
		return fibonacci2(n - 1) + fibonacci2(n - 2);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n + 1];
		long start = System.nanoTime();
		System.out.println(fibonacci1(n));
		long end = System.nanoTime();
		System.out.println((end - start) / 1_000_000_000.0);
		
		start = System.nanoTime();
		System.out.println(fibonacci2(n));
		end = System.nanoTime();
		System.out.println((end - start) / 1_000_000_000.0);
	}
}
