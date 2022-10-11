package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 활주로 건설
public class SW4014 {

	static int N, L, Ans;
	static int[][] map1, map2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map1 = new int[N][N];
			map2 = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map1[i][j] = Integer.parseInt(st.nextToken());
					map2[j][i] = map1[i][j];
				}
			}
//			print(map);
			// 활주로를 건설할 수 있는 경우
			// 높이가 다 같다
			// 높이 차이가 1이고 낮은 높이의 칸이 L이상이다.
			// 333223 이런식으로 다시 높아지는 경우는 불가능
			// 가로 세로 2가지 방향으로 세울수 있다.
			// 행,열 한 줄 모두 활주로가 되는 경우 카운트 한다.
			// 세로 가로 검사 dfs
			Ans = 0;
			for (int i = 0; i < N; i++) {
				if (check(map1[i]))
					Ans++;
				if (check(map2[i]))
					Ans++;
			}
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean check(int[] map) {
		int before = map[0];
		int cnt = 0;
		int j = 0;
		while (j < N) {
			// 높이가 같으면 cnt증가
			if (before == map[j]) {
				cnt++;
				j++;
			}
			// 이전 높이보다 1 높으면
			else if (before + 1 == map[j]) {
				if (cnt < L)
					return false;
				before++;
				cnt = 1;
				j++;
			}
			// 이전 높이보다 1 낮으면
			else if (before - 1 == map[j]) {
				int count = 0;
				for (int k = j; k < N; k++) {
					if (map[k] != before - 1) {
						return false;
					}
					if (++count == L)
						break;
				}
				if (count < L)
					return false;
				before--;
				j += L;
				cnt = 0;
			} else {
				return false;
			}
		}
		return true;
	}

//	// 델타배열 우,하
//	static int[] dr = { 0, 1 };
//	static int[] dc = { 1, 0 };
//
//	private static void dfs(int r, int c, int d, int cnt, int max) {
//		// 끝에 도달했으면 리턴
//		if ((c == N && d == 0) || (r == N && d == 1)) {
//			if (cnt >= L) {
////				System.out.println("final r:" + r + " c:" + c + " d:" + d + " cnt:" + cnt);
//				if (d == 0) {
//					result[d][r] = true;
//				} else {
//					result[d][c] = true;
//				}
//			}
//			return;
//		}
//		if (r == N || c == N)
//			return;
//		max = Math.max(max, map1[r][c]);
////		System.out.println("r:" + r + " c:" + c + " d:" + d + " cnt:" + cnt);
//		// cnt가 경사로 길이보다 크면 Ans 증가시키기
//		int nr = r + dr[d];
//		int nc = c + dc[d];
//		if (map1[nr][nc] == 0) {
//			dfs(nr, nc, d, cnt, max);
//			return;
//		}
//		int diff = map1[r][c] - map1[nr][nc];
//		// 차이가 1이라면 재귀 넘기기
//		if (diff == 1) {
//			if (max > map1[r][c] && cnt < L) {
//				return;
//			}
//			dfs(nr, nc, d, 1, max);
//		}
//		// 차이가 -1인데 이전의 길이가 L미만이면 활주로가 안되는 경우 이므로 리턴하기
//		else if (diff == -1) {
//			if (cnt < L) {
//				return;
//			} else {
//				if (d == 0) {
//					result[d][r] = true;
//				} else {
//					result[d][c] = true;
//				}
//			}
//			dfs(nr, nc, d, 1, max);
//		}
//		// 차이가 0이면 cnt증가 시키기
//		else if (diff == 0) {
//			dfs(nr, nc, d, cnt + 1, max);
//		}
//	}

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
