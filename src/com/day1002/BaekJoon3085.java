package com.day1002;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 사탕 게임
public class BaekJoon3085 {

	static int N, Ans;
	static char[][] map;
	static boolean[][] visited;
	static int[] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new char[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i <= N; i++) {
			map[i][N] = map[i][N - 1];
			map[N][i] = map[N - 1][i];
		}
//		print(map);
		// 바꾸지 않은 상태에서 먹을 수 있는 최대 사탕 확인
		int max = check();
		Ans = Math.max(Ans, max);
		if (max == N) {
			System.out.println(Ans);
			System.exit(0);
		}
		// 사탕의 색이 다른 인접한 두 칸을 고른다.
		for (int i = 0; i <= N; i++) {
			max = 0;
			for (int j = 0; j < N; j++) {
				// 가로방향으로 다를 경우
				if (map[i][j] != map[i][j + 1]) {
					// 사탕을 교환한다.
					swap(new Point(i, j), new Point(i, j + 1));
					max = check();
//					print(map);
//					System.out.println(max);
					Ans = Math.max(Ans, max);
					swap(new Point(i, j + 1), new Point(i, j));
				}
				// 세로방향으로 다를 경우
				if (map[j][i] != map[j + 1][i]) {
					// 사탕을 교환한다.
					swap(new Point(j, i), new Point(j + 1, i));
					max = check();
//					print(map);
//					System.out.println(max);
					Ans = Math.max(Ans, max);
					swap(new Point(j + 1, i), new Point(j, i));
				}
			}
		}
		System.out.println(Ans);
	}

	private static void swap(Point p1, Point p2) {
		char temp = map[p1.x][p1.y];
		map[p1.x][p1.y] = map[p2.x][p2.y];
		map[p2.x][p2.y] = temp;
	}

	// 같은 색으로 이루어져 있는 가장 긴 연속 부분(행,열)을 고른 다음 그 사탕을 모두 먹는다.
	private static int check() {
		// 가로검사
		int max = 0;
		for (int i = 0; i < N; i++) {
			char temp = map[i][0];
			int count = 1;
			for (int j = 1; j < N; j++) {
				if (temp != map[i][j]) {
					temp = map[i][j];
					max = Math.max(max, count);
					count = 1;
					continue;
				}
				count++;
			}
			max = Math.max(max, count);
		}
		// 세로검사
		for (int i = 0; i < N; i++) {
			char temp = map[0][i];
			int count = 1;
			for (int j = 1; j < N; j++) {
				if (temp != map[j][i]) {
					temp = map[j][i];
					max = Math.max(max, count);
					count = 1;
					continue;
				}
				count++;
			}
			max = Math.max(max, count);
		}
		return max;
	}

	private static void print(char[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
