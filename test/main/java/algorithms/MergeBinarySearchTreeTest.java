package algorithms;

import org.hamcrest.MatcherAssert;
import org.junit.*;
import org.junit.Test;

import static algorithms.MergeBinarySearchTree.createTree;
import static algorithms.MergeBinarySearchTree.merge;
import static algorithms.MergeBinarySearchTree.restore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MergeBinarySearchTreeTest {

    @org.junit.Test
    public void testMerge() throws Exception {

        MergeBinarySearchTree.Node tree1 = createTree(new int[]{1,2,3,4});
        MergeBinarySearchTree.Node tree2 = createTree(new int[]{5,6,7});

        assertThat(merge(tree1, tree2), is(new int[]{1,2,3,4,5,6,7}));

    }

    @Test
    public void testRestore() throws Exception {
        MergeBinarySearchTree.Node root = restore(new int[]{1,2,3}, 0, 2);

        MergeBinarySearchTree.Node left = new MergeBinarySearchTree.Node(1);
        MergeBinarySearchTree.Node right = new MergeBinarySearchTree.Node(3);
        MergeBinarySearchTree.Node expected = new MergeBinarySearchTree.Node(2, left, right);

        MatcherAssert.assertThat(root, is(expected));
    }
}