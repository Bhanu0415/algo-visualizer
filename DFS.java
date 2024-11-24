import javax.swing.*;
import java.awt.*;

public class DFS extends JFrame {
    private GraphPanel map;
    
    public DFS() {
        map = new GraphPanel(12);
        map.generate_random();
        map.dfs_or_bfs = 0;
        JButton start_dfs_button = new JButton("Start DFS");
        start_dfs_button.addActionListener(e -> {
            try {
                int startVertex = Integer.parseInt(JOptionPane.showInputDialog("Enter the starting vertex (0 to 11):"));
                int targetVertex = Integer.parseInt(JOptionPane.showInputDialog("Enter the target vertex (0 to 11):"));
                if (startVertex >= 0 && startVertex < 12 && targetVertex >= 0 && targetVertex < 12) {
                    map.startDFS(startVertex, targetVertex);
                }
                else throw new NumberFormatException();
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid vertices between 0 and 11.");
            }
        });

        JButton random_graph_button = new JButton("Random Graph");
        random_graph_button.addActionListener(e -> map.generate_random());

        JButton clear_button = new JButton("Clear Graph");
        clear_button.addActionListener(e -> map.clear());

        JButton add_edge_button = new JButton("Add Edge");
        add_edge_button.addActionListener(e -> {
            try {
                String input_1 = JOptionPane.showInputDialog("Enter vertex 1 (0 to 11):");
                String input_2 = JOptionPane.showInputDialog("Enter vertex 2 (0 to 11):");
                if (input_1 == null || input_2 == null) return;
                int vertex_1 = Integer.parseInt(input_1);
                int vertex_2 = Integer.parseInt(input_2);
                if (vertex_1 >= 0 && vertex_1 < 12 && vertex_2 >= 0 && vertex_2 < 12) 
                    map.add_edge(vertex_1, vertex_2);
                else throw new IllegalArgumentException();
            } 
            catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(null, "Please enter a valid vertices between 0 and 11.");
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
                    map.remove_edge(vertex_1, vertex_2);
                else throw new IllegalArgumentException();
            } 
            catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(null, "Please enter a valid vertices between 0 and 11.");
            }
        }
        );

        JPanel buttons_panel = new JPanel();
        buttons_panel.add(start_dfs_button);
        buttons_panel.add(random_graph_button);
        buttons_panel.add(clear_button);
        buttons_panel.add(add_edge_button);
        buttons_panel.add(remove_edge_button);

        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);
        add(buttons_panel, BorderLayout.SOUTH);

        setTitle("DFS Graph Visualizer");
        setSize(1000, 800);
        setVisible(true);
    }

    public static void DepthFisrt() {
        SwingUtilities.invokeLater(() -> new DFS());
    }
}
