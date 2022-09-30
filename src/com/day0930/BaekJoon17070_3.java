package com.day0930;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파이프 옮기기1
public class BaekJoon17070_3 {

	static int N;
	static long Ans;
	static int[][] map;
	static long[][][] route;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		route = new long[N + 1][N + 1][3];
		for (int j = 2; j <= N; j++) {
			if (map[1][j] == 1) {
				break;
			}
			route[1][j][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if (map[i][j] == 1)
					continue;
				route[i][j][0] = route[i][j - 1][0] + route[i][j - 1][2];
				route[i][j][1] = route[i - 1][j][1] + route[i - 1][j][2];
				if (map[i][j] == 1 || map[i - 1][j] == 1 || map[i][j - 1] == 1)
					continue;
				route[i][j][2] = route[i - 1][j - 1][0] + route[i - 1][j - 1][1] + route[i - 1][j - 1][2];
			}
		}
		Ans = route[N][N][0] + route[N][N][1] + route[N][N][2];
		System.out.println(Ans);
	}

}
