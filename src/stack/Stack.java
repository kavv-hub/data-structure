package stack;

public class Stack<E> {

    private int capacity;
    private int front = -1;
    private int rear = -1;
    private E[] array;

    public Stack(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    public void add(E e) {
        if (rear == capacity - 1) return;
        if (rear == -1) {
            front = rear = 0;
        }
        else {
            rear += 1;
        }
        array[rear] = e;
    }

    public E pop() {
        if (rear == -1) return null;
        E e = array[rear];
        if (front == rear) {
            front = rear = -1;
        }
        else {
            rear--;
        }
        return e;
    }
}
