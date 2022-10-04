package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 스도쿠
public class BaekJoon2239 {

	static int[][] map;
	static List<Point> p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[10][10];
		p = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			String str = br.readLine();
			for (int j = 1; j <= 9; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
				if (map[i][j] == 0)
					p.add(new Point(i, j));
			}
		}
		make(map, 0);
		print(map);
	}

	private static void make(int[][] map, int curInd) {
		// 종료조건
		if (curInd == p.size()) {
			print(map);
			System.exit(0);
		}
		// 재귀 연산
		Point cur = p.get(curInd);
		List<Integer> numbers = setNumbers(map, cur.x, cur.y);
		if (numbers.size() == 0) {
			return;
		}
		int temp = map[cur.x][cur.y];
		for (int i = 0; i < numbers.size(); i++) {
			map[cur.x][cur.y] = numbers.get(i);
			// 다음 재귀
			make(map, curInd + 1);
			map[cur.x][cur.y] = temp;
		}
	}

	// 없는 숫자들의 배열 리턴
	private static List<Integer> setNumbers(int[][] map, int r, int c) {
		List<Integer> list = new ArrayList<>();
		// 가로 검사
		boolean[] isIn = new boolean[10];
		for (int j = 1; j <= 9; j++) {
			isIn[map[r][j]] = true;
		}
		// 세로 검사
		for (int j = 1; j <= 9; j++) {
			isIn[map[j][c]] = true;
		}
		// 사각형 검사
		int rangeR = (r - 1) / 3 * 3 + 1; // 1 4 7 1,2,3->1 4,5,6->2
		int rangeC = (c - 1) / 3 * 3 + 1;
		for (int i = rangeR; i < rangeR + 3; i++) {
			for (int j = rangeC; j < rangeC + 3; j++) {
				isIn[map[i][j]] = true;
			}
		}
		for (int i = 1; i <= 9; i++) {
			if (!isIn[i])
				list.add(i);
		}
		return list;
	}

	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
