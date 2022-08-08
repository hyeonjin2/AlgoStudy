package com.day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BaekJoon6198_Rooftop {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> floors = new Stack<>();
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			if (floors.isEmpty()) {
				floors.push(number);
				continue;
			}
			if (floors.peek() > number) {
				cnt += floors.size();
				floors.push(number);
			} else {
				while (!floors.isEmpty()) {
					if (floors.peek() > number)
						break;
					floors.pop();
				}
				cnt += floors.size();
				floors.push(number);
			}
		}
		System.out.println(cnt);
	}
}
