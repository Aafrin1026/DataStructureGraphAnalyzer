import java.util.Arrays;
import java.util.Random;

/**
 * Array operations for the Data Structure & Graph Performance Analyzer.
 *
 * Supports:
 * - Insert at end
 * - Insert at a position
 * - Delete by position
 * - Delete by value
 * - Display
 * - Sort
 * - Generate random array
 */
public class ArrayOperations {

    private int[] data;
    private int size;

    // Initial capacity
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Creates an empty array with an initial capacity.
     */
    public ArrayOperations() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the number of elements currently stored.
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the array is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a value to the end of the array.
     *
     * Average case: O(1)
     * Resize case: O(n)
     */
    public void append(int value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    /**
     * Inserts a value at the specified position.
     *
     * Elements from that position onward are shifted right.
     *
     * Time complexity: O(n)
     */
    public void insertAt(int position, int value) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException(
                    "Invalid position: " + position
            );
        }

        ensureCapacity();

        // Shift elements to the right
        for (int i = size; i > position; i--) {
            data[i] = data[i - 1];
        }

        data[position] = value;
        size++;
    }

    /**
     * Deletes and returns the value at the specified position.
     *
     * Time complexity: O(n)
     */
    public int deleteAt(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid position: " + position
            );
        }

        int removedValue = data[position];

        // Shift elements to the left
        for (int i = position; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        // Clear unused last position
        data[size] = 0;

        return removedValue;
    }

    /**
     * Deletes the first occurrence of a value.
     *
     * Returns true if the value was found and deleted.
     *
     * Time complexity: O(n)
     */
    public boolean deleteByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                deleteAt(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Displays the current array contents.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }

        System.out.print("Array: ");

        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);

            if (i < size - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    /**
     * Sorts the current array in ascending order.
     *
     * Time complexity: O(n log n)
     */
    public void sort() {
        Arrays.sort(data, 0, size);
    }

    /**
     * Generates random values and replaces the current array.
     *
     * @param count number of elements
     * @param maxValue maximum possible random value
     */
    public void fillRandom(int count, int maxValue) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "Number of elements cannot be negative."
            );
        }

        if (maxValue < 1) {
            throw new IllegalArgumentException(
                    "Maximum value must be at least 1."
            );
        }

        data = new int[Math.max(INITIAL_CAPACITY, count)];
        size = count;

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            data[i] = random.nextInt(maxValue) + 1;
        }
    }

    /**
     * Returns a copy containing only the actual array elements.
     */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /**
     * Checks whether the array is sorted in ascending order.
     */
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Doubles the internal array capacity when it becomes full.
     */
    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;

            if (newCapacity == 0) {
                newCapacity = INITIAL_CAPACITY;
            }

            data = Arrays.copyOf(data, newCapacity);
        }
    }
}