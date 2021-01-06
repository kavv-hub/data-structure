package list;

public class LinkedList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }

    public E valueAt(int i) {
        if (i > this.size) return null;
        Node<E> current = head;
        while (i > 0) {
            current = current.next;
            i++;
        }
        return current.value;
    }

    public void pushFront(E e) {
        Node<E> node = new Node<>(e);
        node.next = head;
        head = node;
        if (tail == null) tail = head;
        size++;
    }

    public E popFront() {
        Node<E> node = head;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return node.value;
    }

    public void pushBack(E e) {
        Node<E> node = new Node<>(e);
        if (tail == null) {
            head = tail = node;
            return;
        }

        tail.next = node;
        tail = node;
        size++;
    }

    public E popBack() {
        if (tail == null) return null;

        Node<E> node = tail;
        Node<E> current = head;
        for (int i = 1; i < size - 1; i++) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        size--;

        return node.value;
    }

    public E front() {
        return head == null ? null : head.value;
    }

    public E back() {
        return tail == null ? null : tail.value;
    }

    public void insert(E e, int index) {
        if (index > size || index < 0) return;
        if (index == size) {
            pushBack(e);
        }
        else if (index == 0) {
            pushFront(e);
        }
        else {
            Node<E> node = new Node<>(e);
            Node<E> previous = head;
            for (int i = 0; i < index - 1; i++) previous = previous.next;
            Node<E> next = previous.next;
            previous.next = node;
            node.next = next;
        }
    }

    public E erase(int index) {
        if (index > size - 1 || index < 0) return null;
        if (index == size - 1) {
            return popBack();
        }
        else if (index == 0) {
            return popFront();
        }
        else {
            Node<E> previous = head;
            for (int i = 0; i < index - 1; i++) previous = previous.next;
            Node<E> node = previous.next;
            previous.next = previous.next.next;
            return node.value;
        }
    }

    public void reverse() {
        Node<E> current = head;
        Node<E> next = current.next;

        current.next = null;
        tail = current;
        while (current != null && next != null) {
            Node<E> temp = next.next;
            next.next = current;
            current = next;
            next = temp;
        }
        head = current;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.value);
            sb.append(", ");
            current = current.next;
        }
        return sb.toString();
    }

    private class Node<E>
    {
        private E value;

        private Node<E> next;

        public Node(E value)
        {
            this.value = value;
        }
    }
}
