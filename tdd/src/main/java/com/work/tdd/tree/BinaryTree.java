package com.work.tdd.tree;

public interface BinaryTree<T> {
	
	public BinaryTree<T> getLeft();
	
	public BinaryTree<T> getRight();
	
	public BinaryTree<T> setLeft(BinaryTree<T> left);
	
	public BinaryTree<T> setRight(BinaryTree<T> right);
	
	public T content();
	
	public void setContent(T content);
	
	public BinaryTree<T> getParent();

	public void setParent(BinaryTree<T> parent);
}
