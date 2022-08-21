package com.day0806;

import java.util.HashMap;

public class Programmers2_Dictionary_ {
	static int solution(String word) {
		int answer = 0;
		int num;
		int[] arr = new int[word.length()];
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('A', 0);
		map.put('E', 1);
		map.put('I', 2);
		map.put('O', 3);
		map.put('U', 4);
		int[] nums = { 781, 156, 31, 6, 1 };
		for (int i = 0; i < word.length(); i++) {
			if (map.containsKey(word.charAt(i))) {
				arr[i] = map.get(word.charAt(i));
			}
		}
		for (int i = 0; i < arr.length; i++) {
			int tmp = nums[i] * arr[i] + 1;
			answer += tmp;
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution("EIO"));
	}
}
