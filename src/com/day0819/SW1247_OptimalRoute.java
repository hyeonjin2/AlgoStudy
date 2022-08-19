package com.day0819;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것
2. N명의 고객을 어떤 순서로 방문해야 할까? --> 순열
*/
public class SW1247_OptimalRoute {
	static Point home, company;
	static Point[] customers;

	static int N, answer;
	static int[] selected; // 순열 생성 위해서. 고객 방문 순서가 저장됨.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine()); // 고객 수
			customers = new Point[N];
			selected = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 회사
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Point(x, y);

			// 집
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y);

			// 고객
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customers[i] = new Point(x, y);
			}

			// 고객 방문 순서를 정하기 위해서 초기값 지정
			for (int i = 0; i < N; i++) {
				selected[i] = i; // 0,1,2,3,4...
			}
			answer = Integer.MAX_VALUE;
			// 거리를 계산
			do {
				int tmp = calc(selected);
				answer = Math.min(answer, tmp);
			} while (np(selected));

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	// selected 배열에 들어 있는 고객 순서대로 방문해본 뒤 거리 리턴
	private static int calc(int[] selected) {
		int sum = 0;
		// 회사~첫 고객 거리
		sum += getDistance(company, customers[selected[0]]);

		// 고객 간 거리
		int i = 0;
		for (i = 0; i < selected.length - 1; i++) { // 마지막 고객 전까지
			int from = selected[i];
			int to = selected[i + 1];

			sum += getDistance(customers[from], customers[to]);

		}

		// 마지막 고객~집 거리
		sum += getDistance(customers[selected[i]], home);

		return sum;
	}

	private static int getDistance(Point p1, Point p2) {

		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	private static boolean np(int[] selected) {
		// 1.top 찾기(제일 큰수) -> i
		int i = N - 1;
		while (i > 0 && selected[i - 1] >= selected[i]) {
			i--;
		}
		if (i == 0) // 마지막 순열의 경우
			return false;

		// 2.i-1위치와 교환할 다음 수를 뒷쪽에서부터 체크하면서 찾기 -> j
		int j = N - 1;
		while (selected[i - 1] >= selected[j]) {
			j--;
		}

		// 3.i-1위치와 j위치 교환
		swap(selected, i - 1, j);

		// 4.i(top)위치부터 맨뒤까지 오름차순 정렬해서 가장 작은 수 만들기
		int k = N - 1;
		while (i < k) {
			swap(selected, i++, k--);
		}

		return true;
	}

	private static void swap(int[] selected, int i, int j) {
		int tmp = selected[i];
		selected[i] = selected[j];
		selected[j] = tmp;
	}

}
