package com.day0806;

import java.util.Stack;

public class Programmers2_Pair {

	static int solution(String s) {
		int answer = -1;
		boolean empty = true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(s.charAt(i));
				empty = false;
				continue;
			}
			if (stack.peek() == s.charAt(i)) {
				stack.pop();
			}else {
				stack.push(s.charAt(i));
			}
		}
		if (stack.isEmpty() && !empty) {
			answer = 1;
		} else {
			answer = 0;
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution("abcda"));
	}
}
