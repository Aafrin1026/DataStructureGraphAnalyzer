/**
 * Searching operations for the Data Structure & Graph Performance Analyzer.
 *
 * Provides:
 * - Linear search
 * - Binary search
 * - Step counting
 * - Execution time measurement
 */
public class SearchOperations {

    /**
     * Stores the result of a search operation.
     */
    public static class Result {

        public final int index;
        public final int steps;
        public final long nanos;

        public Result(int index, int steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }

        /**
         * Returns true when the searched value was found.
         */
        public boolean found() {
            return index >= 0;
        }
    }

    /**
     * Performs linear search.
     *
     * Checks elements one by one from the beginning.
     *
     * Time complexity: O(n)
     */
    public static Result linearSearch(int[] data, int key) {

        long start = System.nanoTime();

        int steps = 0;

        for (int i = 0; i < data.length; i++) {

            steps++;

            if (data[i] == key) {
                long nanos = System.nanoTime() - start;
                return new Result(i, steps, nanos);
            }
        }

        long nanos = System.nanoTime() - start;

        return new Result(-1, steps, nanos);
    }

    /**
     * Performs binary search.
     *
     * IMPORTANT:
     * The input array must already be sorted
     * in ascending order.
     *
     * Time complexity: O(log n)
     */
    public static Result binarySearch(int[] data, int key) {

        long start = System.nanoTime();

        int left = 0;
        int right = data.length - 1;
        int steps = 0;

        while (left <= right) {

            int middle = left + (right - left) / 2;

            steps++;

            if (data[middle] == key) {
                long nanos = System.nanoTime() - start;
                return new Result(middle, steps, nanos);
            }

            if (data[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        long nanos = System.nanoTime() - start;

        return new Result(-1, steps, nanos);
    }
}
