package queue;

public class LinkedQueue<E>
{
    private Node<E> head;
    private Node<E> tail;

    public void add(E e)
    {
        Node<E> node = new Node<>(e);

        if (head == null) {
            head = tail = node;
            return;
        }

        tail.next = node;
        tail = tail.next;
    }

    public E pop()
    {
        Node<E> temp = head;
        head = head.next;
        return temp.value;
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
