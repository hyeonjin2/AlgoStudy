package com.day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2589_TreasureIsland {

	static class Land {
		int x, y;
		int time;

		public Land(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static int N, M, Ans;
	static char[][] map;
	static boolean[][] visited;
	static List<Land> lands;

	// 땅 4방 탐색 델타배열 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 바디이면 0, 땅이면 1
		map = new char[N][M];
		lands = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				map[i][j] = c;
				if (c == 'L') {
					lands.add(new Land(i, j, 0));
				}
			}
		}
		// 모든 땅을 기준으로 최댓값 조사
		for (int i = 0, size = lands.size(); i < size; i++) {
			bfs(lands.get(i));
		}
		System.out.println(Ans);
	}

	private static void bfs(Land start) {
		Queue<Land> queue = new ArrayDeque<>();
		queue.offer(start);
		visited = new boolean[N][M];
		visited[start.x][start.y] = true;

		while (!queue.isEmpty()) {
			Land cur = queue.poll();
			Ans = Math.max(Ans, cur.time);
			for (int d = 0; d < 4; d++) {
				Land next = new Land(cur.x + dx[d], cur.y + dy[d], cur.time);
				// 배열의 범위 밖이면 건너뜀
				if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M)
					continue;
				// 이미 방문한 곳이면 건너뜀
				if (visited[next.x][next.y])
					continue;
				// 땅이면 이동
				if (map[next.x][next.y] == 'L') {
					visited[next.x][next.y] = true;
					next.time = cur.time + 1;
					queue.add(next);
				}
			}
		}
	}
}
