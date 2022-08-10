package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BaekJoon16926_MarixRotation {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // row 크기
		int M = Integer.parseInt(st.nextToken()); // col 크기
		int R = Integer.parseInt(st.nextToken()); // 회전 횟수
		// matrix 배열 초기화
		int[][] matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = Math.min(N, M) / 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < num; j++) {
				int tmp = matrix[j][j];
				for (int k = j + 1; k < M - j; k++) {
					matrix[j][k - 1] = matrix[j][k];
				}
				for (int k = j + 1; k < N - j; k++) {
					matrix[k - 1][M - j - 1] = matrix[k][M - j - 1];
				}
				for (int k = M - j - 2; k >= j; k--) {
					matrix[N - j - 1][k + 1] = matrix[N - 1 - j][k];
				}
				for (int k = N - j - 2; k >= j; k--) {
					matrix[k + 1][j] = matrix[k][j];
				}
				matrix[j + 1][j] = tmp;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
