/** Circular array queue (FIFO). Enqueue and dequeue are O(1). */
public class MyQueue {
    private final int[] items;
    private int front = 0;
    private int count = 0;

    public MyQueue(int capacity) { items = new int[capacity]; }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull() { return count == items.length; }
    public int size() { return count; }

    public void enqueue(int value) {
        if (isFull()) throw new IllegalStateException("Queue Overflow: the queue is full (capacity " + items.length + ").");
        items[(front + count) % items.length] = value;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue Underflow: cannot dequeue from an empty queue.");
        int v = items[front];
        front = (front + 1) % items.length;
        count--;
        return v;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty. Nothing at the front.");
        return items[front];
    }

    public void display() {
        if (isEmpty()) { System.out.println("Queue is empty."); return; }
        StringBuilder sb = new StringBuilder("Queue (front -> rear): ");
        for (int i = 0; i < count; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < count - 1) sb.append(" <- ");
        }
        System.out.println(sb);
    }
}
