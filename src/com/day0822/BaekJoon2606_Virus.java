package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2606_Virus {

	static int N, Cnt;
	static int[][] matrix;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		matrix = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[from][to] = matrix[to][from] = 1;
		}
		visited = new boolean[N + 1];
		dfs(1);
		System.out.println(Cnt - 1);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		Cnt++;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && matrix[cur][i] != 0)
				dfs(i);
		}
	}

}
