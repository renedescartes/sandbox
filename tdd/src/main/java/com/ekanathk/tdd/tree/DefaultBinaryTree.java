package com.ekanathk.tdd.tree;

public class DefaultBinaryTree<T> implements BinaryTree<T> {

	private T content;
	private BinaryTree<T> left;
	private BinaryTree<T> right;
	private BinaryTree<T> parent;
	
	public DefaultBinaryTree() {
		this(null);
	}

	public DefaultBinaryTree(T content) {
		this(content, null, null);
	}

	public DefaultBinaryTree(T content, BinaryTree<T> left, BinaryTree<T> right) {
		super();
		this.content = content;
		this.left = left;
		this.right = right;
	}

	@Override
	public BinaryTree<T> getLeft() {
		return left;
	}

	@Override
	public BinaryTree<T> getRight() {
		return right;
	}

	@Override
	public T content() {
		return content;
	}

	@Override
	public BinaryTree<T> setLeft(BinaryTree<T> left) {
		this.left = left;
		left.setParent(this);
		return this;
	}

	@Override
	public BinaryTree<T> setRight(BinaryTree<T> right) {
		this.right = right;
		right.setParent(this);
		return this;
	}
	
	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Tree [content=" + content + ", left=" + left
				+ ", right=" + right + "]";
	}

	public BinaryTree<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTree<T> parent) {
		this.parent = parent;
	}
	
}
