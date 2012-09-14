package com.ekanathk.tdd.tree;

public class BinaryTreeBuilder {

	public static BinaryTree<Integer> node(Integer i) {
		return new DefaultBinaryTree<Integer>(i);
	}

	
}
