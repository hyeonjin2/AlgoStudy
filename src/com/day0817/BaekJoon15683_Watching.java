package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon15683_Watching {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// cctv 4방 검색해서 가장 많이 볼 수 있는 곳을 고른다.?
	static class CCTV { // cctv의 번호와 좌표를 저장
		int id;
		int max;
		int[] dir; // 최대 영역을 볼 때의 방향
		Point point;

		public CCTV(int id, Point point) {
			this.id = id;
			this.point = point;
		}
	}

	static int n, m;
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
		search();
	}

	// cctv 4방 탐색 델타 배열
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int cnt;

	// 0:상, 1:하, 2:좌, 3:우
	private static void search() {
		// cctv마다 얼마나 멀리 갈 수 있는지 체크, 개수와 방향 기억
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = map[i][j];
			}
		}
		for (CCTV cctv : cctvs) {
			find(cctv);
			System.out.println(cctv.id + Arrays.toString(cctv.dir));
			print(result);
		}
		int min = n * m;
		min = Math.min(min, count(result));
		System.out.println(min);
	}

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

	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// line별로 0이 얼마나 있는지 확인
	private static void find(CCTV cctv) {
		Point p = cctv.point;
		// 가로방향
		int[] dir = new int[4];
		int max = 0;
		for (int d = 0; d < 4; d++) {
			int count = 1;
			// 가로 탐색
			int nr = p.r + dr[d] * count;
			int nc = p.c + dc[d] * count;
			while (nr >= 0 && nr < n && nc >= 0 && nc < m) {
				// 배열의 범위를 벗어나면 건너뛰기
				// 벽을 만나면 탐색 가능한 거리와 방향 기억 후 반복문 탈출
				if (map[nr][nc] == 6) {
					dir[d] = count - 1;
					break;
				}
				// 벽을 만나지 않고 배열의 범위 안이면 계속 탐색
				result[nr][nc] = -1;
				count++;
				nr = p.r + dr[d] * count;
				nc = p.c + dc[d] * count;
				dir[d] = count - 1;
			}
		}
		cctv.dir = dir;
	}
}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
