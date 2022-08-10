package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon17406_MatrixRotation {

	// 회전 델타 배열 하,우,상,좌
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Queue<Integer> list;
	static boolean[] isSelected;
	static String[] selOper;
	static int[][] matrix;
	static int[][] result;
	static String[] operators;
	static boolean[][] visited;
	static boolean[][] selected;
	static int N, M, K, size;
	static int n, m;
	static int r, c, s;
	static int dir, sum;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // row 크기
		M = Integer.parseInt(st.nextToken()); // col 크기
		K = Integer.parseInt(st.nextToken()); // 회전 횟수
		// matrix 배열 초기화
		matrix = new int[N][M];
		result = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				matrix[i][j] = tmp;
				result[i][j] = tmp;
			}
		}
		// 회전 수 만큼 회전 정보 받아오기
		operators = new String[K];
		for (int i = 0; i < K; i++) {
			operators[i] = br.readLine();

		}
		isSelected = new boolean[K];
		selOper = new String[K];
		perm(0);
		System.out.println(min);
	}

	private static void perm(int cnt) {
		if (cnt == K) {
			calc();
			return;
		}
		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;
			selOper[cnt] = operators[i];
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void calc() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(selOper[i]);
			r = Integer.parseInt(st.nextToken()) - 1; // 회전 시킬 row : r-s ~ r+s 0 ~ 4
			c = Integer.parseInt(st.nextToken()) - 1; // 회전 시킬 col : c-s ~ c+s 1 ~ 5
			s = Integer.parseInt(st.nextToken()); // 회전 시킨 배열의 size 한변의 크기 -> 2*s+1

			visited = new boolean[N][M];
			selected = new boolean[N][M];
			for (int j = 0; j < s; j++) {
				dir = 0;
				size = 8 * (s - j);
				n = r - s + j;
				m = c - s + j;
				list = new ArrayDeque<>();
				setList(n, m, 0);
				rotation();
				getResult(n, m);
			}
		}
		// 배열의 최솟값 구하기
		for (int i = 0; i < N; i++) {
			sum = 0;
			for (int j = 0; j < M; j++) {
				sum += result[i][j];
			}
			min = Math.min(min, sum);
		}
	}

	// matrix 배열을 회전한 숫자들로 채우는 함수
	private static void getResult(int row, int col) {
		if (list.isEmpty()) {
			return;
		}
		result[row][col] = list.poll();
		selected[row][col] = true;
		int next_row = row + dr[dir];
		int next_col = col + dc[dir];
		if (next_row < n || next_row >= n + 2 * s + 1 || next_col < m || next_col >= m + 2 * s + 1
				|| selected[next_row][next_col]) {
			dir = (dir + 1) % 4;
			next_row = row + dr[dir];
			next_col = col + dc[dir];
		}
		getResult(next_row, next_col);
	}

	// list를 회전시키는 함수
	private static void rotation() {
		int tmp = list.peek();
		list.poll();
		list.offer(tmp);
	}

	// cycle의 숫자들을 담는 배열 list를 만드는 함수
	private static void setList(int row, int col, int cnt) {
		if (cnt == size) {
			return;
		}
		list.add(result[row][col]);
		visited[row][col] = true;
		int next_row = row + dr[dir];
		int next_col = col + dc[dir];
		if (next_row < n || next_row >= n + 2 * s + 1 || next_col < m || next_col >= m + 2 * s + 1
				|| visited[next_row][next_col]) {
			dir = (dir + 1) % 4;
			next_row = row + dr[dir];
			next_col = col + dc[dir];
		}
		setList(next_row, next_col, cnt + 1);
	}
}
