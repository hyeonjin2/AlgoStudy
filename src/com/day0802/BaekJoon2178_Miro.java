package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2178_Miro {
	static int min = Integer.MAX_VALUE;
	static int count = 0;
	static int[][] arr;
	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[] moveWhere; // 방문 중인 방향 체크 배열

	// 4방 탐색하여 갈 수 있는 곳으로 이동
	static void move(int curr_row, int curr_col, int last) {
		if (curr_row == arr.length && curr_col == arr[0].length) {
			return;
		}
		// curr_row, curr_col에 대해서 4방 탐색해보기
		for (int k = 0; k < 4; k++) {
			// 방문 중인 방향 배열 false로 초기화
			// 온 곳으로 되돌아가지 않도록 조건 세우기
			Arrays.fill(moveWhere, false);
			// 인덱스가 배열의 범위를 벗어나면 다음 인덱스 탐색
			int next_row = curr_row + dr[k];
			int next_col = curr_col + dc[k];

			if (next_row < 0 || next_row >= arr.length || next_col < 0 || next_col >= arr[0].length) {
				continue;
			}
			if (arr[next_row][next_col] == 1) { // 4방 중 한 곳에 1이 있다면 그 곳으로 이동 후 count++
				move(next_row, next_col, k);
				// 방문 중인 방향 배열 초기화 후 해당 방향 true
				Arrays.fill(moveWhere, false);
				moveWhere[k] = true;
				count++;
			} else {

			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		String str = "";
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - 48;
			}
		}

	}

}
