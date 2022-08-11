package com.day0810;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JUN16926_ArrayRotation {

	// 우상좌하 방향 순으로 이동(반시계방향) <-, ^, ->, v 순으로 이동
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map, result;
	static int N, M, K;
	static boolean[] isSelected;
	static String[] selOper;
	static String[] operator;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();// 행
		M = sc.nextInt();// 열
		K = sc.nextInt();// 회전횟수

		map = new int[N][M];
		result = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.nextLine();
		operator = new String[K];
		for (int i = 0; i < K; i++) {
			operator[i] = sc.nextLine();
		}

		isSelected = new boolean[K];
		selOper = new String[K];
		perm(0);

//		for (int r = 0; r < K; r++) {// 회전 횟수
//			for (int g = 0; g < S; g++) {// 그룹수 만큼
//
//				// 그룹별 시작위치(g)-> (0,0), (1,1), (2,2)~
//				int x = g;
//				int y = g;
//
//				int startValue = map[x][y];// 한 그룹의 시작값은 따로 저장
//
//				// 현재 시작 위치는 x,y
//				int dir = 0;// delta 배열의 인덱스
//				while (dir < 4) {
//					int nx = x + dx[dir];
//					int ny = y + dy[dir];
//					if (nx >= g && ny >= g && nx < N - g && ny < M - g) {// 그룹 안쪽이라면
//						map[x][y] = map[nx][ny];// 새위치에 있는 값이 이전위치로 이사 옴
//						x = nx;
//						y = ny;
//
//					} else
//						dir++;// 방향 바꿔 줌
//				} // while
//				map[g][g + 1] = startValue;
//			} // 그룹 한개 끝남
//		} // 전체 회전 횟수

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	private static void perm(int cnt) {
		if (cnt == K) {
			calc();
			System.out.println(Arrays.toString(selOper));
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					sb.append(result[i][j] + " ");
//				}
//				sb.append("\n");
//			}
//			System.out.println(sb);
			return;
		}
		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			selOper[cnt] = operator[i];
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void calc() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(selOper[i]);
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			for (int g = 0; g < s; g++) {// 그룹수 만큼

				// 그룹별 시작위치(g)->(r-s+g, c-s+g)
				int n = r - s + g;
				int m = c - s + g;
				int x = n;
				int y = m;

				int startValue = result[x][y];// 한 그룹의 시작값은 따로 저장

				// 현재 시작 위치는 x,y
				int dir = 0;// delta 배열의 인덱스
				while (dir < 4) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if (nx >= n && ny >= m && nx < (n + 2 * s) && ny < (m + 2 * s)) {// 그룹 안쪽이라면
						System.out.println(nx + " " + ny);
						result[x][y] = result[nx][ny];// 새위치에 있는 값이 이전위치로 이사 옴
						x = nx;
						y = ny;

					} else
						dir++;// 방향 바꿔 줌
				} // while
				result[g][g + 1] = startValue;
			} // 그룹 한개 끝남
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					sb.append(result[k][j] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
		}
	}
}
