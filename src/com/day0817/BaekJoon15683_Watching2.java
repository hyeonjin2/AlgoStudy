package com.day0817;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon15683_Watching2 {

	// cctv 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// cctv 별로 탐색해야하는 델타 배열의 인덱스
	static int[][] cctv1 = { { 0 }, { 1 }, { 2 }, { 3 } };
	static int[][] cctv2 = { { 0, 1 }, { 2, 3 }, { 0, 1 }, { 2, 3 } };
	static int[][] cctv3 = { { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };
	static int[][] cctv4 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } };
	static int[][] cctv5 = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

//	static class Point {
//		int r, c;
//
//		public Point(int r, int c) {
//			this.r = r;
//			this.c = c;
//		}
//	}

	// cctv 4방 검색해서 가장 많이 볼 수 있는 곳을 고른다.?
	static class CCTV { // cctv의 번호와 좌표를 저장
		int id;
		int max;
		int[][] dir; // 탐색해야하는 배열의 인덱스
		int[] selected; // 선택된 탐색 인덱스
		Point point;

		public CCTV(int id, Point point) {
			this.id = id;
			this.point = point;
			if (id == 1) {
				dir = cctv1;
			} else if (id == 2) {
				dir = cctv2;
			} else if (id == 3) {
				dir = cctv3;
			} else if (id == 4) {
				dir = cctv4;
			} else if (id == 5) {
				dir = cctv5;
			}
		}
	}

	static int n, m, Ans, min;
	static int[][] map;
	static int[][] result;
	static List<CCTV> cctvs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cctvs = new ArrayList<>(); // cctv의 정보를 담을 배열

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int tmp = st.nextToken().charAt(0) - '0';
				map[i][j] = tmp;
				if (tmp != 0 && tmp != 6) {
					cctvs.add(new CCTV(tmp, new Point(i, j)));
				}
			}
		}
		result = new int[n][m];
		order = new int[cctvs.size()];
		min = Integer.MAX_VALUE;
		perm(0);
		System.out.println(min);
	}

	static int cnt;
	static int[] order;

	// cctv 뱡향 중복 순열 생성 메소드
	private static void perm(int cnt) {
		if (cnt == cctvs.size()) {
			Ans++;
			for (int i = 0; i < cctvs.size(); i++) {
				CCTV c = cctvs.get(i);
				c.selected = c.dir[order[i]];
			}
			search();
			min = Math.min(min, count(result));
			return;
		}
		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			perm(cnt + 1);
		}
	}

	// 조합이 생성될 때마다 cctv의 방향 탐색을 하는 메소드
	private static void search() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = map[i][j];
			}
		}
		for (CCTV cctv : cctvs) {
			int[] dir = cctv.selected;
			Point p = cctv.point;
			// 방향 인덱스 배열 크기만큼 탐색
			for (int d = 0; d < dir.length; d++) {
				int count = 1;
				// 가로 탐색
				int nr = p.x + dr[dir[d]] * count;
				int nc = p.y + dc[dir[d]] * count;
				while (nr >= 0 && nr < n && nc >= 0 && nc < m) {
					// 배열의 범위를 벗어나면 건너뛰기
					// 벽을 만나면 탐색 가능한 거리와 방향 기억 후 반복문 탈출
					if (map[nr][nc] == 6) {
						break;
					}
					// 벽을 만나지 않고 배열의 범위 안이면 계속 탐색
					result[nr][nc] = -1;
					count++;
					nr = p.x + dr[dir[d]] * count;
					nc = p.y + dc[dir[d]] * count;
				}
			}
		}
	}

	// 0의 개수를 세는 메소드
	private static int count(int[][] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0)
					count++;
			}
		}
		return count;
	}
}
