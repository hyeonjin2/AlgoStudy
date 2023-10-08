package com.day1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 주사위 굴리기
public class BaekJoon14499 {

	static int N, M, K;
	static int[][] map;
	static int[] dir;
	static int[] dice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print(map);
		dir = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			dir[i] = Integer.parseInt(st.nextToken());
		}
		dice = new int[7];
		move(x, y, 0);

	}

	// 우,좌,상,하
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	private static void move(int x, int y, int ind) {
		if (ind == K) {
			return;
		}
		// 움직여야할 방향
		int d = dir[ind] - 1;
		int nx = x + dx[d];
		int ny = y + dy[d];

		// 배열의 범위를 벗어나면 무시하기
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			move(x, y, ind + 1);
			return;
		}
		int[] temp = new int[7];
		for (int i = 0; i < 7; i++) {
			temp[i] = dice[i];
		}
		// 주사위 굴리기
		if (d == 0) {
			dice[6] = temp[3];
			dice[1] = temp[4];
			dice[3] = temp[1];
			dice[4] = temp[6];
		} else if (d == 1) {
			dice[1] = temp[3];
			dice[4] = temp[1];
			dice[6] = temp[4];
			dice[3] = temp[6];
		} else if (d == 2) {
			dice[2] = temp[6];
			dice[1] = temp[2];
			dice[5] = temp[1];
			dice[6] = temp[5];
		} else if (d == 3) {
			dice[2] = temp[1];
			dice[1] = temp[5];
			dice[5] = temp[6];
			dice[6] = temp[2];
		}
		if (map[nx][ny] == 0) {
			map[nx][ny] = dice[6];
		} else {
			dice[6] = map[nx][ny];
			map[nx][ny] = 0;
		}
		System.out.println(dice[1]);
		move(nx, ny, ind + 1);
	}
}
