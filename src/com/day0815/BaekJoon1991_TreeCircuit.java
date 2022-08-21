package com.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1991_TreeCircuit {

	static class Node {
		char info;
		Node left, right;

		public Node(char info) {
			this.info = info;
		}

		public void add(char info, char left, char right) {
			if (this.info == info) {
				if (left != '.') {
					this.left = new Node(left);
				}
				if (right != '.') {
					this.right = new Node(right);
				}
			} else {
				if (this.left != null) {
					this.left.add(info, left, right);
				}
				if (this.right != null) {
					this.right.add(info, left, right);
				}
			}
		}
	}

	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// root 값 넣기
		char rootInfo = st.nextToken().charAt(0);
		Node root = new Node(rootInfo);
		root.add(rootInfo, st.nextToken().charAt(0), st.nextToken().charAt(0));
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char info = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			root.add(info, left, right);
		}
		sb = new StringBuilder();
		PreOrder(root);
		sb.append("\n");
		InOrder(root);
		sb.append("\n");
		PostOrder(root);
		System.out.println(sb);
	}

	private static void PostOrder(Node node) {
		if (node == null)
			return;
		PostOrder(node.left);
		PostOrder(node.right);
		sb.append(node.info);

	}

	private static void InOrder(Node node) {
		if (node == null)
			return;
		InOrder(node.left);
		sb.append(node.info);
		InOrder(node.right);

	}

	private static void PreOrder(Node node) {
		if (node == null)
			return;
		sb.append(node.info);
		PreOrder(node.left);
		PreOrder(node.right);
	}
}
