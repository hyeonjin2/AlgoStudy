package com.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon5639_BinarySearchTree {
	static StringBuilder sb;

	static class Node {
		int num;
		Node left, right;

		public Node(int num) {
			this.num = num;
		}

		public Node(int num, Node left, Node right) {
			this.num = num;
			this.left = left;
			this.right = right;
		}

		public void add(int n) {
			if (n < this.num) {
				if (this.left == null) {
					this.left = new Node(n);
				} else {
					this.left.add(n);
				}
			} else {
				if (this.right == null) {
					this.right = new Node(n);
				} else {
					this.right.add(n);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node root = new Node(Integer.parseInt(br.readLine()));
		String input;
		while (true) {
			input = br.readLine();
			if (input == null || input.equals(""))
				break;
			root.add(Integer.parseInt(input));
		}
		sb = new StringBuilder();
		postOrder(root);
		System.out.println(sb);
	}

	private static void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.num).append("\n");
	}
}
