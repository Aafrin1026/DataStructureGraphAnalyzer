/** Singly linked list built from scratch using an inner Node class. */
public class MyLinkedList {
    private class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private int size = 0;
    private int lastSearchSteps = 0;

    public int size() { return size; }
    public boolean isEmpty() { return head == null; }
    public int getLastSearchSteps() { return lastSearchSteps; }

    /** O(1) */
    public void insertAtHead(int value) {
        Node n = new Node(value);
        n.next = head;
        head = n;
        size++;
    }

    /** O(n) because we walk to the tail. */
    public void insertAtTail(int value) {
        Node n = new Node(value);
        if (head == null) head = n;
        else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    /** Insert at a 1-based position (1 = head, size+1 = tail). */
    public void insertAt(int position, int value) {
        if (position < 1 || position > size + 1)
            throw new IndexOutOfBoundsException("Position must be between 1 and " + (size + 1) + ".");
        if (position == 1) { insertAtHead(value); return; }
        Node cur = head;
        for (int i = 1; i < position - 1; i++) cur = cur.next;
        Node n = new Node(value);
        n.next = cur.next;
        cur.next = n;
        size++;
    }

    /** Delete first node holding value. Returns false when not found. */
    public boolean deleteByValue(int value) {
        if (isEmpty()) throw new IllegalStateException("List is empty. Nothing to delete.");
        if (head.data == value) { head = head.next; size--; return true; }
        Node cur = head;
        while (cur.next != null && cur.next.data != value) cur = cur.next;
        if (cur.next == null) return false;
        cur.next = cur.next.next;
        size--;
        return true;
    }

    /** Returns 1-based position of value or -1. Counts comparisons in lastSearchSteps. */
    public int search(int value) {
        lastSearchSteps = 0;
        Node cur = head;
        int pos = 1;
        while (cur != null) {
            lastSearchSteps++;
            if (cur.data == value) return pos;
            cur = cur.next;
            pos++;
        }
        return -1;
    }

    public void display() {
        if (isEmpty()) { System.out.println("Linked list is empty."); return; }
        StringBuilder sb = new StringBuilder("HEAD -> ");
        for (Node cur = head; cur != null; cur = cur.next) sb.append(cur.data).append(" -> ");
        sb.append("NULL");
        System.out.println(sb);
    }
}
