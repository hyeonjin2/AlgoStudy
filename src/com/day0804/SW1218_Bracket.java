package com.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW1218_Bracket {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			Stack<Character> stack = new Stack<>();
			int length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			for (int i = 0; i < length; i++) {
				if (!stack.isEmpty()) {
					if (str.charAt(i) == ')' && stack.peek() == '(') {
						stack.pop();
					} else if (str.charAt(i) == ']' && stack.peek() == '[') {
						stack.pop();
					} else if (str.charAt(i) == '}' && stack.peek() == '{') {
						stack.pop();
					} else if (str.charAt(i) == '>' && stack.peek() == '<') {
						stack.pop();
					} else {
						stack.push(str.charAt(i));
					}
				} else {
					stack.push(str.charAt(i));
				}
			}
			int result = stack.isEmpty() ? 1 : 0;
			System.out.println("#" + tc + " " + result);
		}
	}
}
