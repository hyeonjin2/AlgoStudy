package com.day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마
public class BaekJoon20440 {
	static class Mosquito implements Comparable<Mosquito> {
		int start, end;

		public Mosquito(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Mosquito o) {
			return this.start - o.start;
		}
	}

	static Mosquito[] mosquitos;
	static int[] time;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		mosquitos = new Mosquito[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			mosquitos[i] = new Mosquito(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(mosquitos);
		time = new int[2_100_000_001];
		for (int i = 0; i < N; i++) {
			Mosquito cur = mosquitos[i];
		}
	}
}
