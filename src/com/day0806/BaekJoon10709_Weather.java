package com.day0806;

import java.util.Scanner;

public class BaekJoon10709_Weather {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		StringBuilder sb = new StringBuilder();
		int[][] cloud = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = sc.nextLine();
			boolean isCloud = false;
			int cnt = 0;
			for (int j = 0; j < M; j++) {
				char curr = str.charAt(j);
				if (curr == 'c') {
					cloud[i][j] = 0;
					cnt = 0;
					isCloud = true;
					sb.append(cloud[i][j] + " ");
					continue;
				} else if (isCloud) {
					cloud[i][j] = ++cnt;
				} else if (curr == '.') {
					cloud[i][j] = -1;
				}
				sb.append(cloud[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
