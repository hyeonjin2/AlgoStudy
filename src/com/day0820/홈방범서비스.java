package com.day0820;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 홈방범서비스 {
	static int T, N, M, Ans;
	static int[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("홈방범서비스.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Ans = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// print(map);
			// solving
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					bfs(r, c);
				}
			}
			System.out.printf("#%d %d\n", tc, Ans);
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static void bfs(int r, int c) {
		Queue<Point> Q = new LinkedList();
		boolean[][] v = new boolean[N][N];
		Q.add(new Point(r, c));
		v[r][c] = true;
		int house = 0;
		house = map[r][c] == 1 ? 1 : 0;
		int k = 1;
		while (!Q.isEmpty()) {

			int size = Q.size();

			int cost = k * k + (k - 1) * (k - 1);
			// System.out.println("cost : "+cost);
			if (M * house - cost >= 0) {
				Ans = Math.max(Ans, house);
			}

			for (int i = 0; i < size; i++) {
				Point p = Q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
						if (map[nr][nc] == 1)
							house++;
						v[nr][nc] = true;
						Q.add(new Point(nr, nc));
					}
				}
			}
			k++;
		}
	}

	private static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
