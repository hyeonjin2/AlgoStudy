package com.day0817;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW5644_WirelessCharger {
	
	static class Station{
		int x, y;
		int cover;//충전범위
		int perform;//충전량
		
		public Station(int x, int y, int cover, int perform) {
			this.x = x;
			this.y = y;
			this.cover = cover;
			this.perform = perform;
		}		
	}
	
	//방향값: 상1 우2 하3 좌4 
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0,  0, 1, 0,-1};
	static ArrayList<Station> list;	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());//test case
		
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());//이동시간
			int bcCount = Integer.parseInt(st.nextToken());//충전소 개수
			
			list = new ArrayList<Station>();
			
			//사용자 초기위치
			Point A = new Point(0,0);
			Point B = new Point(9,9);	
			
			//사용자가 시간대 별로 움직이는 방향값 
			int[] dirA = new int[time];
			int[] dirB = new int[time];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {//A가 시간별로 움직이는 방향값
				dirA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {//B가 시간별로 움직이는 방향값
				dirB[i] = Integer.parseInt(st.nextToken());
			}
			
			//충전소 정보
			for (int i = 0; i < bcCount; i++) {
				st = new StringTokenizer(br.readLine());				
				int y = Integer.parseInt(st.nextToken()) - 1;//인덱스 1부터 시작이라서
				int x = Integer.parseInt(st.nextToken()) - 1;//인덱스 1부터 시작이라서
				int cover = Integer.parseInt(st.nextToken());//충전범위
				int perform = Integer.parseInt(st.nextToken());//충전량
				list.add(new Station(x, y, cover, perform));				
			}
			
			int total = 0;//답
			for (int i = 0; i <= time; i++) {//매 초마다 작업(0초인 경우도 계산해줘야 해서)
				//1.사용자 별로 접속 가능한 충전소 체크
				int[][] possible = new int[bcCount][2];
				for (int j = 0; j < bcCount; j++) {//충전소 수 만큼
					Station station = list.get(j);//충전소 하나 꺼내고
					
					if(Math.abs(A.x - station.x) + Math.abs(A.y - station.y) <= station.cover)
						possible[j][0]++;//A사용자가 접속 가능함!
					
					if(Math.abs(B.x - station.x) + Math.abs(B.y - station.y) <= station.cover)
						possible[j][1]++;//B사용자가 접속 가능함!
				}
								
				//2.접속 가능한 충전소 중에서 충전량이 최대인 양 계산
				//2-1.사용자가 혼자만 충전 가능한 곳 중에서 최대인 양 계산
				int maxA = 0, maxB = 0;
				for (int j = 0; j < bcCount; j++) {
					Station station = list.get(j);//충전소 하나 꺼내고
					
					if(possible[j][0] == 1 && possible[j][1] == 0 && maxA < station.perform) {//A만 접속 가능한 경우
						maxA = station.perform;
					}
					
					if(possible[j][0] == 0 && possible[j][1] == 1 && maxB < station.perform) {//B만 접속 가능한 경우
						maxB = station.perform;
					}					
				}				
				
				//2-2.사용자 둘이 동시에 접속 가능한 곳에서 최대인 양 계산
				for (int j = 0; j < bcCount; j++) {
					Station station = list.get(j);//충전소 하나 꺼내고
					if(possible[j][0] == 1 && possible[j][1] == 1) {//두명다 j번째 충전소 접속 가능한 경우
						if(maxA < station.perform && maxA <= maxB) {//둘 중에 더 작은 쪽으로 몰아줌
							maxA = station.perform;
							
						}else if(maxB < station.perform && maxA >= maxB) {//둘 중에 더 작은 쪽으로 몰아줌
							maxB = station.perform;
						}						
					}					
				}
				
				//3.A + B 사용량 합계 구하고 누적
				total += maxA + maxB;//단위 시간에 두사람이 충전 받을 수 있는 최대량이 누적
				
				//4.다음 시간의 경우 계산을 위해서 방향 전환 -> 모든 시간 다 처리 안했으면
				if(i == time)
					break;
				
				else {//이동 시간 남아 있음
					A.x += dx[dirA[i]];
					A.y += dy[dirA[i]];
					
					B.x += dx[dirB[i]];
					B.y += dy[dirB[i]];					
				}				
			}		
			
			System.out.println("#" + tc + " " + total);
		}		
	}	
}
