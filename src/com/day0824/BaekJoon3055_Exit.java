package com.day0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon3055_Exit {

	static int N, M, Ans;
	static char[][] map;
	static boolean[][] visitedW;
	static boolean[][] visitedH;
	static Point hedgehog;
	static Queue<Point> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 배열 초기화
		map = new char[N][M];
		visitedW = new boolean[N][M];
		visitedH = new boolean[N][M];
		queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					visitedW[i][j] = true;
					queue.add(new Point(i, j));
				} else if (map[i][j] == 'S') {
					visitedH[i][j] = true;
					hedgehog = new Point(i, j);
				}
			}
		}
		// 물 퍼지기
		queue.offer(hedgehog);
		bfs();
		// 고슴도치 이동하기
		// 반복해서 home에 도착하는 최소시간 구하기
		if (Ans == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(Ans);
		}

//		print(map);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int level;

	private static void bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) {
				Point cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
					if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M)
						continue;
					if (map[cur.x][cur.y] == '*') {// 물이라면
						if (!visitedW[next.x][next.y] && map[next.x][next.y] == '.') {
							visitedW[next.x][next.y] = true;
							map[next.x][next.y] = '*';
							queue.offer(next);
						}
					} else if (map[cur.x][cur.y] == 'S') { // 고슴도치라면
						if (!visitedH[next.x][next.y] && map[next.x][next.y] != '*' && map[next.x][next.y] != 'X') {
							visitedH[next.x][next.y] = true;
							if (map[next.x][next.y] == 'D') {
								Ans = level;
								return;
							}
							map[next.x][next.y] = 'S';
							queue.offer(next);
						}
					}
				}
//				System.out.println("=========="+level+"==========");
//				System.out.println(queue);
//				print(map);
			}
		}
	}

	private static void print(char[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
