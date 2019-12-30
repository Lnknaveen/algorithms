package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BinarySearchTrees
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private class BSNode {
        T data;
        BSNode left, right;

        public BSNode(final BSNode left, final BSNode right, final T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private int nodeCount = 0;

    private BSNode root;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean add(final T element) {
        if (contains(element)) {
            return false;
        } else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    private BSNode add(BSNode node, final T element) {
        if (node == null) {
            node = new BSNode(null, null, element);
        } else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }

    public boolean remove(final T element) {
        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }

        return false;
    }

    private BSNode remove(BSNode node, final T element) {
        if (node == null)
            return null;

        final int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            node.left = remove(node.left, element);
        } else if (cmp > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                final BSNode rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                final BSNode leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;
            } else {
                final BSNode tmp = digLeft(node.right);

                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
            }
        }

        return node;
    }

    private BSNode digLeft(final BSNode node) {
        BSNode cur = node;

        while (cur.left != null)
            cur = cur.left;

        return cur;
    }

    private BSNode digRight(final BSNode node) {
        BSNode cur = node;

        while (cur.right != null)
            cur = cur.right;

        return cur;
    }

    public boolean contains(final T element) {
        return contains(root, element);
    }

    private boolean contains(final BSNode node, final T element) {
        if (node == null)
            return false;

        final int cmp = element.compareTo(node.data);

        if (cmp < 0)
            return contains(node.left, element);
        else if (cmp > 0)
            return contains(node.right, element);
        else
            return true;
    }

    public int height() {
        return height(root);
    }

    public int height(final BSNode node) {
        if (node == null)
            return 0;

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public List<T> traverse(final String order) {
        List<T> result = new ArrayList<>();

        switch (order) {
        case "PRE":
            preOrderTraversal(result, root);
            break;
        case "IN":
            inOrderTraversal(result, root);
            break;
        case "POST":
            postOrderTraversal(result, root);
            break;
        case "LEVEL":
            result = levelOrderTraversal();
        }

        return result;
    }

    private void preOrderTraversal(List<T> result, BSNode node) {
        result.add(node.data);

        if (node.left != null)
            preOrderTraversal(result, node.left);

        if (node.right != null)
            preOrderTraversal(result, node.right);
    }

    private void inOrderTraversal(List<T> result, BSNode node) {
        if (node.left != null)
            inOrderTraversal(result, node.left);

        result.add(node.data);

        if (node.right != null)
            inOrderTraversal(result, node.right);
    }

    private void postOrderTraversal(List<T> result, BSNode node) {
        if (node.left != null)
            postOrderTraversal(result, node.left);

        if (node.right != null)
            postOrderTraversal(result, node.right);

        result.add(node.data);
    }

    private List<T> levelOrderTraversal() {
        final List<T> result = new ArrayList<>();
        final Queue<BSNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            final BSNode node = stack.poll();
            result.add(node.data);

            if (node.left != null)
                stack.offer(node.left);

            if (node.right != null)
                stack.offer(node.right);
        }

        return result;
    }
}