package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SW1873_Battle {
	static char[][] field;
	// 동작에 대한 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int curr_row = 0;
	static int curr_col = 0;
	static int dir = 0; // 앞으로 움직여야할 방향 0,1,2,3 -> 상,하,좌,우
	static char[] train = { '^', 'v', '<', '>' };

	static void trainMove() {
		int next_row;
		int next_col;
		// 전차 이동
		field[curr_row][curr_col] = '.'; // 전차의 위치 평지로 바꾸기
		next_row = curr_row + dr[dir];
		next_col = curr_col + dc[dir];

		// 만약 전차의 이동이 맵을 벗어나면 이동 X
		if (next_row < 0 || next_row >= field.length || next_col < 0 || next_col >= field[0].length) {
			field[curr_row][curr_col] = train[dir];
			return;
		}
		// 앞에 방해물이 있으면 이동 X
		if (field[next_row][next_col] == '*' || field[next_row][next_col] == '#' || field[next_row][next_col] == '-') {
			field[curr_row][curr_col] = train[dir];
			return;
		}
		curr_row = next_row;
		curr_col = next_col;
		field[curr_row][curr_col] = train[dir];
	}

	static void shoot() {
		int next_row;
		int next_col;
		boolean find = false;
		int count = 1;

		while (!find) {
			next_row = curr_row + dr[dir] * count;
			next_col = curr_col + dc[dir] * count;

			// 만약 포탄의 이동이 맵을 벗어나면 부수기X
			if (next_row < 0 || next_row >= field.length || next_col < 0 || next_col >= field[0].length) {
				break;
			}
			// 앞에 강철벽이나 물이 있으면 못 부숨
			if (field[next_row][next_col] == '#') {
				break;
			}
			// 앞에 돌벽이 있는 경우 벽을 평지로 만듦
			if (field[next_row][next_col] == '*') {
				field[next_row][next_col] = '.';
				find = true;
			}
			// 못 찾은 경우
			count++;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // max row
			int M = Integer.parseInt(st.nextToken()); // max col
			field = new char[N][M];
			
			HashMap<Character, Integer> shape = new HashMap<>(); // 전차모양에 따른 방향을 저장하는 map
			shape.put('^', 0);
			shape.put('v', 1);
			shape.put('<', 2);
			shape.put('>', 3);

			// 필드 입력 받기, 전차 찾기
			for (int i = 0; i < N; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < M; j++) {
					field[i][j] = tmp.charAt(j);
					if (shape.containsKey(field[i][j])) { // 좌
						curr_row = i;
						curr_col = j;
						dir = shape.get(field[i][j]);
					}
				}
			}
			// 동작 입력 받기
			int num = Integer.parseInt(br.readLine());
			String move = br.readLine();
			for (int i = 0; i < num; i++) {
				switch(move.charAt(i)) {
				case 'U':
					dir = 0;
					trainMove();
					break;
				case 'D':
					dir = 1;
					trainMove();
					break;
				case 'L':
					dir = 2;
					trainMove();
					break;
				case 'R':
					dir = 3;
					trainMove();
					break;
				case 'S':
					shoot();
					default:
						break;
				}
			}
			// 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sb.append(field[i][j]);
				}
				sb.append("\n");
			}
			System.out.print("#" + tc + " ");
			System.out.print(sb);
		}
	}
}
