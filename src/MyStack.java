/** Array-based stack (LIFO). Push, pop and peek are all O(1). */
public class MyStack {
    private final int[] items;
    private int top = -1;

    public MyStack(int capacity) { items = new int[capacity]; }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == items.length - 1; }
    public int size() { return top + 1; }

    public void push(int value) {
        if (isFull()) throw new IllegalStateException("Stack Overflow: the stack is full (capacity " + items.length + ").");
        items[++top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack Underflow: cannot pop from an empty stack.");
        return items[top--];
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty. Nothing to peek.");
        return items[top];
    }

    public void display() {
        if (isEmpty()) { System.out.println("Stack is empty."); return; }
        System.out.println("Stack (top -> bottom):");
        for (int i = top; i >= 0; i--) System.out.println("  | " + items[i] + " |" + (i == top ? "  <- top" : ""));
    }
}
