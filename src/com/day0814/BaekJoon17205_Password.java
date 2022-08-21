package com.day0814;

import java.io.IOException;
import java.util.Scanner;

public class BaekJoon17205_Password {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();

		long[] order = new long[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				order[i] = 1;
				continue;
			}
			order[i] = order[i - 1] + (long) Math.pow(26, i);
		}
		long count = 0;
		String str = sc.next();
		int[] word = new int[str.length()];
		for (int i = 0; i < word.length; i++) {
			word[i] = str.charAt(i) - 'a';
			count += word[i] * order[n - i - 1] + 1;
		}
		System.out.println(count);
	}
}
