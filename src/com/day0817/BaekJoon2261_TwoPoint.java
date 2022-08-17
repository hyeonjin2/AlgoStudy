package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2261_TwoPoint {

	static class Point implements Comparable<Point> {
		long r;
		long c;

		public Point(long r, long c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			return (int) (this.r != o.r ? this.r - o.r : this.c - o.c);
		}
	}

	static int N, min;
	static Point[] points;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		points = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		long min = Long.MAX_VALUE;
		Arrays.sort(points);
		for (int i = 0; i < N; i++) {
			int start = i;
			int end = N - 1;
			while (start <= end) {
				Point sp = points[start];
				Point ep = points[end];
				long r = (long) Math.pow(sp.r - ep.r, 2);
				long c = (long) Math.pow(sp.c - ep.c, 2);
				min = Math.min(min, r + c);
				start++;
				end--;
			}
		}
//		int start = 0;
//		int end = N - 1;
//		while (start <= end) {
//			Point sp = points[start];
//			Point ep = points[end];
//			long r = (long) Math.pow(sp.r - ep.r, 2);
//			long c = (long) Math.pow(sp.c - ep.c, 2);
//			min = Math.min(min, r + c);
//			start++;
//			end--;
//		}
		System.out.println(min);
	}

//	private static void binarySearch(Point target, int start, int end) {
//		if (start == end)
//			return;
//		int mid = (start + end) / 2;
//		if (target points[mid]) {
//			binarySearch(start, mid);
//		} else {
//			binarySearch(mid + 1, end);
//		}
//	}

}
