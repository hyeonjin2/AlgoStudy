package com.day0810;

import java.util.Scanner;

public class BaekJoon17406_MatrixRotation2 {

	// 우상좌하 방향 순으로 이동(반시계방향) 좌, 상, 우, 하 <-, ^, ->, v 순으로 이동
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();// 행
		int M = sc.nextInt();// 열
		int R = sc.nextInt();// 회전횟수

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int group = Math.min(N, M) / 2;
		for (int r = 0; r < R; r++) {// 회전 횟수
			for (int g = 0; g < group; g++) {// 그룹수 만큼

				// 그룹별 시작위치(g)-> (0,0), (1,1), (2,2)~
				int x = g;
				int y = g;

				int startValue = map[x][y];// 한 그룹의 시작값은 따로 저장

				// 현재 시작 위치는 x,y
				int dir = 0;// delta 배열의 인덱스
				while (dir < 4) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if (nx >= g && ny >= g && nx < N - g && ny < M - g) {// 그룹 안쪽이라면
						map[x][y] = map[nx][ny];// 새위치에 있는 값이 이전위치로 이사 옴
						x = nx;
						y = ny;

					} else
						dir++;// 방향 바꿔 줌
				} // while
				map[g + 1][g] = startValue;
			} // 그룹 한개 끝남
		} // 전체 회전 횟수

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
