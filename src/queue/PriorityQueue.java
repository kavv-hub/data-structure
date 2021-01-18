package queue;

public class PriorityQueue<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 100;

    private int front = -1, rear = -1;

    private final E[] heap;

    public PriorityQueue() {
        this.heap = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(E value) {
        if (rear == heap.length - 1) return;

        if (front == -1)
            front = rear = 0;
        else
            rear += 1;

        heap[rear] = value;

        int current = rear;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap[parent].compareTo(heap[current]) > 0) {
                swap(heap, parent, current);
            }
            current = parent;
        }
    }

    public E pop() {
        if (front == -1) return null;

        E value = heap[front];
        heap[front] = heap[rear--];

        this.heapify(this.heap, front);

        return value;
    }

    public E peek() {
        return heap[0];
    }

    private void heapify(E[] heap, int current) {
        while (current <= rear) {
            int left = current * 2 + 1, right = current * 2 + 2;

            int smallest = current;
            if (left <= rear && heap[smallest].compareTo(heap[left]) > 0)
                smallest = left;
            if (right <= rear && heap[smallest].compareTo(heap[right]) > 0)
                smallest = right;

            if (smallest == current) break;

            swap(heap, current, smallest);

            current = smallest;
        }
    }

    private void swap(E[] A, int i, int j) {
        E temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
