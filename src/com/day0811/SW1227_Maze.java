package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW1227_Maze {

	static char[][] map;
	static final int N = 100;
	static boolean[][] visited;
	static int result = 0;
	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine(); // testcase 번호 입력 넘어가기
			// 입력 받기
			map = new char[N][N];
			Point start = null;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
					// 시작점 찾기
					if (map[i][j] == '2') {
						start = new Point(i, j);
					}
				}
			}
			result = 0;
			visited = new boolean[N][N];
			dfs(start);

			System.out.println("#" + tc + " " + result);
		}
	}

	private static void dfs(Point start) {
		Stack<Point> stack = new Stack<>(); // 4방 탐색 후 해당자리가 0인 인덱스를 스택에 저장

		stack.push(start);// 루트노드 인덱스부터
		visited[start.r][start.c] = true;
		while (!stack.isEmpty()) { // 방문대상이 있을때까지 반복
			Point curr = stack.pop(); // 방문차례인 대상 정보 꺼내기
			// 대상의 값이 3이면 값 리턴
			if (map[curr.r][curr.c] == '3') {
				result = 1;
				break;
			}
			// 방문해서 해야할 일 처리 : 4방 탐색을 해서 주변에 0을 가진 인덱스를 스택에 저장
			for (int k = 0; k < 4; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];
				// 벽이 아니고 배열의 범위 안이면 다음 인덱스 탐색
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != '1') {
					visited[nr][nc] = true;
					stack.push(new Point(nr, nc));
				}
			}
		}
	}
}
