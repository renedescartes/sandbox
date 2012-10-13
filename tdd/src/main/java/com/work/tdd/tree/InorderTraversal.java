package com.work.tdd.tree;

public class InorderTraversal<T> implements Traversal<T, Boolean>{

	@Override
	public Boolean traverse(BinaryTree<T> tree, TreeVisitor<T> visitor) {
		boolean proceed = true;
		if(tree.getLeft() != null) {
			proceed = traverse(tree.getLeft(), visitor);
		}
		if(proceed) {
			proceed = visitor.visit(tree);
		}
		if(proceed && tree.getRight() != null) {
			proceed = traverse(tree.getRight(), visitor);
		}
		return proceed;
	}

}
