package com.work.tdd.tree;

public class Heap<T extends Comparable<? super T>> extends DefaultBinaryTree<T> {

	public Heap() {
		super();
	}

	public Heap(T content, BinaryTree<T> left, BinaryTree<T> right) {
		super(content, left, right);
	}

	public Heap(T content) {
		super(content);
	}

	@Override
	public BinaryTree<T> setLeft(BinaryTree<T> left) {
		BinaryTree<T> child = left, parent = this;
		while(child.content().compareTo(parent.content()) > 0) {
			swap(child, parent);
			child = parent;
			parent = parent.getParent();
		}
		return this;
	}

	@Override
	public BinaryTree<T> setRight(BinaryTree<T> right) {
		// TODO Auto-generated method stub
		return super.setRight(right);
	}

	public static <T> void swap(BinaryTree<T> n1, BinaryTree<T> n2) {
		T buffer = n1.content();
		n1.setContent(n2.content());
		n2.setContent(buffer);
	}
	

}
