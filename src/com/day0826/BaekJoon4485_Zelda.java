package com.day0826;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon4485_Zelda {

	static int N, Ans;
	static boolean[][] visited;
	static int[][] map, D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			cnt++;
			StringTokenizer st;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N];
			D = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			Ans = Integer.MAX_VALUE;
			search(new Point(0, 0));
			sb.append("Problem ").append(cnt).append(": ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	private static void search(Point start) {
		// 출발 정점 처리
		D[start.x][start.y] = map[start.x][start.y];
		int min;
		Point minPoint;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				min = Integer.MAX_VALUE;
				minPoint = new Point(-1, -1);
				// step1. 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
				// 방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
				for (int d = 0; d < 5; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (!visited[nx][ny] && min > D[nx][ny]) {
						min = D[nx][ny];
						minPoint = new Point(nx, ny);
					}
				}
				if (minPoint.x == -1 || minPoint.y == -1)
					continue;
				// 방문 처리
				visited[minPoint.x][minPoint.y] = true;
				System.out.println(i + " " + j + minPoint);
				D[minPoint.x][minPoint.y] = min;
				print(D);
				// 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
				for (int d = 1; d < 5; d++) {
					int nx = minPoint.x + dx[d];
					int ny = minPoint.y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (!visited[nx][ny] && D[nx][ny] > D[minPoint.x][minPoint.y] + map[nx][ny]) {
						D[nx][ny] = D[minPoint.x][minPoint.y] + map[nx][ny];
					}
				}
			}
		}
		Ans = D[N - 1][N - 1];
	}

	static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
/*








*/