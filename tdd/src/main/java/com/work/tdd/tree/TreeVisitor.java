package com.work.tdd.tree;

public interface TreeVisitor<T> {

	public boolean visit(BinaryTree<T> node);
}
