package com.day0804;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW1218_PairingParenthesis {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer buffer = new StringBuffer();
		String starter = "<{[("; // 여는 괄호

		for (int tc = 1; tc <= 10; tc++) {
			int answer = 1; // 답
			buffer.append("#" + tc + " ");
			br.readLine(); // 입력될 문자열 길이
			String line = br.readLine(); // 입력되는 문자열 내용

			Stack<String> st = new Stack<>(); // 여는 괄호 저장
			for (int i = 0; i < line.length(); i++) {
				String item = line.charAt(i) + "";
//				String item2 = line.substring(i, i);

				// item이 여는 괄호인지 닫는 괄호인지 구분
				if (starter.contains(item)) { // 여는 괄호
					st.push(item);
				} else { // 닫는 괄호
					boolean flag = false; // 쌍이 맞는지 체크할 변수
					if (!st.isEmpty()) {
						// stack에서 괄호 한 개 꺼내기
						String pair = st.pop();
						if (item.equals(">") && pair.equals("<")) {
							flag = true;
						} else if (item.equals("}") && pair.equals("{")) {
							flag = true;
						} else if (item.equals("]") && pair.equals("[")) {
							flag = true;
						} else if (item.equals(")") && pair.equals("(")) {
							flag = true;
						}

						if (!flag) {
							answer = 0;
							break;
						} // if
					} // if
				} // else
			} // for
			buffer.append(answer).append("\n");
		} // for
		System.out.println(buffer);
	}// main
}
