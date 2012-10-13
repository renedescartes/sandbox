package com.work.tdd.tree;

public class ElementNumberVisitor<T> implements TreeVisitor<T>{

	private int count = 0;
	private T content = null;
	
	public ElementNumberVisitor(int count) {
		if(count < 1) {
			throw new IllegalArgumentException("The count should be atleast 1");
		}
		this.count = count;
	}

	@Override
	public boolean visit(BinaryTree<T> node) {
		if(--count == 0) {
			this.content = node.content();
			return false;
		}
		return true;
	}

	public T getContent() {
		return content;
	}

	
}
