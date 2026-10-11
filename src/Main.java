/**
 * Data Structure & Graph Performance Analyzer
 * CIT300 Graded Practical Assignment 2 - main menu and integration.
 */
public class Main {
    private static final ArrayOperations array = new ArrayOperations();
    private static final MyStack stack = new MyStack(10);
    private static final MyQueue queue = new MyQueue(10);
    private static final MyLinkedList list = new MyLinkedList();
    private static final Graph graph = new Graph();
    private static final PerformanceTracker tracker = new PerformanceTracker();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 9);
            switch (choice) {
                case 1: arrayMenu(); break;
                case 2: stackMenu(); break;
                case 3: queueMenu(); break;
                case 4: linkedListMenu(); break;
                case 5: searchMenu(); break;
                case 6: graphMenu(); break;
                case 7: performanceComparison(); break;
                case 8: tracker.printAll(); break;
                case 9: running = false; System.out.println("Goodbye!"); break;
            }
        }
    }

    // ---------------------------------------------------------------- ARRAY
    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Position");
            System.out.println("3. Delete by Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search (Linear)");
            System.out.println("6. Display Array");
            System.out.println("7. Sort Array");
            System.out.println("8. Generate Random Array");
            System.out.println("9. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 9);
            try {
                switch (c) {
                    case 1:
                        array.append(InputHelper.readInt("Enter value: "));
                        System.out.println("Value inserted.");
                        break;
                    case 2: {
                        int pos = InputHelper.readIntInRange("Enter position (0 to " + array.size() + "): ", 0, array.size());
                        array.insertAt(pos, InputHelper.readInt("Enter value: "));
                        System.out.println("Value inserted.");
                        break;
                    }
                    case 3: {
                        if (array.isEmpty()) { System.out.println("Array is empty. Nothing to delete."); break; }
                        int pos = InputHelper.readIntInRange("Enter position (0 to " + (array.size() - 1) + "): ", 0, array.size() - 1);
                        System.out.println("Deleted value: " + array.deleteAt(pos));
                        break;
                    }
                    case 4: {
                        if (array.isEmpty()) { System.out.println("Array is empty. Nothing to delete."); break; }
                        int v = InputHelper.readInt("Enter value to delete: ");
                        System.out.println(array.deleteByValue(v) ? "Value deleted." : "Value not found.");
                        break;
                    }
                    case 5: {
                        if (array.isEmpty()) { System.out.println("Array is empty. Nothing to search."); break; }
                        int key = InputHelper.readInt("Enter value to search: ");
                        SearchOperations.Result r = SearchOperations.linearSearch(array.toArray(), key);
                        reportSearch("Linear Search", r, key);
                        tracker.add("Search", "Linear Search", "O(n)", r.steps, r.nanos,
                                "Array size " + array.size() + ", key " + key + (r.found() ? " found" : " not found"));
                        break;
                    }
                    case 6: array.display(); break;
                    case 7: array.sort(); System.out.println("Array sorted."); array.display(); break;
                    case 8: {
                        int n = InputHelper.readIntInRange("How many elements (1-100000)? ", 1, 100000);
                        array.fillRandom(n, 1000000);
                        System.out.println("Generated " + n + " random elements.");
                        break;
                    }
                    case 9: return;
                }
            } catch (IllegalStateException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- STACK
    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS (capacity 10) ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: stack.push(InputHelper.readInt("Enter value to push: ")); System.out.println("Pushed."); break;
                    case 2: System.out.println("Popped: " + stack.pop()); break;
                    case 3: System.out.println("Top element: " + stack.peek()); break;
                    case 4: stack.display(); break;
                    case 5: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- QUEUE
    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS (capacity 10) ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: queue.enqueue(InputHelper.readInt("Enter value to enqueue: ")); System.out.println("Enqueued."); break;
                    case 2: System.out.println("Dequeued: " + queue.dequeue()); break;
                    case 3: System.out.println("Front element: " + queue.peek()); break;
                    case 4: queue.display(); break;
                    case 5: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------- LINKED LIST
    private static void linkedListMenu() {
        while (true) {
            System.out.println("\n--------------- LINKED LIST OPERATIONS ---------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search");
            System.out.println("6. Display");
            System.out.println("7. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 7);
            try {
                switch (c) {
                    case 1: list.insertAtHead(InputHelper.readInt("Enter value: ")); System.out.println("Inserted at head."); break;
                    case 2: list.insertAtTail(InputHelper.readInt("Enter value: ")); System.out.println("Inserted at tail."); break;
                    case 3: {
                        int pos = InputHelper.readIntInRange("Enter position (1 to " + (list.size() + 1) + "): ", 1, list.size() + 1);
                        list.insertAt(pos, InputHelper.readInt("Enter value: "));
                        System.out.println("Inserted.");
                        break;
                    }
                    case 4: {
                        if (list.isEmpty()) { System.out.println("List is empty. Nothing to delete."); break; }
                        int v = InputHelper.readInt("Enter value to delete: ");
                        System.out.println(list.deleteByValue(v) ? "Value deleted." : "Value not found.");
                        break;
                    }
                    case 5: {
                        if (list.isEmpty()) { System.out.println("List is empty. Nothing to search."); break; }
                        int key = InputHelper.readInt("Enter value to search: ");
                        long t0 = System.nanoTime();
                        int pos = list.search(key);
                        long time = System.nanoTime() - t0;
                        System.out.println(pos == -1 ? "Value not found." : "Found at position " + pos + ".");
                        System.out.println("Steps: " + list.getLastSearchSteps());
                        tracker.add("Search", "Linked List Search", "O(n)", list.getLastSearchSteps(), time,
                                "List size " + list.size() + ", key " + key + (pos == -1 ? " not found" : " found"));
                        break;
                    }
                    case 6: list.display(); break;
                    case 7: return;
                }
            } catch (IllegalStateException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ------------------------------------------------------------ SEARCHING
    private static void searchMenu() {
        while (true) {
            System.out.println("\n--------------- SEARCHING OPERATIONS ---------------");
            System.out.println("(Searches the array from the Array menu)");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Compare Linear vs Binary");
            System.out.println("4. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 4);
            if (c == 4) return;
            if (array.isEmpty()) {
                System.out.println("Array is empty. Add elements (or generate a random array) in the Array menu first.");
                continue;
            }
            if (c == 1) {
                int key = InputHelper.readInt("Enter value to search: ");
                SearchOperations.Result r = SearchOperations.linearSearch(array.toArray(), key);
                reportSearch("Linear Search", r, key);
                recordSearch("Linear Search", "O(n)", r, key);
            } else {
                if (!ensureSorted()) continue;
                int key = InputHelper.readInt("Enter value to search: ");
                SearchOperations.Result bin = SearchOperations.binarySearch(array.toArray(), key);
                if (c == 2) {
                    reportSearch("Binary Search", bin, key);
                    recordSearch("Binary Search", "O(log n)", bin, key);
                } else {
                    SearchOperations.Result lin = SearchOperations.linearSearch(array.toArray(), key);
                    reportSearch("Linear Search", lin, key);
                    reportSearch("Binary Search", bin, key);
                    recordSearch("Linear Search", "O(n)", lin, key);
                    recordSearch("Binary Search", "O(log n)", bin, key);
                    System.out.println("\nBinary search needed " + bin.steps + " step(s) versus " + lin.steps
                            + " for linear search because it halves the search range each step (O(log n) vs O(n)).");
                }
            }
        }
    }

    /** Binary search needs sorted data. Offer to sort, otherwise cancel. */
    private static boolean ensureSorted() {
        if (array.isSorted()) return true;
        System.out.println("Binary search requires a sorted array, and the array is NOT sorted.");
        System.out.println("1. Sort the array now");
        System.out.println("2. Cancel");
        if (InputHelper.readIntInRange("Enter your choice: ", 1, 2) == 1) {
            array.sort();
            System.out.println("Array sorted.");
            return true;
        }
        return false;
    }

    private static void reportSearch(String name, SearchOperations.Result r, int key) {
        System.out.println(name + ": " + (r.found() ? key + " found at index " + r.index : key + " not found")
                + " | steps: " + r.steps + " | time: " + String.format("%.2f", r.nanos / 1000.0) + " us");
    }

    private static void recordSearch(String algo, String complexity, SearchOperations.Result r, int key) {
        tracker.add("Search", algo, complexity, r.steps, r.nanos,
                "Array size " + array.size() + ", key " + key + (r.found() ? " found" : " not found"));
    }

    // ---------------------------------------------------------------- GRAPH
    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Load Sample Graph");
            System.out.println("7. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 7);
            try {
                switch (c) {
                    case 1: {
                        String name = InputHelper.readNonEmptyString("Enter vertex name: ");
                        graph.addVertex(name);
                        System.out.println("Vertex '" + name + "' added.");
                        break;
                    }
                    case 2: {
                        if (graph.vertexCount() < 2) { System.out.println("Add at least two vertices first."); break; }
                        String a = InputHelper.readNonEmptyString("Enter first vertex: ");
                        String b = InputHelper.readNonEmptyString("Enter second vertex: ");
                        graph.addEdge(a, b);
                        System.out.println("Edge added.");
                        break;
                    }
                    case 3: graph.display(); break;
                    case 4: case 5: {
                        if (graph.isEmpty()) { System.out.println("Graph is empty. Add vertices first."); break; }
                        String start = InputHelper.readNonEmptyString("Enter start vertex: ");
                        runTraversal(c == 4, start);
                        break;
                    }
                    case 6: graph.loadSample(); System.out.println("Sample graph loaded (vertices A-F)."); graph.display(); break;
                    case 7: return;
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static Graph.TraversalResult runTraversal(boolean bfs, String start) {
        Graph.TraversalResult r = bfs ? graph.bfs(start) : graph.dfs(start);
        String name = bfs ? "BFS" : "DFS";
        System.out.println(name + " order: " + r.order);
        System.out.println("Vertices visited: " + r.visited + " | steps: " + r.steps
                + " | time: " + String.format("%.2f", r.nanos / 1000.0) + " us");
        tracker.add("Graph Traversal", name, "O(V + E)", r.steps, r.nanos,
                "Start " + start + ", " + graph.vertexCount() + " vertices, " + graph.edgeCount() + " edges");
        return r;
    }

    // ------------------------------------------------- PERFORMANCE COMPARISON
    private static void performanceComparison() {
        System.out.println("\n--------------- PERFORMANCE COMPARISON ---------------");
        int n = InputHelper.readIntInRange("Array size for the search test (10 - 1000000): ", 10, 1000000);
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i * 2;      // sorted data
        int key = sorted[n - 1];                               // worst case for linear search
        SearchOperations.Result lin = SearchOperations.linearSearch(sorted, key);
        SearchOperations.Result bin = SearchOperations.binarySearch(sorted, key);
        System.out.println("Searching for the LAST element (" + key + ") in a sorted array of " + n + " elements:");
        reportSearch("Linear Search", lin, key);
        reportSearch("Binary Search", bin, key);
        tracker.add("Search", "Linear Search", "O(n)", lin.steps, lin.nanos, "Benchmark, size " + n + ", worst case");
        tracker.add("Search", "Binary Search", "O(log n)", bin.steps, bin.nanos, "Benchmark, size " + n + ", worst case");

        if (graph.isEmpty()) {
            System.out.println("\nGraph is empty, so a sample graph is loaded for the traversal test.");
            graph.loadSample();
        }
        String start = graph.firstVertex();
        System.out.println("\nTraversing the graph from vertex '" + start + "':");
        runTraversal(true, start);
        runTraversal(false, start);

        tracker.printComparison();
        System.out.println("\nWhy the results differ:");
        System.out.println(" - Linear search checks elements one by one: O(n), so " + lin.steps + " steps here.");
        System.out.println(" - Binary search halves the range each step: O(log n), only " + bin.steps + " steps, but needs sorted data.");
        System.out.println(" - BFS and DFS both visit every vertex and examine every edge: O(V + E).");
        System.out.println("   They differ in ORDER (queue vs recursion/stack), not in total work.");
        System.out.println(" - Timings are noisy on small inputs (JIT warm-up), so steps are the more reliable measure.");
    }
}
