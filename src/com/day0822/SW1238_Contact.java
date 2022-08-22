package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1238_Contact {

	static int Ans;
	static int[][] matrix;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
//		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 간선의 수
			int N = Integer.parseInt(st.nextToken());
			// 시작점
			int start = Integer.parseInt(st.nextToken());
			// 배열 초기화
			matrix = new int[101][101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				matrix[from][to] = 1;
			}
			// 동시에 통화 가능하므로 bfs구현
			bfs(start);
			sb.append("#").append(1).append(" ").append(Ans).append("\n");
//		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited = new boolean[101];
		visited[start] = true;
		while (!queue.isEmpty()) {
			// 가장 마지막에 연락한 사람 중 가장 큰 번호를 구해야하므로 같은 레벨끼리 검사
			int size = queue.size();
			Ans = 0;
			for (int k = 0; k < size; k++) {
				int cur = queue.poll();
				Ans = Math.max(Ans, cur);
				System.out.print(cur + " ");
				for (int i = 0; i < 101; i++) {
					if (!visited[i] && matrix[cur][i] != 0) {
						visited[i] = true;
						queue.offer(i);
					}
				}
			}
			System.out.println();
		}
	}

}
