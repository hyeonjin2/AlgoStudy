package com.day0826;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 미세먼지 안녕!
public class BaekJoon17144_FindDust {

	static int N, M, T;
	static Queue<Point> queue;
	static List<Point> object;
	static int[][] room;
	static int[][] copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		room = new int[N][M];
		copy = new int[N][M];
		object = new ArrayList<>();
		queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = room[i][j];
				if (room[i][j] > 0) {
					queue.offer(new Point(i, j));
				} else if (room[i][j] < 0) {
					object.add(new Point(i, j));
				}
			}
		}
		while (T > 0) {
			// step1. 미세먼지 확산
			copyArr(copy, room);
			bfs();
//			print(copy);
			// step2.
			// 공기청정기 작동 -> 미세먼지가 한칸씩 이동
			copyArr(room, copy);
			work();
//			print(room);
			queue.clear();
			// 미세먼지 큐에 넣기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (room[i][j] > 0) {
						queue.offer(new Point(i, j));
					}
				}
			}
			// T만큼 반복하기
			T--;
		}
		// 미세먼지 양 계산하기
		long result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (room[i][j] > 0) {
					result += (long) room[i][j];
				}
			}
		}
		System.out.println(result);
//		print(room);
	}

	private static void copyArr(int[][] room, int[][] copy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				room[i][j] = copy[i][j];
			}
		}
	}

	private static boolean inArray(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= M)
			return false;
		return true;
	}

	// 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 공기청정기 작동 -> 미세먼지가 한칸씩 이동
	private static void work() {
		Point up = object.get(0); // 공기청정기의 위쪽 좌표
		Point down = object.get(1); // 공기청정기의 아래쪽 좌표
		// 행 : 0~up.x, 열:0~M 까지 시계방향 이동
		for (int i = 0; i < down.x; i++) {
			for (int j = 0; j < M; j++) {
				int nx, ny;
				if (i == up.x && j == up.y) {
					room[i][j + 1] = 0;
					continue;
				}
				// 0번째 행은 왼쪽으로 이동
				if (i == 0) {
					nx = i + dx[2];
					ny = j + dy[2];
					// 배열의 범위 밖이면 아래로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[1];
						ny = j + dy[1];
					}
					// 0번째 열은 아래로 이동
				} else if (j == 0) {
					nx = i + dx[1];
					ny = j + dy[1];
					// 공기청정기를 만나면 정화
					if (copy[nx][ny] == -1 && room[i][j] != -1) {
						continue;
					}
					// M-1번째 열은 위로 이동
				} else if (j == M - 1) {
					nx = i + dx[0];
					ny = j + dy[0];
					// 배열의 범위 밖이면 오른쪽으로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[3];
						ny = j + dy[3];
					}
					// 공기청정기와 같은 줄이면 오른쪽으로 이동
				} else if (i == up.x) {
					nx = i + dx[3];
					ny = j + dy[3];
					// 배열의 범위 밖이면 위로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[0];
						ny = j + dy[0];
					}

					// 나머지는 가만히
				} else {
					nx = i;
					ny = j;
				}
				// copy에 있는 미세먼지를 room에 덮어씌우기
				room[nx][ny] = copy[i][j];
			}
		}
		// 행:down.x~N, 열:0~M까지 반시계방향 이동
		for (int i = down.x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int nx, ny;
				if (i == down.x && j == down.y) {
					room[i][j + 1] = 0;
					continue;
				}
				// 공기청정기와 같은 줄이면 오른쪽으로 이동
				if (i == down.x) {
					nx = i + dx[3];
					ny = j + dy[3];
					// 배열의 범위 밖이면 아래로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[1];
						ny = j + dy[1];
					}
					// 0번째 열은 위로 이동
				} else if (j == 0) {
					nx = i + dx[0];
					ny = j + dy[0];
					// 공기청정기를 만나면 정화
					if (copy[nx][ny] == -1 && room[i][j] != -1) {
						continue;
					}
					// M-1번째 열은 아래로 이동
				} else if (j == M - 1) {
					nx = i + dx[1];
					ny = j + dy[1];
					// 배열의 범위 밖이면 왼쪽으로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[2];
						ny = j + dy[2];
					}
					// N번째 줄이면 왼쪽으로 이동
				} else if (i == N - 1) {
					nx = i + dx[2];
					ny = j + dy[2];
					// 배열의 범위 밖이면 위로 이동
					if (!inArray(nx, ny)) {
						nx = i + dx[0];
						ny = j + dy[0];
					}

					// 나머지는 가만히
				} else {
					nx = i;
					ny = j;
				}
				// copy에 있는 미세먼지를 room에 덮어씌우기
				room[nx][ny] = copy[i][j];
			}
		}
	}

	// 미세먼지 4방 동시 확산 -> BFS 메소드
	private static void bfs() {
		while (!queue.isEmpty()) {
			// 미세먼지 확산 -> 미세먼지가 있는 칸에서 동시에 일어남
			Point cur = queue.poll();
			int before = room[cur.x][cur.y];
			int count = 0; // 확산되는 공간의 수
			// 미세먼지는 인접한 4방향으로 확산
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				// 인접한 방향에 공기청정기가 있거나, 배열의 범위 밖이면 확산X
				if (inArray(nx, ny) && room[nx][ny] != -1) {
					// 확산되는 양 미세먼지의 양/5
					copy[nx][ny] += before / 5;
					count++;
				}
			}
			// 남은 미세먼지 = 원래 미세먼지 - 미세먼지/5*확산된 개수
			copy[cur.x][cur.y] -= before / 5 * count;
			if (copy[cur.x][cur.y] < 0)
				copy[cur.x][cur.y] = 0;
		}
	}

	// 2차원 배열 출력 메소드
	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
