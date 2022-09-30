package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon17070_MovingPipe {

	static int map[][], N, count;
	static int horizontal = 1, vertical = 2, diagonal = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move(1, 2, horizontal);
		System.out.println(count);
	}

	// (r,c)위치에서 dir방향으로 움직였을 때 (n,n)에 도달하는 경우의 수
	private static void move(int r, int c, int dir) {
		if (r == N && c == N) {
			++count;
			return;
		}
		if (dir == horizontal) { // 가로
			// 1. 가로
			if (c + 1 <= N && map[r][c + 1] == 0) {
				move(r, c + 1, horizontal);
			}

		} else if (dir == vertical) {
			// 2. 세로
			if (r + 1 <= N && map[r + 1][c] == 0) {
				move(r + 1, c, vertical);
			}

		} else if (dir == diagonal) {
			// 1. 가로
			if (c + 1 <= N && map[r][c + 1] == 0) {
				move(r, c + 1, horizontal);
			}
			// 2. 세로
			if (r + 1 <= N && map[r + 1][c] == 0) {
				move(r + 1, c, vertical);
			}
		}
		// 3. 대각선
		if (r + 1 <= N && c + 1 <= N && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
			move(r + 1, c + 1, diagonal);
		}
	}
}
