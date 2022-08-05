package com.day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon2493_Top {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> numbers = new Stack<>();
		Stack<Integer> idx = new Stack<>();
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (numbers.isEmpty()) {
				numbers.push(num);
				idx.push(0);
				continue;
			}
			if (numbers.peek() < num) {
				numbers.pop();
				idx.pop();
				numbers.push(num);
				idx.push(i);
			}else {
				
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(idx.pop());
		}
		
	}
}
