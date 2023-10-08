package com.day1012;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 소문난 칠공주
public class BaekJoon14890 {

	static int N, Ans;
	static char[][] map;
	static int[] students;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		students = new int[7];
		comb(0, 0);
		System.out.println(Ans);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 7) {
			bfs();
			return;
		}
		for (int i = start; i < N * N; i++) {
			students[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(students[0]);
		boolean[] visited = new boolean[7];
		visited[0] = true;
		int cnt = 1;
		int cntS = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int r = cur / N;
			int c = cur % N;
			if (map[r][c] == 'S')
				cntS++;
			for (int d = 0; d < 4; d++) {
				for (int next = 1; next < 7; next++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!visited[next] && nr == students[next] / N && nc == students[next] % N) {
						visited[next] = true;
						queue.offer(students[next]);
						cnt++;
					}
				}
			}
		}
		// while문을 다 돌았을 때 cnt==7이라면 모두 연결되어 있는 경우
		if (cnt == 7) {
			if (cntS >= 4) {
				Ans++;
			}
		}

	}
}
