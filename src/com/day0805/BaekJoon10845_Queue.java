package com.day0805;

import java.util.Scanner;

class MyQue {
	int front;
	int rear;
	int[] arr;

	MyQue() {
		this.front = 0;
		this.rear = 0;
		this.arr = new int[10001];
	}

	void push(int num) {
		if (this.rear <= 10000) {
			arr[rear] = num;
			rear++;
		}
	}

	void pop() {
		if (rear != front) {
			System.out.println(arr[front]);
			front++;
		} else {
			System.out.println(-1);
		}
	}

	void size() {
		System.out.println(rear - front);
	}

	void empty() {
		System.out.println((rear == front) ? 1 : 0);
	}

	void front() {
		System.out.println((rear != front) ? arr[front] : -1);
	}

	void back() {
		System.out.println((rear != front) ? arr[rear - 1] : -1);
	}
}

public class BaekJoon10845_Queue {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		MyQue queue = new MyQue();
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			switch (str) {
			case "push":
				queue.push(sc.nextInt());
				break;
			case "front":
				queue.front();
				break;
			case "size":
				queue.size();
				break;
			case "back":
				queue.back();
				break;
			case "pop":
				queue.pop();
				break;
			case "empty":
				queue.empty();
				break;

			default:
				break;
			}
		}
	}
}
