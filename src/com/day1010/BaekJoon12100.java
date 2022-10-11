package com.day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2048 (Easy)
public class BaekJoon12100 {

	static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print(map);
		for (int d = 0; d < 4; d++) {
			move(d);
		}
	}

	// 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 블럭들을 d방향으로 이동시키는 메소드
	private static void move(int d) {
		// 이동된 map을 임시 저장하기 위한 2차원 배열
		int[][] copy = new int[N][N];
		copy(copy, map);
		
		copy(map, copy);
	}

	private static void copy(int[][] copy, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
