package com.work.tdd.tree;

import java.util.ArrayList;
import java.util.List;

public class ListTreeVisitor<T> implements TreeVisitor<T> {

	List<T> contents = new ArrayList<T>();
	
	@Override
	public boolean visit(BinaryTree<T> node) {
		contents.add(node.content());
		return true;
	}

	public List<T> getContents() {
		return contents;
	}
	
}
