package com.day0826;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 녹색 옷 입은 애가 젤다지? 다익스트라 풀이
public class BaekJoon4485_ZeldaDijkstra {

	static int N, Ans, total;
	static boolean[][] visited;
	static int[][] map, D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			tc++;
			StringTokenizer st;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N];
			D = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			Ans = Integer.MAX_VALUE;
			search();
			sb.append("Problem ").append(tc).append(": ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void search() {
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		D[0][0] = map[0][0];
		// 출발 정점 처리
		pQueue.offer(new int[] { 0, 0, map[0][0] });
		while (!pQueue.isEmpty()) {
			int[] cur = pQueue.poll();
			// 목적지에 도착했으면 리턴
			if (visited[cur[0]][cur[1]])
				continue;
			if (cur[0] == -1 && cur[1] == -1) {
				Ans = total;
				return;
			}
			visited[cur[0]][cur[1]] = true;
			total = cur[2];
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (!visited[nx][ny] && D[nx][ny] > total + map[nx][ny]) {
					D[nx][ny] = total + map[nx][ny];
					pQueue.offer(new int[] { nx, ny, D[nx][ny] });
				}
			}
		}
	}

	static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
/*








*/