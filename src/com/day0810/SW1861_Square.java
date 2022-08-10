package com.day0810;

import java.util.Scanner;

public class SW1861_Square {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][] distance;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();

			map = new int[N][N];
			distance = new int[N][N];
			// 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 길이 확인하러 go
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (distance[i][j] == 0) {
						go(i, j); // i,j위치에서 출발하여 갈 수 있는 최대 거리 계산
					}
				}
			}
			// 모든 위치에서 갈 수 있는 최장거리 계산이 끝난 후
			int maxLen = -1; // 최장거리
			int roomNo = 0; // 방번호

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (distance[i][j] > maxLen) {
						maxLen = distance[i][j];
						roomNo = map[i][j];
					} else if (maxLen == distance[i][j]) {
						roomNo = Math.min(roomNo, map[i][j]);
					}
				}
			}
			System.out.println("#" + tc + " " + roomNo + " " + maxLen);
		}
	}

	// x,y위치에서 출발하여 갈 수 있는 최대 거리 계산
	private static void go(int x, int y) {
		distance[x][y] = 1;

		for (int i = 0; i < 4; i++) { // 4방체크
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isIn(nx, ny))
				continue;
			if (map[x][y] + 1 == map[nx][ny]) {
				go(nx, ny); // 새 위치에서 또 가봄
				distance[x][y] = distance[nx][ny] + 1; // 다음 위치가 갈 수 있는 거리에 1을 더한다.
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < N);
	}
}
