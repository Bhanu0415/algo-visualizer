
# Algorithm Visualizer

**Algorithm Visualizer** is a desktop application that provides interactive and visual demonstrations of various algorithms. This tool is designed for learners and educators to understand complex algorithms intuitively with step-by-step visualization.

## Features

- **Graph Algorithms**
  - **BFS (Breadth-First Search):** Interactive traversal and pathfinding.
  - **DFS (Depth-First Search):** Explore graph depth-first.
  - Compare BFS and DFS.

- **Binary Search Tree (BST) Operations**
  - Visualize BST insertion, deletion, and search processes.
  
- **Sorting Algorithms**
  - Bubble Sort, Insertion Sort, Selection Sort, Merge Sort, Quick Sort, Bucket Sort.
  - Visual comparison of sorting methods.
  
- **Interactive Visualizations**
  - Dynamic graph and BST operations.
  - Real-time updates and animations.
  
## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/algorithm-visualizer.git
   cd algorithm-visualizer
   ```

2. Ensure you have Java installed. This project uses **Java Swing** for the graphical interface. You can download the latest version of Java from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

3. Compile the project using any Java IDE (e.g., IntelliJ, Eclipse) or the command line:
   ```bash
   javac *.java
   ```

4. Run the main file to start the application:
   ```bash
   java ProjectMain
   ```

## Usage

1. Open the application.
2. Choose the algorithm you wish to visualize.
3. Interact using buttons and input prompts to manipulate the graph, BST, or sorting data.
4. Observe the animation and output in real time.

## File Structure

- **BFS.java**: Implements the Breadth-First Search algorithm with a graphical interface for visualization.
- **DFS.java**: Implements Depth-First Search visualization.
- **BST_Insert.java**: Demonstrates the BST insertion process with animation.
- **BST_Delete.java**: Handles BST node deletion with step-by-step color-coded visualization.
- **BST_Search.java**: Provides search functionality within a BST.
- **BSTVisualizer.java**: The graphical interface and utility functions for visualizing BST operations.
- **Sorting.java**: Shared utilities for sorting algorithm visualizations.
- **BubbleSort.java, InsertionSort.java, SelectionSort.java, MergeSort.java, QuickSortVisualize.java, BucketSort.java**: Individual sorting algorithm implementations.
- **BucketSortVisualizer.java**: Special visualization for Bucket Sort.
- **GraphPanel.java**: Utility class for rendering graph structures and handling edges and nodes.
- **DFSvsBFS.java**: Compares DFS and BFS algorithms.
- **Node.java**: Defines a generic node structure used in trees and graphs.
- **ProjectMain.java**: The entry point for the application.

## Contribution

Contributions are welcome! If you find a bug or want to add a new feature:
1. Fork the repository.
2. Create a branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.
