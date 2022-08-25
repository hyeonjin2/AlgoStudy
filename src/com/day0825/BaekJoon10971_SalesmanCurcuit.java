package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon10971_SalesmanCurcuit {

	static int N, Ans;
	static int[] selected;
	static int[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 도시 방문 순서 순열 뽑기
		Ans = Integer.MAX_VALUE;
		selected = new int[N];
		perm(0, 0);
		System.out.println(Ans);
	}

	private static void perm(int cnt, int flag) {
		if (cnt == N) {
			int sum = calc();
			Ans = Math.min(Ans, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}

	// 0,1,2,3,0
	private static int calc() {
		// 최소비용 계산하기
		int sum = 0;
		int start = selected[0];
		if (adjMatrix[selected[N - 1]][start] == 0)
			return Integer.MAX_VALUE;
		for (int i = 0; i < N - 1; i++) {
			int from = selected[i];
			int to = selected[i + 1];
			if (adjMatrix[from][to] == 0) {
				return Integer.MAX_VALUE;
			}
			sum += adjMatrix[from][to];
		}
		sum += adjMatrix[selected[N - 1]][start];
		return sum;
	}
}
