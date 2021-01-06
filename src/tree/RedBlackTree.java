package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class RedBlackTree<E extends Comparable<E>> {
    private Node<E> root;

    public void insert(E element) {
        Node<E> node = _insert(element);
        fixUp(node);
    }

    private void fixUp(Node<E> current) {
        while (current.parent != null && current.parent.color == Node.Color.RED) {
            Node<E> parent = current.parent;
            Node<E> grandparent = current.parent.parent;

            if (parent == grandparent.left) {
                if (grandparent.right != null && grandparent.right.color == Node.Color.RED) {
                    grandparent.right.color = grandparent.left.color = Node.Color.BLACK;
                    grandparent.color = Node.Color.RED;
                    current = grandparent;
                }
                else {
                    if (current == parent.right) {
                        current = parent;
                        rotateLeft(parent);
                    }

                    current.parent.color = Node.Color.BLACK;
                    current.parent.parent.color = Node.Color.RED;
                    rotateRight(grandparent);
                }
            }
            else {
                if (grandparent.left != null && grandparent.left.color == Node.Color.RED) {
                    grandparent.left.color = grandparent.right.color = Node.Color.BLACK;
                    grandparent.color = Node.Color.RED;
                    current = grandparent;
                }
                else {
                    if (current == parent.left) {
                        current = parent;
                        rotateRight(parent);
                    }

                    current.parent.color = Node.Color.BLACK;
                    current.parent.parent.color = Node.Color.RED;
                    rotateLeft(grandparent);
                }
            }
        }

        root.color = Node.Color.BLACK;
    }

    private Node<E> _insert(E element) {
        Node<E> current = root, previous = null;
        while (current != null) {
            previous = current;
            if (current.value.compareTo(element) <= 0)
                current = current.left;
            else
                current = current.right;
        }

        if (previous == null) {
            root = new Node<E>(element, Node.Color.BLACK);
            return root;
        }

        Node<E> newNode = new Node<>(element, Node.Color.RED, previous);
        if (previous.value.compareTo(element) <= 0) {
            previous.left = newNode;
        }
        else {
            previous.right = newNode;
        }

        return newNode;
    }

    public void rotateLeft(Node<E> node) {
        Node<E> right = node.right;
        node.right = right.left;
        if (right.left != null) right.left.parent = node;

        if (node.parent == null)
            root = right;
        else if (node == node.parent.left)
            node.parent.left = right;
        else
            node.parent.right = right;

        right.left = node;
        right.parent = node.parent;
        node.parent = right;
    }

    public void rotateRight(Node<E> node) {
        Node<E> left = node.left;
        node.left = left.right;
        if (left.right != null) left.right.parent = node;

        if (node.parent == null)
            root = left;
        else if (node == node.parent.left)
            node.parent.left = left;
        else
            node.parent.right = left;

        left.right = node;
        left.parent = node.parent;
        node.parent = left;
    }

    public void print() {
        Queue<Node<E>> queue = new ArrayDeque<>();
        if (root != null) queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node<E> node = queue.poll();
                System.out.print(node.value);
                System.out.print(" ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }

    public static class Node<E extends Comparable<E>> {
        enum Color { RED, BLACK; }

        E value;
        Color color;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E value, Color color) {
            this.value = value;
            this.color = color;
        }

        public Node(E value, Color color, Node<E> parent) {
            this.value = value;
            this.color = color;
            this.parent = parent;
        }
    }
}
