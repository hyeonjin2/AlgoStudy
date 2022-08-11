package com.day0811;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class SW1227_Maze2 {

	static int N = 100;
	static int[][] map;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

// BFS 풀이
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner scan = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			scan.nextInt();
			map = new int[N][N];
			Point p = null;
			Ans = 0;
			for (int i = 0; i < N; i++) {
				String str = scan.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
					if (map[i][j] == 2) {
						p = new Point(i, j);
					}
				}
			}
			bfs(p);
			System.out.printf("#%d %d\n", tc, Ans);
		}
	}

	static int Ans = 0;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs(Point start) {
		// bfs
		// Q 생성하고
		// Q 에 하나 저장
		// loop Q에서 하나 빼고 빼온 애와 연결된 노드들을 Q에 넣는다.
		// Q가 빌 때까지 반복
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		boolean[][] v = new boolean[N][N];
		v[start.r][start.c] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (map[p.r][p.c] == 3) {
				// 도착
				Ans = 1;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];// 경계 안에 있다면 연결된 자식 노드들 담기
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && map[nr][nc] != 1) {
					v[nr][nc] = true;
					queue.add(new Point(nr,nc));
				}
			}
		}
	}
}
