
import java.util.ArrayList;
import java.util.List;

public class Graph {

    // Stores vertex names.
    private final List<String> vertices = new ArrayList<>();

    // Stores the index of each vertex's neighbours.
    private final List<List<Integer>> adj = new ArrayList<>();

    private int edgeCount = 0;

    // Result returned to Main.java after BFS or DFS.
    public static class TraversalResult {
        public final List<String> order;
        public final int visited;
        public final int steps;
        public final long nanos;

        public TraversalResult(
                List<String> order,
                int visited,
                int steps,
                long nanos) {

            this.order = order;
            this.visited = visited;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    // Find a vertex's index.
    private int indexOf(String name) {
        return vertices.indexOf(name);
    }

    // Add a vertex.
    public void addVertex(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Vertex name cannot be empty.");
        }

        name = name.trim();

        if (indexOf(name) != -1) {
            throw new IllegalArgumentException(
                    "Vertex already exists: " + name);
        }

        vertices.add(name);
        adj.add(new ArrayList<>());
    }

    // Add an undirected edge.
    public void addEdge(String a, String b) {
        int i = indexOf(a);
        int j = indexOf(b);

        if (i == -1 || j == -1) {
            throw new IllegalArgumentException(
                    "Both vertices must exist.");
        }

        if (i == j) {
            throw new IllegalArgumentException(
                    "Self-loops are not allowed.");
        }

        if (adj.get(i).contains(j)) {
            throw new IllegalArgumentException(
                    "Edge already exists.");
        }

        adj.get(i).add(j);
        adj.get(j).add(i);

        edgeCount++;
    }

    // Display the graph.
    public void display() {
        if (isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.println("Adjacency List:");

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " -> ");

            for (int neighbour : adj.get(i)) {
                System.out.print(vertices.get(neighbour) + " ");
            }

            System.out.println();
        }
    }

    // Number of vertices.
    public int vertexCount() {
        return vertices.size();
    }

    // Number of undirected edges.
    public int edgeCount() {
        return edgeCount;
    }

    // Check whether the graph is empty.
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    // Return the first vertex.
    public String firstVertex() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Graph is empty.");
        }

        return vertices.get(0);
    }

    // Breadth-First Search using the project's MyQueue.
    public TraversalResult bfs(String start) {
        int startIndex = indexOf(start);

        if (startIndex == -1) {
            throw new IllegalArgumentException(
                    "Starting vertex does not exist: " + start);
        }

        long startTime = System.nanoTime();

        boolean[] visited = new boolean[vertices.size()];
        List<String> order = new ArrayList<>();

        // MyQueue stores integer vertex indexes.
        MyQueue queue = new MyQueue(
                Math.max(10, vertices.size()));

        int pending = 0;
        int steps = 0;

        visited[startIndex] = true;
        queue.enqueue(startIndex);
        pending++;

        while (pending > 0) {
            int current = queue.dequeue();
            pending--;

            order.add(vertices.get(current));
            steps++;

            for (int neighbour : adj.get(current)) {
                steps++;

                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.enqueue(neighbour);
                    pending++;
                }
            }
        }

        long elapsed = System.nanoTime() - startTime;

        return new TraversalResult(
                order, order.size(), steps, elapsed);
    }

    // Depth-First Search using recursion.
    public TraversalResult dfs(String start) {
        int startIndex = indexOf(start);

        if (startIndex == -1) {
            throw new IllegalArgumentException(
                    "Starting vertex does not exist: " + start);
        }

        long startTime = System.nanoTime();

        boolean[] visited = new boolean[vertices.size()];
        List<String> order = new ArrayList<>();

        int[] steps = {0};

        dfsRecursive(startIndex, visited, order, steps);

        long elapsed = System.nanoTime() - startTime;

        return new TraversalResult(
                order, order.size(), steps[0], elapsed);
    }

    private void dfsRecursive(
            int current,
            boolean[] visited,
            List<String> order,
            int[] steps) {

        visited[current] = true;
        order.add(vertices.get(current));

        // Count this vertex as processed.
        steps[0]++;

        for (int neighbour : adj.get(current)) {
            // Count each examined adjacency entry.
            steps[0]++;

            if (!visited[neighbour]) {
                dfsRecursive(
                        neighbour, visited, order, steps);
            }
        }
    }

    // Load the sample graph used for demonstration.
    public void loadSample() {
        vertices.clear();
        adj.clear();
        edgeCount = 0;

        addVertex("A");
        addVertex("B");
        addVertex("C");
        addVertex("D");
        addVertex("E");
        addVertex("F");

        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "D");
        addEdge("C", "D");
        addEdge("C", "E");
        addEdge("D", "F");
        addEdge("E", "F");
    }

    // Remove an undirected edge.
    public void removeEdge(String a, String b) {
        int i = indexOf(a);
        int j = indexOf(b);

        if (i == -1 || j == -1) {
            throw new IllegalArgumentException(
                    "Both vertices must exist.");
        }

        if (!adj.get(i).remove(Integer.valueOf(j))) {
            throw new IllegalArgumentException(
                    "Edge does not exist.");
        }

        adj.get(j).remove(Integer.valueOf(i));
        edgeCount--;
    }
}
