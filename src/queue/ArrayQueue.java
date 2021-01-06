package queue;

public class ArrayQueue<E>
{
    private int capacity = 8;
    private E[] queue = (E[]) new Object[capacity];
    private int front = - 1;
    private int rear = -1;

    public void add(E element) {
        if ((rear + 1) % capacity == front) return;
        if (front == -1) {
            front = rear = 0;
        }
        else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = element;
    }

    public E poll() {
        if (front == -1) return null;

        E element = queue[front];
        if (front == rear) {
            front = rear = -1;
        }
        else {
            front =  (front + 1) % capacity;
        }
        return element;
    }
}