import java.util.ArrayList;

/** Stores every measured result so options 7 and 8 can display them. */
public class PerformanceTracker {
    private static class Record {
        String operation, algorithm, complexity, details;
        int steps;
        long nanos;
    }

    private final ArrayList<Record> records = new ArrayList<>();

    public void add(String operation, String algorithm, String complexity, int steps, long nanos, String details) {
        Record r = new Record();
        r.operation = operation; r.algorithm = algorithm; r.complexity = complexity;
        r.steps = steps; r.nanos = nanos; r.details = details;
        records.add(r);
    }

    public boolean isEmpty() { return records.isEmpty(); }

    /** Prints every result recorded in this session. */
    public void printAll() {
        System.out.println("\n=============================================================================================");
        System.out.println("ALL RESULTS (this session)");
        System.out.println("=============================================================================================");
        if (records.isEmpty()) {
            System.out.println("No results recorded yet. Run searches, traversals or the performance comparison first.");
            return;
        }
        System.out.printf("%-4s %-16s %-24s %-9s %-12s %s%n", "#", "Operation", "Algorithm", "Steps", "Time (us)", "Details");
        System.out.println("---------------------------------------------------------------------------------------------");
        int n = 1;
        for (Record r : records)
            System.out.printf("%-4d %-16s %-24s %-9d %-12.2f %s%n", n++, r.operation, r.algorithm, r.steps, r.nanos / 1000.0, r.details);
        System.out.println("=============================================================================================");
    }

    /** Prints the latest result for each key algorithm side by side. */
    public void printComparison() {
        String[][] rows = {
            {"Search", "Linear Search"}, {"Search", "Binary Search"},
            {"Graph Traversal", "BFS"}, {"Graph Traversal", "DFS"}
        };
        System.out.println("\n=====================================================================");
        System.out.println("PERFORMANCE COMPARISON (latest result for each algorithm)");
        System.out.println("=====================================================================");
        System.out.printf("%-17s %-15s %-10s %-12s %s%n", "Operation", "Algorithm", "Steps", "Time (us)", "Complexity");
        System.out.println("---------------------------------------------------------------------");
        for (String[] row : rows) {
            Record latest = null;
            for (Record r : records)
                if (r.operation.equals(row[0]) && r.algorithm.startsWith(row[1])) latest = r;
            if (latest == null)
                System.out.printf("%-17s %-15s %-10s %-12s %s%n", row[0], row[1], "-", "-", "(not run yet)");
            else
                System.out.printf("%-17s %-15s %-10d %-12.2f %s%n", row[0], row[1], latest.steps, latest.nanos / 1000.0, latest.complexity);
        }
        System.out.println("=====================================================================");
    }
}
