package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1260_DFSandBFS {

	static int N;
	static int[][] matrix;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		matrix = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			matrix[from][to] = matrix[to][from] = 1;
		}
		dfs(start);
		System.out.println();
		visited = new boolean[N + 1];
		bfs(start);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		System.out.print(cur + " ");

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && matrix[cur][i] != 0)
				dfs(i);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited = new boolean[N + 1];
		visited[start] = true;

		while (!queue.isEmpty()) {

			int cur = queue.poll();
			System.out.print(cur + " ");

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && matrix[cur][i] != 0) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}
