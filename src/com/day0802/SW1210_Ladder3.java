package com.day0802;

import java.util.Arrays;
import java.util.Scanner;

// 출발점 -> 도착점 : 모든 시작 가능한 열을 체크해야 함
// 도착점 -> 출발점으로 오는 방법
public class SW1210_Ladder3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			sc.nextInt(); // testcase 번호. 사용 X
			String[][] ladder = new String[100][100];

			for (int i = 0; i < 100; i++) {
				ladder[i] = sc.nextLine().split(" "); // split() --> String[] 리턴
			}
//			// 입력확인
//			for (String[] line : ladder) {
//				System.out.println(Arrays.toString(line));
//			}
			// 거꾸로 올라가서 출발점을 찾기 위해 도착점 찾아두기. 도착점의 열값 찾기
			int end = 0;
			for (int i = 0; i < 100; i++) {
				if (ladder[99][i].equals("2")) {
					end = i;
					break;
				}
			}
			int x = 99, y = end; // 도착점 : ladder[99][end]
			while (x > 0) { // 제일 첫번째 행은 체크 안해도 됨. 첫번째 행에서 좌우, 위로 움직이지 않음
				// 1. 오른쪽으로 갈 수 있는지 => 계속 오른쪽으로 이동
				if (y + 1 < 100 && ladder[x][y + 1].equals("1")) { // 오른쪽 위치가 배열 안이고 값이 1이라면
					do {
						y++; // 열 값 증가
					} while (y + 1 < 100 && ladder[x][y + 1].equals("1"));
				}
				// 2. 왼쪽으로 갈 수 있는지 => 계속 왼쪽으로 이동
				else if (y - 1 >= 0 && ladder[x][y - 1].equals("1")) { // 오른쪽 위치가 배열 안이고 값이 1이라면
					do {
						y--; // 열 값 증가
					} while (y - 1 >= 0 && ladder[x][y - 1].equals("1"));
				}
				// 3. 좌,우가 안되면 위로 이동
				x--;
			}
			System.out.println("#" + tc + " " + y);
		}
	}
}
