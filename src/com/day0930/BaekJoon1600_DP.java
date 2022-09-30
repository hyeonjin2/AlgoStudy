package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 말이 되고픈 원숭이
public class BaekJoon1600_DP {

	static int N, M, kCnt;
	static int[][] map;
	static int[][][] route;
	// 4방 탐색 델타 배열
	static int[] dx1 = { -1, 1, 0, 0 };
	static int[] dy1 = { 0, 0, -1, 1 };
	// 말처럼 움직일 때의 델타 배열
	static int[] dx2 = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy2 = { -2, -1, 1, 2, -2, -1, 1, 2 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		kCnt = K;
		route = new int[N + 1][M + 1][K + 1];
		// d[i][j][k] : k만큼 말처럼 최소로 이동해 i,j에 도달하는 경우의 수
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= M; j++) {
				if (map[i][j] == 1)
					continue;
				for (int k = 0; k <= K; k++) {
					// 원숭이처럼 이동해서 오는 경우
					int count1 = Integer.MAX_VALUE;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx1[d];
						int ny = j + dy1[d];
						if (isIn(nx, ny) && route[nx][ny][k] != 0) {
							count1 = Math.min(count1, route[nx][ny][k] + 1);
						}
					}
					// 말처럼 이동해서 오는 경우
					int count2 = Integer.MAX_VALUE;
					if (k > 0) {
						for (int d = 0; d < 8; d++) {
							int nx = i + dx2[d];
							int ny = j + dy2[d];
							if (isIn(nx, ny) && route[nx][ny][k - 1] != 0) {
								count2 += Math.min(count2, route[nx][ny][k - 1] + 1);
							}
						}
					}
					if (count1 == Integer.MAX_VALUE && count1 == Integer.MAX_VALUE)
						continue;
					route[i][j][k] = Math.min(count1, count2);
				}
			}
		}
		int result = 0;
		System.out.println(route[N][M][1]);
		for (int k = 0; k <= K; k++) {
			result += route[N][M][k];
		}
		System.out.println(result);
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
}
