package com.ekanathk.tdd.tree;

public class DefaultPrintTreeVisitor<T> implements TreeVisitor<T> {

	@Override
	public boolean visit(BinaryTree<T> node) {
		System.out.println(node.content());
		return true;
	}

}
