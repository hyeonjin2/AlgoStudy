package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1010_Bridge {
	static int N;
	static int M;
	static int[][] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// N=2, M=2이면 2C2를 구해야 하므로 배열의 크기 각각 +1
			count = new int[M + 1][N + 1];
			System.out.println(comb(M, N));
		}
	}

	// mCn을 구하는 함수
	private static int comb(int m, int n) {
		// 1. 종료조건 : m==n이거나 n==0인 경우
		if (m == n || n == 0) {
			count[m][n] = 1;
			return count[m][n];
		}
		// 2. 반복조건 : comb(m,n)이 계산된 적이 없는 경우
		if (count[m][n] == 0) {
			count[m][n] = comb(m - 1, n) + comb(m - 1, n - 1);
		}
		// 3. 계산된 값을 return
		return count[m][n]; // mCn = m-1Cn + m-1Cn-1
	}
}
