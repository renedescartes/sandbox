package com.work.tdd.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.work.tdd.tree.BinaryTreeBuilder.node;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InorderTraversalTest {

	private BinaryTree<Integer> tree = 
		node(4)
		.setLeft(
				node(7)
					.setLeft(
							node(3)
					)
					.setRight(
							node(6)
					)
		)
		.setRight(
				node(8)
					.setRight(node(9))
		);
	
	@Test
	public void testSimpleInOrderTraversal() {
		InorderTraversal<Integer> traversal = new InorderTraversal<Integer>();
		ListTreeVisitor<Integer> visitor = new ListTreeVisitor<Integer>();
		traversal.traverse(tree, visitor);
		List<Integer> expected = Arrays.asList(3, 7, 6, 4, 8, 9);
		assertArrayEquals(expected.toArray(new Integer[0]), visitor.getContents().toArray(new Integer[0]));
	}
	
	@Test
	public void testElementNumber() {
		InorderTraversal<Integer> traversal = new InorderTraversal<Integer>();
		ElementNumberVisitor<Integer> visitor = new ElementNumberVisitor<Integer>(5);
		traversal.traverse(tree, visitor);
		assertEquals(8, visitor.getContent().intValue());
	}
}
