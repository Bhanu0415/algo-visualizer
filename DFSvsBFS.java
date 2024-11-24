import javax.swing.*;
import java.awt.*;

public class DFSvsBFS extends JFrame {
    private GraphPanel dfs_map;
    private GraphPanel bfs_map;

    public DFSvsBFS() {
        dfs_map = new GraphPanel(12);
        bfs_map = new GraphPanel(12);
        dfs_map.generate_random();
        bfs_map.set_nodes(dfs_map.get_nodes());

        dfs_map.dfs_or_bfs = 0;
        bfs_map.dfs_or_bfs = 1;

        JSplitPane split_map = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dfs_map, bfs_map);
        split_map.setResizeWeight(0.5);

        JPanel button_panel = new JPanel();
        JButton start_button = new JButton("Start DFS and BFS");
        start_button.addActionListener(e -> {
            try {
                int start_vertex = Integer.parseInt(JOptionPane.showInputDialog("Enter the starting vertex (0 to 11):"));
                int target_vertex = Integer.parseInt(JOptionPane.showInputDialog("Enter the target vertex (0 to 11):"));
                if (start_vertex >= 0 && start_vertex < 12 && target_vertex >= 0 && target_vertex < 12) {
                    dfs_map.startDFS(start_vertex, target_vertex);
                    bfs_map.startBFS(start_vertex, target_vertex);
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Please enter valid vertices between 0 and 11.");
            }
        });

        JButton random_generate = new JButton("Random Graph");
        random_generate.addActionListener(e -> {
            dfs_map.generate_random();
            bfs_map.set_nodes(dfs_map.get_nodes());
            dfs_map.repaint();
            bfs_map.repaint();
        });

        
        JButton clear_button = new JButton("Clear Graph");
        clear_button.addActionListener(e -> {
            dfs_map.clear();
            bfs_map.clear();
        }
        );

        JButton add_edge_button = new JButton("Add Edge");
        add_edge_button.addActionListener(e -> {
            try {
                String input_1 = JOptionPane.showInputDialog("Enter vertex 1 (0 to 11):");
                String input_2 = JOptionPane.showInputDialog("Enter vertex 2 (0 to 11):");
                if (input_1 == null || input_2 == null) return;
                int vertex_1 = Integer.parseInt(input_1);
                int vertex_2 = Integer.parseInt(input_2);
                if (vertex_1 >= 0 && vertex_1 < 12 && vertex_2 >= 0 && vertex_2 < 12)
                    {
                        dfs_map.add_edge(vertex_1, vertex_2);
                        bfs_map.add_edge(vertex_1,vertex_2);
                    }
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(null, "Please enter valid vertices between 0 and 11.");
            }
        }
        );

        JButton remove_edge_button = new JButton("Remove Edge");
        remove_edge_button.addActionListener(e -> {
            try {
                String input_1 = JOptionPane.showInputDialog("Enter vertex 1 (0 to 11):");
                String input_2 = JOptionPane.showInputDialog("Enter vertex 2 (0 to 11):");
                if (input_1 == null || input_2 == null) return;
                int vertex_1 = Integer.parseInt(input_1);
                int vertex_2 = Integer.parseInt(input_2);
                if (vertex_1 >= 0 && vertex_1 < 12 && vertex_2 >= 0 && vertex_2 < 12)
                {
                    dfs_map.remove_edge(vertex_1, vertex_2);
                    bfs_map.remove_edge(vertex_1,vertex_2);
                }
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(null, "Please enter valid vertices between 0 and 11.");
            }
        }
        );

        button_panel.add(start_button);
        button_panel.add(random_generate);
        button_panel.add(clear_button);
        button_panel.add(add_edge_button);
        button_panel.add(remove_edge_button);

        setLayout(new BorderLayout());
        add(button_panel, BorderLayout.SOUTH);
        add(split_map, BorderLayout.CENTER);

        setTitle("DFS vs BFS Graph Comparison");
        setSize(1920, 820);
        setVisible(true);
    }

    public static void DFSvsBFS() {
        SwingUtilities.invokeLater(() -> new DFSvsBFS());
    }
}
