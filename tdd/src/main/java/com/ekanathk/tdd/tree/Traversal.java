package com.ekanathk.tdd.tree;

public interface Traversal<T, E> {

	public E traverse(BinaryTree<T> tree, TreeVisitor<T> visitor);
}
