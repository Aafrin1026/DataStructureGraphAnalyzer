# Data Structure & Graph Performance Analyzer

**Module:** CIT300 Data Structures and Algorithms - Graded Practical Assignment 2 (Week 12)
**Language:** Java 
**Type:** Console-based application

## Project Description
A single integrated console application that demonstrates the practical use of arrays, stacks,
queues, linked lists, searching algorithms and graphs, and compares their performance using
step counts and execution time. All data structures are implemented from scratch
(no `java.util.Stack`, `LinkedList` or `Queue`).

## Team Members

**Student Name:** H.Aafrin Banu (Group Leader)
**Student ID:** 23DA2-0659
**Assigned Responsibility:** Linked List, Main System Integration and Performance Comparison
**Individual Contribution:**
- Implemented `MyLinkedList` with an inner `Node` class (insert at head/tail/position, delete by value, search with step counting, display)
- Implemented `Main` (main menu, all submenus, integration of every module)
- Implemented `InputHelper` (input validation) and `PerformanceTracker` (Performance Comparison and Display All Results)
- Set up the GitHub repository, reviewed pull requests, tested the full system and wrote the README

**Student Name:** AF.Hilma
**Student ID:** 23DA2-0981
**Assigned Responsibility:** Array and Searching
**Individual Contribution:**
- Implemented `ArrayOperations` (insert at end/position, delete by position/value, display, sort, random generation)
- Implemented `SearchOperations` (linear search and binary search with step counting and timing)
- Tested array and searching with empty, invalid and unsorted inputs
- Integrated the Array and Searching menus with the main application

**Student Name:** RF.Raseedha
**Student ID:** 23DA2-0959
**Assigned Responsibility:** Stack and Queue
**Individual Contribution:**
- Implemented `MyStack` (push, pop, peek, display, overflow and underflow handling)
- Implemented `MyQueue` as a circular array (enqueue, dequeue, peek, display, overflow and underflow handling)
- Tested empty and full conditions for both structures
- Integrated the Stack and Queue menus with the main application

**Student Name:** JF.Samrootha
**Student ID:** 23DA2-0813
**Assigned Responsibility:** Graph
**Individual Contribution:**
- Implemented `Graph` using an adjacency list (add vertex, add edge, display graph, sample graph)
- Implemented BFS traversal (using the custom queue) and DFS traversal with step counting and timing
- Tested graph functionality with invalid vertices, duplicate edges and an empty graph
- Integrated the Graph module with the main application

## Technologies Used
Java (JDK 11 or newer), Git and GitHub

## Main System Features
1. Array operations: insert (end/position), delete (position/value), linear search, display, sort, random generation
2. Stack operations: push, pop, peek, display, with overflow and underflow handling
3. Queue operations: enqueue, dequeue, peek, display (circular array), with overflow and underflow handling
4. Linked list operations: insert (head/tail/position), delete, search, display
5. Searching: linear search, binary search (offers to sort first) and a side-by-side comparison
6. Graph operations: add vertex, add edge, display, BFS, DFS, load sample graph
7. Performance comparison: steps, time and complexity for linear vs binary search and BFS vs DFS
8. Display all results recorded during the session
9. Full input validation and handling of empty or invalid operations

## Project Structure
```
src/
  Main.java               main menu, submenus, integration
  InputHelper.java        input validation
  ArrayOperations.java    array
  MyStack.java            stack
  MyQueue.java            queue (circular array)
  MyLinkedList.java       singly linked list
  SearchOperations.java   linear and binary search
  Graph.java              adjacency-list graph, BFS, DFS
  PerformanceTracker.java stores and prints results
```

## How to Run
Run these commands from inside the project folder (the one that contains `src`).
```bash
javac -d out src/*.java
java -cp out Main
```
Or open the `src` folder in IntelliJ IDEA, Eclipse or VS Code and run `Main.java`.

## Complexity Summary
| Operation | Complexity |
|---|---|
| Linear search | O(n) |
| Binary search (sorted data) | O(log n) |
| Array insert/delete (middle) | O(n) |
| Stack push/pop/peek | O(1) |
| Queue enqueue/dequeue | O(1) |
| Linked list insert at head | O(1) |
| Linked list search/delete by value | O(n) |
| BFS / DFS (adjacency list) | O(V + E) |
