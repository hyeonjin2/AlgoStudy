package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1861_Square {
	static int answer; // 최대로 갈 수 있는 방의 숫자
	static int sum; // 방문할 수 있는 방의 개수
	// 이동할 수 있는 방향의 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N;
	static int[][] map;
	static boolean found;
	static int[] numbers;
	static boolean[] visited;
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			// 2차원 배열 초기화
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sum = 0;
			result = new int[N * N + 1];
			visited = new boolean[N * N + 1];
			// 방의 숫자와 방의 개수 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[map[i][j]]) { // 이미 방문했던 곳은 또 안가도 됨 -> 방문이 더이상 안되서 return되었기 때문
						continue;
					}
					numbers = new int[N * N];
					visit(i, j, 1);
				}
			}
			// 갈 수 있는 방의 최대 길이 - sum
			// 그 때의 방의 숫자(최솟값) - answer
			int[] idx = new int[N * N];
			int size = 0;
			int val = Integer.MIN_VALUE;
			int answer = Integer.MIN_VALUE;
			for (int i = 0; i < result.length; i++) {
				if (result[i] != 0 && val < result[i]) {
					val = result[i];
					answer = i;
				}
			}
			// 출력
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void visit(int row, int col, int cnt) {
		found = false;
		numbers[cnt - 1] = map[row][col];
		visited[map[row][col]] = true;
		// 반복 조건 : 상,하,좌,우에 차가 1나는 곳이 있는지 탐색
		for (int k = 0; k < 4; k++) {
			int next_row = row + dr[k];
			int next_col = col + dc[k];

			// 배열의 범위를 벗어나면 안됨
			if (next_row < 0 || next_row >= N || next_col < 0 || next_col >= N) {
				continue;
			}
			// 방문하려는 곳과 현재 있는 곳의 차이가 1이고 방문한 적 없다면 이동
			if (map[next_row][next_col] - map[row][col] == 1) {
				visit(next_row, next_col, cnt + 1);
				found = true;
			}
		}
		// 종료 조건 : 더이상 갈 곳이 없을 때 -> 1차이 나는 곳이 없을 때
		if (!found) {
			if (sum <= cnt) {
				sum = cnt;
				result[numbers[0]] = cnt;
			}
			return;
		}
	}
}
