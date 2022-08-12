package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int r;
	int c;
	int d;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BaekJoon15686_Chicken {

	static int N, M;
	static int[][] map;
	static List<Point> chicken;
	static Point[] selected;
	static List<Point> client;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // matrix 크기
		M = Integer.parseInt(st.nextToken()); // 남겨야할 치킨집의 수
		map = new int[N][N];
		chicken = new ArrayList<>();
		client = new ArrayList<>();
		// map 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 치킨 집을 찾으면 인덱스를 저장한다.
				if (map[i][j] == 2) {
					chicken.add(new Point(i, j));
				} else if (map[i][j] == 1) {
					client.add(new Point(i, j));
				}
			}
		}
		// 남겨둘 치킨집을 뽑는다.
		selected = new Point[M]; // M개 남겨둔다.
		result = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(result);
	}

	private static void comb(int cnt, int start) {
		// 종료조건 M개를 뽑았을 때
		if (cnt == M) {
			// 도시의 치킨 거리 구하기
			// 각 가정집에서의 치킨 거리 구하기

			result = result > calc2() ? calc2() : result;
			return;
		}
		for (int i = start; i < chicken.size(); i++) { // 치킨집의 수 만큼 for문을 돌린다.
			selected[cnt] = chicken.get(i);
			comb(cnt + 1, i + 1);// *********************
		}
	}

	private static int calc2() {
		int sum = 0;
		for (int i = 0; i < client.size(); i++) {
			int tmp = Integer.MAX_VALUE;
			for (int j = 0; j < selected.length; j++) {
				Point c = client.get(i);
				Point s = selected[j];
				int diff = Math.abs(c.r - s.r) + Math.abs(c.c - s.c);
				tmp = tmp > diff ? diff : tmp;
			}
			sum += tmp;
		}
		return sum;
	}
}
